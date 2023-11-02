package com.nuri.mys.bems.service.control;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Schema(description = "This is the root of all models")
public class Header implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String siteNm;
	/** Transaction Identifier, yyyyMMddHHmmssSSS 형식 */
	@Schema(description="Transaction Identifier", required = true)
	private String transactionId;
	
	/** Site(PMS) Identifier */
	@Schema(description="Site(PMS) Identifier", required = true)
	@NotBlank(message = "Null or spaces are not allowed")
	private String siteId;
	
	/** Device Group Identifier, 100: PCS, 200: BMS, 210: BRS, etc */
	@Schema(description="Device Group Identifier", required = true)
	@NotBlank(message = "Null or spaces are not allowed")
	private String grId;
	
	/** Monitored Time or Time to collect data from a device */
	@Schema(description="Monitored Time or Time to collect data from a device", required = false)
	@NotBlank(message = "Null or spaces are not allowed")
	@Pattern(regexp = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])", message = "Only numbers in YYYYMMDDHHMMSS format can be entered")
	private String time;

	private String devId;
	private String devNm;
//	private String areaCd;
	
	public Header() {
		
	}
	
	public Header(Header h) {
		setHeader(h);
	}
	
	public void setHeader(Header h) {
		setTransactionId(h.getTransactionId());
		setSiteId(h.getSiteId());
		setGrId(h.getGrId());
		setTime(h.getTime());
		setSiteNm(h.getSiteNm());
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getTime() {
		return time;
	}

	/**
	 * @return the grId
	 */
	public String getGrId() {
		return grId;
	}

	/**
	 * @param grId the grId to set
	 */
	public void setGrId(String grId) {
		this.grId = grId;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	/**
	 * @return the siteNm
	 */
	public String getSiteNm() {
		return siteNm;
	}

	/**
	 * @param siteNm the siteNm to set
	 */
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}

	public String getDevNm() {
		return devNm;
	}

	public void setDevNm(String devNm) {
		this.devNm = devNm;
	}
}
