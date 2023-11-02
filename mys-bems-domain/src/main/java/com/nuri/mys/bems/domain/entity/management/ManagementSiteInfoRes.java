package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementSiteInfoRes")
public class ManagementSiteInfoRes {
    @Schema(description = "사업장 아이디")
    private String siteId;
    @Schema(description = "사업장명")
    private String siteNm;
    @Schema(description = "담당자명")
    private String mngerNm;
    @Schema(description = "전화번호")
    private String phoneNo;
    @Schema(description = "알림 수신 여부")
    private String smsYn;
    @Schema(description = "가동시작일")
    private String cod;
    @Schema(description = "주소")
    private String address;
    @Schema(description = "주소 상세")
    private String addressDetail;

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

    public String getMngerNm() {
        return mngerNm;
    }

    public void setMngerNm(String mngerNm) {
        this.mngerNm = mngerNm;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSmsYn() {
        return smsYn;
    }

    public void setSmsYn(String smsYn) {
        this.smsYn = smsYn;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
