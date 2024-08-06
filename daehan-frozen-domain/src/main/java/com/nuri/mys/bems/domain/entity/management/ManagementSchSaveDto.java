package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "ManagementSchSaveDto")
public class ManagementSchSaveDto extends ManagementSchCommonDto{

    @Schema(required = true, description = "선택 요일 배열", example = "[1, 2, 3]")
    private String[] weekArr;
    @Schema(required = true, description = "장비그룹 ID, 3자리 숫자로 제한한다.", example = "100", maxLength = 10, hidden = true)
    private String grId;
    @Schema(required = true, description = "장비 명", example = "PCS-1", maxLength = 255, hidden = true)
    private String deviceNm;
    @Schema(required = true, description = "충방전 상태", example = "1", maxLength = 2)
    @NotBlank
    private String chadiStatus;
    @Schema(required = true, description = "충방전 전력량", example = "200")
    @NotNull
    private double chadiKw;
    @Schema(required = true, description = "시작 시간", example = "06", maxLength = 2)
    @NotBlank
    private String startHh;
    @Schema(required = true, description = "시작 분", example = "00", maxLength = 2)
    @NotBlank
    private String startMi;
    @Schema(required = true, description = "종료 시간", example = "08", maxLength = 2)
    @NotBlank
    private String endHh;
    @Schema(required = true, description = "종료 분", example = "00", maxLength = 2)
    @NotBlank
    private String endMi;

    public String[] getWeekArr() {
        return weekArr;
    }

    public void setWeekArr(String[] weekArr) {
        this.weekArr = weekArr;
    }

    public String getGrId() {
        return grId;
    }

    public void setGrId(String grId) {
        this.grId = grId;
    }

    public String getDeviceNm() {
        return deviceNm;
    }

    public void setDeviceNm(String deviceNm) {
        this.deviceNm = deviceNm;
    }

    public String getChadiStatus() {
        return chadiStatus;
    }

    public void setChadiStatus(String chadiStatus) {
        this.chadiStatus = chadiStatus;
    }

    public double getChadiKw() {
        return chadiKw;
    }

    public void setChadiKw(double chadiKw) {
        this.chadiKw = chadiKw;
    }

    public String getStartHh() {
        return startHh;
    }

    public void setStartHh(String startHh) {
        this.startHh = startHh;
    }

    public String getStartMi() {
        return startMi;
    }

    public void setStartMi(String startMi) {
        this.startMi = startMi;
    }

    public String getEndHh() {
        return endHh;
    }

    public void setEndHh(String endHh) {
        this.endHh = endHh;
    }

    public String getEndMi() {
        return endMi;
    }

    public void setEndMi(String endMi) {
        this.endMi = endMi;
    }

}
