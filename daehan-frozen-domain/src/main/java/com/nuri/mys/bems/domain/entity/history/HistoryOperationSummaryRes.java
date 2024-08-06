package com.nuri.mys.bems.domain.entity.history;


import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Schema(description = "HistoryOperationSummaryRes")
public class HistoryOperationSummaryRes {
	@Schema(description = "인버터 기간별 발전량")
	private Double periodGenKwh;
	@Schema(description = "ESS 기간별 충전량")
	private Double periodChargeKwh;
	@Schema(description = "ESS 기간별 방전량")
	private Double periodDischaKwh;
	@Schema(description = "인버터 총 발전량")
	private Double totGenKwh;
	@Schema(description = "ESS 총 충전량")
	private Double totChargeKwh;
	@Schema(description = "ESS 총 방전량")
	private Double totDischaKwh;

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
