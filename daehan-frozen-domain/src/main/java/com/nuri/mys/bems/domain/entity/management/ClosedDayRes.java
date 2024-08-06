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
    @Schema(description="운휴일 설명")
    private String holidDesc;
    @Schema(description="공휴일 여부")
    private String legalHolidYn;

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

    public String getHolidDesc() {
        return holidDesc;
    }

    public void setHolidDesc(String holidDesc) {
        this.holidDesc = holidDesc;
    }

    public String getLegalHolidYn() {
        return legalHolidYn;
    }

    public void setLegalHolidYn(String legalHolidYn) {
        this.legalHolidYn = legalHolidYn;
    }
}
