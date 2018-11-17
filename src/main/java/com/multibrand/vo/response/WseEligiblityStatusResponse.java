package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({WseEsenseEligibility.class})
public class WseEligiblityStatusResponse extends GenericResponse
{

	
	private List<WseEsenseEligibility> WseEsenseEligibilityList;

	/**
	 * @return the wseEsenseEligibilityList
	 */
	public List<WseEsenseEligibility> getWseEsenseEligibilityList()
	{
		return WseEsenseEligibilityList;
	}

	/**
	 * @param wseEsenseEligibilityList the wseEsenseEligibilityList to set
	 */
	public void setWseEsenseEligibilityList(
			List<WseEsenseEligibility> wseEsenseEligibilityList)
	{
		WseEsenseEligibilityList = wseEsenseEligibilityList;
	}
	
	
	
}
