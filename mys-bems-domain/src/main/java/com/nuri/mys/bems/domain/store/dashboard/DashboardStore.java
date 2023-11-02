package com.nuri.mys.bems.domain.store.dashboard;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import com.nuri.mys.bems.domain.entity.dashboard.*;
import com.nuri.mys.bems.domain.entity.dashboard.*;

import java.util.List;

public interface DashboardStore {

    DashboardEssRunDataRes getEssRunData(DashboardEssRunDataDto params);

    DashboardEssRunDataDto getBmsData();

    DashboardPmsSettingDataRes getPmsSettingData();

    List<DashboardTodayEventDataRes> getTodayEventData(CommonPageDto params);

    int getTodayEventDataCnt();

    DashboardWeatherDataRes getPvsWeatherInfo();

    DashboardWeatherDataRes getSiteInfo();

    DashboardWeatherDataRes getWeatherInfo();

    DashboardOperationDataRes getOperationPcsData();

    DashboardOperationDataRes getOperationPviData();

    DashboardSiteInfoRes getSiteDetailInfo();

    List<DashboardOperationChartRes> getOperationChart();
}
