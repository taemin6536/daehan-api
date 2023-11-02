package com.nuri.mys.bems.domain.entity.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DashboardEssChadiDataRes")
public class DashboardEssChadiDataRes {
    @Schema(description = "금일 충전량")
    private Double todayChargeKwh;
    @Schema(description = "금일 방전량")
    private Double todayDischargeKwh;
    @Schema(description = "전일 충전량")
    private Double yesterdayChargeKwh;
    @Schema(description = "전일 방전량")
    private Double yesterdayDischargeKwh;

    public Double getTodayChargeKwh() {
        return todayChargeKwh;
    }

    public void setTodayChargeKwh(Double todayChargeKwh) {
        this.todayChargeKwh = todayChargeKwh;
    }

    public Double getTodayDischargeKwh() {
        return todayDischargeKwh;
    }

    public void setTodayDischargeKwh(Double todayDischargeKwh) {
        this.todayDischargeKwh = todayDischargeKwh;
    }

    public Double getYesterdayChargeKwh() {
        return yesterdayChargeKwh;
    }

    public void setYesterdayChargeKwh(Double yesterdayChargeKwh) {
        this.yesterdayChargeKwh = yesterdayChargeKwh;
    }

    public Double getYesterdayDischargeKwh() {
        return yesterdayDischargeKwh;
    }

    public void setYesterdayDischargeKwh(Double yesterdayDischargeKwh) {
        this.yesterdayDischargeKwh = yesterdayDischargeKwh;
    }
}
