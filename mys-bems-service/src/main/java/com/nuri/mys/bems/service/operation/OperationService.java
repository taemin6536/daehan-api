package com.nuri.mys.bems.service.operation;

import com.nuri.mys.bems.domain.entity.common.CommonDto;
import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import com.nuri.mys.bems.domain.entity.operation.*;
import com.nuri.mys.bems.domain.logic.common.CommonLogic;
import com.nuri.mys.bems.domain.logic.operation.OperationLogic;
import com.nuri.mys.bems.domain.store.operation.OperationStore;
import com.nuri.mys.bems.domain.entity.operation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OperationService implements OperationLogic {
    @Autowired
    private OperationStore operationStore;

    @Autowired
    private CommonLogic commonService;

    @Override
    public OperationSummaryRes getOperationSummary() {
        OperationSummaryRes result1 = operationStore.getSolarGenSummary(); // 태양광
        OperationSummaryRes essResult = operationStore.getEssSummary();

        OperationSummaryRes result = essResult != null ? essResult : new OperationSummaryRes();

        if(result1 != null) {
            result.setPeriodGenKwh(result1.getPeriodGenKwh());
            result.setTotGenKwh(result1.getTotGenKwh());
        }

        return result;
    }

    @Override
    public List<OperationChartRes> getOperationChart() {
        CommonDto params = new CommonDto();

        // Chart Tick Create Parameter Setting
        params.setPeriodType("hour");
        params.setDateFrom(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).format(DateTimeFormatter.ofPattern(commonService.hourlyFormat)));
        params.setDateTo(LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1)).format(DateTimeFormatter.ofPattern(commonService.hourlyFormat)));

        // Chart Tick Create
        List<OperationChartRes> tickList = new ArrayList<OperationChartRes>();
        for (String time : commonService.makeChartTicks(params)) {
            OperationChartRes temp = new OperationChartRes();
            temp.setTime(time);
            tickList.add(temp);
        }

        // List 병합을 위해 Map 으로 변환
        // Tick, List to Map
        Map<String, OperationChartRes> tick = tickList.stream().collect(Collectors.toMap(OperationChartRes::getTime, pmsModel -> pmsModel));
        // Data, List to Map
        Map<String, OperationChartRes> data = operationStore.getOperationChart(params).stream().collect(Collectors.toMap(OperationChartRes::getTime, PmsModel -> PmsModel));
        // Merge Two Map
        tick.putAll(data);
        // Sort Map By Time (Asceding)
        Map<String, OperationChartRes> sortedResult =
                tick.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (unsorted, sorted) -> unsorted, LinkedHashMap::new));

        // LinkedHashMap Values to ArrayList
        return new ArrayList<OperationChartRes>(sortedResult.values());
    }

    @Override
    public List<OperationEventSummaryRes> getEventStatusSummary() {
        return operationStore.getEventStatusSummary();
    }

    @Override
    public List<OperationEventTableRes> getEventStatusTable(OperationEventTableDto params) {
        List<OperationEventTableRes> resultList = operationStore.getEventStatusTable(params);
        if(resultList.size() > 0) {
            int cnt = operationStore.getEventStatusTableCnt(params);
            resultList.get(0).setDataTotalCount(cnt);
        }
        return resultList;
    }

    @Override
    public List<OperationTableRes> getOperationTable(CommonPageDto params) {
        List<OperationTableRes> resultList = operationStore.getOperationTable(params);
        if(resultList.size() > 0) {
            int cnt = operationStore.getOperationTableCount(params);
            resultList.get(0).setDataTotalCount(cnt);
        }
        return resultList;
    }

    @Override
    public <T extends OperationDeviceCommonRes> List<T> getDeviceStatusChart(OperationDeviceCommonDto params, HttpServletRequest request) {
        List<T> resultList = getOperationDeviceData(params, request);
        T temp = (T) new OperationDeviceCommonRes();
        while(resultList.size() < 90) {
            resultList.add(temp);
        }
        return resultList;
    }

    @Override
    public <T extends OperationDeviceCommonRes> List<T> getDeviceStatusTable(OperationDeviceCommonDto params, HttpServletRequest request) {
        List<T> resultList = getOperationDeviceData(params, request);
        return resultList;
    }

    private <T extends OperationDeviceCommonRes> List<T> getOperationDeviceData(OperationDeviceCommonDto params, HttpServletRequest request) {
        List<T> resultList = new ArrayList<T>();
        String[] array = request.getRequestURI().split("/");
        String path = array[array.length - 1];
        String device = path.split("(?=(\\p{Upper}))")[0].toLowerCase();

        switch (device) {
            case "pcs":
                resultList = (List<T>) operationStore.getPcsStatusChart(params);
                break;
            case "bms":
                resultList = (List<T>) operationStore.getBmsStatusChart(params);
                break;
            case "pvi":
                resultList = (List<T>) operationStore.getPviStatusChart(params);
                break;
            case "ths":
                resultList = (List<T>) operationStore.getThsStatusChart(params);
                break;
        }
        return resultList;
    }
}
