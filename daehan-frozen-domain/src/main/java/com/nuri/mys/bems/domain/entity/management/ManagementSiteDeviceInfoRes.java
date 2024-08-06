package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementSiteDeviceInfoRes")
public class ManagementSiteDeviceInfoRes {
    @Schema(description = "사업장 아이디")
    private String siteId;
    @Schema(description = "사업장명")
    private String siteNm;
    @Schema(description = "장비그룹명")
    private String grNm;
    @Schema(description = "장비 아이디")
    private String deviceId;
    @Schema(description = "장비명")
    private String deviceNm;
    @Schema(description = "용량")
    private double capa;

    @Schema(description = "DataGrid Data Total Count")
    private int dataTotalCount;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteNm() {
        return siteNm;
    }

    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    public String getGrNm() {
        return grNm;
    }

    public void setGrNm(String grNm) {
        this.grNm = grNm;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNm() {
        return deviceNm;
    }

    public void setDeviceNm(String deviceNm) {
        this.deviceNm = deviceNm;
    }

    public double getCapa() {
        return capa;
    }

    public void setCapa(double capa) {
        this.capa = capa;
    }

    public int getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(int dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }
}
