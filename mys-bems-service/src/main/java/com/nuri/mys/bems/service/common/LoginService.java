package com.nuri.mys.bems.service.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.domain.jwt.entity.Token;
import com.nuri.mys.bems.domain.jwt.entity.User;
import com.nuri.mys.bems.domain.jwt.entity.UserInfoRes;
import com.nuri.mys.bems.domain.logic.common.LoginLogic;
import com.nuri.mys.bems.domain.store.common.CommonStore;
import com.nuri.mys.bems.domain.store.common.LoginStore;
import com.nuri.mys.bems.domain.store.common.TokenStore;
import com.nuri.mys.bems.service.jwt.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class LoginService implements LoginLogic {

    @Autowired
    private LoginStore loginStore;

    @Autowired
    private CommonStore commonStore;

    @Autowired
    private TokenStore tokenStore;

    @Value("${spring.privateKeyString}")
    private String privateKeyString;

    @Value("${spring.publicKeyString}")
    private String publicKeyString;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    CommonCodeRes code = new CommonCodeRes();

    @Override
    public LoginResultRes loginCheck(LoginCheckDto model, HttpServletRequest request) {
        LoginResultRes result = new LoginResultRes();
        try {
            // 로그인 설정 정책가져오기
            List<LoginSystemSettingForLoginRes> sysconfig = loginStore.getSystemSettingForLogin("essSysConfig");
            String useLoginSMSYN = "N"; //로그인 시 SMS 인증 사용 여부
            String useLoginEMAILYN = "N"; //로그인 시 이메일 인증 사용 여부
            String useLoginTelegramYN = "N"; //로그인 시 텔레그램 인증 사용 여부

            String useLoginFailCntYN = "N"; //로그인 시 실패 카운트 체크할지 여부
            String loginFailCntLimit = "0"; //로그인 시 실패 카운트 체크
            for (int i = 0; i < sysconfig.size(); i++) {
                if (sysconfig.get(i).getVkey().equals("useLoginSMSYN")) {
                    useLoginSMSYN = sysconfig.get(i).getVvalue();
                } else if (sysconfig.get(i).getVkey().equals("useLoginEMAILYN")) {
                    useLoginEMAILYN = sysconfig.get(i).getVvalue();
                } else if (sysconfig.get(i).getVkey().equals("useLoginTelegramYN")) {
                    useLoginTelegramYN = sysconfig.get(i).getVvalue();
                } else if (sysconfig.get(i).getVkey().equals("useLoginFailCntYN")) {
                    useLoginFailCntYN = sysconfig.get(i).getVvalue();
                } else if (sysconfig.get(i).getVkey().equals("loginFailCntLimit")) {
                    loginFailCntLimit = sysconfig.get(i).getVvalue();
                }
            }
            // UI단에서 암호화한 RSA 복고화
            // 패스워드 암호화 하여 LoginUserRes에 저장하는 부분
            // 선 처리: UI단에서 application.yml 에 선언한 publicKeyString 로 암호화하여 서버에 전달
            // 로그인 시 암호 비교 처리 : 서버에서는 암호화하여 DB에 저장 된 비밀번호를 가져와 서버의 privateKeyString으로 RSA 복호화하여 비교

            // publicKeyString : 평문 데이터를 RSA 암호화 시 사용
            // privateKeyString : 암호화된 데이터를 RSA 복호화 시 사용

            String pass = Base64AndRSAdecrypt(privateKeyString, model.getPassword()); // 복호화 진행
            model.setPassword(pass);

            log.info("pass 복호화");

            LoginUserRes dbData = loginStore.loginCheck(model);

            // a.계정 정보가 없는 경우 실패코드 반환
            if(dbData == null) {
                result.setUserId(model.getUserId());
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("ID_FAIL");
//                result.setFailContents(code.getDetailCd());
                result.setFailContents(code.getCdEngNm());
                saveLoginLog(request, model.getUserId(), "ID_FAIL", "web");
                return result;
            }

            // b. 계정 정보가 있지만 비밀번호가 맞지 않는 경우
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(model.getPassword(), dbData.getPassword())) {
                // b-1. 임시 비밀번호 발급을 받은 상태에서 과거 비밀번호로 로그인 시 임시비밀번호로 로그인 알림
                if("Y".equals(dbData.getTempPassYn()) && passwordEncoder.matches(model.getPassword(), dbData.getPastPassNo1())) {
                    result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                    code = commonStore.getMessageStatusCodeNew("TEMPORARY_PASS_LOGIN");
//                    result.setFailContents(code.getDetailCd());
                    result.setFailContents(code.getCdEngNm());
                    return result;
                }
                // b-2. 로그인 시 실패 카운트 체크 하지 않는 경우 실패코드 반환
                if(useLoginFailCntYN.equals("N")) {
                    result.setUserId(model.getUserId());
                    result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                    code = commonStore.getMessageStatusCodeNew("PASS_FAIL");
//                    result.setFailContents(code.getDetailCd());
                    result.setFailContents(code.getCdEngNm());
                    saveLoginLog(request, model.getUserId(), "PASS_FAIL", "web");
                    return result;
                }
                // b-3. 로그인 시 실패 카운트 체크하는 경우 카운트+1, 횟수 초과 시 실패코드 반환
                if (dbData.getLoginFailCnt() >= Integer.parseInt(loginFailCntLimit)) {
                    result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                    code = commonStore.getMessageStatusCodeNew("FAIL_CNT_OVER");
//                    result.setFailContents(code.getDetailCd());
                    result.setFailContents(code.getCdEngNm());
                    saveLoginLog(request, model.getUserId(), "FAIL_CNT_OVER", "web");
                    return result;
                } else {
                    LoginUserRes temp = new LoginUserRes();
                    temp.setUserId(dbData.getUserId());
                    temp.setLoginFailCnt(dbData.getLoginFailCnt()+1);
                    loginStore.updateLoginFailCnt(temp);
                    result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                    code = commonStore.getMessageStatusCodeNew("PASS_FAIL");
//                    result.setFailContents(code.getDetailCd());
                    result.setFailContents(code.getCdEngNm());
                    saveLoginLog(request, model.getUserId(), "PASS_FAIL", "web");
                    return result;
                }
            }

            // c. 비밀번호가 맞아 정상 로그인 시
//            // c-1. 시스템 설정의 2차 인증 사용하는 경우 인증번호 발송
//            if ("Y".toUpperCase().equals(useLoginSMSYN) || "Y".toUpperCase().equals(useLoginEMAILYN) || "Y".equals(useLoginTelegramYN)) {
//                int smscode = getRandomCode(); // sms 코드 랜덤 생성
//                if ("Y".toUpperCase().equals(useLoginSMSYN)) {
//                    smsNumberSend(dbData, smscode); //Sms 인증번호 발송
//                } else if("Y".toUpperCase().equals(useLoginEMAILYN)) {
//                    emailNumberSend(dbData, smscode); //Email 인증번호 발송
//                } else if("Y".toUpperCase().equals(useLoginTelegramYN)) {
//                    umsSend(dbData, smscode, "telegramInfo"); // Telegram 인증번호 발송
//                }
//                result.setUserId(model.getUserId());
//                result.setStatus(commonStore.getMessageStatusCode("SESS"));
//                result.setLoginAfterData(commonStore.getMessageStatusCode("SMS_SEND"));
//            } else {
                // c-2. 2차 인증 사용안하는 경우 토큰 생성
                String token = callTokenService(dbData); //token 생성
                result.setLoginAfterData(token);
                result.setUserId(dbData.getUserId());

                code = commonStore.getMessageStatusCodeNew("SESS");
                result.setStatus(code.getDetailCd());
//                result.setPopContents(code.getCdEngNm());

                LoginUserRes temp = new LoginUserRes();
                temp.setUserId(dbData.getUserId());
                temp.setLoginFailCnt(0);
                loginStore.updateLoginFailCnt(temp);

                // 로그인 이력 저장
                saveLoginLog(request, model.getUserId(), "SESS", "web");

                Date date = new Date();
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date); // 10분 더하기
                cal.add(Calendar.MONTH, -6);
                date = new Date(cal.getTimeInMillis()); // Date(long date)

                // 임시비밀번호로 로그인 or 6개월 동안 변경 안한 패스워드일 경우 비밀번호 변경 알림
                if (dbData.getTempPassYn().toUpperCase().equals("Y")) {
                    code = commonStore.getMessageStatusCodeNew("PASS_CHANGE");
//                    result.setFailContents(code.getDetailCd());
                    result.setPopContents(code.getCdEngNm());
                } else if (dbData.getPassUpdateDt() != null && dbData.getPassUpdateDt().getTime() < date.getTime()) {
                    code = commonStore.getMessageStatusCodeNew("PASS_UNCHANGED_FOR_6MONTHS");
//                    result.setFailContents(code.getDetailCd());
                    result.setPopContents(code.getCdEngNm());
                }
//            }
            return result;
        } catch (Exception ex) {
            result.setUserId(model.getUserId());
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
//            result.setFailContents(ex.getMessage());
            result.setFailContents(code.getCdEngNm());
            saveLoginLog(request, model.getUserId(), "FAIL", "web");
            return result;
        }
    }

