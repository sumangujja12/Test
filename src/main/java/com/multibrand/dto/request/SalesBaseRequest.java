package com.multibrand.dto.request;

import java.io.Serializable;

import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.ChannelType;
import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;
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
	@Length(max = 40, groups = SizeConstraint.class)
	private String affiliateId;
	
	@QueryParam(value = "channelType")
	@ChannelType
	private String channelType;
	
	@QueryParam(value = "companyCode")
	@NotEmpty
	@Length(max = 4, groups = SizeConstraint.class)
	@ValidateCompanyCode(message="is not valid")
	private String companyCode;
	
	@QueryParam(value = "brandId")
	@Length(max = 2, groups = SizeConstraint.class)
	private String brandId;
	
	@QueryParam(value = "languageCode")
	@Length(max = 1, groups = SizeConstraint.class)
	private String languageCode = null;

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
	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}
