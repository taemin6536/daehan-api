package com.nuri.mys.bems.domain.entity.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DashboardSiteInfoRes")
public class DashboardSiteInfoRes {
    @Schema(description = "사업장 아이디")
    private String siteId;
    @Schema(description = "사업장명")
    private String siteNm;
    @Schema(description = "담당자명")
    private String managerNm;
    @Schema(description = "주소")
    private String address;
    @Schema(description = "전화번호")
    private String phoneNo;

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

    public String getManagerNm() {
        return managerNm;
    }

    public void setManagerNm(String managerNm) {
        this.managerNm = managerNm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
