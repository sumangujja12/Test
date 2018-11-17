package com.multibrand.dao;

import java.util.List;

import com.multibrand.vo.response.BussinessPartnerDO;

public interface BPAccountContractPayDAO
{
	public List<BussinessPartnerDO> getSVTData(String bpNumber,
			String companyCode, String brandName, String sessionId);
}
