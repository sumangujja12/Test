package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

@Component
public class NEIPaypalPaymentRequest implements FormEntityRequest, Constants, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5573962052855620479L;


	@Length(max = 12, groups = SizeConstraint.class)
	private String uername;

	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 13, groups = SizeConstraint.class)
	@Pattern(regexp = " ^0$|^[1-9]\\d*$|^\\.\\d+$|^0\\.\\d*$|^[1-9]\\d*\\.\\d*$", groups = FormatConstraint.class, message=" is  invalid")
	private String payment;

	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 60, groups = SizeConstraint.class)
	private String ppalauth;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 20, groups = SizeConstraint.class)	
	private String ssId;

	/**
	 * @return the uername
	 */
	public String getUername() {
		return uername;
	}

	/**
	 * @param uername the uername to set
	 */
	public void setUername(String uername) {
		this.uername = uername;
	}

	/**
	 * @return the payment
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	/**
	 * @return the ppalauth
	 */
	public String getPpalauth() {
		return ppalauth;
	}

	/**
	 * @param ppalauth the ppalauth to set
	 */
	public void setPpalauth(String ppalauth) {
		this.ppalauth = ppalauth;
	}

	/**
	 * @return the ssId
	 */
	public String getSsId() {
		return ssId;
	}

	/**
	 * @param ssId the ssId to set
	 */
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaypalPaymentRequest [uername=" + uername + ", payment=" + payment + ", ppalauth=" + ppalauth
				+ ", ssId=" + ssId + "]";
	}

}
