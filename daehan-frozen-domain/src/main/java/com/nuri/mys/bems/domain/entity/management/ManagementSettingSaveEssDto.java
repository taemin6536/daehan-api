package com.nuri.mys.bems.domain.entity.management;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Schema(description = "ManagementSettingSaveEssDto")
public class ManagementSettingSaveEssDto {

    @Min(0)
    @Schema(required = true, description = "최소 SOC", example="2")
    private double minSoc;
    @Max(100)
    @Schema(required = true, description = "최대 SOC", example="80")
    private double maxSoc;
    @Schema(required = true, description = "출력제한 값", example="300", maxLength = 22)
    private double chadiPowerLimit;
    @Schema(description = "목표 전력")
    private double targetPower;
    @Max(100)
    @Schema(description = "목표 전력 비율", example="10")
    private double targetPowerRate;
    @Schema(description = "운전 모드(1:auto/2:manual/3:schedule)", example="2", maxLength=2)
    private String sysOPMode;
    @Schema(description = "운전 모드(1:auto/2:manual/3:schedule)", example="2", maxLength=2)
    private String sysOPModeNm;
    @Max(100)
    @Schema(description = "발전 추종률", example="100")
    private double pvFollowRate;
    @Schema(description = "사용자 아이디", hidden = true)
    private String userId;
    @Schema(description = "사업장 아이디", hidden = true)
    private String siteId;

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

    public String getSysOPModeNm() {
        return sysOPModeNm;
    }

    public void setSysOPModeNm(String sysOPModeNm) {
        this.sysOPModeNm = sysOPModeNm;
    }

    public double getPvFollowRate() {
        return pvFollowRate;
    }

    public void setPvFollowRate(double pvFollowRate) {
        this.pvFollowRate = pvFollowRate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

}
