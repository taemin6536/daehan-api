package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementUserSystemSettingRes")
public class ManagementUserSystemSettingRes {
    @Schema(required = false, description = "속성")
    private String keyNm;
    @Schema(required = false, description = "Y/N")
    private String value;

    public String getKeyNm() {
        return keyNm;
    }

    public void setKeyNm(String keyNm) {
        this.keyNm = keyNm;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
