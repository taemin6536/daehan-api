package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "ManagementUserDetailDto")
public class ManagementUserDetailDto {
    @Schema(required = true, description = "사용자 시퀀스", example="165")
    @NotNull
    private int userSeq;

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

}
