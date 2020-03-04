package com.it.multibrand.karate.billing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;
							
@KarateOptions(features = { "src/test/resources/com/it/multibrand/karate/billing/AverageBilling.feature",
		 					"src/test/resources/com/it/multibrand/karate/billing/EnrollAverageBilling.feature",
		 					"src/test/resources/com/it/multibrand/karate/billing/GetBalanceForGMEMobile.feature",
		 					"src/test/resources/com/it/multibrand/karate/billing/UpdatePaperFreeBilling.feature"
						})
public class BillingRunner {
		BaseFunctions baseFunctions = new BaseFunctions();

	   @Test
	   public void testParallel() {

	      Results results = Runner.parallel(getClass(), 10, "target/surefire-reports");

	      baseFunctions.generateReport(results.getReportDir());
	      assertTrue(results.getErrorMessages(), results.getFailCount() == 0);	    
	   }
}