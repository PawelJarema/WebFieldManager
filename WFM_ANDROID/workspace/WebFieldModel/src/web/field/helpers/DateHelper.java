package web.field.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class DateHelper {

	public static Date getUtcNow() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
		Date utc = sdf.parse(sdf.format(new Date()));
		return utc;
	}
}
