package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "ManagementUserHeaderSaveDto")
public class ManagementUserHeaderSaveDto {
    @Schema(required=true, description = "사용자 시퀀스")
    private int userSeq;
    @Schema(required = true, description = "사용자 아이디", example = "admin", maxLength = 20, hidden = true)
    private String userId;
    @Schema(description = "사용자 이름")
    private String userNm;
    @Schema(required = true, description = "기존비밀번호(헤더 사용자 수정 시)")
    @NotBlank
    private String nowPassNo;
    @Schema(description = "신규비밀번호", example = "ifvk76S9OZEg3IHAZgDICvBnepGQgaQw0Nvybc3EOLDOh3UrmLD0qSsqDMJeBT05B/aMF3sB6w+NN6X00UKoisogG+QUne3ysClJhm5GorkfyRmLf0gIa86YOEfOvhPhvcB6QjpUcOwBHbCzGlBIPsoJNJu10DaYyeavCjo2H6c=", maxLength = 255)
    private String passNo; //test1234
    @Schema(description = "유저 권한 아이디", example = "ROLE_USER", maxLength = 50)
    private String permGrId;
    @Schema(description = "유저 권한 명", example="사용자", maxLength=255, hidden = true)
    private String permGrNm;
    @Schema(description = "모바일 번호", example = "010-1234-5678")
    private String mobileNo;
    @Schema(description = "전화 번호", example = "010-1234-5678")
    private String phoneNo;
    @Schema(description = "이메일", example = "test@gmail.com", maxLength = 100)
    private String email;
    @Pattern(regexp="^(\\d{10})")
    private String telegramId;
    @Schema(description = "SMS 수신여부", example = "Y", maxLength = 1)
    private String smsRecYn;
    @Schema(description = "이메일 수신여부", example = "Y", maxLength = 1)
    private String emailRecYn;
    @Schema(description = "텔레그램 수신여부", example = "Y", maxLength = 1)
    private String telegramRecYn;
    @Schema(description = "이벤트 알람 수신여부", example = "Y", maxLength = 1)
    private String eventRecYn;

    @Schema(description = "지난 패스워드 1", hidden=true)
    private String pastPassNo1;
    @Schema(description = "지난 패스워드 2", hidden=true)
    private String pastPassNo2;
    @Schema(description = "지난 패스워드 3", hidden=true)
    private String pastPassNo3;
    @Schema(description = "임시 패스워드 발급 여부", hidden=true)
    private String tempPassYn;

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNowPassNo() {
        return nowPassNo;
    }

    public void setNowPassNo(String nowPassNo) {
        this.nowPassNo = nowPassNo;
    }

    public String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTempPassYn() {
        return tempPassYn;
    }

    public void setTempPassYn(String tempPassYn) {
        this.tempPassYn = tempPassYn;
    }
}