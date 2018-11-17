/***
 * @author dkrishn1
 */

package com.multibrand.vo.response;

import java.util.List;

public class PreferenceInfo {

	private String eventId;
	private String partnetId;
	private String ca;
	private String co;
	private String esid;
	private List<PreferenceParam> paramList;
	
	
	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the partnetId
	 */
	public String getPartnetId() {
		return partnetId;
	}
	/**
	 * @param partnetId the partnetId to set
	 */
	public void setPartnetId(String partnetId) {
		this.partnetId = partnetId;
	}
	/**
	 * @return the ca
	 */
	public String getCa() {
		return ca;
	}
	/**
	 * @param ca the ca to set
	 */
	public void setCa(String ca) {
		this.ca = ca;
	}
	/**
	 * @return the co
	 */
	public String getCo() {
		return co;
	}
	/**
	 * @param co the co to set
	 */
	public void setCo(String co) {
		this.co = co;
	}
	/**
	 * @return the esid
	 */
	public String getEsid() {
		return esid;
	}
	/**
	 * @param esid the esid to set
	 */
	public void setEsid(String esid) {
		this.esid = esid;
	}
	/**
	 * @return the paramList
	 */
	public List<PreferenceParam> getParamList() {
		return paramList;
	}
	/**
	 * @param paramList the paramList to set
	 */
	public void setParamList(List<PreferenceParam> paramList) {
		this.paramList = paramList;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PreferenceInfo [eventId=" + eventId + ", partnetId=" + partnetId + ", ca=" + ca + ", co=" + co
				+ ", esid=" + esid + ", paramList=" + paramList + "]";
	}

}