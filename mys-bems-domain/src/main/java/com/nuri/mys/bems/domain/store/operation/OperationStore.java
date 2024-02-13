package com.nuri.mys.bems.domain.store.operation;

import com.nuri.mys.bems.domain.entity.common.CommonDto;
import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import com.nuri.mys.bems.domain.entity.operation.*;
import com.nuri.mys.bems.domain.entity.operation.*;

import java.util.List;

public interface OperationStore {
    OperationSummaryRes getSolarGenSummary();

    OperationSummaryRes getEssSummary();

    List<OperationChartRes> getOperationChart(CommonDto params);

    List<OperationDevicePcsDataRes> getPcsStatusChart(OperationDeviceCommonDto params);

    List<OperationDeviceBmsDataRes> getBmsStatusChart(OperationDeviceCommonDto params);

    List<OperationDevicePviDataRes> getPviStatusChart(OperationDeviceCommonDto params);

    List<OperationDeviceThsDataRes> getThsStatusChart(OperationDeviceCommonDto params);

    List<OperationEventSummaryRes> getEventStatusSummary();

    List<OperationEventTableRes> getEventStatusTable(OperationEventTableDto params);

    int getEventStatusTableCnt(OperationEventTableDto params);

    List<OperationTableRes> getOperationTable(CommonPageDto params);

    int getOperationTableCount(CommonPageDto params);

    List<OperationDevicePvsDataRes> getPvsStatusChart(OperationDeviceCommonDto params);
}
