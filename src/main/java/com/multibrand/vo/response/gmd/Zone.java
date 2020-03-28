package com.multibrand.vo.response.gmd;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Zone")
public class Zone
{

	private String zoneId;

	/**
	 * @return the zoneId
	 */
	public String getZoneId() {
		return zoneId;
	}

	/**
	 * @param zoneId the zoneId to set
	 */
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
}
