package com.nuri.mys.bems.domain.store.common;

import com.nuri.mys.bems.domain.entity.common.CommonCodeRes;
import com.nuri.mys.bems.domain.entity.common.CommonDeviceDetailDto;
import com.nuri.mys.bems.domain.entity.common.CommonDeviceDetailRes;
import com.nuri.mys.bems.domain.entity.ums.UmsResultModel;
import com.nuri.mys.bems.domain.entity.common.*;
import org.json.simple.JSONObject;

import java.util.List;

public interface CommonStore {
    String getMessageStatusCode(String cdNm);
    CommonCodeRes getMessageStatusCodeNew(String cdNm);

    List<CommonCodeRes> getCommonDeviceInfo();

    List<CommonDeviceDetailRes> getCommonDeviceDetailInfo(CommonDeviceDetailDto params);

    List<CommonCodeRes> getEventLevel();

    List<CommonCodeRes> getEventStatus();

    String getSiteId();

    JSONObject getPmsUserSmsInfo();

    int saveUmsHistory(UmsResultModel params);

    String getUmsClassification();

    String getDevNm(Object event);

    String getSiteNm(Object event);
}
