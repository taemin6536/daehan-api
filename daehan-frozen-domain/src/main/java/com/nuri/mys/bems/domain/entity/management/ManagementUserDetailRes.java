package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "ManagementUserDetailRes")
public class ManagementUserDetailRes {
    @Schema(description = "유저 시퀀스")
    private int userSeq;
    @Schema(description = "유저 아이디")
    private String userId;
    @Schema(description = "유저 이름")
    private String userNm;
    @Schema(description = "유저 권한 아이디")
    private String permGrId;
    @Schema(description = "유저 권한 명")
    private String permGrNm;
    @Schema(description = "전화번호")
    private String mobileNo;
    @Schema(description = "전화번호")
    private String phoneNo;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "텔레그램 아이디")
    private String telegramId;
    @Schema(description = "SMS 수신여부")
    private String smsRecYn;
    @Schema(description = "이메일 수신여부")
    private String emailRecYn;
    @Schema(description = "텔레그램 수신여부")
    private String telegramRecYn;
    @Schema(description = "이벤트 알람 수신여부")
    private String eventRecYn;
    @Schema(description = "신규비밀번호", hidden=true)
    private String passNo;
    @Schema(description = "지난 패스워드 1", hidden=true)
    private String pastPassNo1;
    @Schema(description = "지난 패스워드 2", hidden=true)
    private String pastPassNo2;
    @Schema(description = "지난 패스워드 3", hidden=true)
    private String pastPassNo3;
    @Schema(description = "패스워드 변경 일자", hidden=true)
    private Date passUpdateDt;
    @Schema(description = "생성 아이디", hidden=true)
    private String createId;

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getPermGrId() {
        return permGrId;
    }

    public void setPermGrId(String permGrId) {
        this.permGrId = permGrId;
    }

    public String getPermGrNm() {
        return permGrNm;
    }

    public void setPermGrNm(String permGrNm) {
        this.permGrNm = permGrNm;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getSmsRecYn() {
        return smsRecYn;
    }

    public void setSmsRecYn(String smsRecYn) {
        this.smsRecYn = smsRecYn;
    }

    public String getEmailRecYn() {
        return emailRecYn;
    }

    public void setEmailRecYn(String emailRecYn) {
        this.emailRecYn = emailRecYn;
    }

    public String getTelegramRecYn() {
        return telegramRecYn;
    }

    public void setTelegramRecYn(String telegramRecYn) {
        this.telegramRecYn = telegramRecYn;
    }

    public String getEventRecYn() {
        return eventRecYn;
    }

    public void setEventRecYn(String eventRecYn) {
        this.eventRecYn = eventRecYn;
    }

    public String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
    }

    public String getPastPassNo1() {
        return pastPassNo1;
    }

    public void setPastPassNo1(String pastPassNo1) {
        this.pastPassNo1 = pastPassNo1;
    }

    public String getPastPassNo2() {
        return pastPassNo2;
    }

    public void setPastPassNo2(String pastPassNo2) {
        this.pastPassNo2 = pastPassNo2;
    }

    public String getPastPassNo3() {
        return pastPassNo3;
    }

    public void setPastPassNo3(String pastPassNo3) {
        this.pastPassNo3 = pastPassNo3;
    }

    public Date getPassUpdateDt() {
        return passUpdateDt;
    }

    public void setPassUpdateDt(Date passUpdateDt) {
        this.passUpdateDt = passUpdateDt;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
}
