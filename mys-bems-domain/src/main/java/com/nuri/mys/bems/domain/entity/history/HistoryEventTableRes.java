package com.nuri.mys.bems.domain.entity.history;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryEventTableRes")
public class HistoryEventTableRes {
	@Schema(description = "이벤트 일련번호")
	private int eventSeq;
	@Schema(description = "장비 ID")
	private String deviceId;
	@Schema(description = "장비 명")
	private String deviceNm;
	@Schema(description = "그룹 ID")
	private String grId;
	@Schema(description = "시작 시간")
	private String startTime;
	@Schema(description = "종료 시간")
	private String endTime;
	@Schema(description = "이벤트 레벨")
	private String eventLvl;
	@Schema(description = "이벤트레벨 명")
	private String eventLvlNm;
	@Schema(description = "이벤트 상태 코드")
	private String eventStatusCd;
	@Schema(description = "이벤트 상태 명")
	private String eventStatusNm;
	@Schema(description = "이벤트 명")
	private String eventNm;
	@Schema(description = "이벤트 설명")
	private String eventDesc;
	@Schema(description = "경고")
	private int warning;
	@Schema(description = "고장")
	private int fault;

	@Schema(description = "이벤트상태(Front용)")
	private String eventStatusClass;
	
    @Schema(description = "DataGrid Data Total Count")
    private int dataTotalCount;

	public int getEventSeq() {
		return eventSeq;
	}

	public void setEventSeq(int eventSeq) {
		this.eventSeq = eventSeq;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceNm() {
		return deviceNm;
	}

	public void setDeviceNm(String deviceNm) {
		this.deviceNm = deviceNm;
	}

	public String getGrId() {
		return grId;
	}

	public void setGrId(String grId) {
		this.grId = grId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEventLvl() {
		return eventLvl;
	}

	public void setEventLvl(String eventLvl) {
		this.eventLvl = eventLvl;
	}

	public String getEventLvlNm() {
		return eventLvlNm;
	}

	public void setEventLvlNm(String eventLvlNm) {
		this.eventLvlNm = eventLvlNm;
	}

	public String getEventStatusCd() {
		return eventStatusCd;
	}

	public void setEventStatusCd(String eventStatusCd) {
		this.eventStatusCd = eventStatusCd;
	}

	public String getEventStatusNm() {
		return eventStatusNm;
	}

	public void setEventStatusNm(String eventStatusNm) {
		this.eventStatusNm = eventStatusNm;
	}

	public String getEventNm() {
		return eventNm;
	}

	public void setEventNm(String eventNm) {
		this.eventNm = eventNm;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
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

	public String getEventStatusClass() {
		return eventStatusClass;
	}

	public void setEventStatusClass(String eventStatusClass) {
		this.eventStatusClass = eventStatusClass;
	}

	public int getDataTotalCount() {
		return dataTotalCount;
	}

	public void setDataTotalCount(int dataTotalCount) {
		this.dataTotalCount = dataTotalCount;
	}
}
