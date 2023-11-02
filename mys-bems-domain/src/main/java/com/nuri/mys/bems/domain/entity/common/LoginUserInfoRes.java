package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "LoginUserInfoRes")
public class LoginUserInfoRes {
    @Schema(required = false, description = "로그인 사용자 ID", example = "admin")
    private String userId;
    @Schema(required = false, description = "권한명")
    private String role;
    @Schema(required = false, description = "권한", hidden = true)
    private String permId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }
}
