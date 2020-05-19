package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TdspByZipResponse extends GenericResponse {
	
	
	 private java.lang.String companyCode;

	 private java.lang.String serviceId;
	 private java.lang.String serviceIdDescription;
	 
	/**
	 * @return the companyCode
	 */
	public java.lang.String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(java.lang.String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * @return the serviceId
	 */
	public java.lang.String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(java.lang.String serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return the serviceIdDescription
	 */
	public java.lang.String getServiceIdDescription() {
		return serviceIdDescription;
	}
	/**
	 * @param serviceIdDescription the serviceIdDescription to set
	 */
	public void setServiceIdDescription(java.lang.String serviceIdDescription) {
		this.serviceIdDescription = serviceIdDescription;
	} 
}