//    @Override
//    public String getTokenInfo(Map<String, Object> param) {
//        return getToken(param);
//    }

    @Override
    public LoginResultRes logout(LogoutDto model, HttpServletRequest request, String systemDivision) {
        // logout 시 jwt 는 쿠키에서 삭제
        LoginResultRes result = new LoginResultRes();
        String status = "SESS";
        String userId = model.getUserId();
        String cd = commonStore.getMessageStatusCode(status);
        System.out.println(cd.substring(0, 5));
        if (!(cd.substring(0, 6).toUpperCase().equals("STATUS"))) {
            cd = "System Error";
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vUserId", userId);
        UserInfoRes user = loginStore.getUserInfo(params);
        params = new HashMap<String, Object>();
        //params.put("N_USER_LOGIN_SEQ", ); 자동 증가
        params.put("vUserId", userId);
        if (user != null) {
            params.put("vUserNm", user.getvUserNm());
            params.put("vPermGrId", user.getvPermGrId());
            params.put("vPermGrNm", user.getvPermGrNm());
            params.put("vDeviceGb", systemDivision);
            //params.put("V_SITE_ID", );
            //params.put("V_SITE_NM", );
            params.put("vLoginIp", getClientIpAddr(request));
            //params.put("T_LOGIN_DT", user.get);
            params.put("vLoginGb", "LOGOUT");
            params.put("vLoginRltCd", cd);
            params.put("vLoginRltDesc", status);
            params.put("vCreateId", user.getvUserId());
            params.put("vUpdateId", user.getvUserId());
        } else {
            params.put("vUserNm", "");
            params.put("vPermGrId", "");
            params.put("vPermGrNm", "");
            params.put("vDeviceGb", systemDivision);
            //params.put("V_SITE_ID", );
            //params.put("V_SITE_NM", );
            params.put("vLoginIp", getClientIpAddr(request));
            //params.put("T_LOGIN_DT", user.get);
            params.put("vLoginGb", "LOGOUT");
            params.put("vLoginRltCd", cd);
            params.put("vLoginRltDesc", status);
            params.put("vCreateId", "");
            params.put("vUpdateId", "");
        }
        System.out.println(params);
        int i = loginStore.saveLoginLog(params);
        if (i > 0) {
            code = commonStore.getMessageStatusCodeNew("SESS");
            result.setStatus(code.getDetailCd());
//            result.setPopContents(code.getCdEngNm());
        } else {
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public LoginSystemSettingRes getSystemSetting() {
        return loginStore.getSystemSetting();
    }

    @Override
    public String getToken(Map<String, Object> param) {
        JSONObject ob = new JSONObject();
        String token = (String) param.get("token");
        String userId = jwtTokenProvider.getAud(token);
        String role = jwtTokenProvider.getRole(token);
        String id = jwtTokenProvider.getId(token);
//        Token t = tokenStore.findById(id);
        String result = "";
        if (id != null) {
            ob.put("id", id);
            ob.put("userId", userId);
            ob.put("role", role);
            result = ob.toJSONString();
        } else
            result = "fail";
        return result;
    }

    public String checkToken(String token){
        String id = jwtTokenProvider.getId(token);
//        Token t = tokenStore.findById(id);
        if (id != null) {
            return "success";
        }
        return "fail";
    }

    @Override
    public LoginUserInfoRes getUserRole(Authentication authentication) {
        User user = (User)authentication.getPrincipal();

        LoginUserInfoRes result = loginStore.getUserRole(user);
        return result;
    }

    @Override
    public Boolean roleCheck(Authentication authentication) {
        User user = (User)authentication.getPrincipal();

        LoginUserInfoRes result = loginStore.getUserRole(user);
        if(user.getRole().equals(result.getPermId())) {
            return true;
        }
        return false;
    }

//    @Override
//    public ResponseEntity<JSONArray> getPmsUserSmsInfo(JSONObject params) {
//        JSONObject smsInfo = commonStore.getPmsUserSmsInfo(params);
//        String strInfo = smsInfo != null ? smsInfo.toJSONString() : null;
//        JSONArray jsonArray = new JSONArray();
//        if(strInfo != null) {
//            int strLength = strInfo.length(); // ums서비스로 보낼 메세지 길이
//
//            for(int i=0; i<=strLength/100; i++) {
//                String str = "";
//                if(i == strLength/100) {
//                    str = strInfo.substring(i*100, strLength);
//                } else {
//                    str = strInfo.substring(i * 100, i * 100 + 100);
//                }
//                try {
//                    String data = Base64AndRSAencryp(publicKeyString, str);
//                    JSONObject obj = new JSONObject();
//                    obj.put("data", data);
//                    jsonArray.add(obj);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return new ResponseEntity<JSONArray>(jsonArray, HttpStatus.OK);
//    }

    public static int getRandomCode() {
        Random random = new Random();
        int result = random.nextInt(1000000) + 100000;
        if (result > 1000000) {
            result = result - 100000;
        }
        return result;
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Host").split(":")[0];
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public String Base64AndRSAdecrypt(String privateKeyString, String decryptText) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] privateKeyByte = Base64.getDecoder().decode(privateKeyString.getBytes());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        String encrypted = decryptText;
        // 복호화 합니다.
        String decrypted = decryptRSA(encrypted, privateKey);
        System.out.println("decrypted : " + decrypted);

        return decrypted;
    }

    public String decryptRSA(String encrypted, PrivateKey privateKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] byteEncrypted = Base64.getDecoder().decode(encrypted.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytePlain = cipher.doFinal(byteEncrypted);
        String decrypted = new String(bytePlain, "utf-8");
        return decrypted;
    }

    public String Base64AndRSAencryp(String publicKeyString, String encodingText) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] publicKeyByte = Base64.getDecoder().decode(publicKeyString.getBytes());
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyByte);

        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        // 암호화 합니다.
        String encodingData = encryptRSA(encodingText, publicKey);
        System.out.println("encodingData : " + encodingData);

        return encodingData;
    }

    public String encryptRSA(String plainText, PublicKey publicKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytePlain = cipher.doFinal(plainText.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(bytePlain);
        return encrypted;
    }

    public void saveLoginLog(HttpServletRequest request, String userId, String status, String systemDivision) {
        System.out.println("로그인 로그 찍기");
        String cd = commonStore.getMessageStatusCode(status);
        if (!(cd.substring(0, 6).toUpperCase().equals("STATUS"))) {
            cd = "System Error";
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vUserId", userId);
        UserInfoRes user = loginStore.getUserInfo(params);
        params = new HashMap<String, Object>();
        //params.put("N_USER_LOGIN_SEQ", ); 자동 증가
        params.put("vUserId", userId);

        if (user != null) {
            params.put("vUserNm", user.getvUserNm());
            params.put("vPermGrId", user.getvPermGrId());
            params.put("vPermGrNm", user.getvPermGrNm());
            params.put("vDeviceGb", systemDivision);
            //params.put("V_SITE_ID", );
            //params.put("V_SITE_NM", );
            params.put("vLoginIp", getClientIpAddr(request));
            //params.put("T_LOGIN_DT", user.get);
            params.put("vLoginGb", "LOGIN");
            params.put("vLoginRltCd", cd);
            params.put("vLoginRltDesc", status);
            params.put("vCreateId", user.getvUserId());
            params.put("vUpdateId", user.getvUserId());
        } else {
            params.put("vUserNm", "");
            params.put("vPermGrId", "");
            params.put("vPermGrNm", "");
            params.put("vDeviceGb", systemDivision);
            //params.put("V_SITE_ID", );
            //params.put("V_SITE_NM", );
            params.put("vLoginIp", getClientIpAddr(request));
            //params.put("T_LOGIN_DT", user.get);
            params.put("vLoginGb", "LOGIN");
            params.put("vLoginRltCd", cd);
            params.put("vLoginRltDesc", status);
            params.put("vCreateId", "");
            params.put("vUpdateId", "");
        }
        System.out.println(params);
        loginStore.saveLoginLog(params);
    }

    public String callTokenService(LoginUserRes model) {

        // jwt 서비스에서 받기 위해 jsonObject형에 담아서 넘기며, userId, role, url 을 파라미터로 셋팅
        // org.json.simple을 사용한 이유는 jwt 서비스로 넘길 때 url 파라미터를 null로 넘기고 싶은데
        // com.google.gson의 JsonObject.addProperty에는 (string, string) 형태라서 simple json의 JsonObject.put(object, object)을 사용

        JSONObject ob = new JSONObject();
        ob.put("userId", model.getUserId());
        ob.put("role", model.getRole());
        ob.put("url", null);

        // 시스템 설정 셋팅
        List<LoginSystemSettingForLoginRes> systemSetting = loginStore.getSystemSettingForLogin("essSysConfig");
        JSONObject payload = new JSONObject();

        for (int i = 0; i < systemSetting.size(); i++) {
            if (systemSetting.get(i).getVkey().equals("systemName")) {
                payload.put("systemName", systemSetting.get(i).getVvalue());
            } else if (systemSetting.get(i).getVkey().equals("systemNameDesc")) {
                payload.put("systemNameDesc", systemSetting.get(i).getVvalue());
            } else if (systemSetting.get(i).getVkey().equals("systemCompany")) {
            } else if (systemSetting.get(i).getVkey().equals("systemCompany")) {
                payload.put("systemCompany", systemSetting.get(i).getVvalue());
            }
        }

        List<SiteInfoRes> sitelist = loginStore.getSiteInfo(model);
        // 사이트 정보를 가져와서 순서를 보장하기 위해 LinkedHashMap 사용
        LinkedHashMap siteInfoMap = new LinkedHashMap();

        // 사이트별 자원명 및 자원타입 셋팅 ( "PV":"100"...)
        for (int i = 0; i < sitelist.size(); i++) {
            siteInfoMap.put(sitelist.get(i).getvEnergyNm(), sitelist.get(i).getvEnergyType());
        }


        // Gson의 toJsonTree를 사용하기 위해 simple json이 아닌 google Gson 선언
        Gson gson = new Gson();

        payload.put("siteInfo", gson.toJsonTree(siteInfoMap, LinkedHashMap.class).getAsJsonObject());
        // payload = {"systemName":"TEMS","systemCompany":"NURI","systemNameDesc":"Total Energy Management System","siteInfo":{"PSS":"400","ESS":"200","PV":"100","GEO":"600","PEO":"700","PEH":"500"}}

        // value 셋팅을 위해 userId에 매핑된 자원코드 및 자원명을 리스트로 넘김
        ob.put("value", payload);

        // **** 여기서부터 restTemplate을 사용하여, 다른 서비스의 API를 호출하는 부분 ****

        // HttpMessageConverter 는 JSON 데이터를 HTTP BODY에 직접 쓰기 위해 사용
//		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
//
//		//FormHttpMessageConverter가 지원하는 타입은 "application/x-www-form-urlencoded", "multipart/form-data", "multipart/mixed"
//		//StringHttpMessageConverter 지원하는 타입은 "text/plain", "*/*"
//		converters.add(new FormHttpMessageConverter());
//		converters.add(new StringHttpMessageConverter());
//
//		// RestTemplate은 HTTP Client로 REST API를 호출하는 클래스(GET, POST, PUT, DELETE, EXCHANGE 등)
//		RestTemplate restTemplate = new RestTemplate();
//
//		//restTemplate이 지원하는 messageConverters는 7가지인데 이중 위에서 만든 converters 2가지(FormHttpMessageConverter,StringHttpMessageConverter)넣음
//		restTemplate.setMessageConverters(converters);
//
//		// HttpEntity 클래스 : Http 프로토콜을 이용하는 통신의 header와 body 관련 정보를 저장
//		// HttpEntity에 담을 헤더 설정
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		// HttpEntity 안에 body 부분에 위에서 만든 jsonArray data를 넣고 header도 위에서 만든 HttpHeaders를 넣어 서비스 요청.
//		HttpEntity<String> request = new HttpEntity<String>(ob.toJSONString(), headers);
//
//		// 어디 서비스(UMS)로 보낼 지 설정, UMS서비스에서는 urlDevision을 보고 sms, email, telegram, fcm등 구분
//		UrlInfoModel urlParam = new UrlInfoModel();
//		urlParam.setvUrlDevision("tokenServer_v1.0");
//		UrlInfoModel urlData = loginStore.getUrlInfo(urlParam);
//
//		// ums 서비스가 올라가 있는 서버 ip, ums 서비스 포트, 요청 url
//		String url = "http://localhost:30000/api/v1/user/signin/userinfo";
//        String url = urlData.getvIp() + ":" + urlData.getvPort() + urlData.getvUrl();

        // restTemplate.postForObject 메서드는 POST 요청(헤더와 바디)을 보내고 객체로 결과를 리턴받으며
        // 보낼 파라미터는 UMS URL, 헤더 및 바디(json데이터) , String.class
        String result = userSigninUserinfo(ob.toJSONString());

        // jwt 에서 가져오는 정보
        // [ {"role":"ROLE_SYSTEM","userId":"admin","value":{"systemName":"TEMS","systemCompany":"NURI","systemNameDesc":"Total Energy Management System","siteInfo":{"PSS":"400","ESS":"200","PV":"100","GEO":"600","PEO":"700","PEH":"500"}},"url":null}]
        return result;
    }

    public String userSigninUserinfo(String payload) {
//        log.debug("payload, {}", payload);

        // Json의 순서 보장을 위한 Gson 사용
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

        User member = new User();
        member.setUserId(jsonObject.get("userId").getAsString());
        deleteTokenUserinfo(member);
        String token = this.jwtTokenProvider.createTokenUserinfo(jsonObject);
        return token;
    }

    public String deleteTokenUserinfo(User member) {
        List<Token> tokens = this.tokenStore.findByAudience(member.getUserId());
        for (Iterator<Token> iterator = tokens.iterator(); iterator.hasNext(); ) {
            Token token = iterator.next();
            this.tokenStore.deleteById(token.getTokenId());
        }
        return "success";
    }
}
