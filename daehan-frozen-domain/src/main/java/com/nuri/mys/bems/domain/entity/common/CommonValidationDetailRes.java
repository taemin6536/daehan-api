package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "결과를 리턴하는 모델")
public class CommonValidationDetailRes {
    @Schema(required = false, description = "구분")
    private String classfication;
    @Schema(required = false, description = "오류 메세지")
    private String message;

    public String getClassfication() {
        return classfication;
    }

    public void setClassfication(String classfication) {
        this.classfication = classfication;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
