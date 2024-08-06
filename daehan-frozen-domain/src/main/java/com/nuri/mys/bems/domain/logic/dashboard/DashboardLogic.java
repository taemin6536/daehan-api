package com.nuri.mys.bems.domain.logic.dashboard;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import com.nuri.mys.bems.domain.entity.dashboard.*;
import com.nuri.mys.bems.domain.entity.dashboard.*;

import java.util.List;

public interface DashboardLogic {

    DashboardEssRunDataRes getEssRunData();

    DashboardPmsSettingDataRes getPmsSettingData();

    List<DashboardTodayEventDataRes> getTodayEventData(CommonPageDto params);

    DashboardWeatherDataRes getWeatherData();

    DashboardOperationDataRes getDashboardOperationData();

    DashboardSiteInfoRes getSiteDetailInfo();

    List<DashboardOperationChartRes> getOperationChart();
}
