package com.nuri.mys.bems.service.control;

/**
 * @author jmlee
 */

public class Event {
    private String evtCode;
    private int evtValue;
    private String evtLevel;
    private String evtName;
    private String evtDesc;
    private String evtGrid;
    private String startTime;
    private String endTime;

    public String getEvtCode() {
        return evtCode;
    }

    public void setEvtCode(String evtCode) {
        this.evtCode = evtCode;
    }

    public int getEvtValue() {
        return evtValue;
    }

    public void setEvtValue(int evtValue) {
        this.evtValue = evtValue;
    }

    public String getEvtLevel() {
        return evtLevel;
    }

    public void setEvtLevel(String evtLevel) {
        this.evtLevel = evtLevel;
    }

    public String getEvtName() {
        return evtName;
    }

    public void setEvtName(String evtName) {
        this.evtName = evtName;
    }

    public String getEvtDesc() {
        return evtDesc;
    }

    public void setEvtDesc(String evtDesc) {
        this.evtDesc = evtDesc;
    }

    public String getEvtGrid() {
        return evtGrid;
    }

    public void setEvtGrid(String evtGrid) {
        this.evtGrid = evtGrid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
