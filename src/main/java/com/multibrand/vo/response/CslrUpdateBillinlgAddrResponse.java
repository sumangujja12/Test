package com.multibrand.vo.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="CslrCcsProfileResponse")
public class CslrUpdateBillinlgAddrResponse extends GenericResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5368169802583075934L;
	
	@SerializedName("d")
	private CslrUpdateBillinlgAddrVO cslrUpdateBillinlgAddrVO;

	/**
	 * @return the cslrUpdateBillinlgAddrVO
	 */
	public CslrUpdateBillinlgAddrVO getCslrUpdateBillinlgAddrVO() {
		return cslrUpdateBillinlgAddrVO;
	}

	/**
	 * @param cslrUpdateBillinlgAddrVO the cslrUpdateBillinlgAddrVO to set
	 */
	public void setCslrUpdateBillinlgAddrVO(CslrUpdateBillinlgAddrVO cslrUpdateBillinlgAddrVO) {
		this.cslrUpdateBillinlgAddrVO = cslrUpdateBillinlgAddrVO;
	}
	
}
