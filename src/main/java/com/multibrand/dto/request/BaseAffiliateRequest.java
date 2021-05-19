package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.ChannelType;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class BaseAffiliateRequest implements Constants, FormEntityRequest, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4445444910516034860L;
	
	@QueryParam(value = "affiliateId")
	@NotBlank
	@Size(max = 40, groups = SizeConstraint.class)
	private String affiliateId;
	
	@QueryParam(value = "channelType")
	@ChannelType
	private String channelType;
	
	@QueryParam(value = "companyCode")
	@Size(max = 4, groups = SizeConstraint.class)
	private String companyCode;
	
	@QueryParam(value = "brandId")
	@Size(max = 2, groups = SizeConstraint.class)
	private String brandId;
	
	@QueryParam(value = "languageCode")
	@Size(max = 1, groups = SizeConstraint.class)
	private String languageCode = null;
	
	private String callExecuted;
	
	/**
	 * @return the affiliateId
	 */
	public String getAffiliateId() {
		return affiliateId;
	}

	/**
	 * @param affiliateId
	 *            the affiliateId to set
	 */
	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the brandId
	 */
	public String getBrandId() {
		return brandId;
	}

	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode the languageCode to set
	 */
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
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
