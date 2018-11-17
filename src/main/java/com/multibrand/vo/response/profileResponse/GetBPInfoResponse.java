package com.multibrand.vo.response.profileResponse;

import java.util.List;

import com.multibrand.vo.response.BussinessPartnerDO;
import com.multibrand.vo.response.GenericResponse;

public class GetBPInfoResponse extends GenericResponse
{

	private List<BussinessPartnerDO> bussinessPartnerInfo;

	/**
	 * @return the bussinessPartnerInfo
	 */
	public List<BussinessPartnerDO> getBussinessPartnerInfo()
	{
		return bussinessPartnerInfo;
	}

	/**
	 * @param bussinessPartnerInfo the bussinessPartnerInfo to set
	 */
	public void setBussinessPartnerInfo(
			List<BussinessPartnerDO> bussinessPartnerInfo)
	{
		this.bussinessPartnerInfo = bussinessPartnerInfo;
	}
	
	
	
	
}
