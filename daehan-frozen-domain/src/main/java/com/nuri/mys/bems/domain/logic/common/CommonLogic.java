package com.nuri.mys.bems.domain.logic.common;

import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.domain.entity.common.*;

import java.util.List;

public interface CommonLogic {
    public String minlyFormat = "yyyyMMddHHmm";
    public String hourlyFormat = "yyyyMMddHH";
    public String dailyFormat = "yyyyMMdd";
    public String monthlyFormat = "yyyyMM";
    public String timeFormat = "HHmm";

    public CommonDto periodSearch(CommonDto params);
    public CommonPeriodDto periodSearch(CommonPeriodDto params);
    public List<String> makeChartTicks(CommonDto params);
    public List<String> makeChartTicks(CommonPeriodDto params);

    List<CommonCodeRes> getCommonDeviceInfo();

    List<CommonDeviceDetailRes> getCommonDeviceDetailInfo(CommonDeviceDetailDto params);

    List<CommonCodeRes> getEventLevel();

    List<CommonCodeRes> getEventStatus();
}
