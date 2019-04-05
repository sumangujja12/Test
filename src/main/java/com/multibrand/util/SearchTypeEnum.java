package com.multibrand.util;

public enum SearchTypeEnum {

	//ECOMM : Start || US16540 || SDL Integration: Adding Enum Type: EcomFilter || atiwari || 02/18/2019
	TITLE_FILTER("TitleFilter"), STR_VALUE_FILTER("StringValueFilter"), KEYWORD_FILTER("KeywordFilter"), ECOM_FILTER("EcomFilter");
	//ECOMM : END || US16540 || SDL Integration: Adding Enum Type: EcomFilter || atiwari || 02/18/2019
	private final String keyType;

	SearchTypeEnum(String keyType) {
		this.keyType = keyType;
	}

	public String getSearchType() {
		return this.keyType;
	}
}
