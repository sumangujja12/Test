package com.it.multibrand.karate.utils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class BaseFunctions {

  public static void generateReport(String karateOutputPath) {
     Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
     List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
     for(File jsonFile : jsonFiles){
    	 jsonPaths.add(jsonFile.getAbsolutePath());
     }
     Configuration config = new Configuration(new File("target"), "cucumber-html-report");
     ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
     reportBuilder.generateReports();
  }
  
  public static String generateRandomNumber(){
  	int min = 1;
  	int max = 9;
  	
		Random r = new Random();
  	float random = min + r.nextFloat() * (max - min);
  	double roundOff = (double) Math.round(random * 100) / 100;
		return Double.toString(roundOff);
	

  }
  
  public static String getCurrentDate(){
  	String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		return date;
  }
  
	public static String getCardExpDate() {
		Random r = new Random();
		LocalDate ld = LocalDate.now().plusMonths(2).plusMonths(r.nextInt((60 - 0) + 1) + 0);
		String expdate = ld.getMonthValue()+"/"+ld.getYear();
		return expdate;
	}
}

