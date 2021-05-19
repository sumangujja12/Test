package com.multibrand.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TokenPropertyHolder {
	static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	private static final Properties TokenServerProps = new Properties();
	//private static long lastModifiedTime = -1;
	public static Properties initTokenServerProps() throws IOException {
		InputStream is = null;
		try {
			is = TokenPropertyHolder.class.getResourceAsStream("/properties/environment.properties");
			//lastModifiedTime = new File("/TokenServer.properties").lastModified();
			TokenServerProps.load(is);	
			return TokenServerProps;
			
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
	public static Properties getInstance() throws IOException {
		return initTokenServerProps();
	}
	public static Properties getErrorLogMonitoringProps() {
		return TokenServerProps;
	}
}