package com.multibrand.bo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dao.PreferencesDAO;
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
import com.multibrand.domain.EmailInformation;
import com.multibrand.domain.SaveUpdateContactPrefsRequest;
import com.multibrand.domain.SaveUpdateContactPrefsResponse;
import com.multibrand.domain.SendActivationRequest;
import com.multibrand.domain.SendActivationResponse;
import com.multibrand.domain.SendNewActivationRequest;
import com.multibrand.domain.SendNewActivationResponse;
import com.multibrand.domain.SmsOptInOutEligResponse;
import com.multibrand.domain.SmsOptInOutRequest;
import com.multibrand.domain.SmsOptInOutResponse;
import com.multibrand.dto.request.ActivationRequest;
import com.multibrand.dto.request.DeactivateRequest;
import com.multibrand.dto.request.GetAllBPRequest;
import com.multibrand.dto.request.GetContactInfoRequest;
import com.multibrand.dto.request.ReadContactAlertRequest;
import com.multibrand.dto.request.SaveUpdateAlertPrefRequest;
import com.multibrand.dto.request.SendActivateRequest;
import com.multibrand.dto.request.SendNewActivateRequest;
import com.multibrand.exception.OAMException;
import com.multibrand.service.PreferenceService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.JavaBeanUtil;
import com.multibrand.vo.request.OptInOptOutRequest;
import com.multibrand.vo.request.PrivacyPreferencesRequest;
import com.multibrand.vo.request.SMSOptInOutEligibilityRequest;
import com.multibrand.vo.response.ActivationResponse;
import com.multibrand.vo.response.DeactivationResponse;
import com.multibrand.vo.response.GetAllBPsResponse;
import com.multibrand.vo.response.GetContactAlertPrefsResponse;
import com.multibrand.vo.response.GetContactInfoResponse;
import com.multibrand.vo.response.OptInOptOutResponse;
import com.multibrand.vo.response.PhoneInformation;
import com.multibrand.vo.response.PrivacyPreferenceResponse;
import com.multibrand.vo.response.SMSOptInOutEligibilityResponse;
import com.multibrand.vo.response.UpdationResponse;



/**
 * This class is handling Preference related API calls
 * @author mshukla1
 *
 */
@Component
public class PreferenceBO implements Constants {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	PreferenceService prefService;
	
