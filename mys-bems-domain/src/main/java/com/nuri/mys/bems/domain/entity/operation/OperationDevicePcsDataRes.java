package com.nuri.mys.bems.domain.entity.operation;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OperationDevicePcsDataRes")
public class OperationDevicePcsDataRes extends OperationDeviceCommonRes{
    @Schema(description = "유효 전력")
    private Double actKw;
    @Schema(description = "무효 전력")
    private Double reactKw;
    @Schema(description = "배터리 전압")
    private Double batV;
    @Schema(description = "배터리 전류")
    private Double batA;
    @Schema(description = "배터리 전력")
    private Double batKw;
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
    @Schema(description = "DC 링크 전류")
    private Double dclinkA;
    @Schema(description = "DC 링크 전압")
    private Double dclinkV;
    @Schema(description = "내부 온도")
    private Double boxTemp;
    @Schema(description = "외부 온도")
    private Double outTemp;
    @Schema(description = "역율")
    private Double gridPF;
    @Schema(description = "주파수")
    private Double gridFreq;
    @Schema(description = "총 방전량")
    private Double totGridKwhOut;
    @Schema(description = "총 충전량")
    private Double totGridKwhIn;

    public Double getBatV() {
        return batV;
    }

    public void setBatV(Double batV) {
        this.batV = batV;
    }

    public Double getBatA() {
        return batA;
    }

    public void setBatA(Double batA) {
        this.batA = batA;
    }

    public Double getBatKw() {
        return batKw;
    }

    public void setBatKw(Double batKw) {
        this.batKw = batKw;
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

    public Double getDclinkV() {
        return dclinkV;
    }

    public void setDclinkV(Double dclinkV) {
        this.dclinkV = dclinkV;
    }

    public Double getBoxTemp() {
        return boxTemp;
    }

    public void setBoxTemp(Double boxTemp) {
        this.boxTemp = boxTemp;
    }

    public Double getOutTemp() {
        return outTemp;
    }

    public void setOutTemp(Double outTemp) {
        this.outTemp = outTemp;
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

    public Double getDclinkA() {
        return dclinkA;
    }

    public void setDclinkA(Double dclinkA) {
        this.dclinkA = dclinkA;
    }

    public Double getGridPF() {
        return gridPF;
    }

    public void setGridPF(Double gridPF) {
        this.gridPF = gridPF;
    }

    public Double getGridFreq() {
        return gridFreq;
    }

    public void setGridFreq(Double gridFreq) {
        this.gridFreq = gridFreq;
    }

    public Double getTotGridKwhOut() {
        return totGridKwhOut;
    }

    public void setTotGridKwhOut(Double totGridKwhOut) {
        this.totGridKwhOut = totGridKwhOut;
    }

    public Double getTotGridKwhIn() {
        return totGridKwhIn;
    }

    public void setTotGridKwhIn(Double totGridKwhIn) {
        this.totGridKwhIn = totGridKwhIn;
    }
}
