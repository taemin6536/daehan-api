package com.nuri.mys.bems.service.common;

import com.nuri.mys.bems.domain.entity.ums.UmsModel;
import com.nuri.mys.bems.domain.entity.ums.UmsResultModel;
import com.nuri.mys.bems.domain.store.common.CommonStore;
import com.nuri.mys.bems.service.control.Event;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

@Slf4j
@Component
public class UmsService {

    @Autowired
    private CommonStore commonStore;

//    @Autowired
//    private SmsStore smsStore;

    @Value("${spring.sms.kr.callbackNumber}")
    private String callbackNumber;

    @Value("${spring.privateKeyString}")
    private String privateKeyString; // 개인키를 base64 암호화한 키값

    @Value("${spring.mail.smtp.starttls.enable}")
    private String enable;

    @Value("${spring.mail.smtp.host-gmail}")
    private String gmailHost;

    @Value("${spring.mail.smtp.host-nuri}")
    private String nuriHost;

    @Value("${spring.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.smtp.port}")
    private String port;

    @Value("${spring.mail.gmail.frommailAdress}")
    private String fromGmailAdress;

    @Value("${spring.mail.gmail.frommailpasswd}")
    private String fromGmailpasswd;

    @Value("${spring.mail.nuri.frommailAdress}")
    private String fromNuriAdress;

    @Value("${spring.mail.nuri.frommailpasswd}")
    private String fromNuripasswd;

    @Value("${spring.telegram.api-url}")
    private String telegramUrl;

    @Value("${spring.telegram.token}")
    private String telegramToken;

    @Value("${spring.privateKeyString}")
    private String telegramPrivateKeyString;

    public String saveUmsAndSend(UmsModel model) {
        String result = "";
        try {
            String data = model.getData();
            JSONObject jsonObj = transJsonObject(data);
            List<String> urlDevisions = model.getDevisions();

            UmsResultModel resultModel = new UmsResultModel();
            resultModel.setContents((String) jsonObj.get("contents"));
            resultModel.setData(data);
            resultModel.setProject((String) jsonObj.get("project"));

            int i = 0;
            for(String temp : urlDevisions) {
                switch (temp) {
                    case "Telegram_V1.0" :
                        resultModel.setId((String) jsonObj.get("telegramInfo"));
                        resultModel.setTableNm("NHT_TELEGRAM");
                        result = telegramSend(model);
                        break;
                    case "LG_V1.0" :
                        resultModel.setId((String) jsonObj.get("smsInfo"));
                        resultModel.setTableNm("NHT_SMS_KR");
                        result = smsSend(model);
                        break;
                    case "Gmail_V1.0" :
                        resultModel.setId((String) jsonObj.get("emailInfo"));
                        resultModel.setTableNm("NHT_MAIL");
                        result = emailSend(model);
                        break;
                }
                resultModel.setvUrlDevision(temp);
                String[] resultArray = result.split(";");
                if(resultArray.length > 1){
                    resultModel.setStatus(resultArray[0]);
                    resultModel.setErrorExcetion(resultArray[1]);
                } else{
                    resultModel.setStatus(resultArray[0]);
                    resultModel.setErrorExcetion(null);
                }
                i += commonStore.saveUmsHistory(resultModel);
            }
            if(i > 0) {
                return "sess";
            }
            return "fail";
        } catch (Exception ex) {
            result = "fail;"+ex.getMessage();
            return result;
        }
    }

    public String smsSend(UmsModel model) {
        String result = "";
        try {
            String data = model.getData();
            System.out.println(data);

            // 복호화 된 데이터 JsonObject로 변환
            JSONObject jsonObj = transJsonObject(data);
            UmsModel saveModel = new UmsModel();
            saveModel.setMobile_no((String) jsonObj.get("smsInfo"));
            saveModel.setContents(((String) jsonObj.get("contents")).replaceAll("<p>", "").replaceAll("</p>", "\n"));
            saveModel.setProject((String) jsonObj.get("project"));

            // 발신번호복호화 진행
            String callbackNumber_ = Base64AndRSAdecrypt(privateKeyString, callbackNumber);

            result = "sess;"+"";
            return result;
        }
        catch (Exception ex){
            result = "fail;"+ex.getMessage();
            return result;
        }
    }

