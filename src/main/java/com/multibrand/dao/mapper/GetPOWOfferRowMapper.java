package com.multibrand.dao.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.DBConstants;
import com.multibrand.vo.response.POWOfferDO;

public class GetPOWOfferRowMapper implements RowMapper<POWOfferDO> ,DBConstants {

	public POWOfferDO mapRow(ResultSet rs, int rowNum) throws SQLException {
		POWOfferDO powOfferDO = new POWOfferDO();
		powOfferDO.setOfferCode(rs.getString(OFFER));
		powOfferDO.setPromotionCode(rs.getString(PROMO));
		powOfferDO.setCampaignCode(rs.getString(CAMPAIGN_CD));
		powOfferDO.seteSenseFlag(rs.getString(ESENCE_ELIG_FLAG));
		//powOfferDO.setValueSegment(rs.getString(VALUE_SEGMENT));
		powOfferDO.setPrePayFlag(rs.getString(PREPAY_FLAG));
		powOfferDO.setTerm(rs.getString(TERM));
		return powOfferDO;
	}
	
}
