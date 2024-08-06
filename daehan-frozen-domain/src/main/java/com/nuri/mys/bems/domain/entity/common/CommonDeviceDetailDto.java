package com.nuri.mys.bems.domain.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "CommonDeviceDetailDto")
public class CommonDeviceDetailDto{
	@NotBlank
	@Pattern(regexp="^(\\d{3})")
	@Schema(required = true, description = "장비그룹 ID, 3자리 숫자로 제한한다.", example="100")
	private String grId;

	public String getGrId() {
		return grId;
	}

	public void setGrId(String grId) {
		this.grId = grId;
	}
}
