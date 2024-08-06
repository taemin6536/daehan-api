package com.nuri.mys.bems.domain.entity.operation;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OperationDeviceThsDataRes")
public class OperationDeviceThsDataRes extends OperationDeviceCommonRes{
    @Schema(description = "온도")
	private Double sen01;
    @Schema(description = "습도")
	private Double sen02;

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
}
