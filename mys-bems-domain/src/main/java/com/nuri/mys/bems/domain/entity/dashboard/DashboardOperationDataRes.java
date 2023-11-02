package com.nuri.mys.bems.domain.entity.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DashboardOperationDataRes")
public class DashboardOperationDataRes {
    @Schema(description = "금월 발전량")
    private Double monlyGenKwh;
    @Schema(description = "금일 발전량")
    private Double dailyGenKwh;
    @Schema(description = "금월 충전량")
    private Double monlyChargeKwh;
    @Schema(description = "금월 방전량")
    private Double monlyDischaKwh;
    @Schema(description = "금일 충전량")
    private Double dailyChargeKwh;
    @Schema(description = "금일 방전량")
    private Double dailyDischaKwh;

    public Double getMonlyGenKwh() {
        return monlyGenKwh;
    }

    public void setMonlyGenKwh(Double monlyGenKwh) {
        this.monlyGenKwh = monlyGenKwh;
    }

    public Double getDailyGenKwh() {
        return dailyGenKwh;
    }

    public void setDailyGenKwh(Double dailyGenKwh) {
        this.dailyGenKwh = dailyGenKwh;
    }

    public Double getMonlyChargeKwh() {
        return monlyChargeKwh;
    }

    public void setMonlyChargeKwh(Double monlyChargeKwh) {
        this.monlyChargeKwh = monlyChargeKwh;
    }

    public Double getMonlyDischaKwh() {
        return monlyDischaKwh;
    }

    public void setMonlyDischaKwh(Double monlyDischaKwh) {
        this.monlyDischaKwh = monlyDischaKwh;
    }

    public Double getDailyChargeKwh() {
        return dailyChargeKwh;
    }

    public void setDailyChargeKwh(Double dailyChargeKwh) {
        this.dailyChargeKwh = dailyChargeKwh;
    }

    public Double getDailyDischaKwh() {
        return dailyDischaKwh;
    }

    public void setDailyDischaKwh(Double dailyDischaKwh) {
        this.dailyDischaKwh = dailyDischaKwh;
    }
}
