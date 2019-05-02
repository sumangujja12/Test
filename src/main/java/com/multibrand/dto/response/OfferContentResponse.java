package com.multibrand.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.SerializedName;
import com.multibrand.util.Constants;

@Component
@XmlRootElement(name="OfferContentResponse")
public class OfferContentResponse implements Constants {
	
	private String offerHeadline;
	private String offerDescription;
	private String energyTypeDescription;
	private String energyTypeIcon;
	private String specialOfferDescription;
	private String specialOfferIcon;
	private String productDisclaimer;
	private String genericDisclaimer;
	private String errorMessage;
	@SerializedName("messageKey")
	@JsonIgnore
	private String offerCode;
	/**
	 * @return the offerHeadline
	 */
	public String getOfferHeadline() {
		return offerHeadline;
	}
	/**
	 * @param offerHeadline the offerHeadline to set
	 */
	public void setOfferHeadline(String offerHeadline) {
		this.offerHeadline = offerHeadline;
	}
	/**
	 * @return the offerDescription
	 */
	public String getOfferDescription() {
		return offerDescription;
	}
	/**
	 * @param offerDescription the offerDescription to set
	 */
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	/**
	 * @return the energyTypeDescription
	 */
	public String getEnergyTypeDescription() {
		return energyTypeDescription;
	}
	/**
	 * @param energyTypeDescription the energyTypeDescription to set
	 */
	public void setEnergyTypeDescription(String energyTypeDescription) {
		this.energyTypeDescription = energyTypeDescription;
	}
	/**
	 * @return the energyTypeIcon
	 */
	public String getEnergyTypeIcon() {
		return energyTypeIcon;
	}
	/**
	 * @param energyTypeIcon the energyTypeIcon to set
	 */
	public void setEnergyTypeIcon(String energyTypeIcon) {
		this.energyTypeIcon = energyTypeIcon;
	}
	/**
	 * @return the specialOfferDescription
	 */
	public String getSpecialOfferDescription() {
		return specialOfferDescription;
	}
	/**
	 * @param specialOfferDescription the specialOfferDescription to set
	 */
	public void setSpecialOfferDescription(String specialOfferDescription) {
		this.specialOfferDescription = specialOfferDescription;
	}
	/**
	 * @return the specialOfferIcon
	 */
	public String getSpecialOfferIcon() {
		return specialOfferIcon;
	}
	/**
	 * @param specialOfferIcon the specialOfferIcon to set
	 */
	public void setSpecialOfferIcon(String specialOfferIcon) {
		this.specialOfferIcon = specialOfferIcon;
	}
	/**
	 * @return the productDisclaimer
	 */
	public String getProductDisclaimer() {
		return productDisclaimer;
	}
	/**
	 * @param productDisclaimer the productDisclaimer to set
	 */
	public void setProductDisclaimer(String productDisclaimer) {
		this.productDisclaimer = productDisclaimer;
	}
	/**
	 * @return the genericDisclaimer
	 */
	public String getGenericDisclaimer() {
		return genericDisclaimer;
	}
	/**
	 * @param genericDisclaimer the genericDisclaimer to set
	 */
	public void setGenericDisclaimer(String genericDisclaimer) {
		this.genericDisclaimer = genericDisclaimer;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		if(this.errorMessage != null && this.errorMessage.contains("ERROR")) {
			return CONTET_ERROR_MESSAGEKEY;
		}
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	
	
	

	
}
