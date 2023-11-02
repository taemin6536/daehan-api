package com.nuri.mys.bems.domain.entity.operation;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OperationCapaSummaryRes")
public class OperationCapaSummaryRes {
	@Schema(description = "태양광 용량")
	private Double solarCapa;
	@Schema(description = "pcs 용량")
	private Double pcsCapa;
	@Schema(description = "bms 용량")
	private Double bmsCapa;

	public Double getSolarCapa() {
		return solarCapa;
	}

	public void setSolarCapa(Double solarCapa) {
		this.solarCapa = solarCapa;
	}

	public Double getPcsCapa() {
		return pcsCapa;
	}

	public void setPcsCapa(Double pcsCapa) {
		this.pcsCapa = pcsCapa;
	}

	public Double getBmsCapa() {
		return bmsCapa;
	}

	public void setBmsCapa(Double bmsCapa) {
		this.bmsCapa = bmsCapa;
	}
}
