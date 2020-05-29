/**
 * 
 */
package com.multibrand.dto.request;

import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Length;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;

public class SalesOfferRequest extends SalesBaseRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The transaction type. */
	@QueryParam(value = "transactionType")
	@Length(max = 3, groups = SizeConstraint.class)
	private String transactionType; //MVI or SWI

	
	/** The promo code. */
	@QueryParam(value = "promoCode")
	@Length(max = 10, groups = SizeConstraint.class)
	private String promoCode;

	/** The tdsp code ccs. */
	@QueryParam(value = "tdspCodeCCS")
	@Length(max = 5, groups = SizeConstraint.class)
	private String tdspCodeCCS;

	/** The esid. */
	@QueryParam(value = "esid")
	@Length(max = 32, groups = SizeConstraint.class)
	private String esid;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

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

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}
}