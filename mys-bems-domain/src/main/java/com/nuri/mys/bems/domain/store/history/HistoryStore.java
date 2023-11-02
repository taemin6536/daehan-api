package com.nuri.mys.bems.domain.store.history;

import com.nuri.mys.bems.domain.entity.common.CommonDto;
import com.nuri.mys.bems.domain.entity.history.*;
import com.nuri.mys.bems.domain.entity.realtime.HistoryRealtimeBmsDataRes;
import com.nuri.mys.bems.domain.entity.realtime.HistoryRealtimePcsDataRes;
import com.nuri.mys.bems.domain.entity.realtime.HistoryRealtimePviDataRes;
import com.nuri.mys.bems.domain.entity.history.*;

import java.util.List;

public interface HistoryStore {


    List<HistoryRealtimePcsDataRes> getPcsRealtimeData(HistoryDeviceRealtimeDto params);
    List<HistoryRealtimeBmsDataRes> getBmsRealtimeData(HistoryDeviceRealtimeDto params);
    List<HistoryRealtimePviDataRes> getPviRealtimeData(HistoryDeviceRealtimeDto params);
    List<HistoryDeviceThsDataRes> getThsRealtimeData(HistoryDeviceRealtimeDto params);

    int getHistoryOperationTableCnt(HistoryOperationTableDto params);
    List<HistoryOperationTableRes> getHistoryOperationTable(HistoryOperationTableDto params);
    List<HistoryOperationChartRes> getHistoryOperationChart(HistoryOperationChartDto params);
    HistoryOperationSummaryRes getHistoryOperationSummary(CommonDto params);

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
    int getEventStatisticsTableCnt(HistoryEventTableDto params);

    List<HistoryEventTableRes> getEventLatestTableData(HistoryEventTableDto params);

    List<HistoryEventSumRes> getEventLatestSummaryData(HistoryEventSumDto params);

    HistoryOperationSummaryRes getHistoryOperationTotSummary(CommonDto param);
}
