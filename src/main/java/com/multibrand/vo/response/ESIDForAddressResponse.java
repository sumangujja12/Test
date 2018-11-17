package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="ESIDForAddress")
public class ESIDForAddressResponse extends GenericResponse {
	
	
	private java.lang.String companyCode;

    private java.lang.String customerClass;

   /* private java.lang.String errCode;

    private java.lang.String errMessage;
*/
    private java.lang.String meterType;

    private java.lang.String pointofDeliveryID;

    private java.lang.String serviceId;

    
    
	public java.lang.String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(java.lang.String companyCode) {
		this.companyCode = companyCode;
	}

	public java.lang.String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(java.lang.String customerClass) {
		this.customerClass = customerClass;
	}

	public java.lang.String getMeterType() {
		return meterType;
	}

	public void setMeterType(java.lang.String meterType) {
		this.meterType = meterType;
	}

	public java.lang.String getPointofDeliveryID() {
		return pointofDeliveryID;
	}

	public void setPointofDeliveryID(java.lang.String pointofDeliveryID) {
		this.pointofDeliveryID = pointofDeliveryID;
	}

	public java.lang.String getServiceId() {
		return serviceId;
	}

	public void setServiceId(java.lang.String serviceId) {
		this.serviceId = serviceId;
	}
	
}
