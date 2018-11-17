package com.multibrand.dto.request;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.SizeConstraint;

/**
 * Represent request structure of ESID details.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.x, Hibernate-validation 4.x and Jersey 1.17
 */
public class EsidDetailsRequest extends BaseAffiliateRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String servStreetNum;

	
	private String servStreetName;

	@Length(max = 10, groups = SizeConstraint.class)
	private String servStreetAptNum;

	@NotBlank
	@Length(min = 5, max = 10, groups = SizeConstraint.class)
	private String servZipCode;

	// @NotBlank(groups = BasicConstraint.class)
	@Length(max = 3, groups = SizeConstraint.class)
	private String transactionType; // MVI or SWI

	@Length(max = 32, groups = SizeConstraint.class)
	private String esid;
	
	/**
	 * Default constructor.
	 */
	public EsidDetailsRequest() {

	}

	/**
	 * @return the servStreetNum
	 */
	public String getServStreetNum() {
		return servStreetNum;
	}

	/**
	 * @param servStreetNum
	 *            the servStreetNum to set
	 */
	public void setServStreetNum(String servStreetNum) {
		this.servStreetNum = servStreetNum;
	}

	/**
	 * @return the servStreetName
	 */
	public String getServStreetName() {
		return servStreetName;
	}

	/**
	 * @param servStreetName
	 *            the servStreetName to set
	 */
	public void setServStreetName(String servStreetName) {
		this.servStreetName = servStreetName;
	}

	/**
	 * @return the servStreetAptNum
	 */
	public String getServStreetAptNum() {
		return servStreetAptNum;
	}

	/**
	 * @param servStreetAptNum
	 *            the servStreetAptNum to set
	 */
	public void setServStreetAptNum(String servStreetAptNum) {
		this.servStreetAptNum = servStreetAptNum;
	}

	/**
	 * @return the servZipCode
	 */
	public String getServZipCode() {
		return servZipCode;
	}

	/**
	 * @param servZipCode
	 *            the servZipCode to set
	 */
	public void setServZipCode(String servZipCode) {
		this.servZipCode = servZipCode;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return StringUtils.defaultIfEmpty(transactionType, MVI);
	}

	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = StringUtils.defaultIfEmpty(transactionType, MVI);
	}	

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
