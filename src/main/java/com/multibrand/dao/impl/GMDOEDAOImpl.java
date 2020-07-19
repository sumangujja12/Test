package com.multibrand.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.mapper.GetTrackNoRowMapper;
import com.multibrand.dao.mapper.UserInfoRowMapper;
import com.multibrand.dto.GMDPersonDetailsDTO;
import com.multibrand.dto.GMDServiceLocationDetailsDTO;
import com.multibrand.dto.request.GMDEnrollmentRequest;
import com.multibrand.resources.GMDResource;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.vo.response.HourlyUsage;
@Repository("GMDOEDAO")
public class GMDOEDAOImpl   {

	@Resource(name = "choiceGMDJdbcTemplate")
	private JdbcTemplate choiceGMDJdbcTemplate;
	
	 @Resource(name="sqlQuerySource")
	 protected AbstractMessageSource sqlMessage;
	 
	private static Logger logger = LogManager.getLogger(GMDOEDAOImpl.class);
		

	public boolean inserPersonDetails(GMDPersonDetailsDTO enrollRequest) {

		StringBuffer strBuffer = new StringBuffer("");
		strBuffer.append(" insert into GME_CHOICE.person_gmd ");
		strBuffer.append(" (PERSON_ID,NAME_FIRST,NAME_LAST,DATE_OF_BIRTH,PHONE_NUMBER,");
		strBuffer.append(" ADDRESS_EMAIL, ADDITIONAL_NAME_FIRST,ADDITIONAL_NAME_LAST,");
		strBuffer.append(" KEEP_ME_INFORMED_FLAG, IDOC_NUMBER, PERSON_STATUS, CREATION_DATE, ");
		strBuffer.append(" LANGUAGE_CD,BUSINESS_PARTNER_ID,COMPANY_CODE, ID_CD) ");
		strBuffer.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		int isUpdate = choiceGMDJdbcTemplate.update(strBuffer.toString(),
				new GMDPersonPreParedStatementSetter(enrollRequest));
		if (isUpdate == 0) {
			return false;
		}
		return true;
	}

	public boolean insertServiceLocationLocation(GMDServiceLocationDetailsDTO enrollRequest) {

		StringBuffer strBuffer = new StringBuffer("");
		strBuffer.append(" insert into GME_CHOICE.SERVICE_LOCATION_GMD ");
		strBuffer.append(" ( TRACKING_NUMBER,PERSON_ID,SRVC_REQ_TYPE_CD,");
		strBuffer.append("	OFFER_CD,ESID,CONTRACT_ACCOUNT_NUM,BUSINESS_PARTNER_ID,SRVC_ADDRESS_LINE_1,");
		strBuffer.append(" SRVC_ADDRESS_LINE_2,SRVC_CITY,SRVC_STATE,SRVC_ZIP,");
		strBuffer.append(" ADDRESS_BILL_SAMEAS_SRVC_FLAG,SRVC_ADDRESS_OVERRIDE_FLAG, ");
		strBuffer.append(" BILL_ADDRESS_LINE_1, BILL_ADDRESS_LINE_2,BILL_CITY,");
		strBuffer.append(" BILL_STATE, BILL_ZIP,SERVICE_START_DATE,");
		strBuffer.append(" ESID_MATCH_FLAG, REFERRER_CD,GEO_ZONE,OFFER_CELL_TRK_CD,");
		strBuffer.append(" COMPLETION_STATUS_CD,AD_ID,TDSP_CD, OFFER_TEASER, CA_CHECK_DIGIT, ERROR_CD,");
		strBuffer.append(" CAMPAIGN_CODE, CONTACTBY_PHONE_FLAG,CONTACTBY_EMAIL_FLAG, ENROLL_SOURCE,");
		strBuffer.append(" SW_HOLD_STATUS,PREMISE_TYPE,ESID_STATUS,BRAND_NAME,COMPANY_CODE,ERROR_CODES_LIST )  ");

		strBuffer.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		int isUpdate = choiceGMDJdbcTemplate.update(strBuffer.toString(),
				new GMDServiceLocationPreParedStatementSetter(enrollRequest));
		if (isUpdate == 0) {
			return false;
		}
		return true;
	}
	
	public Integer getNextValForSequence(String sequence) {
		String query = sqlMessage.getMessage(DBConstants.QUERY_SEQUENCE_NEXTVAL, new Object[] { sequence }, null);

		Integer nextVal = Integer.valueOf(0);

		try {
			nextVal = choiceGMDJdbcTemplate.queryForObject(query, Integer.class);
		} catch (DataAccessException dae) {
			this.logger.error("getDataWithOutParam returing String: Exception while performing DB operation : "
					+ dae.getMessage());
		} catch (Throwable t) {
			this.logger
					.error("getDataWithOutParam returing String: Throwable Exception while performing DB operation : "
							+ t.getMessage());
		}

		
		if (logger.isDebugEnabled()) {
			logger.debug("getNextValForSequence : next value for sequence : " + sequence + " is " + nextVal);
		}
		return nextVal;
	}
	
	
	public boolean checkTrackNo(GMDEnrollmentRequest enrollmentRequest) {

		StringBuffer strBuffer = new StringBuffer("");
		strBuffer.append(" Select TRACKING_NUMBER from GME_CHOICE.SERVICE_LOCATION_GMD ");
		strBuffer.append(" where ESID =? and SERVICE_START_DATE =? ");
		final String esid = enrollmentRequest.getEsiId();
		final String startDate = enrollmentRequest.getServiceStartDate();
		
		List<GMDServiceLocationDetailsDTO> returnList = choiceGMDJdbcTemplate.query(strBuffer.toString(),
				new PreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						// TODO Auto-generated method stub
						ps.setString(1, esid);
						if (StringUtils.isNotBlank(startDate)) {
							ps.setDate(2, CommonUtil.getSqlDate(startDate,
									Constants.DATE_FORMAT));
						} else {
							ps.setDate( 2, null);
						}
						
					}
				},
				new GetTrackNoRowMapper());

		if (returnList != null & returnList.size() > 0) {
			return true;
		}

		return false;

	}

}
