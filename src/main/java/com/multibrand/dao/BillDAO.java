package com.multibrand.dao;

import java.util.Map;

import com.multibrand.vo.request.AvgTempRequestVO;
import com.multibrand.vo.request.ProjectedBillRequestVO;
import com.multibrand.vo.request.RetroPopupRequestVO;
import com.multibrand.vo.request.StoreUpdatePayAccountRequest;
import com.multibrand.vo.response.AvgTempResponse;
import com.multibrand.vo.response.ProjectedBillResponseList;
import com.multibrand.vo.response.RetroEligibilityResponse;
import com.multibrand.vo.response.billingResponse.BankCCInfoResponse;
import com.multibrand.vo.response.billingResponse.BankInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.CcInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.PayAccountDO;
import com.multibrand.vo.response.billingResponse.PayAccountInfoResponse;

public interface BillDAO
{
	public ProjectedBillResponseList getProjectedBillDetails(ProjectedBillRequestVO projReq, String companyCode, String sessionId) throws Exception;
	public AvgTempResponse getAverageTempBill(AvgTempRequestVO averageRequestVO,String companyCode, String sessionId)throws Exception;
	public BankCCInfoResponse getBankCCInfo(String bpid, String companyCode, String sessionId, String brandName)throws Exception;
	public BankInfoUpdateResponse updateBankInfoDB(String bpid, String bankAcNo, String bankRoutingNo, String updateFlag, String accountNickName, String defaultFlag, String bankAccountType,
			String bankAccountHolderType, String nameOnAccount, String onlinePayAccountId, String companyCode,String brandName, String sessionId)throws Exception;
	public CcInfoUpdateResponse updateCCInfoDB (String bpid, String ccType, String ccNumber, String expMonth, String expYear, String billingZipCode, String updateFlag, String accountNickName,
			String defaultFlag, String nameOnAccount, String onlinePayAccountId, String companyCode,String brandName,String sessionId) throws Exception;
	
	
	public PayAccountInfoResponse getPayAccounts(String contractAccountNumber) throws Exception;
	 public int storePayAccount(StoreUpdatePayAccountRequest request) throws Exception;
	 public int updatePayAccount(StoreUpdatePayAccountRequest request) throws Exception;
	 
	 public int insertRetroPopup(RetroPopupRequestVO request) throws Exception;
	 public RetroEligibilityResponse checkRetroEligibility(RetroPopupRequestVO retroReq, String companyCode, String sessionId) throws Exception;
	 public Map<String, Object> getThirdPartyPaymentLog(String companyCode, String accountNumber) throws Exception;
	 public PayAccountDO savePayAccount(StoreUpdatePayAccountRequest request) throws Exception;
	 public PayAccountDO modifyPayAccount(StoreUpdatePayAccountRequest request) throws Exception;
}
