package com.nuri.mys.bems.domain.entity.operation;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OperationDeviceCommonRes")
public class OperationDeviceCommonRes {
    @Schema(required = true, description = "시간")
    private String time;
    @Schema(required = true, description = "장비 ID")
    private String deviceId;
    @Schema(description = "장비명")
    private String deviceNm;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
