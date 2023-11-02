package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementSchPcsInfoDto")
public class ManagementSchPcsInfoDto {
	@Schema(required = false, description = "장비 ID", example="1000001", maxLength = 50)
	private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
