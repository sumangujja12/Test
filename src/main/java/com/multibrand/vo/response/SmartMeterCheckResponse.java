package com.multibrand.vo.response;
/**
 * author Siva Murugan M
 */
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="SmartMeterCheckResponse")
@Component
public class SmartMeterCheckResponse extends GenericResponse
{
	
	private java.lang.String flag="";

	/**
	 * @return the flag
	 */
	public java.lang.String getFlag()
	{
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(java.lang.String flag)
	{
		this.flag = flag;
	}
	
	
	

}
