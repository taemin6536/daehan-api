package com.nuri.mys.bems.domain.entity.control;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "ControlDeviceDetailDto")
public class ControlDeviceDetailDto {
    @Schema(required = false, description = "시퀀스", hidden = true)
    private String seq;
    @Schema(required = false, description = "장비 ID", example = "7000001")
    private String devId;
    @Schema(required = false, description = "장비 명", example = "Hydropower-1", hidden=true)
    private String devNm;
    @NotBlank
    @Schema(required = true, description = "제어명")
    private String name;
    @Schema(required = false, description = "pcs 용량 등 amount")
    private Double param;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevNm() {
        return devNm;
    }

    public void setDevNm(String devNm) {
        this.devNm = devNm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getParam() {
        return param;
    }

    public void setParam(Double param) {
        this.param = param;
    }
}
