package com.nuri.mys.bems.domain.entity.operation;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OperationEventTableDto")
public class OperationEventTableDto extends CommonPageDto {
	@Schema(required = false,  description = "장비 타입", example="311", maxLength = 10)
	private String grId;
	@Schema(required = false, description = "수위",example = "W", maxLength = 10)
	private String eventLvl;
	@Schema(required = false, description = "상태코드", example = "ES001", maxLength = 5)
	private String eventStatusCd;

	public String getGrId() {
		return grId;
	}

	public void setGrId(String grId) {
		this.grId = grId;
	}

	public String getEventLvl() {
		return eventLvl;
	}

	public void setEventLvl(String eventLvl) {
		this.eventLvl = eventLvl;
	}

	public String getEventStatusCd() {
		return eventStatusCd;
	}

	public void setEventStatusCd(String eventStatusCd) {
		this.eventStatusCd = eventStatusCd;
	}
}
