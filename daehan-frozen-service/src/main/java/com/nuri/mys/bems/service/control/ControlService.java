package com.nuri.mys.bems.service.control;

import com.google.gson.Gson;
import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.common.CommonResultRes;
import com.nuri.mys.bems.domain.entity.control.*;
import com.nuri.mys.bems.domain.entity.management.*;
import com.nuri.mys.bems.domain.logic.common.CommonLogic;
import com.nuri.mys.bems.domain.logic.control.ControlLogic;
import com.nuri.mys.bems.domain.store.control.ControlStore;
import com.nuri.mys.bems.domain.entity.management.*;
import com.nuri.mys.bems.domain.store.common.CommonStore;
import com.nuri.mys.bems.domain.entity.control.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@PropertySource("classpath:application.yml")
public class ControlService implements ControlLogic {

    private static final String SEC_FORMAT = "yyyyMMddHHmmss";
    private static final String MIL_FORMAT = "yyyyMMddHHmmssSSS";

    @Value("${spring.interface.ip}")
    private String ip;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private ControlStore controlStore;

    @Autowired
    private CommonLogic commonService;

    @Autowired
    private CommonStore commonStore;

    CommonCodeRes code = new CommonCodeRes();

    @Override
    public List<ControlTableDataRes> getControlTableData(ControlTableDataDto params) {
        commonService.periodSearch(params);
        params.setDateFrom(StringUtils.rightPad(params.getDateFrom(), 14, '0'));
        params.setDateTo(StringUtils.rightPad(params.getDateTo(), 14, '0'));
        List<ControlTableDataRes> resultList = new ArrayList<ControlTableDataRes>();

        if("control".equals(params.getClassificationCd())) {
            resultList = controlStore.getControlTableData(params);
        } else {
            resultList = controlStore.getControlSettingData(params);
        }
        if(resultList.size() > 0) {
            int cnt = controlStore.getControlTableDataCnt(params);
            resultList.get(0).setDataTotalCount(cnt);
        }
        return resultList;
    }

    @Override
    public List<CommonCodeRes> getSearchDevice() {
        return controlStore.getSearchDevice();
    }

    @Override
    public List<ControlDeviceNumberRes> getSearchDeviceNumber() {
        return controlStore.getSearchDeviceNumber();
    }

