package es.cnc.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateUtil {
	public static LocalDateTime dateToLocalDateTime (Date date){
		Date aux;
		if (date instanceof java.sql.Date) {
			aux = new Date(date.getTime());
		} else {
			aux = date;
		}
		return aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
		
		public static void main(String[] args) throws Exception {
			LocalDateTime hora = LocalDateTime.now();
			LocalDate fecha = LocalDate.now();
			System.out.println(toISOLocalDateTimeNoMillis(hora));
			System.out.println(toYYYYMMDDz(fecha));
		}
		
}
