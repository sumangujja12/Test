package com.multibrand.bo;

import java.rmi.RemoteException;

import org.apache.commons.lang.StringUtils;
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
import com.multibrand.util.JavaBeanUtil;
import com.multibrand.vo.request.StraxContractCancelRequest;
import com.multibrand.vo.request.StraxInvoiceRequest;
import com.multibrand.vo.response.StraxContractCancelResponse;
import com.multibrand.vo.response.StraxInvoiceResponse;

@Component
public class StraxBO extends BaseAbstractService implements Constants{

	@Autowired
	private StraxAccountService straxAccountService;
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public StraxContractCancelResponse cancelStraxContract(StraxContractCancelRequest request, String sessionId) {
		
		StraxContractCancelResponse response = new StraxContractCancelResponse();
		StraxCancelAccountResponse straxCancelAccountResponse = null;
		try {
			StraxCancelAccountRequest straxCancelAccountRequest = new StraxCancelAccountRequest();
			straxCancelAccountRequest.setStraxLeadID(request.getStraxLeadID());
			straxCancelAccountRequest.setCaNumber(request.getCaNumber());
			straxCancelAccountRequest.setCancellationDate(request.getCancellationDate());
			straxCancelAccountResponse = straxAccountService.cancelStraxContract(straxCancelAccountRequest, "0121", sessionId);
			if(StringUtils.isNotBlank(straxCancelAccountResponse.getErrorCode())){
				
				logger.info("cancelStraxContract failed with error code:::::{}",straxCancelAccountResponse.getErrorCode());
				logger.info("cancelStraxContract failed with error::::::{}",straxCancelAccountResponse.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(straxCancelAccountResponse.getErrorMessage());
			}
			else
			{
				logger.info("cancelStraxContract() call successful:::::"); 
				JavaBeanUtil.copy(straxCancelAccountResponse, response);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		} catch (RemoteException e) {
			logger.error("RemoteException::::: StraxBO.cancelStraxContract()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: StraxBO.cancelStraxContract()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		return response;
		
	
	}
	
	public StraxInvoiceResponse invoiceStraxContract(StraxInvoiceRequest request, String sessionId) {
		
		StraxInvoiceResponse response = new StraxInvoiceResponse();
		StraxInvoiceAccountResponse straxInvoiceAccountResponse = null;
		
		try {
			StraxInvoiceAccountRequest straxInvoiceAccountRequest = new StraxInvoiceAccountRequest();
			straxInvoiceAccountRequest.setStraxLeadID(request.getStraxLeadID());
			straxInvoiceAccountRequest.setCaNumber(request.getCaNumber());
			straxInvoiceAccountRequest.setTotalInvoiceAmount(request.getTotalAmount());
						
			straxInvoiceAccountResponse = straxAccountService.invoiceStraxContract(straxInvoiceAccountRequest, "0121", sessionId);
			if(StringUtils.isNotBlank(straxInvoiceAccountResponse.getErrorCode())){
				
				logger.info("invoiceStraxContract failed with ErrorCode:::::{}",straxInvoiceAccountResponse.getErrorCode());
				logger.info("invoiceStraxContract failed with ErrorMessage::::::{}",straxInvoiceAccountResponse.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(straxInvoiceAccountResponse.getErrorMessage());
			}
			else
			{
				logger.info("invoiceStraxContract() call successful:::::"); 
				JavaBeanUtil.copy(straxInvoiceAccountResponse, response);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		} catch (RemoteException e) {
			logger.error("RemoteException::::: StraxBO.invoiceStraxContract()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: StraxBO.invoiceStraxContract()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;
		
	
	}
}
