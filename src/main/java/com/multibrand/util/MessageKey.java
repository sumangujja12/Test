package com.multibrand.util;

public class MessageKey {

	private String keyName;
	private MessageKeyTypeEnum keyType;
	private SearchTypeEnum searchType;
	
	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}
	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	/**
	 * @return the keyType
	 */
	public MessageKeyTypeEnum getKeyType() {
		return keyType;
	}
	/**
	 * @param keyType the keyType to set
	 */
	public void setKeyType(MessageKeyTypeEnum keyType) {
		this.keyType = keyType;
	}
	/**
	 * @return the searchType
	 */
	public SearchTypeEnum getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(SearchTypeEnum searchType) {
		this.searchType = searchType;
	}
	
	

}
