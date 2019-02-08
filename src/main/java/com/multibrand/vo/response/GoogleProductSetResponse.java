package com.multibrand.vo.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="GoogleProductSetResponse")
public class GoogleProductSetResponse extends GenericResponse implements Serializable{

	private static final long serialVersionUID = -3404996051795114145L;
	
	@SerializedName("d")
	private GoogleProductSetResponseOutData googleProductSetResponseOutData;
	
	public GoogleProductSetResponseOutData getGoogleProductSetResponseOutData() {
		return googleProductSetResponseOutData;
	}
	
	public void setGoogleProductSetResponseOutData(GoogleProductSetResponseOutData googleProductSetResponseOutData) {
		this.googleProductSetResponseOutData = googleProductSetResponseOutData;
	}

	@Override
	public String toString() {
		return "GoogleProductSetResponse [googleProductSetResponseOutData=" + googleProductSetResponseOutData + "]";
	}
	
	

	
}
