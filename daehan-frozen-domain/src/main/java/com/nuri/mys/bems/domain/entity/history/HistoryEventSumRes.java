package com.nuri.mys.bems.domain.entity.history;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryEventSumRes")
public class HistoryEventSumRes {
	@Schema(description = "그룹 ID")
	private String grId;
	@Schema(description = "그룹명")
	private String grNm;
	@Schema(description = "경고")
	private int warning;
	@Schema(description = "고장")
	private int fault;

	public String getGrId() {
		return grId;
	}

	public void setGrId(String grId) {
		this.grId = grId;
	}

	public String getGrNm() {
		return grNm;
	}

	public void setGrNm(String grNm) {
		this.grNm = grNm;
	}

	public int getWarning() {
		return warning;
	}

	public void setWarning(int warning) {
		this.warning = warning;
	}

	public int getFault() {
		return fault;
	}

	public void setFault(int fault) {
		this.fault = fault;
	}
}
