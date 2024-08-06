package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CommonDeviceRes")
public class CommonDeviceDetailRes {
    @Schema(description = "장비 아이디")
    private String deviceId;
    @Schema(description = "장비 명")
    private String deviceNm;

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
}
