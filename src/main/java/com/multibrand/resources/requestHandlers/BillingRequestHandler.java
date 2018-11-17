package com.multibrand.resources.requestHandlers;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.StoreUpdatePayAccountRequest;


@Component
public class BillingRequestHandler implements Constants {

	
	
	/*public StoreUpdatePayAccountRequest getStorePayAccountRequest(String contractAccountNumber, String companyCode, String brandName, String onlinePayAccountType, 
			String lastFourDigit, String nameOnAccount, String payAccountNickName, String payAccountToken, String activeFlag, String lastPaymentDate, 
			String activationDate,String verifyCard, String routingNumber, String zipCode, String ccExpMonth, String ccExpYear, String ccType, String autoPay){
		
		StoreUpdatePayAccountRequest request = new StoreUpdatePayAccountRequest();
		
		
		request.setContractAccountNumber(contractAccountNumber);
		request.setCompanyCode(companyCode);
		request.setBrandName(brandName);
		request.setOnlinePayAccountType(onlinePayAccountType);
		request.setLastFourDigit(lastFourDigit);
		request.setNameOnAccount(nameOnAccount);
		request.setPayAccountNickName(payAccountNickName);
		request.setPayAccountToken(payAccountToken);
		request.setActiveFlag(activeFlag);
		if(!StringUtils.isEmpty(lastPaymentDate)){
		request.setLastPaymentDate(CommonUtil.getSqlDate(lastPaymentDate, DT_FMT_REQUEST));
		}
		
		if(!StringUtils.isEmpty(activationDate)){
			request.setActivationDate(CommonUtil.getSqlDate(activationDate, DT_FMT_REQUEST));
		}
		
		request.setVerifyCard(verifyCard);
		request.setRoutingNumber(routingNumber);
		request.setZipCode(zipCode);
		request.setCcExpMonth(ccExpMonth);
		request.setCcExpYear(ccExpYear);
		request.setCcType(ccType);
		request.setAutoPay(autoPay);
		
		return request;
	}
	
	public StoreUpdatePayAccountRequest getUpdatePayAccountRequest(String contractAccountNumber, String companyCode, String brandName, String onlinePayAccountType, 
			String lastFourDigit, String nameOnAccount, String payAccountNickName, String payAccountToken, String activeFlag, String lastPaymentDate, 
			String activationDate,String verifyCard, String routingNumber, String zipCode, String ccExpMonth, String ccExpYear, String onlinePayAccountId, String ccType, String autoPay ){
		
		StoreUpdatePayAccountRequest request = new StoreUpdatePayAccountRequest();
		
		
		request.setContractAccountNumber(contractAccountNumber);
		request.setCompanyCode(companyCode);
		request.setBrandName(brandName);
		request.setOnlinePayAccountType(onlinePayAccountType);
		request.setLastFourDigit(lastFourDigit);
		request.setNameOnAccount(nameOnAccount);
		request.setPayAccountNickName(payAccountNickName);
		request.setPayAccountToken(payAccountToken);
		request.setActiveFlag(activeFlag);
		if(!StringUtils.isEmpty(lastPaymentDate)){
		request.setLastPaymentDate(CommonUtil.getSqlDate(lastPaymentDate, DT_FMT_REQUEST));
		}
		
		if(!StringUtils.isEmpty(activationDate)){
			request.setActivationDate(CommonUtil.getSqlDate(activationDate, DT_FMT_REQUEST));
		}
		
		request.setVerifyCard(verifyCard);
		request.setRoutingNumber(routingNumber);
		request.setZipCode(zipCode);
		request.setCcExpMonth(ccExpMonth);
		request.setCcExpYear(ccExpYear);
		request.setOnlinePayAccountId(Long.parseLong(onlinePayAccountId));
		request.setCcType(ccType);
		request.setAutoPay(autoPay);
		
		
		return request;
	}*/
}
