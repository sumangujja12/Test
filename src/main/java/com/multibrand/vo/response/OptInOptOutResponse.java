package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author dkrishn1
 *
 */
@XmlRootElement
public class OptInOptOutResponse extends GenericResponse {
	private String type;
	private String code;
	private String message;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "OptInOptOutResponse [type=" + type + ", code=" + code + ", message=" + message + "]";
	}
	

}
