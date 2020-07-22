package com.multibrand.vo.response.lucenesearch;

/** This class will be used in GSON mapping the Search Response 
 * 
 * @author RKiran
 */

public class Capabilities {
	

    private String capabilityType;

    private String esiId;

    private String tdu;

    private String meterType;

    private String address;

    private String addressOverflow;

    private String city;

    private String state;

    private String zipcode;
    
    private String aglcPremisesNumber ;
    
    private String aglcAccountNumber ;
    
    public void setCapabilityType(String capabilityType){
        this.capabilityType = capabilityType;
    }
    public String getCapabilityType(){
        return this.capabilityType;
    }
    public void setEsiId(String esiId){
        this.esiId = esiId;
    }
    public String getEsiId(){
        return this.esiId;
    }
    public void setTdu(String tdu){
        this.tdu = tdu;
    }
    public String getTdu(){
        return this.tdu;
    }
    public void setMeterType(String meterType){
        this.meterType = meterType;
    }
    public String getMeterType(){
        return this.meterType;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddressOverflow(String addressOverflow){
        this.addressOverflow = addressOverflow;
    }
    public String getAddressOverflow(){
        return this.addressOverflow;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void setZipcode(String zipcode){
        this.zipcode = zipcode;
    }
    public String getZipcode(){
        return this.zipcode;
    }
	public String getAglcPremisesNumber() {
		return aglcPremisesNumber;
	}
	public void setAglcPremisesNumber(String aglcPremisesNumber) {
		this.aglcPremisesNumber = aglcPremisesNumber;
	}
	public String getAglcAccountNumber() {
		return aglcAccountNumber;
	}
	public void setAglcAccountNumber(String aglcAccountNumber) {
		this.aglcAccountNumber = aglcAccountNumber;
	}
	


}
