package com.nuri.mys.bems.service.common;

import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.domain.jwt.entity.User;
import com.nuri.mys.bems.domain.logic.common.CommonLogic;
import com.nuri.mys.bems.domain.entity.common.*;
import com.nuri.mys.bems.domain.store.common.CommonStore;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommonService implements CommonLogic {

	LocalDate fromDate, toDate;
	LocalDateTime fromTime, toTime;

	@Autowired
	private CommonStore commonStore;


	public String getUserId(Authentication authentication) throws ParseException {
		User user = (User)authentication.getPrincipal();
		String id = user.getUserId();
		return id;
	}

	@Override
	public CommonDto periodSearch(CommonDto params){
		String periodTableName = "";
		String dateFrom = params.getDateFrom() == null || params.getDateFrom().isEmpty() ? "" : params.getDateFrom();
		String dateTo = params.getDateTo() == null || params.getDateFrom().isEmpty() ? "" : params.getDateTo();

		try {
			switch (params.getPeriodType()) {
				// 분 (장비 통계, 기간 검색에서만 사용)
				case "minute":
					periodTableName = "minly";

					fromTime = LocalDateTime.of(LocalDate.parse(dateFrom.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.parse(dateFrom.substring(8, 12), DateTimeFormatter.ofPattern(timeFormat)));
					toTime = LocalDateTime.of(LocalDate.parse(dateTo.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.parse(dateTo.substring(8, 12), DateTimeFormatter.ofPattern(timeFormat))).plusMinutes(1);

					params.setDateFrom(fromTime.format(DateTimeFormatter.ofPattern(minlyFormat)));
					params.setDateTo(toTime.format(DateTimeFormatter.ofPattern(minlyFormat)));

					break;
				// 시간
				case "hour":
					periodTableName = "hourly";

					fromTime = LocalDateTime.of(LocalDate.parse(dateFrom.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.parse(dateFrom.substring(8, 12), DateTimeFormatter.ofPattern(timeFormat)));
					toTime = LocalDateTime.of(LocalDate.parse(dateTo.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.parse(dateTo.substring(8, 12), DateTimeFormatter.ofPattern(timeFormat))).plusHours(1);

					params.setDateFrom(fromTime.format(DateTimeFormatter.ofPattern(hourlyFormat)));
					params.setDateTo(toTime.format(DateTimeFormatter.ofPattern(hourlyFormat)));

					break;
				// 일
				case "day":
					periodTableName = "hourly";

					fromTime = LocalDateTime.of(LocalDate.parse(dateFrom.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.MIN);
					toTime = fromTime.plusDays(1);

					params.setDateFrom(fromTime.format(DateTimeFormatter.ofPattern(hourlyFormat)));
					params.setDateTo(toTime.format(DateTimeFormatter.ofPattern(hourlyFormat)));

					break;
				// 월
				case "month":
					periodTableName = "daily";

					fromDate = LocalDate.of(Integer.valueOf(dateFrom.substring(0, 4)), Integer.valueOf(dateFrom.substring(4, 6)), 1);
					toDate = fromDate.plusMonths(1);

					params.setDateFrom(fromDate.format(DateTimeFormatter.ofPattern(dailyFormat)));
					params.setDateTo(toDate.format(DateTimeFormatter.ofPattern(dailyFormat)));
					break;
				// 연
				case "year":
					periodTableName = "monly";

					fromDate = LocalDate.of(Integer.valueOf(dateFrom.substring(0, 4)), 1, 1);
					toDate = LocalDate.of(Integer.valueOf(dateTo.substring(0, 4)), 1, 1).plusYears(1);

					params.setDateFrom(fromDate.format(DateTimeFormatter.ofPattern(monthlyFormat)));
					params.setDateTo(toDate.format(DateTimeFormatter.ofPattern(monthlyFormat)));
					break;
				// 기간
				case "period":
					periodTableName = "daily";

					fromDate = LocalDate.parse(dateFrom.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat));
					toDate = LocalDate.parse(dateTo.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)).plusDays(1);

					params.setDateFrom(fromDate.format(DateTimeFormatter.ofPattern(dailyFormat)));
					params.setDateTo(toDate.format(DateTimeFormatter.ofPattern(dailyFormat)));
					break;
				default:
					throw new RuntimeException("Not Validated periodType. : " + params.getPeriodType());
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		System.out.println("periodTableName : " + periodTableName + ", " + params.getDateFrom() + " ~ " + params.getDateTo());
		params.setPeriodTableName(periodTableName);

		return params;
	}

	@Override
	public CommonPeriodDto periodSearch(CommonPeriodDto params){
		String periodTableName = "";
		String dateFrom = params.getDateFrom() == null || params.getDateFrom().isEmpty() ? "" : params.getDateFrom();
		String dateTo = params.getDateTo() == null || params.getDateFrom().isEmpty() ? "" : params.getDateTo();

		try {
			switch (params.getPeriodType()) {
				// 분 (장비 통계, 기간 검색에서만 사용)
				case "minute":
					periodTableName = "minly";

					fromTime = LocalDateTime.of(LocalDate.parse(dateFrom.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.parse(dateFrom.substring(8, 12), DateTimeFormatter.ofPattern(timeFormat)));
					toTime = LocalDateTime.of(LocalDate.parse(dateTo.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.parse(dateTo.substring(8, 12), DateTimeFormatter.ofPattern(timeFormat))).plusMinutes(1);

					params.setDateFrom(fromTime.format(DateTimeFormatter.ofPattern(minlyFormat)));
					params.setDateTo(toTime.format(DateTimeFormatter.ofPattern(minlyFormat)));

					break;
				// 시간
				case "hour":
					periodTableName = "hourly";

					fromTime = LocalDateTime.of(LocalDate.parse(dateFrom.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.parse(dateFrom.substring(8, 12), DateTimeFormatter.ofPattern(timeFormat)));
					toTime = LocalDateTime.of(LocalDate.parse(dateTo.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.parse(dateTo.substring(8, 12), DateTimeFormatter.ofPattern(timeFormat))).plusHours(1);

					params.setDateFrom(fromTime.format(DateTimeFormatter.ofPattern(hourlyFormat)));
					params.setDateTo(toTime.format(DateTimeFormatter.ofPattern(hourlyFormat)));

					break;
				// 일
				case "day":
					periodTableName = "hourly";

					fromTime = LocalDateTime.of(LocalDate.parse(dateFrom.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)),
							LocalTime.MIN);
					toTime = fromTime.plusDays(1);

					params.setDateFrom(fromTime.format(DateTimeFormatter.ofPattern(hourlyFormat)));
					params.setDateTo(toTime.format(DateTimeFormatter.ofPattern(hourlyFormat)));

					break;
				// 월
				case "month":
					periodTableName = "daily";

					fromDate = LocalDate.of(Integer.valueOf(dateFrom.substring(0, 4)), Integer.valueOf(dateFrom.substring(4, 6)), 1);
					toDate = fromDate.plusMonths(1);

					params.setDateFrom(fromDate.format(DateTimeFormatter.ofPattern(dailyFormat)));
					params.setDateTo(toDate.format(DateTimeFormatter.ofPattern(dailyFormat)));
					break;
				// 연
				case "year":
					periodTableName = "monly";

					fromDate = LocalDate.of(Integer.valueOf(dateFrom.substring(0, 4)), 1, 1);
					toDate = LocalDate.of(Integer.valueOf(dateTo.substring(0, 4)), 1, 1).plusYears(1);

					params.setDateFrom(fromDate.format(DateTimeFormatter.ofPattern(monthlyFormat)));
					params.setDateTo(toDate.format(DateTimeFormatter.ofPattern(monthlyFormat)));
					break;
				// 기간
				case "period":
					periodTableName = "daily";

					fromDate = LocalDate.parse(dateFrom.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat));
					toDate = LocalDate.parse(dateTo.substring(0, 8), DateTimeFormatter.ofPattern(dailyFormat)).plusDays(1);

					params.setDateFrom(fromDate.format(DateTimeFormatter.ofPattern(dailyFormat)));
					params.setDateTo(toDate.format(DateTimeFormatter.ofPattern(dailyFormat)));
					break;
				default:
					throw new RuntimeException("Not Validated periodType. : " + params.getPeriodType());
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		System.out.println("periodTableName : " + periodTableName + ", " + params.getDateFrom() + " ~ " + params.getDateTo());
		params.setPeriodTableName(periodTableName);

		return params;
	}

	@Override
	public List<String> makeChartTicks(CommonDto params) {
		List<LocalDateTime> times = new ArrayList<LocalDateTime>();
		List<LocalDate> days = new ArrayList<LocalDate>();
		List<String> result = new ArrayList<String>();

		LocalDate startDate, endDate;
		LocalDateTime startTime, endTime;

		try {
			switch (params.getPeriodType()) {
				// 분 (장비 통계, 기간 검색에서만 사용)
				case "minute":
					startTime = LocalDateTime.parse(params.getDateFrom(), DateTimeFormatter.ofPattern(minlyFormat));
					endTime = LocalDateTime.parse(params.getDateTo(), DateTimeFormatter.ofPattern(minlyFormat));

					times = Stream.iterate(startTime, t -> t.plusMinutes(1)).limit(ChronoUnit.MINUTES.between(startTime, endTime)).collect(Collectors.toList());

					for (LocalDateTime time : times) {
						result.add(time.format(DateTimeFormatter.ofPattern(minlyFormat)));
					}
					break;
				// 시간
				case "hour":
					// 일
				case "day":
					startTime = LocalDateTime.parse(params.getDateFrom(), DateTimeFormatter.ofPattern(hourlyFormat));
					endTime = LocalDateTime.parse(params.getDateTo(), DateTimeFormatter.ofPattern(hourlyFormat));

					times = Stream.iterate(startTime, t -> t.plusHours(1)).limit(ChronoUnit.HOURS.between(startTime, endTime)).collect(Collectors.toList());

					for (LocalDateTime time : times) {
						result.add(time.format(DateTimeFormatter.ofPattern(hourlyFormat)));
					}
					break;
				// 월
				case "month":
					// 기간
				case "period":
					startDate = LocalDate.parse(params.getDateFrom(), DateTimeFormatter.ofPattern(dailyFormat));
					endDate = LocalDate.parse(params.getDateTo(), DateTimeFormatter.ofPattern(dailyFormat));

					days = Stream.iterate(startDate, t -> t.plusDays(1)).limit(ChronoUnit.DAYS.between(startDate, endDate)).collect(Collectors.toList());

					for (LocalDate day : days) {
						result.add(day.format(DateTimeFormatter.ofPattern(dailyFormat)));
					}
					break;
				// 연
				case "year":
					startDate = LocalDate.parse(params.getDateFrom().concat("01"), DateTimeFormatter.ofPattern(dailyFormat));
					endDate = LocalDate.parse(params.getDateTo().concat("01"), DateTimeFormatter.ofPattern(dailyFormat));

					days = Stream.iterate(startDate, t -> t.plusMonths(1)).limit(ChronoUnit.MONTHS.between(startDate, endDate)).collect(Collectors.toList());

					for (LocalDate day : days) {
						result.add(day.format(DateTimeFormatter.ofPattern(monthlyFormat)));
					}
					break;
				default:
					throw new RuntimeException("Not Validated periodType. : " + params.getPeriodType());
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
	}

	@Override
	public List<String> makeChartTicks(CommonPeriodDto params) {
		List<LocalDateTime> times = new ArrayList<LocalDateTime>();
		List<LocalDate> days = new ArrayList<LocalDate>();
		List<String> result = new ArrayList<String>();

		LocalDate startDate, endDate;
		LocalDateTime startTime, endTime;

		try {
			switch (params.getPeriodType()) {
				// 분 (장비 통계, 기간 검색에서만 사용)
				case "minute":
					startTime = LocalDateTime.parse(params.getDateFrom(), DateTimeFormatter.ofPattern(minlyFormat));
					endTime = LocalDateTime.parse(params.getDateTo(), DateTimeFormatter.ofPattern(minlyFormat));

					times = Stream.iterate(startTime, t -> t.plusMinutes(1)).limit(ChronoUnit.MINUTES.between(startTime, endTime)).collect(Collectors.toList());

					for (LocalDateTime time : times) {
						result.add(time.format(DateTimeFormatter.ofPattern(minlyFormat)));
					}
					break;
				// 시간
				case "hour":
					// 일
				case "day":
					startTime = LocalDateTime.parse(params.getDateFrom(), DateTimeFormatter.ofPattern(hourlyFormat));
					endTime = LocalDateTime.parse(params.getDateTo(), DateTimeFormatter.ofPattern(hourlyFormat));

					times = Stream.iterate(startTime, t -> t.plusHours(1)).limit(ChronoUnit.HOURS.between(startTime, endTime)).collect(Collectors.toList());

					for (LocalDateTime time : times) {
						result.add(time.format(DateTimeFormatter.ofPattern(hourlyFormat)));
					}
					break;
				// 월
				case "month":
					// 기간
				case "period":
					startDate = LocalDate.parse(params.getDateFrom(), DateTimeFormatter.ofPattern(dailyFormat));
					endDate = LocalDate.parse(params.getDateTo(), DateTimeFormatter.ofPattern(dailyFormat));

					days = Stream.iterate(startDate, t -> t.plusDays(1)).limit(ChronoUnit.DAYS.between(startDate, endDate)).collect(Collectors.toList());

					for (LocalDate day : days) {
						result.add(day.format(DateTimeFormatter.ofPattern(dailyFormat)));
					}
					break;
				// 연
				case "year":
					startDate = LocalDate.parse(params.getDateFrom().concat("01"), DateTimeFormatter.ofPattern(dailyFormat));
					endDate = LocalDate.parse(params.getDateTo().concat("01"), DateTimeFormatter.ofPattern(dailyFormat));

					days = Stream.iterate(startDate, t -> t.plusMonths(1)).limit(ChronoUnit.MONTHS.between(startDate, endDate)).collect(Collectors.toList());

					for (LocalDate day : days) {
						result.add(day.format(DateTimeFormatter.ofPattern(monthlyFormat)));
					}
					break;
				default:
					throw new RuntimeException("Not Validated periodType. : " + params.getPeriodType());
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
	}

	@Override
	public List<CommonCodeRes> getCommonDeviceInfo() {
		return commonStore.getCommonDeviceInfo();
	}

	@Override
	public List<CommonDeviceDetailRes> getCommonDeviceDetailInfo(CommonDeviceDetailDto params) {
		return commonStore.getCommonDeviceDetailInfo(params);
	}

	@Override
	public List<CommonCodeRes> getEventLevel() {
		return commonStore.getEventLevel();
	}

	@Override
	public List<CommonCodeRes> getEventStatus() {
		return commonStore.getEventStatus();
	}
}