    public String emailSend(UmsModel model) {
        String result = "";
        try {
            String data = model.getData();
            System.out.println(data);

            // JsonObject로 변환
            JSONObject jsonObj = transJsonObject(data);
            String toEmail = (String) jsonObj.get("emailInfo");
            String contents = (String) jsonObj.get("contents");
            String title = (String) jsonObj.get("title");

            String[] array = toEmail.split(",");

            Properties p = System.getProperties();
            p.put("mail.smtp.starttls.enable", enable); // gmail은 무조건 true 고정
            String userMailService = "nuri";
            if(userMailService.equals("nuri")) {
                p.put("mail.smtp.host", nuriHost); // smtp 서버 주소
            }else {
                p.put("mail.smtp.host", gmailHost); // smtp 서버 주소
            }
            p.put("mail.smtp.auth", auth); // gmail은 무조건 true 고정
            p.put("mail.smtp.port", port); // gmail 포트

            // 복호화 진행
            String frommailAdress = "";
            String frommailpasswdData = "";
            if(userMailService.equals("nuri")) {
                frommailAdress = fromNuriAdress;
                frommailpasswdData = Base64AndRSAdecrypt(privateKeyString, fromNuripasswd);
            }else {
                frommailAdress = fromGmailAdress;
                frommailpasswdData = Base64AndRSAdecrypt(privateKeyString, fromGmailpasswd);
            }

            Authenticator auth = new MyAuthentication(frommailAdress, frommailpasswdData);

            //session 생성 및  MimeMessage생성
            Session session = Session.getDefaultInstance(p, auth);
            MimeMessage msg = new MimeMessage(session);

            //편지보낸시간
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress() ;
            //from = new InternetAddress("nuritelecom.service2@gmail.com");
            from = new InternetAddress(frommailAdress);
            // 이메일 발신자
            msg.setFrom(from);

            for(String email : array) {
                // 이메일 수신자
                InternetAddress to = new InternetAddress(email);
                msg.setRecipient(Message.RecipientType.TO, to);

                // 이메일 제목
                msg.setSubject(title, "EUC-KR");
                // 이메일 내용
                msg.setText(contents, "EUC-KR");
                // 이메일 헤더
                msg.setHeader("content-Type", "text/html");
                //메일보내기
                javax.mail.Transport.send(msg);
            }
            result = "sess;"+"";
            return result;
        }
        catch (Exception ex){
            result = "fail;"+ex.getMessage();
            return result;
        }
    }

    public String telegramSend(UmsModel model) {
        String result = "";
        BufferedReader in = null;
        try{
            String data = model.getData();
            System.out.println(data);

            JSONObject jsonObj = transJsonObject(data);
            String chatId = (String) jsonObj.get("telegramInfo");
            String contents = ((String) jsonObj.get("contents")).replaceAll("<p>", "").replaceAll("</p>", "%0A");
            String[] array = chatId.split(",");

            String token = Base64AndRSAdecrypt(privateKeyString, telegramToken);

            for(String id : array) {
                URL obj = new URL(telegramUrl + token + "/sendmessage?chat_id=" + id + "&text=" + contents + "&parse_mode=html"); // 호출할 url

                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                con.setRequestMethod("GET");
                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String line;

                while((line = in.readLine()) != null) { // response를 차례대로 출력
                    System.out.println(line);
                }
            }
            result = "sess;"+"";
            return result;
        } catch (Exception ex){
            result = "fail;"+ex.getMessage();
            return result;
        } finally {
            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
        }

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
        byte[] bytePlain = cipher.doFinal(byteEncrypted);
        String decrypted = new String(bytePlain, "utf-8");
        return decrypted;
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

    public JSONObject transJsonObject(String decryptedData) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(decryptedData);
        JSONObject jsonObj = (JSONObject) obj;

        return jsonObj;
    }

    public void sendEventMessage(com.nuri.mys.bems.service.control.Message event) {
        String umsClassification = commonStore.getUmsClassification();
        if(umsClassification != null) {
            JSONObject umsInfo = commonStore.getPmsUserSmsInfo();
            if(umsInfo == null) {
                return;
            }
            String smsInfo = umsInfo.get("mobileNo") == null ? "" : (String) umsInfo.get("mobileNo"); // 전화번호 세팅
            String emailInfo = umsInfo.get("email") == null ? "" : (String) umsInfo.get("email"); // 이메일 세팅
            String telegramInfo = umsInfo.get("telegramId") == null ? "" : (String) umsInfo.get("telegramId"); // 텔레그램

            String contents = "";

            for (Event payload : (List<Event>) event.getPayload()) {
                contents = "<p>사업장 : " + event.getSiteNm() + "</p>"
                        + "<p>장비 : " + event.getDevNm() + "</p>"
                        + "<p>발생 시간 : " + event.getTime() + "</p>"
                        + "<p>이벤트 설명 : " + payload.getEvtDesc() + "</p>";
            }

            // 보낼 메시지 평문
            String text = "{\"urlDevision\":\"" + umsClassification + "\",\"contents\":\"" + contents +
                    "\",\"title\":\"이벤트 발생 알람\",\"project\":\"TEMS\"," +
                    "\"emailInfo\":\"" + emailInfo + "\",\"telegramInfo\":\"" + telegramInfo + "\"" + ",\"smsInfo\":\"" + smsInfo + "\"}";

            UmsModel param = new UmsModel();
            param.setData(text);
            param.setDevisions(Arrays.asList(umsClassification.split(",")));
            String umsResult = saveUmsAndSend(param);
        }
    }
}

