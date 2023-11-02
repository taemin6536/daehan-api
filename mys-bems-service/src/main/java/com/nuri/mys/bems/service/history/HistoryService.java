package com.nuri.mys.bems.service.history;

import com.nuri.mys.bems.domain.entity.common.CommonDto;
import com.nuri.mys.bems.domain.entity.history.*;
import com.nuri.mys.bems.domain.entity.history.*;
import com.nuri.mys.bems.domain.entity.operation.OperationDeviceCommonRes;
import com.nuri.mys.bems.domain.logic.history.HistoryLogic;
import com.nuri.mys.bems.domain.store.history.HistoryStore;
import com.nuri.mys.bems.service.common.CommonService;
import com.nuri.mys.bems.service.common.PagingParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HistoryService implements HistoryLogic {
    @Autowired
    private HistoryStore historyStore;

    @Autowired
    private CommonService commonService;

    @Override
    public List<HistoryOperationTableRes> getHistoryOperationTable(HistoryOperationTableDto params) {
        commonService.periodSearch(params);
        PagingParamService.setPagingParam(params);
        List<HistoryOperationTableRes> result = historyStore.getHistoryOperationTable(params);
        int cnt = historyStore.getHistoryOperationTableCnt(params);
        if(result.size() > 0){
            result.get(0).setDataTotalCount(cnt);
        }
        return result;
    }

    @Override
    public List<HistoryOperationChartRes> getHistoryOperationChart(HistoryOperationChartDto params) {
        commonService.periodSearch(params);

        // Chart Tick Create
        List<HistoryOperationChartRes> tickList = new ArrayList<HistoryOperationChartRes>();
        for (String time : commonService.makeChartTicks(params)) {
            HistoryOperationChartRes temp = new HistoryOperationChartRes();
            temp.setTime(time);
            tickList.add(temp);
        }

        // List 병합을 위해 Map 으로 변환
        // Tick, List to Map
        Map<String, HistoryOperationChartRes> tick = tickList.stream().collect(Collectors.toMap(HistoryOperationChartRes::getTime, historyModel -> historyModel));
        // Data, List to Map
        Map<String, HistoryOperationChartRes> data = historyStore.getHistoryOperationChart(params).stream().collect(Collectors.toMap(HistoryOperationChartRes::getTime, HistoryModel -> HistoryModel));
        // Merge Two Map
        tick.putAll(data);
        // Sort Map By Time (Asceding)
        Map<String, HistoryOperationChartRes> sortedResult =
                tick.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (unsorted, sorted) -> unsorted, LinkedHashMap::new));

        // LinkedHashMap Values to ArrayList
        return new ArrayList<HistoryOperationChartRes>(sortedResult.values());
    }


    @Override
    public HistoryOperationSummaryRes getHistoryOperationSummary(HistoryOperationSummaryDto params) {
        CommonDto param = new CommonDto();
        param.setDateFrom(params.getDateFrom());
        param.setDateTo(params.getDateTo());
        param.setPeriodType(params.getPeriodType());

        commonService.periodSearch(param);
        HistoryOperationSummaryRes totResult = historyStore.getHistoryOperationTotSummary(param);
        HistoryOperationSummaryRes result = historyStore.getHistoryOperationSummary(param);
        if(totResult != null) {
            result.setTotChargeKwh(totResult.getTotChargeKwh());
            result.setTotDischaKwh(totResult.getTotDischaKwh());
            result.setTotGenKwh(totResult.getTotGenKwh());
        }
        return result;
    }

    //TODO 이 아래 부분도 중복이 너무 많다. 간략하게 할 수 있는 방법 모색 필요
    
    @Override
    public List<HistoryDevicePcsDataRes> getStatisticsPcsTable(HistoryDeviceCommonDto params) {
        commonService.periodSearch(params);
        PagingParamService.setPagingParam(params);
        return historyStore.getStatisticsPcsTable(params);
    }


    @Override
    public List<HistoryDevicePcsDataRes> getStatisticsPcsChart(HistoryDeviceChartCommonDto params) {
        commonService.periodSearch(params);

        // Chart Tick Create
        List<HistoryDevicePcsDataRes> tickList = new ArrayList<HistoryDevicePcsDataRes>();
        for (String time : commonService.makeChartTicks(params)) {
            HistoryDevicePcsDataRes temp = new HistoryDevicePcsDataRes();
            temp.setTime(time);
            tickList.add(temp);
        }

        // List 병합을 위해 Map 으로 변환
        // Tick, List to Map
        Map<String, HistoryDevicePcsDataRes> tick = tickList.stream().collect(Collectors.toMap(HistoryDevicePcsDataRes::getTime, historyModel -> historyModel));
        // Data, List to Map
        Map<String, HistoryDevicePcsDataRes> data = historyStore.getStatisticsPcsChart(params).stream().collect(Collectors.toMap(HistoryDevicePcsDataRes::getTime, HistoryModel -> HistoryModel));
        // Merge Two Map
        tick.putAll(data);
        // Sort Map By Time (Asceding)
        Map<String, HistoryDevicePcsDataRes> sortedResult =
                tick.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (unsorted, sorted) -> unsorted, LinkedHashMap::new));

        // LinkedHashMap Values to ArrayList
        return new ArrayList<HistoryDevicePcsDataRes>(sortedResult.values());
    }

    @Override
    public List<HistoryDeviceBmsDataRes> getStatisticsBmsTable(HistoryDeviceCommonDto params) {
        commonService.periodSearch(params);
        PagingParamService.setPagingParam(params);
        return historyStore.getStatisticsBmsTable(params);
    }

    @Override
    public List<HistoryDeviceBmsDataRes> getStatisticsBmsChart(HistoryDeviceChartCommonDto params) {
        commonService.periodSearch(params);

        // Chart Tick Create
        List<HistoryDeviceBmsDataRes> tickList = new ArrayList<HistoryDeviceBmsDataRes>();
        for (String time : commonService.makeChartTicks(params)) {
            HistoryDeviceBmsDataRes temp = new HistoryDeviceBmsDataRes();
            temp.setTime(time);
            tickList.add(temp);
        }

        // List 병합을 위해 Map 으로 변환
        // Tick, List to Map
        Map<String, HistoryDeviceBmsDataRes> tick = tickList.stream().collect(Collectors.toMap(HistoryDeviceBmsDataRes::getTime, historyModel -> historyModel));
        // Data, List to Map
        Map<String, HistoryDeviceBmsDataRes> data = historyStore.getStatisticsBmsChart(params).stream().collect(Collectors.toMap(HistoryDeviceBmsDataRes::getTime, HistoryModel -> HistoryModel));
        // Merge Two Map
        tick.putAll(data);
        // Sort Map By Time (Asceding)
        Map<String, HistoryDeviceBmsDataRes> sortedResult =
                tick.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (unsorted, sorted) -> unsorted, LinkedHashMap::new));

        // LinkedHashMap Values to ArrayList
        return new ArrayList<HistoryDeviceBmsDataRes>(sortedResult.values());
    }

    @Override
    public List<HistoryDevicePviDataRes> getStatisticsPviTable(HistoryDeviceCommonDto params) {
        commonService.periodSearch(params);
        PagingParamService.setPagingParam(params);
        return historyStore.getStatisticsPviTable(params);
    }

    @Override
    public List<HistoryDevicePviDataRes> getStatisticsPviChart(HistoryDeviceChartCommonDto params) {
        commonService.periodSearch(params);

        // Chart Tick Create
        List<HistoryDevicePviDataRes> tickList = new ArrayList<HistoryDevicePviDataRes>();
        for (String time : commonService.makeChartTicks(params)) {
            HistoryDevicePviDataRes temp = new HistoryDevicePviDataRes();
            temp.setTime(time);
            tickList.add(temp);
        }

        // List 병합을 위해 Map 으로 변환
        // Tick, List to Map
        Map<String, HistoryDevicePviDataRes> tick = tickList.stream().collect(Collectors.toMap(HistoryDevicePviDataRes::getTime, historyModel -> historyModel));
        // Data, List to Map
        Map<String, HistoryDevicePviDataRes> data = historyStore.getStatisticsPviChart(params).stream().collect(Collectors.toMap(HistoryDevicePviDataRes::getTime, HistoryModel -> HistoryModel));
        // Merge Two Map
        tick.putAll(data);
        // Sort Map By Time (Asceding)
        Map<String, HistoryDevicePviDataRes> sortedResult =
                tick.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (unsorted, sorted) -> unsorted, LinkedHashMap::new));

        // LinkedHashMap Values to ArrayList
        return new ArrayList<HistoryDevicePviDataRes>(sortedResult.values());
    }

    @Override
    public List<HistoryDeviceThsDataRes> getStatisticsThsTable(HistoryDeviceCommonDto params) {
        commonService.periodSearch(params);
        PagingParamService.setPagingParam(params);
        return historyStore.getStatisticsThsTable(params);
    }

    @Override
    public List<HistoryDeviceThsDataRes> getStatisticsThsChart(HistoryDeviceChartCommonDto params) {
        commonService.periodSearch(params);

        // Chart Tick Create
        List<HistoryDeviceThsDataRes> tickList = new ArrayList<HistoryDeviceThsDataRes>();
        for (String time : commonService.makeChartTicks(params)) {
            HistoryDeviceThsDataRes temp = new HistoryDeviceThsDataRes();
            temp.setTime(time);
            tickList.add(temp);
        }

        // List 병합을 위해 Map 으로 변환
        // Tick, List to Map
        Map<String, HistoryDeviceThsDataRes> tick = tickList.stream().collect(Collectors.toMap(HistoryDeviceThsDataRes::getTime, historyModel -> historyModel));
        // Data, List to Map
        Map<String, HistoryDeviceThsDataRes> data = historyStore.getStatisticsThsChart(params).stream().collect(Collectors.toMap(HistoryDeviceThsDataRes::getTime, HistoryModel -> HistoryModel));
        // Merge Two Map
        tick.putAll(data);
        // Sort Map By Time (Asceding)
        Map<String, HistoryDeviceThsDataRes> sortedResult =
                tick.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (unsorted, sorted) -> unsorted, LinkedHashMap::new));

        // LinkedHashMap Values to ArrayList
        return new ArrayList<HistoryDeviceThsDataRes>(sortedResult.values());
    }

    @Override
    public List<HistoryEventSumRes> getEventStatisticsSummary(HistoryEventSumDto params) {
        // 이벤트 최초 호출 시 가장 최근 데이터 10개 return
        if("Y".equals(params.getInitialYn())) {
            return historyStore.getEventLatestSummaryData(params);
        }
        CommonDto dto = new CommonDto();
        dto.setDateFrom(params.getDateFrom());
        dto.setDateTo(params.getDateTo());
        dto.setPeriodType(params.getPeriodType());
        commonService.periodSearch(dto);
        params.setDateFrom(dto.getDateFrom());
        params.setDateTo(dto.getDateTo());
        return historyStore.getEventStatisticsSummary(params);
    }

    @Override
    public List<HistoryEventTableRes> getEventStatisticsTable(HistoryEventTableDto params) {
        List<HistoryEventTableRes> result = new ArrayList<HistoryEventTableRes>();
        // 이벤트 최초 호출 시 가장 최근 데이터 10개 return
        if("Y".equals(params.getInitialYn())) {
            result = historyStore.getEventLatestTableData(params);
            if(result.size() > 0) {
                result.get(0).setDataTotalCount(result.size());
            }
            return result;
        }
        commonService.periodSearch(params);
        PagingParamService.setPagingParam(params);
        result = historyStore.getEventStatisticsTable(params);
        int cnt = historyStore.getEventStatisticsTableCnt(params);
        if(result.size() > 0){
            result.get(0).setDataTotalCount(cnt);
        }
        return result;
    }

    @Override
    public <T> List<T> getDeviceRealtimeData(HistoryDeviceRealtimeDto params) {
        params.setDateFrom(StringUtils.rightPad(params.getDateFrom(), 12, '0'));
        params.setDateTo(StringUtils.rightPad(params.getDateTo(), 12, '0'));
        List<T> resultList = getRealtimeDeviceData(params);
        T temp = (T) new OperationDeviceCommonRes();
        return resultList;
    }

    private <T> List<T> getRealtimeDeviceData(HistoryDeviceRealtimeDto params) {
        List<T> resultList = new ArrayList<T>();
        String grId = params.getDeviceId().substring(0, 3);
        switch (grId) {
            case "100":
                resultList = (List<T>) historyStore.getPcsRealtimeData(params);
                break;
            case "200":
                resultList = (List<T>) historyStore.getBmsRealtimeData(params);
                break;
            case "311":
                resultList = (List<T>) historyStore.getPviRealtimeData(params);
                break;
            case "420":
                resultList = (List<T>) historyStore.getThsRealtimeData(params);
                break;
        }
        return resultList;
    }
}
