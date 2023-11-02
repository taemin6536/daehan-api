package com.nuri.mys.bems.domain.entity.operation;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OperationDevicePviDataRes")
public class OperationDevicePviDataRes extends OperationDeviceCommonRes{
    @Schema(description = "DC 전압")
    private Double dcV;
    @Schema(description = "DC 전류")
    private Double dcA;
    @Schema(description = "DC 전력")
    private Double dcKw;
    @Schema(description = "유효 전력")
    private Double actKw;
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
    @Schema(description = "역율")
    private Double gridPF;
    @Schema(description = "주파수")
    private Double gridFreq;

    public Double getActKw() {
        return actKw;
    }

    public void setActKw(Double actKw) {
        this.actKw = actKw;
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

    public Double getOutTemp() {
        return outTemp;
    }

    public void setOutTemp(Double outTemp) {
        this.outTemp = outTemp;
    }

    public Double getBoxTemp() {
        return boxTemp;
    }

    public void setBoxTemp(Double boxTemp) {
        this.boxTemp = boxTemp;
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
}
