package com.multibrand.helper;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.multibrand.dao.RegistrationDAO;
import com.multibrand.dao.UserInfoDAOIF;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.UserIdRequest;
import com.multibrand.vo.response.UserIdResponse;

@Component
public class ProfileHelper {

	@Resource(name = "registrationDao")
	private RegistrationDAO registrationDAOImpl;
	
	@Resource(name="userinfodao")
	private UserInfoDAOIF userDAOImpl;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	
	public UserIdResponse getuserId(UserIdRequest request){
		UserIdResponse response =null;
		logger.info("Start ProfileHelper getUserId");
		
		response = CommonUtil.validateUserIdRequest(request);
		
		if(StringUtils.isNotBlank(response.getResultcode())){
			logger.info("UserId Request validation failed  ");
			return response;
		}else{
			if((StringUtils.equals(request.getCompanycode(),Constants.COMPANY_CODE_RELIANT))
					||(StringUtils.equals(request.getCompanycode(),Constants.COMPANY_CODE_PENNYWISE))
					||(StringUtils.equals(request.getCompanycode(),Constants.COMPANY_CODE_CIRRO))){
				//System.out.println("RELIANT/PENNYWISE/CIRRO Profile");
				response = userDAOImpl.getUserName(request);
			}else if(StringUtils.equals(request.getCompanycode(),Constants.COMPANY_CODE_GME)){
				//System.out.println("GME Profile");
				response=registrationDAOImpl.getUserName(request);
			}else{
				response=new UserIdResponse();
				response.setResultcode(Constants.ONE);
				response.setResultdescription("Request Entity has following error: Invalid Company Code");
			}
		}
		
		logger.info("End ProfileHelper getUserId");
		return response;
	}
	
}
