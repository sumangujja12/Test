package com.multibrand.dto.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.BasicConstraint;
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

	// Online Affiliate attributes:
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 40, groups = SizeConstraint.class)
	private String affiliateId;
	
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
