package com.nuri.mys.bems.service.dashboard.rest;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import com.nuri.mys.bems.domain.entity.dashboard.*;
import com.nuri.mys.bems.domain.entity.dashboard.*;
import com.nuri.mys.bems.domain.logic.dashboard.DashboardLogic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@Tag(name = "3.PMS-DASHBOARD", description = "PMS-DASHBOARD")
@CrossOrigin("*")
@RestController
public class DashboardResource {

    @Autowired
    private DashboardLogic dashboardService;

    private static final Logger logger = LogManager.getLogger(DashboardResource.class);

    @PostMapping("/pms/dashboard/status/weatherInfo")
    @Operation(summary = "대시보드 > 기상현황", description = "일출 일몰 시간, 일사량 등 현재 시간의 기상 데아터")
    public DashboardWeatherDataRes getWeatherData(@ApiIgnore Authentication authentication) {
        return dashboardService.getWeatherData();
    }

    @PostMapping("/pms/dashboard/status/operationData")
    @Operation(summary = "대시보드 > 운영 현황", description = "금일, 금월 등 충방전, 발전량 데이터")
    public DashboardOperationDataRes getDashboardOperationData(@ApiIgnore Authentication authentication) {
        return dashboardService.getDashboardOperationData();
    }

    @PostMapping("/pms/dashboard/status/siteDetailInfo")
    @Operation(summary = "대시보드 > 사업장 정보", description = "사업장명 주소 등 상세 정보")
    public DashboardSiteInfoRes getSiteDetailInfo(@ApiIgnore Authentication authentication) {
        return dashboardService.getSiteDetailInfo();
    }

    @PostMapping("/pms/dashboard/status/essRunData")
    @Operation(summary = "대시보드 > ESS 운용률", description = "금일, 전일 충방전 운용률 데이터", hidden=true)
    public DashboardEssRunDataRes getEssRunData(@ApiIgnore Authentication authentication) {
        return dashboardService.getEssRunData();
    }

    @PostMapping("/pms/dashboard/status/pmsSettingInfo")
    @Operation(summary = "대시보드 > 설정 및 정보", description = "장비별 용량 및 SOC 데이터")
    public DashboardPmsSettingDataRes getPmsSettingData(@ApiIgnore Authentication authentication) {
        return dashboardService.getPmsSettingData();
    }

    @PostMapping("/pms/header/todayEventData")
    @Operation(summary = "대시보드 > 이벤트", description = "오늘 발생한 이벤트 중 종료되지 않은 이벤트 데이터")
    public List<DashboardTodayEventDataRes> getTodayEventData(
            @ApiIgnore Authentication authentication,
            @RequestBody CommonPageDto params) {
        return dashboardService.getTodayEventData(params);
    }

    @PostMapping("/pms/dashboard/status/operationChartData")
    @Operation(summary="대시보드 > 발전량 및 충방전량 차트", description="차트(금일 시간별 발전량 및 충방전량)")
    public List<DashboardOperationChartRes> getOperationChart(@ApiIgnore Authentication authentication){
        return dashboardService.getOperationChart();
    }
}
