package com.nuri.mys.bems.service.dashboard;

import java.text.SimpleDateFormat;
import java.util.*;

import com.nuri.mys.bems.domain.entity.dashboard.DashboardWeatherDataRes;
import com.nuri.mys.bems.domain.store.dashboard.DashboardStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * 위도 경도 샘플
 * 37.052051, 126.441279
 * @author lsh
 *
 */
@Service
public class Sunset {
	private static final Logger log = LoggerFactory.getLogger( Sunset.class);
	
	// 원주율
	private static final double PI = 3.141592;
	// 위도
//	@Value("${sun.lati}")
	private double latitude;

	// 경도 
//	@Value("${sun.longi}")
	private double longitude;

	@Autowired
	private DashboardStore dashboardStore;
	
	/**
	 * 위도는 + , 경도는 - 값입력 (구글 지도에서는 경도는 +)
	 * @throws Exception
	 */
	public DashboardWeatherDataRes operation() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		DashboardWeatherDataRes params = dashboardStore.getSiteInfo();
		log.info("[SUN] saves starting ...");

		latitude = Double.parseDouble(params.getGpsY());
		longitude = Double.parseDouble(params.getGpsX()) * (-1);
		//		double latitude, longitude, lst;
		SimpleDateFormat SDF_SEC = new SimpleDateFormat("yyyyMMddHHmmss");
		try
		{
			Calendar ct = Calendar.getInstance();

			//일출 sunRise
			getSunriseTime(ct, latitude, longitude, -9, 0);
			Date date = ct.getTime();
			String riseTime = new SimpleDateFormat("HH:mm", new Locale("en", "US")).format(date);
			log.info("[SUN] sun rise : " + new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(date));

			//일몰 sunSet
			getSunsetTime(ct, latitude, longitude, -9, 0);
			Date date1 = ct.getTime();
			String setTime = new SimpleDateFormat("HH:mm", new Locale("en", "US")).format(date1);
			log.info("[SUN] sun set : " + new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(date1));

			params.setRiseTime(riseTime);
			params.setSetTime(setTime);
		}
		catch(Exception e)
		{
			log.error("[SUN] saves error : " + e);
		}
		log.info("[SUN] saves ending ...");
		return params;
	}
	
	protected boolean IsLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}

	private int getLastDay(int uiYear, int ucMonth) {
		switch (ucMonth) {
		case 2: // 2월
			if ((uiYear % 4) == 0) { // 4로 나누어 떨어지는 해는 윤년임.
				// common year
				if (uiYear % 100 == 0) { // 그중에서 100으로 나누어 떨어지는 해는 평년임
					// leap year
					if (uiYear % 400 == 0)
						return 29; // 그중에서 400으로 나누어 떨어지는 해는 윤년임.
					return 28; // 평년
				}
				return 29; // 윤년
			}
			return 28; // else 평년
		case 4:
		case 6:
		case 9:
		case 11: // 4, 6, 9, 11월
			return 30; // 30일
		}

		return 31; // 그외 31일
	}

	private int calcJulianDay(int uiYear, int ucMonth, int ucDay) {
		int i;
		int iJulDay;
		iJulDay = 0;
		for (i = 1; i < ucMonth; i++) {
			iJulDay += getLastDay(uiYear, i);
		}
		iJulDay += ucDay;

		return iJulDay;
	}

	private double calcGamma(int iJulDay) {
		return (2.0 * PI / 365.0) * (iJulDay - 1);
	}

	private double calcGamma2(int iJulDay, int hour) {
		return (2.0 * PI / 365.0) * (iJulDay - 1 + (hour / 24.0));
	}

	// Return the equation of time value for the given date.
	private double calcEqofTime(double gamma) {
		return (229.18 * (0.000075 + 0.001868 * Math.cos(gamma) - 0.032077 * Math.sin(gamma)
				- 0.014615 * Math.cos(2 * gamma) - 0.040849 * Math.sin(2 * gamma)));

	}

	// Return the solar declination angle (in radians) for the given date.
	private double calcSolarDec(double gamma) {
		return (0.006918 - 0.399912 * Math.cos(gamma) + 0.070257 * Math.sin(gamma) - 0.006758 * Math.cos(2 * gamma)
				+ 0.000907 * Math.sin(2 * gamma));
	}

	private double degreeToRadian(double angleDeg) {
		return (PI * angleDeg / 180.0);
	}

	private double radianToDegree(double angleRad) {
		return (180 * angleRad / PI);
	}

	private double calcHourAngle(double latitude, double solarDec, int time) {
		double latRad = degreeToRadian(latitude);
		double hour_angle = Math.acos(Math.cos(degreeToRadian(90.833)) / (Math.cos(latRad) * Math.cos(solarDec))
				- Math.tan(latRad) * Math.tan(solarDec));
		if (time == 1) {
			return hour_angle;
		} else if (time == 0) {
			return -hour_angle;
		}
		return 0;
	}

	private double calcSunriseGMT(int iJulDay, double latitude, double longitude) {
		double gamma = calcGamma(iJulDay);
		double eqTime = calcEqofTime(gamma);
		double solarDec = calcSolarDec(gamma);
		double hourAngle = calcHourAngle(latitude, solarDec, 1);
		double delta = longitude - radianToDegree(hourAngle);
		double timeDiff = 4.0 * delta;
		double timeGMT = 720.0 + timeDiff - eqTime;
		double gamma_sunrise = calcGamma2(iJulDay, (int) (timeGMT / 60.0));
		eqTime = calcEqofTime(gamma_sunrise);
		solarDec = calcSolarDec(gamma_sunrise);
		hourAngle = calcHourAngle(latitude, solarDec, 1);
		delta = longitude - radianToDegree(hourAngle);
		timeDiff = 4.0 * delta;
		timeGMT = 720.0 + timeDiff - eqTime;

		return timeGMT;
	}

	private double calcSunsetGMT(int iJulDay, double latitude, double longitude) {
		// First calculates sunrise and approx length of day
		double gamma = calcGamma(iJulDay + 1);
		double eqTime = calcEqofTime(gamma);
		double solarDec = calcSolarDec(gamma);
		double hourAngle = calcHourAngle(latitude, solarDec, 0);
		double delta = longitude - radianToDegree(hourAngle);
		double timeDiff = 4.0 * delta;
		double setTimeGMT = 720.0 + timeDiff - eqTime;
		// first pass used to include fractional day in gamma calc
		double gamma_sunset = calcGamma2(iJulDay, (int) (setTimeGMT / 60.0));
		eqTime = calcEqofTime(gamma_sunset);
		solarDec = calcSolarDec(gamma_sunset);
		hourAngle = calcHourAngle(latitude, solarDec, 0);
		delta = longitude - radianToDegree(hourAngle);
		timeDiff = 4.0 * delta;
		setTimeGMT = 720.0 + timeDiff - eqTime; // in minutes
		return setTimeGMT;
	}

	// timeString returns a zero-padded string given time in minutes
	private void getTimeString(double minutes) {
		double floatHour = minutes / 60.0;
		double hour = Math.floor(floatHour);
		double floatMinute = 60.0 * (floatHour - Math.floor(floatHour));
		double minute = Math.floor(floatMinute);
		double floatSec = 60.0 * (floatMinute - Math.floor(floatMinute));
		double second = Math.floor(floatSec);

		System.out.println(" " + hour + "시 " + minute + "분 " + second + "초");
	}

	protected Calendar getTimeString(double minutes, Calendar date) {
		double floatHour = minutes / 60.0;
		double hour = Math.floor(floatHour);
		double floatMinute = 60.0 * (floatHour - Math.floor(floatHour));
		double minute = Math.floor(floatMinute);
		double floatSec = 60.0 * (floatMinute - Math.floor(floatMinute));
		double second = Math.floor(floatSec);

		date.set(Calendar.HOUR_OF_DAY, (int) hour);
		date.set(Calendar.MINUTE, (int) minute);
		date.set(Calendar.SECOND, (int) second);

		return date;
	}

	public double getSunriseTime(int year, int month, int day, double latitude, double longitude, int zone,
			int daySavings) {
		int julday = calcJulianDay(year, month, day);
		double timeLST = calcSunriseGMT(julday, latitude, longitude) - (60.0 * zone) + daySavings; // minutes
		return timeLST;
	}

	public Calendar getSunriseTime(Calendar date, double latitude, double longitude, int zone, int daySavings) {
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		int julday = calcJulianDay(year, month, day);
		double timeLST = calcSunriseGMT(julday, latitude, longitude) - (60.0 * zone) + daySavings; // minutes

		date = getTimeString(timeLST, date);

		return date;
	}

	public Calendar getSunsetTime(Calendar date, double latitude, double longitude, int zone, int daySavings) {
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		int julday = calcJulianDay(year, month, day);
		double timeLST = calcSunsetGMT(julday, latitude, longitude) - (60.0 * zone) + 0;

		date = getTimeString(timeLST, date);

		return date;
	}
}
