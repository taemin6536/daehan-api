package com.nuri.mys.bems.domain.entity.operation;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OperationDeviceBmsDataRes")
public class OperationDeviceBmsDataRes extends OperationDeviceCommonRes{
    @Schema(description = "셀 전압 최소")
    private Double minCellV;
    @Schema(description = "셀 전압 최대")
    private Double maxCellV;
    @Schema(description = "셀 온도 최소")
    private Double minCellT;
    @Schema(description = "셀 온도 최대")
    private Double maxCellT;
    @Schema(description = "배터리 SOC")
    private Double batSoc;
    @Schema(description = "배터리 SOH")
    private Double batSoh;
    @Schema(description = "배터리 DC 전압")
    private Double batV;
    @Schema(description = "배터리 DC 전류")
    private Double batA;
    @Schema(description = "배터리 DC 전력")
    private Double batKw;
    @Schema(description = "충전 전류 제한값")
    private Double chaPowLimit;
    @Schema(description = "방전 전류 제한값")
    private Double disPowLimit;

    public Double getMinCellV() {
        return minCellV;
    }

    public void setMinCellV(Double minCellV) {
        this.minCellV = minCellV;
    }

    public Double getMaxCellV() {
        return maxCellV;
    }

    public void setMaxCellV(Double maxCellV) {
        this.maxCellV = maxCellV;
    }

    public Double getMinCellT() {
        return minCellT;
    }

    public void setMinCellT(Double minCellT) {
        this.minCellT = minCellT;
    }

    public Double getMaxCellT() {
        return maxCellT;
    }

    public void setMaxCellT(Double maxCellT) {
        this.maxCellT = maxCellT;
    }

    public Double getBatSoc() {
        return batSoc;
    }

    public void setBatSoc(Double batSoc) {
        this.batSoc = batSoc;
    }

    public Double getBatSoh() {
        return batSoh;
    }

    public void setBatSoh(Double batSoh) {
        this.batSoh = batSoh;
    }

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

    public Double getChaPowLimit() {
        return chaPowLimit;
    }

    public void setChaPowLimit(Double chaPowLimit) {
        this.chaPowLimit = chaPowLimit;
    }

    public Double getDisPowLimit() {
        return disPowLimit;
    }

    public void setDisPowLimit(Double disPowLimit) {
        this.disPowLimit = disPowLimit;
    }
}
