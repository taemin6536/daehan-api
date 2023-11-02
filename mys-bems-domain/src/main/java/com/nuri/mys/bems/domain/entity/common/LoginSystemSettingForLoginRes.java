package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "LoginSystemSettingForLoginRes")
public class LoginSystemSettingForLoginRes {
    @Schema(required = false, description = "사이트ID")
    private String siteId;
    @Schema(required = false, description = "사이트명")
    private String siteNm;
    @Schema(required = false, description = "대분류")
    private String vproperType;
    @Schema(required = false, description = "중분류")
    private String vkey;
    @Schema(required = false, description = "데이터")
    private String vvalue;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteNm() {
        return siteNm;
    }

    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    public String getVproperType() {
        return vproperType;
    }

    public void setVproperType(String vproperType) {
        this.vproperType = vproperType;
    }

    public String getVkey() {
        return vkey;
    }

    public void setVkey(String vkey) {
        this.vkey = vkey;
    }

    public String getVvalue() {
        return vvalue;
    }

    public void setVvalue(String vvalue) {
        this.vvalue = vvalue;
    }
}
