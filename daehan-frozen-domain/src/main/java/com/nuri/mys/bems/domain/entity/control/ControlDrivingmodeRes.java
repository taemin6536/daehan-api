package com.nuri.mys.bems.domain.entity.control;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ControlDrivingmodeRes")
public class ControlDrivingmodeRes {

	@Schema(description = "모드 명칭(스케줄,자동,수동)")
	private String drivingMode;

	public String getDrivingMode() {
		return drivingMode;
	}

	public void setDrivingMode(String drivingMode) {
		this.drivingMode = drivingMode;
	}
}