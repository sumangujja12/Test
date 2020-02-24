package com.multibrand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.ExternalContentDao;
import com.multibrand.dao.PersonDao;
import com.multibrand.dao.jdbc.sp.ProcedureTemplate;
import com.multibrand.dto.request.AddPersonRequest;
import com.multibrand.dto.request.UpdatePersonRequest;
import com.multibrand.dto.response.PersonResponse;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;

/**
 * Implementation of {@link PersonDao}.
 * 
 * @author Pankaj Shelare
 * @version Since JDK 1.6, Spring 3.0 and Jersey 1.17
 */
@Repository("personDAO")
public class PersonDaoImpl extends AbstractSpringDAO implements PersonDao,
		Constants {

	@Resource(name = "externalContentDao")
	private ExternalContentDao externalContentDao;

	@Resource(name = "choiceDataSourceProcedureTemplate")
	private ProcedureTemplate procedureTemplate;

	@Autowired(required = true)
	public PersonDaoImpl(
			@Qualifier("choiceJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(PersonDaoImpl.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getPersonIdByTrackingNo(String trackingNo) {
		String personId = null;
		if (StringUtils.isNotEmpty(trackingNo)) {
			try {
				String sqlQuery = sqlMessage.getMessage(
						QUERY_GET_PERSON_AFFILIATE_ID_BY_TRACKING_NO, null,
						null);
				long personIdLong = getJdbcTemplate().queryForLong(sqlQuery,
						new Object[] { Long.valueOf(trackingNo) });
				personId = Long.toString(personIdLong);
			} catch (Exception e) {
				logger.error("Problem occurred while getting a "
						+ "Person ID with Tracking ID as: " + trackingNo, e);
				personId = null;
			}
		}
		return personId;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map<String, String>> getPersonIdAndRetryCountByTrackingNo(
			String trackingNo) {
		List<Map<String, String>> dataList = null;
		if (StringUtils.isNotEmpty(trackingNo)) {
			try {
				String sqlQuery = sqlMessage
						.getMessage(
								QUERY_GET_PERSON_AFFILIATE_ID_AND_RETRY_COUNT_BY_TRACKING_NO,
								null, null);
				dataList = getJdbcTemplate().query(sqlQuery,
						new Object[] { Long.valueOf(trackingNo) },
						new RowMapper<Map<String, String>>() {
							@Override
							public Map<String, String> mapRow(ResultSet rs,
									int rowNo) throws SQLException {
								Map<String, String> row = new HashMap<String, String>();
								row.put(Constants.PERSON_AFFILIATE_PERSON_ID,
										rs.getString(Constants.PERSON_AFFILIATE_PERSON_ID));
								row.put(Constants.PERSON_AFFILIATE_RETRY_COUNT,
										rs.getString(Constants.PERSON_AFFILIATE_RETRY_COUNT));
								return row;
							}
						});
			} catch (Exception e) {
				logger.error("Problem occurred while getting a "
						+ "Person ID & Retry Count with Tracking ID as: "
						+ trackingNo, e);
				dataList = null;
			}
		}
		return dataList;
	}

	/**
	 * {@inheritDoc}
	 */
	public String addPerson(AddPersonRequest request) {

		logger.debug("Entering >> addPerson");

		// get the next sequence id for adding details into the table.
		String personId = externalContentDao
				.getNextValForSequence(AFFILIATE_PERSON_SEQ);

		try {
			request.setPersonId(personId);
			request.setDrlFour(StringUtils.EMPTY);
			request.setSsnFour(StringUtils.EMPTY);

			logger.debug("request = " + request);

			// Execute the procedure:
			procedureTemplate.execute(request);

			String errorCode = request.getErrorCode();
			logger.debug("errorCode = " + errorCode);

			// Error condition:
			if (StringUtils.isNotEmpty(errorCode)) {
				logger.debug("Problem occurred while adding person. "
						+ "Check logs for more details.");
				request.setPersonId(StringUtils.EMPTY);
				personId = StringUtils.EMPTY;
			}

		} catch (Exception e) {
			logger.error("Problem occurred while adding person.", e);
			request.setPersonId(StringUtils.EMPTY);
			personId = StringUtils.EMPTY;
		}

		logger.debug("personId = " + personId);
		logger.debug("Exiting << addPerson");

		return personId;
	}

	/**
	 * {@inheritDoc}
	 */
	public String updatePerson(UpdatePersonRequest request) {
		logger.debug("Entering >> updatePerson");
		logger.debug("request = " + request);
		String errorCode = null;
		if (request != null) {
			try {

				// Check mandatory parameter: personId
				String personId = request.getPersonId();
				String trackingNo = request.getTrackingId();
				if (StringUtils.isEmpty(personId)) {
					if (StringUtils.isNotEmpty(trackingNo)) {
						personId = getPersonIdByTrackingNo(trackingNo);
					} else {
						errorCode = FAILED;
					}
				}

				if (StringUtils.isNotEmpty(personId)) {
					processPersonBankCreditDetails(request);
					request.setContactTimeCode(StringUtils.EMPTY);
					request.setAdditionalNameFirst(StringUtils.EMPTY);
					request.setAdditionalNameLast(StringUtils.EMPTY);
					request.setKeepMeInformedFlag(StringUtils.EMPTY);
					request.setContactByEmailFlag(StringUtils.EMPTY);
					request.setContactByPhoneFlag(StringUtils.EMPTY);
					request.setPersonStatus(StringUtils.EMPTY);
					request.setTitleName(StringUtils.EMPTY);
					request.setFaxNumber(StringUtils.EMPTY);
					request.setEnrollmentNumber(StringUtils.EMPTY);
					request.setDrlFour(StringUtils.EMPTY);
					request.setSsnFour(StringUtils.EMPTY);

					logger.debug("Input procedure request = " + request);
					
					// Execute the procedure:
					procedureTemplate.execute(request);

					String dbErrorCode = request.getErrorCode();
					logger.debug("After procedure execution, dbErrorCode = "
							+ dbErrorCode);

					// Error condition:
					if (StringUtils.isNotEmpty(dbErrorCode)) {
						logger.debug("Problem occurred while updating person. "
								+ "Check logs for more details.");
						errorCode = FAILED;
					}
				} else {
					logger.debug("Person ID is null hence, Update person details failed.");
					errorCode = FAILED;
				}

			} catch (Exception e) {
				logger.error("Problem occurred while adding person.", e);
				errorCode = FAILED;
			}
		} else {
			logger.debug("UpdatePersonRequest is null hence, Update person details failed.");
			errorCode = FAILED;
		}

		if (StringUtils.isNotEmpty(errorCode)) {
			logger.error("Failed person update in DB with personId = "
					+ request.getPersonId());
		} else if (StringUtils.isEmpty(errorCode)) {
			logger.error("Successfully updated persion in DB with personId = "
					+ request.getPersonId());
		}

		logger.debug("errorCode = " + errorCode);
		logger.debug("Exiting << updatePerson");
		return errorCode;
	}

	/**
	 * {@inheritDoc}
	 */
	public PersonResponse getPerson(String personId) {
		logger.debug("Entering >> getPerson");
		logger.debug("personId = " + personId);
		PersonResponse data = null;
		if (StringUtils.isNotEmpty(personId)) {
			try {
				String sqlQuery = sqlMessage.getMessage(
						QUERY_GET_PERSON_AFFILIATE_DETAILS_BY_PERSON_ID, null,
						null);
				List<PersonResponse> dataList = getJdbcTemplate().query(
						sqlQuery, new Object[] { Long.valueOf(personId) },
						new RowMapper<PersonResponse>() {
							@Override
							public PersonResponse mapRow(ResultSet rs, int rowNo)
									throws SQLException {
								PersonResponse dataRow = new PersonResponse();
								dataRow.setPersonId(rs.getString("person_id"));
								dataRow.setFirstName(rs.getString("name_first"));
								dataRow.setLastName(rs.getString("name_last"));
								dataRow.setDob(DateUtil.getFormattedDate(
										MMddyyyy, DT_SQL_FMT_DB,
										rs.getString("date_of_birth")));
								dataRow.setSsn(rs.getString("ssn"));
								dataRow.setIdType(rs.getString("id_cd"));
								dataRow.setIdNumber(rs.getString("id_number"));
								dataRow.setIdStateOfIssue(rs
										.getString("id_state_of_issue"));
								dataRow.setContactTimeCode(rs
										.getString("contact_time_cd"));
								dataRow.setPhoneNum(rs
										.getString("phone_number"));
								dataRow.setEmail(rs.getString("address_email"));
								dataRow.setAdditionalNameFirst(rs
										.getString("additional_name_first"));
								dataRow.setAdditionalNameLast(rs
										.getString("additional_name_last"));
								dataRow.setKeepMeInformedFlag(rs
										.getString("keep_me_informed_flag"));
								dataRow.setContactByEmailFlag(rs
										.getString("contact_by_email_flag"));
								dataRow.setContactByPhoneFlag(rs
										.getString("contact_by_phone_flag"));
								dataRow.setPersonStatus(rs
										.getString("person_status"));
								dataRow.setTitleName(rs.getString("name_title"));
								dataRow.setLanguageCode(rs
										.getString("language_cd"));
								dataRow.setFaxNumber(rs.getString("fax_number"));
								dataRow.setCredLevelNum(rs
										.getString("cred_level_num"));
								dataRow.setCredScoreNum(rs
										.getString("cred_score_num"));
								dataRow.setCredSourceNum(rs
										.getString("cred_source_num"));
								dataRow.setMiddleName(rs
										.getString("name_middle"));
								dataRow.setBusinessPartnerId(rs
										.getString("business_partner_id"));
								dataRow.setEnrollmentNumber(rs
										.getString("enrollment_number"));
								dataRow.setCredStatusCode(rs
										.getString("cred_status_cd"));
								dataRow.setCredStatusDate(DateUtil.getFormattedDate(
										MMddyyyy, DT_SQL_FMT_DB,
										rs.getString("cred_status_date")));
								dataRow.setIdocNumber(rs
										.getString("idoc_number"));
								dataRow.setEmailOptionRps(rs
										.getString("email_option_rps"));
								dataRow.setEmailOptionSo(rs
										.getString("email_option_so"));
								dataRow.setEmailOptionEe(rs
										.getString("email_option_ee"));
								dataRow.setRoutingNumber(rs
										.getString("routing_number"));
								dataRow.setBankInstitutionName(rs
										.getString("banking_institution_name"));
								dataRow.setCcType(rs.getString("cc_type"));
								dataRow.setCcExpiryMonth(rs
										.getString("cc_expiry_month"));
								dataRow.setCcExpiryYear(rs
										.getString("cc_expiry_year"));
								dataRow.setCcBillzip(rs.getString("cc_billzip"));
								dataRow.setAccountNumber(rs
										.getString("account_number"));
								dataRow.setAccountName(rs
										.getString("account_name"));
								dataRow.setBankAccountLastThree(rs
										.getString("bf22"));
								dataRow.setDrlFour(rs.getString("sf31"));
								dataRow.setSsnFour(rs.getString("sf11"));
								dataRow.setMaidenName(rs
										.getString("name_maiden"));
								dataRow.setPosIdStatus(rs
										.getString("pos_id_flag"));
								dataRow.setPosIdDate(DateUtil.getFormattedDate(
										MMddyyyy, DT_SQL_FMT_DB,
										rs.getString("pos_id_date")));
								dataRow.setAdvActionData(rs
										.getString("adv_action_data"));
								dataRow.setRetryCount(rs
										.getString("retry_count"));
								dataRow.setNoid(rs
										.getString("noid"));
								return dataRow;
							}
						});
				if (dataList != null && dataList.size() > 0) {
					data = dataList.get(0);
				}
			} catch (Exception e) {
				logger.error("Problem occurred while getting a "
						+ "Person details with Person ID as: " + personId, e);
				data = null;
			}
		}
		logger.debug("data = " + data);
		logger.debug("Exiting << getPerson");
		return data;
	}

	private void processPersonBankCreditDetails(UpdatePersonRequest request) {
		if (request != null) {
			if (Boolean.toString(true)
					.equalsIgnoreCase(request.getEsuiteFlag())
					|| Constants.OFFER_CATEGORY_PREPAY.equals(request
							.getOfferCategory())) {
				if (Constants.BNK.equals(request.getPaymentMethod())) {
					request.setCcType(Constants.NA);
					request.setCcExpiryMonth(Constants.NA);
					request.setCcExpiryYear(Constants.NA);
					request.setCcBillzip(Constants.NA);
				} else if (Constants.PAYMENT_MODE_CC.equals(request
						.getPaymentMethod())) {
					request.setRoutingNumber(Constants.NA);
					request.setBankInstitutionName(Constants.NA);
					request.setBankAccountLastThree(Constants.NA);
				}
			} else {
				request.setCcType(StringUtils.EMPTY);
				request.setCcExpiryMonth(StringUtils.EMPTY);
				request.setCcExpiryYear(StringUtils.EMPTY);
				request.setCcBillzip(StringUtils.EMPTY);
				request.setRoutingNumber(StringUtils.EMPTY);
				request.setBankInstitutionName(StringUtils.EMPTY);
				request.setAccountName(StringUtils.EMPTY);
				request.setAccountNumber(StringUtils.EMPTY);
				request.setBankAccountLastThree(StringUtils.EMPTY);
			}
		}
	}
	
}
