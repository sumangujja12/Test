package com.multibrand.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Component;


/**
 * 
 * @author nulthi1
 * @version 1.0
 * 
 */
@Component
public class DateUtil implements Constants{

	/**
	 * Field Log.
	 */
	private static Logger Log = LogManager.getLogger(DateUtil.class);
	private static final long ONE_HOUR = 60 * 60 * 1000L;
	private static final String DATE_FORMAT_YYYY = "yyyy";
	private static Pattern DATE_PATTERN_YYYYMMDD = Pattern.compile("([1-9]\\d{3}(0[0-9]|1[0-9])(0[1-9]|[12]\\d|3[01]))");
																
	/**
	 * Method getMonDD.
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String getMonDD(Date date) {

		return getMonDD(date, Locale.US);
	}

	/**
	 * Method getMonDD.
	 * 
	 * @param date
	 *            Date
	 * @param locale
	 *            Locale
	 * @return String
	 */
	public static String getMonDD(Date date, Locale locale) {

		String monthDay = "";

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		String mmm = new DateFormatSymbols(locale).getShortMonths()[cal
				.get(Calendar.MONTH)];
		int dd = cal.get(Calendar.DATE);

		monthDay = mmm + " " + dd;

		return monthDay;
	}

	/**
	 * Method addDays.
	 * 
	 * @param date
	 *            Date
	 * @param noOfDays
	 *            int
	 * @return Date
	 */
	public static Date addDays(Date date, int noOfDays) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, noOfDays);

		return cal.getTime();

	}

	/**
	 * Method addMonths.
	 * 
	 * @param date
	 *            Date
	 * @param noOfMonths
	 *            int
	 * @return Date
	 */
	public static Date addMonths(Date date, int noOfMonths) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, noOfMonths);

		return cal.getTime();

	}

	/**
	 * Method addYears.
	 * 
	 * @param date
	 *            Date
	 * @param noOfYears
	 *            int
	 * @return Date
	 */
	public static Date addYears(Date date, int noOfYears) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, noOfYears);

		return cal.getTime();

	}

	/**
	 * Method getNextWeekDate.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getNextWeekDate(Date date) {

		return addDays(date, 7);

	}

	/**
	 * Method getPreviousWeekDate.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getPreviousWeekDate(Date date) {

		return addDays(date, -7);

	}

	/**
	 * Method getYear.
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String getYear(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return "" + cal.get(Calendar.YEAR);
	}

	/**
	 * Method getYearInt.
	 * 
	 * @param date
	 *            Date
	 * @return int
	 */
	public static int getYearInt(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.YEAR);
	}

	/**
	 * Returns the current year
	 * 
	 * @author jyogapa1
	 * @return
	 */
	public static Integer getCurrentYear() {
		// set current date and year
		Date currentDate = new Date();
		Integer currentYear = 0;

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YYYY);
		String strCurrentYear = formatter.format(currentDate);
		currentYear = Integer.parseInt(strCurrentYear);

		return currentYear;
	}

	/**
	 * Method getMonthInt.
	 * 
	 * @param date
	 *            Date
	 * @return int
	 */
	public static int getMonthInt(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * Method compareToTodaysDate.
	 * 
	 * @param date
	 *            Date
	 * @return int
	 */
	public static int compareToTodaysDate(Date date) {

		Calendar today = Calendar.getInstance();
		// truncating the date ( excludes HH:MM:ss)
		today.setTime(getDate(getFormatedDate(today.getTime(), "dd/MM/yyyy"),
				"dd/MM/yyyy"));

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.compareTo(today);

	}

	/**
	 * Method getYearMMdd.
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String getYearMMdd(Date date) {

		return getFormatedDate(date, "yyyyMMdd");
	}

	/**
	 * Method getDate.
	 * 
	 * @param dateStr
	 *            String
	 * @param pattern
	 *            String
	 * @return Date
	 */
	public static Date getDate(String dateStr, String pattern) {

		Date returnDate = null;
		try {
			returnDate = new SimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {

			throw new RuntimeException("getDate() :: parsing date ('" + dateStr
					+ "')  with the pattern ('" + pattern
					+ "') Failed: cause: " + e.getCause(), e);
		}

		return returnDate;
	}

	/**
	 * Method getMonthsDiff.
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return int
	 */
	public static int getMonthsDiff(Date d1, Date d2) {

		// logger.info("Date d1 :" + d1);
		// logger.info("Date d2 :" + d2);
		int monthDiff = 0;

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);

		monthDiff = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12
				+ (cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH));

		// logger.info("monthDiff :" + monthDiff );
		return Math.abs(monthDiff);
	}

	/**
	 * Method getFormatedDate.
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String getFormatedDate(Date date, String pattern) {

		String formatedDateStr = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			formatedDateStr = sdf.format(date);
		} catch (Exception e) {

			throw new RuntimeException("getFormatedDate() :: format date ('"
					+ date + "')  with the pattern ('" + pattern
					+ "') Failed: cause: " + e.getCause(), e);
		}
		return formatedDateStr;
	}

	/**
	 * Method getFormattedDate.
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String getFormattedDate(Date date, String pattern) {

		String formattedDateStr = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			formattedDateStr = sdf.format(date);
		} catch (Exception e) {

			throw new RuntimeException("getFormattedDate() :: format date ('"
					+ date + "')  with the pattern ('" + pattern
					+ "') Failed: cause: " + e.getCause(), e);
		}
		return formattedDateStr;
	}

	/**
	 * Method getFormattedDate.
	 * 
	 * @param long Date
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String getFormattedDate(long date, String pattern) {

		String formatedDateStr = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			formatedDateStr = sdf.format(date);
		} catch (Exception e) {

			throw new RuntimeException("getFormattedDate() :: format date ('"
					+ date + "')  with the pattern ('" + pattern
					+ "') Failed: cause: " + e.getCause(), e);
		}
		return formatedDateStr;
	}

	/**
	 * Method getCurrentDate.
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {

		Calendar today = Calendar.getInstance();

		return today.getTime();
	}

	/**
	 * Method getLastYearDate
	 * 
	 * @return String
	 */
	public static String getLastYearDate() {

		Date date = getCurrentDate();
		date = addDays(date, -365);
		return getFormatedDate(date, "MM/dd/yyyy");

	}

	/**
	 * Method Returns Current Date Formatted.
	 * 
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String getCurrentDateFormatted(String pattern) {
		Calendar today = Calendar.getInstance();

		return getFormatedDate(today.getTime(), pattern);
	}

	/**
	 * Method getFormatDateCal.
	 * 
	 * @param cal
	 * @param pattern
	 * @return
	 */
	public static String getFormatDateCal(Calendar cal, String pattern) {
		if (cal != null) {
			Date date = cal.getTime();
			String strDate = getFormatedDate(date, pattern);
			return strDate;
		} else {
			return "";
		}
	}

	/********************************************
	 * Function: getTransactionDate() Purpose: returns the Transaction Date
	 * Inputs: locale Returns: Complete Date
	 * 
	 * @param locale
	 *            String
	 * @return String
	 ********************************************/
	public static String getTransactionDate(String locale) {
		java.util.Date currentDate = new java.util.Date();
		java.text.SimpleDateFormat sdf = null;
		if (locale.equals("en") || locale.equals("en_US")) {
			sdf = new java.text.SimpleDateFormat(
					"MMMMM d, yyyy 'at' hh:mm aaa.", new Locale("en", "EN"));
		} else {
			sdf = new java.text.SimpleDateFormat(
					"d 'de' MMMMM, yyyy 'a las' hh:mm aaa.", new Locale("es",
							"ES"));
		}
		return sdf.format(currentDate);
	}

	/********************************************
	 * Function: getSubmissionDateByLocale() Purpose: returns the Transaction
	 * Date Inputs: locale Returns: Complete Date
	 * 
	 * @param locale
	 *            String
	 * @param datePattern
	 *            String
	 * @return String
	 ********************************************/
	public static String getSubmissionDateByLocale(String locale,
			String datePattern) {
		Calendar today = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = null;

		if (locale.equalsIgnoreCase("en") || locale.equalsIgnoreCase("en_US")) {
			sdf = new java.text.SimpleDateFormat(datePattern, new Locale("en",
					"EN"));
		} else {
			sdf = new java.text.SimpleDateFormat(datePattern, new Locale("es",
					"ES"));
		}

		return sdf.format(today.getTime());
	}

	/**
	 * Method getFormattedDate.
	 * 
	 * @param newFormat
	 *            String
	 * @param oldFormat
	 *            String
	 * @param oldString
	 *            String
	 * @return String
	 */
	public static String getFormattedDate(String newFormat, String oldFormat,
			String oldString) {
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = null;
		String newString = null;
		try {
			Log.info("inside getFormattedDate:: simpledateformating the date:: "+oldString);
			//setting leniency to false for proper pattern check @Jasveen
			sdf.setLenient(false);
			d = sdf.parse(oldString);
			sdf.applyPattern(newFormat);
			newString = sdf.format(d);
		} catch (Exception e) {
			Log.error("ParseException in DateUtil:getFormattedDate():"
					+ e.getMessage());
			return oldString;

		}
		return newString;
	}

	/**
	 * Method getFormattedDateWithLocale.
	 * 
	 * @param newFormat
	 *            String
	 * @param oldFormat
	 *            String
	 * @param oldString
	 *            String
	 * @return String
	 */
	public static String getFormattedDateWithLocale(String newFormat,
			String oldFormat, String oldString, Locale locale) {

		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat, locale);
		Date d = null;
		try {
			d = sdf.parse(oldString);
		} catch (ParseException e) {
			Log.error("ParseException in DateUtil:getFormattedDateWithLocale():"
					+ e.getMessage());
			return oldString;

		}
		sdf.applyPattern(newFormat);
		String newString = sdf.format(d);
		return newString;
	}

	/**
	 * Method to return Calendar object for the given date object
	 * 
	 * @param date
	 * @return
	 * @author vanagan1
	 */
	public static Calendar getCalendarForDate(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}

	/**
	 * Method to compute days between two given dates
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long daysBetween(Date startDate, Date endDate) {
		return ((endDate.getTime() - startDate.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}

	/**
	 * Method main.
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {

		/*
		 * Log.info(getFormatedDate( getDate("2011/12/01" , "yyyy/MM/dd"),
		 * "MM/dd/yyyy")); Log.info(getCurrentDateFormat("MM/dd/yyyy"));
		 * logger.debug(getFormattedDate("MMMMMMMMMM yyyy",
		 * "yyyy-MM-DD","2013-09-12"));
		 */
		// logger.debug("test"+ getFormattedDateWithLocale("MMMMMMMMMM yyyy",
		// "yyyy-MM-dd","2013-09-12",new Locale("en", "EN" )));
	}

	/**
	 * Method to display Full date with Time stamp and Time zone
	 * 
	 * @param parse
	 * @param dateFormat
	 * @param timezone
	 * @return
	 */
	public static Object getFormatedDateWithTimeZone(Date date,
			String dateFormat, String timezone) {
		String formatedDateStr = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setTimeZone(TimeZone.getTimeZone("CST"));
			formatedDateStr = sdf.format(date);
		} catch (Exception e) {

			throw new RuntimeException("getFormatedDate() :: format date ('"
					+ date + "')  with the pattern ('" + dateFormat
					+ "') Failed: cause: " + e.getCause(), e);
		}
		return formatedDateStr;
	}

	/********************************************
	 * Dkrishn1 Method: getTransactionDate(String locale) Purpose: returns the
	 * Locale sensitive Transaction Date Inputs: String (locale) : "en_US";
	 * "es_US" Returns: Formatted Transaction Date according to Locale
	 ********************************************/
	public static String getTransactionDateWithLocale(String locale) {
		String date = "";
		String time = "";

		java.util.Date sysDate = null;
		sysDate = new java.util.Date();

		java.text.SimpleDateFormat simpleTime = new SimpleDateFormat(
				"hh:mm a z");

		String languageLocale = "";
		if (locale.equals("en_US")) {
			languageLocale = "en";
		} else {
			languageLocale = "es";
		}

		Locale localeLang = new Locale(languageLocale, "US");

		java.text.DateFormat dateFmt = java.text.DateFormat.getDateInstance(
				java.text.DateFormat.LONG, localeLang);

		date = dateFmt.format(sysDate);
		time = simpleTime.format(sysDate);
		String theDate = date + " " + time;
		return theDate;
	}
	
	/**
	 * Generates Date for the given string in specified pattern with avoiding
	 * parsing exception.
	 * 
	 * @param dateStr
	 *            Date as string
	 * @param pattern
	 *            Date pattern to be converted
	 * @param refrainException
	 *            true/false to indicate avoiding exception
	 * @return Date
	 * 
	 */
	public static Date getDateForString(String dateStr, String pattern, boolean refrainException) {

		Date returnDate = null;

		try {
			if (refrainException) {

				if (StringUtils.isNotBlank(dateStr) && !"NULL".equalsIgnoreCase(dateStr)) {
					returnDate = new SimpleDateFormat(pattern).parse(dateStr);
				}

			} else {
				returnDate = new SimpleDateFormat(pattern).parse(dateStr);
			}
		} catch (ParseException e) {

			throw new RuntimeException("getDate() :: parsing date ('" + dateStr + "')  with the pattern ('" + pattern
					+ "') Failed: cause: " + e.getCause(), e);
		}

		return returnDate;
	}
	
	/** 
	 * This method returns starting date of the given week
	 * @author NGASPerera
	 * @param year        Year          int
	 * @param weekNumber  Week number   int
	 * @param startingDay Starting day  int
	 * @return Date       Fist day of the given week
	 * @
	 */
	public Date getStartingDateOftheWeek(int year, int weekNumber, int weekStartingDay) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
			cal.set(Calendar.DAY_OF_WEEK, weekStartingDay);
			date = formatter.parse(formatter.format(cal.getTime()));
		} catch (ParseException e) {
			throw new IllegalArgumentException();
		}
		return date;

	}
	
	
	/**
	 * This method added number of days to a given date
	 * @author NGASPerera
	 * @param date    Date
	 * @param addNum  int
	 * @return Date 
	 * The same method is in this class but the previous method is a static method and Calendar
	 * is a not a thread safe. Because of that re written the same method.
	 */
	public Date addNumberOfDays(Date date, int addNum){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, addNum);
		return cal.getTime();
	}
	
	/**
	 * This method compares the month of given two dates.
	 * @author NGASPerera
	 * @param startDate  Date
	 * @param endDate    Date
	 * @return an int 
	 * if startDate month and endDate month both are same then will return 0
	 * if startDate month is on previous month then will return > 0
	 * if endDate month is on next moth then will return < 0
	 */
	public int compareMonths(Date startDate, Date endDate){
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(startDate);
		endCal.setTime(endDate);
		return startCal.get(startCal.MONTH) - endCal.get(endCal.MONTH);
	}
	
	/**
	 * This method will return next month of parameterized month
	 * @author NGASPerera
	 * @param currentDate
	 * @return Date 
	 */
	public Date getNextMonth(Date currentDate){
		Calendar currentCal = Calendar.getInstance();
		currentCal.setTime(currentDate);
		currentCal.set(currentCal.MONTH,1);
		currentCal.set(currentCal.DATE,1);
		return currentCal.getTime();
	}

	/**
	 * This method will return previous month of parameterized month
	 * @author NGASPerera
	 * @param currentDate
	 * @return Date
	 */
	public Date getPreviousMonth(Date currentDate){
		Calendar currentCal  = Calendar.getInstance();
		currentCal.setTime(currentDate);
		currentCal.set(currentCal.MONTH,-1);
		currentCal.set(currentCal.DATE,1);
		return currentCal.getTime();
		
	}
	/**
	 * This method will parse String into Date
	 * @author NGASPerera 
	 * @param date String
	 * @return date Date
	 */
	public Date getDate(String date){
		Date d = null;
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.yyyyMMdd);
		try {
			d = formatter.parse(date);
		} catch (ParseException e) {
			Log.info(e);
		}
		return d;
	}
	
	/**
	 * This method will parse Date into String
	 * @author NGASPerera 
	 * @param date Date
	 * @return date String
	 */
	public String getDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.yyyyMMdd);
		return formatter.format(date);
	}
	
	/**
	 * This method returns List of Data that belongs to a range
	 * @author NGASPerera
	 * @param startDate Date
	 * @param endDate Date
	 * @return List<Date>
	 */
	public List<Date> getDatesInRange(Date startDate, Date endDate) {
		List<Date> datesInRange = new ArrayList<>();
		while (startDate.compareTo(endDate) <= 0) {
			datesInRange.add(startDate);
			startDate = addNumberOfDays(startDate, 1);

		}
		return datesInRange;
	}
	/**
	 * This method returns difference between two time in seconds
	 * @author NGASPerera
	 * @param Date startDate
	 * @param Date endDate
	 * @return long timeDiffInSeconds
	 */
	public long getTimeDifferenceInSeconds(Date startDate, Date endDate){
		return  (startDate.getTime() - endDate.getTime()) / 1000;
	}

	public static boolean matches(String date) {
		if(StringUtils.isEmpty(date)){
			return false;
		} else {
			return DATE_PATTERN_YYYYMMDD.matcher(date).matches();
		}
    }
	
}
