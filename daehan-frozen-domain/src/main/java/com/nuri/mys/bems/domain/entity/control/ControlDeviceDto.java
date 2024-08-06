package com.nuri.mys.bems.domain.entity.control;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "ControlDeviceDto")
public class ControlDeviceDto {
    @NotBlank
    @Schema(required = true, description = "사이트 코드", example = "1050022010")
    private String siteId;
    @Schema(required = false, description = "장비 타입", example = "700")
    private String grId;
    @Schema(required = false, description = "장비 그룹명", example = "Hydropower", hidden=true)
    private String grNm;
    @Schema(required = false, description = "로그 설명", hidden = true)
    private String description;
    @Schema(required = true, description = "사용자 아이디", hidden = true)
    private String userId;
    @NotBlank
    @Schema(required = true, description = "명령 구분(controlStatus/changeGrid/controlChadi/controlPower)", example = "controlMode")
    private String classification;
    @Schema(required = true, description = "제어 상세 정보")
    private ControlDeviceDetailDto command;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getGrId() {
        return grId;
    }

    public void setGrId(String grId) {
        this.grId = grId;
    }

    public String getGrNm() {
        return grNm;
    }

    public void setGrNm(String grNm) {
        this.grNm = grNm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public ControlDeviceDetailDto getCommand() {
        return command;
    }

    public void setCommand(ControlDeviceDetailDto command) {
        this.command = command;
    }
}
