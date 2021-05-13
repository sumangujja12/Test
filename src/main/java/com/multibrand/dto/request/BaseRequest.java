package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

/**
 * 
 * 
 * @author jyogapa1
 * 
 */
@Component
public class BaseRequest implements FormEntityRequest, Constants, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4445444910516034860L;
	
	@NotBlank(groups = BasicConstraint.class)
	@Size(max = 4, groups = SizeConstraint.class)
	private String companyCode;
	
	@QueryParam(value = "brandId")
	@Size(max = 2, groups = SizeConstraint.class)
	private String brandId;
	
	@QueryParam(value = "languageCode")
	@Size(max = 1, groups = SizeConstraint.class)
	private String languageCode = null;

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode
	 *            the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return the brandName
	 */
	// public String getBrandName() {
	// return brandName;
	// }

	/**
	 * @param brandName
	 *            the brandName to set
	 */
	// public void setBrandName(String brandName) {
	// this.brandName = brandName;
	// }

	/**
	 * @return the companyCode
	 */
	
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode
	 *            the companyCode to set
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
	 * @param brandId
	 *            the brandId to set
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append("companyCode=").append(companyCode).append(';');
		value.append("brandId=").append(brandId).append(';');
		value.append("languageCode=").append(languageCode).append(';');
		
		return value.toString();
	}

	enum CompanyCode{
		WRONG_COMPANY_CODE
	}
}
