package com.nuri.mys.bems.domain.entity.history;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "HistoryDeviceRealtimeDto")
public class HistoryDeviceRealtimeDto {
    @Schema(required = true, description = "장비 ID", example = "1000001")
    @Pattern(regexp="^(\\d{7})")
    @NotBlank
    private String deviceId;
    @Schema(required = false, description = "RACK장비 선택시 String ID", example = "1")
    private String rackStringId;
    @Schema(required = true, description = "입력 형식(yyyyMMddHHmmss) 12자리. 현황일 시 오늘 날짜, 통계일 시 기간 날짜", example="202211050000")
    @NotBlank
    private String dateFrom;
    @Schema(required = true, description = "입력 형식(yyyyMMddHHmmss) 12자리. 현황이나 기간 조건에 따라서 없는 경우 있음", example="202211302359")
    @NotBlank
    private String dateTo;

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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
