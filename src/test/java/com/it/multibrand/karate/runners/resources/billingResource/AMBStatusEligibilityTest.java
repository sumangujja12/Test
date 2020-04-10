package com.it.multibrand.karate.runners.resources.billingResource;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

@KarateOptions(features = "src/test/resources/com/it/multibrand/karate/resources/billResource/features/ambStatusEligibility.feature", tags = {"@amb-status-eligibility"})
public class AMBStatusEligibilityTest {
    BaseFunctions baseFunctions = new BaseFunctions();

    @Test
    public void testParallel() {

        Results results = Runner.parallel(getClass(), 5, "target/surefire-reports");

        BaseFunctions.generateReport(results.getReportDir());
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }
}
