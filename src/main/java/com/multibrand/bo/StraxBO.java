package com.multibrand.bo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.StraxCancelAccountRequest;
import com.multibrand.domain.StraxCancelAccountResponse;
import com.multibrand.domain.StraxInvoiceAccountRequest;
import com.multibrand.domain.StraxInvoiceAccountResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.StraxAccountService;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.SecurityContractCancelRequest;
import com.multibrand.vo.request.SecurityInvoiceRequest;

@Component
public class StraxBO extends BaseAbstractService implements Constants{

	@Autowired
	private StraxAccountService straxAccountService;
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public StraxCancelAccountResponse cancelStraxContract(SecurityContractCancelRequest securityContractCancelRequest, String sessionId) {
		
		StraxCancelAccountResponse straxCancelAccountResponse = null;
		try {
			StraxCancelAccountRequest straxCancelAccountRequest = new StraxCancelAccountRequest();
			straxCancelAccountRequest.setStraxLeadID(securityContractCancelRequest.getStraxLeadID());
			straxCancelAccountRequest.setCaNumber(securityContractCancelRequest.getCaNumber());
			straxCancelAccountRequest.setCancellationDate(securityContractCancelRequest.getCancellationDate());
			straxAccountService.cancelStraxContract(straxCancelAccountRequest, "0121", sessionId);
		} catch (Exception e) {
			
			// set error codes
			throw new OAMException(200, e.getMessage(), straxCancelAccountResponse);
		}
		
		return straxCancelAccountResponse;
		
	
	}
	
	public StraxInvoiceAccountResponse invoiceStraxContract(SecurityInvoiceRequest securityInvoiceRequest, String sessionId) {
		
		StraxInvoiceAccountResponse straxInvoiceAccountResponse = null;
		
		try {
			StraxInvoiceAccountRequest straxInvoiceAccountRequest = new StraxInvoiceAccountRequest();
			straxInvoiceAccountRequest.setStraxLeadID(securityInvoiceRequest.getStraxLeadID());
			straxInvoiceAccountRequest.setCaNumber(securityInvoiceRequest.getCaNumber());
			straxInvoiceAccountRequest.setTotalInvoiceAmount(securityInvoiceRequest.getTotalAmount());
			
			
			straxAccountService.invoiceStraxContract(straxInvoiceAccountRequest, "0121", sessionId);
		} catch (Exception e) {
			
			// set error codes
			throw new OAMException(200, e.getMessage(), straxInvoiceAccountResponse);
		}
		
		return straxInvoiceAccountResponse;
		
	
	}
}
