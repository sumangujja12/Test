package com.multibrand.dao;

import com.multibrand.exception.OAMException;
import com.multibrand.vo.request.UserIdRequest;
import com.multibrand.vo.response.UserIdResponse;
import com.multibrand.vo.response.UserInfoResponse;

public interface UserInfoDAOIF {
	public UserInfoResponse getUserInfo(String UserId, String companyCode)throws OAMException;
	public UserIdResponse getUserName(UserIdRequest request);
}





