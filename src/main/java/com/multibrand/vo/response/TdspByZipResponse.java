package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TdspByZipResponse extends GenericResponse {
	
	
	 private java.lang.String companyCode;

	    private java.lang.String serviceId;

		public java.lang.String getCompanyCode() {
			return companyCode;
		}

		public void setCompanyCode(java.lang.String companyCode) {
			this.companyCode = companyCode;
		}

		public java.lang.String getServiceId() {
			return serviceId;
		}

		public void setServiceId(java.lang.String serviceId) {
			this.serviceId = serviceId;
		}

	    
	    

}
