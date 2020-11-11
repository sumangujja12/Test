package com.multibrand.vo.response.gmd;

import com.multibrand.vo.response.GenericResponse;

public class GmdHourHeadsSpikeResponse extends GenericResponse {

	private String exMessage;
	private String expriceSpikeFound;
	private LmpAllZonesPriceResponse extAllZonesPrice;
	private LmpSpikedPriceResponse extSpikedPrice;
	private GmdZoneCaResponse extZoneCa;
	
	public String getExMessage() {
		return exMessage;
	}
	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}
	public String getExpriceSpikeFound() {
		return expriceSpikeFound;
	}
	public void setExpriceSpikeFound(String expriceSpikeFound) {
		this.expriceSpikeFound = expriceSpikeFound;
	}
	public LmpAllZonesPriceResponse getExtAllZonesPrice() {
		return extAllZonesPrice;
	}
	public void setExtAllZonesPrice(LmpAllZonesPriceResponse extAllZonesPrice) {
		this.extAllZonesPrice = extAllZonesPrice;
	}
	public LmpSpikedPriceResponse getExtSpikedPrice() {
		return extSpikedPrice;
	}
	public void setExtSpikedPrice(LmpSpikedPriceResponse extSpikedPrice) {
		this.extSpikedPrice = extSpikedPrice;
	}
	public GmdZoneCaResponse getExtZoneCa() {
		return extZoneCa;
	}
	public void setExtZoneCa(GmdZoneCaResponse extZoneCa) {
		this.extZoneCa = extZoneCa;
	}
	
	

}
