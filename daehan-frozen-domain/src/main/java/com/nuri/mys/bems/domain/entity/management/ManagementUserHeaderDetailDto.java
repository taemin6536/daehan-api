package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementUserHeaderDetailDto")
public class ManagementUserHeaderDetailDto {
    @Schema(required = true, description = "사용자 ID", example="165", maxLength = 22, hidden = true)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
