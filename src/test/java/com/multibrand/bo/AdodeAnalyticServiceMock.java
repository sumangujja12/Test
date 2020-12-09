package com.multibrand.bo;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdodeAnalyticServiceMock  implements Runnable {
	private String url = "";
	private Map<String,String> inputJson = null;
	private String iotURL = "";
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public AdodeAnalyticServiceMock (String iotUrl, String url, Map<String,String> inputJson) {
		this.inputJson =inputJson;
		this.url = url;
		this.iotURL = iotUrl;
	}
	
	public void run() {
      System.out.println("Mock Running");
	}
}
