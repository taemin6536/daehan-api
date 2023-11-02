package com.nuri.mys.bems.domain.entity.operation;


import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Schema(description = "OperationChartRes")
public class OperationChartRes {
	@Schema(description = "시간")
	private String time;
	@Schema(description = "충전량")
	private Double periodChargeKwh;
	@Schema(description = "방전량")
	private Double periodDischaKwh;
	@Schema(description = "발전량")
	private Double periodGenKwh;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getPeriodChargeKwh() {
		return periodChargeKwh;
	}

	public void setPeriodChargeKwh(Double periodChargeKwh) {
		this.periodChargeKwh = periodChargeKwh;
	}

	public Double getPeriodDischaKwh() {
		return periodDischaKwh;
	}

	public void setPeriodDischaKwh(Double periodDischaKwh) {
		this.periodDischaKwh = periodDischaKwh;
	}

	public Double getPeriodGenKwh() {
		return periodGenKwh;
	}

	public void setPeriodGenKwh(Double periodGenKwh) {
		this.periodGenKwh = periodGenKwh;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
