package com.nuri.mys.bems.service.management;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.common.CommonResultRes;
import com.nuri.mys.bems.domain.entity.control.ControlCapacityDto;
import com.nuri.mys.bems.domain.entity.management.*;
import com.nuri.mys.bems.domain.entity.ums.UmsModel;
import com.nuri.mys.bems.domain.entity.control.ControlEssSettingDto;
import com.nuri.mys.bems.domain.logic.management.ManagementLogic;
import com.nuri.mys.bems.domain.store.common.CommonStore;
import com.nuri.mys.bems.domain.store.common.LoginStore;
import com.nuri.mys.bems.domain.store.management.ManagementStore;
import com.nuri.mys.bems.service.common.CommonService;
import com.nuri.mys.bems.service.common.PagingParamService;
import com.nuri.mys.bems.service.common.PasswordEncoding;
import com.nuri.mys.bems.service.common.UmsService;
import com.nuri.mys.bems.service.control.ControlService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ManagementService implements ManagementLogic {

    private static final Logger log = LogManager.getLogger(ManagementService.class);

    @Autowired
    private ManagementStore managementStore;

    @Autowired
    private LoginStore loginStore;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ControlService controlService;

    @Autowired
    private UmsService umsService;

    @Autowired
    private CommonStore commonStore;

    @Value("${spring.privateKeyString}")
    private String privateKeyString;

    @Value("${spring.publicKeyString}")
    private String publicKeyString;

    ManagementUserDetailRes passwordParam = new ManagementUserDetailRes();
    CommonCodeRes code = new CommonCodeRes();

    public String getSessMessage() {
        code = commonStore.getMessageStatusCodeNew("SESS");
        String result = code.getCdEngNm();
        return result;
    }

    // Connection History
    @Override
    public List<ManagementConnectionHistoryRes> getConnectionHistoryTable(ManagementConnectionHistoryDto params) {
        commonService.periodSearch(params);
        PagingParamService.setPagingParam(params);
        int cnt = managementStore.getConnectionHistoryTableCnt(params);
        List<ManagementConnectionHistoryRes> result = new ArrayList<ManagementConnectionHistoryRes>();
        result = managementStore.getConnectionHistoryTable(params);
        if(result.size() > 0){
            result.get(0).setDataTotalCount(cnt);
        }
        return result;
    }

    @Override
    public List<ManagementUserSystemSettingRes> getSystemSetting() {
        return managementStore.getSystemSetting();
    }

    // User

    @Override
    public List<CommonCodeRes> getPermType() {
        return managementStore.getPermType();
    }

    @Override
    public List<ManagementUserTableDataRes> getUserTable(ManagementUserTableDataDto params) {
        PagingParamService.setPagingParam(params);
        int cnt = managementStore.getUserTableCnt(params);
        List<ManagementUserTableDataRes> result = new ArrayList<ManagementUserTableDataRes>();
        result = managementStore.getUserTable(params);
        if(result.size() > 0){
            result.get(0).setDataTotalCount(cnt);
        }
        // 알람 구분 세팅
        List<ManagementUserSystemSettingRes> settingRes = managementStore.getSystemSetting();
        String telegram = "";
        String sms = "";
        String mail = "";
        for(ManagementUserSystemSettingRes temp : settingRes) {
            switch (temp.getKeyNm()) {
                case "useAlarmTelegram" :
                    telegram = "Y";
                    break;
                case "useAlarmSMS" :
                    sms = "Y";
                    break;
                case "useAlarmEmail" :
                    mail = "Y";
                    break;
            }
        }
        for(ManagementUserTableDataRes temp : result) {
            List<String> alarm = new ArrayList<String>();
            if("Y".equals(temp.getTelegramRecYn()) && telegram.equals("Y")) {
                alarm.add("Telegram");
            }
            if("Y".equals(temp.getEmailRecYn()) && mail.equals("Y")) {
                alarm.add("Mail");
            }
            if("Y".equals(temp.getSmsRecYn()) && sms.equals("Y")) {
                alarm.add("SMS");
            }
            temp.setAlarmClassification(alarm);
        }
        return result;
    }

    @Override
    public CommonResultRes saveUser(ManagementUserSaveDto params) throws Exception {
        CommonResultRes result = new CommonResultRes();
        ManagementUserIdCheckDto dto = new ManagementUserIdCheckDto();
        dto.setObjectUserId(params.getObjectUserId());
        if(idCheck(dto).getStatus().equals("STATUS6")) {
            // 암호화
            params = passwordParamCreate(params);

            List<CommonCodeRes> permType = managementStore.getPermType();
            for(CommonCodeRes temp : permType) {
                if(params.getPermGrId().equals(temp.getDetailCd())) {
                    params.setPermGrNm(temp.getCdEngNm());
                    break;
                }
            }

            int i = managementStore.saveUser(params);
            if (i > 0) {
                result.setStatus(commonStore.getMessageStatusCode("SESS"));
                result.setPopContents(getSessMessage());
            } else {
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("NOT_EXIST_INSERT_DATA");
//                result.setFailContents(code.getDetailCd());
                result.setFailContents(code.getCdEngNm());
            }
        } else{
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("ID_OVERLAP");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public CommonResultRes idCheck(ManagementUserIdCheckDto params) {
        CommonResultRes result = new CommonResultRes();
        int cnt = managementStore.idCheck(params);
        if("admin".equals(params.getObjectUserId()) || "system".equals(params.getObjectUserId())) {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("CREATE_IMPOSSIBILITY");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        } else if(cnt > 0){
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("ID_OVERLAP");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        } else{
            result.setStatus(commonStore.getMessageStatusCode("SESS"));
            result.setPopContents("Check for the availability of the ID.");
        }
        return result;
    }

    @Override
    public CommonResultRes updateUser(ManagementUserUpdateDto params) {
        CommonResultRes result = new CommonResultRes();
        List<CommonCodeRes> permType = managementStore.getPermType();
        for(CommonCodeRes temp : permType) {
            if(params.getPermGrId().equals(temp.getDetailCd())) {
                params.setPermGrNm(temp.getCdEngNm());
                break;
            }
        }
        int i = managementStore.updateUser(params);
        if (i > 0) {
            result.setStatus(commonStore.getMessageStatusCode("SESS"));
            result.setPopContents(getSessMessage());
        } else {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NOT_EXIST_UPDATE_DATA");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public CommonResultRes deleteUser(ManagementUserDeleteDto params) {
        CommonResultRes result = new CommonResultRes();
        int i = managementStore.deleteUser(params);
        if (i > 0) {
            result.setStatus(commonStore.getMessageStatusCode("SESS"));
            result.setPopContents(getSessMessage());
        } else {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NOT_EXIST_DELETE_DATA");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public ManagementUserDetailRes getHeaderUserDetail(ManagementUserHeaderDetailDto params) {
        return managementStore.getHeaderUserDetail(params);
    }

    @Override
    public CommonResultRes saveHeaderUser(ManagementUserHeaderSaveDto params) {
        CommonResultRes result = new CommonResultRes();
        try {
            // 업데이트 시 패스워드 변경
            boolean status = false;
            String passCheck = PasswordCheckLogic(params);
            if(passCheck.equals("false_nowPassFail")){
                status = false;
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("NOW_PASS_NOTEQUALS");
//                result.setFailContents(code.getDetailCd());
                result.setFailContents(code.getCdEngNm());
            } else if(passCheck.equals("false_samePass")){
                status = false;
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("CURRENT_PASS_EQUALS");
//                result.setFailContents(code.getDetailCd());
                result.setFailContents(code.getCdEngNm());
            } else if(passCheck.equals("false_prePass")){
                status = false;
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("PRE_PASS_EQUALS");
//                result.setFailContents(code.getDetailCd());
                result.setFailContents(code.getCdEngNm());
            } else if(passCheck.equals("false_udateDate")){
                status = false;
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("TODAY_PASS_CHANGE");
//                result.setFailContents(code.getDetailCd());
                result.setFailContents(code.getCdEngNm());
            } else if(passCheck.equals("true_pasChange")){
                status = true;
                params.setPassNo(passwordParam.getPassNo());
                params.setPastPassNo1(passwordParam.getPastPassNo1());
                params.setPastPassNo2(passwordParam.getPastPassNo2());
                params.setPastPassNo3(passwordParam.getPastPassNo3());
            } else{
                status = true;
            }

            List<CommonCodeRes> permType = managementStore.getPermType();
            for(CommonCodeRes temp : permType) {
                if(params.getPermGrId().equals(temp.getDetailCd())) {
                    params.setPermGrNm(temp.getCdEngNm());
                    break;
                }
            }

            if(status == true) {
                params.setTempPassYn("N");
                int i = managementStore.updateUserAndPass(params);
                if (i > 0) {
                    result.setStatus(commonStore.getMessageStatusCode("SESS"));
                    result.setPopContents(getSessMessage());
                } else {
                    result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                    code = commonStore.getMessageStatusCodeNew("NOT_EXIST_UPDATE_DATA");
//                    result.setFailContents(code.getDetailCd());
                    result.setFailContents(code.getCdEngNm());
                }
            }
        }catch (Exception ex){
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
//            result.setFailContents(ex.getMessage());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public ManagementUserDetailRes getUserDetail(ManagementUserDetailDto params) {
        return managementStore.getUserDetail(params);
    }

    @Override
    public CommonResultRes saveCapa(List<ManagementSettingSaveCapaDto> params) {
        CommonResultRes result = new CommonResultRes();
        List<ManagementCommonCapaInfoRes> beforeData = managementStore.getDeviceCapa(params);
        int i = 0;
        for(int j = 0; j < params.size(); j++){
            params.get(j).setGrId(params.get(j).getDeviceId().substring(0,3));
            i += managementStore.saveCapa(params.get(j));
        }
        if (i > 0) {
            ControlCapacityDto controlParam = new ControlCapacityDto();
            controlParam.setUserId(params.get(0).getUserId());
            controlParam.setBeforeData(beforeData);
            controlParam.setAfterData(params);
            controlService.sendCapacity(controlParam);
            result.setStatus(commonStore.getMessageStatusCode("SESS"));
            result.setPopContents(getSessMessage());
        } else {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NOT_EXIST_INSERT_DATA");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public CommonResultRes initUserPass(ManagementUserDetailDto params) {
        CommonResultRes result = new CommonResultRes();
        try {
            List<String> urlDevisions = new ArrayList<String>();
            // 1. 시스템 설정값 확인
            List<ManagementUserSystemSettingRes> settingRes = managementStore.getSystemSetting();

            // 시스템 설정의 SMS/Email/Telegram 사용여부가 Y인것이 없을 경우 실패코드 반환
            if(settingRes == null || settingRes.size() == 0) {
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                result.setFailContents("Invalid System Setting");
                return result;
            }

            // 1-2. 사용자의 수신여부를 확인하고 시스템 설정의 우선순위에 따라 url Devision을 세팅한다.
            ManagementUserDetailRes userInfo = managementStore.getUserDetailAndPass(params);
            String telegramId = userInfo.getTelegramId() == null ? "" : userInfo.getTelegramId();
            String mobileNo = userInfo.getMobileNo() == null ? "" : userInfo.getMobileNo();
            String email = userInfo.getEmail() == null ? "" : userInfo.getEmail();

            for(ManagementUserSystemSettingRes temp : settingRes) {
                if("useAlarmTelegram".equalsIgnoreCase(temp.getKeyNm()) && "Y".equals(userInfo.getTelegramRecYn()) && !telegramId.isEmpty()) {
                    urlDevisions.add("Telegram_V1.0");
                } else if("useAlarmSMS".equalsIgnoreCase(temp.getKeyNm()) && "Y".equals(userInfo.getSmsRecYn()) && !mobileNo.isEmpty()) {
                    urlDevisions.add("LG_V1.0");
                } else if("useAlarmEmail".equalsIgnoreCase(temp.getKeyNm()) && "Y".equals(userInfo.getEmailRecYn()) && !email.isEmpty()) {
                    urlDevisions.add("Gmail_V1.0");
                }
            }

            // 1-3. 사용자 수신여부 Y가 없거나 ID 정보(sms,email,telegram id)가 모두 없을 경우 실패코드 반환
            if(urlDevisions.isEmpty()) {
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("NOT_RECEIVE_ALARM");
//                result.setFailContents(code.getDetailCd());
                result.setFailContents(code.getCdEngNm());
                return result;
            }

            // 1-4. 임시 패스워드 생성 및 사용자 패스워드 업데이트
            String newPass = getRandomPassword(); // 임시 패스워드 생성
            PasswordEncoding passwordEncoding = new PasswordEncoding();
            String passEncrypt = passwordEncoding.encode(newPass); // 암호화

            ManagementUserDetailRes updatePassParam = new ManagementUserDetailRes();

            // 지난 패스워드 정보 가져오기 (지난 패스워드 정보를 한칸씩 밀기 위해 )
            updatePassParam.setUserSeq(params.getUserSeq());
            updatePassParam.setPassNo(passEncrypt);
            updatePassParam.setPastPassNo1(userInfo.getPassNo());
            updatePassParam.setPastPassNo2(userInfo.getPastPassNo1());
            updatePassParam.setPastPassNo3(userInfo.getPastPassNo2());
            int i = managementStore.updatePass(updatePassParam); // 패스워드 update

            // UMS SEND
            if (i == 1) {
              // 만약 패스워드 변경이 정상적으로 되었다면
              result = sendTempPassword(urlDevisions, userInfo, newPass); // 임시 패스워드 전송
            }
        } catch (Exception ex){
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
//            result.setFailContents(ex.getMessage());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    public String PasswordCheckLogic(ManagementUserHeaderSaveDto model) throws Exception {
        PasswordEncoding passwordEncoding = new PasswordEncoding();

        String nowpass = Base64AndRSAdecrypt(privateKeyString, (String) model.getNowPassNo()); // 현재 패스 복호화 진행
//        String pass = Base64AndRSAdecrypt(privateKeyString, (String) model.getPassNo()); // 신규 패스 복호화 진행

        //복호화 된 pass db와 비교
        ManagementUserDetailDto obj = new ManagementUserDetailDto();
        obj.setUserSeq(model.getUserSeq());
        ManagementUserDetailRes dbData = managementStore.getUserDetailAndPass(obj);

        // 1. 신규 비밀번호 입력 안했을 경우 -> 현재 비밀번호 맞게 입력했는지 확인
        if(model.getPassNo() == null) {
            // 1-1. 비밀번호 불일치 시
            if(!passwordEncoding.matches(nowpass, dbData.getPassNo())) {
                log.info("신규 비밀번호 입력 X, 현재 비밀번호 불일치");
                return "false_nowPassFail";
            } else {
                log.info("신규 비밀번호 입력 X, 현재 비밀번호 일치");
                return "true";
            }
        }

        // 2. 신규 비밀번호 입력했을 경우 규칙 확인
        // 2-1. 현재 = DB = 신규 비밀번호 일 경우
        String pass = Base64AndRSAdecrypt(privateKeyString, (String) model.getPassNo()); // 신규 패스 복호화 진행
        if(nowpass.equals(pass)) {
            log.info("패스워드 1번 경로");
            return "false_samePass";
        }
        // 2-2. 이전 3개의 비밀번호와 동일한 경우
        if (passwordEncoding.matches(pass, dbData.getPastPassNo1()) || passwordEncoding.matches(pass, dbData.getPastPassNo1())
                || passwordEncoding.matches(pass, dbData.getPastPassNo2()) || passwordEncoding.matches(pass, dbData.getPastPassNo3()) ) {
            log.info("패스워드 2번 경로");
            return "false_prePass";
        }

        // 2-3. 신규 비밀번호 규칙에 맞게 입력했을 경우 비밀번호 한개씩 당겨서 업데이트
        log.info("패스워드 3번 경로");
        passwordParam.setPastPassNo3(dbData.getPastPassNo2());
        passwordParam.setPastPassNo2(dbData.getPastPassNo1());
        passwordParam.setPastPassNo1(dbData.getPassNo());
        String passEncrypt = passwordEncoding.encode(pass);
        passwordParam.setPassNo(passEncrypt);
        return "true_pasChange";
    }

    public ManagementUserSaveDto passwordParamCreate(ManagementUserSaveDto model) throws Exception{
        PasswordEncoding passwordEncoding = new PasswordEncoding();

        String nowpass = Base64AndRSAdecrypt(privateKeyString, (String) model.getPassNo()); // 복호화 진행
        String passEncrypt = passwordEncoding.encode(nowpass);
        model.setPassNo(passEncrypt);

        return model;
    }

    //비밀번호 초기화시 임시 비밀번호
    public static String getRandomPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7','8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z' };

        int idx = 0;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            log.info("idx :::: "+idx);
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }

    public CommonResultRes sendTempPassword(List<String> urlDevisions, ManagementUserDetailRes userInfo, String pass) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, org.json.simple.parser.ParseException {
        CommonResultRes result = new CommonResultRes();

        String smsInfo = userInfo.getMobileNo() == null ? "" : userInfo.getMobileNo(); // 전화번호 세팅
        String emailInfo = userInfo.getEmail() == null ? "" : userInfo.getEmail(); // 이메일 세팅
        String telegramInfo = userInfo.getTelegramId() == null ? "" : userInfo.getTelegramId(); // 텔레그램 아이디 세팅
        String devisions = String.join(",", urlDevisions);

        // 보낼 메시지 평문
        String text = "{\"urlDevision\":\""+ devisions +"\",\"contents\":\"Temporary password : "+ pass +
                "\",\"title\":\"Temporary password\",\"project\":\"TEMS\",\"emailInfo\":\"" + emailInfo + "\",\"telegramInfo\":\"" + telegramInfo +"\"" + ",\"smsInfo\":\"" + smsInfo + "\"}";

        UmsModel param = new UmsModel();
        param.setData(text);
        param.setDevisions(urlDevisions);
        String umsResult = umsService.saveUmsAndSend(param);
        if(umsResult.contains("sess")) {
            result.setStatus(commonStore.getMessageStatusCode("SESS"));
            result.setPopContents(getSessMessage());
        } else {
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    /**
     * Public Key를 base64로 복호화 하여 RSA 암호화 함수를 호출 한다.
     * @param publicKeyString 공개키.
     * @param encodingText 암호화할 평문.
     * @return encodingData 암호화된 데이터
     */
    public String Base64AndRSAencryp(String publicKeyString, String encodingText) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] publicKeyByte = Base64.getDecoder().decode(publicKeyString.getBytes());
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyByte);

        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        // 암호화 합니다.
        String encodingData = encryptRSA(encodingText, publicKey);
        log.info("encodingData : " + encodingData);

        return encodingData;
    }

    public String Base64AndRSAdecrypt(String privateKeyString, String decryptText) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] privateKeyByte = Base64.getDecoder().decode(privateKeyString.getBytes());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        String encrypted = decryptText;
        // 복호화 합니다.
        String decrypted = decryptRSA(encrypted, privateKey);
        log.info("decrypted : " + decrypted);

        return decrypted;
    }

    /**
     * Private Key로 RAS 복호화를 수행합니다.
     *
     * @param encrypted 암호화된 이진데이터를 base64 인코딩한 문자열 입니다.
     * @param privateKey 복호화를 위한 개인키 입니다.
     * @return
     * @throws Exception
     */
    public String decryptRSA(String encrypted, PrivateKey privateKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] byteEncrypted = Base64.getDecoder().decode(encrypted.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        log.info("decrypt 1");

        byte[] bytePlain = cipher.doFinal(byteEncrypted);
        log.info("decrypt 2");

        String decrypted = new String(bytePlain, "utf-8");
        return decrypted;
    }

    /**
     * Public Key로 RSA 암호화를 수행합니다.
     * @param plainText 암호화할 평문입니다.
     * @param publicKey 공개키 입니다.
     * @return encrypted 암호화된 데이터
     */
    public String encryptRSA(String plainText, PublicKey publicKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytePlain = cipher.doFinal(plainText.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(bytePlain);
        return encrypted;
    }

    /**
     * SHA256 암호화 문자열로 변환
     * @param value
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String encryptSHA256(String value) throws Exception { //throws NoSuchAlgorithmException{
        String encryptData = "";

        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(value.getBytes());
        //sha.update(value.getBytes("UTF-8"));

        byte[] digest = sha.digest();
        for (int i=0; i<digest.length; i++) {
            encryptData += Integer.toHexString(digest[i] & 0xFF).toUpperCase();
        }
        log.info(encryptData);
        return encryptData;
    }

    @Override
    public ManagementSettingCapaInfoRes getCapaData() {
        ManagementSettingCapaInfoRes result = new ManagementSettingCapaInfoRes();
        result.setPcsInfo(managementStore.getCapaData("100"));
        result.setBmsInfo(managementStore.getCapaData("200"));
        result.setSolarInfo(managementStore.getCapaData("311"));
        return result;
    }

    @Override
    public List<CommonCodeRes> getSeasonInfo() {
        return managementStore.getSeasonInfo();
    }

    @Override
    public List<CommonCodeRes> getChargeInfo() {
        return managementStore.getChargeInfo();
    }

    @Override
    public ManagementSiteInfoRes getSiteInfo() {
        return managementStore.getSiteInfo();
    }

    @Override
    public List<ManagementSiteDeviceInfoRes> getSiteDeviceInfo(ManagementSiteDeviceInfoDto params) {
        PagingParamService.setPagingParam(params);
        int cnt = managementStore.getSiteDeviceInfoCnt(params);
        List<ManagementSiteDeviceInfoRes> result = new ArrayList<ManagementSiteDeviceInfoRes>();
        result = managementStore.getSiteDeviceInfo(params);
        if(result.size() > 0){
            result.get(0).setDataTotalCount(cnt);
        }
        return result;
    }

    @Override
    public ManagementSiteInfoRes getSiteDetailInfo(ManagementSiteDetailDto params) {
        return managementStore.getSiteDetailInfo(params);
    }

    @Override
    public CommonResultRes updateSiteDetailInfo(ManagementUpdateSiteInfoDto params) {
        CommonResultRes result = new CommonResultRes();
        int i = managementStore.updateSiteDetailInfo(params);
        if (i > 0) {
            result.setStatus(commonStore.getMessageStatusCode("SESS"));
            result.setPopContents(getSessMessage());
        } else {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NOT_EXIST_UPDATE_DATA");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public ManagementSiteDeviceInfoRes getSiteDeviceDetailInfo(ManagementSiteDeviceDetailDto params) {
        return managementStore.getSiteDeviceDetailInfo(params);
    }

    @Override
    public ManagementSettingEssInfoRes getEssSettingData() {
        return managementStore.getPmsSetting();
    }

    @Override
    public CommonResultRes saveSetting(ManagementSettingSaveEssDto params) {
        ManagementSettingEssInfoRes beforeData = managementStore.getPmsSetting();
        CommonResultRes result = new CommonResultRes();
        int i = managementStore.savePmsSetting(params);
        if (i > 0 ) {
            ControlEssSettingDto controlParam = new ControlEssSettingDto();
            controlParam.setUserId(params.getUserId());
            controlParam.setBeforeData(beforeData);
            controlParam.setAfterData(params);
            controlService.saveEssSetting(controlParam);
            result.setStatus(commonStore.getMessageStatusCode("SESS"));
            result.setPopContents(getSessMessage());
        } else {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NOT_EXIST_INSERT_DATA");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public CommonResultRes saveSch(ManagementSchSaveDto params) {
        CommonResultRes result = new CommonResultRes();
        // 데이터 검증 작업
        try{
            ManagementSchChartDataDto dto = new ManagementSchChartDataDto();
            dto.setDeviceId(params.getDeviceId());
            dto.setSeasonCd(params.getSeasonCd());
            String paramSH = params.getStartHh();
            String paramEH = params.getEndHh();
            String paramSM = params.getStartMi();
            String paramEM = params.getEndMi();
            String startDataOperLap = "";
            String endDataOperLap = "";
            String dbStartDataOperLap = "";
            String dbEndDataOperLap = "";

            for(String week : params.getWeekArr()) {
                dto.setWeekCd(week);
                List<ManagementSchDataRes> schInfos = managementStore.getSchChartData(dto);

                for (int i = 0; i < schInfos.size(); i++) {
                    ManagementSchDataRes dbData = schInfos.get(i);

                    Date ParamStartTime = dateFomating("startTime", paramSH, paramSM);
                    Date ParamEndTime = dateFomating("endTime", paramEH, paramEM);
                    Date DbStartTime = dateFomating("startTime", dbData.getStartHh(), dbData.getStartMi());
                    Date DbEndTime = dateFomating("endTime", dbData.getEndHh(), dbData.getEndMi());

                    //중복 데이터 검사
                    startDataOperLap = isOverLap(DbStartTime, DbEndTime, ParamStartTime, "startTime");
                    endDataOperLap = isOverLap(DbStartTime, DbEndTime, ParamEndTime, "endTime");

                    dbStartDataOperLap = isOverLap(ParamStartTime, ParamEndTime, DbStartTime, "startTime");
                    dbEndDataOperLap = isOverLap(ParamStartTime, ParamEndTime, DbEndTime, "endTime");
                    if (startDataOperLap.equals("fail") || endDataOperLap.equals("fail") || dbStartDataOperLap.equals("fail") || dbEndDataOperLap.equals("fail")) {
                        result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                        code = commonStore.getMessageStatusCodeNew("DATA_OVERLAP");
    //                    result.setFailContents(code.getDetailCd());
                        result.setFailContents(code.getCdEngNm());
                        return result;
                    }
                }
            }

            if("1".equals(params.getChadiStatus())) {
                params.setChadiKw(params.getChadiKw() * -1);
            }

            if(result.getStatus() == null || !result.getStatus().equals("STATUS7")) {
                // 저장 전 device 가져오기
                ManagementSchPcsInfoDto pcs = new ManagementSchPcsInfoDto();
                pcs.setDeviceId(params.getDeviceId());
                List<ManagementCommonCapaInfoRes> deviceInfoModel = managementStore.getPcsInfo(pcs);

                int result_i = 0;
                for (int i = 1; i< deviceInfoModel.size(); i++) {
                    params.setDeviceId(deviceInfoModel.get(i).getDeviceId());
                    params.setDeviceNm(deviceInfoModel.get(i).getDeviceNm());
                    result_i += managementStore.saveSch(params);
                }
                if(result_i > 0){
                    controlService.sendSchedule(params);
                    result.setStatus(commonStore.getMessageStatusCode("SESS"));
                    result.setPopContents(getSessMessage());
                }else{
                    result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                    code = commonStore.getMessageStatusCodeNew("NOT_EXIST_INSERT_DATA");
//                    result.setFailContents(code.getDetailCd());
                    result.setFailContents(code.getCdEngNm());
                }
            }
        }catch (Exception ex){
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
//            result.setFailContents(ex.getMessage());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public List<ManagementSchDataRes> getSchTable(ManagementSchTableDataDto params) {
        PagingParamService.setPagingParam(params);
        int cnt = managementStore.getSchDataCnt(params);
        List<ManagementSchDataRes> result = new ArrayList<ManagementSchDataRes>();
        result = managementStore.getSchTable(params);
        if(result.size() > 0){
            result.get(0).setDataTotalCount(cnt);
        }
        return result;
    }

    @Override
    public List<ManagementSchDataRes> getSchChartData(ManagementSchChartDataDto params) {
        return managementStore.getSchChartData(params);
    }

    @Override
    public ManagementHolidayDataRes getHolidayData(ManagementHolidayDataDto params) {
        int count = managementStore.getYearHolidayCnt(params);
        List<ClosedDayRes> result02 = managementStore.getClosedDayData(params);

        ManagementHolidayDataRes result = new ManagementHolidayDataRes();
        result.setSiteId(params.getSiteId());
//        result.setHolidayList(result01);
        result.setClosedList(result02);
        result.setDataTotalCount(count);

        return result;
    }

    @Override
    public CommonResultRes deleteSch(ManagementSchDeleteDto params) {
        CommonResultRes result = new CommonResultRes();
        try {
            ManagementSchCommonDto sch = managementStore.getControlSch(params);
            sch.setUserId(params.getUserId());
            int i = managementStore.deleteSch(params);
            if(i == params.getSchSeq().length) {
                controlService.sendSchedule(sch);
                result.setStatus(commonStore.getMessageStatusCode("SESS"));
                result.setPopContents(getSessMessage());
            } else if(i ==0) {
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("NOT_EXIST_DELETE_DATA");
//                result.setFailContents(code.getDetailCd());
                result.setFailContents(code.getCdEngNm());
            } else {
                code = commonStore.getMessageStatusCodeNew("FAIL");
                result.setStatus(code.getDetailCd());
                int temp = params.getSchSeq().length - i;
//                result.setFailContents("SESSCNT :: "+ i + "FAIL :: " + temp);
                result.setFailContents(code.getCdEngNm());
            }
        } catch (Exception ex) {
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
//            result.setFailContents(ex.getMessage());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public CommonResultRes saveHolidayData(ManagementSaveHolidayDataDto params) {
        CommonResultRes result = new CommonResultRes();
        LocalDate today = LocalDate.now();
        String strToday = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        params.setToday(strToday);

        List<ClosedDayRes> holidayMapList = new ArrayList<ClosedDayRes>();
        List<ClosedDayRes> closedDayMapList = new ArrayList<ClosedDayRes>();

        if(params.getClosedDay() != null && !params.getClosedDay().isEmpty()) {
            ClosedDayRes closedDayRes = new ClosedDayRes();
            closedDayRes.setTime(params.getClosedDay());
            closedDayRes.setHolidDesc(params.getHolidayDesc());
            if(params.getLegalHolidayYn().toUpperCase().equals("Y")) {
                holidayMapList.add(closedDayRes);
            } else {
                closedDayMapList.add(closedDayRes);
            }
//            params.getLegalHolidayYn().toUpperCase().equals("Y") ?
//                    saveL :
//            holidayList.retainAll(closedDayList);
//            closedDayList.removeAll(holidayList);
//            int delStatus = managementStore.deleteClosedData(params);
        } else {
            // 해당 년도의 오늘 날짜 이후 운휴일 삭제
            int delStatus = managementStore.deleteHolidayData(params);
            LocalDate lastDate = LocalDate.parse(params.getYear() + "-12-31");
            if(params.getLegalHolidayChk().equals("1")) {
//                holidayList = managementStore.getLegalHolidayData(params);
                holidayMapList = managementStore.getLegalHolidayData(params);
            }

            // 해당 년도의 오늘 날짜 이후 요일별 운휴일 등록
            for (String weekCd : params.getWeekArr()) {
                System.out.print("nWeekCd :::: " + convertWeekCdCalToLocalDate(weekCd));
                LocalDate firstDate = getFirstDay(params.getYear(), convertWeekCdCalToLocalDate(weekCd));
                System.out.println(" //// firstDate :::: " + firstDate.toString());

                while (firstDate.isBefore(lastDate)) {
                    if (firstDate.isAfter(today)) {
                        ClosedDayRes closedDayRes = new ClosedDayRes();
                        closedDayRes.setTime(firstDate.toString().replaceAll("-", ""));
                        closedDayMapList.add(closedDayRes);
                    }
                    firstDate = firstDate.plusDays(7);
                }

                if (firstDate.isEqual(lastDate)) {
                    if (firstDate.isAfter(today)) {
                        ClosedDayRes closedDayRes = new ClosedDayRes();
                        closedDayRes.setTime(firstDate.toString().replaceAll("-", ""));
                        closedDayMapList.add(closedDayRes);
                    }
                }
            }
            List<ClosedDayRes> finalHolidayMapList = holidayMapList;

            closedDayMapList.removeIf(closedDay -> {
                String timeToRemove = closedDay.getTime();
                return finalHolidayMapList.stream().anyMatch(holiday -> holiday.getTime().equals(timeToRemove));
            });
        }

        params.setClosedDayList(closedDayMapList);
        params.setHolidayList(holidayMapList);

        if(closedDayMapList != null && closedDayMapList.size() > 0) {
            int saveStatus01 = managementStore.saveClosedData(params);
            if(saveStatus01 == 0) {
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("NOT_EXIST_INSERT_DATA");
                result.setFailContents(code.getCdHanNm());
                return result;
            } else {
                params.setHolidayCd("0");
                params.setDay(params.getClosedDayList().get(0).getTime());
            }
        }
        if(holidayMapList != null && holidayMapList.size() > 0) {
            int saveStatus = managementStore.saveHolidayData(params);
            if(saveStatus == 0) {
                result.setStatus(commonStore.getMessageStatusCode("FAIL"));
                code = commonStore.getMessageStatusCodeNew("NOT_EXIST_INSERT_DATA");
                result.setFailContents(code.getCdHanNm());
                return result;
            } else {
                params.setHolidayCd("1");
                params.setDay(params.getHolidayList().get(0).getTime());
            }
        }
        controlService.sendHoliday(params);
        result.setStatus(commonStore.getMessageStatusCode("SESS"));
        result.setPopContents(getSessMessage());
        return result;
    }

    @Override
    public CommonResultRes deleteHolidayData(ManagementDeleteHolidayDataDto params) {
        CommonResultRes result = new CommonResultRes();
        params.setHolidayCd(managementStore.getHolidayCd(params));
        int i = managementStore.deleteClosedData(params);
        if(i == 0) {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NOT_EXIST_DELETE_DATA");
            result.setFailContents(code.getCdHanNm());
            return result;
        }
        params.setDay(params.getClosedDay());
        controlService.sendHoliday(params);
        result.setStatus(commonStore.getMessageStatusCode("SESS"));
        result.setPopContents(getSessMessage());
        return result;
    }

    @Override
    public CommonResultRes saveLegalHoliday(List<ManagementSaveLegalHolidayDto> params) {
        CommonResultRes result = new CommonResultRes();
        managementStore.deleteLegalHoliday();
        int i = managementStore.insertLegalHoliday(params);
        if(i == 0) {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NOT_EXIST_DELETE_DATA");
            result.setFailContents(code.getCdHanNm());
            return result;
        }
        result.setStatus(commonStore.getMessageStatusCode("SESS"));
        result.setPopContents(getSessMessage());
        return result;
    }

    @Override
    public List<ManagementLegalHolidayTableRes> getLegalHoliday(ManagementLegalHolidayTableDto params) {
        PagingParamService.setPagingParam(params);
        int cnt = managementStore.getLegalHolidayTableCnt(params);
        List<ManagementLegalHolidayTableRes> result = new ArrayList<ManagementLegalHolidayTableRes>();
        result = managementStore.getLegalHolidayTable(params);
        if(result.size() > 0){
            result.get(0).setDataTotalCount(cnt);
        }
        return result;
    }

    // Date 형태로 변환 하는 함수 (timeType은 startTime인지 endTime 인지 구분 값이 들어옴)
    // endTime 일 경우 00시 00분이면 다음 날로 판단한다.
    public Date dateFomating(String timeType, String hh, String mi) throws ParseException {
        log.info(timeType + " "+ hh + " "+ mi);
        DateFormat dtf = new SimpleDateFormat("yyyyMMddHHmm");
        // 우리는 시간, 분 만 판단 하기 때문에 date 형태로 변환하기 위해 임의 날짜를 입력한다.
        String fullDateTime = "20200101";
        String tempTime = "20200102"; // 만약 end 타임이 00시 00분 일경우 다음 날로 간주한다.
        if(timeType.equals("startTime")){
            fullDateTime = fullDateTime + hh + mi;
        }else if(timeType.equals("endTime")){
            if(hh.equals("00") && mi.equals("00")){
                fullDateTime = tempTime; //00시 00분 이기에 다음 날로 변경
                fullDateTime = fullDateTime + hh + mi;
            }else{
                fullDateTime = fullDateTime + hh + mi;
            }
        }else{ // 날짜 변환 실패
            //return false;
        }
        return dtf.parse(fullDateTime);
    }

    public String isOverLap(Date DbStartTime, Date DbEndTime, Date ParamTime, String timeType){
        String result = "sess";
        String failCentents = "";
        if(ParamTime.after(DbStartTime) && ParamTime.before(DbEndTime)){
            // UI에서 보내준 start 또는 end 시간은 DB 조회 데이터 안에 들어갈 수 없다
            // ex) DBstartTime < paramTime < DBEndTime
            result = "fail";
        }else if(timeType.equals("startTime") && ParamTime.equals(DbStartTime)) {
            // UI에서 보내준 start Time은 DB Start Time과 같을수 없다.
            // ex) DBstartTime == paramStartTime
            result = "fail";
        }else if(timeType.equals("endTime") && ParamTime.equals(DbEndTime)) {
            // UI에서 보내준 end Time은 DB End Time과 같을수 없다.
            // ex) DBendTime == paramendTime
            result = "fail";
        }
        return result;
    }

    public String convertWeekCdCalToLocalDate(String weekCd) {
        StringBuffer stb = new StringBuffer();
        switch (weekCd) {
            case "1":
                stb.append("7");
                break;
            case "2":
                stb.append("1");
                break;
            case "3":
                stb.append("2");
                break;
            case "4":
                stb.append("3");
                break;
            case "5":
                stb.append("4");
                break;
            case "6":
                stb.append("5");
                break;
            case "7":
                stb.append("6");
                break;
        }

        return stb.toString();
    }

    public LocalDate getFirstDay(String year, String weekCd) {
        LocalDate retLocalDate = LocalDate.parse(year + "-01-01");

        for (int idx = 0; idx < 8; idx++) {

            System.out.println("retLocalDate.getDayOfWeek().getValue() ::: " + retLocalDate.getDayOfWeek().getValue());
            if (weekCd.equals(String.valueOf(retLocalDate.getDayOfWeek().getValue()))) {
                break;
            } else {
                retLocalDate = retLocalDate.plusDays(1);
            }
        }

        return retLocalDate;
    }
}
