package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author jmlee
 */
@Schema(description = "ManagementSaveLegalHolidayDto")
public class ManagementSaveLegalHolidayDto {
    @NotBlank
    @Schema(required = true, description = "공휴일 날짜", example="0101")
    private String holiday;
    @NotBlank
    @Schema(required = true, description = "공휴일 설명", example="신정")
    private String holidayDesc;
    @NotBlank
    @Schema(required = true, description = "음력 여부", example="n")
    private String lunarYn;

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public String getLunarYn() {
        return lunarYn;
    }

    public void setLunarYn(String lunarYn) {
        this.lunarYn = lunarYn;
    }
}
