package com.multibrand.vo.response.billingResponse;



import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.vo.response.GenericResponse;
import com.nrg.cxfstubs.profile.ZesZesuerStat;

@XmlRootElement(name="AccountDetails")
@Component
public class GetAccountDetailsResponse extends GenericResponse {

	private ContractAccountDO contractAccountDO;
	//US-F222-DK | 10312018
	private  String paymentReceiptPopupShowFlag;
	private  ZesZesuerStat zesZesuerStat;

    public String getPaymentReceiptPopupShowFlag() {
		return paymentReceiptPopupShowFlag;
	}

	public void setPaymentReceiptPopupShowFlag(String paymentReceiptPopupShowFlag) {
		this.paymentReceiptPopupShowFlag = paymentReceiptPopupShowFlag;
	}

	private java.lang.String emailID="";  
    
    private java.lang.String emailBounceFlag="";
    
    public java.lang.String getEmailBounceFlag() {
    		return emailBounceFlag;    	
	}

	public void setEmailBounceFlag(java.lang.String emailBounceFlag) {
		this.emailBounceFlag = emailBounceFlag;
	}

	private java.lang.String mktPreference="";

    private com.multibrand.vo.response.billingResponse.PhoneDO[] phoneDO;

    public java.lang.String getEmailID() {
		return emailID;
	}

	public void setEmailID(java.lang.String emailID) {
		this.emailID = emailID;
	}

	public java.lang.String getMktPreference() {
		return mktPreference;
	}

	public void setMktPreference(java.lang.String mktPreference) {
		this.mktPreference = mktPreference;
	}

	public com.multibrand.vo.response.billingResponse.PhoneDO[] getPhoneDO() {
		return phoneDO;
	}

	public void setPhoneDO(com.multibrand.vo.response.billingResponse.PhoneDO[] phoneDO) {
		this.phoneDO = phoneDO;
	}

	public ContractAccountDO getContractAccountDO() {
		return contractAccountDO;
	}

	public void setContractAccountDO(ContractAccountDO contractAccountDO) {
		this.contractAccountDO = contractAccountDO;
	}

	/**
	 * @return the zesZesuerStat
	 */
	public ZesZesuerStat getZesZesuerStat() {
		return zesZesuerStat;
	}

	/**
	 * @param zesZesuerStat the zesZesuerStat to set
	 */
	public void setZesZesuerStat(ZesZesuerStat zesZesuerStat) {
		this.zesZesuerStat = zesZesuerStat;
	}

	
	
	
}
