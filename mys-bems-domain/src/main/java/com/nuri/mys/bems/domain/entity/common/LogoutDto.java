package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "LogoutDto")
public class LogoutDto {
    @NotBlank
    @Schema(required = true, description = "로그인 사용자 ID", example = "admin", maxLength = 20)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
