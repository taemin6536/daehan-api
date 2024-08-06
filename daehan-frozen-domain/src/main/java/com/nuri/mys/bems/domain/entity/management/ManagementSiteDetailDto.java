package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "ManagementSiteDeviceInfoDto")
public class ManagementSiteDetailDto {
    @Schema(required = true, description = "사업장 아이디", example="3040023001", maxLength=10)
    @NotBlank
    private String siteId;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}