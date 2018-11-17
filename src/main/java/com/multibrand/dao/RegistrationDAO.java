package com.multibrand.dao;

import com.multibrand.vo.request.UserIdRequest;
import com.multibrand.vo.request.UserRegistrationRequest;
import com.multibrand.vo.response.UserIdResponse;

public interface RegistrationDAO 
{
	public boolean createUserName(UserRegistrationRequest userReqVo);
	public boolean getUserName(UserRegistrationRequest userReqVo);
	public boolean isAccountEnrolled(UserRegistrationRequest userReqVo);
	
	public UserIdResponse getUserName(UserIdRequest request);
		
}
