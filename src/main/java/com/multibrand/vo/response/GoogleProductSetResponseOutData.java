/**
 * 
 */
package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

/**
 * @author HChoudhary
 *
 */
@XmlRootElement(name="GoogleProductSetResponseOutData")
public class GoogleProductSetResponseOutData {
	
	@SerializedName("results")
	private List<GoogleProductSetResponseOutDataResult> result;

	public List<GoogleProductSetResponseOutDataResult> getResult() {
		return result;
	}

	public void setResult(List<GoogleProductSetResponseOutDataResult> result) {
		this.result = result;
	}

	

}
