package com.multibrand.helper;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.multibrand.dao.BillDAO;
import com.multibrand.vo.request.AvgTempRequestVO;
import com.multibrand.vo.request.ProjectedBillRequestVO;
import com.multibrand.vo.request.RetroPopupRequestVO;
import com.multibrand.vo.response.AvgTempResponse;
import com.multibrand.vo.response.ProjectedBillResponseList;
import com.multibrand.vo.response.RetroEligibilityResponse;
import com.multibrand.vo.response.billingResponse.BankCCInfoResponse;
import com.multibrand.vo.response.billingResponse.BankInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.CcInfoUpdateResponse;

/**
 * 
 * @author smuruga1 This class is used to access the bill related DAO.
 */
@Component
public class BillHelper
{

	@Resource(name = "billDao")
	private BillDAO billDAOImpl;
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	/**
	 * This will call the Bill DAO for the projected bill
	 * @param projReq
	 * @return
	 * @throws Exception 
	 */
	public ProjectedBillResponseList getProjectedBillDetails(
			ProjectedBillRequestVO projReq, String companyCode, String sessionId) throws Exception
	{
		logger.info(" START getProjectedBillDetails Method");
		return billDAOImpl.getProjectedBillDetails(projReq, companyCode, sessionId);
	}
	
	/**
	 * This will call the Bill DAO for the projected bill
	 * @param projReq
	 * @return
	 */
	public AvgTempResponse getAverageTempBill(
			AvgTempRequestVO avgTmpRequestVO, String companyCode, String sessionId)throws Exception
	{
		logger.info(" START getAverageTempBill Method");
		return billDAOImpl.getAverageTempBill(avgTmpRequestVO, companyCode, sessionId);
	}
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param companyCode
	 * @param sessionid
	 * @param brandName
	 */
	public BankCCInfoResponse getBankCCInfoDB(String bpid, String companyCode, String sessionId, String brandName)throws Exception{
		
		logger.info("BillHelper.getBankCCInfoDB:::::");
		
		return billDAOImpl.getBankCCInfo(bpid, companyCode, sessionId, brandName);
		
		}
	
	public BankInfoUpdateResponse updateBankInfoDB(String bpid, String bankAcNo, String bankRoutingNo, String updateFlag, String accountNickName, String defaultFlag, String bankAccountType,
			String bankAccountHolderType, String nameOnAccount, String onlinePayAccountId, String companyCode,String brandName, String sessionId) throws Exception{
		logger.info("BillHelper.updateBankInfoDB");
		return billDAOImpl.updateBankInfoDB(bpid, bankAcNo, bankRoutingNo, updateFlag,accountNickName, defaultFlag, bankAccountType,
				bankAccountHolderType, nameOnAccount, onlinePayAccountId, companyCode, brandName, sessionId);
	}
	
	public CcInfoUpdateResponse updateCCInfoDB (String bpid, String ccType, String ccNumber, String expMonth, String expYear, String billingZipCode, String updateFlag, String accountNickName,
			String defaultFlag, String nameOnAccount, String onlinePayAccountId, String companyCode,String brandName,String sessionId) throws Exception{
		logger.info("BillHelper.updateCCInfoDB");
		return billDAOImpl.updateCCInfoDB (bpid,  ccType,  ccNumber,  expMonth,  expYear,  billingZipCode,  updateFlag,  accountNickName,
				 defaultFlag,  nameOnAccount,  onlinePayAccountId,  companyCode, brandName, sessionId);
	}
	
	/**
	 * This will call the Bill DAO for the eligibility check
	 * @param retroReq
	 * @return
	 * @throws Exception 
	 */
	public RetroEligibilityResponse checkRetroEligibility(
			RetroPopupRequestVO retroReq, String companyCode, String sessionId) throws Exception
	{
		logger.info(" START checkRetroEligibility Method");
		return billDAOImpl.checkRetroEligibility(retroReq, companyCode, sessionId);
	}

	/**
	 * This will call the Bill DAO for the eligibility check
	 * @param retroReq
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> getThirdPartyPaymentLog(String companyCode, String accountNumber) throws Exception
	{
		logger.info(" START getThirdPartyPaymentLog Method");
		return billDAOImpl.getThirdPartyPaymentLog(companyCode, accountNumber);
	}
}
