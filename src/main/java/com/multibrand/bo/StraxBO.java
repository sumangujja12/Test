package com.multibrand.bo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.multibrand.vo.request.InvoiceItemCategory;
import com.multibrand.vo.request.StraxContractCancelRequest;
import com.multibrand.vo.request.StraxInvoiceRequest;
import com.multibrand.vo.response.StraxContractCancelResponse;
import com.multibrand.vo.response.StraxInvoiceResponse;

@Component
public class StraxBO extends BaseAbstractService implements Constants{

	@Autowired
	private StraxAccountService straxAccountService;

	public StraxContractCancelResponse cancelStraxContract(StraxContractCancelRequest request, String sessionId) {
		
		StraxContractCancelResponse response = new StraxContractCancelResponse();
		StraxCancelAccountResponse straxCancelAccountResponse = null;
		try {
			StraxCancelAccountRequest straxCancelAccountRequest = new StraxCancelAccountRequest();
			straxCancelAccountRequest.setStraxLeadID(request.getStraxLeadID());
			straxCancelAccountRequest.setCaNumber(request.getCaNumber());
			straxCancelAccountRequest.setCancellationDate(request.getCancellationDate());
			straxCancelAccountResponse = straxAccountService.cancelStraxContract(straxCancelAccountRequest, "0121", sessionId);
			if(!StringUtils.equals(straxCancelAccountResponse.getXCode(),"00"))
			{
				Map<String, String> xCodeValues = new HashMap<>();
				xCodeValues.put("01", CANCEL_CONTRACT_XCODE_01);
				xCodeValues.put("02", CANCEL_CONTRACT_XCODE_02);

				response.setErrorCode(straxCancelAccountResponse.getXCode());
				response.setErrorDescription(xCodeValues.get(straxCancelAccountResponse.getXCode()));
				response.setResultCode("");
				logger.info("cancelStraxContract failed with error code:::::{}",straxCancelAccountResponse.getErrorCode());
				logger.info("cancelStraxContract failed with error::::::{}",straxCancelAccountResponse.getErrorMessage());
			}
			else if(StringUtils.equals(straxCancelAccountResponse.getXCode(),"00"))
			{
				logger.info("cancelStraxContract() call successful:::::{}",straxCancelAccountResponse.getXCode());
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
			straxInvoiceAccountRequest.setInvoiceID(request.getInvoiceID());
			List<com.multibrand.domain.InvoiceItemCategory>invoiceItemCat = new ArrayList<>();
			for (InvoiceItemCategory invoiceItemCategory : request.getInvoiceItems()) {
				com.multibrand.domain.InvoiceItemCategory eachInvoiceItem = new com.multibrand.domain.InvoiceItemCategory();
				eachInvoiceItem.setAmount(invoiceItemCategory.getAmount());
				eachInvoiceItem.setItemCategory(invoiceItemCategory.getItemCategory());
				invoiceItemCat.add(eachInvoiceItem);
			}
			
			int len = request.getInvoiceItems().size();
			com.multibrand.domain.InvoiceItemCategory[] invoiceItmCategory = new com.multibrand.domain.InvoiceItemCategory[invoiceItemCat.size()];
			for (int i = 0; i < len; i++) {
				invoiceItmCategory[i] = invoiceItemCat.get(i);
			}
			straxInvoiceAccountRequest.setInvoiceItems(invoiceItmCategory);
			straxInvoiceAccountResponse = straxAccountService.invoiceStraxContract(straxInvoiceAccountRequest, "0121", sessionId);
			if(!StringUtils.equals(straxInvoiceAccountResponse.getXCode(),"00")){
				
				Map<String, String> xCodeValues = new HashMap<>();
				xCodeValues.put("01", INVOICE_CREATION_XCODE_01);
				xCodeValues.put("02", INVOICE_CREATION_XCODE_02);
				xCodeValues.put("03", INVOICE_CREATION_XCODE_03);
				xCodeValues.put("04", INVOICE_CREATION_XCODE_04);
				
				response.setErrorCode(straxInvoiceAccountResponse.getXCode());
				response.setErrorDescription(xCodeValues.get(straxInvoiceAccountResponse.getXCode()));
				response.setResultCode("");
				logger.info("invoiceStraxContract failed with ErrorCode:::::{}",response.getErrorCode());
				logger.info("invoiceStraxContract failed with ErrorMessage::::::{}",response.getErrorDescription());
				
			}
			else if(StringUtils.equals(straxInvoiceAccountResponse.getXCode(),"00"))
			{
				logger.info("invoiceStraxContract() call successful:::::{}",straxInvoiceAccountResponse.getXCode()); 
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
