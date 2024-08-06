package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ManagementSchDataRes")
public class ManagementSchDataRes {

    @Schema(required = true, description = "시퀀스")
    private int schSeq;
    @Schema(required = true, description = "사이트 아이디")
    private String siteId;
    @Schema(required = true, description = "장비 아이디")
    private String deviceId;
    @Schema(required = true, description = "장비 명")
    private String deviceNm;
    @Schema(required = true, description = "계절")
    private String seasonNm;
    @Schema(required = true, description = "요일")
    private String weekNm;
    @Schema(required = true, description = "충방전 상태")
    private String chadiStatusNm;
    @Schema(required = true, description = "충방전 전력량")
    private String chadiKw;
    @Schema(description = "시작 시간")
    private String startHh;
    @Schema(description = "시작 분")
    private String startMi;
    @Schema(description = "종료 시간")
    private String endHh;
    @Schema(description = "종료 분")
    private String endMi;
    @Schema(required = true, description = "시작시간:시작분")
    private String startTime;
    @Schema(required = true, description = "종료시간:종류분")
    private String endTime;
    @Schema(description = "데이터 총 갯수")
    private int dataTotalCount;

    public int getSchSeq() {
        return schSeq;
    }

    public void setSchSeq(int schSeq) {
        this.schSeq = schSeq;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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

    public String getSeasonNm() {
        return seasonNm;
    }

    public void setSeasonNm(String seasonNm) {
        this.seasonNm = seasonNm;
    }

    public String getWeekNm() {
        return weekNm;
    }

    public void setWeekNm(String weekNm) {
        this.weekNm = weekNm;
    }

    public String getChadiStatusNm() {
        return chadiStatusNm;
    }

    public void setChadiStatusNm(String chadiStatusNm) {
        this.chadiStatusNm = chadiStatusNm;
    }

    public String getChadiKw() {
        return chadiKw;
    }

    public void setChadiKw(String chadiKw) {
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

    public int getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(int dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }
}
