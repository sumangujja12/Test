package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlElement;

import com.multibrand.util.CommonUtil;

public class PendingSwapDO {

	
	 private java.lang.String strBPNumber="";

	    private java.lang.String strCANumber="";

	    private java.lang.String strCampaignCode="";

	    private java.lang.String strContractID="";

	    private java.lang.String strESIID="";

	    private java.lang.String strEndDate="";

	    private java.lang.String strOfferCellCd="";

	    private java.lang.String strOfferCellDesc="";

	    private java.lang.String strOfferCode="";

	    private java.lang.String strProductCd="";

	    private java.lang.String strProductDesc="";

	    private java.lang.String strSignedDate="";

	    private java.lang.String strStartDate="";

		public java.lang.String getStrBPNumber() {
			return strBPNumber;
		}

		public void setStrBPNumber(java.lang.String strBPNumber) {
			this.strBPNumber = strBPNumber;
		}

		public java.lang.String getStrCANumber() {
			return strCANumber;
		}

		public void setStrCANumber(java.lang.String strCANumber) {
			this.strCANumber = strCANumber;
		}

		public java.lang.String getStrCampaignCode() {
			return strCampaignCode;
		}

		public void setStrCampaignCode(java.lang.String strCampaignCode) {
			this.strCampaignCode = strCampaignCode;
		}

		public java.lang.String getStrContractID() {
			return strContractID;
		}

		public void setStrContractID(java.lang.String strContractID) {
			this.strContractID = strContractID;
		}

		public java.lang.String getStrESIID() {
			return strESIID;
		}

		public void setStrESIID(java.lang.String strESIID) {
			this.strESIID = strESIID;
		}

		public java.lang.String getStrEndDate() {
			return CommonUtil.changeDateFormat(strEndDate);
		}

		public void setStrEndDate(java.lang.String strEndDate) {
			this.strEndDate = strEndDate;
		}

		public java.lang.String getStrOfferCellCd() {
			return strOfferCellCd;
		}

		public void setStrOfferCellCd(java.lang.String strOfferCellCd) {
			this.strOfferCellCd = strOfferCellCd;
		}

		public java.lang.String getStrOfferCellDesc() {
			return strOfferCellDesc;
		}

		public void setStrOfferCellDesc(java.lang.String strOfferCellDesc) {
			this.strOfferCellDesc = strOfferCellDesc;
		}

		public java.lang.String getStrOfferCode() {
			return strOfferCode;
		}

		public void setStrOfferCode(java.lang.String strOfferCode) {
			this.strOfferCode = strOfferCode;
		}

		@XmlElement(name="strProductCode")
		public java.lang.String getStrProductCd() {
			return strProductCd;
		}

		public void setStrProductCd(java.lang.String strProductCd) {
			this.strProductCd = strProductCd;
		}

		public java.lang.String getStrProductDesc() {
			return strProductDesc;
		}

		public void setStrProductDesc(java.lang.String strProductDesc) {
			this.strProductDesc = strProductDesc;
		}

		public java.lang.String getStrSignedDate() {
			return CommonUtil.changeDateFormat(strSignedDate);
		}

		public void setStrSignedDate(java.lang.String strSignedDate) {
			this.strSignedDate = strSignedDate;
		}

		public java.lang.String getStrStartDate() {
			return CommonUtil.changeDateFormat(strStartDate);
		}

		public void setStrStartDate(java.lang.String strStartDate) {
			this.strStartDate = strStartDate;
		}
	    
	    
}
