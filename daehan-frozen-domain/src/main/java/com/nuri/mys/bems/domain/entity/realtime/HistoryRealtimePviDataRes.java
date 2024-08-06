package com.nuri.mys.bems.domain.entity.realtime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryRealtimePviDataRes")
public class HistoryRealtimePviDataRes {
    @Schema(description = "시간")
    private String time;
    @Schema(description = "장비 ID")
    private String deviceId;
    @Schema(description = "장비명")
    private String deviceNm;
    @Schema(description = "통신 상태")
    private String comYn;
    @Schema(description = "동작 상태(0:정지, 1:대기, 2:준비, 3:운전)")
    private String runState;
    @Schema(description = "트립 상태(0:정상, 1:경고, 2:장애)")
    private String tripState;
    @Schema(description = "발전 상태(0:발전중, 1:미발전중)")
    private String genState;
    @Schema(description = "유효 전력")
    private Double actKw;
    @Schema(description = "무효 전력")
    private Double reactKw;
    @Schema(description = "총 발전량")
    private Double totGenKwh;
    @Schema(description = "DC 전압")
    private Double dcV;
    @Schema(description = "DC 전류")
    private Double dcA;
    @Schema(description = "DC 전력")
    private Double dcKw;
    @Schema(description = "계통 RS상 전압")
    private Double gridVRS;
    @Schema(description = "계통 ST상 전압")
    private Double gridVST;
    @Schema(description = "계통 TR상 전압")
    private Double gridVTR;
    @Schema(description = "계통 R상 전류")
    private Double gridAR;
    @Schema(description = "계통 S상 전류")
    private Double gridAS;
    @Schema(description = "계통 T상 전류")
    private Double gridAT;
    @Schema(description = "내부 온도")
    private Double boxTemp;
    @Schema(description = "외부 온도")
    private Double outTemp;
    @Schema(description = "주파수")
    private Double gridFreq;
    @Schema(description = "역률")
    private Double gridPF;
    @Schema(description = "생성일자")
    private String createDt;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNm() {
        return deviceNm;
    }

    public void setDeviceNm(String deviceNm) {
        this.deviceNm = deviceNm;
    }

    public Double getActKw() {
        return actKw;
    }

    public void setActKw(Double actKw) {
        this.actKw = actKw;
    }

    public Double getReactKw() {
        return reactKw;
    }

    public void setReactKw(Double reactKw) {
        this.reactKw = reactKw;
    }

    public Double getDcV() {
        return dcV;
    }

    public void setDcV(Double dcV) {
        this.dcV = dcV;
    }

    public Double getDcA() {
        return dcA;
    }

    public void setDcA(Double dcA) {
        this.dcA = dcA;
    }

    public Double getDcKw() {
        return dcKw;
    }

    public void setDcKw(Double dcKw) {
        this.dcKw = dcKw;
    }

    public Double getOutTemp() {
        return outTemp;
    }

    public void setOutTemp(Double outTemp) {
        this.outTemp = outTemp;
    }

    public String getComYn() {
        return comYn;
    }

    public void setComYn(String comYn) {
        this.comYn = comYn;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getTripState() {
        return tripState;
    }

    public void setTripState(String tripState) {
        this.tripState = tripState;
    }

    public String getGenState() {
        return genState;
    }

    public void setGenState(String genState) {
        this.genState = genState;
    }

    public Double getGridVRS() {
        return gridVRS;
    }

    public void setGridVRS(Double gridVRS) {
        this.gridVRS = gridVRS;
    }

    public Double getGridVST() {
        return gridVST;
    }

    public void setGridVST(Double gridVST) {
        this.gridVST = gridVST;
    }

    public Double getGridVTR() {
        return gridVTR;
    }

    public void setGridVTR(Double gridVTR) {
        this.gridVTR = gridVTR;
    }

    public Double getGridAR() {
        return gridAR;
    }

    public void setGridAR(Double gridAR) {
        this.gridAR = gridAR;
    }

    public Double getGridAS() {
        return gridAS;
    }

    public void setGridAS(Double gridAS) {
        this.gridAS = gridAS;
    }

    public Double getGridAT() {
        return gridAT;
    }

    public void setGridAT(Double gridAT) {
        this.gridAT = gridAT;
    }

    public Double getBoxTemp() {
        return boxTemp;
    }

    public void setBoxTemp(Double boxTemp) {
        this.boxTemp = boxTemp;
    }

    public Double getGridFreq() {
        return gridFreq;
    }

    public void setGridFreq(Double gridFreq) {
        this.gridFreq = gridFreq;
    }

    public Double getGridPF() {
        return gridPF;
    }

    public void setGridPF(Double gridPF) {
        this.gridPF = gridPF;
    }

    public Double getTotGenKwh() {
        return totGenKwh;
    }

    public void setTotGenKwh(Double totGenKwh) {
        this.totGenKwh = totGenKwh;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
