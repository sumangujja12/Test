package com.multibrand.dao;

import java.util.List;

import com.multibrand.dto.response.TCSBPDetailsDTO;

public interface TCSDAO {
	public List<TCSBPDetailsDTO> getBPDetails( String agreementId);
}
