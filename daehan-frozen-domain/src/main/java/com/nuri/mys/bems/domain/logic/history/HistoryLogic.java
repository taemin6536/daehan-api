package com.nuri.mys.bems.domain.logic.history;

import com.nuri.mys.bems.domain.entity.history.*;
import com.nuri.mys.bems.domain.entity.history.*;

import java.util.List;

public interface HistoryLogic {


    List<HistoryOperationTableRes> getHistoryOperationTable(HistoryOperationTableDto params);
    List<HistoryOperationChartRes> getHistoryOperationChart(HistoryOperationChartDto params);
    HistoryOperationSummaryRes getHistoryOperationSummary(HistoryOperationSummaryDto params);

    List<HistoryDevicePcsDataRes> getStatisticsPcsTable(HistoryDeviceCommonDto params);
    List<HistoryDeviceBmsDataRes> getStatisticsBmsTable(HistoryDeviceCommonDto params);
    List<HistoryDevicePviDataRes> getStatisticsPviTable(HistoryDeviceCommonDto params);
    List<HistoryDeviceThsDataRes> getStatisticsThsTable(HistoryDeviceCommonDto params);

    List<HistoryDevicePcsDataRes> getStatisticsPcsChart(HistoryDeviceChartCommonDto params);
    List<HistoryDeviceBmsDataRes> getStatisticsBmsChart(HistoryDeviceChartCommonDto params);
    List<HistoryDevicePviDataRes> getStatisticsPviChart(HistoryDeviceChartCommonDto params);
    List<HistoryDeviceThsDataRes> getStatisticsThsChart(HistoryDeviceChartCommonDto params);

    List<HistoryEventSumRes> getEventStatisticsSummary(HistoryEventSumDto params);
    List<HistoryEventTableRes> getEventStatisticsTable(HistoryEventTableDto params);

    <T> List<T> getDeviceRealtimeData(HistoryDeviceRealtimeDto params);
}
