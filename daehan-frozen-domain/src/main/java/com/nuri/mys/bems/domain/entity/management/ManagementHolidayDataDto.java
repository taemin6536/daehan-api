package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author jmlee
 */
@Schema(description = "ManagementHolidayDataDto")
public class ManagementHolidayDataDto {
    @Schema(description = "사업장 아이디")
    private String siteId;
    @Schema(description="년도")
    private String year;
    @Schema(description="월")
    private String month;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
