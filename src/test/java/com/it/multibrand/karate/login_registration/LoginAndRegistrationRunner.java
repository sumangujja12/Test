package com.it.multibrand.karate.login_registration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.it.multibrand.karate.utils.BaseFunctions;
							
@KarateOptions(features = { "src/test/resources/com/it/multibrand/karate/login&reg/ForgotUserName.feature",
						"src/test/resources/com/it/multibrand/karate/login&reg/ForgotPassword.feature",
						"src/test/resources/com/it/multibrand/karate/login&reg/ForgotPasswordLink.feature",
						"src/test/resources/com/it/multibrand/karate/login&reg/RefreshToken.feature",
						"src/test/resources/com/it/multibrand/karate/login&reg/LoginSuccess.feature",
						"src/test/resources/com/it/multibrand/karate/login&reg/LoginFailure.feature",
						"src/test/resources/com/it/multibrand/karate/login&reg/ValidateAccountForMobile.feature",
						"src/test/resources/com/it/multibrand/karate/login&reg/CreateUser.feature"
						})
public class LoginAndRegistrationRunner {
		BaseFunctions baseFunctions = new BaseFunctions();

	   @Test
	   public void testParallel() {

	      Results results = Runner.parallel(getClass(), 10, "target/surefire-reports");

	      baseFunctions.generateReport(results.getReportDir());
	      assertTrue(results.getErrorMessages(), results.getFailCount() == 0);	    
	   }
}