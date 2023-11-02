package com.nuri.mys.bems.domain.entity.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DashboardPmsSettingDataRes")
public class DashboardPmsSettingDataRes {
    @Schema(description = "PCS 용량")
    private Double pcsCapa;
    @Schema(description = "태양광 용량")
    private Double pviCapa;
    @Schema(description = "배터리 용량")
    private Double bmsCapa;
    @Schema(description = "MAX SOC")
    private Double maxSoc;
    @Schema(description = "MIN SOC")
    private Double minSoc;
    @Schema(description = "발전추종률")
    private double pvFollowRate;
    @Schema(description = "인버터 수량")
    private int pviCnt;

    public Double getPcsCapa() {
        return pcsCapa;
    }

    public void setPcsCapa(Double pcsCapa) {
        this.pcsCapa = pcsCapa;
    }

    public Double getPviCapa() {
        return pviCapa;
    }

    public void setPviCapa(Double pviCapa) {
        this.pviCapa = pviCapa;
    }

    public Double getBmsCapa() {
        return bmsCapa;
    }

    public void setBmsCapa(Double bmsCapa) {
        this.bmsCapa = bmsCapa;
    }

    public Double getMaxSoc() {
        return maxSoc;
    }

    public void setMaxSoc(Double maxSoc) {
        this.maxSoc = maxSoc;
    }

    public Double getMinSoc() {
        return minSoc;
    }

    public void setMinSoc(Double minSoc) {
        this.minSoc = minSoc;
    }

    public double getPvFollowRate() {
        return pvFollowRate;
    }

    public void setPvFollowRate(double pvFollowRate) {
        this.pvFollowRate = pvFollowRate;
    }

    public int getPviCnt() {
        return pviCnt;
    }

    public void setPviCnt(int pviCnt) {
        this.pviCnt = pviCnt;
    }
}
