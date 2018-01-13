package es.cnc.util;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class LocalDateUtil {
	public static LocalDateTime dateToLocalDateTime (Date date){
		Date aux = null;
		if (date != null) {
			if (date instanceof java.sql.Date) {
				aux = new Date(date.getTime());
			} else {
				aux = date;
			}
			return aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		} else {
			return null;
		}
	}
	public static LocalDate dateToLocalDate (Date date){
		Date aux;
		if (date instanceof java.sql.Date) {
			aux = new Date(date.getTime());
		} else {
			aux = date;
		}
		return aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static Instant dateToInstant(Date date) {
		return Instant.ofEpochSecond(date.getTime());
	}
	
	public static Date localDateToDate(LocalDate localDate) {
		return localDateTimeToDate(localDate.atStartOfDay());
		//Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.of("CET")));
		
	}
	
	/**
	 * Converts a {@code java.time.LocalDateTime} to a {@code java.util.Date}
	 * adding default ZoneId
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * This method return the date in String format YYYY-MM-DDTHH:mm:ss but no millis 
	 * @param auxTime
	 * @return
	 */
	public static String toISOLocalDateTimeNoMillis(LocalDateTime auxTime) {
			StringBuilder sb = new StringBuilder();
			DateTimeFormatter formatter = null; 
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			sb.append(formatter.format(auxTime.atZone(ZoneId.systemDefault())));
			return sb.toString();
		}
	/**
	 * Returns a String with the date in next format: YYYY-MM-ddZZZZZ
	 * This format is defined by XMLGregorianCalendar  
	 * @param auxTime
	 * @return
	 */
	public static String toYYYYMMDDz(LocalDate auxTime) {
//		return auxTime.toString();
		/*
		 * XMLGregorianCalendar format: "%Y-%M-%D" + "%z"; 
		 */
		StringBuilder sb = new StringBuilder();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddZZZZZ");
		sb.append(formatter.format(auxTime.atStartOfDay().atZone(ZoneId.systemDefault())));
		return sb.toString();
	}
	
	public static String obtainTextualMonth(TemporalAccessor date) {
		String month = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
		month = formatter.format(date);
//		month = date.format(formatter);
		return capitalizeFirst(month);
		
	}
	
	public static String capitalizeFirst(String text) {
		String aux = text;
		if (text != null && text.length()>0) {
			aux = text.substring(0, 1).toUpperCase();
			if (text.length() > 1) {
				aux = aux + text.substring(1).toLowerCase();
			}
		}
		return aux;
	}
	
	/**
	 * Calculate the first LocalDateTime from year and month
	 * @param year
	 * @param month
	 * @return
	 */
	public static LocalDateTime fromInitialYearMonth(int year, int month) {
		LocalDateTime from = null;
		
		from = LocalDateTime.of(year, month, 1, 0, 0);
		
		return from;
	}

	/**
	 * Calculate the last LocalDateTime from year and month
	 * @param year
	 * @param month
	 * @return
	 */
	public static LocalDateTime fromLastYearMonth(int year, int month) {
		LocalDateTime to = null;
		
		to = fromInitialYearMonth(year, month);
		to = to.plusMonths(1l);
		to = to.minusNanos(1l);
		
		return to;
	}

	public static Integer currentYear() {
		LocalDate today = LocalDate.now();
		return today.getYear();
	}
	public static void main(String[] args) throws Exception {
//		LocalDateTime hora = LocalDateTime.now();
//		LocalDate fecha = LocalDate.now();
//		System.out.println(toISOLocalDateTimeNoMillis(hora));
//		System.out.println(toYYYYMMDDz(fecha));
//		System.out.println(obtainTextualMonth(hora));
//		System.out.println(obtainTextualMonth(fecha));
		
		LocalDateTime from = fromInitialYearMonth(2016, 2);
		System.out.println(toISOLocalDateTimeNoMillis(from));
//		System.out.println(toYYYYMMDDz(from));

		LocalDateTime to = fromLastYearMonth(2016, 2);
		System.out.println(toISOLocalDateTimeNoMillis(to));

		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		System.out.println(dtf.format(from));
		TemporalAccessor ta = null;
		ta = dtf.parse("1/01/18");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		System.out.println(df.format(localDateTimeToDate(from)));
		
	}
	
}
