package com.nuri.mys.bems.domain.entity.history;

import com.nuri.mys.bems.domain.entity.common.CommonPeriodDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "HistoryDeviceChartCommonDto")
public class HistoryDeviceChartCommonDto extends CommonPeriodDto {
    @Schema(required = true, description = "장비 ID, 7자리 숫자로 제한한다.")
    @NotBlank
    @Pattern(regexp="^(\\d{7})")
    private String deviceId;
    @Schema(required = true, description = "장비 현황&통계, 조회 컬럼 배열", example="[\"time\"]")
    private String[] columnArray;
    @Schema(required = false, description = "RACK장비 선택시 String ID", example = "1")
    private String rackStringId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String[] getColumnArray() {
        return columnArray;
    }

    public void setColumnArray(String[] columnArray) {
        this.columnArray = columnArray;
    }

    public String getRackStringId() {
        return rackStringId;
    }

    public void setRackStringId(String rackStringId) {
        this.rackStringId = rackStringId;
    }
}
