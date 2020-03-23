/**
 * 
 */
package com.multibrand.dto.request;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author 289347
 *
 */
public class ProspectDataRequest extends SalesBaseRequest {

	private static final long serialVersionUID = 1L;

	@QueryParam(value = "prospectID")
	@NotBlank
	private String prospectID;
	
	@QueryParam(value = "last4SSN") 
	@NotBlank
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
	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}