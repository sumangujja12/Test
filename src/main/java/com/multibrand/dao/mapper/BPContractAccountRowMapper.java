package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.BPBalanceDO;
import com.multibrand.vo.response.BPContractAccountDO;
import com.multibrand.vo.response.BPContractDO;
import com.multibrand.vo.response.BPPaymentDO;
import com.multibrand.vo.response.BPPhoneDO;
import com.multibrand.vo.response.BussinessPartnerDO;
import com.multibrand.vo.response.billingResponse.AddressDO;
import com.multibrand.vo.response.billingResponse.OfferDO;
import com.multibrand.vo.response.historyResponse.PaymentDO;

public class BPContractAccountRowMapper implements ResultSetExtractor<List<BussinessPartnerDO>>, Constants
{

	@Override
	public List<BussinessPartnerDO> extractData(ResultSet rs) throws SQLException, DataAccessException
	{

		BussinessPartnerDO bpBussinessPartnerDO = null;
		String bpNo = "";
		String caNo = "";
		String bpPhNO = "";
		String strRemark ="";
		List<BPPhoneDO> bpPhoneList = null;
		List<BPContractAccountDO> bpContractAccountDOList = null;
		List<BPContractDO> bpContractDOList = null;
		List<PaymentDO> bpPaymentDOList = null;
		List<BPBalanceDO> bpBalanceDOList = null;
		List<BussinessPartnerDO> bpBussinessPartnerDOList = new LinkedList<BussinessPartnerDO>();

		while (rs.next()) {
			String tempBpNo = rs.getString("BP_NUMBER");

			if (tempBpNo != null && !tempBpNo.equalsIgnoreCase(bpNo)) {
				bpNo = rs.getString("BP_NUMBER");
				bpBussinessPartnerDO = new BussinessPartnerDO();
				bpBussinessPartnerDO.setBpFirstName(CommonUtil.getBlankString(rs.getString("BP_FIRST_NAME")));
				bpBussinessPartnerDO.setBpLastName(CommonUtil.getBlankString(rs.getString("BP_LAST_NAME")));
				bpBussinessPartnerDO.setBpMiddleName(CommonUtil.getBlankString(rs.getString("BP_MIDDLE_NAME")));
				bpBussinessPartnerDO.setBpNumber(CommonUtil.getBlankString(rs.getString("BP_NUMBER")));
				bpBussinessPartnerDO.setBpOrgName(CommonUtil.getBlankString(rs.getString("BP_ORG_NAME")));
				bpBussinessPartnerDO.setBpPreferedLang(CommonUtil.getBlankString(rs.getString("BP_PREFERRED_LANG")));
				bpBussinessPartnerDO.setBpType(CommonUtil.getBlankString(rs.getString("BP_TYPE")));
				if(rs.getString("BRAND_NAME")!=null && rs.getString("BRAND_NAME").equals("CIRRO"))
					bpBussinessPartnerDO.setBrandName(CIRRO_BRAND_NAME);
				else
					bpBussinessPartnerDO.setBrandName(CommonUtil.getBlankString(rs.getString("BRAND_NAME")));
				bpBussinessPartnerDO.setCompanyCode(CommonUtil.getBlankString(rs.getString("COMPANY_CODE")));
				bpBussinessPartnerDO.setEmailBounceFlag(CommonUtil.getBlankString(rs.getString("EMAIL_BOUNCE_FLAG")));
				bpBussinessPartnerDO.setEmailId(CommonUtil.getBlankString(rs.getString("EMAIL_ID")));
				bpBussinessPartnerDO.setMarketPreference(CommonUtil.getBlankString(rs.getString("MARKET_PREFERENCE")));
				bpPhoneList = new LinkedList<BPPhoneDO>();
				bpBussinessPartnerDO.setPhoneDO(bpPhoneList);
				bpContractAccountDOList = new LinkedList<BPContractAccountDO>();
				bpBussinessPartnerDO.setBpContractAccount(bpContractAccountDOList);
				bpBussinessPartnerDOList.add(bpBussinessPartnerDO);

			}

			bpPhNO = rs.getString("PHONE_NUMBER");
			strRemark = rs.getString("REMARK");

			if ((bpPhNO != null && !bpPhNO.equals("")) || (strRemark != null && !strRemark.equals(""))) {
				BPPhoneDO bpPhoneDO = new BPPhoneDO();
				bpPhoneDO.setPhoneBounceFlag(CommonUtil.getBlankString(rs.getString("PHONE_BOUNCE_FLAG")));
				bpPhoneDO.setPhoneNumber(CommonUtil.getBlankString(rs.getString("PHONE_NUMBER")));
				bpPhoneDO.setRemark(CommonUtil.getBlankString(rs.getString("REMARK")));
				bpPhoneDO.setSmsActiveFlag(CommonUtil.getBlankString(rs.getString("SMS_ACTIVE_FLAG")));
				bpPhoneList.add(bpPhoneDO);
				continue;
			}
			
			if (rs.getString("CA_NUMBER") != null && rs.getString("CA_NUMBER").equalsIgnoreCase("0")) {
				continue;
			}

			if ((rs.getString("CA_NUMBER") != null && !rs.getString("CA_NUMBER").equalsIgnoreCase(""))
					&& !caNo.equalsIgnoreCase(rs.getString("CA_NUMBER"))) {
				caNo = rs.getString("CA_NUMBER");
				BPContractAccountDO bpContractAccountDO = new BPContractAccountDO();
				// Start for Cirro Collective Billing changes
				bpContractAccountDO.setCollectiveAccountFlag(CommonUtil.getBlankString(rs.getString("CCA_FLAG")));
				bpContractAccountDO.setCollectiveContractAccount(CommonUtil.getBlankString(rs.getString("CCA_CONTRACT_ACCOUNT")));
				// End for Cirro Collective Billing changes
				bpContractAccountDO.setStrCheckDigit(CommonUtil.getBlankString(rs.getString("CHECK_DIGIT")));
				bpContractAccountDO.setStrCANumber(caNo);
				bpContractAccountDO.setCAName(CommonUtil.getBlankString(rs.getString("CA_NAME")));
				bpContractAccountDO.setStrExFirstName(CommonUtil.getBlankString(rs.getString("CA_FIRST_NAME")));
				bpContractAccountDO.setStrExLastName(CommonUtil.getBlankString(rs.getString("CA_LAST_NAME")));
				bpContractAccountDO.setStrLanguageCode(CommonUtil.getBlankString(rs.getString("CA_PREFERRED_LANG")));
				String strMutliFlag = CommonUtil.getBlankString(rs.getString("MULTI_CONTRACT_FLAG"));
				if (strMutliFlag != null && strMutliFlag.trim().equalsIgnoreCase("X")) {
					bpContractAccountDO.setStrMultiContractFlag("Y");
				} else {
					bpContractAccountDO.setStrMultiContractFlag(strMutliFlag);
				}
				bpContractAccountDO.setStrCustSegment(CommonUtil.getBlankString(rs.getString("CUSTOMER_SEGMENT")));
				bpContractAccountDO.setStrEnrollSource(CommonUtil.getBlankString(rs.getString("ENROLL_SOURCE")));

				AddressDO addressDO = new AddressDO();
				addressDO.setStrAddressID(CommonUtil.getBlankString(rs.getString("BILLING_ADDR_ID")));
				addressDO.setStrStreetNum(CommonUtil.getBlankString(rs.getString("BILLING_STREEET_NUM")));
				addressDO.setStrApartNum(CommonUtil.getBlankString(rs.getString("BILLING_APT_NUM")));
				addressDO.setStrStreetName(CommonUtil.getBlankString(rs.getString("BILLING_STREET_NAME")));
				addressDO.setStrCity(CommonUtil.getBlankString(rs.getString("BILLING_CITY")));
				addressDO.setStrState(CommonUtil.getBlankString(rs.getString("BILLING_STATE")));
				addressDO.setStrCountry(CommonUtil.getBlankString(rs.getString("BILLING_COUNTRY")));
				addressDO.setStrPOBox(CommonUtil.getBlankString(rs.getString("BILLING_PO_BOX")));
				addressDO.setStrZip(CommonUtil.getBlankString(rs.getString("BILLING_ZIP_CODE")));
				addressDO.setStrAddressLine("");
				addressDO.setStrZipComplete("");

				bpContractAccountDO.setBillingAddressDO(addressDO);

				bpContractAccountDO.setStrAutoBankEli(CommonUtil.getSVTFlagValue(rs.getString("AUTOPAY_ELIG_BANK")));
				bpContractAccountDO.setStrAutoCCElig(CommonUtil.getSVTFlagValue(rs.getString("AUTOPAY_ELIG_CC")));
				bpContractAccountDO.setStrAutoPayFlag(CommonUtil.getBlankString(rs.getString("AUTOPAY_FLAG")));
				bpContractAccountDO.setStrAPBankAccNum(CommonUtil.getBlankString(rs.getString("AUTOPAY_BANK_ACCT_NO")));
				bpContractAccountDO.setStrAPCCNum(CommonUtil.getBlankString(rs.getString("AUTOPAY_CC_NO")));
				bpContractAccountDO.setStrAPCCType(CommonUtil.getBlankString(rs.getString("AUTOPAY_CC_TYPE")));
				if (rs.getString("AUTOPAY_CC_EXP_DT") != null) {
					bpContractAccountDO.setStrAPCCExpDate(CommonUtil.changeDateFormat(
							rs.getString("AUTOPAY_CC_EXP_DT"), DT_SQL_FMT_DB, DT_FMT));
				} else {
					bpContractAccountDO.setStrAPCCExpDate("");
				}

				bpContractAccountDO.setStrEbillFlag(CommonUtil.getSVTFlagValue(rs.getString("EBILL_FLAG")));
				bpContractAccountDO.setStrPaperFlag(CommonUtil.getSVTFlagValue(rs.getString("PAPER_FLAG")));
				bpContractAccountDO.setStrNCAStatus(CommonUtil.getSVTFlagValue(rs.getString("NCA_STATUS")));
				bpContractAccountDO.setStrNCCAStatus(CommonUtil.getSVTFlagValue(rs.getString("NCCA_STATUS")));
				bpContractAccountDO.setStrBalBillFlag(CommonUtil.getSVTFlagValue(rs.getString("BALANCE_BILL_FLAG")));
				bpContractAccountDO.setStrAvlBillFlag(CommonUtil.getSVTFlagValue(rs.getString("AVERAGE_BILL_ELIG")));
				bpContractAccountDO.setStrAvgBillFlag(CommonUtil.getSVTFlagValue(rs.getString("AVERAGE_BILL_FLAG")));
				bpContractAccountDO.setStrPaymentType(CommonUtil.getBlankString(rs.getString("PAYMENT_TYPE")));
				bpContractAccountDO.setStrCurrentInvoiceID(CommonUtil.getBlankString(rs.getString("INVOICE_ID")));
				bpContractAccountDO.setStrCompany(CommonUtil.getBlankString(rs.getString("COMPANY_CODE")));
				if(rs.getString("BRAND_NAME")!=null && rs.getString("BRAND_NAME").equals("CIRRO"))
					bpContractAccountDO.setStrBrandName(CIRRO_BRAND_NAME);
				else
					bpContractAccountDO.setStrBrandName(CommonUtil.getBlankString(rs.getString("BRAND_NAME")));
				bpContractAccountDO.setStrBPNumber(CommonUtil.getBlankString(rs.getString("BP_NUMBER")));
				/*
				 * bpContractAccountDO.setStrInvoiceId(CommonUtil.getBlankString(
				 * rs.getString("INVOICE_ID")));
				 * 
				 * if (rs.getString("INVOICE_DATE") != null) {
				 * bpContractAccountDO
				 * .setInvoiceDate(CommonUtil.changeDateFormat
				 * (rs.getString("INVOICE_DATE"), DT_SQL_FMT_DB, DT_FMT)); }
				 * else { bpContractAccountDO.setInvoiceDate(""); }
				 */

				bpContractAccountDO.setStrCharityId(CommonUtil.getBlankString(rs.getString("CHARITY_ID")));
				bpContractAccountDO.setStrCharityName(CommonUtil.getBlankString(rs.getString("CHARITY_NAME")));
				bpContractAccountDO
						.setStrLegacyAccount(CommonUtil.getBlankString(rs.getString("LEGACY_ACCOUNT_NUMBER")));

				if (rs.getString("ACCOUNT_CONVERSION_DATE") != null) {
					bpContractAccountDO.setStrConversionDate(CommonUtil.changeDateFormat(
							rs.getString("ACCOUNT_CONVERSION_DATE"), DT_SQL_FMT_DB, yyyy_MM_dd));
				} else {
					bpContractAccountDO.setStrConversionDate("");
				}

				/*
				 * bpContractAccountDO.setStrCaActiveFlag(CommonUtil.getBlankString
				 * (rs.getString("CA_ACTIVE_FLAG")));
				 * 
				 * if (rs.getString("CA_INACTIVE_DATE") != null) {
				 * bpContractAccountDO
				 * .setCaInactiveDate(CommonUtil.changeDateFormat
				 * (rs.getString("CA_INACTIVE_DATE"), DT_SQL_FMT_DB, DT_FMT)); }
				 * else { bpContractAccountDO.setCaInactiveDate(""); }
				 */

				bpContractAccountDO.setStrDisconnectAmt(CommonUtil.getBlankString(rs.getString("DISCONNECT_AMT")));

				if (rs.getString("DISCONNECT_DATE") != null) {
					bpContractAccountDO.setStrDisconnectDate(CommonUtil.changeDateFormat(
							rs.getString("DISCONNECT_DATE"), DT_SQL_FMT_DB, yyyy_MM_dd));
				} else {
					bpContractAccountDO.setStrDisconnectDate("");
				}

				bpContractAccountDO.setStrDNPFlag(CommonUtil.getSVTFlagValue(rs.getString("DISCONNECT_ELIG")));
				bpContractAccountDO.setUnBilledAmt(CommonUtil.getBlankString(rs.getString("UNBILLED_AMT")));
				bpContractAccountDOList.add(bpContractAccountDO);

				bpContractDOList = new LinkedList<BPContractDO>();
				bpContractAccountDO.setListOfContracts(bpContractDOList);

				bpPaymentDOList = new LinkedList<PaymentDO>();
				bpContractAccountDO.setPaymentDO(bpPaymentDOList);
				bpBalanceDOList = new LinkedList<BPBalanceDO>();
				bpContractAccountDO.setArDetailDo(bpBalanceDOList);
			}

			if (rs.getString("CONTRACT_ID") != null && !rs.getString("CONTRACT_ID").equals("")) {
				BPContractDO bpContractDO = new BPContractDO();
				bpContractDO.setStrContractID(CommonUtil.getBlankString(rs.getString("CONTRACT_ID")));

				if (rs.getString("CONTRACT_START_DATE") != null) {
					bpContractDO.setStrContractStartDate(CommonUtil.changeDateFormat(
							rs.getString("CONTRACT_START_DATE"), DT_SQL_FMT_DB, yyyy_MM_dd));
				} else {
					bpContractDO.setStrContractStartDate("");
				}

				if (rs.getString("CONTRACT_END_DATE") != null) {
					bpContractDO.setStrContractEndDate(CommonUtil.changeDateFormat(rs.getString("CONTRACT_END_DATE"),
							DT_SQL_FMT_DB, yyyy_MM_dd));
				} else {
					bpContractDO.setStrContractEndDate("");
				}

				if (rs.getString("MOVE_IN_DATE") != null) {
					bpContractDO.setStrMoveInDate(CommonUtil.changeDateFormat(rs.getString("MOVE_IN_DATE"),
							DT_SQL_FMT_DB, yyyy_MM_dd));
				} else {
					bpContractDO.setStrMoveInDate("");
				}

				if (rs.getString("MOVE_OUT_DATE") != null) {
					bpContractDO.setStrMoveOutDate(CommonUtil.changeDateFormat(rs.getString("MOVE_OUT_DATE"),
							DT_SQL_FMT_DB, yyyy_MM_dd));
				} else {
					bpContractDO.setStrMoveOutDate("");
				}

				bpContractDO.setStrESIID(CommonUtil.getBlankString(rs.getString("ESIID")));
				bpContractDO.setStrGuardLightFlag(CommonUtil.getSVTFlagValue(rs.getString("GUARD_LIGHT_FLAG")));
				bpContractDO.setStrMeterNumber(CommonUtil.getBlankString(rs.getString("METER_NUMBER")));
				bpContractDO.setStrMeterType(CommonUtil.getBlankString(rs.getString("METER_TYPE")));
				bpContractDO.setStrContractLegacyAccount(CommonUtil.getBlankString(rs.getString("LEGACY_CSP_NUMBER")));

				AddressDO addressDO = new AddressDO();
				addressDO.setStrAddressID(CommonUtil.getBlankString(rs.getString("SRVC_ADDR_ID")));
				addressDO.setStrStreetNum(CommonUtil.getBlankString(rs.getString("SRVC_STREET_NUM")));
				addressDO.setStrApartNum(CommonUtil.getBlankString(rs.getString("SRVC_APT_NUM")));
				addressDO.setStrStreetName(CommonUtil.getBlankString(rs.getString("SRVC_STREET_NAME")));
				addressDO.setStrCity(CommonUtil.getBlankString(rs.getString("SRVC_CITY")));
				addressDO.setStrState(CommonUtil.getBlankString(rs.getString("SRVC_STATE")));
				addressDO.setStrAddressLine(CommonUtil.getBlankString(rs.getString("SRVC_ADDR_LINE")));
				addressDO.setStrZip(CommonUtil.getBlankString(rs.getString("SRVC_ZIP_CODE")));
				addressDO.setStrCountry("");
				addressDO.setStrZipComplete("");
				addressDO.setStrPOBox("");
				bpContractDO.setServiceAddressDO(addressDO);

				OfferDO currentPlan = new OfferDO();
				currentPlan.setStrContractTerm(CommonUtil.getBlankString(rs.getString("CONTRACT_LENGTH")));
				currentPlan.setStrOfferCode(CommonUtil.getBlankString(rs.getString("OFFER_CODE")));
				if (rs.getString("OFFER_TEASER") != null && !rs.getString("OFFER_TEASER").trim().equalsIgnoreCase("")) {
					currentPlan.setStrOfferTeaser(rs.getString("OFFER_TEASER"));
				} else {
					currentPlan.setStrOfferTeaser("");
				}
				
				bpContractDO.setCurrentPlan(currentPlan);

				bpContractDO.setStrAvgPrice(CommonUtil.getBlankString(rs.getString("AVG_PRICE")));
				bpContractDO.setStrCancelFee(CommonUtil.getBlankString(rs.getString("CANCELLATION_FEE")));
				/*bpContractDO.setEflDocID(CommonUtil.getBlankString(rs.getString("EFL_DOC_ID_EN")));
				bpContractDO.setEflSmartCode(CommonUtil.getBlankString(rs.getString("EFL_SMART_CODE_EN")));

				bpContractDO.setTosDocID(CommonUtil.getBlankString(rs.getString("TOS_DOC_ID_EN")));

				bpContractDO.setTosSmartCode(CommonUtil.getBlankString(rs.getString("TOS_SMART_CODE_EN")));

				bpContractDO.setYraacDocID(CommonUtil.getBlankString(rs.getString("YRAAC_DOC_ID_EN")));

				bpContractDO.setYraacSmartCode(CommonUtil.getBlankString(rs.getString("YRAAC_SMART_CODE_EN")));

				bpContractDO.setTosSmartCode(CommonUtil.getBlankString(rs.getString("TEFLF_SMART_CODE_EN")));*/
				
				bpContractDO.setEflDocID("");
				bpContractDO.setEflSmartCode("");

				bpContractDO.setTosDocID("");

				bpContractDO.setTosSmartCode("");

				bpContractDO.setYraacDocID("");

				bpContractDO.setYraacSmartCode("");

				bpContractDOList.add(bpContractDO);
				continue;

			}

			if (rs.getString("PAYMENT_AMOUNT") != null && !rs.getString("PAYMENT_AMOUNT").equals("")) {
				BPPaymentDO bpPaymentDO = new BPPaymentDO();
				bpPaymentDO.setPaymentId(CommonUtil.getBlankString(rs.getString("PAYMENT_ID")));
				
				bpPaymentDO.setPaymentAmount(CommonUtil.getBlankString(rs.getString("PAYMENT_AMOUNT")));
				

				String strChannel = CommonUtil.getBlankString(rs.getString("PAYMENT_CHANNEL"));

				if (strChannel != null && strChannel.equalsIgnoreCase("I")) {
					bpPaymentDO.setChannel("Website");
				} else if ((strChannel != null && strChannel.equalsIgnoreCase("T"))) {
					bpPaymentDO.setChannel("Phone");
				} else if (strChannel != null && strChannel.equalsIgnoreCase("X")
						|| (strChannel != null && strChannel.equalsIgnoreCase("O"))) {
					bpPaymentDO.setChannel("Other");
				}

				String strPaymentStatus = CommonUtil.getBlankString(rs.getString("PAYMENT_STATUS"));
				String paymentDate = "";
				if ((strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(CANCELLED))
						|| (strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(REVERSE))) {
					if (rs.getString("PAYMENT_REVERSE_DATE") != null) {
						paymentDate = CommonUtil.changeDateFormat(rs.getString("PAYMENT_REVERSE_DATE"), DT_SQL_FMT_DB,
								yyyy_MM_dd);
						bpPaymentDO.setPaymentDate(paymentDate);
					} else {
						bpPaymentDO.setPaymentDate("");
					}

				} else {
					if (rs.getString("PAYMENT_SCHEDULE_DATE") != null) {
						paymentDate = (CommonUtil.changeDateFormat(rs.getString("PAYMENT_SCHEDULE_DATE"),
								DT_SQL_FMT_DB, yyyy_MM_dd));
						bpPaymentDO.setPaymentDate(paymentDate);
					} else {
						bpPaymentDO.setPaymentDate("");
					}
				}

				if ((strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(SENT))
						|| (strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(""))) {
					bpPaymentDO.setStatus("Paid");
				} else if ((strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(CANCELLED))
						|| (strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(REVERSE))) {
					bpPaymentDO.setStatus("Cancelled");
				} else if ((strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(POSTED))
						|| (strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(RETRY))
						|| (strPaymentStatus != null && strPaymentStatus.equalsIgnoreCase(SCHEDULED))) {
					bpPaymentDO.setStatus("Pending");
				}

				bpPaymentDO.setBankNumber(CommonUtil.getBlankString(rs.getString("BANK_ACCOUNT_NUMBER")));

				bpPaymentDO.setCcType(CommonUtil.getBlankString(rs.getString("CREDIT_CARD_TYPE")));
				bpPaymentDO.setCcNumber(CommonUtil.getBlankString(rs.getString("CREDIT_CARD_NUMBER")));

				bpPaymentDOList.add(bpPaymentDO);
			}

			if (rs.getString("AR_BALANCE") != null && !rs.getString("AR_BALANCE").equals("")) {
				BPBalanceDO balanceD0 = new BPBalanceDO();
				balanceD0.setStrCurrentARBalance(CommonUtil.getBlankString(rs.getString("AR_BALANCE")));
				if(!StringUtils.isEmpty(rs.getString("CREDIT_AMT"))) {
					balanceD0.setStrCreditAmt(StringUtils.trim(CommonUtil.normalizeNegativeNumber(rs
							.getString("CREDIT_AMT"))));
				} else {
					balanceD0.setStrCreditAmt("");
				}

				if (rs.getString("DUE_DATE") != null) {
					balanceD0.setStrCurrentDueDate(CommonUtil.changeDateFormat(rs.getString("DUE_DATE"), DT_SQL_FMT_DB, yyyy_MM_dd));
				} else {
					balanceD0.setStrCurrentDueDate("");
				}

				balanceD0.setStrLastPayAmt(CommonUtil.getBlankString(rs.getString("LAST_PAYMENT_AMT")));
				

				if (rs.getString("LAST_PAYMENT_DATE") != null) {
					balanceD0.setStrLastPayDate(CommonUtil.changeDateFormat(rs.getString("LAST_PAYMENT_DATE"),
							DT_SQL_FMT_DB, yyyy_MM_dd));
				} else {
					balanceD0.setStrLastPayDate("");
				}

				balanceD0.setStrPastDueAmt(CommonUtil.getBlankString(rs.getString("PAST_DUE_AMT")));
				
				bpBalanceDOList.add(balanceD0);
			}

		}

		// TODO Auto-generated method stub
		return bpBussinessPartnerDOList;
	}

}
