package com.multibrand.vo.response;

public class GetAllBPsResponse extends GenericResponse {

	private String bpList[];

	public String[] getBpList() {
		return bpList;
	}

	public void setBpList(String[] bpList) {
		this.bpList = bpList;
	}
	
}
