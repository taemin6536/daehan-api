package com.nuri.mys.bems.domain.entity.history;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryDeviceThsDataRes")
public class HistoryDeviceThsDataRes {
    @Schema(required = false, description = "시간")
	private String time;
    @Schema(required = false, description = "장비 ID")
	private String deviceId;
    @Schema(description = "장비명")
	private String deviceNm;
	@Schema(description = "통신 상태(0:성공, 1:장애)")
	private String comYn;
    @Schema(description = "센서 01")
	private Double sen01;
    @Schema(description = "센서 02")
	private Double sen02;
	@Schema(description = "위치")
	private String location;
	@Schema(description = "DataGrid Data Total Count")
	private int dataTotalCount;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

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

	public String getComYn() {
		return comYn;
	}

	public void setComYn(String comYn) {
		this.comYn = comYn;
	}

	public Double getSen01() {
		return sen01;
	}

	public void setSen01(Double sen01) {
		this.sen01 = sen01;
	}

	public Double getSen02() {
		return sen02;
	}

	public void setSen02(Double sen02) {
		this.sen02 = sen02;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getDataTotalCount() {
		return dataTotalCount;
	}

	public void setDataTotalCount(int dataTotalCount) {
		this.dataTotalCount = dataTotalCount;
	}
}
