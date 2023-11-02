package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "ManagementSiteDeviceInfoDto")
public class ManagementSiteDeviceDetailDto extends ManagementSiteDeviceInfoDto {
    @Schema(required = true, description = "장비 ID, 7자리 숫자로 제한한다.")
    @NotBlank
    @Pattern(regexp="^(\\d{7})")
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}