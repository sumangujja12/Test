package com.it.multibrand.karate.affiliateoffers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;
							
@KarateOptions(features = { "src/test/resources/com/it/multibrand/karate/affiliateoffers/GetAffiliateOffers.feature",
							"src/test/resources/com/it/multibrand/karate/posidbpmatch/PerformPosidBPMatch.feature",
							"src/test/resources/com/it/multibrand/karate/kba/GetKBAQuestions.feature",
							"src/test/resources/com/it/multibrand/karate/kba/SubmitKBAAnswers.feature",
							"src/test/resources/com/it/multibrand/karate/affiliateoffers/Salesoffers.feature",
							"src/test/resources/com/it/multibrand/karate/esiddetails/GetESIDDetails.feature"
							})
public class APIRunner {
		BaseFunctions baseFunctions = new BaseFunctions();

	   @Test
	   public void testAffiliateOffers() {

	      Results results = Runner.parallel(getClass(), 10, "target/surefire-reports");

	      baseFunctions.generateReport(results.getReportDir());
	      assertTrue(results.getErrorMessages(), results.getFailCount() == 0);	    
	   }
}