package it.xplants.xtro.vgi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.webobjects.foundation.NSTimestamp;

public class DateTools {

	public DateTools() {
		// TODO Auto-generated constructor stub
	}

	public static NSTimestamp currentYearMin() {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
		gc.set(Calendar.DAY_OF_YEAR, 1);
		gc.set(Calendar.HOUR_OF_DAY, 12);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		return new NSTimestamp(gc.getTime());
	}

	public static NSTimestamp currentYearMax() {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
		gc.set(Calendar.DAY_OF_YEAR, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.HOUR_OF_DAY, 12);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		return new NSTimestamp(gc.getTime());
	}

	public static NSTimestamp add(NSTimestamp d, int field, int amount) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
		gc.setTime(d);
		gc.set(Calendar.HOUR_OF_DAY, 12);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.add(field, amount);
		return new NSTimestamp(gc.getTime());
	}

	public static int dayDiff(NSTimestamp a, NSTimestamp b) {
		GregorianCalendar temp = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
		temp.setTime(a);
		temp.set(Calendar.HOUR_OF_DAY, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
		long al = temp.getTime().getTime();
		temp.setTime(b);
		temp.set(Calendar.HOUR_OF_DAY, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
		long bl = temp.getTime().getTime();
		return (int) (((((bl - al) / 1000) / 60) / 60) / 24);
	}

	public static NSTimestamp normalize(NSTimestamp d) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
		gc.setTime(d);
		gc.set(Calendar.HOUR_OF_DAY, 12);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return new NSTimestamp(gc.getTime());
	}

	public static NSTimestamp actualMin(NSTimestamp d, int field) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
		gc.setTime(d);
		gc.set(Calendar.HOUR_OF_DAY, 12);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		int a = gc.getActualMinimum(field);
		gc.set(field, a);
		return new NSTimestamp(gc.getTime());
	}

	public static NSTimestamp todayAtTime(String hh_mm) {
		try {
			String date = new SimpleDateFormat("dd/MM/yyyy").format(new NSTimestamp()) + " " + hh_mm + "";
			return new NSTimestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static NSTimestamp actualMax(NSTimestamp d, int field) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
		gc.setTime(d);
		gc.set(Calendar.HOUR_OF_DAY, 12);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		int a = gc.getActualMaximum(field);
		gc.set(field, a);
		return new NSTimestamp(gc.getTime());
	}

}
