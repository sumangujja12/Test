package com.multibrand.dto.request;

import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.ChannelType;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class BaseAffiliateRequest extends BaseRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4445444910516034860L;
	
	@QueryParam(value = "affiliateId")
	// Online Affiliate attributes:
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 40, groups = SizeConstraint.class)
	private String affiliateId;
	
	@QueryParam(value = "channelType")
	@ChannelType(format = "", groups = FormatConstraint.class, message = "ChannelType is not Valid",messageCode="INVALID_CHHANEL_TYPE",messageCodeText="INVALID_CHHANEL_TYPE")
	private String channelType;
	
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

	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append("affiliateId=").append(affiliateId).append(';');

		return (value.toString() + super.toString());
	}

}
