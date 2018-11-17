package com.multibrand.dto.response;

import java.math.BigDecimal;

public class NewCreditScoreResponseDTO {
	private String firstName;
	private String lastName;
	private String companyCode;
	private String enrollType;
	private String essId;
	private String moveInDate;
	private String offerCode;
	private String svrcAptNum;
	private String svrcStreetName;
	private String svrcStreetNum;
	private String svrcCity;
	private String svrcRegion;
	private String svrcCountry;
	private String svrcPostCode;
	private String billStreetName;
	private String billStreetNum;
	private String billAptNum;
	private String billPOBox;
	private String billPostCode;
	private String billCity;
	private String billRegion;
	private String billCountry;
	private String ssn;
	private String defaultScore;
	private String defaultSource;
	private String businessPartner;
	private String creditBucket;
	private String creditScore;
	private String creditSource;
	private String defaultDepositAmt;
	private String depositCode;
	private String depositHold;
	private String depositReason;
	private BigDecimal depositAmt;
	private String dueDate;
	private BigDecimal pmScore;
	private String errCode;
	private String errMessage;
	private com.multibrand.domain.FactorDetailDO[] arrayFactors;
	private String creditScoreDate;
	private String creditScoreLow;
	private String creditScoreHigh;
	private String acctSecStatus;
	private BigDecimal bondPrice;
	private BigDecimal customerFee;
	private String payUpFrontFlag;
	private mc_style.functions.soap.sap.document.sap_com.Tline[] listDeposittext;
	private mc_style.functions.soap.sap.document.sap_com.ZekunDepositDetFields zekunDepositDetailFields;
	private mc_style.functions.soap.sap.document.sap_com.ZetCreditCall zetCreditCall;
	private String exAcpFlag;
	private String exBp;
	private BigDecimal exDepAmtPolr;
	private String exDwellingType;
	private String exExtScrReq;
	private BigDecimal exPmScore;
	private String exProspectScoreUsed;
	private String exTdsp;
	private mc_style.functions.soap.sap.document.sap_com.ZesAcctSecResExport[] listZesAcctSecResExport;
	private mc_style.functions.soap.sap.document.sap_com.ZesCreditAlert[] zesCreditAlert;
	private mc_style.functions.soap.sap.document.sap_com.ZesKeyFactorCgi[] zesKeyFactorCgi;
	private mc_style.functions.soap.sap.document.sap_com.ZetKeyFactDes[] zetKeyFactDes;
	private mc_style.functions.soap.sap.document.sap_com.ZetSecrtyNotif[] zetSecrtyNotif;
	private mc_style.functions.soap.sap.document.sap_com.ZesSecrtyNotifHold[] zesSecrtyNotifHold;
	private static org.apache.axis.description.TypeDesc typeDesc;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * @return the enrollType
	 */
	public String getEnrollType() {
		return enrollType;
	}
	/**
	 * @param enrollType the enrollType to set
	 */
	public void setEnrollType(String enrollType) {
		this.enrollType = enrollType;
	}
	/**
	 * @return the essId
	 */
	public String getEssId() {
		return essId;
	}
	/**
	 * @param essId the essId to set
	 */
	public void setEssId(String essId) {
		this.essId = essId;
	}
	/**
	 * @return the moveInDate
	 */
	public String getMoveInDate() {
		return moveInDate;
	}
	/**
	 * @param moveInDate the moveInDate to set
	 */
	public void setMoveInDate(String moveInDate) {
		this.moveInDate = moveInDate;
	}
	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}
	/**
	 * @param offerCode the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	/**
	 * @return the svrcAptNum
	 */
	public String getSvrcAptNum() {
		return svrcAptNum;
	}
	/**
	 * @param svrcAptNum the svrcAptNum to set
	 */
	public void setSvrcAptNum(String svrcAptNum) {
		this.svrcAptNum = svrcAptNum;
	}
	/**
	 * @return the svrcStreetName
	 */
	public String getSvrcStreetName() {
		return svrcStreetName;
	}
	/**
	 * @param svrcStreetName the svrcStreetName to set
	 */
	public void setSvrcStreetName(String svrcStreetName) {
		this.svrcStreetName = svrcStreetName;
	}
	/**
	 * @return the svrcStreetNum
	 */
	public String getSvrcStreetNum() {
		return svrcStreetNum;
	}
	/**
	 * @param svrcStreetNum the svrcStreetNum to set
	 */
	public void setSvrcStreetNum(String svrcStreetNum) {
		this.svrcStreetNum = svrcStreetNum;
	}
	/**
	 * @return the svrcCity
	 */
	public String getSvrcCity() {
		return svrcCity;
	}
	/**
	 * @param svrcCity the svrcCity to set
	 */
	public void setSvrcCity(String svrcCity) {
		this.svrcCity = svrcCity;
	}
	/**
	 * @return the svrcRegion
	 */
	public String getSvrcRegion() {
		return svrcRegion;
	}
	/**
	 * @param svrcRegion the svrcRegion to set
	 */
	public void setSvrcRegion(String svrcRegion) {
		this.svrcRegion = svrcRegion;
	}
	/**
	 * @return the svrcCountry
	 */
	public String getSvrcCountry() {
		return svrcCountry;
	}
	/**
	 * @param svrcCountry the svrcCountry to set
	 */
	public void setSvrcCountry(String svrcCountry) {
		this.svrcCountry = svrcCountry;
	}
	/**
	 * @return the svrcPostCode
	 */
	public String getSvrcPostCode() {
		return svrcPostCode;
	}
	/**
	 * @param svrcPostCode the svrcPostCode to set
	 */
	public void setSvrcPostCode(String svrcPostCode) {
		this.svrcPostCode = svrcPostCode;
	}
	/**
	 * @return the billStreetName
	 */
	public String getBillStreetName() {
		return billStreetName;
	}
	/**
	 * @param billStreetName the billStreetName to set
	 */
	public void setBillStreetName(String billStreetName) {
		this.billStreetName = billStreetName;
	}
	/**
	 * @return the billStreetNum
	 */
	public String getBillStreetNum() {
		return billStreetNum;
	}
	/**
	 * @param billStreetNum the billStreetNum to set
	 */
	public void setBillStreetNum(String billStreetNum) {
		this.billStreetNum = billStreetNum;
	}
	/**
	 * @return the billAptNum
	 */
	public String getBillAptNum() {
		return billAptNum;
	}
	/**
	 * @param billAptNum the billAptNum to set
	 */
	public void setBillAptNum(String billAptNum) {
		this.billAptNum = billAptNum;
	}
	/**
	 * @return the billPOBox
	 */
	public String getBillPOBox() {
		return billPOBox;
	}
	/**
	 * @param billPOBox the billPOBox to set
	 */
	public void setBillPOBox(String billPOBox) {
		this.billPOBox = billPOBox;
	}
	/**
	 * @return the billPostCode
	 */
	public String getBillPostCode() {
		return billPostCode;
	}
	/**
	 * @param billPostCode the billPostCode to set
	 */
	public void setBillPostCode(String billPostCode) {
		this.billPostCode = billPostCode;
	}
	/**
	 * @return the billCity
	 */
	public String getBillCity() {
		return billCity;
	}
	/**
	 * @param billCity the billCity to set
	 */
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}
	/**
	 * @return the billRegion
	 */
	public String getBillRegion() {
		return billRegion;
	}
	/**
	 * @param billRegion the billRegion to set
	 */
	public void setBillRegion(String billRegion) {
		this.billRegion = billRegion;
	}
	/**
	 * @return the billCountry
	 */
	public String getBillCountry() {
		return billCountry;
	}
	/**
	 * @param billCountry the billCountry to set
	 */
	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}
	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return the defaultScore
	 */
	public String getDefaultScore() {
		return defaultScore;
	}
	/**
	 * @param defaultScore the defaultScore to set
	 */
	public void setDefaultScore(String defaultScore) {
		this.defaultScore = defaultScore;
	}
	/**
	 * @return the defaultSource
	 */
	public String getDefaultSource() {
		return defaultSource;
	}
	/**
	 * @param defaultSource the defaultSource to set
	 */
	public void setDefaultSource(String defaultSource) {
		this.defaultSource = defaultSource;
	}
	/**
	 * @return the businessPartner
	 */
	public String getBusinessPartner() {
		return businessPartner;
	}
	/**
	 * @param businessPartner the businessPartner to set
	 */
	public void setBusinessPartner(String businessPartner) {
		this.businessPartner = businessPartner;
	}
	/**
	 * @return the creditBucket
	 */
	public String getCreditBucket() {
		return creditBucket;
	}
	/**
	 * @param creditBucket the creditBucket to set
	 */
	public void setCreditBucket(String creditBucket) {
		this.creditBucket = creditBucket;
	}
	/**
	 * @return the creditScore
	 */
	public String getCreditScore() {
		return creditScore;
	}
	/**
	 * @param creditScore the creditScore to set
	 */
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	/**
	 * @return the creditSource
	 */
	public String getCreditSource() {
		return creditSource;
	}
	/**
	 * @param creditSource the creditSource to set
	 */
	public void setCreditSource(String creditSource) {
		this.creditSource = creditSource;
	}
	/**
	 * @return the defaultDepositAmt
	 */
	public String getDefaultDepositAmt() {
		return defaultDepositAmt;
	}
	/**
	 * @param defaultDepositAmt the defaultDepositAmt to set
	 */
	public void setDefaultDepositAmt(String defaultDepositAmt) {
		this.defaultDepositAmt = defaultDepositAmt;
	}
	/**
	 * @return the depositCode
	 */
	public String getDepositCode() {
		return depositCode;
	}
	/**
	 * @param depositCode the depositCode to set
	 */
	public void setDepositCode(String depositCode) {
		this.depositCode = depositCode;
	}
	/**
	 * @return the depositHold
	 */
	public String getDepositHold() {
		return depositHold;
	}
	/**
	 * @param depositHold the depositHold to set
	 */
	public void setDepositHold(String depositHold) {
		this.depositHold = depositHold;
	}
	/**
	 * @return the depositReason
	 */
	public String getDepositReason() {
		return depositReason;
	}
	/**
	 * @param depositReason the depositReason to set
	 */
	public void setDepositReason(String depositReason) {
		this.depositReason = depositReason;
	}
	/**
	 * @return the depositAmt
	 */
	public BigDecimal getDepositAmt() {
		return depositAmt;
	}
	/**
	 * @param depositAmt the depositAmt to set
	 */
	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the pmScore
	 */
	public BigDecimal getPmScore() {
		return pmScore;
	}
	/**
	 * @param pmScore the pmScore to set
	 */
	public void setPmScore(BigDecimal pmScore) {
		this.pmScore = pmScore;
	}
	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}
	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return errMessage;
	}
	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	/**
	 * @return the arrayFactors
	 */
	public com.multibrand.domain.FactorDetailDO[] getArrayFactors() {
		return arrayFactors;
	}
	/**
	 * @param arrayFactors the arrayFactors to set
	 */
	public void setArrayFactors(com.multibrand.domain.FactorDetailDO[] arrayFactors) {
		this.arrayFactors = arrayFactors;
	}
	/**
	 * @return the creditScoreDate
	 */
	public String getCreditScoreDate() {
		return creditScoreDate;
	}
	/**
	 * @param creditScoreDate the creditScoreDate to set
	 */
	public void setCreditScoreDate(String creditScoreDate) {
		this.creditScoreDate = creditScoreDate;
	}
	/**
	 * @return the creditScoreLow
	 */
	public String getCreditScoreLow() {
		return creditScoreLow;
	}
	/**
	 * @param creditScoreLow the creditScoreLow to set
	 */
	public void setCreditScoreLow(String creditScoreLow) {
		this.creditScoreLow = creditScoreLow;
	}
	/**
	 * @return the creditScoreHigh
	 */
	public String getCreditScoreHigh() {
		return creditScoreHigh;
	}
	/**
	 * @param creditScoreHigh the creditScoreHigh to set
	 */
	public void setCreditScoreHigh(String creditScoreHigh) {
		this.creditScoreHigh = creditScoreHigh;
	}
	/**
	 * @return the acctSecStatus
	 */
	public String getAcctSecStatus() {
		return acctSecStatus;
	}
	/**
	 * @param acctSecStatus the acctSecStatus to set
	 */
	public void setAcctSecStatus(String acctSecStatus) {
		this.acctSecStatus = acctSecStatus;
	}
	/**
	 * @return the bondPrice
	 */
	public BigDecimal getBondPrice() {
		return bondPrice;
	}
	/**
	 * @param bondPrice the bondPrice to set
	 */
	public void setBondPrice(BigDecimal bondPrice) {
		this.bondPrice = bondPrice;
	}
	/**
	 * @return the customerFee
	 */
	public BigDecimal getCustomerFee() {
		return customerFee;
	}
	/**
	 * @param customerFee the customerFee to set
	 */
	public void setCustomerFee(BigDecimal customerFee) {
		this.customerFee = customerFee;
	}
	/**
	 * @return the payUpFrontFlag
	 */
	public String getPayUpFrontFlag() {
		return payUpFrontFlag;
	}
	/**
	 * @param payUpFrontFlag the payUpFrontFlag to set
	 */
	public void setPayUpFrontFlag(String payUpFrontFlag) {
		this.payUpFrontFlag = payUpFrontFlag;
	}
	/**
	 * @return the listDeposittext
	 */
	public mc_style.functions.soap.sap.document.sap_com.Tline[] getListDeposittext() {
		return listDeposittext;
	}
	/**
	 * @param listDeposittext the listDeposittext to set
	 */
	public void setListDeposittext(
			mc_style.functions.soap.sap.document.sap_com.Tline[] listDeposittext) {
		this.listDeposittext = listDeposittext;
	}
	/**
	 * @return the zekunDepositDetailFields
	 */
	public mc_style.functions.soap.sap.document.sap_com.ZekunDepositDetFields getZekunDepositDetailFields() {
		return zekunDepositDetailFields;
	}
	/**
	 * @param zekunDepositDetailFields the zekunDepositDetailFields to set
	 */
	public void setZekunDepositDetailFields(
			mc_style.functions.soap.sap.document.sap_com.ZekunDepositDetFields zekunDepositDetailFields) {
		this.zekunDepositDetailFields = zekunDepositDetailFields;
	}
	/**
	 * @return the zetCreditCall
	 */
	public mc_style.functions.soap.sap.document.sap_com.ZetCreditCall getZetCreditCall() {
		return zetCreditCall;
	}
	/**
	 * @param zetCreditCall the zetCreditCall to set
	 */
	public void setZetCreditCall(
			mc_style.functions.soap.sap.document.sap_com.ZetCreditCall zetCreditCall) {
		this.zetCreditCall = zetCreditCall;
	}
	/**
	 * @return the exAcpFlag
	 */
	public String getExAcpFlag() {
		return exAcpFlag;
	}
	/**
	 * @param exAcpFlag the exAcpFlag to set
	 */
	public void setExAcpFlag(String exAcpFlag) {
		this.exAcpFlag = exAcpFlag;
	}
	/**
	 * @return the exBp
	 */
	public String getExBp() {
		return exBp;
	}
	/**
	 * @param exBp the exBp to set
	 */
	public void setExBp(String exBp) {
		this.exBp = exBp;
	}
	/**
	 * @return the exDepAmtPolr
	 */
	public BigDecimal getExDepAmtPolr() {
		return exDepAmtPolr;
	}
	/**
	 * @param exDepAmtPolr the exDepAmtPolr to set
	 */
	public void setExDepAmtPolr(BigDecimal exDepAmtPolr) {
		this.exDepAmtPolr = exDepAmtPolr;
	}
	/**
	 * @return the exDwellingType
	 */
	public String getExDwellingType() {
		return exDwellingType;
	}
	/**
	 * @param exDwellingType the exDwellingType to set
	 */
	public void setExDwellingType(String exDwellingType) {
		this.exDwellingType = exDwellingType;
	}
	/**
	 * @return the exExtScrReq
	 */
	public String getExExtScrReq() {
		return exExtScrReq;
	}
	/**
	 * @param exExtScrReq the exExtScrReq to set
	 */
	public void setExExtScrReq(String exExtScrReq) {
		this.exExtScrReq = exExtScrReq;
	}
	/**
	 * @return the exPmScore
	 */
	public BigDecimal getExPmScore() {
		return exPmScore;
	}
	/**
	 * @param exPmScore the exPmScore to set
	 */
	public void setExPmScore(BigDecimal exPmScore) {
		this.exPmScore = exPmScore;
	}
	/**
	 * @return the exProspectScoreUsed
	 */
	public String getExProspectScoreUsed() {
		return exProspectScoreUsed;
	}
	/**
	 * @param exProspectScoreUsed the exProspectScoreUsed to set
	 */
	public void setExProspectScoreUsed(String exProspectScoreUsed) {
		this.exProspectScoreUsed = exProspectScoreUsed;
	}
	/**
	 * @return the exTdsp
	 */
	public String getExTdsp() {
		return exTdsp;
	}
	/**
	 * @param exTdsp the exTdsp to set
	 */
	public void setExTdsp(String exTdsp) {
		this.exTdsp = exTdsp;
	}
	/**
	 * @return the listZesAcctSecResExport
	 */
	public mc_style.functions.soap.sap.document.sap_com.ZesAcctSecResExport[] getListZesAcctSecResExport() {
		return listZesAcctSecResExport;
	}
	/**
	 * @param listZesAcctSecResExport the listZesAcctSecResExport to set
	 */
	public void setListZesAcctSecResExport(
			mc_style.functions.soap.sap.document.sap_com.ZesAcctSecResExport[] listZesAcctSecResExport) {
		this.listZesAcctSecResExport = listZesAcctSecResExport;
	}
	/**
	 * @return the zesCreditAlert
	 */
	public mc_style.functions.soap.sap.document.sap_com.ZesCreditAlert[] getZesCreditAlert() {
		return zesCreditAlert;
	}
	/**
	 * @param zesCreditAlert the zesCreditAlert to set
	 */
	public void setZesCreditAlert(
			mc_style.functions.soap.sap.document.sap_com.ZesCreditAlert[] zesCreditAlert) {
		this.zesCreditAlert = zesCreditAlert;
	}
	/**
	 * @return the zesKeyFactorCgi
	 */
	public mc_style.functions.soap.sap.document.sap_com.ZesKeyFactorCgi[] getZesKeyFactorCgi() {
		return zesKeyFactorCgi;
	}
	/**
	 * @param zesKeyFactorCgi the zesKeyFactorCgi to set
	 */
	public void setZesKeyFactorCgi(
			mc_style.functions.soap.sap.document.sap_com.ZesKeyFactorCgi[] zesKeyFactorCgi) {
		this.zesKeyFactorCgi = zesKeyFactorCgi;
	}
	/**
	 * @return the zetKeyFactDes
	 */
	public mc_style.functions.soap.sap.document.sap_com.ZetKeyFactDes[] getZetKeyFactDes() {
		return zetKeyFactDes;
	}
	/**
	 * @param zetKeyFactDes the zetKeyFactDes to set
	 */
	public void setZetKeyFactDes(
			mc_style.functions.soap.sap.document.sap_com.ZetKeyFactDes[] zetKeyFactDes) {
		this.zetKeyFactDes = zetKeyFactDes;
	}
	/**
	 * @return the zetSecrtyNotif
	 */
	public mc_style.functions.soap.sap.document.sap_com.ZetSecrtyNotif[] getZetSecrtyNotif() {
		return zetSecrtyNotif;
	}
	/**
	 * @param zetSecrtyNotif the zetSecrtyNotif to set
	 */
	public void setZetSecrtyNotif(
			mc_style.functions.soap.sap.document.sap_com.ZetSecrtyNotif[] zetSecrtyNotif) {
		this.zetSecrtyNotif = zetSecrtyNotif;
	}
	/**
	 * @return the zesSecrtyNotifHold
	 */
	public mc_style.functions.soap.sap.document.sap_com.ZesSecrtyNotifHold[] getZesSecrtyNotifHold() {
		return zesSecrtyNotifHold;
	}
	/**
	 * @param zesSecrtyNotifHold the zesSecrtyNotifHold to set
	 */
	public void setZesSecrtyNotifHold(
			mc_style.functions.soap.sap.document.sap_com.ZesSecrtyNotifHold[] zesSecrtyNotifHold) {
		this.zesSecrtyNotifHold = zesSecrtyNotifHold;
	}
	/**
	 * @return the typeDesc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}
	/**
	 * @param typeDesc the typeDesc to set
	 */
	public static void setTypeDesc(org.apache.axis.description.TypeDesc typeDesc) {
		NewCreditScoreResponseDTO.typeDesc = typeDesc;
	}
}