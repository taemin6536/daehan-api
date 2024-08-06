package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Schema(description = "CommonPageDto")
public class CommonPageDto {

	@Schema(description = "몇번째 페이지를 눌렀는지? default:1", example="1")
	private int pageNum;
	@Schema(description = "몇개 데이터씩 볼것인지? default:10", example="10")
	private int rowLength;
	@Schema(description = "정렬할 컬럼 indet default:1", example="1")
	private String orderColNum;
	@Schema(description = "오름,내림차순 정렬 (DESC, ASS) default:DESC", example="DESC")
	private String orderType;
	@Schema(description = "몇번째 로우부터 가져올 것인지?", example="0")
	private int startNumber;
	@Schema(description = "정렬할 컬럼명", example="time")
	private String orderCol;
	@Schema(required = false, description = "엑셀 다운로드 여부 구분하기 위함", example = "excel")
	private String exportType;
	
	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	/**
	 * @return the startNumber
	 */
	public int getStartNumber() {
		return startNumber;
	}
	/**
	 * @param startNumber the startNumber to set
	 */
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
	/**
	 * @return the rowLength
	 */
	public int getRowLength() {
		return rowLength;
	}
	/**
	 * @param rowLength the rowLength to set
	 */
	public void setRowLength(int rowLength) {
		this.rowLength = rowLength;
	}
	/**
	 * @return the orderColNum
	 */
	public String getOrderColNum() {
		return orderColNum;
	}
	/**
	 * @param orderColNum the orderColNum to set
	 */
	public void setOrderColNum(String orderColNum) {
		this.orderColNum = orderColNum;
	}
	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderCol() {
		return orderCol;
	}

	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
