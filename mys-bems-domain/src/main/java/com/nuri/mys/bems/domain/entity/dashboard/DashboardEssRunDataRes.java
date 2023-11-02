package com.nuri.mys.bems.domain.entity.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DashboardEssChadiDataRes")
public class DashboardEssRunDataRes {
    @Schema(description = "금일 충전 운용률")
    private Double todayChargeRate;
    @Schema(description = "금일 방전 운용률")
    private Double todayDischargeRate;
    @Schema(description = "전일 충전 운용률")
    private Double yesterdayChargeRate;
    @Schema(description = "전일 방전 운용률")
    private Double yesterdayDischargeRate;

    public Double getTodayChargeRate() {
        return todayChargeRate;
    }

    public void setTodayChargeRate(Double todayChargeRate) {
        this.todayChargeRate = todayChargeRate;
    }

    public Double getTodayDischargeRate() {
        return todayDischargeRate;
    }

    public void setTodayDischargeRate(Double todayDischargeRate) {
        this.todayDischargeRate = todayDischargeRate;
    }

    public Double getYesterdayChargeRate() {
        return yesterdayChargeRate;
    }

    public void setYesterdayChargeRate(Double yesterdayChargeRate) {
        this.yesterdayChargeRate = yesterdayChargeRate;
    }

    public Double getYesterdayDischargeRate() {
        return yesterdayDischargeRate;
    }

    public void setYesterdayDischargeRate(Double yesterdayDischargeRate) {
        this.yesterdayDischargeRate = yesterdayDischargeRate;
    }
}
