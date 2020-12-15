package com.multibrand.vo.response.gmd;

import com.multibrand.vo.response.GenericResponse;

public class LmpPriceSpikeResponse extends GenericResponse {
	
	private String exMessage;
	private String exPriceSpikeFound;
	private LmpAllZonesPriceResponse extAllZonesPrice;
	private LmpSpikedPriceResponse extSpikedPrice;
	private GmdZoneCaResponse extZoneCa;
	
	public String getExMessage() {
		return exMessage;
	}
	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}
	public String getExPriceSpikeFound() {
		return exPriceSpikeFound;
	}
	public void setExPriceSpikeFound(String exPriceSpikeFound) {
		this.exPriceSpikeFound = exPriceSpikeFound;
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
