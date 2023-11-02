package com.nuri.mys.bems.domain.entity.management;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementSettingEssInfoRes")
public class ManagementSettingEssInfoRes {

    @Schema(required = true, description = "최소 SOC", example="2")
    private double minSoc;
    @Schema(required = true, description = "최대 SOC", example="80")
    private double maxSoc;
    @Schema(required = true, description = "출력제한 값", example="300", maxLength = 22)
    private double chadiPowerLimit;
    @Schema(description = "목표 전력")
    private double targetPower;
    @Schema(description = "목표 전력 비율")
    private double targetPowerRate;
    @Schema(description = "운전 모드")
    private String sysOPMode;
    @Schema(description = "발전 추종률")
    private double pvFollowRate;

    public double getMinSoc() {
        return minSoc;
    }

    public void setMinSoc(double minSoc) {
        this.minSoc = minSoc;
    }

    public double getMaxSoc() {
        return maxSoc;
    }

    public void setMaxSoc(double maxSoc) {
        this.maxSoc = maxSoc;
    }

    public double getChadiPowerLimit() {
        return chadiPowerLimit;
    }

    public void setChadiPowerLimit(double chadiPowerLimit) {
        this.chadiPowerLimit = chadiPowerLimit;
    }

    public double getTargetPower() {
        return targetPower;
    }

    public void setTargetPower(double targetPower) {
        this.targetPower = targetPower;
    }

    public double getTargetPowerRate() {
        return targetPowerRate;
    }

    public void setTargetPowerRate(double targetPowerRate) {
        this.targetPowerRate = targetPowerRate;
    }

    public String getSysOPMode() {
        return sysOPMode;
    }

    public void setSysOPMode(String sysOPMode) {
        this.sysOPMode = sysOPMode;
    }

    public double getPvFollowRate() {
        return pvFollowRate;
    }

    public void setPvFollowRate(double pvFollowRate) {
        this.pvFollowRate = pvFollowRate;
    }
}
