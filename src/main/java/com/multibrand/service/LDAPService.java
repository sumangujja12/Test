package com.multibrand.service;

import java.rmi.RemoteException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.domain.ChangeUsrNameRequest;
import com.multibrand.domain.ChangeUsrNameResponse;
import com.multibrand.domain.LDAPDomain;
import com.multibrand.domain.LDAPDomainPortBindingStub;
import com.multibrand.domain.SyncLDAPRequest;
import com.multibrand.domain.SyncLDAPResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;

/**
 * 
 * @author kdeshmu1
 *
 * This class is responsible for fetching information from Redbull Service LDAPDomain 
 */

@Service
public class LDAPService extends BaseAbstractService {
	
	Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	/**
	 * This will return LDAPDomainProxy and set EndPoint URL
	 * @return LDAPDomainProxy The LDAPDomainProxy Object
	 */
	protected LDAPDomain getLDAPDomainProxy(){
        
		return (LDAPDomain) getServiceProxy(LDAPDomainPortBindingStub.class,LDAP_SERVICE_ENDPOINT_URL);
	}
	
	/**
	 * @author Kdeshmu1
	 * @param request
	 * @return UpdateAddressResponse
	 * @throws Exception 
	 */
	public ChangeUsrNameResponse changeUsername(ChangeUsrNameRequest request, String companyCode, String sessionId) throws Exception{
		
		LDAPDomain proxy = getLDAPDomainProxy();
		long startTime = CommonUtil.getStartTime();
		ChangeUsrNameResponse response=null;
		try{
			response= proxy.changeUserName(request);
		}catch(RemoteException ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("changeUsername", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("changeUsername", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}

        utilityloggerHelper.logTransaction("changeUsername", false, request,response, response.getStrErrorText(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
        if(logger.isDebugEnabled()){
	        logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
        }
		return response;
	}
	
	/**
	 * Synchronize LDAP with given details.  In GME Residential, this was used as part of Account Lockout functionality to update the invalid login count in LDAP
	 * 
	 * @param syncLDAPRequest
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public SyncLDAPResponse synchronizeLDAP(SyncLDAPRequest syncLDAPRequest, String companyCode, String sessionId) throws Exception {
		
		LDAPDomain proxy = getLDAPDomainProxy();
		long startTime = CommonUtil.getStartTime();
		SyncLDAPResponse syncLDAPResponse = null;

		try{
			syncLDAPResponse= proxy.synchronizeLDAP(syncLDAPRequest);
		}catch(RemoteException ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction(LOG_TXN_GME_RES_SYNC_LDAP, false, syncLDAPRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(syncLDAPRequest));
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction(LOG_TXN_GME_RES_SYNC_LDAP, false, syncLDAPRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(syncLDAPRequest));
		}
		if (syncLDAPResponse != null){
        utilityloggerHelper.logTransaction(LOG_TXN_GME_RES_SYNC_LDAP, false, syncLDAPRequest, syncLDAPResponse, syncLDAPResponse.getStrErrorText(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		}
        if(logger.isDebugEnabled()){
	        logger.debug(XmlUtil.pojoToXML(syncLDAPRequest));
			logger.debug(XmlUtil.pojoToXML(syncLDAPResponse));
        }
		return syncLDAPResponse;
	}

}
