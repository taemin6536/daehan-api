package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "ManagementUserDeleteDto")
public class ManagementUserDeleteDto {
    @Schema(required = true, description = "사용자 seq")
    @NotNull
    private int userSeq;

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

}
