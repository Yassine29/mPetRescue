package com.yassine.mpetrescue.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static LocalDate convertToDate(String dbDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantsSetting.DB_DATE_FORMAT);

		LocalDate localDate = LocalDate.parse(dbDate, formatter);

		return localDate;

	}

}
