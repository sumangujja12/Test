/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import com.multibrand.dto.AddressDTO;
import com.multibrand.util.CommonUtil;

/**
 * @author jyogapa1
 * 
 */
public class CheckPermitRequest extends BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddressDTO serviceAddress = null;
	private String localeLanguageCode = null;
	private String permitType = null;

	public AddressDTO getServiceAddress() {
		if (null == serviceAddress) {
			serviceAddress = new AddressDTO();
			setServiceAddress(serviceAddress);
		}
		return serviceAddress;
	}

	public void setServiceAddress(AddressDTO serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	/**
	 * @return the localeLanguageCode
	 */
	//public String getLocaleLanguageCode() {
		//return localeLanguageCode;
	//}

	/**
	 * @param localeLanguageCode
	 *            the localeLanguageCode to set
	 */
	//public void setLocaleLanguageCode(String localeLanguageCode) {
	//	this.localeLanguageCode = localeLanguageCode;
	//}
		
	/**
	 * @return the permitType
	 */
	public String getPermitType() {
		return permitType;
	}

	/**
	 * @param permitType the permitType to set
	 */
	public void setPermitType(String permitType) {
		this.permitType = permitType;
	}
	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}
