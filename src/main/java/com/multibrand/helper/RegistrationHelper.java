package com.multibrand.helper;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.multibrand.dao.RegistrationDAO;
import com.multibrand.vo.request.UserRegistrationRequest;

@Component
public class RegistrationHelper
{

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	@Resource(name = "registrationDao")
	private RegistrationDAO registrationDAOImpl;

	public boolean createUserName(UserRegistrationRequest userReqVo)
	{
		logger.info(" START RegistrationHelper.createUserName Method");
		return registrationDAOImpl.createUserName(userReqVo);
	}

	public boolean getUserName(UserRegistrationRequest userReqVo)
	{
		logger.info(" START RegistrationHelper.getUserName Method");
		return registrationDAOImpl.getUserName(userReqVo);
	}

	public boolean isAccountEnrolled(UserRegistrationRequest userReqVo)
	{
		logger.info(" START RegistrationHelper.getUserNameExists Method");
		return registrationDAOImpl.isAccountEnrolled(userReqVo);
	}
	
}
