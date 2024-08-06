package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.List;

@Schema(description = "ManagementUserTableDataRes")
public class ManagementUserTableDataRes {
    @Schema(description = "유저 시퀀스")
    private int userSeq;
    @Schema(description = "유저 아이디")
    private String userId;
    @Schema(description = "유저 이름")
    private String userNm;
    @Schema(description = "유저 권한ID")
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
    @Schema(description = "알림 구분")
    private String recClassification;
    @Schema(description = "SMS 수신여부")
    private String smsRecYn;
    @Schema(description = "이메일 수신여부")
    private String emailRecYn;
    @Schema(description = "텔레그램 수신여부")
    private String telegramRecYn;
    @Schema(description = "이벤트 알람 수신여부")
    private String eventRecYn;
    @Schema(description = "생성시간")
    private Date createDt;
    @Schema(description = "수정 일시")
    private String updateDt;
    @Schema(description = "데이터 총 갯수")
    private int dataTotalCount;
    @Schema(description = "알람 구분")
    private List<String> alarmClassification;

    public List<String> getAlarmClassification() {
        return alarmClassification;
    }

    public void setAlarmClassification(List<String> alarmClassification) {
        this.alarmClassification = alarmClassification;
    }

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

    public String getRecClassification() {
        return recClassification;
    }

    public void setRecClassification(String recClassification) {
        this.recClassification = recClassification;
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

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }

    public int getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(int dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }
}
