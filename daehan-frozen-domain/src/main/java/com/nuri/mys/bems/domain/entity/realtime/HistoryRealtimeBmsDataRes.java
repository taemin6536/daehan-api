package com.nuri.mys.bems.domain.entity.realtime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HistoryRealtimeBmsDataRes")
public class HistoryRealtimeBmsDataRes {
    @Schema(description = "시간")
    private String time;
    @Schema(description = "장비 ID")
    private String deviceId;
    @Schema(description = "장비 명")
    private String deviceNm;
    @Schema(description = "통신 상태")
    private String comYn;
    @Schema(description = "")
    private String relayState;
    @Schema(description = "트립 상태")
    private String tripState;
    @Schema(description = "충방전 상태")
    private String chargeState;
    @Schema(description = "배터리 DC 전압")
    private Double batV;
    @Schema(description = "배터리 DC 전류")
    private Double batA;
    @Schema(description = "배터리 DC 전력")
    private Double batKw;
    @Schema(description = "배터리 SOC")
    private Double batSoc;
    @Schema(description = "배터리 SOH")
    private Double batSoh;
    @Schema(description = "셀 전압 최소")
    private Double minCellV;
    @Schema(description = "셀 전압 최대")
    private Double maxCellV;
    @Schema(description = "셀 온도 최소")
    private Double minCellT;
    @Schema(description = "셀 온도 최대")
    private Double maxCellT;
    @Schema(description = "충전 전류 제한값")
    private Double chaPowLimit;
    @Schema(description = "방전 전류 제한값")
    private Double disPowLimit;
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

    public String getComYn() {
        return comYn;
    }

    public void setComYn(String comYn) {
        this.comYn = comYn;
    }

    public String getRelayState() {
        return relayState;
    }

    public void setRelayState(String relayState) {
        this.relayState = relayState;
    }

    public String getTripState() {
        return tripState;
    }

    public void setTripState(String tripState) {
        this.tripState = tripState;
    }

    public String getChargeState() {
        return chargeState;
    }

    public void setChargeState(String chargeState) {
        this.chargeState = chargeState;
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

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
