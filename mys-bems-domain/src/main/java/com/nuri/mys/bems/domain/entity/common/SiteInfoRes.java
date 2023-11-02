package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "SiteInfoRes")
public class SiteInfoRes {
    @Schema(required = false, description = "사업장ID")
    private String vSiteId;
    @Schema(required = false, description = "사업장명")
    private String vSiteNm;
    @Schema(required = false, description = "화면표출여부")
    private String vViewYn;
    @Schema(required = false, description = "제어표출여부")
    private String vOperYn;
    @Schema(required = false, description = "관리표출여부")
    private String vMngYn;
    @Schema(required = false, description = "에너지 타입")
    private String vEnergyType;
    @Schema(required = false, description = "에너지 명")
    private String vEnergyNm;

    public String getvSiteId() {
        return vSiteId;
    }

    public void setvSiteId(String vSiteId) {
        this.vSiteId = vSiteId;
    }

    public String getvSiteNm() {
        return vSiteNm;
    }

    public void setvSiteNm(String vSiteNm) {
        this.vSiteNm = vSiteNm;
    }

    public String getvViewYn() {
        return vViewYn;
    }

    public void setvViewYn(String vViewYn) {
        this.vViewYn = vViewYn;
    }

    public String getvOperYn() {
        return vOperYn;
    }

    public void setvOperYn(String vOperYn) {
        this.vOperYn = vOperYn;
    }

    public String getvMngYn() {
        return vMngYn;
    }

    public void setvMngYn(String vMngYn) {
        this.vMngYn = vMngYn;
    }

    public String getvEnergyType() {
        return vEnergyType;
    }

    public void setvEnergyType(String vEnergyType) {
        this.vEnergyType = vEnergyType;
    }

    public String getvEnergyNm() {
        return vEnergyNm;
    }

    public void setvEnergyNm(String vEnergyNm) {
        this.vEnergyNm = vEnergyNm;
    }
}
