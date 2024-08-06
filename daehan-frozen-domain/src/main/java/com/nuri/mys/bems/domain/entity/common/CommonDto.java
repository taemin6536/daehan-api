package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "CommonDto")
public class CommonDto extends CommonPageDto {

	@Schema(required = true, description = "입력 형식(yyyyMMddHHmmss) 12자리. 현황일 시 오늘 날짜, 통계일 시 기간 날짜", example="202211050000")
	@NotBlank
	private String dateFrom;
	@Schema(required = true, description = "입력 형식(yyyyMMddHHmmss) 12자리. 현황이나 기간 조건에 따라서 없는 경우 있음", example="202211302359")
	@NotBlank
	private String dateTo;
	@Schema(required = false, description = "기간 타입(시간(hourly)/일(daily)/월(daily)/년(monly)/기간(daily))에 따른 테이블명", example="hourly",hidden = true)
	private String periodTableName; // Enum으로 관리 필요.
	@Schema(required = true, description = "기간 검색 타입(시간(hour)/일(day)/월(month)/년(year)/기간(period))", example="hour")
	@NotBlank
	private String periodType; // Enum으로 관리 필요.

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

}
