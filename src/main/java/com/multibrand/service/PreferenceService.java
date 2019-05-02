package com.multibrand.service;

import java.rmi.RemoteException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.domain.ActivatePhoneRequest;
import com.multibrand.domain.ActivateResponse;
import com.multibrand.domain.AllBPRequest;
import com.multibrand.domain.AllBPResponse;
import com.multibrand.domain.ContactInfoRequest;
import com.multibrand.domain.ContactInfoResponse;
import com.multibrand.domain.ContactPrefsRequest;
import com.multibrand.domain.ContactPrefsResponse;
import com.multibrand.domain.DeactivatePhoneRequest;
import com.multibrand.domain.DeactivateResponse;
import com.multibrand.domain.PreferencesDomain;
import com.multibrand.domain.PreferencesDomainPortBindingStub;
import com.multibrand.domain.ProfileDomain;
import com.multibrand.domain.ProfileDomainPortBindingStub;
import com.multibrand.domain.SaveUpdateContactPrefsRequest;
import com.multibrand.domain.SaveUpdateContactPrefsResponse;
import com.multibrand.domain.SendActivationRequest;
import com.multibrand.domain.SendActivationResponse;
import com.multibrand.domain.SendNewActivationRequest;
import com.multibrand.domain.SendNewActivationResponse;
import com.multibrand.domain.SmsOptInOutEligRequest;
import com.multibrand.domain.SmsOptInOutEligResponse;
import com.multibrand.domain.SmsOptInOutRequest;
import com.multibrand.domain.SmsOptInOutResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.SMSOptInOutEligibilityRequest;

/**
 * 
 * @author mshukla1
 *
 * This class is responsible for fetching information from NRGWS PreferencesDomain
 */

@Service
public class PreferenceService extends BaseAbstractService {

	
private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	
	protected PreferencesDomain getPreferencesDoamin(){
		
		return (PreferencesDomain)getServiceProxy(PreferencesDomainPortBindingStub.class, 
				PREFERENCE_SERVICE_ENDPOINT_URL);
	}
	
