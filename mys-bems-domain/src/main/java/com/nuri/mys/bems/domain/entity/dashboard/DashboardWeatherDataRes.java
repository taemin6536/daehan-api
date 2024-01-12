package com.nuri.mys.bems.domain.entity.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DashboardWeatherDataRes")
public class DashboardWeatherDataRes {
    @Schema(description = "일출시간")
    private String riseTime;
    @Schema(description = "일몰시간")
    private String setTime;
    @Schema(description = "날씨 설명")
    private String weatherDesc;
    @Schema(description = "아이콘")
    private String icon;
    @Schema(description="온도")
    private double temp;
    @Schema(description="습도")
    private double humidity;
    @Schema(description="수평 일사량")
    private double horiRadiation;
    @Schema(description="경사 일사량")
    private double inclRadiation;
    @Schema(description="기압")
    private int pressure;
    @Schema(description="풍향")
    private String windDirection;
    @Schema(description="풍속")
    private String windSpeed;
    @Schema(description = "좌표 X", hidden = true)
    private String gpsX;
    @Schema(description = "좌표 Y", hidden = true)
    private String gpsY;

    public String getRiseTime() {
        return riseTime;
    }

    public void setRiseTime(String riseTime) {
        this.riseTime = riseTime;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getHoriRadiation() {
        return horiRadiation;
    }

    public void setHoriRadiation(double horiRadiation) {
        this.horiRadiation = horiRadiation;
    }

    public double getInclRadiation() {
        return inclRadiation;
    }

    public void setInclRadiation(double inclRadiation) {
        this.inclRadiation = inclRadiation;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getGpsX() {
        return gpsX;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }

    public String getGpsY() {
        return gpsY;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }
}

