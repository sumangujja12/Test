package com.multibrand.vo.response.gmd;

import javax.xml.bind.annotation.XmlRootElement;
import com.multibrand.vo.response.GenericResponse;

@XmlRootElement
public class PriceSpikeAlertResponse  extends GenericResponse {
	
	private String message;
	private String priceSpikeFound;
	private ProjectedPrice projectedPrice;
	private SpikeProjectedPrice spikeProjectedPrice;
	private ZoneCa zoneCa;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPriceSpikeFound() {
		return priceSpikeFound;
	}
	public void setPriceSpikeFound(String priceSpikeFound) {
		this.priceSpikeFound = priceSpikeFound;
	}
	public ProjectedPrice getProjectedPrice() {
		return projectedPrice;
	}
	public void setProjectedPrice(ProjectedPrice projectedPrice) {
		this.projectedPrice = projectedPrice;
	}
	public SpikeProjectedPrice getSpikeProjectedPrice() {
		return spikeProjectedPrice;
	}
	public void setSpikeProjectedPrice(SpikeProjectedPrice spikeProjectedPrice) {
		this.spikeProjectedPrice = spikeProjectedPrice;
	}
	public ZoneCa getZoneCa() {
		return zoneCa;
	}
	public void setZoneCa(ZoneCa zoneCa) {
		this.zoneCa = zoneCa;
	}
	
	
	
	
	

}
