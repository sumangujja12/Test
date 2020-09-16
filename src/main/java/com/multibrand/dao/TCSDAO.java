package com.multibrand.dao;

import com.multibrand.dto.response.TCSPersonalizedFlagsDTO;

public interface TCSDAO {
	public TCSPersonalizedFlagsDTO getPersonalizedFlags(String bp, String ca);
}
