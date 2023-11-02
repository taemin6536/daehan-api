package com.nuri.mys.bems.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuri.mys.bems.domain.store.common.CommonStore;
import com.nuri.mys.bems.service.control.Message;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class EventPush {

    protected static final SimpleDateFormat SDF_SEC = new SimpleDateFormat("yyyyMMddHHmmss");
    protected static final SimpleDateFormat SDF_MIL = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    ObjectMapper mapper = new ObjectMapper();
    Random random = new Random();
    Date time = new Date();

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private UmsService umsService;

    @Autowired
    private CommonStore commonStore;

//    @Scheduled(cron = "0 */1 * * * *")
    private void sendEventData() throws JsonProcessingException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String[] grArr = new String[]{"100", "200", "311"};
        String[] lvlArr = new String[]{"F", "W"};

        List<JSONObject> payload = new ArrayList<JSONObject>();
        JSONObject resultObj = new JSONObject();
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("grId", grArr[random.nextInt(3)]);
        jsonObj.put("evtLevel", random.nextInt(2) + 2);
        jsonObj.put("evtName", "Rest Cell Balancing");
        jsonObj.put("evtDesc", "Operating Cell Balancing during the break");
        jsonObj.put("startTime", SDF_SEC.format(time));

        payload.add(jsonObj);

        resultObj.put("classification", "event");
        resultObj.put("devId", jsonObj.get("grId") + "0001");
        resultObj.put("grId", jsonObj.get("grId"));
        resultObj.put("siteId", "3040023001");
        resultObj.put("time", jsonObj.get("startTime"));
        resultObj.put("transactionId", SDF_MIL.format(time));
        resultObj.put("payload", payload);

        Message event = mapper.readValue(resultObj.toJSONString(), Message.class);

        event.setSiteNm(commonStore.getSiteId());
        event.setDevNm(commonStore.getDevNm(event));

        umsService.sendEventMessage(event);
//        this.template.convertAndSend("/pms/push/realtimeData/1050022010", payload);


    }
}
