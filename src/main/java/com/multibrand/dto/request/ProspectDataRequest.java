/**
 * 
 */
package com.multibrand.dto.request;
import javax.ws.rs.QueryParam;

import com.multibrand.request.validation.NotEmpty;

/**
 * 
 * @author 289347
 *
 */
public class ProspectDataRequest extends SalesBaseRequest {

	private static final long serialVersionUID = 1L;

	@QueryParam(value = "prospectID")
	@NotEmpty
	private String prospectID;
	
	@QueryParam(value = "last4SSN") 
	@NotEmpty
	private String	lastfourdigitSSN;
	
	public String getProspectID() {
		return prospectID;
	}

	public void setProspectID(String prospectID) {
		this.prospectID = prospectID;
	}

	public String getLastfourdigitSSN() {
		return lastfourdigitSSN;
	}

	public void setLastfourdigitSSN(String lastfourdigitSSN) {
		this.lastfourdigitSSN = lastfourdigitSSN;
	}
	

}