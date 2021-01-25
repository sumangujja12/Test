package com.multibrand.dto.response;

import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;

public class NEIPaypalPaymentResponse  extends ZEISUNEIPAYPALPAYMENTResponse{

	
	public NEIPaypalPaymentResponse() {
		// TODO Auto-generated constructor stub
	}
    public NEIPaypalPaymentResponse(ZEISUNEIPAYPALPAYMENTResponse response) {
		
    	if(response != null) {
	    	this.setXCODE(response.getXCODE());
	    	this.setEOTBDID(response.getEOTBDID());
    	}
	}
    
    
	private String message;
	
		
	/**
	 * @return the message
	 */
	public String getMessage() {
		
		if(xcode != null) {
			switch(xcode) {
				case "00" : return "Successful Payment";
				case "01" : return "Retry Payment";
				case "52" : return "Duplicate Payment";
				case "53" : return "Bank Routing Error";
				case "54" : return "Failed Payment"; 
				case "03" : return "Invalid bank route #";
				case "04" : return "Invalid bank acct #";
				case "07" : return "System error";
				case "98" : return "# of max lines exceeded";
				case "08" : return "Number range error";
				default : return message;
					 
			}
		 }
		return message;
				
	}

	
	@Override
	public String toString() {
		return "NEIPaypalPaymentResponse [eotbdId=" + eotbdid + ", xcode=" + xcode + ", message=" + message + "]";
	}
	
}
