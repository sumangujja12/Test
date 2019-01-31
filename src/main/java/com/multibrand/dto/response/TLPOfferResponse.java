package com.multibrand.dto.response;

import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.TLPOfferDO;

public class TLPOfferResponse extends GenericResponse {
	
	private TLPOfferDO[] TLPOfferList;

	public TLPOfferDO[] getTLPOfferList() {
		return TLPOfferList;
	}

	public void setTLPOfferList(TLPOfferDO[] tLPOfferList) {
		TLPOfferList = tLPOfferList;
	}

	
	
	
	


}
