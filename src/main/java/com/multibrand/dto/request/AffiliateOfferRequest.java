/**
 * 
 */
package com.multibrand.dto.request;

import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;

/**
 * The Class AffiliateOfferRequest.
 *
 * @author Arumugam
 */
public class AffiliateOfferRequest extends BaseAffiliateRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The transaction type. */
	@QueryParam(value = "transactionType")
	@Size(max = 3, groups = SizeConstraint.class)
	private String transactionType; //MVI or SWI

	
	/** The promo code. */
	@QueryParam(value = "promoCode")
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 10, groups = SizeConstraint.class)
	private String promoCode;

	/** The tdsp code ccs. */
	@QueryParam(value = "tdspCodeCCS")
	@Size(max = 5, groups = SizeConstraint.class)
	private String tdspCodeCCS;

	/** The esid. */
	@QueryParam(value = "esid")
	@Size(max = 32, groups = SizeConstraint.class)
	private String esid;

	
	/**
	 * Gets the transaction type.
	 *
	 * @return the transaction type
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * Sets the transaction type.
	 *
	 * @param transactionType the new transaction type
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * Gets the promo code.
	 *
	 * @return the promo code
	 */
	public String getPromoCode() {
		return promoCode;
	}

	/**
	 * Sets the promo code.
	 *
	 * @param promoCode the new promo code
	 */
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	/**
	 * Gets the tdsp code ccs.
	 *
	 * @return the tdsp code ccs
	 */
	public String getTdspCodeCCS() {
		return tdspCodeCCS;
	}

	/**
	 * Sets the tdsp code ccs.
	 *
	 * @param tdspCodeCCS the new tdsp code ccs
	 */
	public void setTdspCodeCCS(String tdspCodeCCS) {
		this.tdspCodeCCS = tdspCodeCCS;
	}

	/**
	 * Gets the esid.
	 *
	 * @return the esid
	 */
	public String getEsid() {
		return esid;
	}

	/**
	 * Sets the esid.
	 *
	 * @param esid the new esid
	 */
	public void setEsid(String esid) {
		this.esid = esid;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
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