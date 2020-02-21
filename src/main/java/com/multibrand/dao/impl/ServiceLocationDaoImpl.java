package com.multibrand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.multibrand.dao.ServiceLocationDao;
import com.multibrand.dao.jdbc.sp.ProcedureTemplate;
import com.multibrand.dao.mapper.EnrollmentDataResponseRowMapper;
import com.multibrand.dto.request.AddServiceLocationRequest;
import com.multibrand.dto.request.UpdateServiceLocationRequest;
import com.multibrand.dto.response.PersonResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.exception.OEException;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.util.LoggerUtil;

@Repository("serviceLocationDAO")
public class ServiceLocationDaoImpl extends AbstractSpringDAO implements
		ServiceLocationDao, Constants {

	@Resource(name = "externalContentDao")
	private ExternalContentDao externalContentDao;

	@Resource(name = "personDAO")
	private PersonDao personDao;

	@Resource(name = "choiceDataSourceProcedureTemplate")
	private ProcedureTemplate procedureTemplate;

	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	@Autowired(required = true)
	public ServiceLocationDaoImpl(
			@Qualifier("choiceJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(ServiceLocationDaoImpl.class);
	}

	public List<Map<String, Object>> getPendingRequestDetails(
			String trackingNumber) throws OEException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPendingRequestDetails : Entered the method : trackingNumber= "
					+ trackingNumber);
		}
		String sqlQuery = sqlMessage.getMessage(
				SQL_GET_PENDING_REQUEST_DETAILS, new Object[] { "'"
						+ trackingNumber + "'" }, null);
		if (logger.isDebugEnabled()) {
			logger.debug("getPendingRequestDetails : sqlQuery for getPendingRequestDetails: "
					+ sqlQuery);
		}
		List<Map<String, Object>> pendingReqDetailsList = getMapDataWithoutParam(sqlQuery);
		return pendingReqDetailsList;
	}

	public List<Map<String, Object>> getPreviousProviderNameFromCode(
			String repCode) throws OEException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPreviousProviderNameFromCode : Entered the method : repCode= "
					+ repCode);
		}
		String sqlQuery = sqlMessage.getMessage(SQL_GET_PREVIOUS_PROVIDER_NAME,
				new Object[] { "'" + repCode + "'" }, null);
		if (logger.isDebugEnabled()) {
			logger.debug("getPreviousProviderNameFromCode : sqlQuery for getPendingRequestDetails: "
					+ sqlQuery);
		}
		List<Map<String, Object>> previousProviderNameList = getMapDataWithoutParam(sqlQuery);
		return previousProviderNameList;
	}

	public String addServiceLocation(AddServiceLocationRequest request) {
		logger.debug("Entering >> addServiceLocation");

		// get the next sequence id for adding details into the table.
		String trackingId = externalContentDao
				.getNextValForSequence(AFFILIATE_TRACKING_NO_SEQ);
		request.setTrackingId(trackingId);

		try {
			logger.debug("BEFORE: request = " + request);

			String serviceReqTypeCd = request.getServiceRequestTypeCode();
			if (!StringUtils.isEmpty(serviceReqTypeCd)) {
				if (serviceReqTypeCd.equalsIgnoreCase(MVI)) {
					serviceReqTypeCd = "N";
				} else {
					serviceReqTypeCd = "S";
				}
			}
			request.setServiceRequestTypeCode(serviceReqTypeCd);
			request.setServAddressLine1(CommonUtil.getAddressLine1(
					request.getServStreetNum(), request.getServStreetName()));
			request.setServAddressLine2(request.getServStreetAptNum());

			String sameBillingServiceAddressFlag = request
					.getAddressBillSameAsServiceFlag();
			if (StringUtils.isNotEmpty(sameBillingServiceAddressFlag)) {
				if ((sameBillingServiceAddressFlag
						.equalsIgnoreCase(Constants.FLAG_YES_PROFILE)
						|| "Y".equalsIgnoreCase(sameBillingServiceAddressFlag) || "X"
							.equalsIgnoreCase(sameBillingServiceAddressFlag))) {
					sameBillingServiceAddressFlag = "Y";
				} else {
					sameBillingServiceAddressFlag = "N";
				}
			}
			request.setAddressBillSameAsServiceFlag(sameBillingServiceAddressFlag);

			if (StringUtils.isNotEmpty(request.getBillStreetName())
					|| StringUtils.isNotEmpty(request.getBillStreetNum())
					|| StringUtils.isNotEmpty(request.getBillStreetAptNum())
					|| StringUtils.isNotEmpty(request.getBillPoBox())
					|| StringUtils.isNotEmpty(request.getBillCity())
					|| StringUtils.isNotEmpty(request.getBillState())
					|| StringUtils.isNotEmpty(request.getBillZipCode())) {

				if (StringUtils.isNotBlank(request.getBillPoBox())) {
					request.setBillAddressLine1(request.getBillPoBox());
				} else {
					request.setBillAddressLine1(CommonUtil.getAddressLine1(request.getBillStreetNum(), request.getBillStreetName()));
					request.setBillAddressLine2(request.getBillStreetAptNum());
				}
			} else {
				request.setBillAddressLine1(null);
				request.setBillAddressLine2(null);
				request.setBillCity(null);
				request.setBillState(null);
				request.setBillZipCode(null);
			}

			if (StringUtils.isNotBlank(request.getErrorCode())
					&& request.getErrorCode().length() > 10) {
				request.setErrorCode(TIBCOSD);
			}

			request.setReferrerCode(StringUtils.EMPTY);
			request.setCompletionStatusCode(StringUtils.EMPTY);

			String promoType = null;
			String promoValue = null;
			String offerCategory = request.getOfferCategory();

			if (offerCategory != null) {
				if (OFFER_CATEGORY_UNITED.equalsIgnoreCase(offerCategory)) {
					promoType = "UA";
					promoValue = request.getFrequentFlyerNumber();
				} else if (OFFER_CATEGORY_AA.equalsIgnoreCase(offerCategory)) {
					promoType = "AA";
					promoValue = request.getFrequentFlyerNumber();
				} else if (OFFER_CATEGORY_SOUTHWEST
						.equalsIgnoreCase(offerCategory)) {
					promoType = "SW";
					promoValue = request.getFrequentFlyerNumber();
				}
			} else if (!StringUtils.isEmpty(request.getRealtorId())) {
				promoType = "MC";
				promoValue = request.getRealtorId();
			}

			request.setPromoType(promoType);
			request.setPromoValue(promoValue);

			// Trim and set Zip code:
			String trimServZipCode = CommonUtil.trimZipCode(request
					.getServZipCode());
			String trimBillZipCode = CommonUtil.trimZipCode(request
					.getBillZipCode());
			request.setServZipCode(trimServZipCode);
			request.setBillZipCode(trimBillZipCode);
			
			
			// Set ESID blank for NESID, NRESID & MESID cases:
			if (NESID.equalsIgnoreCase(request.getEsid())
					|| MESID.equalsIgnoreCase(request.getEsid())
					|| NRESID.equalsIgnoreCase(request.getEsid())) {
				request.setEsid(StringUtils.EMPTY);
			}

			logger.debug("Input procedure request = " + request);

			// Execute the procedure:
			procedureTemplate.execute(request);

			String dbErrorCode = request.getOutErrorCode();
			logger.debug("dbErrorCode = " + dbErrorCode);

			// Error condition:
			if (StringUtils.isNotEmpty(dbErrorCode)) {
				logger.debug("Problem occurred while adding service location. "
						+ "Check logs for more details.");
				request.setTrackingId(StringUtils.EMPTY);
				trackingId = StringUtils.EMPTY;
			}

		} catch (Exception e) {
			logger.error("Problem occurred while adding service location.", e);
			request.setTrackingId(StringUtils.EMPTY);
			trackingId = StringUtils.EMPTY;
		}

		logger.debug("trackingId = " + trackingId);
		logger.debug("Exiting << addServiceLocation");

		return trackingId;
	}

	public String updateServiceLocation(UpdateServiceLocationRequest request) {
		logger.debug("Entering >> updateServiceLocation");
		logger.debug("BEFORE: request = " + request);
		String errorCode = null;

		try {

			String trackingId = request.getTrackingId();

			if (StringUtils.isNotEmpty(trackingId)) {

				// Check mandatory parameter: personId
				String personId = request.getPersonId();
				if (StringUtils.isEmpty(personId)) {
					personId = personDao.getPersonIdByTrackingNo(trackingId);
					request.setPersonId(personId);
				}

				String serviceReqTypeCd = request.getServiceRequestTypeCode();
				if (!StringUtils.isEmpty(serviceReqTypeCd)) {
					if (MVI.equalsIgnoreCase(serviceReqTypeCd)) {
						serviceReqTypeCd = "N";
					} else {
						serviceReqTypeCd = "S";
					}
				}
				request.setServiceRequestTypeCode(serviceReqTypeCd);
				request.setSpanishReqFlag(StringUtils.EMPTY);

				String providerAgreementFlag = request
						.getProviderAgreementFlag();
				if (StringUtils.isNotEmpty(providerAgreementFlag)) {
					if (("Yes".equalsIgnoreCase(providerAgreementFlag)
							|| "Y".equalsIgnoreCase(providerAgreementFlag) || "X"
								.equalsIgnoreCase(providerAgreementFlag))) {
						providerAgreementFlag = "Y";
					} else {
						providerAgreementFlag = "N";
					}
				}
				request.setProviderAgreementFlag(providerAgreementFlag);
				String depositAmount = request.getDepositAmount();
				if (StringUtils.isEmpty(depositAmount)) {
					depositAmount = "0";
				}
				request.setDepositAmount(depositAmount);
				request.setServAddressLine1(CommonUtil.getAddressLine1(
						request.getServStreetNum(), request.getServStreetName()));
				request.setServAddressLine2(request.getServStreetAptNum());

				String sameBillingServiceAddressFlag = request
						.getAddressBillSameAsServiceFlag();
				if (StringUtils.isNotEmpty(sameBillingServiceAddressFlag)) {
					if ((Constants.FLAG_YES_PROFILE
							.equalsIgnoreCase(sameBillingServiceAddressFlag)
							|| "Y".equalsIgnoreCase(sameBillingServiceAddressFlag) || "X"
								.equalsIgnoreCase(sameBillingServiceAddressFlag))) {
						sameBillingServiceAddressFlag = "Y";
					} else {
						sameBillingServiceAddressFlag = "N";
					}
				}
				request.setAddressBillSameAsServiceFlag(sameBillingServiceAddressFlag);
				

				if (StringUtils.isNotEmpty(request.getBillPoBox())) {
					request.setBillAddressLine1 (request.getBillPoBox());
				} else {
					request.setBillAddressLine1(CommonUtil.getAddressLine1(
							request.getBillStreetNum(), request.getBillStreetName()));
				}
				
				request.setBillAddressLine2(request.getBillStreetAptNum());
				
				request.setReferrerCode(StringUtils.EMPTY);

				request.setMailAddressLine1(CommonUtil.getAddressLine1(
						request.getMailStreetNum(), request.getMailStreetName()));
				request.setMailAddressLine2(request.getMailStreetAptNum());

				String esidMatchFlag = request.getEsidMatchFlag();
				if (StringUtils.isNotEmpty(esidMatchFlag)) {
					if("2".equals(esidMatchFlag)){
						esidMatchFlag = "2";
					} else if (("Yes".equalsIgnoreCase(esidMatchFlag)
							|| "Y".equalsIgnoreCase(esidMatchFlag) || "X"
								.equalsIgnoreCase(esidMatchFlag))) {
						esidMatchFlag = "1";
					} else {
						esidMatchFlag = "0";
					}
				}
				logger.info("Esid Match Flag: "+esidMatchFlag);
				request.setEsidMatchFlag(esidMatchFlag);

				if (StringUtils.isNotEmpty(request.getErrorCode())
						&& request.getErrorCode().length() > 10) {
					request.setErrorCode(TIBCOSD);
				}
				request.setCompletionStatusCode(StringUtils.EMPTY);
				request.setAdId(StringUtils.EMPTY);

				String promoType = null;
				String promoValue = null;
				String offerCategory = request.getOfferCategory();
				if (offerCategory != null) {
					if (OFFER_CATEGORY_UNITED.equalsIgnoreCase(offerCategory)) {
						promoType = "UA";
						promoValue = request.getFrequentFlyerNumber();
					} else if (OFFER_CATEGORY_AA
							.equalsIgnoreCase(offerCategory)) {
						promoType = "AA";
						promoValue = request.getFrequentFlyerNumber();
					} else if (OFFER_CATEGORY_SOUTHWEST
							.equalsIgnoreCase(offerCategory)) {
						promoType = "SW";
						promoValue = request.getFrequentFlyerNumber();
					}
				} else if (StringUtils.isNotEmpty(request.getRealtorId())) {
					promoType = "MC";
					promoValue = request.getRealtorId();
				}
				request.setPromoType(promoType);
				request.setPromoValue(promoValue);

				request.setCepProcessedFlag(StringUtils.EMPTY);
				String eSuiteFlag = Boolean.toString(true).equalsIgnoreCase(
						request.getEsuiteFlag()) ? "Y" : "N";
				request.setEsuiteFlag(eSuiteFlag);

				String eSuiteAuthFlag = request.getEsuiteAuthFlag();
				if (StringUtils.isNotEmpty(eSuiteAuthFlag)) {
					if (("Yes".equalsIgnoreCase(eSuiteAuthFlag)
							|| "Y".equalsIgnoreCase(eSuiteAuthFlag) || "X"
								.equalsIgnoreCase(eSuiteAuthFlag))) {
						eSuiteAuthFlag = "Y";
					} else {
						eSuiteAuthFlag = "N";
					}
				}
				request.setEsuiteAuthFlag(eSuiteAuthFlag);

				String eSuiteOAMFlag = request.getEsuiteOamFlag();
				if (StringUtils.isNotEmpty(eSuiteOAMFlag)) {
					if (("Yes".equalsIgnoreCase(eSuiteOAMFlag)
							|| "Y".equalsIgnoreCase(eSuiteOAMFlag) || "X"
								.equalsIgnoreCase(eSuiteOAMFlag))) {
						eSuiteOAMFlag = "Y";
					} else {
						eSuiteOAMFlag = "N";
					}
				}
				request.setEsuiteOamFlag(eSuiteOAMFlag);

				String eSuiteEmailFlag = request.getEsuiteEmailFlag();
				if (StringUtils.isNotEmpty(eSuiteEmailFlag)) {
					if (("Yes".equalsIgnoreCase(eSuiteEmailFlag)
							|| "Y".equalsIgnoreCase(eSuiteEmailFlag) || "X"
								.equalsIgnoreCase(eSuiteEmailFlag))) {
						eSuiteEmailFlag = "Y";
					} else {
						eSuiteEmailFlag = "N";
					}
				}
				request.setEsuiteEmailFlag(eSuiteEmailFlag);

				String eSuiteAutoPayFlag = request.getEsuiteAutoPayFlag();
				if (StringUtils.isNotEmpty(eSuiteAutoPayFlag)) {
					if (("Yes".equalsIgnoreCase(eSuiteAutoPayFlag)
							|| "Y".equalsIgnoreCase(eSuiteAutoPayFlag) || "X"
								.equalsIgnoreCase(eSuiteAutoPayFlag))) {
						eSuiteAutoPayFlag = "Y";
					} else {
						eSuiteAutoPayFlag = "N";
					}
				}
				request.setEsuiteAutoPayFlag(eSuiteAutoPayFlag);

				request.setUserName(StringUtils.EMPTY);
				request.setPassword(StringUtils.EMPTY);
				request.setWeeklySummaryEnroll(StringUtils.EMPTY);

				String priorityMoveInFlag = Boolean.toString(true)
						.equalsIgnoreCase(request.getPriorityMoveinFlag()) ? "Y"
						: "N";
				request.setPriorityMoveinFlag(priorityMoveInFlag);

				String htmlCaptureFlag = Boolean.toString(true)
						.equalsIgnoreCase(request.getHtmlCaptureFlag()) ? "Y"
						: "N";
				request.setHtmlCaptureFlag(htmlCaptureFlag);

				String prePayFlag = StringUtils.EMPTY;
				if (OFFER_CATEGORY_PREPAY.equals(request.getOfferCategory())) {
					prePayFlag = "Y";
				} else {
					prePayFlag = "N";
				}
				request.setPrepayFlag(prePayFlag);

				request.setEcoShare(StringUtils.EMPTY);

				String activeCustomerFlag = request.getActiveCustomerFlag();
				if (StringUtils.isNotEmpty(activeCustomerFlag)) {
					if (("Yes".equalsIgnoreCase(activeCustomerFlag)
							|| "Y".equalsIgnoreCase(activeCustomerFlag) || "X"
								.equalsIgnoreCase(activeCustomerFlag))) {
						activeCustomerFlag = "X";
					} else {
						activeCustomerFlag = "O";
					}
				}
				request.setActiveCustomerFlag(activeCustomerFlag);

				String addressMatchFlag = request.getAddressMatchFlag();
				if (StringUtils.isNotEmpty(addressMatchFlag)) {
					if (("Yes".equalsIgnoreCase(addressMatchFlag)
							|| "Y".equalsIgnoreCase(addressMatchFlag) || "X"
								.equalsIgnoreCase(addressMatchFlag))) {
						addressMatchFlag = "X";
					} else {
						addressMatchFlag = "O";
					}
				}
				request.setAddressMatchFlag(addressMatchFlag);

				String pendingBalanceFlag = request.getPendingBalanceFlag();
				if (StringUtils.isNotEmpty(pendingBalanceFlag)) {
					if (("Yes".equalsIgnoreCase(pendingBalanceFlag)
							|| "Y".equalsIgnoreCase(pendingBalanceFlag) || "X"
								.equalsIgnoreCase(pendingBalanceFlag))) {
						pendingBalanceFlag = "X";
					} else {
						pendingBalanceFlag = "O";
					}
				}
				request.setPendingBalanceFlag(pendingBalanceFlag);

				String addressSearchPerformed = request
						.getAddressSearchPerformed();
				if (StringUtils.isNotEmpty(addressSearchPerformed)) {
					if (("Yes".equalsIgnoreCase(addressSearchPerformed)
							|| "Y".equalsIgnoreCase(addressSearchPerformed) || "X"
								.equalsIgnoreCase(addressSearchPerformed))) {
						addressSearchPerformed = "X";
					} else {
						addressSearchPerformed = "O";
					}
				}
				request.setAddressSearchPerformed(addressSearchPerformed);

				String bpMatchNoCCSResponse = request.getBpMatchNoCcsResponse();
				if (StringUtils.isNotEmpty(bpMatchNoCCSResponse)) {
					if (("Yes".equalsIgnoreCase(bpMatchNoCCSResponse)
							|| "Y".equalsIgnoreCase(bpMatchNoCCSResponse) || "X"
								.equalsIgnoreCase(bpMatchNoCCSResponse))) {
						bpMatchNoCCSResponse = "X";
					} else {
						bpMatchNoCCSResponse = "O";
					}
				}
				request.setBpMatchNoCcsResponse(bpMatchNoCCSResponse);

				String payUpFront = request.getIsPayUpFront();
				if (StringUtils.isNotEmpty(payUpFront)) {
					if (("Yes".equalsIgnoreCase(payUpFront)
							|| "Y".equalsIgnoreCase(payUpFront) || "X"
								.equalsIgnoreCase(payUpFront))) {
						payUpFront = "X";
					} else {
						payUpFront = "O";
					}
				}
				request.setIsPayUpFront(payUpFront);

				// Trim and set Zip code:
				String trimServZipCode = CommonUtil.trimZipCode(request
						.getServZipCode());
				String trimBillZipCode = CommonUtil.trimZipCode(request
						.getBillZipCode());
				request.setServZipCode(trimServZipCode);
				request.setBillZipCode(trimBillZipCode);
				
				// Set ESID blank for NESID, NRESID & MESID cases:
				if (NESID.equalsIgnoreCase(request.getEsid())
						|| MESID.equalsIgnoreCase(request.getEsid())
						|| NRESID.equalsIgnoreCase(request.getEsid())) {
					request.setEsid(StringUtils.EMPTY);
				}

				logger.debug("Input procedure request = " + request);

				// Execute the procedure:
				procedureTemplate.execute(request);
				errorCode = request.getOutErrorCode();

				logger.debug("errorCode = " + errorCode);

				// Error condition:
				if (StringUtils.isNotEmpty(errorCode)) {
					logger.debug("Problem occurred while updating service location. "
							+ "Check logs for more details.");
				}

				logger.debug("AFTER: request = " + request);

			} else {
				logger.debug("Tracking number for updating service location is null "
						+ "hence, update service location call failed.");
				errorCode = FAILED;
			}

		} catch (Exception e) {
			logger.error("Problem occurred while updating service location.", e);
			errorCode = FAILED;
		}

		logger.debug("errorCode = " + errorCode);
		logger.debug("Exiting << updateServiceLocation");

		return errorCode;
	}

	public ServiceLocationResponse getServiceLocation(String trackingId) {
		logger.info("Entering >> getServiceLocation");
		logger.info("trackingId = " + trackingId);
		ServiceLocationResponse data = null;
		if (StringUtils.isNotEmpty(trackingId)) {
			try {
				String sqlQuery = sqlMessage
						.getMessage(
								QUERY_GET_SERVICE_LOCATION_AFFILIATE_DETAILS_BY_TRACKING_ID,
								null, null);
				List<ServiceLocationResponse> dataList = getJdbcTemplate()
						.query(sqlQuery,
								new Object[] { Long.valueOf(trackingId) },
								new RowMapper<ServiceLocationResponse>() {
									@Override
									public ServiceLocationResponse mapRow(
											ResultSet rs, int rowNo)
											throws SQLException {
										ServiceLocationResponse dataRow = new ServiceLocationResponse();
										dataRow.setTrackingId(rs
												.getString("tracking_number"));
										dataRow.setPersonId(rs
												.getString("person_id"));
										dataRow.setServiceRequestTypeCode(rs
												.getString("srvc_req_type_cd"));
										dataRow.setPreviousProviderCode(rs
												.getString("rep_cd"));
										dataRow.setDepositCode(rs
												.getString("deposit_cd"));
										dataRow.setRequestStatusCode(rs
												.getString("req_status_cd"));
										dataRow.setOfferCode(rs
												.getString("offer_cd"));
										dataRow.setSpanishReqFlag(rs
												.getString("spanish_req_flag"));
										dataRow.setProviderAgreementFlag(rs
												.getString("provider_agreement_flag"));
										dataRow.setDepositAmount(rs
												.getString("deposit_amount"));
										dataRow.setEsid(rs.getString("esid"));
										dataRow.setContractAccountNum(rs
												.getString("contract_account_num"));
										dataRow.setServAddressLine1(rs
												.getString("srvc_address_line_1"));
										dataRow.setServAddressLine2(rs
												.getString("srvc_address_line_2"));
										dataRow.setServCity(rs
												.getString("srvc_city"));
										dataRow.setServState(rs
												.getString("srvc_state"));
										dataRow.setServZipCode(rs
												.getString("srvc_zip"));
										dataRow.setServiceZipOverrideFlag(rs
												.getString("srvc_zip_override_flag"));
										dataRow.setAddressBillSameAsServiceFlag(rs
												.getString("address_bill_sameas_srvc_flag"));
										dataRow.setServiceAddressOverrideFlag(rs
												.getString("srvc_address_override_flag"));
										dataRow.setBillAddressLine1(rs
												.getString("bill_address_line_1"));
										dataRow.setBillAddressLine2(rs
												.getString("bill_address_line_2"));
										dataRow.setBillCity(rs
												.getString("bill_city"));
										dataRow.setBillState(rs
												.getString("bill_state"));
										dataRow.setBillZipCode(rs
												.getString("bill_zip"));
										dataRow.setServiceStartDate(DateUtil.getFormattedDate(
												MMddyyyy,
												DT_SQL_FMT_DB,
												rs.getString("service_start_date")));
										dataRow.setGuid(rs.getString("guid_id"));
										dataRow.setSignupChannelCode(rs
												.getString("signup_channel_cd"));
										dataRow.setReferrerCode(rs
												.getString("referrer_cd"));
										dataRow.setAccountName(rs
												.getString("acct_name"));
										dataRow.setMailAddressLine1(rs
												.getString("mail_address_line_1"));
										dataRow.setMailAddressLine2(rs
												.getString("mail_address_line_2"));
										dataRow.setMailCity(rs
												.getString("mail_address_city"));
										dataRow.setMailState(rs
												.getString("mail_address_state"));
										dataRow.setMailZipCode(rs
												.getString("mail_address_zip"));
										dataRow.setEsidMatchFlag(rs
												.getString("esid_match_flag"));
										dataRow.setGeoZone(rs
												.getString("geo_zone"));
										dataRow.setPromoCodeEntered(rs
												.getString("offer_cell_trk_cd"));
										dataRow.setCompletionStatusCode(rs
												.getString("completion_status_cd"));
										dataRow.setAdId(rs.getString("ad_id"));
										dataRow.setPayCode(rs
												.getString("deposit_required_cd"));
										dataRow.setTdspCode(rs
												.getString("tdsp_cd"));
										dataRow.setOfferCodeTitle(rs
												.getString("offer_teaser"));
										dataRow.setCaCheckDigit(rs
												.getString("ca_check_digit"));
										dataRow.setOfferCellTrackCodeSelected(rs
												.getString("offer_cell_trk_cd_selected"));
										dataRow.setBillingAddressOverrideFlag(rs
												.getString("blng_address_override_flag"));
										dataRow.setErrorCode(rs
												.getString("error_cd"));
										dataRow.setPromoType(rs
												.getString("promo_type"));
										dataRow.setPromoValue(rs
												.getString("promo_value"));
										dataRow.setDwellingType(rs
												.getString("dwelling_type"));
										dataRow.setCepProcessedFlag(rs
												.getString("cep_processed_flag"));
										dataRow.setRecentPageAccessed(rs
												.getString("recent_page_accessed"));
										dataRow.setEsuiteFlag(rs
												.getString("esuite_flag"));
										dataRow.setEsuiteStatus(rs
												.getString("esuite_status"));
										dataRow.setEsuiteAuthFlag(rs
												.getString("esuite_auth_flag"));
										dataRow.setEsuiteOamFlag(rs
												.getString("esuite_oam_flag"));
										dataRow.setEsuiteEmailFlag(rs
												.getString("esuite_email_flag"));
										dataRow.setEsuiteAutoPayFlag(rs
												.getString("esuite_auto_pay_flag"));
										dataRow.setEsuiteOamDate(rs
												.getString("esuite_oam_date"));
										dataRow.setEsuiteEmailDate(DateUtil.getFormattedDate(
												MMddyyyy,
												DT_SQL_FMT_DB,
												rs.getString("esuite_email_date")));
										dataRow.setEsuiteAutoPayDate(DateUtil.getFormattedDate(
												MMddyyyy,
												DT_SQL_FMT_DB,
												rs.getString("esuite_auto_pay_date")));
										dataRow.setEsuiteAutoPayOption(rs
												.getString("esuite_auto_pay_option"));
										dataRow.setUserName(rs
												.getString("user_name"));
										dataRow.setPassword(rs
												.getString("password"));
										dataRow.setWeeklySummaryEnroll(rs
												.getString("wse_flag"));
										dataRow.setPermitType(rs
												.getString("permit_type"));
										dataRow.setPermitClass(rs
												.getString("permit_class"));
										dataRow.setPermitDetail(rs
												.getString("permit_detail"));
										dataRow.setCityCountry(rs
												.getString("city_county"));
										dataRow.setCityCountryName(rs
												.getString("city_county_name"));
										dataRow.setPermitPhone(rs
												.getString("permit_phone"));
										dataRow.setPriorityMoveinFlag(rs
												.getString("priority_movein_flag"));
										dataRow.setHtmlCaptureFlag(rs
												.getString("html_capture_flag"));
										dataRow.setNonCommodityProduct(rs
												.getString("value_product_code"));
										dataRow.setPrepayFlag(rs
												.getString("prepay_flag"));
										dataRow.setPrepayTotalToday(rs
												.getString("prepay_total_today"));
										dataRow.setPrepayDocId(rs
												.getString("prepay_doc_id"));
										dataRow.setEcoShare(rs
												.getString("eco_share"));
										dataRow.setActiveCustomerFlag(rs
												.getString("active_customer_flag"));
										dataRow.setAddressMatchFlag(rs
												.getString("address_match_flag"));
										dataRow.setPendingBalanceFlag(rs
												.getString("pending_bal_flag"));
										dataRow.setBpActiveContract(rs
												.getString("bp_match_active_contract"));
										dataRow.setMatchedPartnerId(rs
												.getString("matched_partner_id"));
										dataRow.setAddressSearchPerformed(rs
												.getString("add_search_performed"));
										dataRow.setBpMatchNoCcsResponse(rs
												.getString("bpmatch_no_ccs_resp"));
										dataRow.setProductType(rs
												.getString("offer_type"));
										dataRow.setProductSkuCode(rs
												.getString("product_sku_code"));
										dataRow.setPlanName(rs
												.getString("offer_name"));
										dataRow.setEnrollSource(rs
												.getString("enroll_source"));
										dataRow.setActivationFee(rs
												.getString("activation_fee"));
										dataRow.setBondPrice(rs
												.getString("bond_price"));
										dataRow.setAccSecStatus(rs
												.getString("acc_sec_status"));
										dataRow.setIsPayUpFront(rs
												.getString("is_pay_upfront"));
										dataRow.setSecurityMethod(rs
												.getString("security_method"));
										dataRow.setActivationFeeCode(rs
												.getString("activation_fee_cd"));
										dataRow.setMeterType(rs
												.getString("meter_type"));
										dataRow.setSwitchHoldStatus(rs
												.getString("sw_hold_status"));
										dataRow.setRecentDisconnectFlag(rs
												.getString("rec_disconnect_flag"));
										dataRow.setPremiseType(rs
												.getString("premise_type"));
										dataRow.setEsidStatus(rs
												.getString("esid_status"));
										dataRow.setVerifyAdditionalOptions(rs
												.getString("verify_lightbox_options"));
										dataRow.setAmbProgramCode(rs
												.getString("average_billing_request"));
										dataRow.setPaperlessProgramCode(rs
												.getString("paperless_billing_request"));
										dataRow.setRhsProgramCodeOne(rs
												.getString("rhs_offer_name_one"));
										dataRow.setRhsProgramCodeTwo(rs
												.getString("rhs_offer_name_two"));
										dataRow.setRhsOfferType(rs
												.getString("rhs_offer_type"));
										dataRow.setFreqFlyerFirstName(rs
												.getString("freq_flyer_first_name"));
										dataRow.setFreqFlyerLastName(rs
												.getString("freq_flyer_last_name"));
										dataRow.setReferralId(rs
												.getString("referral_id"));
										dataRow.setRecentCallMade(rs
												.getString("recent_call_made"));
										dataRow.setNestSid(rs
												.getString("nest_sid"));
										dataRow.setGzProductId(rs
												.getString("gz_product_id"));
										dataRow.setAffiliateId(rs
												.getString("affiliate_id"));
										dataRow.setBrandId(rs
												.getString("brand_name"));
										dataRow.setCompanyCode(rs
												.getString("company_code"));
										dataRow.setSunClub(rs
												.getString("sun_club"));
										dataRow.setDriverClub(rs
												.getString("driver_club"));
										dataRow.setGiftCard(rs
												.getString("gift_card"));
										dataRow.setResidentialSolar(rs
												.getString("residential_solar"));
										dataRow.setMessageCode(rs
												.getString("recent_msg_cd"));
										dataRow.setAgentID(rs
												.getString("agent_id"));
										dataRow.setAgentType(rs
												.getString("agent_type"));
										dataRow.setAgentFirstName(rs
												.getString("agent_first_name"));
										dataRow.setAgentLastName(rs
												.getString("agent_last_name"));
										dataRow.setVendorCode(rs
												.getString("vendor_code"));
										dataRow.setVendorName(rs
												.getString("vendor_name"));
										dataRow.setTlpReportApiStatus(rs
												.getString("tlp_report_api_status"));
										
										return dataRow;
									}
								});
				if (dataList != null && dataList.size() > 0) {
					data = dataList.get(0);
					PersonResponse personResponse = personDao.getPerson(data
							.getPersonId());
					data.setPersonResponse(personResponse);
				}
			} catch (Exception e) {
				logger.error("Problem occurred while getting a "
						+ "Service Location details with Tracking ID as: "
						+ trackingId, e);
				data = null;
			}
		}
		logger.info("data = " + data);
		logger.info("Exiting << getServiceLocation");
		return data;
	}
	
	public ServiceLocationResponse getEnrollmentData(String trackingId,String guid) {
		logger.info("Entering >> getServiceLocation");
		logger.info("trackingId = " + trackingId);
		ServiceLocationResponse data = null;
		if (StringUtils.isNotEmpty(trackingId) && StringUtils.isNotEmpty(guid)) {
			try {
				String sqlQuery = sqlMessage
						.getMessage(
								QUERY_GET_ENROLLMENT_DETAILS_BY_TRACKING_ID_GUID,
								null, null);
				List<ServiceLocationResponse> dataList = getJdbcTemplate()
						.query(sqlQuery,new Object[] { Long.valueOf(trackingId), guid },new EnrollmentDataResponseRowMapper() );
				if (dataList != null && dataList.size() > 0) {
					data = dataList.get(0);
					
				}
			} catch (Exception e) {
				logger.error("Problem occurred while getting a "
						+ "Service Location details with Tracking ID as: "
						+ trackingId, e);
				data = null;
			}
		}
		logger.info("data = " + data);
		logger.info("Exiting << getServiceLocation");
		return data;
	}

}
