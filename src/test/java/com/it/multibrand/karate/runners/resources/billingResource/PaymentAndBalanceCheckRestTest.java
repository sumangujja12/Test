package com.it.multibrand.karate.runners.resources.billingResource;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;


@KarateOptions(features = "src/test/resources/com/it/multibrand/karate/resources/billResource/features/submitPayment.feature")
public class PaymentAndBalanceCheckRestTest {
	BaseFunctions baseFunctions = new BaseFunctions();

	@Test
	public void testParallel() {

		Results results = Runner.parallel(getClass(), 5, "target/surefire-reports");

		BaseFunctions.generateReport(results.getReportDir());
		assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
	}
}
