package com.nuri.mys.bems.service.operation.rest;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import com.nuri.mys.bems.domain.entity.operation.*;
import com.nuri.mys.bems.domain.entity.operation.*;
import com.nuri.mys.bems.domain.logic.operation.OperationLogic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "5.PMS-OPERATION", description = "PMS-OPERATION")
@CrossOrigin("*")
@RestController
public class OperationResource {

    private static final Logger logger = LogManager.getLogger(OperationResource.class);

    @Autowired
    private OperationLogic operationService;

    @PostMapping("/pms/operation/status/operationSummaryData")
    @Operation(summary="현황 > 운영현황", description="금일 및 누적 태양광 발전량, 소수력 발전량, 충방전량 요약 데이터")
    public OperationSummaryRes getOperationSummary(@ApiIgnore Authentication authentication){
        return operationService.getOperationSummary();
    }

    @PostMapping("/pms/operation/status/operationChartData")
    @Operation(summary="현황 > 운영현황", description="금일 및 누적 태양광 발전량, 소수력 발전량, 충방전량 차트 데이터")
    public List<OperationChartRes> getOperationChart(@ApiIgnore Authentication authentication){
        return operationService.getOperationChart();
    }

    @PostMapping("/pms/operation/status/operationTableData")
    @Operation(summary="현황 > 운영현황", description="금일 및 누적 태양광 발전량, 소수력 발전량, 충방전량 요약 데이터")
    public List<OperationTableRes> getOperationTable(
            @Valid @RequestBody CommonPageDto params,
            @ApiIgnore Authentication authentication){
        return operationService.getOperationTable(params);
    }

    @PostMapping("/pms/operation/status/pcsOperationStatusChart")
    @Operation(summary="현황 > 장비현황", description="pcs 운영현황 차트")
    public List<OperationDevicePcsDataRes> getPcsStatusChart(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusChart(params, request);
    }

    @PostMapping("/pms/operation/status/pcsOperationStatusTable")
    @Operation(summary="현황 > 장비현황", description="pcs 운영현황 그리드")
    public List<OperationDevicePcsDataRes> getPcsStatusTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusTable(params, request);
    }

    @PostMapping("/pms/operation/status/bmsOperationStatusChart")
    @Operation(summary="현황 > 장비현황", description="bms 운영현황 차트")
    public List<OperationDeviceBmsDataRes> getBmsStatusChart(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusChart(params, request);
    }

    @PostMapping("/pms/operation/status/bmsOperationStatusTable")
    @Operation(summary="현황 > 장비현황", description="bms 운영현황 그리드")
    public List<OperationDeviceBmsDataRes> getBmsStatusTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusTable(params, request);
    }

    @PostMapping("/pms/operation/status/pviOperationStatusChart")
    @Operation(summary="현황 > 장비현황", description="pvi 운영현황 차트")
    public List<OperationDevicePviDataRes> getPviStatusChart(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusChart(params, request);
    }

    @PostMapping("/pms/operation/status/pviOperationStatusTable")
    @Operation(summary="현황 > 장비현황", description="pvi 운영현황 그리드")
    public List<OperationDevicePviDataRes> getPviStatusTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusTable(params, request);
    }

    @PostMapping("/pms/operation/status/thsOperationStatusChart")
    @Operation(summary="현황 > 장비현황", description="ths 운영현황 차트")
    public List<OperationDeviceThsDataRes> getThsStatusChart(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusChart(params, request);
    }

    @PostMapping("/pms/operation/status/thsOperationStatusTable")
    @Operation(summary="현황 > 장비현황", description="ths 운영현황 그리드")
    public List<OperationDeviceThsDataRes> getThsStatusTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusTable(params, request);
    }

    @PostMapping("/pms/operation/status/pvsOperationStatusChart")
    @Operation(summary="현황 > 장비현황", description="pvs 운영현황 차트")
    public List<OperationDevicePvsDataRes> getPvsStatusChart(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusChart(params, request);
    }

    @PostMapping("/pms/operation/status/pvsOperationStatusTable")
    @Operation(summary="현황 > 장비현황", description="pvs 운영현황 그리드")
    public List<OperationDevicePvsDataRes> getPvsStatusTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationDeviceCommonDto params,
            HttpServletRequest request){
        return operationService.getDeviceStatusTable(params, request);
    }

    @PostMapping("/pms/operation/status/operationEventSummary")
    @Operation(summary="현황 > 이벤트 현황", description="금일의 장비별 고장/경고 이벤트 발생 횟수 요약 데이터")
    public List<OperationEventSummaryRes> getEventStatusSummary(@ApiIgnore Authentication authentication){
        return operationService.getEventStatusSummary();
    }

    @PostMapping("/pms/operation/status/operationEventTable")
    @Operation(summary="현황 > 장비현황", description="금일의 장비별 이벤트 발생 시간, 수위, 메세지 등의 테이블 데이터")
    public List<OperationEventTableRes> getEventStatusTable(
            @ApiIgnore Authentication authentication,
            @Valid @RequestBody OperationEventTableDto params){
        return operationService.getEventStatusTable(params);
    }
}
