package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementSchCommonDto")
public class ManagementSchCommonDto {
    @Schema(required = true, description = "사업장 아이디")
    private String siteId;
    @Schema(required = true, description = "장비 아이디")
    private String deviceId;
    @Schema(required = true, description = "사용자 아이디")
    private String userId;
    @Schema(required = false, description = "계절 선택")
    private String seasonCd;
    @Schema(required = false, description = "요일 코드", hidden = true)
    private String weekCd;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSeasonCd() {
        return seasonCd;
    }

    public void setSeasonCd(String seasonCd) {
        this.seasonCd = seasonCd;
    }

    public String getWeekCd() {
        return weekCd;
    }

    public void setWeekCd(String weekCd) {
        this.weekCd = weekCd;
    }
}
