package com.multibrand.dto.request;

import org.apache.commons.lang.StringUtils;

import com.multibrand.exception.ValidateRequestException;
/**
 * @author bbachin1
 * 
 */
public class CCSEmailRequest extends NRGServicesRequest implements BaseEmailRequest {
	
	/*Reliant Ebill properties */
	private String contractaccountnumber;
	private String checkdigit;
	private String caname;
	private String billdeliverymethod;
	/*Reliant Ebill properties */
	
	/*common properties */
	private String brandname;
	private String languagecode;
	private String companycode;
	private String emailid;
	/*common properties */
	
	
	public String getContractaccountnumber() {
		return contractaccountnumber;
	}
	public void setContractaccountnumber(String contractaccountnumber) {
		this.contractaccountnumber = contractaccountnumber;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getCheckdigit() {
		return checkdigit;
	}
	public void setCheckdigit(String checkdigit) {
		this.checkdigit = checkdigit;
	}
	public String getCaname() {
		return caname;
	}
	public void setCaname(String caname) {
		this.caname = caname;
	}
	public String getBilldeliverymethod() {
		return billdeliverymethod;
	}
	public void setBilldeliverymethod(String billdeliverymethod) {
		this.billdeliverymethod = billdeliverymethod;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getLanguagecode() {
		return languagecode;
	}
	public void setLanguagecode(String languagecode) {
		this.languagecode = languagecode;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	
	
	@Override
	public void validateRequest() throws ValidateRequestException {
		
		if(StringUtils.isBlank(this.companycode)){throw new ValidateRequestException("COMPANY CODE SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.brandname)){throw new ValidateRequestException("BRAND NAME SHOULD NOT BE EMPTY");}
		if(StringUtils.equalsIgnoreCase(this.companycode, RELIANT_COMPANY_CODE)){
			if(!StringUtils.equalsIgnoreCase(this.brandname, RELIANT_BRAND_NAME)){throw new ValidateRequestException("BRAND NAME AND COMPANY CODE DOES NOT MATCH");}
		}
		if(StringUtils.equalsIgnoreCase(this.companycode, GME_COMPANY_CODE)){
			if(!StringUtils.equalsIgnoreCase(this.brandname, GME_BRAND_NAME)){throw new ValidateRequestException("BRAND NAME AND COMPANY CODE DOES NOT MATCH");}
		}
		
		if((StringUtils.isNotBlank(this.companycode) && StringUtils.equalsIgnoreCase(this.companycode, RELIANT_COMPANY_CODE)) && 
				(StringUtils.isNotBlank(this.brandname) && StringUtils.equalsIgnoreCase(this.brandname, RELIANT_BRAND_NAME))){
			if(StringUtils.isBlank(this.billdeliverymethod)){throw new ValidateRequestException("BILL DELIVERY METHOD IS REQUIRED FOR BRAND RELIANT");}
		}
		
		if((StringUtils.isNotBlank(this.companycode) && StringUtils.equalsIgnoreCase(this.companycode, GME_COMPANY_CODE)) && 
				(StringUtils.isNotBlank(this.brandname) && StringUtils.equalsIgnoreCase(this.brandname, GME_BRAND_NAME))){
			if(!(StringUtils.isBlank(this.billdeliverymethod) || StringUtils.equalsIgnoreCase(this.billdeliverymethod, EMAIL))){throw new ValidateRequestException("ONLY EMAIL OPTION IS ALLOWED FOR BRAND GME");}
		}
		
	}
	
	

}
