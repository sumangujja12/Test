package com.it.multibrand.karate.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class BaseFunctions {




  public void generateReport(String karateOutputPath) {
     Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
     List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
     for(File jsonFile : jsonFiles){
    	 jsonPaths.add(jsonFile.getAbsolutePath());
     }
     Configuration config = new Configuration(new File("target"), "cucumber-html-report");
     ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
     reportBuilder.generateReports();
  }
}

