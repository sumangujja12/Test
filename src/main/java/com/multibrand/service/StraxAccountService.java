package com.multibrand.service;

import java.rmi.RemoteException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.bo.StraxBO;
import com.multibrand.domain.SecurityAccountsDomain;
import com.multibrand.domain.StraxCancelAccountRequest;
import com.multibrand.domain.StraxCancelAccountResponse;
import com.multibrand.domain.StraxInvoiceAccountRequest;
import com.multibrand.domain.StraxInvoiceAccountResponse;

import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;



@Service
public class StraxAccountService  extends BaseAbstractService {
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	protected SecurityAccountsDomain getSecurityAccountsDomainProxy() {

		return (SecurityAccountsDomain) getServiceProxy(com.multibrand.domain.SecurityAccountsDomainPortBindingStub.class,
				SECURITY_SERVICE_ENDPOINT_URL);
	}

	public StraxCancelAccountResponse cancelStraxContract(StraxCancelAccountRequest straxCancelAccountRequest, String companyCode, String sessionId) throws Exception {

		StraxCancelAccountResponse straxCancelAccountResponse = null;
		long startTime = CommonUtil.getStartTime();
		
		try {
			SecurityAccountsDomain proxy = getSecurityAccountsDomainProxy();
			straxCancelAccountResponse = proxy.executeCancelAccount(straxCancelAccountRequest);
			
		}

		catch(RemoteException ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("cancelStraxContract", false, straxCancelAccountRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(straxCancelAccountRequest));
			throw ex;
		}
		catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("cancelStraxContract", false, straxCancelAccountRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(straxCancelAccountRequest));
			throw ex;
		}
		utilityloggerHelper.logTransaction("cancelStraxContract", false, straxCancelAccountRequest,straxCancelAccountResponse, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(straxCancelAccountRequest));
			logger.debug(XmlUtil.pojoToXML(straxCancelAccountResponse));
		}
		

		return straxCancelAccountResponse;

	}
	
	public StraxInvoiceAccountResponse invoiceStraxContract(StraxInvoiceAccountRequest straxInvoiceAccountRequest, String companyCode, String sessionId) throws Exception {

		StraxInvoiceAccountResponse straxInvoiceAccountResponse = null;
		long startTime = CommonUtil.getStartTime();
		
		try {
			SecurityAccountsDomain proxy = getSecurityAccountsDomainProxy();
			straxInvoiceAccountResponse = proxy.invoiceSecurityAccount(straxInvoiceAccountRequest);
		
		}

		catch(RemoteException ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("invoiceStraxContract", false, straxInvoiceAccountRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(straxInvoiceAccountRequest));
			throw ex;
		}
		catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("invoiceStraxContract", false, straxInvoiceAccountRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(straxInvoiceAccountRequest));
			throw ex;
		}
		utilityloggerHelper.logTransaction("invoiceStraxContract", false, straxInvoiceAccountRequest,straxInvoiceAccountResponse, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(straxInvoiceAccountRequest));
			logger.debug(XmlUtil.pojoToXML(straxInvoiceAccountResponse));
		}

		return straxInvoiceAccountResponse;

	}
}
