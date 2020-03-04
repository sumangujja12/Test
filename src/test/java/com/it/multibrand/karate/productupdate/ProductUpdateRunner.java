package com.it.multibrand.karate.productupdate;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;
							
@KarateOptions(features = { "src/test/resources/com/it/multibrand/karate/productupdate/ReadProduct.feature",
							"src/test/resources/com/it/multibrand/karate/productupdate/EnrollProduct.feature",
							"src/test/resources/com/it/multibrand/karate/productupdate/De-EnrollProduct.feature"
						})
public class ProductUpdateRunner {
		BaseFunctions baseFunctions = new BaseFunctions();

	   @Test
	   public void testParallel() {

	      Results results = Runner.parallel(getClass(), 10, "target/surefire-reports");

	      baseFunctions.generateReport(results.getReportDir());
	      assertTrue(results.getErrorMessages(), results.getFailCount() == 0);	    
	   }
}