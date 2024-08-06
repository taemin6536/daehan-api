package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "ManagementUserIdCheckDto")
public class ManagementUserIdCheckDto {
    @Schema(required = true, description = "생성아이디", example="testnew", maxLength=20)
    @NotBlank
    private String objectUserId;

    public String getObjectUserId() {
        return objectUserId;
    }

    public void setObjectUserId(String objectUserId) {
        this.objectUserId = objectUserId;
    }
}