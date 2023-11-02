package com.nuri.mys.bems.domain.entity.control;

import com.nuri.mys.bems.domain.entity.common.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "ControlTableDataDto")
public class ControlTableDataDto extends CommonDto {
    @Schema(required = false, description = "장비 그룹 아이디", example="100")
    private String grId;
    @Schema(required = false, description = "장비 ID", example="2100001")
    private String deviceId;
    @NotBlank
    @Schema(required = true, description = "제어/설정 구분", example="control")
    private String classificationCd;

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

    public String getClassificationCd() {
        return classificationCd;
    }

    public void setClassificationCd(String classificationCd) {
        this.classificationCd = classificationCd;
    }
}
