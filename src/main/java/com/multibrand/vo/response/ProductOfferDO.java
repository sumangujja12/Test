
package com.multibrand.vo.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductOfferDO {

	private String strProductName;
	private String strProductTagLine;
	private String strAdditionalText;
	private String strLegalEase;
	private ProductDetailDO objProdDetail;
	private ArrayList<ProductBonusDO> arrlProdBonus;
	private ArrayList<ProductTypeDO> arrlProdType;
	private ArrayList arrlCCSData;
	private String authText;
	private String seEligible;
	private HashMap<String, String> strTDSPSurcharge;
	private String strAdditionalPricingText;
	private String strPriority;
	
	public ProductOfferDO() {
		arrlProdBonus = new ArrayList<ProductBonusDO>();
		arrlProdType = new ArrayList<ProductTypeDO>();
		authText = "";
	}

	public String getAuthText() {
		return authText;
	}

	public void setAuthText(String authText) {
		this.authText = authText;
	}

	public ArrayList getArrlCCSData() {
		return arrlCCSData;
	}

	public void setArrlCCSData(ArrayList arrlCCSData) {
		this.arrlCCSData = arrlCCSData;
	}

	public ProductDetailDO getObjProdDetail() {
		return objProdDetail;
	}

	public void setObjProdDetail(ProductDetailDO objProdDetail) {
		this.objProdDetail = objProdDetail;
	}

	public String getStrAdditionalText() {
		return strAdditionalText;
	}

	public void setStrAdditionalText(String strAdditionalText) {
		this.strAdditionalText = strAdditionalText;
	}

	public String getStrLegalEase() {
		return strLegalEase;
	}

	public void setStrLegalEase(String strLegalEase) {
		this.strLegalEase = strLegalEase;
	}

	public String getStrProductName() {
		return strProductName;
	}

	public void setStrProductName(String strProductName) {
		this.strProductName = strProductName;
	}

	public String getStrProductTagLine() {
		return strProductTagLine;
	}

	public void setStrProductTagLine(String strProductTagLine) {
		this.strProductTagLine = strProductTagLine;
	}

	public ArrayList<ProductBonusDO> getArrlProdBonus() {
		return arrlProdBonus;
	}

	public void setArrlProdBonus(ArrayList<ProductBonusDO> arrlProdBonus) {
		this.arrlProdBonus = arrlProdBonus;
	}

	public ArrayList<ProductTypeDO> getArrlProdType() {
		return arrlProdType;
	}

	public void setArrlProdType(ArrayList<ProductTypeDO> arrlProdType) {
		this.arrlProdType = arrlProdType;
	}

	public String getSeEligible() {
		return seEligible;
	}

	public void setSeEligible(String seEligible) {
		this.seEligible = seEligible;
	}

	public HashMap<String, String> getStrTDSPSurcharge() {
		return strTDSPSurcharge;
	}

	public void setStrTDSPSurcharge(HashMap<String, String> strTDSPSurcharge) {
		this.strTDSPSurcharge = strTDSPSurcharge;
	}

	public String getStrAdditionalPricingText() {
		return strAdditionalPricingText;
	}

	public void setStrAdditionalPricingText(String strAdditionalPricingText) {
		this.strAdditionalPricingText = strAdditionalPricingText;
	}

	public String getStrPriority() {
		return strPriority;
	}

	public void setStrPriority(String strPriority) {
		this.strPriority = strPriority;
	}

	@Override
	public String toString() {
		return "ProductOfferDO [strProductName=" + strProductName
				+ ", strProductTagLine=" + strProductTagLine
				+ ", strAdditionalText=" + strAdditionalText
				+ ", strLegalEase=" + strLegalEase + ", objProdDetail="
				+ objProdDetail + ", arrlProdBonus=" + arrlProdBonus
				+ ", arrlProdType=" + arrlProdType + ", arrlCCSData="
				+ arrlCCSData + ", authText=" + authText + ", seEligible="
				+ seEligible + ", strTDSPSurcharge=" + strTDSPSurcharge
				+ ", strAdditionalPricingText=" + strAdditionalPricingText
				+ ", strPriority=" + strPriority + "]";
	}
	
	
	
}
