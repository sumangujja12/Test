package com.it.multibrand.karate.accountdetails;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;
							
@KarateOptions(features = { "src/test/resources/com/it/multibrand/karate/accountdetails/CO_Status.feature",
		 					"src/test/resources/com/it/multibrand/karate/accountdetails/EnvironmentalImpact.feature",
		 					"src/test/resources/com/it/multibrand/karate/accountdetails/GetAccountDetails.feature",
		 					"src/test/resources/com/it/multibrand/karate/accountdetails/ReadContactAlertPref.feature",
		 					"src/test/resources/com/it/multibrand/karate/accountdetails/UpdateBillingAddress.feature",
		 					"src/test/resources/com/it/multibrand/karate/accountdetails/UpdateContactInfo.feature"
						})
public class AccountDetailsRunner {
		BaseFunctions baseFunctions = new BaseFunctions();

	   @Test
	   public void testParallel() {

	      Results results = Runner.parallel(getClass(), 10, "target/surefire-reports");

	      baseFunctions.generateReport(results.getReportDir());
	      assertTrue(results.getErrorMessages(), results.getFailCount() == 0);	    
	   }
}