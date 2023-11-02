package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "LoginCheckDto")
public class LoginCheckDto {
    @NotBlank
    @Schema(required = true, description = "로그인 사용자 ID, 최대 길이는 20으로 제한하며 그 이상일 경우에는 정상적으로 처리하지 못한다.", example = "admin", maxLength = 20)
    private String userId;
    @NotBlank
    @Schema(required = true, description = "로그인 사용자 비밀번호", example = "ryJz6IKXTT+ko7rqVocxISlI6g0oUfGrrmTO7h6RZABag8/HCHUSfV9H3gKG/NDk7bYgqQCn2oS3KRU7To1SWAIL7ukj6YXGihRS5RSNmwHUeNZ0mbZ2nVplWD1VKwZr9/HZLQTPaZRrSUSH4A0NGWDsViOBTRsWSAkGI7d/nrQ=")
    private String password;

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
}