	protected ProfileDomain getProfileDomainProxy() {

		return (ProfileDomain) getServiceProxy(
				ProfileDomainPortBindingStub.class,
				PROFILE_SERVICE_ENDPOINT_URL);
	}
	/**
	 * Calling PreferencesDomain activatePhone operation
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public ActivateResponse activatePhone(ActivatePhoneRequest request,String sessionId)throws Exception{
	
		PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		
		ActivateResponse response = null;
		try{
			response= prefDomainProxy.activatePhone(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("activatePhone", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("activatePhone", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		
		utilityloggerHelper.logTransaction("activatePhone", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * Calling PreferencesDomain sendActivate operation
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public SendActivationResponse sendActivation(SendActivationRequest request,String sessionId)throws Exception{
		
		PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		
		SendActivationResponse response = null;
		try{
			response= prefDomainProxy.sendActivate(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("sendActivation", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("sendActivation", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		
		utilityloggerHelper.logTransaction("sendActivation", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	
	/**
	 * Calling PreferencesDomain sendNewActivate operation
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public SendNewActivationResponse sendNewActivation(SendNewActivationRequest request, String sessionId)throws Exception{
		
		PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		
		SendNewActivationResponse response = null;
		try{
			response= prefDomainProxy.sendNewActivate(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("sendNewActivation", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("sendNewActivation", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		
		utilityloggerHelper.logTransaction("sendNewActivation", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * Calling PreferencesDomain getContactInformation operation
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public ContactInfoResponse getContactInformation(ContactInfoRequest request, String sessionId)throws Exception{
		
		PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		
		ContactInfoResponse response = null;
		
		try{
			response= prefDomainProxy.getContactInformation(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getContactInformation", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getContactInformation", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		
		utilityloggerHelper.logTransaction("getContactInformation", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * Calling ProfileDomain fetchAllBPForPhoneNumber operation
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public AllBPResponse getAllBPs(AllBPRequest request,String sessionId)throws Exception{
		
		ProfileDomain profileDomain = getProfileDomainProxy();
		long startTime = CommonUtil.getStartTime();
		
		AllBPResponse response=null;
		try{
		   response = profileDomain.fetchAllBPForPhoneNumber(request);	
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getAllBPs", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getAllBPs", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		return response;
	}
	
	/**
	 * Calling PreferencesDomain deactivatePhone operation
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public DeactivateResponse deactivatePhone(DeactivatePhoneRequest request,String sessionId)throws Exception{
		
		PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		
		DeactivateResponse response = null;
		try{
			response= prefDomainProxy.deactivatePhone(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("deactivatePhone", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("deactivatePhone", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		
		utilityloggerHelper.logTransaction("deactivatePhone", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	/**
	 * Calling Preference domain readContactPrefs method
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public ContactPrefsResponse readContactPrefs(ContactPrefsRequest request,String sessionId)throws Exception{
		
		PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		
		ContactPrefsResponse response = null;
		try{
			response= prefDomainProxy.readContactAlertPrefs(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("readContactAlertPrefs", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("readContactAlertPrefs", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			throw ex;
		}
		
		utilityloggerHelper.logTransaction("readContactAlertPrefs", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
		
	}
	
	
	/**
     * Calling Preference domain saveUpdateContactPrefs
     * @param request
     * @param sessionId
     * @return
     * @throws Exception
     */
    public SaveUpdateContactPrefsResponse saveUpdateContactPrefs(SaveUpdateContactPrefsRequest request,String sessionId)throws Exception{
    	
    	PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		SaveUpdateContactPrefsResponse response = null;
		
		try{
			response = prefDomainProxy.saveUpdateContactAlertPrefs(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("saveUpdateContactPrefs", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("saveUpdateContactPrefs", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		}
		
		utilityloggerHelper.logTransaction("saveUpdateContactPrefs", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
    }
    
    /** US12887 - DK | SMS ALERTS | 10/15/2018 **/
    /**
     * This method returns OPT IN  and OPT OUT Eligibility from CCS via NRGWS Service Layer for given BP/CA combination. NRGWS Preference domain checkOptInOutEligibility
     * @param request   - The SMSOptInOutEligibilityRequest Request
     * @param sessionId  - The current SessionID
     * @return  SmsOptInOutEligResponse  -The SmsOptInOutEligResponse Object
     * @throws Exception   - In case of any exceptions
     */
    public  SmsOptInOutEligResponse checkOptInOutEligibility(SMSOptInOutEligibilityRequest request,String sessionId)throws Exception{
    	
    	PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		SmsOptInOutEligResponse response = null;
		SmsOptInOutEligRequest srvcRequest  = createRequest(request);
		try{
			response = prefDomainProxy.checkOptInOutEligibility(srvcRequest);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("checkOptInOutEligibility", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("checkOptInOutEligibility", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		}
		if (response != null) {
			utilityloggerHelper.logTransaction("checkOptInOutEligibility", false, request, response,
					response.getMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId,
					request.getCompanyCode());
		}
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
    }

	private SmsOptInOutEligRequest createRequest(SMSOptInOutEligibilityRequest request) {
		SmsOptInOutEligRequest req = new SmsOptInOutEligRequest();
		req.setBusinessPartner(request.getBusinessPartner());
		req.setCompanyCode(request.getCompanyCode());
		req.setBrandId(request.getBrandId());
		req.setMobileNumber(request.getMobileNumber());
		req.setContractAccount(request.getContractAccount());
		req.setOptInFlag(request.getOptInFlag());
		req.setRequestCode(request.getRequestCode());
		req.setChannel(request.getChannel());
		req.setHouseNumber(request.getHouseNumber());		
		return req;
	}
	/** END US12887 - DK | SMS ALERTS | 10/15/2018 **/
	
	/** US12884 - DK | SMS ALERTS | 10/15/2018 **/
	/**
	 * This method is used to call OPT IN and OPT OUT service 
	 * @param request  The SmsOptInOutRequest Object
	 * @return response The SmsOptInOutResponse Object
	 * @throws Exception - Throws error in case of any exceptions
	 */
	public SmsOptInOutResponse optInOutService(SmsOptInOutRequest request, String sessionId){
		PreferencesDomain prefDomainProxy = getPreferencesDoamin();
		long startTime = CommonUtil.getStartTime();
		SmsOptInOutResponse response = null;
		try{
			logger.info(" PreferencesDomain :: optInOutService called for brand id ["+request.getBrand()+"] BP ["+request.getBPNumber()+"] CA ["+request.getCANumber()+"] ");
			response = prefDomainProxy.optInOut(request);
		}catch(RemoteException ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("optInOutService", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getBukrs());
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("optInOutService", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getBukrs());
		}
		
		utilityloggerHelper.logTransaction("optInOutService", false, request,response, response.getMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, request.getBukrs());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	/** END | US12887 - DK | SMS ALERTS | 10/15/2018 **/
	
}
