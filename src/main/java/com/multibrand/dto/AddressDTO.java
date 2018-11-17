package com.multibrand.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author dkrishn1
 *
 */
public class AddressDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String unitNum="";
	private String streetNum="";
	private String streetName="";
	private String city="";
	private String state="";
	private String zipcode="";
	private String zipcodeComplete="";
	private String poBox="";
	private String country="";
	private String trilliumMatchStatus=""; //COMPLETE_MATCH, NO_MATCH, PARTIAL_MATCH
	private String trilliumCallStatus="";   
	/*private String formattedAddress="";
	private String streetAddress="";*/

	public String getTrilliumCallStatus() {
		return trilliumCallStatus;
	}
	
	
	public void setTrilliumCallStatus(String trilliumCallStatus) {
		this.trilliumCallStatus = trilliumCallStatus;
	}
	
	
	public String getTrilliumMatchStatus() {
		return trilliumMatchStatus;
	}
	
	
	public void setTrilliumMatchStatus(String trilliumMatchStatus) {
		this.trilliumMatchStatus = trilliumMatchStatus;
	}
	
	
	/**
	 * @return the unitNum
	 */
	public String getUnitNum() {
		return unitNum;
	}
	
	
	/**
	 * @param unitNum the unitNum to set
	 */
	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	
	
	/**
	 * @return the streetNum
	 */
	public String getStreetNum() {
		return streetNum;
	}
	
	
	/**
	 * @param streetNum the streetNum to set
	 */
	public void setStreetNum(String streetNum) {
		if(null!=streetNum){
			streetNum=streetNum.trim();
		}
		this.streetNum = streetNum;
	}
	
	
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	
	
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		if(null!=streetName){
			streetName=streetName.trim();
		}
		this.streetName = streetName;
	}
	
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	
	/**
	 * @param city the city to set
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
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	
	
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		if(StringUtils.isBlank(zipcode)){
			this.zipcode ="";
		}else if(zipcode.length()>=5){
			this.zipcode = zipcode.substring(0, 5);
		}else{
			this.zipcode=zipcode;
		}
	}
	
	
	/**
	 * @return the poBox
	 */
	public String getPoBox() {
		return poBox;
	}
	
	
	/**
	 * @param poBox the poBox to set
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	
	
	
	
	public String getCountry() {
		return country;
	}
	
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	public String getFormattedAddress() {
		
	   String formattedAddr = "";
	   StringBuffer buff = new StringBuffer();
	    
	   if(StringUtils.isBlank(StringUtils.trim(poBox))) {
		   buff.append(getStreetAddress());	
	   }
	   else{
		   buff.append("PO Box: ").append(poBox);
	   }
	   
	   formattedAddr =  buff.append(", ").append(city).append(", ")
		  				.append(state).append(' ')
		  				.append(zipcode).toString();
	   return formattedAddr;
	}
	
	public String getFormattedAddressWithFullZipcode() {
		
		   String formattedAddr = "";
		   StringBuffer buff = new StringBuffer();
		    
		   if(StringUtils.isBlank(StringUtils.trim(poBox))) {
			   
			   buff.append(getStreetAddress());	
		   }
		   else{
			   buff.append("PO Box: ").append(poBox);
		   }
		   
		   formattedAddr =  buff.append(", ").append(city).append(", ")
			  				.append(state).append(' ')
			  				.append(zipcodeComplete).toString();
		   return formattedAddr;
		}
	
	public String getStreetAddress() {
		
		 StringBuffer buff = null;
		 String streetAddress=null;
		    
		   if(StringUtils.isBlank(StringUtils.trim(poBox))) {
			   
			   buff = new StringBuffer()
			   				  .append(streetNum).append(' ')
			   				  .append(streetName);
			   
			   if(StringUtils.isNotBlank(StringUtils.trim(unitNum))) {
				   buff.append(" #").append(unitNum);
			   }
		   }
		   if(buff!=null) {
			   return buff.toString();
		   }
		   else {  
		   return streetAddress;
		   }
	}
	
	
	public String getZipcodeComplete() {
		return zipcodeComplete;
	}
	
	
	public void setZipcodeComplete(String zipcodeComplete) {
		this.zipcodeComplete = zipcodeComplete;
	}
	
	
	public String getAddressLine1() {

		String addressLine1 = "";
		StringBuffer buff = new StringBuffer();

		if (StringUtils.isBlank(StringUtils.trim(poBox))) {

			buff.append(getStreetAddress());
		} else {
			buff.append("PO Box: ").append(poBox);
		}

		addressLine1 = buff.toString();
	
		return addressLine1;
	}
	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}

}
