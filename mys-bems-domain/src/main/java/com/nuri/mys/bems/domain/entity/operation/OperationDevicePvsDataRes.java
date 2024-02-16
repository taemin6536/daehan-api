package com.nuri.mys.bems.domain.entity.operation;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OperationDevicePvsDataRes")
public class OperationDevicePvsDataRes extends OperationDeviceCommonRes{
    @Schema(description = "수평 일사량")
	private Double horiRadiation;
    @Schema(description = "경사 일사량")
	private Double inclRadiation;
	@Schema(description = "온도")
	private Double temperature;
	@Schema(description = "습도")
	private Double humidity;

	public Double getHoriRadiation() {
		return horiRadiation;
	}

	public void setHoriRadiation(Double horiRadiation) {
		this.horiRadiation = horiRadiation;
	}

	public Double getInclRadiation() {
		return inclRadiation;
	}

	public void setInclRadiation(Double inclRadiation) {
		this.inclRadiation = inclRadiation;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
}
