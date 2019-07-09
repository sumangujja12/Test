package com.multibrand.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtilTestNew {
	
	public String getDate(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	} 
	
	
	public String addDaysToCurrentDate(int numberOfDays,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,numberOfDays);
		return sdf.format(cal.getTime());
	}
}
