package com.nuri.mys.bems.domain.entity.management;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementSchTableDataDto")
public class ManagementSchTableDataDto extends CommonPageDto {
	@Schema(required = false, description = "장비 ID", example="1000001")
	private String deviceId;
	@Schema(required = false, description = "충전:1,방전:0", example="1", maxLength = 2)
	private String chadiStatus;
	@Schema(required = false, description = "봄:1, 여름:2, 가을:3, 겨울:4", example="1", maxLength = 2)
	private String seasonCd;
	@Schema(required = false, description = "1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일", example="1")
	private String weekCd;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getChadiStatus() {
		return chadiStatus;
	}

	public void setChadiStatus(String chadiStatus) {
		this.chadiStatus = chadiStatus;
	}

	public String getSeasonCd() {
		return seasonCd;
	}

	public void setSeasonCd(String seasonCd) {
		this.seasonCd = seasonCd;
	}

	public String getWeekCd() {
		return weekCd;
	}

	public void setWeekCd(String weekCd) {
		this.weekCd = weekCd;
	}
}
