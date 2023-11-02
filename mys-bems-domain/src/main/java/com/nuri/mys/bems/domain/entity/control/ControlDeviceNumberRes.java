package com.nuri.mys.bems.domain.entity.control;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="ControlDeviceNumberRes")
public class ControlDeviceNumberRes {
    @Schema(description = "코드명")
    private String cdNm;
    @Schema(description = "pcs 장비아이디")
    private String pcsDeviceId;
    @Schema(description = "bms 장비아이디")
    private String bmsDeviceId;
    @Schema(description = "pcs 장비명")
    private String pcsDeviceNm;
    @Schema(description = "bms 장비명")
    private String bmsDeviceNm;

    public String getCdNm() {
        return cdNm;
    }

    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    public String getPcsDeviceId() {
        return pcsDeviceId;
    }

    public void setPcsDeviceId(String pcsDeviceId) {
        this.pcsDeviceId = pcsDeviceId;
    }

    public String getBmsDeviceId() {
        return bmsDeviceId;
    }

    public void setBmsDeviceId(String bmsDeviceId) {
        this.bmsDeviceId = bmsDeviceId;
    }

    public String getPcsDeviceNm() {
        return pcsDeviceNm;
    }

    public void setPcsDeviceNm(String pcsDeviceNm) {
        this.pcsDeviceNm = pcsDeviceNm;
    }

    public String getBmsDeviceNm() {
        return bmsDeviceNm;
    }

    public void setBmsDeviceNm(String bmsDeviceNm) {
        this.bmsDeviceNm = bmsDeviceNm;
    }
}
