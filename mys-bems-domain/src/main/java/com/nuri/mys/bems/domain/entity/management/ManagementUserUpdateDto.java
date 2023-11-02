package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(description = "ManagementUserUpdateDto")
public class ManagementUserUpdateDto {
    @Schema(required = true, description = "사용자 seq")
    @NotNull
    private int userSeq;
    @Schema(required = true, description = "사용자 아이디", example = "admin", maxLength = 20, hidden = true)
    private String userId;
    @Schema(description = "수정하려는 사용자 이름", example="홍길동", maxLength=20)
    private String objectUserNm;
    @Schema(required = true, description = "유저 권한 아이디", example = "ROLE_USER", maxLength = 50)
    @NotBlank
    private String permGrId;
    @Schema(required = true, description = "유저 권한 명", example="사용자", maxLength=255, hidden = true)
    private String permGrNm;
    @Schema(description = "모바일 번호", example = "010-1234-5678")
    private String mobileNo;
    @Schema(description = "전화 번호", example = "010-1234-5678")
    private String phoneNo;
    @Schema(description = "이메일", example = "test@gmail.com")
    private String email;
    @Schema(description = "텔레그램 아이디(숫자 10자리로 구성)")
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

    public String getObjectUserNm() {
        return objectUserNm;
    }

    public void setObjectUserNm(String objectUserNm) {
        this.objectUserNm = objectUserNm;
    }
}
