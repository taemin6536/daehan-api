package com.nuri.mys.bems.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nuri.mys.bems.domain.entity.common.RealtimeDto;
import com.nuri.mys.bems.domain.store.common.CommonStore;
import com.nuri.mys.bems.domain.store.control.ControlStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class PushTestHandler {

    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    private ControlStore controlStore;

    @Autowired
    private CommonStore commonStore;
    
    @Autowired
    private UmsService umsService;

    private static ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new Gson();
    private static JSONParser jsonParser = new JSONParser();
    Random random = new Random();

    List<RealtimeDto> params = new ArrayList<>();

    private static String siteId;

    @PostConstruct
    public final void setSiteId() {
        String text = commonStore.getSiteId();
        siteId = text;
    }

    public void setFlowchartState(List<RealtimeDto> params){
        this.params = params;
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

            String grId = (String) jsonObj.get("grId") == null ?
                    String.valueOf(jsonObj.get("devId")).substring(0, 3) : (String) jsonObj.get("grId");
            tmp.put("devId", jsonObj.get("devId"));
            jsonObj.put("grId", grId);
            payload.add(tmp);
            jsonObj.put("siteId", "1050022010");
            jsonObj.remove("devId");
            jsonObj.put("payload", payload);
            jsonObj.put("classification", "device"); // 장비 실시간 데이터

            processingDeviceData(jsonObj, grId);

            this.template.convertAndSend("/pms/push/realtimeData/"+jsonObj.get("siteId"), jsonObj);

        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error =  {}", e);
        }
    }

//    @Scheduled(fixedRate = 1000)
    public void fixedRateTime() {
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("classification", "time");
        jsonObj.put("time", nowTime);
        this.template.convertAndSend("/pms/push/realtimeData/1050022010", jsonObj);
    }

    private void processingDeviceData(JSONObject jsonObj, String grId) {
        String changeGrid = "";
        switch (grId) {
            case "100":
                jsonObj = processingPcsData(jsonObj);
                break;
            case "200":
                jsonObj = processingBmsData(jsonObj);
                break;
            case "360":
                changeGrid = "311";
                jsonObj.put("grId", changeGrid);
                jsonObj = processingPviData(jsonObj);
                break;
        }

    }

    public void sendEventRealtime (Exchange exchange) {
        String json = null;
        Object obj = null;
        JSONObject jsonObject = new JSONObject();
        try {
            obj = exchange.getIn().getBody();
            json = (String) obj;

            JSONParser jsonParser = new JSONParser();
            obj = jsonParser.parse(json);
            JSONObject jsonObj = (JSONObject) obj;
            String grId = String.valueOf(jsonObj.get("grId"));

            jsonObj.put("classification", "event"); // 이벤트 데이터

            this.template.convertAndSend("/pms/push/realtimeData/"+jsonObj.get("siteId"), jsonObj);

//            umsService.sendEventMessage(jsonObj);

            log.info(json);

        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error =  {}", e);
        }
    }

    // PCS - 100
    public JSONObject processingPcsData(JSONObject jsonObj) {
        List<JSONObject> payload = (List<JSONObject>) jsonObj.get("payload");
        Double capa = controlStore.getDeviceCapa(jsonObj);
        capa = capa == null ? 0 : capa;
        
        for(JSONObject jsonTemp : payload) {
            jsonTemp.put("capa", capa);
            jsonTemp.put("gridVR", 0);
            jsonTemp.put("gridVS", 0);
            jsonTemp.put("gridVT", 0);
            setRealtimeTestData(jsonTemp, "100");
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
            jsonTemp.put("avgCellV", random.nextDouble() * 150);
            jsonTemp.put("avgCellT", random.nextDouble() * 15);
            setRealtimeTestData(jsonTemp, "200");
        }
        return jsonObj;
    }

    // PVI - 311
    public JSONObject processingPviData(JSONObject jsonObj) {
        String grId = (String) jsonObj.get("grId");
        List<JSONObject> payload = (List<JSONObject>) jsonObj.get("payload");
        List<JSONObject> result = new ArrayList<JSONObject>();
        Double capa = controlStore.getDeviceCapa(jsonObj);
        capa = capa == null ? 0 : capa;

        Double gridVRS = 0.0;
        Double gridVST = 0.0;
        Double gridVTR = 0.0;
        Double gridRA = 0.0;
        Double gridSA = 0.0;
        Double gridTA = 0.0;

        for(JSONObject jsonTemp : payload) {
            JSONObject resultObj = new JSONObject();
            gridVRS = Double.parseDouble(jsonTemp.get("AC_RSV").toString());
            gridVST = Double.parseDouble(jsonTemp.get("AC_STV").toString());
            gridVTR = Double.parseDouble(jsonTemp.get("AC_TRV").toString());
            gridRA = Double.parseDouble(jsonTemp.get("AC_RA").toString());
            gridSA = Double.parseDouble(jsonTemp.get("AC_SA").toString());
            gridTA = Double.parseDouble(jsonTemp.get("AC_TA").toString());

            resultObj.put("devId", grId + String.valueOf(jsonTemp.get("devId")).substring(3, 7));
            resultObj.put("capa", capa);
            resultObj.put("runState", jsonTemp.get("run_state"));
            resultObj.put("comYn", jsonTemp.get("comYn"));
            resultObj.put("tripState", jsonTemp.get("DC_trip"));
            resultObj.put("dcKw", ((Double) jsonTemp.get("DC_inputV")) * ((Double) jsonTemp.get("DC_inputA")) / 1000);
            resultObj.put("dcV", jsonTemp.get("DC_inputV"));
            resultObj.put("dcA", jsonTemp.get("DC_inputA"));
            resultObj.put("actKw", jsonTemp.get("AC_outputKw"));
            resultObj.put("reactKw", 0);
            resultObj.put("gridVRS", gridVRS);
            resultObj.put("gridVST", gridVST);
            resultObj.put("gridVTR", gridVTR);
            resultObj.put("gridAR", gridRA);
            resultObj.put("gridAS", gridSA);
            resultObj.put("gridAT", gridTA);
            resultObj.put("gridPF", 0);
            resultObj.put("gridFreq", jsonTemp.get("Freq"));
            resultObj.put("boxTemp", jsonTemp.get("InTemp"));
            resultObj.put("outTemp", 0);
            resultObj.put("gridKwhOut", 0);
            resultObj.put("gridKwhIn", 0);
            resultObj.put("totGridKwhOut", 0);
            resultObj.put("totGridKwhIn", 0);

            setRealtimeTestData(resultObj, "311");

            result.add(resultObj);
        }
        jsonObj.put("payload", result);
        return jsonObj;
    }

    public void setRealtimeTestData(JSONObject jsonTemp, String grId){
        params.stream().forEach((v)->{
            if(v.getGrId().equals(grId)){
                jsonTemp.put(v.getState(), v.getValue());
            }
        });
    }
}
