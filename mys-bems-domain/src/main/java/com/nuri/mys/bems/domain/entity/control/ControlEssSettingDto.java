package com.nuri.mys.bems.domain.entity.control;


import com.nuri.mys.bems.domain.entity.management.ManagementSettingEssInfoRes;
import com.nuri.mys.bems.domain.entity.management.ManagementSettingSaveEssDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ControlEssSettingDto")
public class ControlEssSettingDto extends ControlCommonDto {
    @Schema(description = "이전 설정 데이터", hidden=true)
    private ManagementSettingEssInfoRes beforeData;
    @Schema(description = "저장 데이터", hidden = true)
    private ManagementSettingSaveEssDto afterData;

    public ManagementSettingEssInfoRes getBeforeData() {
        return beforeData;
    }

    public void setBeforeData(ManagementSettingEssInfoRes beforeData) {
        this.beforeData = beforeData;
    }

    public ManagementSettingSaveEssDto getAfterData() {
        return afterData;
    }

    public void setAfterData(ManagementSettingSaveEssDto afterData) {
        this.afterData = afterData;
    }
}
