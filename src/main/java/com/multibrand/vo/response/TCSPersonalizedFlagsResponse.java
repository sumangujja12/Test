package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.dto.response.TCSPersonalizedFlagsDTO;

@XmlRootElement(name = "TCSPersonalizedFlagsResponse")
public class TCSPersonalizedFlagsResponse extends GenericResponse {
	
	private TCSPersonalizedFlagsDTO tcsPersonalizedFlagsDTO;

	public TCSPersonalizedFlagsDTO getTcsPersonalizedFlagsDTO() {
		return tcsPersonalizedFlagsDTO;
	}

	public void setTcsPersonalizedFlagsDTO(TCSPersonalizedFlagsDTO tcsPersonalizedFlagsDTO) {
		this.tcsPersonalizedFlagsDTO = tcsPersonalizedFlagsDTO;
	}
	
}
