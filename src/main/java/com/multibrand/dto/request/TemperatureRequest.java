package com.multibrand.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidDateTime;
import com.multibrand.util.Constants;

public class TemperatureRequest implements FormEntityRequest, Constants, Serializable {
	
	
	//ps.print("contractAccountNumber=000071214185&contractId=0042306109&esid=1008901023816906180105&fromDate=2014-09-29&toDate=2015-05-10");
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String contractAccountNumber;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String contractId;
	
	@NotBlank(groups = BasicConstraint.class)
	private String esid;
	
	@NotBlank(groups = BasicConstraint.class)
	@ValidDateTime(format = "yyyy-MM-dd", groups = FormatConstraint.class, message = "must be in yyyy-MM-dd format")
	private String fromDate;
	
	@NotBlank(groups = BasicConstraint.class)
	@ValidDateTime(format = "yyyy-MM-dd", groups = FormatConstraint.class, message = "must be in yyyy-MM-dd format")
	private String toDate;

	/**
	 * @return the contractAccountNumber
	 */
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}

	/**
	 * @param contractAccountNumber the contractAccountNumber to set
	 */
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}

	/**
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return the esid
	 */
	public String getEsid() {
		return esid;
	}

	/**
	 * @param esid the esid to set
	 */
	public void setEsid(String esid) {
		this.esid = esid;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TemperatureRequest [contractAccountNumber="
				+ contractAccountNumber + ", contractId=" + contractId
				+ ", esid=" + esid + ", fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}
	
}
