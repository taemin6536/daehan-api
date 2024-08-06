package com.nuri.mys.bems.domain.jwt.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "UserInfoRes")
public class UserInfoRes {
    @Schema(required = false, description = "사용자 시퀀스")
    private int nUserSeq;
    @Schema(required = false, description = "사용자 아이디")
    private String vUserId;
    @Schema(required = false, description = "사용자 명")
    private String vUserNm;
    @Schema(required = false, description = "사용자 영문명")
    private String vUserEngNm;
    @Schema(required = false, description = "권한 아이디")
    private String vPermGrId;
    @Schema(required = false, description = "권한")
    private String vPermGrNm;
    @Schema(required = false, description = "사이트 아이디")
    private String vSiteId;
    @Schema(required = false, description = "사업자 그룹 아이디")
    private String vBizGrId;
    @Schema(required = false, description = "에너지 타입")
    private String vEnergyType;
    @Schema(required = false, description = "전화번호")
    private String vPhoneNo;
    @Schema(required = false, description = "핸드폰 번호")
    private String vMobileNo;
    @Schema(required = false, description = "로그인 실패 횟수")
    private int nLoginFailCnt;
    @Schema(required = false, description = "비밀번호 수정 일시")
    private Date tPassUpdateDt;
    @Schema(required = false, description = "SMS 수신여부")
    private String vSmsRecYn;
    @Schema(required = false, description = "화면표출여부")
    private String vWebShowYn;
    @Schema(required = false, description = "인증번호")
    private int nSmsNo;
    @Schema(required = false, description = "생일 일시")
    private Date tCreateDt;
    @Schema(required = false, description = "수정 일시")
    private Date tUpdateDt;
    @Schema(required = false, description = "생성 자")
    private String vCreateId;
    @Schema(required = false, description = "수정 자")
    private String vUpdateId;
    @Schema(description = "계정 변경 구분")
    private String vChangeDiv;
    @Schema(description = "계정 변경 설명")
    private String vChangeDesc;
    @Schema(description = "계정 잠금 설정")
    private String vAccountState;

    public int getnUserSeq() {
        return nUserSeq;
    }

    public void setnUserSeq(int nUserSeq) {
        this.nUserSeq = nUserSeq;
    }

    public String getvUserId() {
        return vUserId;
    }

    public void setvUserId(String vUserId) {
        this.vUserId = vUserId;
    }

    public String getvUserNm() {
        return vUserNm;
    }

    public void setvUserNm(String vUserNm) {
        this.vUserNm = vUserNm;
    }

    public String getvUserEngNm() {
        return vUserEngNm;
    }

    public void setvUserEngNm(String vUserEngNm) {
        this.vUserEngNm = vUserEngNm;
    }

    public String getvPermGrId() {
        return vPermGrId;
    }

    public void setvPermGrId(String vPermGrId) {
        this.vPermGrId = vPermGrId;
    }

    public String getvPermGrNm() {
        return vPermGrNm;
    }

    public void setvPermGrNm(String vPermGrNm) {
        this.vPermGrNm = vPermGrNm;
    }

    public String getvSiteId() {
        return vSiteId;
    }

    public void setvSiteId(String vSiteId) {
        this.vSiteId = vSiteId;
    }

    public String getvBizGrId() {
        return vBizGrId;
    }

    public void setvBizGrId(String vBizGrId) {
        this.vBizGrId = vBizGrId;
    }

    public String getvEnergyType() {
        return vEnergyType;
    }

    public void setvEnergyType(String vEnergyType) {
        this.vEnergyType = vEnergyType;
    }

    public String getvPhoneNo() {
        return vPhoneNo;
    }

    public void setvPhoneNo(String vPhoneNo) {
        this.vPhoneNo = vPhoneNo;
    }

    public String getvMobileNo() {
        return vMobileNo;
    }

    public void setvMobileNo(String vMobileNo) {
        this.vMobileNo = vMobileNo;
    }

    public int getnLoginFailCnt() {
        return nLoginFailCnt;
    }

    public void setnLoginFailCnt(int nLoginFailCnt) {
        this.nLoginFailCnt = nLoginFailCnt;
    }

    public Date gettPassUpdateDt() {
        return tPassUpdateDt;
    }

    public void settPassUpdateDt(Date tPassUpdateDt) {
        this.tPassUpdateDt = tPassUpdateDt;
    }

    public String getvSmsRecYn() {
        return vSmsRecYn;
    }

    public void setvSmsRecYn(String vSmsRecYn) {
        this.vSmsRecYn = vSmsRecYn;
    }

    public String getvWebShowYn() {
        return vWebShowYn;
    }

    public void setvWebShowYn(String vWebShowYn) {
        this.vWebShowYn = vWebShowYn;
    }

    public int getnSmsNo() {
        return nSmsNo;
    }

    public void setnSmsNo(int nSmsNo) {
        this.nSmsNo = nSmsNo;
    }

    public Date gettCreateDt() {
        return tCreateDt;
    }

    public void settCreateDt(Date tCreateDt) {
        this.tCreateDt = tCreateDt;
    }

    public Date gettUpdateDt() {
        return tUpdateDt;
    }

    public void settUpdateDt(Date tUpdateDt) {
        this.tUpdateDt = tUpdateDt;
    }

    public String getvCreateId() {
        return vCreateId;
    }

    public void setvCreateId(String vCreateId) {
        this.vCreateId = vCreateId;
    }

    public String getvUpdateId() {
        return vUpdateId;
    }

    public void setvUpdateId(String vUpdateId) {
        this.vUpdateId = vUpdateId;
    }

    public String getvChangeDiv() {
        return vChangeDiv;
    }

    public void setvChangeDiv(String vChangeDiv) {
        this.vChangeDiv = vChangeDiv;
    }

    public String getvChangeDesc() {
        return vChangeDesc;
    }

    public void setvChangeDesc(String vChangeDesc) {
        this.vChangeDesc = vChangeDesc;
    }

    public String getvAccountState() {
        return vAccountState;
    }

    public void setvAccountState(String vAccountState) {
        this.vAccountState = vAccountState;
    }
}
