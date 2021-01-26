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
	
	public void loadMessage() {
		
		if(getXCODE() != null) {
			switch(getXCODE()) {
				case "00" : message =  "Successful Payment";break;
				case "01" : message =  "Retry Payment";break;
				case "52" : message =  "Duplicate Payment";break;
				case "53" : message =  "Bank Routing Error";break;
				case "54" : message =  "Failed Payment"; break;
				case "03" : message =  "Invalid bank route #";break;
				case "04" : message = "Invalid bank acct #";break;
				case "07" : message =  "System error";break;
				case "98" : message =  "# of max lines exceeded";break;
				case "08" : message =  "Number range error";break;
			}
		 }
	}
		
	/**
	 * @return the message
	 */
	public String getMessage() {
				
		return message;
				
	}

	
	@Override
	public String toString() {
		return "NEIPaypalPaymentResponse [eotbdId=" + eotbdid + ", xcode=" + xcode + ", message=" + getMessage() + "]";
	}
	
}
