package com.multibrand.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.multibrand.bo.BillingBO;
import com.multibrand.vo.request.SaveAMBSingupRequestVO;
import com.multibrand.vo.response.billingResponse.AccountDetailsProp;


public class AMBMailServiceWorker implements Runnable {
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	private final SaveAMBSingupRequestVO request;
	private final AccountDetailsProp accountDetails;
	private final String sessionId;
	

	BillingBO billingBO = new BillingBO();
	
	public AMBMailServiceWorker(SaveAMBSingupRequestVO request, AccountDetailsProp accountDetails, String sessionId) {
		this.request=request;
		this.accountDetails=accountDetails;
		this.sessionId=sessionId;
	}

	@Override
	public void run() {
		try {
			billingBO.sendConfirmationMailForAmbSignup(request,accountDetails,sessionId);
		} catch (Exception e) {
			logger.error("Exception occured in AMBMailServiceWorker " +e.getStackTrace());
		}
		
	}

}
