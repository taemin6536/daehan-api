package com.nuri.mys.bems.domain.entity.history;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryOperationChartRes")
public class HistoryOperationChartRes {
	@Schema(required = true, description = "시간")
    private String time;
	@Schema(description = "기간별 태양광 발전량")
    private Double periodGenKwh;
	@Schema(description = "기간별 충전량")
    private Double periodChargeKwh;
    @Schema(description = "기간별 방전량")
    private Double periodDischaKwh;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPeriodGenKwh() {
        return periodGenKwh;
    }

    public void setPeriodGenKwh(Double periodGenKwh) {
        this.periodGenKwh = periodGenKwh;
    }

    public Double getPeriodChargeKwh() {
        return periodChargeKwh;
    }

    public void setPeriodChargeKwh(Double periodChargeKwh) {
        this.periodChargeKwh = periodChargeKwh;
    }

    public Double getPeriodDischaKwh() {
        return periodDischaKwh;
    }

    public void setPeriodDischaKwh(Double periodDischaKwh) {
        this.periodDischaKwh = periodDischaKwh;
    }

}
