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
	
	public static long dateToSeconds(Date date){
		return date.getTime() / 1000;
	}
	
	public static long stringDateToSeconds(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.ENGLISH);
		Date parsed = sdf.parse(date);
		
		return dateToSeconds(parsed);
	}
	
}
