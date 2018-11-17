package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.dto.response.TCSBPDetailsDTO;

@XmlRootElement(name = "TCSBPDetailsResponse")
public class TCSBPDetailsResponse extends GenericResponse {
	
	private List<TCSBPDetailsDTO> tcsBPDetailsList;

	/**
	 * @return the tcsBPDetailsList
	 */
	public List<TCSBPDetailsDTO> getTcsBPDetailsList() {
		return tcsBPDetailsList;
	}

	/**
	 * @param tcsBPDetailsList the tcsBPDetailsList to set
	 */
	public void setTcsBPDetailsList(List<TCSBPDetailsDTO> tcsBPDetailsList) {
		this.tcsBPDetailsList = tcsBPDetailsList;
	}
	
}
