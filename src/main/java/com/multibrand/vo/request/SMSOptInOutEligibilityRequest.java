package com.multibrand.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

/**
 * 
 * @author dkrishn1
 *
 */
public class SMSOptInOutEligibilityRequest implements FormEntityRequest, Constants, Serializable {
	
	private String brandId;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String businessPartner;
	
	private String channel;
	private String companyCode;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String contractAccount;  
	
	private String mobileNumber;
	private String optInFlag;
	private String requestCode;
	
	private String houseNumber;
	
	//private BPDetailsRequest etActiveBP;
	//private BPDetailsRequest etInactiveBP;
	
	
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBusinessPartner() {
		return businessPartner;
	}
	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getContractAccount() {
		return contractAccount;
	}
	public void setContractAccount(String contractAccount) {
		this.contractAccount = contractAccount;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOptInFlag() {
		return optInFlag;
	}
	public void setOptInFlag(String optInFlag) {
		this.optInFlag = optInFlag;
	}
	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	

}
