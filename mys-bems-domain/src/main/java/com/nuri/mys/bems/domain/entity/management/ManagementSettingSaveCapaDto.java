package com.nuri.mys.bems.domain.entity.management;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "ManagementSettingSaveCapaDto")
public class ManagementSettingSaveCapaDto {
    @Schema(required = true, description = "장비그룹 ID, 3자리 숫자로 제한한다.", example = "311", hidden = true)
    private String grId;
    @Schema(required = true, description = "장비 ID, 7자리 숫자로 제한한다.", example = "3110001")
    @NotBlank
    @Pattern(regexp="^(\\d{7})")
    private String deviceId;
    @Schema(required = true, description = "장비 용량", example = "100")
    private double capa;
    @Schema(description = "사용자 아이디", hidden = true)
    private String userId;

    public String getGrId() {
        return grId;
    }

    public void setGrId(String grId) {
        this.grId = grId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public double getCapa() {
        return capa;
    }

    public void setCapa(double capa) {
        this.capa = capa;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
