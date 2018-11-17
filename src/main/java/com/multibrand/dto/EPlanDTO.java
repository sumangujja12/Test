package com.multibrand.dto;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author vsood30
 *
 */
public class EPlanDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5783194813524248914L;
	private String esuiteStatus;
	private String esuiteAuthFlag;
	private String esuiteOAMFlag;
	private String esuiteEmailFlag;
	private String esuiteOAMDate;
	private String esuiteAutoPayFlag;
	private String esuiteEmailDate;
	private String esuiteAutopayDate;
	private String esuiteAutopayOption;
	private PaymentDTO autoPayDTO;

	public String getEsuiteStatus() {
		return esuiteStatus;
	}

	public void setEsuiteStatus(String esuiteStatus) {
		this.esuiteStatus = esuiteStatus;
	}

	public String getEsuiteAuthFlag() {
		return esuiteAuthFlag;
	}

	public void setEsuiteAuthFlag(String esuiteAuthFlag) {
		this.esuiteAuthFlag = esuiteAuthFlag;
	}

	public String getEsuiteOAMFlag() {
		return esuiteOAMFlag;
	}

	public void setEsuiteOAMFlag(String esuiteOAMFlag) {
		this.esuiteOAMFlag = esuiteOAMFlag;
	}

	public String getEsuiteEmailFlag() {
		return esuiteEmailFlag;
	}

	public void setEsuiteEmailFlag(String esuiteEmailFlag) {
		this.esuiteEmailFlag = esuiteEmailFlag;
	}

	public String getEsuiteOAMDate() {
		return esuiteOAMDate;
	}

	public void setEsuiteOAMDate(String esuiteOAMDate) {
		this.esuiteOAMDate = esuiteOAMDate;
	}

	public String getEsuiteAutoPayFlag() {
		return esuiteAutoPayFlag;
	}

	public void setEsuiteAutoPayFlag(String esuiteAutoPayFlag) {
		this.esuiteAutoPayFlag = esuiteAutoPayFlag;
	}

	public String getEsuiteEmailDate() {
		return esuiteEmailDate;
	}

	public void setEsuiteEmailDate(String esuiteEmailDate) {
		this.esuiteEmailDate = esuiteEmailDate;
	}

	public String getEsuiteAutopayDate() {
		return esuiteAutopayDate;
	}

	public void setEsuiteAutopayDate(String esuiteAutopayDate) {
		this.esuiteAutopayDate = esuiteAutopayDate;
	}

	public String getEsuiteAutopayOption() {
		return esuiteAutopayOption;
	}

	public void setEsuiteAutopayOption(String esuiteAutopayOption) {
		this.esuiteAutopayOption = esuiteAutopayOption;
	}

	public PaymentDTO getAutoPayDTO() {
		return autoPayDTO;
	}

	public void setAutoPayDTO(PaymentDTO autoPayDTO) {
		this.autoPayDTO = autoPayDTO;
	}
	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}	
}
