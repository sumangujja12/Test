package com.multibrand.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PropertyHolder {
	static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	private static final Properties ServerProps = new Properties();

	public static void main(String[] args) throws IOException {
		System.out.println("Inside propHolder.java: " + getInstance("/properties/appConstants.properties").getProperty("08901"));
	}
	public static Properties initServerProps(String fileName) throws IOException {
		InputStream is = null;
		try {
			is = PropertyHolder.class.getResourceAsStream(fileName);
			ServerProps.load(is);
			return ServerProps;
			
		}
		finally {
			if (is !=null) {
				try {is.close();} catch(Exception ex) {
					logger.error("EXCEPTION", ex);
				}
				is=null;
			}
		}
	}
	public static Properties getInstance(String fileName) throws IOException {
		return initServerProps(fileName);
	}
	public static Properties getErrorLogMonitoringProps() {
		return ServerProps;
	}
}