package com.multibrand.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateValidator {

	public boolean isValidDate(String dateToValidate, String dateFormat) {  
		if (dateToValidate == null) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(dateToValidate);
			return true;
		} catch (ParseException e) {
			return false;

		}

	}

}
