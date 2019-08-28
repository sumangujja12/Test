package com.multibrand.helper;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.CreateContactLogResponse;
import com.multibrand.service.TOSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;


@Component
public class ContactLogHelper implements Constants {
	
Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	protected TOSService tOSService;
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	/**
	 * @author Cuppala
	 * @param CreateContactLogRequest
	 * @return boolean
	 * 
	 */
	public boolean updateContactLog(CreateContactLogRequest pRequest) {
		logger.info("TOS:: updateContactLog : Entering the method:" );
		long startTime = CommonUtil.getStartTime();
		String responseStatus = "";
		Boolean isUpdateContactLogSuccessful = false;
		String sessionId = httpRequest.getSession(true).getId();
		CreateContactLogRequest request = new CreateContactLogRequest();
		request.setBusinessPartnerNumber(pRequest.getBusinessPartnerNumber());
		request.setContractAccountNumber(pRequest.getContractAccountNumber());
		request.setContactClass(pRequest.getContactClass());
		request.setContactActivity(pRequest.getContactActivity());
		request.setCommitFlag(pRequest.getCommitFlag());
		request.setContactType(pRequest.getContactType());
		request.setDivision(pRequest.getDivision());
		request.setTextLines(pRequest.getTextLines());
		request.setFormatCol("");
		request.setCompanyCode(pRequest.getCompanyCode());
		try{
		
		CreateContactLogResponse reponse = tOSService.updateContactLog(request);;
		if(null != reponse && 
				StringUtils.isNotBlank(reponse.getErrCode())){
			responseStatus = reponse.getErrCode();
		}
		
		if(StringUtils.isBlank(responseStatus)){
			isUpdateContactLogSuccessful = true;
		}
		
		try{
			
		    utilityloggerHelper.logTransaction("updateContactLog", false, pRequest,responseStatus, "", CommonUtil.getElapsedTime(startTime), "", sessionId, pRequest.getCompanyCode());

		    
		} catch(Exception e){
			logger.error("Exception While logging::", e);
		}
	} catch (Exception e) {
		logger.error("TOS:: updateContactLog : Exception while getting data from ccs:", e);
	} 
	
	logger.info("TOS:: updateContactLog : Entering the method:" );
	return isUpdateContactLogSuccessful;
	}

}
