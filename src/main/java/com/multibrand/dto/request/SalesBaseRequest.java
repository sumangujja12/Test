package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Component;

import com.multibrand.request.validation.ChannelType;
import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.ValidateCompanyCode;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

@Component
public class SalesBaseRequest implements Constants, FormEntityRequest, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1593895628065539633L;

	@QueryParam(value = "affiliateId")
	@NotEmpty
	@Size(max = 40)
	private String affiliateId;
	
	@QueryParam(value = "channelType")
	@ChannelType
	private String channelType;
	
	@QueryParam(value = "companyCode")
	@NotEmpty
	@Size(max = 4)
	@ValidateCompanyCode(message="is not valid")
	private String companyCode;
	
	@QueryParam(value = "brandId")
	@Size(max = 2)
	private String brandId;
	
	@QueryParam(value = "languageCode")
	@Size(max = 1)
	private String languageCode = null;
	
	private String callExecuted;

	public String getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}	
	
	/**
	 * @return the callExecuted
	 */
	public String getCallExecuted() {
		return callExecuted;
	}

	/**
	 * @param callExecuted the callExecuted to set
	 */
	public void setCallExecuted(String callExecuted) {
		this.callExecuted = callExecuted;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}
