package com.multibrand.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferPlanSDLVO implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("name")
	private String offerName;
	
	private String highlights;
	
	@JsonProperty("overviewText")
	private String overViewText;
	
	@JsonProperty("promotionTitle")
	private String promotionTitle;
	
	@JsonProperty("promotionText")
	private String promotionText;
	
	@JsonProperty("tagline")
	private String tagLine;
	
	@JsonProperty("isRenewable")
	private String isRenewable;
	
	private String summary;
	
	@JsonProperty("offerCodes")
	private String offerCodes;
	
	@JsonProperty("renewablePercentage")
	private String renewablePercentage;
	
	
	public String getRenewablePercentage() {
		return renewablePercentage;
	}



	public void setRenewablePercentage(String renewablePercentage) {
		this.renewablePercentage = renewablePercentage;
	}



	public String getPromotionTitle() {
		return promotionTitle;
	}
	
	

	public String getIsRenewable() {
		return isRenewable;
	}







	public void setIsRenewable(String isRenewable) {
		this.isRenewable = isRenewable;
	}



	public void setPromotionTitle(String promotionTitle) {
		this.promotionTitle = promotionTitle;
	}

	public String getPromotionText() {
		return promotionText;
	}

	public void setPromotionText(String promotionText) {
		this.promotionText = promotionText;
	}

	public String getOverViewText() {
		return overViewText;
	}

	public String getOfferCodes() {
		return offerCodes;
	}

	public void setOfferCodes(String offerCodes) {
		
		this.offerCodes = offerCodes.replaceAll("[^\\d.]", "");
	}

	public void setOverViewText(String overViewText) {
		this.overViewText = overViewText;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getHighlights() {
		return highlights;
	}
	

	
	@JsonSetter("highlights")
	public void setHighlights(JsonNode highlightNode) throws JsonProcessingException {
		if (highlightNode != null) {
			if (highlightNode.getNodeType() == JsonNodeType.STRING) {
				this.highlights = highlightNode.asText();
			} else if (highlightNode.getNodeType() == JsonNodeType.OBJECT) {
				JsonNode fragmentNode = highlightNode.get("Fragments");
				this.highlights = fragmentNode != null ? fragmentNode.get(0).asText() : "";
			}
		}
		
	}
	
	public String getSummary() {
		return summary;
	}
	
	@JsonSetter("summary")
	public void setSummary(JsonNode summaryNode) {
		if (summaryNode != null) {
			if (summaryNode.getNodeType() == JsonNodeType.STRING) {
				this.summary = summaryNode.asText();
			} else if (summaryNode.getNodeType() == JsonNodeType.OBJECT) {
				JsonNode fragmentNode = summaryNode.get("Fragments");
				this.summary = fragmentNode != null ? fragmentNode.get(0).asText() : "";
			}
		}
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

}
