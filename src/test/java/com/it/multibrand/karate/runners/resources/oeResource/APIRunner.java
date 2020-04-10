package com.it.multibrand.karate.runners.resources.oeResource;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;
							
@KarateOptions(features = { "src/test/resources/com/it/multibrand/karate/resources/oeResource/features/GetAffiliateOffers.feature",
							"src/test/resources/com/it/multibrand/karate/resources/oeResource/features/PerformPosidBPMatch.feature",
							"src/test/resources/com/it/multibrand/karate/resources/oeResource/features/GetKBAQuestions.feature",
							"src/test/resources/com/it/multibrand/karate/resources/oeResource/features/SubmitKBAAnswers.feature",
							"src/test/resources/com/it/multibrand/karate/resources/oeResource/features/Salesoffers.feature",
							"src/test/resources/com/it/multibrand/karate/resources/oeResource/features/GetESIDDetails.feature"
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