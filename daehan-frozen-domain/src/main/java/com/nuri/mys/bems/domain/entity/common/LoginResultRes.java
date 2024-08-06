package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ResultModel")
public class LoginResultRes {
    @Schema(required = false, description = "로그인 사용자 ID")
    private String userId;
    @Schema(required = false, description = "로그인 성공 실패 여부")
    private String status;
    @Schema(required = false, description = "SMS 전송 체크시 : 인증번호 발송 여부, SMS 전송 미 체크시 : 토큰 값")
    private String loginAfterData;
    @Schema(required = false, description = "로그인 실패 시 이유")
    private String failContents;
    @Schema(required = false, description = "성공이지만 팝업 호출 필요 시")
    private String popContents;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginAfterData() {
        return loginAfterData;
    }

    public void setLoginAfterData(String loginAfterData) {
        this.loginAfterData = loginAfterData;
    }

    public String getFailContents() {
        return failContents;
    }

    public void setFailContents(String failContents) {
        this.failContents = failContents;
    }

    public String getPopContents() {
        return popContents;
    }

    public void setPopContents(String popContents) {
        this.popContents = popContents;
    }
}
