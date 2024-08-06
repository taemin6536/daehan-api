package com.nuri.mys.bems.domain.entity.control;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ControlTableDataRes")
public class ControlTableDataRes {
    @Schema(description = "시간")
    private String time;
    @Schema(description = "사용자")
    private String userId;
    @Schema(description = "사용자명")
    private String userNm;
    @Schema(description = "장비그룹명")
    private String grNm;
    @Schema(description = "장비명")
    private String deviceNm;
    @Schema(description = "메시지")
    private String description;
    @Schema(description = "구분")
    private String classification;
    @Schema(description = "권한")
    private String permGrNm;
    @Schema(description = "DataGrid Data Total Count")
    private int dataTotalCount;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGrNm() {
        return grNm;
    }

    public void setGrNm(String grNm) {
        this.grNm = grNm;
    }

    public String getDeviceNm() {
        return deviceNm;
    }

    public void setDeviceNm(String deviceNm) {
        this.deviceNm = deviceNm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(int dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getPermGrNm() {
        return permGrNm;
    }

    public void setPermGrNm(String permGrNm) {
        this.permGrNm = permGrNm;
    }
}
