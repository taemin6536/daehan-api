package com.nuri.mys.bems.domain.entity.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DashboardTodayEventDataRes")
public class DashboardTodayEventDataRes {
    @Schema(description = "발생시간")
    private String startTime;
    @Schema(description = "장비아이디")
    private String deviceId;
    @Schema(description = "장비명")
    private String deviceNm;
    @Schema(description = "이벤트 수위 코드")
    private String eventLvl;
    @Schema(description = "이벤트 수위 명")
    private String eventLvlNm;
    @Schema(description = "이벤트명")
    private String eventNm;
    @Schema(description = "이벤트 상태 코드")
    private String eventStatusCd;
    @Schema(description = "이벤트 상태 명")
    private String eventStatusNm;
    @Schema(description = "DataGrid Data Total Count")
    private int dataTotalCount;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getEventNm() {
        return eventNm;
    }

    public void setEventNm(String eventNm) {
        this.eventNm = eventNm;
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

    public int getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(int dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }
}
