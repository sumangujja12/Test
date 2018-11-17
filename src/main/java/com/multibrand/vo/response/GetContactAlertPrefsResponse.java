package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;
import com.multibrand.domain.PreferenceInfo;
import com.multibrand.domain.BillAlertPrefs;
import com.multibrand.domain.MarketingPrefs;
import com.multibrand.domain.PrepayEmailPrefs;
import com.multibrand.domain.PrepayPhonePrefs;
import com.multibrand.domain.PrepayTextPrefs;
import com.multibrand.domain.UsageAlertPrefs;

@XmlRootElement(name="GetContactAlertPrefsResponse")
public class GetContactAlertPrefsResponse extends GenericResponse {

	private MarketingPrefs marketingPref;
	private BillAlertPrefs billAlert;
	private UsageAlertPrefs usageAlert;
	private PrepayEmailPrefs ppemailPref;
	private PrepayPhonePrefs ppphonePref;
	private PrepayTextPrefs pptextPref;
	
	// US12888 | DK | 10182018
	private PreferenceInfo[] preferenceList;

	
	/**
	 * @return the preferenceList
	 */
	public PreferenceInfo[] getPreferenceList() {
		return preferenceList;
	}

	/**
	 * @param preferenceList the preferenceList to set
	 */
	public void setPreferenceList(PreferenceInfo[] preferenceList) {
		this.preferenceList = preferenceList;
	}

	/**
	 * @return the marketingPref
	 */
	public MarketingPrefs getMarketingPref() {
		return marketingPref;
	}

	/**
	 * @param marketingPref the marketingPref to set
	 */
	public void setMarketingPref(MarketingPrefs marketingPref) {
		this.marketingPref = marketingPref;
	}

	/**
	 * @return the billAlert
	 */
	public BillAlertPrefs getBillAlert() {
		return billAlert;
	}

	/**
	 * @param billAlert the billAlert to set
	 */
	public void setBillAlert(BillAlertPrefs billAlert) {
		this.billAlert = billAlert;
	}

	/**
	 * @return the usageAlert
	 */
	public UsageAlertPrefs getUsageAlert() {
		return usageAlert;
	}

	/**
	 * @param usageAlert the usageAlert to set
	 */
	public void setUsageAlert(UsageAlertPrefs usageAlert) {
		this.usageAlert = usageAlert;
	}

	/**
	 * @return the emailPref
	 */
	public PrepayEmailPrefs getPPEmailPref() {
		return ppemailPref;
	}

	/**
	 * @param emailPref the emailPref to set
	 */
	public void setPPEmailPref(PrepayEmailPrefs emailPref) {
		this.ppemailPref = emailPref;
	}

	/**
	 * @return the phonePref
	 */
	public PrepayPhonePrefs getPPPhonePref() {
		return ppphonePref;
	}

	/**
	 * @param phonePref the phonePref to set
	 */
	public void setPPPhonePref(PrepayPhonePrefs phonePref) {
		this.ppphonePref = phonePref;
	}

	/**
	 * @return the prepay
	 */
	public PrepayTextPrefs getPPTextPref() {
		return pptextPref;
	}

	/**
	 * @param prepay the prepay to set
	 */
	public void setPPTextPref(PrepayTextPrefs prepay) {
		this.pptextPref = prepay;
	}



}
