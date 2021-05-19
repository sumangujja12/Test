package com.multibrand.dto.request;

import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Length;

import com.multibrand.request.validation.NotEmpty;
import com.multibrand.request.validation.SizeConstraint;

public class SalesHoldLookupRequest  extends SalesBaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@QueryParam(value = "caNumber")
	@NotEmpty
	@Size(max = 12, groups = SizeConstraint.class)
	private String caNumber;
	
	@QueryParam(value = "checkDigit")
	@NotEmpty
	@Size(max = 1, groups = SizeConstraint.class)
	private String checkDigit;
	
	@QueryParam(value = "billZipCode")
	@NotEmpty
	@Size(max = 10, groups = SizeConstraint.class)
	private String billZipCode;

	public String getCaNumber() {
		return caNumber;
	}

	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	public String getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	public String getBillZipCode() {
		return billZipCode;
	}

	public void setBillZipCode(String billZipCode) {
		this.billZipCode = billZipCode;
	}

}
