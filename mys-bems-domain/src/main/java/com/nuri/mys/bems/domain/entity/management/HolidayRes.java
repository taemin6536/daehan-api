package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author jmlee
 */
public class HolidayRes {
    @Schema(description="날짜")
    private String time;
    @Schema(description="공휴일 설명")
    private String holidDesc;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHolidDesc() {
        return holidDesc;
    }

    public void setHolidDesc(String holidDesc) {
        this.holidDesc = holidDesc;
    }
}
