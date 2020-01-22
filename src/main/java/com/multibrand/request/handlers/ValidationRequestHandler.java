package com.multibrand.request.handlers;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.multibrand.domain.ValidatePosIdKBARequest;
import com.multibrand.domain.ValidatePosIdRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
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
	
	public ValidatePosIdKBARequest createPoisdWithKBARequest(PerformPosIdAndBpMatchRequest performPosIdBpRequest){
		
		ValidatePosIdKBARequest validatePosIdKBARequest = new ValidatePosIdKBARequest();
		
		validatePosIdKBARequest.setCompanyCode(performPosIdBpRequest.getCompanyCode());
		validatePosIdKBARequest.setBrandName(performPosIdBpRequest.getBrandId());
		validatePosIdKBARequest.setChannel(CHANNEL_TYPE_AA);
		
		validatePosIdKBARequest.setChannelType((performPosIdBpRequest.getChannelType()!= null) ?performPosIdBpRequest.getChannelType():CHANNEL_TYPE_AA);
		String langCode = (StringUtils.equalsIgnoreCase(performPosIdBpRequest.getLanguageCode(), EN_US)? E:S);
		validatePosIdKBARequest.setLanguageCode(langCode);
		
		validatePosIdKBARequest.setIsNoKBA(FLAG_X);
		
		validatePosIdKBARequest.setFirstName(performPosIdBpRequest.getFirstName());
		validatePosIdKBARequest.setLastName(performPosIdBpRequest.getLastName());
		validatePosIdKBARequest.setMiddleInitial(performPosIdBpRequest.getMiddleName());
		String dob = DateUtil.getFormattedDate(DATE_FORMAT, Constants.MMddyyyy, performPosIdBpRequest.getDob());
		validatePosIdKBARequest.setDob(dob);
		validatePosIdKBARequest.setTokenizedSSN(performPosIdBpRequest.getTokenSSN());		
		if(StringUtils.isNotEmpty(performPosIdBpRequest.getTokenTDL())){
			validatePosIdKBARequest.setTokenizedDrl(performPosIdBpRequest.getTokenTDL());        
			validatePosIdKBARequest.setDlrState(DRL_STATE_TX);
	    } 
		
		
		
		validatePosIdKBARequest.setHomePhone(performPosIdBpRequest.getPhoneNum());
		validatePosIdKBARequest.setEmailAddress(performPosIdBpRequest.getEmail());
		validatePosIdKBARequest.setIpAddress((performPosIdBpRequest.getIpAddress()!= null) ?performPosIdBpRequest.getIpAddress():EMPTY);
		validatePosIdKBARequest.setEsid(EMPTY);
		
		com.multibrand.domain.AddressDTO serviceAddressDTO = new com.multibrand.domain.AddressDTO();
		serviceAddressDTO.setStrStreetNum(performPosIdBpRequest.getServStreetNum());
		serviceAddressDTO.setStrStreetName(performPosIdBpRequest.getServStreetName());		
		serviceAddressDTO.setStrUnitNumber(performPosIdBpRequest.getServStreetAptNum());
		serviceAddressDTO.setStrCity(performPosIdBpRequest.getServCity());
		serviceAddressDTO.setStrState(performPosIdBpRequest.getServState());
		serviceAddressDTO.setStrZip(performPosIdBpRequest.getServZipCode());
		
		validatePosIdKBARequest.setServiceAddress(serviceAddressDTO);
			
		
		return validatePosIdKBARequest;
		
	}
	
	
	
}
