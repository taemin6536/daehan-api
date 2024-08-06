package com.nuri.mys.bems.service.dashboard;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import com.nuri.mys.bems.domain.entity.dashboard.*;
import com.nuri.mys.bems.domain.entity.dashboard.*;
import com.nuri.mys.bems.domain.logic.dashboard.DashboardLogic;
import com.nuri.mys.bems.domain.store.dashboard.DashboardStore;
import com.nuri.mys.bems.service.common.PagingParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService implements DashboardLogic {

    @Autowired
    private DashboardStore dashboardStore;

    @Autowired Sunset sunset;

    @Override
    public DashboardEssRunDataRes getEssRunData() {
        DashboardEssRunDataDto temp = dashboardStore.getBmsData();
        return dashboardStore.getEssRunData(temp);
    }

    @Override
    public DashboardPmsSettingDataRes getPmsSettingData() {
        return dashboardStore.getPmsSettingData();
    }

    @Override
    public List<DashboardTodayEventDataRes> getTodayEventData(CommonPageDto params) {
        PagingParamService.setPagingParam(params);
        List<DashboardTodayEventDataRes> result = dashboardStore.getTodayEventData(params);
        if(result.size() > 0) {
            int cnt = dashboardStore.getTodayEventDataCnt();
            result.get(0).setDataTotalCount(cnt);
        }
        return result;
    }

    @Override
    public DashboardWeatherDataRes getWeatherData(){
        DashboardWeatherDataRes result1 = new DashboardWeatherDataRes(); // 일출 일몰 정보
        DashboardWeatherDataRes result = dashboardStore.getWeatherInfo(); // 날씨 정보
        DashboardWeatherDataRes pvsInfo = dashboardStore.getPvsWeatherInfo(); //기상정보

        result = result != null ? result : new DashboardWeatherDataRes();

        try {
            result1 = sunset.operation();
//            sunset = new Sunset().operation(); // new로 객체를 생성해서 쓰면 그 객체는 Spring Container가 관리하는게 아니어서 @Autowired를 통한 의존성 주입이 불가
            result.setRiseTime(result1.getRiseTime());
            result.setSetTime(result1.getSetTime());

            if(pvsInfo != null) {
                result.setTemp(pvsInfo.getTemp());
                result.setHumidity(pvsInfo.getHumidity());
                result.setHoriRadiation(pvsInfo.getHoriRadiation());
                result.setInclRadiation(pvsInfo.getInclRadiation());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        //test
    }

    @Override
    public DashboardOperationDataRes getDashboardOperationData() {
        DashboardOperationDataRes result1 = dashboardStore.getOperationPcsData(); // 충방전 데이터
        DashboardOperationDataRes result2 = dashboardStore.getOperationPviData(); // 발전량 데이터

        DashboardOperationDataRes result = result1 != null ? result1 : new DashboardOperationDataRes();

        if(result2 != null) {
            result.setDailyGenKwh(result2.getDailyGenKwh());
            result.setMonlyGenKwh(result2.getMonlyGenKwh());
        }
        return result;
    }

    @Override
    public DashboardSiteInfoRes getSiteDetailInfo() {
        return dashboardStore.getSiteDetailInfo();
    }

    @Override
    public List<DashboardOperationChartRes> getOperationChart() {
        return dashboardStore.getOperationChart();
    }
}
