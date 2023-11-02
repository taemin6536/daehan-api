package com.nuri.mys.bems.domain.logic.operation;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import com.nuri.mys.bems.domain.entity.operation.*;
import com.nuri.mys.bems.domain.entity.operation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OperationLogic {
    OperationSummaryRes getOperationSummary();

    List<OperationChartRes> getOperationChart();

    List<OperationEventSummaryRes> getEventStatusSummary();

    List<OperationEventTableRes> getEventStatusTable(OperationEventTableDto params);

    List<OperationTableRes> getOperationTable(CommonPageDto params);

    <T extends OperationDeviceCommonRes> List<T> getDeviceStatusChart(OperationDeviceCommonDto params, HttpServletRequest request);

    <T extends OperationDeviceCommonRes> List<T> getDeviceStatusTable(OperationDeviceCommonDto params, HttpServletRequest request);
}
