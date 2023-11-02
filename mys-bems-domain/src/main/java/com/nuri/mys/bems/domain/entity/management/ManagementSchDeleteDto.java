package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementSchDeleteDto")
public class ManagementSchDeleteDto extends ManagementSchCommonDto{
    @Schema(required = true, description = "시퀀스", example = "[1, 2, 3]")
    private int[] schSeq;

    public int[] getSchSeq() {
        return schSeq;
    }

    public void setSchSeq(int[] schSeq) {
        this.schSeq = schSeq;
    }
}
