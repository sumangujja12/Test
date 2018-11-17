package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransferServiceResponse extends GenericResponse {
	
	    private java.lang.String IDO_number;

		public java.lang.String getIDO_number() {
			return IDO_number;
		}

		public void setIDO_number(java.lang.String iDO_number) {
			IDO_number = iDO_number;
		}
	    
	    

}
