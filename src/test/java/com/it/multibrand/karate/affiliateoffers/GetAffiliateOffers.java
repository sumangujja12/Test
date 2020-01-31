package com.it.multibrand.karate.affiliateoffers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;
							
@KarateOptions (features = "src/test/resources/com/it/multibrand/karate/affiliateoffers/GetAffiliateOffers.feature")
public class GetAffiliateOffers {
		BaseFunctions baseFunctions = new BaseFunctions();

	   @Test
	   public void testAffiliateOffers() {

	      Results results = Runner.parallel(getClass(), 5, "target/surefire-reports");

	      baseFunctions.generateReport(results.getReportDir());
	      assertTrue(results.getErrorMessages(), results.getFailCount() == 0);	    
	   }
}
