package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="CslrCcsProfileResponse")
public class CslrCcsProfileResponse extends GenericResponse {
	
	@SerializedName("d")
	private CslrCcsProfileVO cslrCcsProfileVO;

	/**
	 * @return the cslrCcsProfileVO
	 */
	public CslrCcsProfileVO getCslrCcsProfileVO() {
		return cslrCcsProfileVO;
	}

	/**
	 * @param cslrCcsProfileVO the cslrCcsProfileVO to set
	 */
	public void setCslrCcsProfileVO(CslrCcsProfileVO cslrCcsProfileVO) {
		this.cslrCcsProfileVO = cslrCcsProfileVO;
	}
	
}
