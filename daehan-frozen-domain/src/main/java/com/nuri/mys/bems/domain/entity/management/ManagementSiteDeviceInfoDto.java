package com.nuri.mys.bems.domain.entity.management;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "ManagementSiteDeviceInfoDto")
public class ManagementSiteDeviceInfoDto extends CommonPageDto {
    @Schema(required = true, description = "사업장 아이디", example="3040023001", maxLength=10)
    @NotBlank
    private String siteId;
    @Schema(description = "장비그룹ID", example="311")
    private String grId;

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
}