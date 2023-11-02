package com.nuri.mys.bems.domain.entity.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DashboardEssRunDataDto")
public class DashboardEssRunDataDto {

    @Schema(description = "bms 용량")
    private Double bmsCapa;
    @Schema(description = "MAX SOC")
    private Double maxSoc;
    @Schema(description = "MIN SOC")
    private Double minSoc;

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
}
