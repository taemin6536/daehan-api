package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementConnectionHistoryRes")
public class ManagementConnectionHistoryRes{

	@Schema(description = "사용자 아이디")
	private String userId;
	@Schema(description = "사용자 명")
	private String userNm;
	@Schema(description = "권한 그룹 ID")
	private String permGrId;
	@Schema(description = "권한그룹 명")
	private String permGrNm;
	@Schema(description = "로그인 IP")
	private String loginIp;
	@Schema(description = "로그인 일시")
	private String loginDt;
	@Schema(description = "로그인 구분")
	private String loginGb;
	@Schema(description = "데이터 총 갯수")
	private int dataTotalCount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPermGrId() {
		return permGrId;
	}

	public void setPermGrId(String permGrId) {
		this.permGrId = permGrId;
	}

	public String getPermGrNm() {
		return permGrNm;
	}

	public void setPermGrNm(String permGrNm) {
		this.permGrNm = permGrNm;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginDt() {
		return loginDt;
	}

	public void setLoginDt(String loginDt) {
		this.loginDt = loginDt;
	}

	public String getLoginGb() {
		return loginGb;
	}

	public void setLoginGb(String loginGb) {
		this.loginGb = loginGb;
	}

	public int getDataTotalCount() {
		return dataTotalCount;
	}

	public void setDataTotalCount(int dataTotalCount) {
		this.dataTotalCount = dataTotalCount;
	}
}
