package com.nuri.mys.bems.domain.entity.management;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * @author jmlee
 */
@Schema(description = "ManagementLegalHolidayTableRes")
public class ManagementLegalHolidayTableRes extends ManagementSaveLegalHolidayDto {
    @Schema(description = "생성시간")
    private Date createDt;
    @Schema(description = "수정 일시")
    private String updateDt;
    @Schema(description = "데이터 총 갯수")
    private int dataTotalCount;

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }

    public int getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(int dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }
}
