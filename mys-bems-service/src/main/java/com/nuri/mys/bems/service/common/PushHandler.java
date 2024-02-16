package com.nuri.mys.bems.service.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuri.mys.bems.domain.store.common.CommonStore;
import com.nuri.mys.bems.domain.store.control.ControlStore;
import com.nuri.mys.bems.service.control.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class PushHandler {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ControlStore controlStore;

    @Autowired
    private CommonStore commonStore;

    @Autowired
    private UmsService umsService;

    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static String siteId;

    @PostConstruct
    public final void setSiteId() {
        String text = commonStore.getSiteId();
        siteId = text;
    }

    public void sendDeviceRealtime(Exchange exchange) {
        String json = null;
        Object obj = null;

        try {
            obj = exchange.getIn().getBody();
            json = (String) obj;

            List<JSONObject> payload = new ArrayList<JSONObject>();
            JSONParser jsonParser = new JSONParser();
            obj = jsonParser.parse(json);
            JSONObject jsonObj = (JSONObject) obj;
            JSONObject tmp = (JSONObject) jsonObj.get("payload");
            tmp.put("devId", jsonObj.get("devId"));
            payload.add(tmp); // jsonObj 타입 payload를 list로 변환
            String grId = (String) jsonObj.get("grId") == null ?
                    String.valueOf(jsonObj.get("devId")).substring(0, 3) : (String) jsonObj.get("grId");
            jsonObj.put("payload", payload);
            jsonObj.put("classification", "device"); // 장비 실시간 데이터
            processingDeviceData(jsonObj, grId);
//            log.info("sendDeviceRealtime = {}", jsonObj.toString());
            this.template.convertAndSend("/pms/push/realtimeData/"+jsonObj.get("siteId"), jsonObj);

        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error =  {}", e);
        }
    }

    private void processingDeviceData(JSONObject jsonObj, String grId) {
        switch (grId) {
            case "100":
                jsonObj = processingPcsData(jsonObj);
                break;
            case "200":
                jsonObj = processingBmsData(jsonObj);
                break;
            case "311":
                jsonObj = processingPviData(jsonObj);
                break;
        }
    }

    // PCS - 100
    public JSONObject processingPcsData(JSONObject jsonObj) {
        List<JSONObject> payload = (List<JSONObject>) jsonObj.get("payload");
        Double capa = controlStore.getDeviceCapa(jsonObj); // PCS 용량
        capa = capa == null ? 0 : capa;

        for(JSONObject jsonTemp : payload) {
            jsonTemp.put("capa", capa);
        }
        return jsonObj;
    }

    // BMS - 200
    public JSONObject processingBmsData(JSONObject jsonObj) {
        List<JSONObject> payload = (List<JSONObject>) jsonObj.get("payload");
        Double capa = controlStore.getDeviceCapa(jsonObj);
        capa = capa == null ? 0 : capa;

        for(JSONObject jsonTemp : payload) {
            jsonTemp.put("capa", capa);
        }

        return jsonObj;
    }

    // PVI - 311
    public JSONObject processingPviData(JSONObject jsonObj) {
        List<JSONObject> payload = (List<JSONObject>) jsonObj.get("payload");
        for(JSONObject jsonTemp : payload) {
            Double capa = controlStore.getDeviceCapa(jsonTemp);
            capa = capa == null ? 0 : capa;
            jsonTemp.put("capa", capa);
        }
        return jsonObj;
    }

    public void sendEventRealtime (Exchange exchange) {
        String json = null;
        Object obj = null;
        JSONObject jsonObject = new JSONObject();
        try {
            obj = exchange.getIn().getBody();
            json = (String) obj;
            Message event = objectMapper.readValue(json, Message.class);

            JSONParser jsonParser = new JSONParser();
            obj = jsonParser.parse(json);
            JSONObject jsonObj = (JSONObject) obj;
//            Message event = (Message) obj;

            jsonObj.put("classification", "event"); // 이벤트 데이터

            event.setSiteNm(commonStore.getSiteNm(event));
            event.setDevNm(commonStore.getDevNm(event));

            this.template.convertAndSend("/pms/push/realtimeData/"+event.getSiteId(), jsonObj);

            umsService.sendEventMessage(event);
            log.info(json);

        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error =  {}", e);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void fixedRateTime() {
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("classification", "time");
        jsonObj.put("time", nowTime);
        this.template.convertAndSend("/pms/push/realtimeData/"+siteId, jsonObj);
    }
}
