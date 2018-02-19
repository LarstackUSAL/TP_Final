package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilities {

	public static Calendar stringToCalendar(String fecha, String formatoFecha) {

		// "dd/MM/yyyy"
		// "dd-MM-yyyy"
		// "yyyyMMdd"	

		Calendar calendar = Calendar.getInstance();

		try {

			SimpleDateFormat format = new SimpleDateFormat(formatoFecha);
			Date date;
			date = format.parse(fecha);
			calendar.setTime(date);

		} catch (ParseException e) {

			System.out.println("Se ha verificado un error con el parsing de la fecha.");
			calendar = null;
		}

		return calendar;		
	}
	
	public static String calendarToString(Calendar fecha, String formatoFecha){

		// "dd/MM/yyyy"
		// "dd-MM-yyyy"
		// "yyyyMMdd"	
		// "yyyyMMdd HH:mm:ss"

		Date fechaDate = fecha.getTime();
		DateFormat formatter = new SimpleDateFormat(formatoFecha);
		return formatter.format(fechaDate);
	}
	
	public static String fillString(String s, int longitudFinalString, String filler, boolean izquierda){

		if(s.length() < longitudFinalString){

			int espacios = (longitudFinalString-s.length());

			for (int i = 0; i < espacios; i++) {

				if(izquierda){

					s = filler + s;
				}else{

					s += filler;
				}
			}
		}

		return s;
	}
}
