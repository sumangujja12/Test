package com.multibrand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.multibrand.dao.BillDAO;
import com.multibrand.dao.ResultObject;
import com.multibrand.dao.mapper.BankCCInfoRowMapper;
import com.multibrand.dao.mapper.PayAccountsRowMapper;
import com.multibrand.dao.mapper.ProjectedBillRowMapper;
import com.multibrand.dao.mapper.RetroBillingStatusRowMapper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.ProjectedBillStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.util.DateUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.AvgTempRequestVO;
import com.multibrand.vo.request.ProjectedBillRequestVO;
import com.multibrand.vo.request.RetroPopupRequestVO;
import com.multibrand.vo.request.StoreUpdatePayAccountRequest;
import com.multibrand.vo.response.AvgTempResponse;
import com.multibrand.vo.response.ProjectedBillResponse;
import com.multibrand.vo.response.ProjectedBillResponseList;
import com.multibrand.vo.response.RetroEligibilityResponse;
import com.multibrand.vo.response.billingResponse.BankCCInfoResponse;
import com.multibrand.vo.response.billingResponse.BankInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.CcInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.PayAccount;
import com.multibrand.vo.response.billingResponse.PayAccountDO;
import com.multibrand.vo.response.billingResponse.PayAccountInfoResponse;

import oracle.jdbc.OracleTypes;
@Component("billDao")
public class BillDAOImpl implements BillDAO, DBConstants, Constants
{
	@Resource(name="sqlQuerySource")
	protected AbstractMessageSource sqlMessage;
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	private JdbcTemplate smartJdbcTemplate;
	
	@Autowired
	@Qualifier("cpdbJdbcTemplate")
	private JdbcTemplate cpdbJdbcTemplate;
	
	@Autowired
	@Qualifier("gmeResJdbcTemplate")
	private JdbcTemplate gmeResJdbcTemplate;
	
	@Autowired
	private JdbcTemplate svtJdbcTemplate;

	@Autowired(required = true)
	public BillDAOImpl(
			@Qualifier("smartMainJdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.smartJdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ProjectedBillResponseList getProjectedBillDetails(
			ProjectedBillRequestVO projReq, String companyCode, String sessionId) throws Exception
	{
		logger.info("Inside getProjectedBillDetails in BillDaoImpl");
		
		long startTime = CommonUtil.getStartTime();
		
		//ProjectedBillResponse response = null;
		try{
		logger.info("Values in the request are CA is "
				+ projReq.getContractAccountNumber() + " and CO is "
				+ projReq.getContractId());

		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		BaseStoredProcedure storedProc = null;
		//System.out.println("hai1");
		inParamsTypeMap.put(ESIID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CONTRACT_ACT_ID_IN_V, OracleTypes.VARCHAR);
		//System.out.println("hai2");
		inParams.put(ESIID_IN_V,projReq.getEsIid() );
		inParams.put(CONTRACT_ACT_ID_IN_V, projReq.getContractAccountNumber());
		
		outParamsTypeMap.put(BILPRD_OUT_REC, new ResultObject(
				OracleTypes.CURSOR, new ProjectedBillRowMapper()));
		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));

		//System.out.println("hai3");
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, PROJECTEDBILL_PROC,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		
		Map<String, Object> storedProcResult = storedProc.execute();

			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(PROJECTEDBILL_PROC + "-" + elapsedTimeDisp);

		// END (TIME LOG)
		
		ProjectedBillResponseList responseList = null;
		logger.info(storedProcResult.get("RET_TYP_OUT_V"));
		
		if (storedProcResult != null
				&& (storedProcResult.get("RET_TYP_OUT_V") == null || storedProcResult
						.get("RET_TYP_OUT_V").equals(""))) {
			
			responseList = new ProjectedBillResponseList();
			List<ProjectedBillResponse> storeResponseList = (List<ProjectedBillResponse>) storedProcResult
					.get(BILPRD_OUT_REC);

			if (storeResponseList != null) {
				responseList.setProjectedBillResponse(storeResponseList);
				logger.info(storeResponseList.size());
			}
			
		}
			
		
		utilityloggerHelper.logTransaction("getProjectedBillDetails", false, projReq,responseList,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(projReq));
			logger.debug(XmlUtil.pojoToXML(responseList));
		}
		logger.info("Exiting getProjectedBillDetails in BillDaoImpl");
	
