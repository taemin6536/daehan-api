package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "결과를 리턴하는 모델")
public class CommonValidationRes {
    @NotNull(message="Null 또는 빈 값")
    @Schema(required = false, description = "에러 코드")
    private String status;
    @Schema(required = false, description = "오류 내용")
    private List<CommonValidationDetailRes> failContents;
    @Schema(required = false, description = "모델명")
    private String objectName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CommonValidationDetailRes> getFailContents() {
        return failContents;
    }

    public void setFailContents(List<CommonValidationDetailRes> failContents) {
        this.failContents = failContents;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