    @Override
    public CommonResultRes saveEssSetting(ControlEssSettingDto params) {
        CommonResultRes result = new CommonResultRes();

        // SYSTEM 계정일 경우 제어 권한 X
        if(params.getUserId().toUpperCase().equals("SYSTEM")) {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NO_ACCESS_PERMISSION");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
            return result;
        }

        ManagementSettingEssInfoRes beforeData = params.getBeforeData();
        ManagementSettingSaveEssDto afterData = params.getAfterData();
        List<String> descList = new ArrayList<String>();
        String desc = "";

        if(beforeData.getMaxSoc() != afterData.getMaxSoc() || beforeData.getMinSoc() != afterData.getMinSoc()) {
            desc = String.valueOf("Soc:" + beforeData.getMinSoc() + "~" + beforeData.getMaxSoc() + "->"
            + afterData.getMinSoc() + "~" + afterData.getMaxSoc());
            descList.add(desc);
        }
        if(!beforeData.getSysOPMode().equals(afterData.getSysOPModeNm())) {
            desc = String.valueOf("Driving Mode:"+beforeData.getSysOPMode() + "->" + afterData.getSysOPModeNm());
            descList.add(desc);
        }

        JSONObject obj = new JSONObject(); // 전체 jsonObject
        JSONObject temp = new JSONObject(); // payload
        params.setSiteId(commonStore.getSiteId());

        temp.put("minSoc", afterData.getMinSoc());
        temp.put("maxSoc", afterData.getMaxSoc());
        temp.put("mode", Integer.valueOf(afterData.getSysOPMode()));

        obj.put("time", getNowDate(SEC_FORMAT));
        obj.put("transactionId", getNowDate(MIL_FORMAT));
        obj.put("siteId", params.getSiteId());
        obj.put("userId", params.getUserId());
        obj.put("grId", "");
        obj.put("devId", "");
        obj.put("payload", temp);
        params.setDescription(StringUtils.join(descList, ", "));
//        params.setDescription(getDescription("essSetting")); // description 생성

        try {
            controlStore.saveSettingHistory(params); // 제어 이력 저장
//            temp.put("seq", params.getSeq());
            obj.put("payload", temp);
            result = sendControlSetting(obj, "setting");
        } catch (Exception e) {
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    @Override
    public CommonResultRes sendControl(ControlDeviceDto params) {
        CommonResultRes result = new CommonResultRes();

        // SYSTEM 계정일 경우 제어 권한 X
        if(params.getUserId().toUpperCase().equals("SYSTEM")) {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NO_ACCESS_PERMISSION");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
            return result;
        }

        JSONObject obj = new JSONObject(); // 전체 returnObj

        // 장비 명, 장비 그룹 명 세팅
        if("all".equals(params.getCommand().getDevId())) {
            String grNm = controlStore.getGrNm(params);
            params.getCommand().setDevNm(grNm + "-ALL");
            params.setGrNm(grNm);
        } else {
            Map<String, Object> nameMap = controlStore.getDeviceNm(params);
            params.getCommand().setDevNm((String) nameMap.get("deviceNm"));
            params.setGrNm((String) nameMap.get("grNm"));
        }

        params.setDescription(getDescription(params.getClassification())); // description 생성
        controlStore.saveControlHistory(params); // 제어 이력 저장

        // 충전일 때 양수->음수 변환 필요
        if(params.getClassification().equals("controlChadi")) {
            if(params.getCommand().getName().equals("charge")) {
                params.getCommand().setParam(params.getCommand().getParam() * -1);
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(params);
        obj = (JSONObject) JSONValue.parse(json);

        obj.remove("classification");
        obj.remove("description");
        obj.remove("grNm");
        ((JSONObject) obj.get("command")).remove("devNm");
        obj.put("devId", ((JSONObject) obj.get("command")).get("devId"));
        ((JSONObject) obj.get("command")).remove("devId");
        obj.put("time", getNowDate(SEC_FORMAT));
        obj.put("transactionId", getNowDate(MIL_FORMAT));
        obj.put("payload", obj.get("command"));
        obj.remove("command");

        result = sendControlSetting(obj, params.getGrNm());
        code = commonStore.getMessageStatusCodeNew("SESS");
        result.setStatus(code.getDetailCd());
        result.setPopContents(code.getCdEngNm());
        if("STATUS6".equals(result.getStatus()) && "controlMode".equals(params.getClassification())) {
            result.setEtc("mode:" + String.valueOf(params.getCommand().getName()));
        }

        return result;
    }

    public CommonResultRes sendSchedule(ManagementSchCommonDto params) {
        CommonResultRes result = new CommonResultRes();

        // SYSTEM 계정일 경우 제어 권한 X
        if(params.getUserId().toUpperCase().equals("SYSTEM")) {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NO_ACCESS_PERMISSION");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
            return result;
        }

        // db insert 또는 update 성공 시
        JSONObject obj = new JSONObject();
        JSONObject temp = new JSONObject();

        obj.put("time", getNowDate(SEC_FORMAT));
        obj.put("transactionId", getNowDate(MIL_FORMAT));
        obj.put("siteId", params.getSiteId());
        obj.put("userId", params.getUserId());
        obj.put("devId", params.getDeviceId());
        obj.put("grId", "100");

        temp.put("seasonCd", params.getSeasonCd());
        temp.put("weekCd", params.getWeekCd());

        ControlScheduleDto dto = new ControlScheduleDto();
        dto.setSiteId(params.getSiteId());
        dto.setUserId(params.getUserId());
        dto.setDescription(getDescription("schedule"));
        dto.setDevId(params.getDeviceId());
        dto.setCommand(temp);

        // 장비 명, 장비 그룹 명 세팅
        if(!"1000".equals(params.getDeviceId())) {
            String deviceNm = controlStore.getPcsDeviceNm(dto);
            dto.setDevNm(deviceNm);
        } else {
            dto.setDevNm("PCS-ALL");
        }

        try {
            controlStore.saveSettingMngHistory(dto); // 제어 이력 저장
            obj.put("payload", temp);
            result = sendControlSetting(obj, "schedule");
        } catch (Exception e) {
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
            return result;
        }

        return result;
    }

    public CommonResultRes sendHoliday(ManagementHolidayCommonDto params) {
        CommonResultRes result = new CommonResultRes();

        // SYSTEM 계정일 경우 제어 권한 X
        if(params.getUserId().toUpperCase().equals("SYSTEM")) {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NO_ACCESS_PERMISSION");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
            return result;
        }

        // db insert 또는 update 성공 시
        JSONObject obj = new JSONObject();
        JSONObject temp = new JSONObject();

        obj.put("time", getNowDate(SEC_FORMAT));
        obj.put("transactionId", getNowDate(MIL_FORMAT));
        obj.put("siteId", params.getSiteId());
        obj.put("userId", params.getUserId());
        obj.put("devId", params.getDeviceId());
        obj.put("grId", "100");

        temp.put("holidayCd", params.getHolidayCd());
        temp.put("day", params.getDay());

        ControlHolidayDto dto = new ControlHolidayDto();
        dto.setSiteId(params.getSiteId());
        dto.setUserId(params.getUserId());
        dto.setDescription(getDescription("holiday"));
        dto.setDevId(params.getDeviceId());
        dto.setCommand(temp);

        // 장비 명, 장비 그룹 명 세팅
        if(!"1000".equals(params.getDeviceId())) {
            String deviceNm = controlStore.getPcsDeviceNm(dto);
            dto.setDevNm(deviceNm);
        } else {
            dto.setDevNm("PCS-ALL");
        }

        try {
            controlStore.saveSettingMngHistory(dto); // 제어 이력 저장
            obj.put("payload", temp);
            result = sendControlSetting(obj, "holiday");
        } catch (Exception e) {
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
            return result;
        }

        return result;
    }

    public CommonResultRes sendCapacity(ControlCapacityDto params) {
        CommonResultRes result = new CommonResultRes();

        // SYSTEM 계정일 경우 제어 권한 X
        if(params.getUserId().toUpperCase().equals("SYSTEM")) {
            result.setStatus(commonStore.getMessageStatusCode("FAIL"));
            code = commonStore.getMessageStatusCodeNew("NO_ACCESS_PERMISSION");
//            result.setFailContents(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
            return result;
        }

        // db insert 또는 update 성공 시
        JSONObject obj = new JSONObject();
        JSONObject temp = new JSONObject();
        params.setSiteId(commonStore.getSiteId());

        List<ManagementCommonCapaInfoRes> beforeData = params.getBeforeData();
        List<ManagementSettingSaveCapaDto> afterData = params.getAfterData();
        List<String> descList = new ArrayList<String>();
        String desc = "";
        for(ManagementCommonCapaInfoRes before : beforeData) {
            ManagementSettingSaveCapaDto after = (ManagementSettingSaveCapaDto) afterData.stream()
                    .filter(a -> a.getDeviceId().equals(before.getDeviceId()))
                    .findFirst().get();
            if(before.getCapa() != after.getCapa()) {
                desc = String.valueOf(before.getDeviceNm()+" Capacity:"+before.getCapa()
                +"->"+after.getCapa());
                descList.add(desc);
            }
        }

        obj.put("time", getNowDate(SEC_FORMAT));
        obj.put("transactionId", getNowDate(MIL_FORMAT));
        obj.put("siteId", params.getSiteId());
        obj.put("userId", params.getUserId());

        params.setDescription(StringUtils.join(descList, ", "));

        try {
            controlStore.saveSettingHistory(params); // 제어 이력 저장
            temp.put("name", "schUpdate");
            temp.put("seq", params.getSeq());
            obj.put("payload", temp);
            result = sendControlSetting(obj, "schedule");
        } catch (Exception e) {
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
            return result;
        }

        return result;
    }

    @Override
    public ControlDrivingmodeRes getStatusDrivingMode() {
        return controlStore.getStatusDrivingMode();
    }

    public String getNowDate(String pattern) {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(today);
    }

    public String getDescription(String Classification) {
        String str = "";
        switch (Classification) {
            case "essSetting":
                str = "\"pmsUpdate\"";
                break;
            case "controlStatus":
                str = "CONCAT(#{command.devNm}, \" \", #{command.name})";
                break;
            case "controlChadi":
                str = "CONCAT(#{command.devNm}, \" \", #{command.name}, \" \", FORMAT(#{command.param}, 2), \"kW\")";
                break;
            case "controlMode":
                str = "CONCAT(#{grNm}, \" \", #{command.name})";
                break;
            case "controlPower":
                str = "CONCAT(#{command.devNm}, \" \", #{command.name}, \" \", #{command.param}, \"%\")";
                break;
            case "schedule":
                str = "CONCAT(#{devNm}, \" Change Schedule\")";
            case "holiday":
                str = "CONCAT(#{devNm}, \" Change ClosedDay\")";
        }
        return str;
    }

    public CommonResultRes sendControlSetting(JSONObject obj, String DivisionCode) {
        CommonResultRes result = new CommonResultRes();

        try {
            String url = getUrl(DivisionCode);
            jmsTemplate.convertAndSend(new ActiveMQTopic(url), obj.toString());
            log.info("----------------------success-------------------------------");
            code = commonStore.getMessageStatusCodeNew("SESS");
            result.setStatus(code.getDetailCd());
            result.setPopContents(code.getCdEngNm());
        } catch(Exception e) {
            code = commonStore.getMessageStatusCodeNew("FAIL");
            result.setStatus(code.getDetailCd());
            result.setFailContents(code.getCdEngNm());
        }
        return result;
    }

    private String getUrl(String divisionCode) {
        switch (divisionCode) {
            case "setting":
                return "ems/pms/settings/mode";
            case "schedule":
                return "ems/pms/settings/schedule";
            case "holiday":
                return "ems/pms/settings/holiday";
            case "PCS":
                return "ems/pms/pcs/control";
            case "BMS":
                return "ems/pms/bms/control";
            default:
                return "";
        }
    }
}
