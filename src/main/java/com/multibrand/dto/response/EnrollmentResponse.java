/**
 * 
 */
package com.multibrand.dto.response;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

/**
 * @author jyogapa1
 * 
 */
@XmlRootElement
public class EnrollmentResponse extends GenericResponse {

	// @Deprecated
	// private OESignupDTO oeSignupDTO;

	// private String transactionId = null;
	private String trackingId = null;
	private String idocNumber = null;
	private String caNumber = null;
	private String checkDigit = null;
	private String bpid = null;
	private Response.Status httpStatus;
	
	public EnrollmentResponse() {
		super();
	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}

	/**
	 * @param trackingId
	 *            the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	/**
	 * @return the checkDigit
	 */
	public String getCheckDigit() {
		return checkDigit;
	}

	/**
	 * @param checkDigit
	 *            the checkDigit to set
	 */
	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	/**
	 * @return the caNumber
	 */
	public String getCaNumber() {
		return caNumber;
	}

	/**
	 * @param caNumber
	 *            the caNumber to set
	 */
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	/**
	 * @return the idocNumber
	 */
	public String getIdocNumber() {
		return idocNumber;
	}

	/**
	 * @param idocNumber
	 *            the idocNumber to set
	 */
	public void setIdocNumber(String idocNumber) {
		this.idocNumber = idocNumber;
	}

	/**
	 * @return the bpid
	 */
	public String getBpid() {
		return bpid;
	}

	/**
	 * @param bpid
	 *            the bpid to set
	 */
	public void setBpid(String bpid) {
		this.bpid = bpid;
	}

	public Response.Status getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Response.Status httpStatus) {
		this.httpStatus = httpStatus;
	}

}
