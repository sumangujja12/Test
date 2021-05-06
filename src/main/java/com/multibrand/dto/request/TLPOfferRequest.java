/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

/**
 * The Class AffiliateOfferRequest.
 *
 * @author Arumugam
 */
public class TLPOfferRequest implements FormEntityRequest, Constants, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 4, groups = SizeConstraint.class)
	private String companyCode;
	
	@NotBlank(groups = BasicConstraint.class)
	private String vendorID;
	
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 10, groups = SizeConstraint.class)
	private String promoCode;
	
	@Size(max = 5, groups = SizeConstraint.class)
	private String tdspCodeCCS;
	
	
	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}

	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
	}

	

	public String getVendorID() {
		return vendorID;
	}
	
	

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see com.multibrand.dto.request.BaseAffiliateRequest#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
		//return super.toString() + CommonUtil.doRender(this)
	}	
}