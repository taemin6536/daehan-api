package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 * @author jmlee
 */
@Schema(description = "ManagementDeleteHolidayDataDto")
public class ManagementDeleteHolidayDataDto extends ManagementHolidayCommonDto{
    @NotBlank
    @Schema(required = true, description = "운휴일", example="20230712")
    private String closedDay;

    public String getClosedDay() {
        return closedDay;
    }

    public void setClosedDay(String closedDay) {
        this.closedDay = closedDay;
    }

}
