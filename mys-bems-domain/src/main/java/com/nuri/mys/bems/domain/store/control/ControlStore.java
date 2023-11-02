package com.nuri.mys.bems.domain.store.control;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.control.*;
import com.nuri.mys.bems.domain.entity.control.*;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface ControlStore {
    List<ControlTableDataRes> getControlTableData(ControlTableDataDto params);

    int getControlTableDataCnt(ControlTableDataDto params);

    List<ControlTableDataRes> getControlSettingData(ControlTableDataDto params);

    List<CommonCodeRes> getSearchDevice();

    List<ControlDeviceNumberRes> getSearchDeviceNumber();

    int savePmsSetting(ControlEssSettingDto params);

    void saveSettingHistory(ControlCommonDto params);

    void saveControlHistory(ControlDeviceDto params);

    Map<String, Object> getDeviceNm(ControlDeviceDto params);

    String getGrNm(ControlDeviceDto params);

    String getPcsDeviceNm(ControlCommonDto params);

    ControlDrivingmodeRes getStatusDrivingMode();

    String getWindDirection(JSONObject jsonTemp);

    Double getDeviceCapa(JSONObject jsonObj);

    void saveSettingMngHistory(ControlCommonDto dto);
}
