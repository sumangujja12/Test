package com.multibrand.vo.response.gmd;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.multibrand.vo.response.GenericResponse;

@XmlRootElement
@XmlSeeAlso({ GMDZoneByEsiIdResponseVO.class })
public class GMDZoneByEsiIdResponseVO extends GenericResponse

{
	private List<Zone> zoneList;

	/**
	 * @return the zoneList
	 */
	public List<Zone> getZoneList() {
		return zoneList;
	}

	/**
	 * @param zoneList the zoneList to set
	 */
	public void setZoneList(List<Zone> zoneList) {
		this.zoneList = zoneList;
	}
}
