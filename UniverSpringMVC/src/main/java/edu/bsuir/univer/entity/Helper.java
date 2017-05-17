package edu.bsuir.univer.entity;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
	
	public static boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	public static boolean tryParseDate(String value){
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			format.parse(value);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public static Date parseDate(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String dateToString(Date date){
		Format formatter = new SimpleDateFormat("yyyy-dd-MM");
		String s = formatter.format(date);
		return s;
	}
	
	public static Date parseDateTime(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String dateTimeToString(Date date){
		Format formatter = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		String s = formatter.format(date);
		return s;
	}
}
