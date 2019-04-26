package com.multibrand.bo;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.ContractAccountDO;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.domain.UpdateContactRequest;
import com.multibrand.domain.UpdateContactRequestAttNamValPairMapEntry;
import com.multibrand.domain.UpdateContactResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.LDAPHelper;
import com.multibrand.service.ProfileService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.UpdateContactInfoResponse;
import com.multibrand.vo.response.webagent.UserInfoResponseWebAgent;

@Component
public class WebAgentBO implements Constants {

	Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	ProfileService profileService;
		
	@Autowired
	LDAPHelper ldapHelper;
	
	public UpdateContactInfoResponse updateEmail(String userName,String email,String companyCode,String sessionId){
		
		String businessPartner=null;
		ProfileResponse profileRes = null;
		UserInfoResponseWebAgent userInfoResWebgent=null;
		String accountNumber="";
		String uniqueId="";
		
		logger.info("WebAgentBO updateEmail()...Starts");
		UpdateContactInfoResponse updateContactInfoResponse = new UpdateContactInfoResponse();
		try {
			long startTime = CommonUtil.getStartTime();
			userInfoResWebgent=ldapHelper.getUserInfoForWebAgent(userName, companyCode, sessionId);
			// this has been handled in helper layer so as to handle the logging for exceptions for this ldap call
			/*utilityloggerHelper.logTransaction("getUserInfoForWebAgent", false, "userName="+userName+",companyCode="+companyCode,userInfoResWebgent, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			logger.info(XmlUtil.pojoToXML("userName="+userName+",companyCode="+companyCode));
	    	logger.info(XmlUtil.pojoToXML(userInfoResWebgent));*/
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap = profileService.getProfile(userInfoResWebgent.getAccountNumber(),companyCode,sessionId);
			if(responseMap!= null && responseMap.size()!= 0)
			{
				profileRes = (ProfileResponse)responseMap.get("profileResponse");
			}
			ContractAccountDO ctrdo = null;
			if (profileRes != null) {
				ctrdo = profileRes.getContractAccountDO();
			}
	      if (ctrdo != null)
	      {
	        this.logger.info("bp number " + ctrdo.getStrBPNumber());
	        businessPartner = ctrdo.getStrBPNumber();
	      }
	      if(userInfoResWebgent!=null)
	      {
	    	  accountNumber=userInfoResWebgent.getAccountNumber();
	    	  uniqueId=userInfoResWebgent.getUniqueId();
	      }
		logger.info("USerName "+userName+" AccoutNumber "+accountNumber +" BPNumber "+businessPartner
				+" UniqueID "+uniqueId +" New Email "+email);
		UpdateContactResponse response = new UpdateContactResponse();
		UpdateContactRequest updateContactRequest =new UpdateContactRequest();
		UpdateContactRequestAttNamValPairMapEntry updateContactRequestAttNamValPairMapEntry[] = {};
    	updateContactRequest.setAttNamValPairMap(updateContactRequestAttNamValPairMapEntry);
		updateContactRequest.setStrCompanyCode(companyCode);
		updateContactRequest.setIsEmailUpdated(Constants.FLAG_Y);
		updateContactRequest.setIsTelephoneUpdated(Constants.FLAG_NO);
     	updateContactRequest.setStrCANumber(accountNumber);
		updateContactRequest.setStrEmailId(email);
		updateContactRequest.setStrLDAPOrg(Constants.LDAP_ORG_GME);
		updateContactRequest.setStrBPNumber(businessPartner);
		updateContactRequest.setStrUniqueId(uniqueId);
		updateContactRequest.setUserName(userName);
		
		response = profileService.updateContactInfoWS(updateContactRequest, companyCode, sessionId);
		
		if (response.getErrorCode()==null || response.getErrorCode().equals("")){
			updateContactInfoResponse.setResultCode(RESULT_CODE_SUCCESS);
			updateContactInfoResponse.setResultDescription(MSG_SUCCESS);
		}else{
			updateContactInfoResponse.setResultCode(RESULT_CODE_CCS_ERROR);
			logger.info("::::::::::::::::error code :::" +response.getErrorCode() );
			logger.info("::::::::::::::::error Message :::" +response.getErrorMessage() );
			updateContactInfoResponse.setResultDescription(response.getErrorCode());
		}
		} catch(NamingException namingEx){
			logger.error("Naming exception occurred...");
			
		}
		catch (RemoteException e) {
			logger.error(e);
			updateContactInfoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updateContactInfoResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), updateContactInfoResponse);
		}
		catch (Exception e) {
			logger.error(e);
			updateContactInfoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updateContactInfoResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), updateContactInfoResponse);
		}
		logger.info("WebAgentBO updateEmail()...Ends");
		return updateContactInfoResponse;
	}
}
