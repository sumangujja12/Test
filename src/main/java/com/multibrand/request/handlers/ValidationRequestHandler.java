package com.multibrand.request.handlers;

import org.springframework.stereotype.Component;

import com.multibrand.domain.ValidatePosIdRequest;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;

@Component
public class ValidationRequestHandler implements Constants {
	
	LoggerUtil logger = LoggerUtil.getInstance("ValidationRequestHandler");

	
	public ValidatePosIdRequest createPosIdRequest(String dob,String tdl,String companyCode,String maidenName,
			String firstName,String lastName,String ssn,String middleName) {
		ValidatePosIdRequest validatePosIdReq= new ValidatePosIdRequest();
		validatePosIdReq.setBirthdt(dob);
		validatePosIdReq.setDrLicense(tdl);
		validatePosIdReq.setBukrs(companyCode);
		validatePosIdReq.setDrLicenseLand1(US);
		validatePosIdReq.setDrLicenseRegio(TX);
		validatePosIdReq.setMaidenName(maidenName);
		validatePosIdReq.setNameFirst(firstName);
		validatePosIdReq.setNameLast(lastName);
		validatePosIdReq.setNameLast2(middleName);
		validatePosIdReq.setPartner(EMPTY);
		validatePosIdReq.setSocSecure(ssn);
		validatePosIdReq.setStrCompanyCode(companyCode);
		validatePosIdReq.setType(EMPTY);
		return validatePosIdReq;
	
	}
	
}
