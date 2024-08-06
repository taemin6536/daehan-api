package com.nuri.mys.bems.domain.entity.history;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryEventSumDto")
public class HistoryEventSumDto {

	@Schema(required = false,  description = "입력 형식(yyyyMMddHHmmss) 14자리. 현황일 시 오늘 날짜, 통계일 시 기간 날짜", example="20220820000000")
	private String dateFrom;
	@Schema(required = false,  description = "입력 형식(yyyyMMddHHmmss) 14자리. 현황이나 기간 조건에 따라서 없는 경우 있음", example="20220920000000")
	private String dateTo;
	@Schema(required = false,  description = "기간 타입(시간(hourly)/일(daily)/월(daily)/년(monly)/기간(daily))에 따른 테이블명", hidden=true)
	private String periodTableName; // Enum으로 관리 필요.
	@Schema(required = false,  description = "기간 검색 타입(시간(hour)/일(day)/월(month)/년(year)/기간(period))", example="month")
	private String periodType;
	@Schema(required = false, description = "최초 호출 유무(Y/N)", example = "Y")
	private String initialYn;

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getPeriodTableName() {
		return periodTableName;
	}

	public void setPeriodTableName(String periodTableName) {
		this.periodTableName = periodTableName;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public String getInitialYn() {
		return initialYn;
	}

	public void setInitialYn(String initialYn) {
		this.initialYn = initialYn;
	}
}
