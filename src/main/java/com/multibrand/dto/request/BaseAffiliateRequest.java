package com.multibrand.dto.request;

import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.ChannelType;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidateCompanyCode;
import com.multibrand.util.Constants;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class BaseAffiliateRequest implements Constants{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4445444910516034860L;
	
	@QueryParam(value = "affiliateId")
	@NotBlank
	@Length(max = 40, groups = SizeConstraint.class)
	private String affiliateId;
	
	@QueryParam(value = "channelType")
	@ChannelType
	private String channelType;
	
	@QueryParam(value = "companyCode")
	@NotBlank
	@Length(max = 4, groups = SizeConstraint.class)
	@ValidateCompanyCode
	private String companyCode;
	
	@QueryParam(value = "brandId")
	@Length(max = 2, groups = SizeConstraint.class)
	private String brandId;
	
	@QueryParam(value = "languageCode")
	@Length(max = 1, groups = SizeConstraint.class)
	private String languageCode = null;
	
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

	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append("affiliateId=").append(affiliateId).append(';');

		return (value.toString() + super.toString());
	}

}
