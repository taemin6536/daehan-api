package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author jmlee
 */
public class ClosedDayRes {
    @Schema(description="날짜")
    private String time;
    @Schema(description="요일 코드")
    private String weekCd;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeekCd() {
        return weekCd;
    }

    public void setWeekCd(String weekCd) {
        this.weekCd = weekCd;
    }
}