	@Autowired
	private PreferencesDAO preferencesDAO;
	
	
	/**
	 * This method is used to Activate the Phone on SMS Alerts
	 * @param activationCode
	 * @param brand_Id
	 * @param businessPartner
	 * @param contactNumber
	 * @param contract
	 * @param contractAccount
	 * @param language
	 * @param userId
	 * @param userUniqueId
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public ActivationResponse activatePhone(ActivationRequest deactivateRequest,String sessionId){
		
		ActivateResponse activateResponse = null;
		ActivationResponse response = new ActivationResponse();

		ActivatePhoneRequest request = new ActivatePhoneRequest();
		
		request.setActivationCode(deactivateRequest.getActivationCode());
		request.setBrand_Id(deactivateRequest.getBrandId());
		request.setBusinessPartner(deactivateRequest.getBusinessPartner());
		request.setCompanyCode(deactivateRequest.getCompanyCode());
		request.setContactNumber(deactivateRequest.getContactNumber());
		request.setContract(deactivateRequest.getContract());
		request.setContractAccount(deactivateRequest.getContractAccount());
		request.setLanguage(deactivateRequest.getLanguage());
		request.setUserId(deactivateRequest.getUserId());
		request.setUserUniqueId(deactivateRequest.getUserUniqueId());
		
		try{
			logger.info("Before service call :::::");
			activateResponse = prefService.activatePhone(request, sessionId);
			logger.info("After service call :::::");
			if(StringUtils.isNotBlank(activateResponse.getErrorCode())){
				logger.info("Response from prefService::::");
				logger.info("ErrorCode:::::"+activateResponse.getErrorCode());
				logger.info("ErrorMessage::::::"+activateResponse.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(activateResponse.getErrorMessage());
			}
			else
			{
				logger.info("activatePhone() call successful:::::"); 
				JavaBeanUtil.copy(activateResponse, response);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		}catch (RemoteException e) {
			logger.error("RemoteException::::: PreferenceBO.activatePhone()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: PreferenceBO.activatePhone()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;
	}
	
	/**
	 * This method is Used to Send the Activation Code to the Phone
	 * @param brand_Id
	 * @param businessPartner
	 * @param resendActivation
	 * @param contactNumber
	 * @param contract
	 * @param contractAccount
	 * @param language
	 * @param userId
	 * @param userUniqueId
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public ActivationResponse sendActivate(SendActivateRequest sendActivateReq,String sessionId){
		
		ActivationResponse response = new ActivationResponse();
		SendActivationResponse sendActResponse = null;
		
		SendActivationRequest request = new SendActivationRequest();
		
		request.setBrandId(sendActivateReq.getBrandId());
		request.setBusinessPartner(sendActivateReq.getBusinessPartner());
		request.setCompanyCode(sendActivateReq.getCompanyCode());
		request.setContactNumber(sendActivateReq.getContactNumber());
		request.setContract(sendActivateReq.getContract());
		request.setContractAccount(sendActivateReq.getContractAccount());
		request.setLanguage(sendActivateReq.getLanguage());
		request.setResendActivation(sendActivateReq.getResendActivation());
		request.setUserId(sendActivateReq.getUserId());
		request.setUserUniqueId(sendActivateReq.getUserUniqueId());
		
		try{
			logger.info("Before service call :::::sendActivation");
			sendActResponse = prefService.sendActivation(request, sessionId);
			logger.info("After service call :::::sendActivation");
			if(StringUtils.isNotBlank(sendActResponse.getErrorCode())){
				logger.info("Response from prefService::::");
				logger.info("ErrorCode:::::"+sendActResponse.getErrorCode());
				logger.info("ErrorMessage::::::"+sendActResponse.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(sendActResponse.getErrorMessage());
			}
			else
			{
				logger.info("sendActivation() call successful:::::"); 
				JavaBeanUtil.copy(sendActResponse, response);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		}catch (RemoteException e) {
			logger.error("RemoteException::::: PreferenceBO.sendActivation()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: PreferenceBO.sendActivation()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		return response;
	}
	
	
	/**
	 * This method is Used to send a new Activation Code to the Phone
	 * @param brand_Id
	 * @param businessPartner
	 * @param resendActivation
	 * @param contactNumber
	 * @param contract
	 * @param contractAccount
	 * @param language
	 * @param userId
	 * @param userUniqueId
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public ActivationResponse sendNewActivate(SendNewActivateRequest sendNewActivateReq,String sessionId){
		
		ActivationResponse response = new ActivationResponse();
		SendNewActivationResponse sendNewActResponse = null;
		
		SendNewActivationRequest request = new SendNewActivationRequest();
		
		request.setBrandId(sendNewActivateReq.getBrandId());
		request.setBusinessPartner(sendNewActivateReq.getBusinessPartner());
		request.setCompanyCode(sendNewActivateReq.getCompanyCode());
		request.setContactNumber(sendNewActivateReq.getContactNumber());
		request.setContract(sendNewActivateReq.getContract());
		request.setContractAccount(sendNewActivateReq.getContractAccount());
		request.setLanguage(sendNewActivateReq.getLanguage());
		request.setResendActivation(sendNewActivateReq.getResendActivation());
		request.setUserId(sendNewActivateReq.getUserId());
		request.setUserUniqueId(sendNewActivateReq.getUserUniqueId());
		
		try{
			logger.info("Before service call :::::sendActivation");
			sendNewActResponse = prefService.sendNewActivation(request, sessionId);
			logger.info("After service call :::::sendActivation");
			if(StringUtils.isNotBlank(sendNewActResponse.getErrorCode())){
				logger.info("Response from prefService::::");
				logger.info("ErrorCode:::::"+sendNewActResponse.getErrorCode());
				logger.info("ErrorMessage::::::"+sendNewActResponse.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(sendNewActResponse.getErrorMessage());
			}
			else
			{
				logger.info("sendNewActivation() call successful:::::"); 
				JavaBeanUtil.copy(sendNewActResponse, response);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		}catch (RemoteException e) {
			logger.error("RemoteException::::: PreferenceBO.sendNewActivation()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: PreferenceBO.sendNewActivation()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		return response;
	}
	
	/**
	 * This method is used to get status of Account
	 * @param businessPartner
	 * @param companyCode
	 * @param contract
	 * @param contractAccount
	 * @param userUniqueId
	 * @param sessionId
	 * @return
	 */
	public GetContactInfoResponse getContactInformation(GetContactInfoRequest contactInfoReq,String sessionId){
		
		GetContactInfoResponse response = new GetContactInfoResponse();
		ContactInfoResponse contactInfoResp = null;
		ContactInfoRequest request = new ContactInfoRequest();
		
		request.setBusinessPartner(contactInfoReq.getBusinessPartner());
		request.setCompanyCode(contactInfoReq.getCompanyCode());
		request.setContract(contactInfoReq.getContract());
		request.setContractAccount(contactInfoReq.getContractAccount());
		request.setUserUniqueId(contactInfoReq.getUserUniqueId());
		
		try{
			logger.info("Before service call :::::getContactInformation");
			contactInfoResp = prefService.getContactInformation(request, sessionId);
			logger.info("After service call :::::getContactInformation");
			if(!contactInfoResp.getErrorCode().equals(NO_ERROR_CODE)){
				logger.info("Response from prefService::::");
				logger.info("ErrorCode:::::"+contactInfoResp.getErrorCode());
				logger.info("ErrorMessage::::::"+contactInfoResp.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(contactInfoResp.getErrorMessage());
			}
			else
			{
				logger.info("getContactInformation() call successful:::::"); 
				JavaBeanUtil.copy(contactInfoResp, response);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		}catch (RemoteException e) {
			logger.error("RemoteException::::: PreferenceBO.getContactInformation()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: PreferenceBO.getContactInformation()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		return response;
	}
	
	/**
	 * This method is used to get all BPs associated with phoneNumber
	 * @param companyCode
	 * @param phoneNumber
	 * @param sessionId
	 * @return
	 */
	public GetAllBPsResponse getAllBP(GetAllBPRequest getAllBPReq,String sessionId){
		
		GetAllBPsResponse response = new GetAllBPsResponse();
		AllBPResponse allBpsResp = null;
		
		AllBPRequest request = new AllBPRequest();
		request.setCompanyCode(getAllBPReq.getCompanyCode());
		request.setPhoneNumber(getAllBPReq.getContactNumber());
		
		try{
			logger.info("Before service call :::::getAllBP");
			logger.info("PhoneNumber in getAllBP call::: "+request.getPhoneNumber());
			allBpsResp= prefService.getAllBPs(request, sessionId);
			logger.info("After service call :::::getAllBP");
			
			if(StringUtils.isNotBlank(allBpsResp.getErrorCode()))
			{
				logger.info("Response from profileDomain::::");
				logger.info("ErrorCode:::::"+allBpsResp.getErrorCode());
				logger.info("ErrorMessage::::::"+allBpsResp.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(allBpsResp.getErrorMessage());
			}
			else
			{
				logger.info("getAllBP() call successful:::::"); 
				JavaBeanUtil.copy(allBpsResp, response);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		}catch (RemoteException e) {
			logger.error("RemoteException::::: PreferenceBO.getAllBP()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: PreferenceBO.getAllBP()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;
		
	}
	
	/**
	 * This method is used to Deactivate the Phone on SMS Alerts
	 * @param brand_Id
	 * @param businessPartner
	 * @param contactNumber
	 * @param contract
	 * @param contractAccount
	 * @param language
	 * @param userId
	 * @param userUniqueId
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public DeactivationResponse deactivatePhone(DeactivateRequest request,String sessionId){
		
		DeactivationResponse response = new DeactivationResponse();
        DeactivateResponse deactivateResp=null;
        
        DeactivatePhoneRequest deactivateRequest = new DeactivatePhoneRequest();
        
        deactivateRequest.setBrandId(request.getBrandId());
        deactivateRequest.setBusinessPartner(request.getBusinessPartner());
        deactivateRequest.setCompanyCode(request.getCompanyCode());
        deactivateRequest.setContactNumber(request.getContactNumber());
        deactivateRequest.setContract(request.getContract());
        deactivateRequest.setContractAccount(request.getContractAccount());
        deactivateRequest.setLanguage(request.getLanguage());
        deactivateRequest.setUserId(request.getUserId());
        deactivateRequest.setUserUniqueId(request.getUserUniqueId());
        
        try{
			logger.info("Calling deactivatePhone for CA/BP :::::"
					+ deactivateRequest.getContractAccount() + "/"
					+ deactivateRequest.getBusinessPartner()
					+ "on contactNumber::: "
					+ deactivateRequest.getContactNumber());
			deactivateResp = prefService.deactivatePhone(deactivateRequest, sessionId);
			
			if(StringUtils.isNotBlank(deactivateResp.getErrorCode())){
				logger.info("Response from prefService::::");
				logger.info("ErrorCode:::::"+deactivateResp.getErrorCode());
				logger.info("ErrorMessage::::::"+deactivateResp.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(deactivateResp.getErrorMessage());
			}
			else
			{
				logger.info("deactivatePhone() call successful:::::"); 
				JavaBeanUtil.copy(deactivateResp, response);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		}catch (RemoteException e) {
			logger.error("RemoteException::::: PreferenceBO.deactivatePhone()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: PreferenceBO.deactivatePhone()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;
	}
	
	
	
	/**
	 * This method is used to get contact alert preferences
	 * @param companyCode
	 * @param businessPartner
	 * @param contract
	 * @param contractAccount
	 * @param userId
	 * @param userUniqueId
	 * @param sessionId
	 * @return
	 */
	public GetContactAlertPrefsResponse readContactPrefs(ReadContactAlertRequest readContactAlertReq,String sessionId){
		
		GetContactAlertPrefsResponse response = new GetContactAlertPrefsResponse();
		ContactPrefsResponse contactPrefResp = null;
		
		ContactPrefsRequest request = new ContactPrefsRequest();
		request.setBusinessPartner(readContactAlertReq.getBusinessPartner());
		request.setCompanyCode(readContactAlertReq.getCompanyCode());
		request.setContract(readContactAlertReq.getContract());
		request.setContractAccount(readContactAlertReq.getContractAccount());
		request.setUserId(readContactAlertReq.getUserId());
		request.setUserUniqueId(readContactAlertReq.getUserUniqueId());
		request.setIsPrepay(readContactAlertReq.getIsPrepay());
		try
		{
			logger.info("Calling readContactPref for CA/BP::: "+request.getContractAccount()+"/"+request.getBusinessPartner());
			contactPrefResp = prefService.readContactPrefs(request, sessionId);
			
			if(StringUtils.isNotBlank(contactPrefResp.getErrorCode())){
				logger.info("Response from prefService::::");
				logger.info("ErrorCode:::::"+contactPrefResp.getErrorCode());
				logger.info("ErrorMessage::::::"+contactPrefResp.getErrorMessage());
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(contactPrefResp.getErrorMessage());
			}else
			{
				logger.info("readContactPrefs() call successful:::::"); 
				//JavaBeanUtil.copy(contactPrefResp, response);
				BeanUtils.copyProperties(response, contactPrefResp);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			}
		}catch (RemoteException e) {
			logger.error("RemoteException::::: PreferenceBO.readContactPrefs()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: PreferenceBO.readContactPrefs()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
	    return response;
	}
	
	/**
	 * This method is used to save and update the contact alert preferences
	 * @param businessPartner
	 * @param companyCode
	 * @param contract
	 * @param contractAccount
	 * @param efficiencyMktFlag
	 * @param lowBalEmailPref
	 * @param lowBalPhonePref
	 * @param lowBalTextPref
	 * @param myBillIsDuePref
	 * @param myCostExceeds
	 * @param myCostExceedsPref
	 * @param myDailyUsageExceeds
	 * @param myDailyUsageExceedsPref
	 * @param myDayUsageSpikesPref
	 * @param myProjBillExceeds
	 * @param myProjBillExceedsPref
	 * @param payConfEmailPref
	 * @param payConfPhonePref
	 * @param paymentConfTextPref
	 * @param prodServMktFlag
	 * @param thirdPartyMktFlag
	 * @param weeklyBalSumEmailPref
	 * @param weeklyBalSumPhonePref
	 * @param weeklyBalSummTextPref
	 * @param dailyBalSummEmailPref
	 * @param dailyBalSummTextPref
	 * @param isPrepay
	 * @param userId
	 * @param userUniqueId
	 * @param sessionId
	 * @return
	 */
	public UpdationResponse saveUpdateContactAlertPref(SaveUpdateAlertPrefRequest saveUpdateRequest,
			String sessionId) {
		
		UpdationResponse response = new UpdationResponse();
		SaveUpdateContactPrefsResponse saveUpdContPref = null;
		
		SaveUpdateContactPrefsRequest request = new SaveUpdateContactPrefsRequest();
		request.setBusinessPartner(saveUpdateRequest.getBusinessPartner());
		request.setCompanyCode(saveUpdateRequest.getCompanyCode());
		request.setContract(saveUpdateRequest.getContract());
		request.setContractAccount(saveUpdateRequest.getContractAccount());
		
		request.setEfficiencyMktFlag(CommonUtil.getValue(saveUpdateRequest.getEfficiencyMktFlag()));
		request.setLowBalEmailPref(CommonUtil.getValue(saveUpdateRequest.getLowBalEmailPref()));
		request.setLowBalPhonePref(CommonUtil.getValue(saveUpdateRequest.getLowBalPhonePref()));
		request.setLowBalTextPref(CommonUtil.getValue(saveUpdateRequest.getLowBalTextPref()));
		request.setMyBillIsDuePref(CommonUtil.getValue(saveUpdateRequest.getMyBillIsDuePref()));
		request.setMyCostExceeds(CommonUtil.getValue(saveUpdateRequest.getMyCostExceeds()));
		request.setMyCostExceedsPref(CommonUtil.getValue(saveUpdateRequest.getMyCostExceedsPref()));
		request.setMyDailyUsageExceeds(CommonUtil.getValue(saveUpdateRequest.getMyDailyUsageExceeds()));
		request.setMyDailyUsageExceedsPref(CommonUtil.getValue(saveUpdateRequest.getMyDailyUsageExceedsPref()));
		request.setMyDayUsageSpikesPref(CommonUtil.getValue(saveUpdateRequest.getMyDayUsageSpikesPref()));
		request.setMyProjBillExceeds(CommonUtil.getValue(saveUpdateRequest.getMyProjBillExceeds()));
		request.setMyProjBillExceedsPref(CommonUtil.getValue(saveUpdateRequest.getMyProjBillExceedsPref()));
		request.setPayConfEmailPref(CommonUtil.getValue(saveUpdateRequest.getPayConfEmailPref()));
		request.setPayConfPhonePref(CommonUtil.getValue(saveUpdateRequest.getPayConfPhonePref()));
		request.setPaymentConfTextPref(CommonUtil.getValue(saveUpdateRequest.getPaymentConfTextPref()));
		request.setProdServMktFlag(CommonUtil.getValue(saveUpdateRequest.getProdServMktFlag()));
		request.setThirdPartyMktFlag(CommonUtil.getValue(saveUpdateRequest.getThirdPartyMktFlag()));
		
		
		request.setUserId(saveUpdateRequest.getUserId());
		request.setUserUniqueId(saveUpdateRequest.getUserUniqueId());
		
		request.setWeeklyBalSumEmailPref(CommonUtil.getValue(saveUpdateRequest.getWeeklyBalSumEmailPref()));
		request.setWeeklyBalSummTextPref(CommonUtil.getValue(saveUpdateRequest.getWeeklyBalSummTextPref()));
		request.setWeeklyBalSumPhonePref(CommonUtil.getValue(saveUpdateRequest.getWeeklyBalSumPhonePref()));
		request.setDailyBalSumEmailPref(CommonUtil.getValue(saveUpdateRequest.getDailyBalSummEmailPref()));
		request.setDailyBalSumTextPref(CommonUtil.getValue(saveUpdateRequest.getDailyBalSummTextPref()));
		request.setIsPrepay(CommonUtil.getValue(saveUpdateRequest.getIsPrepay()));
		//US12910 | DK | START SMS - ALERT UPDATES - 10192018
		request.setBillReadyPref(CommonUtil.getValue(saveUpdateRequest.getBillReadyPref()));
		request.setCcExpirePref(CommonUtil.getValue(saveUpdateRequest.getCcExpirePref()));
		request.setPayConfPostPayPref(CommonUtil.getValue(saveUpdateRequest.getPayConfPostPayPref()));
		request.setAutoPayRemovalPref(CommonUtil.getValue(saveUpdateRequest.getAutoPayRemovalPref()));
		request.setPayFailurePostPayPref(CommonUtil.getValue(saveUpdateRequest.getPayFailurePostPayPref()));
		request.setServiceAlertsPref(CommonUtil.getValue(saveUpdateRequest.getServiceAlertsPref()));
		request.setNewAlertNotificationPref(CommonUtil.getValue(saveUpdateRequest.getNewAlertNotificationPref()));		
		//US12910 | DK | START SMS - ALERT UPDATES - 10192018
		
		
		try{
			 logger.info("Calling saveUpdateContactPref for CA/BP::::"+request.getContractAccount()+"/"+request.getBusinessPartner());
			 saveUpdContPref = prefService.saveUpdateContactPrefs(request, sessionId);
			 
			 if(StringUtils.isNotBlank(saveUpdContPref.getErrorCode()))
			 {
				    logger.info("Response from prefService::::");
					logger.info("ErrorCode:::::"+saveUpdContPref.getErrorCode());
					logger.info("ErrorMessage::::::"+saveUpdContPref.getErrorMessage());
					response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
					response.setResultDescription(saveUpdContPref.getErrorMessage());
			 }else
			 {
				   logger.info("saveUpdateContactAlertPref():::: call successful");
				   response.setIsUpdateSuccess(saveUpdContPref.getSaveOrUpdateSuccess());
				   response.setResultCode(RESULT_CODE_SUCCESS);
				   response.setResultDescription(MSG_SUCCESS);
			 }
		}catch(RemoteException ex){
			logger.error("RemoteException::::: PreferenceBO.saveUpdateContactAlertPref()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, ex.getMessage(), response);
		}catch(Exception ex){
			logger.error("Exception::::: PreferenceBO.saveUpdateContactAlertPref()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, ex.getMessage(), response);
		}
		
		return response;
	}
	
	
	/**
	 * 
	 * @param pDto
	 * @return
	 */
	public PrivacyPreferenceResponse savePrivacyPreference(PrivacyPreferencesRequest pDto){
		
		boolean updateSuccess = false;
		logger.info("PreferenceBO.savePrivacyPreference(): START");
		PrivacyPreferenceResponse response = new PrivacyPreferenceResponse();
		
		try{
			
			   updateSuccess = preferencesDAO.savePrivacyPreference(pDto);
			
			
			   response.setStatus(String.valueOf(updateSuccess));
			   response.setResultCode(RESULT_CODE_SUCCESS);
			   response.setResultDescription(MSG_SUCCESS);
			
		}catch(Exception ex){
			logger.error("Exception::::: PreferenceBO.savePrivacyPreference()::::");
			response.setStatus(String.valueOf(updateSuccess));
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, ex.getMessage(), response);
		}
		
		
		logger.info("PreferenceBO.savePrivacyPreference(): END");
		return response;
	
	
	}
	
	/** US12887 - DK | SMS ALERTS | 10/15/2018 **/
	/**
	 * @author dkrishn1
	 * This method returns OPT IN  and OPT OUT Eligibility from CCS via NRGWS Service Layer for given BP/CA combination.
	 * @param request  - The SMSOptInOutEligibilityRequest Object
	 * @return Response - The SMSOptInOutEligibilityResponse Object
	 */
	public SMSOptInOutEligibilityResponse checkOptInOutEligibility(SMSOptInOutEligibilityRequest request, String sessionId){
		logger.info("PreferenceBO.checkOptInOutEligibility(): START-");
		SMSOptInOutEligibilityResponse response = new SMSOptInOutEligibilityResponse();		
		try{
			   SmsOptInOutEligResponse res = prefService.checkOptInOutEligibility(request, sessionId);
			   if(res!= null)
			   {
				   if(!StringUtils.isNotBlank(res.getErrorCode()))
				   {
					   EmailInformation emailInfo = res.getEmail();				   
					   com.multibrand.vo.response.EmailInformation email = new com.multibrand.vo.response.EmailInformation();
					   if(emailInfo!= null)
					   {
						   email.setBusinessPartner(emailInfo.getBusinessPartner());
						   email.setEmailAddress(emailInfo.getEmailAddress());
						   email.setExternalId(emailInfo.getExternalId());
						   email.setIsBounce(emailInfo.getIsBounce());
						   response.setEmail(email);
					   }
				
					   //response.setActiveBPList(activeBPList);
					   //response.setInactiveBPList(res.getInactiveBPList());
					   response.setCode(res.getCode());
					   
					   response.setEvOptinEligible(res.getEvOptinEligible());
					   response.setEvOptoutEligible(res.getEvOptoutEligible());			   
					   response.setMessage(res.getMessage());			  
					   response.setType(res.getType());			   
					   if(res.getPhones()!= null)
					   {
						   com.multibrand.domain.PhoneInformation[] phArry = res.getPhones();
						   List<PhoneInformation> phonesList = new ArrayList<PhoneInformation>();
						   if(phArry.length>0)
						   {
							   PhoneInformation localPhInfo = new PhoneInformation();
							   for(int i = 0; i < phArry.length ; i++)
							   {
								   com.multibrand.domain.PhoneInformation phoneInfo = new com.multibrand.domain.PhoneInformation();
								   phoneInfo = phArry[i];
								   localPhInfo.setBusinessPartner(phoneInfo.getBusinessPartner());
								   phonesList.add(localPhInfo);
							   }
						   }
						   response.setPhones(phonesList);
					   }
					   response.setMessage(res.getMessage());	
					   response.setResultCode(RESULT_CODE_SUCCESS);
					   response.setResultDescription(MSG_SUCCESS);
				 } 
				 else
				  {
					   logger.info("::::: PreferenceBO.checkOptInOutEligibility():::: - CCS SmsOptInOutEligResponse - CCS object returnred Error Code!");
					   response.setErrorDescription(res.getErrorMessage());	
					   response.setErrorCode(res.getErrorCode());
					   response.setResultCode(RESULT_CODE_CCS_ERROR);
					   response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				}
			   }
			   else
			   {
				   logger.info("::::: PreferenceBO.checkOptInOutEligibility():::: - CCS SmsOptInOutEligResponse - response object is null or empty");
				   response.setResultCode(RESULT_CODE_CCS_ERROR);
				   response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);				   
			   }
			
		}catch(Exception ex){
			//CCS EXCEPTION
			logger.error("Exception::::: PreferenceBO.checkOptInOutEligibility()::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, ex.getMessage(), response);
		}
		
		
		logger.info("PreferenceBO.checkOptInOutEligibility():::-END");
		return response;
		
		/** END US12887 - DK | SMS ALERTS | 10/15/2018 **/
	
	}
		
	/** US12884 - DK | SMS ALERTS | 10/15/2018 **/
	/**
	 * @author dkrishn1
	 * This method does OPT IN  and OPT OUT for SMS Alerts in CCS via NRGWS Service Layer for given BP/CA combination.
	 * @param request  - The OptInOptOutRequest Object
	 * @return response - The OptInOptOutResponse Object
	 */
	public OptInOptOutResponse OptInOptOutToSMS(OptInOptOutRequest req, String sessionId){
		logger.info("::::PreferenceBO.OptInOptOutToSMS(): START::");
		OptInOptOutResponse response = new OptInOptOutResponse();			
		try{
			SmsOptInOutRequest request = createRequest(req);
			SmsOptInOutResponse ccsResponse = prefService.optInOutService(request, sessionId); 
			if(ccsResponse!= null)
			{
				if(StringUtils.isNotBlank(ccsResponse.getErrorCode()))
				{
					logger.info("::::: PreferenceBO.OptInOptOutToSMS():::: - CCS OptInOptOutToSMS - ccsResponse is returned with Error Code/Message!");
					logger.info("ErrorCode:::::"+ccsResponse.getErrorCode());
					logger.info("ErrorMessage::::::"+ccsResponse.getErrorMessage());
					response.setResultCode(RESULT_CODE_CCS_ERROR);
					response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);				
				}
				else
				{	
					 logger.info("::::: PreferenceBO.OptInOptOutToSMS():::: - CCS OptInOptOutToSMS - ccsResponse is being returned !!");
					 response.setCode(ccsResponse.getCode());
					 response.setMessage(ccsResponse.getMessage());
					 response.setType(ccsResponse.getType());
					 response.setResultCode(RESULT_CODE_SUCCESS);
					 response.setResultDescription(MSG_SUCCESS);	
				}
			}
			else
			{	
				 logger.info("::::: PreferenceBO.OptInOptOutToSMS():::: - CCS OptInOptOutToSMS - ccsResponse object is null or empty!");
				 response.setResultCode(RESULT_CODE_CCS_ERROR);
				 response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);	
					  
			}		
		}catch(Exception ex){
			//CCS Remote or System EXCEPTION
			logger.error("Exception::::: PreferenceBO.OptInOptOutToSMS::::");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, ex.getMessage(), response);
		}
		logger.info(":::PreferenceBO.OptInOptOutToSMS:::-END");
		return response;	
	}

	private SmsOptInOutRequest createRequest(OptInOptOutRequest req) {
		SmsOptInOutRequest request = new SmsOptInOutRequest();
		request.setBPNumber(req.getbPNumber());		
		request.setCANumber(req.getCaNumber());
		request.setBukrs(req.getCompanyCode());
		request.setBrand(req.getBrand());
		request.setCellPhone(req.getCellPhone());
		request.setOptinFlag(req.getOptInFlag());
		request.setReqCode(req.getReqCode());
		request.setHouseNum(req.getHouseNumer());
		request.setChannel(req.getChannel());		
		return request;
	}
	/** END US12884 - DK | SMS ALERTS | 10/15/2018 **/	
}
