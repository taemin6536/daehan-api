package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "LoginUserRes")
public class LoginUserRes {
    @Schema(required = false, description = "로그인 사용자 ID", example = "admin")
    private String userId;
    @Schema(required = false, description = "로그인 사용자 비밀번호", example = "ryJz6IKXTT+ko7rqVocxISlI6g0oUfGrrmTO7h6RZABag8/HCHUSfV9H3gKG/NDk7bYgqQCn2oS3KRU7To1SWAIL7ukj6YXGihRS5RSNmwHUeNZ0mbZ2nVplWD1VKwZr9/HZLQTPaZRrSUSH4A0NGWDsViOBTRsWSAkGI7d/nrQ=")
    private String password;
    @Schema(required = false, description = "전화번호")
    private String mobileNo;
    @Schema(required = false, description = "Email")
    private String email;
    @Schema(required = false, description = "권한")
    private String role;
    @Schema(required = false, description = "SMS인증번호")
    private int smsCode;
    @Schema(required = false, description = "임시비밀번호 발급여부")
    private String tempPassYn;
    @Schema(required = false, description = "로그인 실패 갯수")
    private int loginFailCnt;
    @Schema(required = false, description = "비밀번호수정일자", hidden = true)
    private Date passUpdateDt;
    @Schema(required = false, description = "과거 비밀번호", hidden=true)
    private String pastPassNo1;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(int smsCode) {
        this.smsCode = smsCode;
    }

    public String getTempPassYn() {
        return tempPassYn;
    }

    public void setTempPassYn(String tempPassYn) {
        this.tempPassYn = tempPassYn;
    }

    public int getLoginFailCnt() {
        return loginFailCnt;
    }

    public void setLoginFailCnt(int loginFailCnt) {
        this.loginFailCnt = loginFailCnt;
    }

    public Date getPassUpdateDt() {
        return passUpdateDt;
    }

    public void setPassUpdateDt(Date passUpdateDt) {
        this.passUpdateDt = passUpdateDt;
    }

    public String getPastPassNo1() {
        return pastPassNo1;
    }

    public void setPastPassNo1(String pastPassNo1) {
        this.pastPassNo1 = pastPassNo1;
    }
}
