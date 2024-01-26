package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author jmlee
 */
@Schema(description = "ManagementSaveHolidayDataDto")
public class ManagementSaveHolidayDataDto extends ManagementHolidayCommonDto{
    @Schema(description="년도")
    private String year;
    @Schema(description="월")
    private String month;
    @Schema(description="공휴일 포함 체크 여부(1: 포함, 0: 미포함)", example="0")
    private String legalHolidayChk;
    @Schema(description="요일 배열", example="['1', '2', '3']")
    private String[] weekArr;
    @Schema(description="지정 운휴 일자 배열(오늘 날짜 이후로 등록할 모든 운휴일)", example="[20230710, 20230711]")
    private String[] closedDayArr;
    @Schema(description="지정 운휴 일자", example="20240131")
    private String closedDay;
    @Schema(description="법정공휴일 지정 여부", example="N")
    private String legalHolidayYn;
    @Schema(description="공휴일 배열", hidden=true)
    private List<ClosedDayRes> holidayList;
    @Schema(description="오늘 날짜", hidden=true)
    private String today;

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


    public String getLegalHolidayChk() {
        return legalHolidayChk;
    }

    public void setLegalHolidayChk(String legalHolidayChk) {
        this.legalHolidayChk = legalHolidayChk;
    }

    public String[] getWeekArr() {
        return weekArr;
    }

    public void setWeekArr(String[] weekArr) {
        this.weekArr = weekArr;
    }

    public String[] getClosedDayArr() {
        return closedDayArr;
    }

    public void setClosedDayArr(String[] closedDayArr) {
        this.closedDayArr = closedDayArr;
    }

    public String getClosedDay() {
        return closedDay;
    }

    public void setClosedDay(String closedDay) {
        this.closedDay = closedDay;
    }

    public String getLegalHolidayYn() {
        return legalHolidayYn;
    }

    public void setLegalHolidayYn(String legalHolidayYn) {
        this.legalHolidayYn = legalHolidayYn;
    }

    public List<ClosedDayRes> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<ClosedDayRes> holidayList) {
        this.holidayList = holidayList;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

}
