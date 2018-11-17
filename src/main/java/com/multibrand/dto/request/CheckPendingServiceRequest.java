/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import com.multibrand.dto.AddressDTO;
import com.multibrand.dto.ESIDDTO;
import com.multibrand.util.CommonUtil;

/**
 * @author jyogapa1
 * 
 */
public class CheckPendingServiceRequest extends BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddressDTO serviceAddress = null;
	private ESIDDTO esid;
	private String localeLanguageCode = null;

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

	public ESIDDTO getEsid() {
		if (null == esid) {
			esid = new ESIDDTO();
			setEsid(esid);
		}
		return esid;
	}

	/**
	 * @param esid
	 *            the esid to set
	 */
	public void setEsid(ESIDDTO esid) {
		this.esid = esid;
	}

	/**
	 * @return the localeLanguageCode
	 */
	public String getLocaleLanguageCode() {
		return localeLanguageCode;
	}

	/**
	 * @param localeLanguageCode
	 *            the localeLanguageCode to set
	 */
	public void setLocaleLanguageCode(String localeLanguageCode) {
		this.localeLanguageCode = localeLanguageCode;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}
