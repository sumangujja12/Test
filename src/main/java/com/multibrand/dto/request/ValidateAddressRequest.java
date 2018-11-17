/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.CommonUtil;

/**
 * @author jyogapa1
 * 
 */
public class ValidateAddressRequest extends BaseAffiliateRequest implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Length(max = 10, groups = SizeConstraint.class)
	private String streetNum = null;
	
	@Length(max = 60, groups = SizeConstraint.class)
	private String streetName = null;
	
	@Length(max = 10, groups = SizeConstraint.class)
	private String aptNum = null;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 30, groups = SizeConstraint.class)
	private String city = null;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(min = 2, max = 2, groups = SizeConstraint.class, message ="{err.msg.state.format}")
	private String state = null;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String zipCode = null;
	
	@Length(max = 25, groups = SizeConstraint.class)
	private String poBox = null;
	//private String country = null;

	/**
	 * @return the streetNum
	 */
	public String getStreetNum() {
		return streetNum;
	}

	/**
	 * @param streetNum
	 *            the streetNum to set
	 */
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the aptNum
	 */
	public String getAptNum() {
		return aptNum;
	}

	/**
	 * @param aptNum
	 *            the aptNum to set
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the poBox
	 */
	public String getPoBox() {
		return poBox;
	}

	/**
	 * @param poBox
	 *            the poBox to set
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

		
	/**
	 * @return the country
	 */
	/*public String getCountry() {
		return country;
	}*/

	/**
	 * @param country the country to set
	 */
	/*public void setCountry(String country) {
		this.country = country;
	}*/
	

	/**
	 * Gets street address.
	 * 
	 * @return 
	 */
	public String getStreetAddress() {
		StringBuffer streetAddress = new StringBuffer();
		if (StringUtils.isNotEmpty(this.streetNum)) {
			streetAddress.append(streetNum);
			if (StringUtils.isNotEmpty(streetName)) {
				streetAddress.append(" ").append(streetName);
			}
		} else if (StringUtils.isNotEmpty(streetName)) {
			streetAddress.append(streetName);
		}
		
		return streetAddress.toString();
	}

	@Override
	public String toString() {
		return super.toString() + CommonUtil.doRender(this);
	}

}
