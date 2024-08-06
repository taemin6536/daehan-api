package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementCommonCapaInfoRes")

public class ManagementCommonCapaInfoRes {
	@Schema(description = "장비 ID")
	private String deviceId;
	@Schema(description = "장비명")
	private String deviceNm;
	@Schema(description = "용량")
	private double capa;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceNm() {
		return deviceNm;
	}

	public void setDeviceNm(String deviceNm) {
		this.deviceNm = deviceNm;
	}

	public double getCapa() {
		return capa;
	}

	public void setCapa(double capa) {
		this.capa = capa;
	}
}
