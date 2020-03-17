package com.multibrand.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.exception.NRGException;
import com.multibrand.exception.OAMException;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.GMDService;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.billingResponse.GetAccountDetailsResponse;


/**
 * This BO class is to handle all the GMD Related API calls.
 * 
 * @author rpendur1
 */
@Component
public class GMDBO extends BaseAbstractService implements Constants{

	
	
	@Autowired
	private GMDService gmdService;
	

	public GetAccountDetailsResponse getGMDStatementDetails(String accountNumber, String companyCode, 
			String esiId, String year, String month ,String sessionId) {

		GetAccountDetailsResponse accountDetailsResp = new GetAccountDetailsResponse();
		
		try {			
			 gmdService.getGMDStatementDetails(accountNumber, companyCode, 
					esiId,  year, month ,sessionId);
							
		} catch (NRGException e) {
			logger.error("Exception occured in getGMDStatementDetails : " +e.getStackTrace());
			accountDetailsResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			accountDetailsResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), accountDetailsResp);
		}
		return accountDetailsResp;

	}
}