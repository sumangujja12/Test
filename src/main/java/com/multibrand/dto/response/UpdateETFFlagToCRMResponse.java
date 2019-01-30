package com.multibrand.dto.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.UpdateETFFlagToCRMResponseOutData;

@XmlRootElement(name="UpdateETFFlagToCRMResponse")
public class UpdateETFFlagToCRMResponse extends GenericResponse implements Serializable {

	private static final long serialVersionUID = -3671204145307613817L;
	
	@SerializedName("d")
	 UpdateETFFlagToCRMResponseOutData updateETFFlagToCRMResponseOutData;

	public UpdateETFFlagToCRMResponseOutData getUpdateETFFlagToCRMResponseOutData() {
		return updateETFFlagToCRMResponseOutData;
	}

	public void setUpdateETFFlagToCRMResponseOutData(UpdateETFFlagToCRMResponseOutData updateETFFlagToCRMResponseOutData) {
		this.updateETFFlagToCRMResponseOutData = updateETFFlagToCRMResponseOutData;
	}
	
}