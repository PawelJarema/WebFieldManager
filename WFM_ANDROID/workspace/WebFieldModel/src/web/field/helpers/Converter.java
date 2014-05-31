package web.field.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Converter {

	public static String secondsToDateString(long seconds) {
		long millis = seconds * 1000;
		Date date = new Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String formattedDate = sdf.format(date);

		return formattedDate;
	}

	public static long dateToSeconds(Date date) {
		return date.getTime() / 1000;
	}

	public static long stringDateToSeconds(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.ENGLISH);
		Date parsed = sdf.parse(date);

		return dateToSeconds(parsed);
	}

	public static String formatDecimal(double number) {
		float epsilon = 0.004f; // 4 tenths of a cent
		if (Math.abs(Math.round(number) - number) < epsilon) {
			return String.format("%10.0f", number);
		} else {
			return String.format("%10.2f", number);
		}
	}

	public static double percentToDouble(double percent) {
		return percent / 100;
	}

}
