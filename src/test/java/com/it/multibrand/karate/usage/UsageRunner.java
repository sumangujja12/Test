package com.it.multibrand.karate.usage;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;
							
@KarateOptions(features = { "src/test/resources/com/it/multibrand/karate/usage/GetMonthlyUsage.feature",
						"src/test/resources/com/it/multibrand/karate/usage/GetWeeklyUsage.feature",
						"src/test/resources/com/it/multibrand/karate/usage/GetYearlyUsage.feature"
						})
public class UsageRunner {
		BaseFunctions baseFunctions = new BaseFunctions();

	   @Test
	   public void testParallel() {

	      Results results = Runner.parallel(getClass(), 10, "target/surefire-reports");

	      baseFunctions.generateReport(results.getReportDir());
	      assertTrue(results.getErrorMessages(), results.getFailCount() == 0);	    
	   }
}