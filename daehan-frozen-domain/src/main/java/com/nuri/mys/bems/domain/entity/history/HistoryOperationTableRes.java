package com.nuri.mys.bems.domain.entity.history;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryOperationTableRes")
public class HistoryOperationTableRes {
	@Schema(required = true, description = "시간")
    private String time;
	@Schema(description = "기간별 태양광 발전량")
    private Double periodGenKwh;
	@Schema(description = "기간별 충전량")
    private Double periodChargeKwh;
    @Schema(description = "기간별 방전량")
    private Double periodDischaKwh;
    @Schema(description = "누적 태양광 발전량")
    private Double totGenKwh;
    @Schema(description = "누적 충전량")
    private Double totChargeKwh;
    @Schema(description = "누적 방전량")
    private Double totDischaKwh;
    @Schema(description = "DataGrid Data Total Count")
    private int dataTotalCount;

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

    public Double getTotGenKwh() {
        return totGenKwh;
    }

    public void setTotGenKwh(Double totGenKwh) {
        this.totGenKwh = totGenKwh;
    }

    public Double getTotChargeKwh() {
        return totChargeKwh;
    }

    public void setTotChargeKwh(Double totChargeKwh) {
        this.totChargeKwh = totChargeKwh;
    }

    public Double getTotDischaKwh() {
        return totDischaKwh;
    }

    public void setTotDischaKwh(Double totDischaKwh) {
        this.totDischaKwh = totDischaKwh;
    }

    public int getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(int dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }
}
