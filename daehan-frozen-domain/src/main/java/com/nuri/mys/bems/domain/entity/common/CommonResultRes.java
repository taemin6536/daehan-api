package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "결과를 리턴하는 모델")
public class CommonResultRes {
    @Schema(required = false, description = "성공 실패 여부")
    private String status;
    @Schema(required = false, description = "이전 날짜 데이터가 들어왔을 시 리턴")
    private String etc;
    @Schema(required = false, description = "실패 시 이유")
    private String failContents;
    @Schema(required = false, description = "팝업 호출 필요 시")
    private String popContents;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailContents() {
        return failContents;
    }

    public void setFailContents(String failContents) {
        this.failContents = failContents;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getPopContents() {
        return popContents;
    }

    public void setPopContents(String popContents) {
        this.popContents = popContents;
    }
}
