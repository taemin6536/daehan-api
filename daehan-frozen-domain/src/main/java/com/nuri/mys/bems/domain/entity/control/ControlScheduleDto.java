package com.nuri.mys.bems.domain.entity.control;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ControlScheduleDto")
public class ControlScheduleDto extends ControlCommonDto {
    @Schema(name="장비 아이디", hidden = true)
    private String devId;
    @Schema(name="장비 명", hidden = true)
    private String devNm;

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
}
