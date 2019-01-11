/**
 * 
 */
package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the helper class for the application
 * @author 
 *
 */
public class Util {
	
	/**
	 * 
	 */
	private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	/*
	 * This method convert string date to Date object
	 */
	public static Date convertISODate(String sDate){
		Date date=null;
		if(sDate!=null){
			SimpleDateFormat formatter=new SimpleDateFormat(DATE_PATTERN);
			try {
				date=formatter.parse(sDate);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return date;
	}
	
	public static String formatDate(Date date){
		if(date!=null){
			SimpleDateFormat formatter=new SimpleDateFormat(DATE_PATTERN);
			return formatter.format(date);
		}
		return null;
	}
	
	//This method validates input
	public static boolean validateEmail(String email){
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
