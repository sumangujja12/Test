package com.multibrand.vo.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.BpData;
import com.multibrand.vo.response.EmailInformation;
import com.multibrand.vo.response.PhoneInformation;

/**
 * 
 * @author dkrishn1
 *
 */
@XmlRootElement
public class SMSOptInOutEligibilityResponse extends GenericResponse{
	private String status;	
	List<PhoneInformation> phones = new ArrayList<PhoneInformation>();
	EmailInformation email = new EmailInformation();
	
	private String type;
	private String code;
	private String message;
	List<BpData> activeBPList = new ArrayList<BpData>();
	List<BpData> inactiveBPList = new ArrayList<BpData>();
	private String evOptinEligible;
	private String evOptoutEligible;

	
	public List<PhoneInformation> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneInformation> phones) {
		this.phones = phones;
	}

	public EmailInformation getEmail() {
		return email;
	}

	public void setEmail(EmailInformation email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BpData> getActiveBPList() {
		return activeBPList;
	}

	public void setActiveBPList(List<BpData> activeBPList) {
		this.activeBPList = activeBPList;
	}

	public List<BpData> getInactiveBPList() {
		return inactiveBPList;
	}

	public void setInactiveBPList(List<BpData> inactiveBPList) {
		this.inactiveBPList = inactiveBPList;
	}

	public String getEvOptinEligible() {
		return evOptinEligible;
	}

	public void setEvOptinEligible(String evOptinEligible) {
		this.evOptinEligible = evOptinEligible;
	}

	public String getEvOptoutEligible() {
		return evOptoutEligible;
	}

	public void setEvOptoutEligible(String evOptoutEligible) {
		this.evOptoutEligible = evOptoutEligible;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	


	@Override
	public String toString() {
		return "SMSOptInOutEligibilityResponse [status=" + status + ", phones=" + phones + ", email=" + email
				+ ", type=" + type + ", code=" + code + ", message=" + message + ", activeBPList=" + activeBPList
				+ ", inactiveBPList=" + inactiveBPList + ", evOptinEligible=" + evOptinEligible + ", evOptoutEligible="
				+ evOptoutEligible + "]";
	}
	
}
