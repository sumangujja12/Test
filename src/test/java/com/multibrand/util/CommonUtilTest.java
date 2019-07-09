package com.multibrand.util;

import java.text.DecimalFormat;

public class CommonUtilTest {
	
	public String getRandomAmount() {
		DecimalFormat formatter = new DecimalFormat("#0.00");      
		double randomValue = 1 + Math.random( ) * .785;
		return formatter.format(randomValue);
		
	}

}
