package web.field.helpers;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Converter {
	
	private static String CULTURE_MONEY = "EUR"; // ISO 4217
	
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

	private static String determineFormat(double number, float epsilon)
	{
		if (Math.abs(Math.round(number) - number) < epsilon) {
			return "%10.0f";
		} else {
			return "%10.2f";
		}
	}
	
	public static String formatDecimal(double number) {
		float epsilon = 0.004f; // 4 tenths of a cent
		return String.format(
				determineFormat(number, epsilon),
				number);
	}
	
	// view helpers
	public static String formatMoney(double amount) {
		String format = determineFormat(amount, 0.004f);
		Currency localCurrency = Currency.getInstance(CULTURE_MONEY);
		return String.format(format, amount) + " " + localCurrency.getSymbol();
	}

	public static double percentToDouble(double percent) {
		return percent / 100;
	}

}
