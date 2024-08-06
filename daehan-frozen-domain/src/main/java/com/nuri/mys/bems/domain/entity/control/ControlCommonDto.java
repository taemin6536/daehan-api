package com.nuri.mys.bems.domain.entity.control;

import io.swagger.v3.oas.annotations.media.Schema;
import org.json.simple.JSONObject;

import javax.validation.constraints.NotBlank;

@Schema(description = "ControlCommonDto")
public class ControlCommonDto {
    @Schema(required = false, description = "시퀀스", example="0", hidden = true)
    private String seq;
    @NotBlank
    @Schema(required = true, description = "사이트 코드", example = "1050022010")
    private String siteId;
    @Schema(required = false, description = "로그 설명", hidden = true)
    private String description;
    @Schema(description = "사용자 아이디", hidden = true)
    private String userId;
    @Schema(description = "제어 상세 정보", hidden=true)
    private JSONObject command;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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

    public JSONObject getCommand() {
        return command;
    }

    public void setCommand(JSONObject command) {
        this.command = command;
    }
}
