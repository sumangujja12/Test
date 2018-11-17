package com.multibrand.vo.response.billingResponse;

public class PhoneDO {
	
	private String phoneNumber;
    private String remark;
    private String SMSActiveFlag="";
    private String bouncedFlag="";
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the remark
	 */
	public String getRemark()
	{
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	/**
	 * @return the sMSActiveFlag
	 */
	public String getSMSActiveFlag()
	{
		return SMSActiveFlag;
	}
	/**
	 * @param sMSActiveFlag the sMSActiveFlag to set
	 */
	public void setSMSActiveFlag(String sMSActiveFlag)
	{
		SMSActiveFlag = sMSActiveFlag;
	}
	public String getBouncedFlag() {
		return bouncedFlag;
	}
	public void setBouncedFlag(String bouncedFlag) {
		this.bouncedFlag = bouncedFlag;
	}
	
	

	
}
