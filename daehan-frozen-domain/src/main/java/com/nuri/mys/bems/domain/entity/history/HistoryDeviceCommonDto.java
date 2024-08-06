package com.nuri.mys.bems.domain.entity.history;

import com.nuri.mys.bems.domain.entity.common.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryDeviceCommonDto")
public class HistoryDeviceCommonDto extends CommonDto {
    @Schema(required = false, description = "장비 ID", example = "1000001")
    private String deviceId;
    @Schema(required = false, description = "RACK장비 선택시 String ID", example = "1")
    private String rackStringId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRackStringId() {
        return rackStringId;
    }

    public void setRackStringId(String rackStringId) {
        this.rackStringId = rackStringId;
    }
}
