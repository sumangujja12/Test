package com.multibrand.vo.response.billingResponse;



import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.multibrand.vo.response.GenericResponse;
import com.nrg.cxfstubs.profile.ZesZesuerStat;

@XmlRootElement(name="AccountDetails")
@Component
public class GetAccountDetailsResponse extends GenericResponse {

	private ContractAccountDO contractAccountDO;
	//US-F222-DK | 10312018
	private  String paymentReceiptPopupShowFlag;
	private  ZesZesuerStat zesZesuerStat;
	private  boolean isEVPlan = true;
	private  String smartCarToken ="6f4732ba-9b5c-426c-9f48-260fe0ae8750";
	private  List<String> smartCarVehicleIdList;


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

	/**
	 * @return the isEVPlan
	 */
	
	@XmlElement(name = "isEVPlan")
	@JsonProperty(value="isEVPlan")
	public boolean isEVPlan() {
		return isEVPlan;
	}

	/**
	 * @param isEVPlan the isEVPlan to set
	 */
	public void setEVPlan(boolean isEVPlan) {
		this.isEVPlan = isEVPlan;
	}

	/**
	 * @return the smartCarToken
	 */
	public String getSmartCarToken() {
		return smartCarToken;
	}

	/**
	 * @param smartCarToken the smartCarToken to set
	 */
	public void setSmartCarToken(String smartCarToken) {
		this.smartCarToken = smartCarToken;
	}

	/**
	 * @return the smartCarVehicleIdList
	 */
	public List<String> getSmartCarVehicleIdList() {
		smartCarVehicleIdList = new ArrayList();
		smartCarVehicleIdList.add("c6cf7cdb-d8e0-4780-a775-7529560dc583");
		return smartCarVehicleIdList;
	}

	/**
	 * @param smartCarVehicleIdList the smartCarVehicleIdList to set
	 */
	public void setSmartCarVehicleIdList(List<String> smartCarVehicleIdList) {
		this.smartCarVehicleIdList = smartCarVehicleIdList;
	}
}