		return responseList;
		
        }catch(Exception ex){
        	logger.error(ex);
        	utilityloggerHelper.logTransaction("getProjectedBillDetails", false, projReq,ex,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    		if(logger.isDebugEnabled())
    			logger.debug(XmlUtil.pojoToXML(projReq));			
        	throw ex;
		}
	}
	
	
	/**
	 * 
	 * @param averageRequestVO
	 */
	public AvgTempResponse getAverageTempBill(AvgTempRequestVO averageRequestVO,String companyCode,String sessionId)throws Exception
	{
		long startTime = CommonUtil.getStartTime();
		try{
	 
		AvgTempResponse response = null;
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		

		String ZONE_ID_IN_V = averageRequestVO.getZoneId() ; // -- "HOUST" Zone Id which you will find when you hit WP_CUST_BILL_PERIOD table
		String RET_CD_OUT_V = null; // --It returns NULLs if there are not ERRORS else it returns couple of exception codes
		String AVG_TEMP_OUT_V = null; // --This is output temp which you get display on WEB
		String TYP_TEMP_IN_V = "B";

		logger.info("Start Date ** : "+averageRequestVO.getBillStartDate());
		logger.info("End Date ** : " +averageRequestVO.getBillEndDate());
		logger.info("Zonal Id ** : "+ZONE_ID_IN_V);

		
		logger.info("Before setting the input ** : ");
		inParams.put(BILL_STRT_DT, CommonUtil.getSqlDate(averageRequestVO.getBillStartDate(),Constants.DT_FMT));
		inParams.put(BILL_END_DT, CommonUtil.getSqlDate(averageRequestVO.getBillEndDate(),Constants.DT_FMT));
		logger.info("after setting the input ** : ");				
		inParams.put(ZONE_ID, ZONE_ID_IN_V);
		inParams.put(TYP_TEMP, TYP_TEMP_IN_V);

		

		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		logger.info("entryTime ** : "+entryTime);
		
		
		// execute the procedure
		ProjectedBillStoredProcedure storedProc = new ProjectedBillStoredProcedure(
				smartJdbcTemplate, PROJECTEDBILL_PROC_AVG, inParams);

		// execute the procedure statement
		Map<String, Object> storedProcResult = storedProc.execute(inParams);
		
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(PROJECTEDBILL_PROC + "-" + elapsedTimeDisp);

		// END (TIME LOG)

		String returnCode = (String) storedProcResult.get(RET_CD);
		Object avgTemp = storedProcResult.get(AVG_TEMP);
		
		logger.info("PROJECTEDBILL_PROC..The output is returnCode "
				+ returnCode);

		logger.info("PROJECTEDBILL_PROC..The output is avgTemp " + avgTemp);
		
		if (avgTemp != null) {
			response = new AvgTempResponse();
			response.setAvgTemp(avgTemp.toString());
		}
		if (returnCode != null || StringUtils.isNotBlank(returnCode)) {
			logger.info("Errors in Avarage temp store procedure****************");

		}
		
		utilityloggerHelper.logTransaction("getAverageTempBill", false, averageRequestVO,response,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(averageRequestVO));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
		
		}catch(Exception ex){
        	logger.error(ex);
        	utilityloggerHelper.logTransaction("getAverageTempBill", false, averageRequestVO,ex,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
    		if(logger.isDebugEnabled())
    			logger.debug(XmlUtil.pojoToXML(averageRequestVO));			
        	throw ex;
		}
	}
	
	/**
	 *@author ahanda1 
	 */
	public BankCCInfoResponse getBankCCInfo(String bpid, String companyCode, String sessionId, String brandName) throws Exception{
		BankCCInfoResponse response = null;
		
		String query ="select * from OL_PAY_ACCOUNT where BP_NUMBER = ?";
		Object[] args ={bpid};
		BankCCInfoRowMapper rse = new BankCCInfoRowMapper();
		
		response = svtJdbcTemplate.query(query, args, rse);
		
		return response;
	}
	
	/**
	 * @author ahanda1
	 */
	
	public BankInfoUpdateResponse updateBankInfoDB(String bpid, String bankAcNo, String bankRoutingNo, String updateFlag, String accountNickName, String defaultFlag, String bankAccountType,
			String bankAccountHolderType, String nameOnAccount, String onlinePayAccountId, String companyCode,String brandName, String sessionId)throws Exception{
		
		BankInfoUpdateResponse response = new BankInfoUpdateResponse();
		
		
		if(UPDATE_BANK_INFO_ADD.equalsIgnoreCase(updateFlag)){
			String query = "select max(online_pay_account_id) from ol_pay_account where bp_number ="+ bpid;
			int maxOnlinePayAccountId = (svtJdbcTemplate.queryForInt(query))+1;
			logger.info("maxOnlinePayAccountId :::: " + maxOnlinePayAccountId);
			Date creationDate = Calendar.getInstance().getTime();
			query = "INSERT INTO ol_pay_account (BP_NUMBER, ONLINE_PAY_ACCOUNT_ID, PAY_ACCOUNT_NICKNAME, DEFAULT_FLAG,"+
                    "ROUTING_NUMBER, TOKEN_BANK_ACCT_NUMBER, BANK_ACCT_TYPE, BANK_ACCT_HOLDER_TYPE, NAME_ON_ACCOUNT, CREATION_DATE, ONLINE_PAY_ACCOUNT_TYPE)"+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
			
			Object[] args = new Object[11];
			args[0] = bpid;
			args[1] = maxOnlinePayAccountId;
			args[2] = accountNickName;
			args[3] = defaultFlag;
			args[4] = bankRoutingNo;
			args[5] = bankAcNo;
			args[6] = bankAccountType;
			args[7] = bankAccountHolderType;
			args[8] = nameOnAccount;
			args[9] = creationDate;
			args[10] = ONLINE_ACCOUNT_TYPE_BANK;
			int rows = svtJdbcTemplate.update(query, args);
			
			if(rows==1)
				response.setOnlinePayAccountId(String.valueOf(maxOnlinePayAccountId));
			
			logger.info("No of rows added : " + rows);
			
		}
		if(UPDATE_BANK_INFO_UPDATE.equalsIgnoreCase(updateFlag)){
			Date updationDate = Calendar.getInstance().getTime();
			String query = "UPDATE ol_pay_account SET PAY_ACCOUNT_NICKNAME=?,DEFAULT_FLAG=?,ROUTING_NUMBER=?, TOKEN_BANK_ACCT_NUMBER=?,BANK_ACCT_TYPE=?,"+
                    "BANK_ACCT_HOLDER_TYPE=?, NAME_ON_ACCOUNT=?, UPDATE_DATE=? WHERE (BP_NUMBER=? AND ONLINE_PAY_ACCOUNT_ID=?) ";
			
			Object[] args = new Object[10];
			args[0] = accountNickName;
			args[1] = defaultFlag;
			args[2] = bankRoutingNo;
			args[3] = bankAcNo;
			args[4] = bankAccountType;
			args[5] = bankAccountHolderType;
			args[6] = nameOnAccount;
			args[7] = updationDate;
			args[8] = bpid;
			args[9] = onlinePayAccountId;
			int rows = svtJdbcTemplate.update(query, args);
			logger.info("no. of rows updated ::: " + rows);
			
		}
		if(UPDATE_BANK_INFO_DELETE.equalsIgnoreCase(updateFlag)){
			
			String query = "DELETE FROM ol_pay_account WHERE (BP_NUMBER=? AND ONLINE_PAY_ACCOUNT_ID=?) ";
			
			Object[] args = new Object[2];
			args[0] = bpid;
			args[1] = onlinePayAccountId;
			
			int rows = svtJdbcTemplate.update(query, args);
			logger.info("no. of rows updated ::: " + rows);
			
		}
		
		
		return response;
	}
	
	/**
	 * @author ahanda1
	 */
	
	public CcInfoUpdateResponse updateCCInfoDB (String bpid, String ccType, String ccNumber, String expMonth, String expYear, String billingZipCode, String updateFlag, String accountNickName,
			String defaultFlag, String nameOnAccount, String onlinePayAccountId, String companyCode,String brandName,String sessionId) throws Exception{
		
		CcInfoUpdateResponse response = new CcInfoUpdateResponse();
		
		
		if(UPDATE_CC_INFO_ADD.equalsIgnoreCase(updateFlag)){
			String query = "select max(online_pay_account_id) from ol_pay_account where bp_number ="+ bpid;
			int maxOnlinePayAccountId = (svtJdbcTemplate.queryForInt(query))+1;
			logger.info("maxOnlinePayAccountId :::: " + maxOnlinePayAccountId);
			Date creationDate = Calendar.getInstance().getTime();
			query = "INSERT INTO ol_pay_account (BP_NUMBER, ONLINE_PAY_ACCOUNT_ID, PAY_ACCOUNT_NICKNAME, DEFAULT_FLAG,"+
                    "CC_TYPE, TOKEN_CC_NUMBER, CC_EXP_MONTH, CC_EXP_YEAR, NAME_ON_ACCOUNT, CREATION_DATE, ONLINE_PAY_ACCOUNT_TYPE, CC_BILLING_ZIP_CODE)"+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			
			Object[] args = new Object[12];
			args[0] = bpid;
			args[1] = maxOnlinePayAccountId;
			args[2] = accountNickName;
			args[3] = defaultFlag;
			args[4] = ccType;
			args[5] = ccNumber;
			args[6] = Integer.parseInt(expMonth);
			args[7] = Integer.parseInt(expYear);
			args[8] = nameOnAccount;
			args[9] = creationDate;
			args[10] = ONLINE_ACCOUNT_TYPE_CC;
			args[11] = billingZipCode;
			int rows = svtJdbcTemplate.update(query, args);
			
			if(rows==1)
				response.setOnlinePayAccountId(String.valueOf(maxOnlinePayAccountId));
			
			logger.info("No of rows added : " + rows);
			
		}
		if(UPDATE_CC_INFO_UPDATE.equalsIgnoreCase(updateFlag)){
			Date updationDate = Calendar.getInstance().getTime();
			String query = "UPDATE ol_pay_account SET PAY_ACCOUNT_NICKNAME=?,DEFAULT_FLAG=?,CC_TYPE=?, TOKEN_CC_NUMBER=?,CC_EXP_MONTH=?,"+
                    "CC_EXP_YEAR=?, NAME_ON_ACCOUNT=?, UPDATE_DATE=?, CC_BILLING_ZIP_CODE=? WHERE (BP_NUMBER=? AND ONLINE_PAY_ACCOUNT_ID=?) ";
			
			Object[] args = new Object[11];
			args[0] = accountNickName;
			args[1] = defaultFlag;
			args[2] = ccType;
			args[3] = ccNumber;
			args[4] = Integer.parseInt(expMonth);
			args[5] = Integer.parseInt(expYear);
			args[6] = nameOnAccount;
			args[7] = updationDate;
			args[8] = billingZipCode;
			args[9] = bpid;
			args[10] = onlinePayAccountId;
			int rows = svtJdbcTemplate.update(query, args);
			logger.info("no. of rows updated ::: " + rows);
			
		}
		if(UPDATE_CC_INFO_DELETE.equalsIgnoreCase(updateFlag)){
			
			String query = "DELETE FROM ol_pay_account WHERE (BP_NUMBER=? AND ONLINE_PAY_ACCOUNT_ID=?) ";
			
			Object[] args = new Object[2];
			args[0] = bpid;
			args[1] = onlinePayAccountId;
			
			int rows = svtJdbcTemplate.update(query, args);
			logger.info("no. of rows updated ::: " + rows);
			
		}
		
		
		return response;
	}
	/*   *//**
	    * Method main.
	    * @param argz String[]
	 * @throws Exception 
	    *//*
	   public static void main(String[] argz) throws SQLException
	{
		   ClassPathXmlApplicationContext file = new ClassPathXmlApplicationContext(
				"NRGREST-dao-config.xml");

		JdbcTemplate jdbcTemplate = (JdbcTemplate) file
				.getBean("smartMainJdbcTemplate");

	BillDAOImpl iviewDAO = new BillDAOImpl(jdbcTemplate);
	ProjectedBillRequestVO billRequestVO = new ProjectedBillRequestVO();
	billRequestVO.setContractAccountNumber("000006656233");
	billRequestVO.setEsIid("10443720009162582");
	iviewDAO.getProjectedBillDetails(billRequestVO, "", "");
		AvgTempRequestVO avgInVo = new AvgTempRequestVO();
		avgInVo.setBillEndDate("20130920");
		avgInVo.setBillStartDate("20131019");
		avgInVo.setZoneId("HOUST");
		iviewDAO.getAverageTempBill(avgInVo, "");
		//billStartDate=20130920&billEndDate=20131019&zoneId=HOUST
		System.out.println("");
		
	}*/
/*	
	public static void main(String[] argz) throws Exception
	{
	 FileSystemXmlApplicationContext file = new FileSystemXmlApplicationContext(
				"G:/ashish/ashish_workspace/NRGREST/WebContent/WEB-INF/spring/NRGREST-appContext.xml");

	 System.out.println("file :::: "+ file);
	 
		BillDAOImpl billDao = (BillDAOImpl) file
				.getBean("billDao");
		System.out.println("billDao ::: " + billDao);
		
		// bank add
		//System.out.println(billDao.updateBankInfoDB("0001057000", "7Z0S2SMS-250", "321075947", "I", "bankAccount1", "No", "Saving",
			//	"person", "test1", "", "0391", "CE", ""));
		// bank update
		//System.out.println(billDao.updateBankInfoDB("0001057000", "7Z0S2SMS-250", "321075947", "U", "bankAccount1", "No", "Saving",
			//	"person", "test1", "1", "0391", "CE", ""));
		// bank delete
		//System.out.println(billDao.updateBankInfoDB("0001057000", "", "", "D", "", "", "",
			//	"", "", "1", "0391", "CE", ""));
		
		// CC add		
//		System.out.println(billDao.updateCCInfoDB ("0001057000", "ZMCD", "54-ewPa1j-2318", "08", "2014", "11000", "I", "CC1",
	//			"Yes", "test2", "", "0391","CE",""));
		// update CC
	//	System.out.println(billDao.updateCCInfoDB ("0001057000", "ZMCD", "54-4asmsx-6481", "08", "2014", "11000", "U", "CC1",
	//			"No", "test2", "3", "0391","CE",""));
		
		// delete CC
		//System.out.println(billDao.updateCCInfoDB ("0001057000", "", "", "", "", "", "D", "",
			//				"", "", "2", "0391","CE",""));
		
	}*/

	/**
	 * @author ahanda1
	 * 
	 * for fetching pay accounts for given contract account number
	 */
	public PayAccountInfoResponse getPayAccounts(String contractAccountNumber) throws Exception{
		
		logger.info("BillDAO-getPayAccounts :: Start");		
		String query ="select * from OL_PAY_ACCOUNT where USER_ACCOUNT_NUMBER = ? ORDER BY ONLINE_PAY_ACCOUNT_ID ASC";
		Object[] args ={contractAccountNumber};
		PayAccountsRowMapper payAccountsRM = new PayAccountsRowMapper();
		
		PayAccountInfoResponse response = null;
		
		response = gmeResJdbcTemplate.query(query, args, payAccountsRM);
		
		logger.info("BillDAO-getPayAccounts :: End");
		return response;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
       public int storePayAccount(StoreUpdatePayAccountRequest request) throws Exception{
		
		logger.info("BillDAO-storePayAccount :: Start");	
		
		long maxOnlinePayAccountId=0;
		boolean existingPayAccountFlag = false;
		int rows; // rows added or updated;
		
		PayAccountInfoResponse payAccountInfoResponse = this.getPayAccounts(request.getContractAccountNumber());
		
		if(payAccountInfoResponse != null){
			List<PayAccount> payAccounts = payAccountInfoResponse.getPayAccountList();
			if(payAccounts.size() != 0){
				for(PayAccount account : payAccounts){
					
					if(account.getPayAccountToken().equalsIgnoreCase(request.getPayAccountToken())){
						
						// existing payAccount so we need and update and not insert
						existingPayAccountFlag = true;
						// setting the onlinePayAccountId for found payAccount for update request
						request.setOnlinePayAccountId(Long.parseLong(account.getOnlinePayAccountId()));
						break;						
					}			
					
					maxOnlinePayAccountId = Long.parseLong(account.getOnlinePayAccountId());
				}
			
			}
		}
		
		
		
		//String query = "select max(online_pay_account_id) from ol_pay_account where USER_ACCOUNT_NUMBER ="+ request.getContractAccountNumber();
		// maxOnlinePayAccountId = (gmeResJdbcTemplate.queryForInt(query))+1;
		
		
		if(existingPayAccountFlag){
			// updating existing pay account
			rows= this.updatePayAccount(request);	
			
		}else{
		// new pay account addition
		maxOnlinePayAccountId = maxOnlinePayAccountId + 1;
		logger.info("maxOnlinePayAccountId :::: " + maxOnlinePayAccountId);
		Date creationDate = Calendar.getInstance().getTime();
		Date activationDate=CommonUtil.getSqlDate(request.getActivationDate(),DT_FMT_REQUEST);
		String query = "INSERT INTO ol_pay_account (USER_ACCOUNT_NUMBER, ONLINE_PAY_ACCOUNT_TYPE, LAST_FOUR_DIGIT, NAME_ON_ACCOUNT, PAY_ACCOUNT_NICKNAME, PAY_ACCOUNT_TOKEN,"
				+ " ZIP_CODE, ACTIVE_FLAG, ACTIVATION_DATE, CPDB_CREATION_DATE, VERIFY_CARD, ROUTING_NUMBER, CC_EXP_MONTH, CC_EXP_YEAR,"
				+ " ONLINE_PAY_ACCOUNT_ID, CC_TYPE, AUTO_PAY,PAYMENT_INSTITUTION_NAME ) "+
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		Object[] args = new Object[18];
		args[0] = request.getContractAccountNumber();
		args[1] = request.getOnlinePayAccountType();
		args[2] = request.getLastFourDigit();
		args[3] = request.getNameOnAccount();
		args[4] = request.getPayAccountNickName();
		args[5] = request.getPayAccountToken();
		args[6] = request.getZipCode();
		args[7] = request.getActiveFlag();
		args[8] = activationDate;
		args[9] = creationDate;
		args[10] = request.getVerifyCard();
		args[11] =request.getRoutingNumber();
		args[12] = request.getCcExpMonth();
		args[13] = request.getCcExpYear();
		args[14] = maxOnlinePayAccountId;
		args[15] = request.getCcType();
		args[16] = request.getAutoPay();
		args[17] = request.getPaymentInstitutionName();
		rows = gmeResJdbcTemplate.update(query, args);
		}
		
		logger.info("Rows added/updated to DB : " + rows);
		
		logger.info("BillDAO-storePayAccount :: End");
		return rows;
	}
	
       
       public int updatePayAccount(StoreUpdatePayAccountRequest request) throws Exception{
   		
   		logger.info("BillDAO-updatePayAccount :: Start");	
   		
   		String query = null;
   		
   		Object[] args = null;
   		
   		int rows = 0;
   		
   		Date updationDate = Calendar.getInstance().getTime();
   		Date activationDate=CommonUtil.getSqlDate(request.getActivationDate(),DT_FMT_REQUEST);
   		if(ONLINE_ACCOUNT_TYPE_CC.equalsIgnoreCase(request.getOnlinePayAccountType())){
   		
   	    query = "UPDATE ol_pay_account SET LAST_FOUR_DIGIT=?, NAME_ON_ACCOUNT=?, PAY_ACCOUNT_NICKNAME=?, PAY_ACCOUNT_TOKEN=?, "+
                " ZIP_CODE=?, ACTIVE_FLAG=?,ACTIVATION_DATE=?, CPDB_UPDATE_DATE=?, VERIFY_CARD=?, "
                + "CC_EXP_MONTH=?, CC_EXP_YEAR=?, CC_TYPE=?, AUTO_PAY=?"
                + " WHERE (USER_ACCOUNT_NUMBER=? AND ONLINE_PAY_ACCOUNT_ID=?) ";
   	    
   	    args = new Object[15];
		args[0] = request.getLastFourDigit();
		args[1] = request.getNameOnAccount();
		args[2] = request.getPayAccountNickName();
		args[3] = request.getPayAccountToken();
		args[4] = request.getZipCode();
		args[5] = request.getActiveFlag();
		args[6] = activationDate;
		args[7] = updationDate;
		args[8] = request.getVerifyCard();
		args[9] = request.getCcExpMonth();
		args[10] = request.getCcExpYear();
		args[11] = request.getCcType();
		args[12] = request.getAutoPay();
		args[13] = request.getContractAccountNumber();
		args[14] = request.getOnlinePayAccountId();
		
		
		rows = gmeResJdbcTemplate.update(query, args);
			logger.info("Rows updated in DB : " + rows);
   	    
   		}
   		
   		if(ONLINE_ACCOUNT_TYPE_BANK.equalsIgnoreCase(request.getOnlinePayAccountType())){
   	   		
   	   	    query = "UPDATE ol_pay_account SET LAST_FOUR_DIGIT=?, NAME_ON_ACCOUNT=?, PAY_ACCOUNT_NICKNAME=?, PAY_ACCOUNT_TOKEN=?, "+
   	                " ZIP_CODE=?, ACTIVE_FLAG=?, ACTIVATION_DATE=?, CPDB_UPDATE_DATE=?, VERIFY_CARD=?, "
   	                + "ROUTING_NUMBER=?, AUTO_PAY=?,PAYMENT_INSTITUTION_NAME=?"
   	                + " WHERE (USER_ACCOUNT_NUMBER=? AND ONLINE_PAY_ACCOUNT_ID=?) ";
   	   	    
   	   	 args = new Object[14];
 		args[0] = request.getLastFourDigit();
 		args[1] = request.getNameOnAccount();
 		args[2] = request.getPayAccountNickName();
 		args[3] = request.getPayAccountToken();
 		args[4] = request.getZipCode();
 		args[5] = request.getActiveFlag();
 		args[6] = activationDate;
 		args[7] = updationDate;
 		args[8] = request.getVerifyCard();
 		args[9] = request.getRoutingNumber();
 		args[10] = request.getAutoPay();
 		args[11] = request.getPaymentInstitutionName();
 		args[12] = request.getContractAccountNumber();
 		args[13] = request.getOnlinePayAccountId();
         
 		rows = gmeResJdbcTemplate.update(query, args);
   			logger.info("Rows updated in DB : " + rows);
   	   	    
   	   		}	
   		
   		logger.info("BillDAO-updatePayAccount :: End");
   		return rows;
   	}

	@Override
	public int insertRetroPopup(RetroPopupRequestVO request) throws Exception {
		
		logger.info("BillDAO-insertRetroPopup :: Start");	
		int rows; // rows added or updated;
		
		String query = "INSERT INTO RETRO_AVG_BILL_POPUP (contract_account_number, contract_id, popup_close_flag, current_bill_cycle_date,cpdb_created_by, last_update_user)"
				+ " VALUES ( ?, ?, ?, ?, ?, ?)";
				
		
		Object[] args = new Object[10];
		args[0] = request.getContractAccountNumber();
		args[1] = request.getContractId();
		args[2] = request.getPopupCloseFlag();
		args[3] = request.getCurrentBillCycleDate();
		args[4] = request.getCpdbCreatedBy();
		args[5] = request.getLastUpdateUser();
		rows = cpdbJdbcTemplate.update(query, args);
		
		
		logger.info("Rows added/updated to DB : " + rows);
		
		logger.info("BillDAO-insertRetroPopup :: End");
		return rows;
	}

	@SuppressWarnings("unchecked")
	public RetroEligibilityResponse checkRetroEligibility(RetroPopupRequestVO retroReq,String companyCode,String sessionId)throws Exception
	{
		long startTime = CommonUtil.getStartTime();
		RetroEligibilityResponse retroEligibilityResponse = null;
		
		String METHOD_NAME = "Load BillingDAO: hasRetroAvgBillingEligibility(..)";
		logger.debug("START:" + METHOD_NAME);
		
		BaseStoredProcedure storedProc = null;
		Map<String, Object> storedProcResult = null;
		List<RetroEligibilityResponse> retroDtoList = null;

		// procedure parameters map
		Map<String, Object> inParams = new HashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap =  new LinkedHashMap<String, ResultObject> ();
		
		try {
			
			logger.info("Retro Eligibility - SP Call Inputs: CA:"+retroReq.getContractAccountNumber()
					+": INVOICE Id: "+retroReq.getInvoiceNo()+":CO:"+retroReq.getContractId()+
					":AR Balance:"+retroReq.getCurrentARAmount()
					);
			
			// Set input values to the procedure

			inParams.put(in_contract_account_num,retroReq.getContractAccountNumber() );
			inParams.put(in_invoice_no, retroReq.getInvoiceNo());
			inParams.put(in_contract_id, retroReq.getContractId());
			inParams.put(in_current_ar_amount, retroReq.getCurrentARAmount());
			inParams.put(in_company_code, companyCode);

			// Set input parameters type list
			inParamsTypeMap.put(in_contract_account_num, OracleTypes.VARCHAR);
			inParamsTypeMap.put(in_invoice_no, OracleTypes.VARCHAR);
			inParamsTypeMap.put(in_contract_id, OracleTypes.VARCHAR);
			inParamsTypeMap.put(in_current_ar_amount, OracleTypes.VARCHAR);
			inParamsTypeMap.put(in_company_code, OracleTypes.VARCHAR);
			
			// Set output parameters type list
			outParamsTypeMap.put(CONST_OUT_RECORD_SET_CUR, 
						new ResultObject(OracleTypes.CURSOR, new RetroBillingStatusRowMapper()));
			
			// Get Store procedure manager service instance
			StoredProcedureManager storedProcedure = StoredProcedureManager
					.getInstance();

			// START (TIME LOG)
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();

			 
			/*DataSource dataSource;
			DriverManagerDataSource dddataSource = new DriverManagerDataSource();
			//DEV
			dddataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dddataSource.setUrl("jdbc:oracle:thin:@TXAIXEBNDBD02:1536:ecpd2x");
			dddataSource.setUsername("CPDB1_MAIN_USER");
			dddataSource.setPassword("CPDBMUDEV!");
			//STG
			dddataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dddataSource.setUrl("jdbc:oracle:thin:@txaixebxdbs01:1536:ECPS2X");
			dddataSource.setUsername("CPDB1_MAIN_USER");
			dddataSource.setPassword("CPDB1MUSTG!");
			
			dataSource= dddataSource;
			cpdbJdbcTemplate=new JdbcTemplate();
			cpdbJdbcTemplate.setDataSource(dataSource);*/

			storedProc = storedProcedure.createStoredProcedure(
					cpdbJdbcTemplate, RETROPOPUP_PROC, inParams, inParamsTypeMap, outParamsTypeMap, in_contract_account_num);

			
			// execute the procedure statement
			storedProcResult = storedProc.execute(inParams);

			// Elapsed time in minutes (TIME LOG)
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime
					+ " seconds." : "less than a second.";
			logger.info("PKG_ONLINE_USER_MANAGE_GME_MB.SP_GET_RETRO_ELIG_STATUS_GME procedure is completed in "
					+ elapsedTimeDisp);

			// END (TIME LOG)

			// Get retro result found in row-mapper
			retroDtoList = (List<RetroEligibilityResponse>) storedProcResult
					.get(CONST_OUT_RECORD_SET_CUR);
			logger.info(METHOD_NAME+":retroDtoList Size:"+retroDtoList.size());
			
			// get first row only
			retroEligibilityResponse = retroDtoList.get(0);
			if(retroEligibilityResponse!= null)
			{
				logger.info(METHOD_NAME+":is Retro Eligibile from DB :"+retroEligibilityResponse.getRetroSignupEligible());
			}

		} catch (Exception ex) {
			logger.error(ex);
        	utilityloggerHelper.logTransaction("checkRetroEligibility", false, retroReq,ex,"", startTime, "", sessionId, companyCode);
    		if(logger.isDebugEnabled())
    			logger.debug(XmlUtil.pojoToXML(retroReq));
    			logger.debug(XmlUtil.pojoToXML(retroEligibilityResponse));
        	throw ex;
		}

		logger.debug("END:" + METHOD_NAME);
		return 	retroEligibilityResponse;
	}
	
	// START | US-F222-DK | 10312018
	/**
	 *  @author dkrishn1
	 *  This method is used to get the Third party payment receipt details  from cpdb1_main.PAYMENT_RECEIPT_VALIDATION_LOG table.
	 *  for a given Contract Account Number and company Code
	 *  
	 *  @return Map<String, Object> The Map object with all columns
	 *  
	 */
	@Override
	public Map<String, Object> getThirdPartyPaymentLog(String companyCode, String accountNumber) throws Exception {

		logger.debug("START:- BillDAOImple.getThirdPartyPaymentLog() method :::");
		String query = sqlMessage.getMessage(DBConstants.QUERY_PAYMENT_RECEIPT_LOG, null, null);

		return cpdbJdbcTemplate.query(query, new Object[] { accountNumber, companyCode },
				new ResultSetExtractor<HashMap<String, Object>>() {
					@Override
					public HashMap<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
						HashMap<String, Object> paymentReceiptMap = new HashMap<String, Object>();
						while (rs.next()) {
							paymentReceiptMap.put(DBConstants.PAYMENT_VALIDATION_ACTIVE_ID,
									String.valueOf(rs.getLong(COL_TPP_PAYMENT_VALIDATION_ACTIVE_ID)));
							paymentReceiptMap.put(DBConstants.PAYMENT_AMOUNT,
									String.valueOf(rs.getDouble(COL_TPP_PAYMENT_AMOUNT)));
							paymentReceiptMap.put(DBConstants.PAYMENT_DATE,
									DateUtil.getDateForString(String.valueOf(rs.getDate(COL_TPP_PAYMENT_DATE)),
											DBConstants.RESPONSE_DATE_FORMAT, true));
							paymentReceiptMap.put(COL_TPP_RECEIPT_NUMBER,
									String.valueOf(rs.getString(COL_TPP_RECEIPT_NUMBER)));
							paymentReceiptMap.put(COL_TPP_VENDOR_ID, String.valueOf(rs.getString(COL_TPP_VENDOR_ID)));
							paymentReceiptMap.put(COL_TPP_SUCCESS_SHORT_MESSAGE,
									String.valueOf(rs.getString(COL_TPP_SUCCESS_SHORT_MESSAGE)));
							paymentReceiptMap.put(DBConstants.CREATION_DATE,
									DateUtil.getDateForString(String.valueOf(rs.getDate(COL_TPP_CPDB_CREATION_DATE)),
											DBConstants.RESPONSE_DATE_FORMAT, true));
							paymentReceiptMap.put(DBConstants.UPDATE_DATE,
									DateUtil.getDateForString(String.valueOf(rs.getDate(COL_TPP_CPDB_UPDATE_DATE)),
											DBConstants.RESPONSE_DATE_FORMAT, true));
						}
						return paymentReceiptMap;
					}
				});

	}

	//US-F222-DK | 10312018
     public PayAccountDO savePayAccount(StoreUpdatePayAccountRequest request) throws Exception{
		logger.info("BillDAO-savePayAccount :: Start");	
		PayAccountDO payAccountDO = new PayAccountDO();
		PayAccountDO upatePayAccountDO = new PayAccountDO();
		boolean flagNewPayAccount = false;
		long maxOnlinePayAccountId=0;
		String nName = request.getPayAccountNickName();
		int rows = -1; // rows added or updated;
		
		PayAccountInfoResponse payAccountInfoResponse = getPayAccounts(request.getContractAccountNumber());
		
		if(payAccountInfoResponse != null){
			List<PayAccount> payAccounts = payAccountInfoResponse.getPayAccountList();
			if(payAccounts.size() != 0){
					for(int i=0;i<payAccounts.size();){
					
					if((payAccounts.get(i).getPayAccountToken().equalsIgnoreCase(request.getPayAccountToken()))&&(request.getActiveFlag().equalsIgnoreCase(FLAG_Y))&& (payAccounts.get(i).getActiveFlag().equalsIgnoreCase(FLAG_Y))){
						payAccountDO.setAccountDuplicate(true);
						payAccountDO.setCallSuccess(false);
						flagNewPayAccount=false;
						logger.info("Pay  Account Already exists :::: " + request.getPayAccountToken());
						break;						
					}
					else if((payAccounts.get(i).getPayAccountToken().equalsIgnoreCase(request.getPayAccountToken()))&& (payAccounts.get(i).getActiveFlag().equalsIgnoreCase(FLAG_Y))&&(request.getActiveFlag().equalsIgnoreCase(FLAG_N)))
					{
						payAccountDO.setAccountDuplicate(true);
						payAccountDO.setCallSuccess(false);
						flagNewPayAccount=false;
						logger.info("Pay Account Already exists :::: " + request.getPayAccountToken());
						break;
						
					}
					else if((payAccounts.get(i).getPayAccountToken().equalsIgnoreCase(request.getPayAccountToken()))&& (payAccounts.get(i).getActiveFlag().equalsIgnoreCase(FLAG_N))&&(request.getActiveFlag().equalsIgnoreCase(FLAG_Y)))
					{
						payAccountDO.setAccountDuplicate(false);
						flagNewPayAccount=false;
						request.setOnlinePayAccountId(Long.parseLong(payAccounts.get(i).getOnlinePayAccountId()));
						upatePayAccountDO = modifyPayAccount(request);
						if(upatePayAccountDO.isCallSuccess()){
							logger.info("Reactivated Inactive Pay Account :::: " + request.getPayAccountToken());
							payAccountDO.setRows(upatePayAccountDO.getRows());
							payAccountDO.setCallSuccess(true);
						}else
						{
							payAccountDO.setNickNameExistsFlag(upatePayAccountDO.isNickNameExistsFlag());
							payAccountDO.setCallSuccess(false);
						}
						break;
						
					}
					else if((payAccounts.get(i).getPayAccountToken().equalsIgnoreCase(request.getPayAccountToken()))&& (payAccounts.get(i).getActiveFlag().equalsIgnoreCase(FLAG_N))&&(request.getActiveFlag().equalsIgnoreCase("N")))
					{
						payAccountDO.setAccountDuplicate(false);
						flagNewPayAccount=false;
						request.setOnlinePayAccountId(Long.parseLong(payAccounts.get(i).getOnlinePayAccountId()));
						upatePayAccountDO = modifyPayAccount(request);
						if(upatePayAccountDO.isCallSuccess()){
							logger.info("Stored Inactive Pay Account :::: " + request.getPayAccountToken());
							payAccountDO.setRows(upatePayAccountDO.getRows());
							payAccountDO.setCallSuccess(true);
						}else
						{
							payAccountDO.setNickNameExistsFlag(upatePayAccountDO.isNickNameExistsFlag());
							payAccountDO.setCallSuccess(false);
						}
						break;
						
					}else{
					payAccountDO.setAccountDuplicate(false);
					maxOnlinePayAccountId = Long.parseLong(payAccounts.get(i).getOnlinePayAccountId());
					flagNewPayAccount=true;
					i++;
					}
					}
					
				}
			
			if(!(payAccountDO.isNickNameExistsFlag())){
				//to get active nicknames
				List<PayAccount> modifiedList = new ArrayList<PayAccount>();
				
				for(int i=0;i<payAccountInfoResponse.getPayAccountList().size();){
				if(payAccounts.get(i).getActiveFlag().equalsIgnoreCase(FLAG_Y))
				{
					modifiedList.add(payAccounts.get(i));
					i++;
					}else{ 
						i++;
					}
				}
			  		if(modifiedList.size() != 0){
						for(PayAccount nickName : modifiedList){
								if((nickName.getPayAccountNickName().equalsIgnoreCase(nName))){
									payAccountDO.setNickNameExistsFlag(true);
									break;
							}
						}
			  		} 		
				}	
			}
		
		logger.info("Nickname Already exists :::: " + nName);
		if(flagNewPayAccount){
			if(!(payAccountDO.isNickNameExistsFlag())){	
			// new pay account addition
	   		maxOnlinePayAccountId = maxOnlinePayAccountId + 1;
	   		logger.info("maxOnlinePayAccountId :::: " + maxOnlinePayAccountId);
	   		
	   		String currentDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    		
    		if(!((request.getActivationDate().isEmpty()) || (request.getActivationDate().trim().equalsIgnoreCase(""))|| (request.getActivationDate()==null)))
    		{
    			currentDate = request.getActivationDate();
    		}
	   		
	   		Date creationDate = Calendar.getInstance().getTime();
	   		Date activationDate=CommonUtil.getSqlDate(currentDate,DT_FMT_REQUEST);
	   		String query = "INSERT INTO ol_pay_account (USER_ACCOUNT_NUMBER, ONLINE_PAY_ACCOUNT_TYPE, LAST_FOUR_DIGIT, NAME_ON_ACCOUNT, PAY_ACCOUNT_NICKNAME, PAY_ACCOUNT_TOKEN,"
	   				+ " ZIP_CODE, ACTIVE_FLAG, ACTIVATION_DATE, CPDB_CREATION_DATE, VERIFY_CARD, ROUTING_NUMBER, CC_EXP_MONTH, CC_EXP_YEAR,"
	   				+ " ONLINE_PAY_ACCOUNT_ID, CC_TYPE, AUTO_PAY,PAYMENT_INSTITUTION_NAME ) "+
	   				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	   		
	   		
	   		Object[] args = new Object[18];
	   		args[0] = request.getContractAccountNumber();
	   		args[1] = request.getOnlinePayAccountType();
	   		args[2] = request.getLastFourDigit();
	   		args[3] = request.getNameOnAccount();
	   		args[4] = request.getPayAccountNickName();
	   		args[5] = request.getPayAccountToken();
	   		args[6] = request.getZipCode();
	   		args[7] = request.getActiveFlag();
	   		args[8] = activationDate;
	   		args[9] = creationDate;
	   		args[10] = request.getVerifyCard();
	   		args[11] =request.getRoutingNumber();
	   		args[12] = request.getCcExpMonth();
	   		args[13] = request.getCcExpYear();
	   		args[14] = maxOnlinePayAccountId;
	   		args[15] = request.getCcType();
	   		args[16] = request.getAutoPay();
	   		args[17] = request.getPaymentInstitutionName();
	   		rows = gmeResJdbcTemplate.update(query, args);
	   		payAccountDO.setRows(rows);
	   		payAccountDO.setCallSuccess(true);
			}
		}

		logger.info("Rows added/updated to DB : " + rows);
		
		logger.info("BillDAO-savePayAccount :: End");
		return payAccountDO;
	}    
	
     /**
    	 * Enhanced API of updatePayAccount to update Pay Accounts with check on Existing Nick names.
    	 * @param request
    	 * @return
    	 * @throws Exception
    	 */      
     
     public PayAccountDO modifyPayAccount(StoreUpdatePayAccountRequest request) throws Exception{
    	 logger.info("BillDAO-modifyPayAccount :: Start");
 		String query = null;
 		PayAccountDO payAccountDO = new PayAccountDO();
 		Object[] args = null;
 		String currentDate;
 		boolean isNickNameExist = false;
 		boolean isCCExpMonthChange = false;
 		boolean isCCExpYearChange = false;
 		boolean activeFlag = false;
 		int rows = 0;

 		String nName = request.getPayAccountNickName();

 		if (request.getActiveFlag().equalsIgnoreCase(FLAG_Y)) {

 			PayAccountInfoResponse payAccountInfoResponse = getPayAccounts(request.getContractAccountNumber());

 			if (payAccountInfoResponse != null) {
 				List<PayAccount> payAccountList = payAccountInfoResponse.getPayAccountList();
 				List<PayAccount> activePayAccountList = new ArrayList<PayAccount>();
 				
 				if(!payAccountList.isEmpty()){
 					activeFlag = isActiveFlagChange(request,payAccountList);
 				}

 				for (PayAccount payAccount : payAccountList)
 					if (payAccount.getActiveFlag().equalsIgnoreCase(FLAG_Y)) {
 						activePayAccountList.add(payAccount);
 					}
 				

 				if (!activePayAccountList.isEmpty()) {

 					for (PayAccount activePayAccount : activePayAccountList) {
 						if (activePayAccount.getPayAccountNickName() != null) {
 							// Check weather nick name change or not
 							if ((activePayAccount.getPayAccountNickName().equalsIgnoreCase(nName))) {
 								isNickNameExist = true;
 							}
 						}
						
						if (activePayAccount.getOnlinePayAccountType().equalsIgnoreCase(ONLINE_ACCOUNT_TYPE_CC)) {
							// check weather CC expiration month change
							if ((activePayAccount.getPayAccountToken().equalsIgnoreCase(request.getPayAccountToken())
									&& !(activePayAccount.getCcExpMonth().equalsIgnoreCase(request.getCcExpMonth())))) {
								isCCExpMonthChange = true;
							}

							// check weather CC expiration year change
							if ((activePayAccount.getPayAccountToken().equalsIgnoreCase(request.getPayAccountToken())
									&& !(activePayAccount.getCcExpYear().equalsIgnoreCase(request.getCcExpYear())))) {
								isCCExpYearChange = true;
							}
						}
 					} // end for loop for the active pay accounts
 				}
 			}
 		}
 		 

 		if (!((request.getActivationDate().isEmpty()) || (request.getActivationDate().trim().equalsIgnoreCase(""))
 				|| (request.getActivationDate() == null))) {
 			currentDate = request.getActivationDate();
 		} else {
 			currentDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
 		}
 			

 		Date updationDate = Calendar.getInstance().getTime();
 		Date activationDate = CommonUtil.getSqlDate(currentDate, DT_FMT_REQUEST);

 		if (!isNickNameExist || isCCExpMonthChange || isCCExpYearChange || activeFlag) {
 			if (ONLINE_ACCOUNT_TYPE_CC.equalsIgnoreCase(request.getOnlinePayAccountType())) {

 				query = "UPDATE ol_pay_account SET LAST_FOUR_DIGIT=?, NAME_ON_ACCOUNT=?, PAY_ACCOUNT_NICKNAME=?, PAY_ACCOUNT_TOKEN=?, "
 						+ " ZIP_CODE=?, ACTIVE_FLAG=?,ACTIVATION_DATE=?, CPDB_UPDATE_DATE=?, VERIFY_CARD=?, "
 						+ "CC_EXP_MONTH=?, CC_EXP_YEAR=?, CC_TYPE=?, AUTO_PAY=?"
 						+ " WHERE (USER_ACCOUNT_NUMBER=? AND ONLINE_PAY_ACCOUNT_ID=?) ";

 				args = new Object[15];
 				args[0] = request.getLastFourDigit();
 				args[1] = request.getNameOnAccount();
 				args[2] = request.getPayAccountNickName();
 				args[3] = request.getPayAccountToken();
 				args[4] = request.getZipCode();
 				args[5] = request.getActiveFlag();
 				args[6] = activationDate;
 				args[7] = updationDate;
 				args[8] = request.getVerifyCard();
 				args[9] = request.getCcExpMonth();
 				args[10] = request.getCcExpYear();
 				args[11] = request.getCcType();
 				args[12] = request.getAutoPay();
 				args[13] = request.getContractAccountNumber();
 				args[14] = request.getOnlinePayAccountId();

 				rows = gmeResJdbcTemplate.update(query, args);
 				logger.info("Rows updated in DB : " + rows);
 				payAccountDO.setRows(rows);
 				payAccountDO.setNickNameExistsFlag(isNickNameExist);
 				payAccountDO.setCCExpMonthChange(isCCExpMonthChange);
 				payAccountDO.setCCExpYearChange(isCCExpYearChange);
 				payAccountDO.setActiveFlagChange(activeFlag);
 				payAccountDO.setCallSuccess(true);
 			}

 			if (ONLINE_ACCOUNT_TYPE_BANK.equalsIgnoreCase(request.getOnlinePayAccountType())) {

 				query = "UPDATE ol_pay_account SET LAST_FOUR_DIGIT=?, NAME_ON_ACCOUNT=?, PAY_ACCOUNT_NICKNAME=?, PAY_ACCOUNT_TOKEN=?, "
 						+ " ZIP_CODE=?, ACTIVE_FLAG=?, ACTIVATION_DATE=?, CPDB_UPDATE_DATE=?, VERIFY_CARD=?, "
 						+ "ROUTING_NUMBER=?, AUTO_PAY=?,PAYMENT_INSTITUTION_NAME=?"
 						+ " WHERE (USER_ACCOUNT_NUMBER=? AND ONLINE_PAY_ACCOUNT_ID=?) ";

 				args = new Object[14];
 				args[0] = request.getLastFourDigit();
 				args[1] = request.getNameOnAccount();
 				args[2] = request.getPayAccountNickName();
 				args[3] = request.getPayAccountToken();
 				args[4] = request.getZipCode();
 				args[5] = request.getActiveFlag();
 				args[6] = activationDate;
 				args[7] = updationDate;
 				args[8] = request.getVerifyCard();
 				args[9] = request.getRoutingNumber();
 				args[10] = request.getAutoPay();
 				args[11] = request.getPaymentInstitutionName();
 				args[12] = request.getContractAccountNumber();
 				args[13] = request.getOnlinePayAccountId();

 				rows = gmeResJdbcTemplate.update(query, args);
 				logger.info("Rows updated in DB : " + rows);
 				payAccountDO.setRows(rows);
 				payAccountDO.setNickNameExistsFlag(isNickNameExist);
 				payAccountDO.setCCExpMonthChange(isCCExpMonthChange);
 				payAccountDO.setCCExpYearChange(isCCExpYearChange);
 				payAccountDO.setActiveFlagChange(activeFlag);
 				payAccountDO.setCallSuccess(true);
 			}
 		} else {
 			payAccountDO.setNickNameExistsFlag(isNickNameExist);
 			payAccountDO.setCCExpMonthChange(isCCExpMonthChange);
			payAccountDO.setCCExpYearChange(isCCExpYearChange);
			payAccountDO.setActiveFlagChange(activeFlag);
 			payAccountDO.setCallSuccess(false);
 		}
 		logger.info("BillDAO-modifyPayAccount :: End");
 		return payAccountDO;

     }   
     
	public boolean isActiveFlagChange(StoreUpdatePayAccountRequest request, List<PayAccount> payAccountList) {
		boolean isActiveFlagChanged = false;
		try {
			for (PayAccount payAcc : payAccountList) {
				if (payAcc.getPayAccountToken().equalsIgnoreCase(request.getPayAccountToken())
						&& !(payAcc.getActiveFlag().equalsIgnoreCase(request.getActiveFlag()))) {
					isActiveFlagChanged = true;
				}
			}
		} catch (Exception e) {
			isActiveFlagChanged = false;
		}
		return isActiveFlagChanged;
	}
}
