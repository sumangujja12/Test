package com.multibrand.dto;

import java.io.Serializable;

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
	
	@JsonProperty("tagline")
	private String tagLine;
	
	@JsonProperty("offerCodes")
	private String offerCodes;
	
	
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

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

}
