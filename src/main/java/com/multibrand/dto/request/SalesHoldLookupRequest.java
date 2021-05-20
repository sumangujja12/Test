package com.multibrand.dto.request;

import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;

import com.multibrand.request.validation.NotEmpty;

public class SalesHoldLookupRequest  extends SalesBaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@QueryParam(value = "caNumber")
	@NotEmpty
	@Size(max = 12)
	private String caNumber;
	
	@QueryParam(value = "checkDigit")
	@NotEmpty
	@Size(max = 1)
	private String checkDigit;
	
	@QueryParam(value = "billZipCode")
	@NotEmpty
	@Size(max = 10)
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
