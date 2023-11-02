package com.nuri.mys.bems.domain.entity.management;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "ManagementSettingCapaInfoRes")
public class ManagementSettingCapaInfoRes {
    @Schema(description = "solar 장비 목록")
    private List<ManagementCommonCapaInfoRes> solarInfo;
    @Schema(description = "pcs 장비 목록")
    private List<ManagementCommonCapaInfoRes> pcsInfo;
    @Schema(description = "bms 장비 목록")
    private List<ManagementCommonCapaInfoRes> bmsInfo;

    public List<ManagementCommonCapaInfoRes> getSolarInfo() {
        return solarInfo;
    }

    public void setSolarInfo(List<ManagementCommonCapaInfoRes> solarInfo) {
        this.solarInfo = solarInfo;
    }

    public List<ManagementCommonCapaInfoRes> getPcsInfo() {
        return pcsInfo;
    }

    public void setPcsInfo(List<ManagementCommonCapaInfoRes> pcsInfo) {
        this.pcsInfo = pcsInfo;
    }

    public List<ManagementCommonCapaInfoRes> getBmsInfo() {
        return bmsInfo;
    }

    public void setBmsInfo(List<ManagementCommonCapaInfoRes> bmsInfo) {
        this.bmsInfo = bmsInfo;
    }
}
