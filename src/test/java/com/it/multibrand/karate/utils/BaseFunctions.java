package com.it.multibrand.karate.utils;


import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseFunctions {

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


  public void generateReport(String karateOutputPath) {
     Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
     List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
     for(File jsonFile : jsonFiles){
    	 jsonPaths.add(jsonFile.getAbsolutePath());
     }
     //jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
     Configuration config = new Configuration(new File("target"), "cucumber-html-report");
     ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
     reportBuilder.generateReports();
  }
}

