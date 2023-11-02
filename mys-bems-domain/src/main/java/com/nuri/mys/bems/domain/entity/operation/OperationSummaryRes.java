package com.nuri.mys.bems.domain.entity.operation;


import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Schema(description = "OperationSummaryRes")
public class OperationSummaryRes {

	@Schema(description = "금일 태양광 발전량")
	private Double periodGenKwh;
	@Schema(description = "누적 태양광 발전량")
	private Double totGenKwh;
	@Schema(description = "금일 충전량")
	private Double periodChargeKwh;
	@Schema(description = "금일 방전량")
	private Double periodDischaKwh;
	@Schema(description = "누적 충전량")
	private Double totChargeKwh;
	@Schema(description = "누적 방전량")
	private Double totDischaKwh;

	public Double getPeriodGenKwh() {
		return periodGenKwh;
	}

	public void setPeriodGenKwh(Double periodGenKwh) {
		this.periodGenKwh = periodGenKwh;
	}

	public Double getTotGenKwh() {
		return totGenKwh;
	}

	public void setTotGenKwh(Double totGenKwh) {
		this.totGenKwh = totGenKwh;
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

	public Double getTotChargeKwh() {
		return totChargeKwh;
	}

	public void setTotChargeKwh(Double totChargeKwh) {
		this.totChargeKwh = totChargeKwh;
	}

	public Double getTotDischaKwh() {
		return totDischaKwh;
	}

	public void setTotDischaKwh(Double totDischaKwh) {
		this.totDischaKwh = totDischaKwh;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
