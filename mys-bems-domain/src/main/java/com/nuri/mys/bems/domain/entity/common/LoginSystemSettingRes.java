package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "LoginSystemSettingRes")
public class LoginSystemSettingRes {
    @Schema(required = false, description = "사이트ID")
    private String siteId;
    @Schema(required = false, description = "사이트명")
    private String siteNm;
    @Schema(required = false, description = "로그인 실패 횟수 사용 여부")
    private String useLoginFailCntYN;
    @Schema(required = false, description = "로그인 실패 제한 횟수")
    private String loginFailCntLimit;
    @Schema(required = false, description = "로그인 시 SMS 사용 여부")
    private String useLoginSMSYN;
    @Schema(required = false, description = "시스템명")
    private String systemName;
    @Schema(required = false, description = "시스템 상세 설명")
    private String systemNameDesc;
    @Schema(required = false, description = "로그인 시 SMS 실패 횟수 사용 여부")
    private String useLoginSmsFailCntYN;
    @Schema(required = false, description = "로그인 시 EMAIL 사용 여부")
    private String useLoginEMAILYN;

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

    public String getUseLoginFailCntYN() {
        return useLoginFailCntYN;
    }

    public void setUseLoginFailCntYN(String useLoginFailCntYN) {
        this.useLoginFailCntYN = useLoginFailCntYN;
    }

    public String getLoginFailCntLimit() {
        return loginFailCntLimit;
    }

    public void setLoginFailCntLimit(String loginFailCntLimit) {
        this.loginFailCntLimit = loginFailCntLimit;
    }

    public String getUseLoginSMSYN() {
        return useLoginSMSYN;
    }

    public void setUseLoginSMSYN(String useLoginSMSYN) {
        this.useLoginSMSYN = useLoginSMSYN;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemNameDesc() {
        return systemNameDesc;
    }

    public void setSystemNameDesc(String systemNameDesc) {
        this.systemNameDesc = systemNameDesc;
    }

    public String getUseLoginSmsFailCntYN() {
        return useLoginSmsFailCntYN;
    }

    public void setUseLoginSmsFailCntYN(String useLoginSmsFailCntYN) {
        this.useLoginSmsFailCntYN = useLoginSmsFailCntYN;
    }

    public String getUseLoginEMAILYN() {
        return useLoginEMAILYN;
    }

    public void setUseLoginEMAILYN(String useLoginEMAILYN) {
        this.useLoginEMAILYN = useLoginEMAILYN;
    }
}
