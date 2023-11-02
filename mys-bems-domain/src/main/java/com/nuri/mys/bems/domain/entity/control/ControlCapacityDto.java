package com.nuri.mys.bems.domain.entity.control;

import com.nuri.mys.bems.domain.entity.management.ManagementCommonCapaInfoRes;
import com.nuri.mys.bems.domain.entity.management.ManagementSettingSaveCapaDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "ControlCapacityDto")
public class ControlCapacityDto extends ControlCommonDto {
    @Schema(description = "이전 설정 데이터", hidden=true)
    private List<ManagementCommonCapaInfoRes> beforeData;
    @Schema(description = "저장 데이터", hidden = true)
    private List<ManagementSettingSaveCapaDto> afterData;

    public List<ManagementCommonCapaInfoRes> getBeforeData() {
        return beforeData;
    }

    public void setBeforeData(List<ManagementCommonCapaInfoRes> beforeData) {
        this.beforeData = beforeData;
    }

    public List<ManagementSettingSaveCapaDto> getAfterData() {
        return afterData;
    }

    public void setAfterData(List<ManagementSettingSaveCapaDto> afterData) {
        this.afterData = afterData;
    }
}
