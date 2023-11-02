package com.nuri.mys.bems.domain.entity.history;

import com.nuri.mys.bems.domain.entity.common.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryEventTableDto")
public class HistoryEventTableDto extends CommonDto {
	@Schema(required = false,  description = "장비 타입", example="311", maxLength = 10)
	private String grId;
	@Schema(required = false,  description = "장비 ID", example="3110001",maxLength = 50)
	private String deviceId;
	@Schema(required = false, description = "수위", example = "W", maxLength = 10)
	private String eventLvl;
	@Schema(required = false, description = "상태코드", example = "ES001", maxLength = 5)
	private String eventStatusCd;
	@Schema(required = false, description = "최초 호출 유무(Y/N)", example = "Y")
	private String initialYn;

	public String getGrId() {
		return grId;
	}

	public void setGrId(String grId) {
		this.grId = grId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	public String getInitialYn() {
		return initialYn;
	}

	public void setInitialYn(String initialYn) {
		this.initialYn = initialYn;
	}
}
