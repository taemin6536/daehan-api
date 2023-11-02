package com.nuri.mys.bems.domain.entity.dashboard;


import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Schema(description = "DashboardOperationChartRes")
public class DashboardOperationChartRes {
	@Schema(description = "충전량")
	private Double periodChargeKwh;
	@Schema(description = "방전량")
	private Double periodDischaKwh;
	@Schema(description = "발전량")
	private Double periodGenKwh;
	@Schema(description = "시간")
	private String time;

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
