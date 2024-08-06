package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author jmlee
 */
public class ManagementHolidayCommonDto {
    @Schema(required = true, description = "사업장 아이디")
    private String siteId;
    @Schema(required = true, description = "장비 아이디")
    private String deviceId;
    @Schema(required = true, description = "사용자 아이디", hidden = true)
    private String userId;
    @Schema(description="제어 시 넘길 날짜", hidden=true)
    private String day;
    @Schema(description="제어 시 넘길 요일", hidden=true)
    private String holidayCd;

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHolidayCd() {
        return holidayCd;
    }

    public void setHolidayCd(String holidayCd) {
        this.holidayCd = holidayCd;
    }
}
