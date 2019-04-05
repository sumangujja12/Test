package com.multibrand.vo.response.contentResponse;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name = "content")
@Component
public class MobileContentResponse extends GenericResponse {

	private Boolean isVersionUpdated=null;
	private Integer vesrionNumber=null;
	private MobileContentItem errors;

	public MobileContentItem getErrors() {
		return errors;
	}

	public void setErrors(MobileContentItem errors) {
		this.errors = errors;
	}

	public Boolean getIsVersionUpdated() {
		return isVersionUpdated;
	}

	public void setIsVersionUpdated(Boolean isVersionUpdated) {
		this.isVersionUpdated = isVersionUpdated;
	}

	public Integer getVesrionNumber() {
		return vesrionNumber;
	}

	public void setVesrionNumber(Integer vesrionNumber) {
		this.vesrionNumber = vesrionNumber;
	}

}
