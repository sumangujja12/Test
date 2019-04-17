package com.multibrand.util;

public enum MessageKeyTypeEnum {

	CONTENT_DATA("ContentData"),
	OFFER_CODE("OfferCode");

	private final String keyType;

	MessageKeyTypeEnum(String keyType) {
		this.keyType = keyType;
	}

	public String getMessageKeyType() {
		return this.keyType;
	}
}
