/**
 * 
 */
package com.multibrand.vo.response.tpv;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.vo.response.GenericResponse;

/**
 * @author kbhulla1
 *
 */
@XmlRootElement(name="TPVApiTransUpdResponse")
@Component 
public class TransUpdResponseVO extends GenericResponse {

	private String type;
	private String message;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString() {
		return "TransUpdResponseVO [type=" + type + ", message=" + message + "]";
	}
	
	
}
