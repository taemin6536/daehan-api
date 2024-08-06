package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CommonCodeRes")

public class CommonCodeRes {
	@Schema(description = "코드")
	private String detailCd;
	@Schema(description = "코드명")
	private String cdNm;
	@Schema(description = "한글 코드명")
	private String cdHanNm;
	@Schema(description = "영어 코드명")
	private String cdEngNm;

	public String getCdHanNm() {
		return cdHanNm;
	}

	public void setCdHanNm(String cdHanNm) {
		this.cdHanNm = cdHanNm;
	}

	public String getCdEngNm() {
		return cdEngNm;
	}

	public void setCdEngNm(String cdEngNm) {
		this.cdEngNm = cdEngNm;
	}

	public String getDetailCd() {
		return detailCd;
	}

	public void setDetailCd(String detailCd) {
		this.detailCd = detailCd;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
}
