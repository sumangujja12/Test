package com.multibrand.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.multibrand.dto.GMDServiceLocationDetailsDTO;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

public class GMDServiceLocationPreParedStatementSetter implements PreparedStatementSetter {

	private GMDServiceLocationDetailsDTO serviceLocationDTO;

	public GMDServiceLocationPreParedStatementSetter(GMDServiceLocationDetailsDTO serviceLocationDTO) {
		this.serviceLocationDTO = serviceLocationDTO;
	}
	
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setInt(1,Integer.parseInt(serviceLocationDTO.getTrackingId()));
		ps.setInt(2, Integer.parseInt(serviceLocationDTO.getPersonId()));
		ps.setString(3, serviceLocationDTO.getServiceRequestTypeCode());
		ps.setString(4, serviceLocationDTO.getOfferCode());
		ps.setString(5, serviceLocationDTO.getEsid());
		ps.setString(6, serviceLocationDTO.getContractAccountNumber());
		ps.setString(7, serviceLocationDTO.getBussinessPartnerNumber());
		ps.setString(8, serviceLocationDTO.getServAddressLine1());
		ps.setString(9, serviceLocationDTO.getServAddressLine2());
		ps.setString(10, serviceLocationDTO.getServCity());
		ps.setString(11, serviceLocationDTO.getServState());
		ps.setString(12, serviceLocationDTO.getServZipCode());
		
		ps.setString(14, serviceLocationDTO.getServiceAddressOverrideFlag());
		if(Constants.FLAG_YES.equalsIgnoreCase(serviceLocationDTO.getAddressBillSameAsServiceFlag())) {
			ps.setString(13, "Y" );
			ps.setString(15, serviceLocationDTO.getServAddressLine1());
			ps.setString(16, serviceLocationDTO.getServAddressLine2());
			ps.setString(17, serviceLocationDTO.getServCity());
			ps.setString(18, serviceLocationDTO.getServState());
			ps.setString(19, serviceLocationDTO.getServZipCode());
		} else {
			ps.setString(13, "N" );
			ps.setString(15, serviceLocationDTO.getBillAddressLine1());
			ps.setString(16, serviceLocationDTO.getBillAddressLine2());
			ps.setString(17, serviceLocationDTO.getBillCity());
			ps.setString(18, serviceLocationDTO.getBillState());
			ps.setString(19, serviceLocationDTO.getBillZipCode());
		}
		
		if (StringUtils.isNotBlank(serviceLocationDTO.getServiceStartDate())) {
			ps.setDate(20, CommonUtil.getSqlDate(serviceLocationDTO.getServiceStartDate(), Constants.DT_FMT_REQUEST));
		} else {
			ps.setDate(20, null);
		}
		ps.setString(21,"");	
		ps.setString(22,serviceLocationDTO.getReferrerCode());
		ps.setString(23,serviceLocationDTO.getPromoCodeEntered());
		
		
		ps.setString(24,serviceLocationDTO.getTdspCode());
		ps.setString(25,serviceLocationDTO.getOfferCodeTitle());
		ps.setString(26,serviceLocationDTO.getCaCheckDigit());
		ps.setString(27,serviceLocationDTO.getErrorCode());
		ps.setString(28,serviceLocationDTO.getCampaignCode());
		ps.setString(29,serviceLocationDTO.getContactByPhoneFlag());
		ps.setString(30,serviceLocationDTO.getContactByEmailFlag());
		ps.setString(31,serviceLocationDTO.getEnrollSource());
		ps.setString(32,serviceLocationDTO.getSwitchHoldStatus());
		ps.setString(33,serviceLocationDTO.getPremiseType());
		ps.setString(34,serviceLocationDTO.getEsidStatus());
		ps.setString(35,Constants.GME_COMPANY_CODE);
		ps.setString(36,serviceLocationDTO.getErrorCodesList());
	}

}
