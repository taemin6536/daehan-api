package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author jmlee
 */
@Schema(description = "ManagementOperationCloedDataRes")
public class ManagementHolidayDataRes {
    @Schema(description = "사업장 아이디")
    private String siteId;
    @Schema(description = "공휴일 목록")
    private List<HolidayRes> holidayList;
    @Schema(description = "운휴일 목록")
    private List<ClosedDayRes> closedList;
    @Schema(description = "DataGrid Data Total Count")
    private int dataTotalCount;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public List<HolidayRes> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<HolidayRes> holidayList) {
        this.holidayList = holidayList;
    }

    public List<ClosedDayRes> getClosedList() {
        return closedList;
    }

    public void setClosedList(List<ClosedDayRes> closedList) {
        this.closedList = closedList;
    }

    public int getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(int dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }
}
