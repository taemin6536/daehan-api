package com.nuri.mys.bems.service.history.rest;

import com.nuri.mys.bems.domain.entity.history.*;
import com.nuri.mys.bems.domain.logic.history.HistoryLogic;
import com.nuri.mys.bems.domain.entity.history.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "6.PMS-HISTORY", description = "PMS-HISTORY")
@CrossOrigin("*")
@RestController
public class HistoryResource {

    private static final Logger logger = LogManager.getLogger(HistoryResource.class);

    @Autowired
    private HistoryLogic historyService;

    @PostMapping("/pms/history/operation/getHistoryOperationTable")
    @Operation(summary = "History > OPERATION > 이력 테이블", description = "사용자가 검색한 기간의 태양광 발전량, 소수력 발전량, 충/방전량 테이블 데이터")
    public List<HistoryOperationTableRes> getHistoryOperationTable(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryOperationTableDto params) {
        return historyService.getHistoryOperationTable(params);
    }

    @PostMapping("/pms/history/operation/getHistoryOperationChart")
    @Operation(summary = "History > OPERATION > 이력 차트", description = "사용자가 검색한 기간의 태양광 발전량, 소수력 발전량, 충/방전량 차트 데이터")
    public List<HistoryOperationChartRes> getHistoryOperationChart(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryOperationChartDto params) {
        return historyService.getHistoryOperationChart(params);
    }

    @PostMapping("/pms/history/operation/getHistoryOperationSummary")
    @Operation(summary = "History > OPERATION > 발전량,충방전량 Summary", description = "사용자가 검색한 기간의 태양광 발전량, 소수력 발전량, 충/방전량과 누적값 데이터")
    public HistoryOperationSummaryRes getHistoryOperationSummary(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryOperationSummaryDto params) {
        return historyService.getHistoryOperationSummary(params);
    }

    @PostMapping("/pms/history/device/getPcsTable")
    @Operation(summary = "History > DEVICE > PCS 통계 테이블", description = "사용자가 선택한 장비와 기간에 맞는 PCS 테이블 데이터")
    public List<HistoryDevicePcsDataRes> getStatisticsPcsTable(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceCommonDto params) {
        return historyService.getStatisticsPcsTable(params);
    }

    @PostMapping("/pms/history/device/getPcsChart")
    @Operation(summary = "History > DEVICE > PCS 통계 차트", description = "사용자가 선택한 장비와 기간에 맞는 PCS 차트 데이터")
    public List<HistoryDevicePcsDataRes> getStatisticsPcsChart(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceChartCommonDto params) {
        return historyService.getStatisticsPcsChart(params);
    }

    @PostMapping("/pms/history/device/getBmsTable")
    @Operation(summary = "History > DEVICE > BMS 통계 테이블", description = "사용자가 선택한 장비와 기간에 맞는 BMS 테이블 데이터")
    public List<HistoryDeviceBmsDataRes> getStatisticsBmsTable(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceCommonDto params) {
        return historyService.getStatisticsBmsTable(params);
    }

    @PostMapping("/pms/history/device/getBmsChart")
    @Operation(summary = "History > DEVICE > BMS 통계 차트", description = "사용자가 선택한 장비와 기간에 맞는 BMS 차트 데이터")
    public List<HistoryDeviceBmsDataRes> getStatisticsBmsChart(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceChartCommonDto params) {
        return historyService.getStatisticsBmsChart(params);
    }

    @PostMapping("/pms/history/device/getPviTable")
    @Operation(summary = "History > DEVICE > PVI 통계 테이블", description = "사용자가 선택한 장비와 기간에 맞는 PVI 테이블 데이터")
    public List<HistoryDevicePviDataRes> getStatisticsPviTable(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceCommonDto params) {
        return historyService.getStatisticsPviTable(params);
    }

    @PostMapping("/pms/history/device/getPviChart")
    @Operation(summary = "History > DEVICE > PVI 통계 차트", description = "사용자가 선택한 장비와 기간에 맞는 PVI 차트 데이터")
    public List<HistoryDevicePviDataRes> getStatisticsPviChart(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceChartCommonDto params) {
        return historyService.getStatisticsPviChart(params);
    }

    @PostMapping("/pms/history/device/getThsTable")
    @Operation(summary = "History > DEVICE > THS 통계 테이블", description = "사용자가 선택한 장비와 기간에 맞는 THS 테이블 데이터")
    public List<HistoryDeviceThsDataRes> getStatisticsThsTable(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceCommonDto params) {
        return historyService.getStatisticsThsTable(params);
    }

    @PostMapping("/pms/history/device/getThsChart")
    @Operation(summary = "History > DEVICE > THS 통계 차트", description = "사용자가 선택한 장비와 기간에 맞는 THS 차트 데이터")
    public List<HistoryDeviceThsDataRes> getStatisticsThsChart(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceChartCommonDto params) {
        return historyService.getStatisticsThsChart(params);
    }

    @PostMapping("/pms/history/event/getEventStatisticsSummary")
    @Operation(summary = "History > EVENT > 이벤트 Summary(고장,경고)", description = "사용자가 검색한 기간의 장비별 고장/경고 이벤트 발생 횟수 요약 데이터")
    public List<HistoryEventSumRes> getEventStatisticsSummary(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryEventSumDto params) {
        return historyService.getEventStatisticsSummary(params);
    }

    @PostMapping("/pms/history/event/getEventStatisticsTable")
    @Operation(summary = "History > EVENT > 이벤트 테이블 데이터", description = "사용자가 검색한 조건에 따른 장비별 이벤트 발생 시간, 수위, 메세지 등의 테이블 데이터")
    public List<HistoryEventTableRes> getEventStatisticsTable(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryEventTableDto params) {
        return historyService.getEventStatisticsTable(params);
    }

    @PostMapping("/pms/header/getRealtimeData")
    @Operation(summary = "History > DEVICE > 실시간 데이터", description = "사용자가 검색한 장비와 기간에 맞는 실시간 데이터 엑셀 다운로드")
    public <T> List<T> getDeviceRealtimeData(
            @ApiIgnore Authentication authentication,
            @RequestBody @Valid HistoryDeviceRealtimeDto params) {
        return historyService.getDeviceRealtimeData(params);
    }
}
