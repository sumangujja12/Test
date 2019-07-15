package com.multibrand.resource;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.multibrand.config.RestAssuredConfiguration;
import com.multibrand.util.CommonUtilTest;
import com.multibrand.util.DateUtilTestNew;
import com.multibrand.util.DateValidator;
import com.multibrand.util.EndPoint;
import com.multibrand.util.TestConstants;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BillingResourceTest implements TestConstants, EndPoint{
	
	private DateValidator dateValidator;
	private DateUtilTestNew dateUtil;
	private CommonUtilTest commonUtilTest;

	@Before
	public void init() {
		dateValidator = new DateValidator();
		dateUtil = new DateUtilTestNew();
		commonUtilTest = new CommonUtilTest();
		
	}
	
	@Test
	public void test_getBalance_sucess() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BALANCE);
		// if due date is not there ??? // is that mandotory
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		assertTrue(dateValidator.isValidDate(response.getBody().path("dueDate").toString(), "yyyyDDmm"));
	}
	
	@Test
	public void test_getBalance_invalidCANumber(){
		//this scenario is not implemented in proper way
	} 
	
	@Test
	public void test_getBillingAddress_sucess() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BILLING_ADDRESS);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("00", response.getBody().path("statusCode"));
	} 

	@Test
	public void test_getBillingAddress_noData(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BILLING_ADDRESS);
		Assert.assertEquals(200,response.statusCode());
		Assert.assertEquals("3",response.getBody().path("resultCode"));	
		Assert.assertEquals("00",response.getBody().path("statusCode"));
		Assert.assertEquals("No Data",response.getBody().path("resultDescription"));
	}
	
	@Test
	public void test_getAccountDetails_success() throws JSONException {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_ACCOUNT_DETAILS);
		JsonPath jsonPath = new JsonPath(response.body().asString());
		List<HashMap<String, String>> listOfContracts = jsonPath.get("contractAccountDO.listOfContracts");

		assertNotNull("Contract Details Can't be Null", response.path("contractAccountDO"));
		assertNotNull("Billing Address Can't be Null", response.path("contractAccountDO.billingAddressDO"));
		assertNotNull("BP Number Can't be Null", response.path("contractAccountDO.strBPNumber"));

		Assert.assertEquals("BP Number Isn't matching", response.path("contractAccountDO.strBPNumber"), "6021838525");
		Assert.assertEquals("ResultCode Isn't matching", response.path("resultCode"), "0");
		Assert.assertEquals("ESI Id Isn't matching", "10443720009849083", listOfContracts.get(0).get("strESIID"));
	}
	
	@Test
	public void test_getAccountDetails_noData() {
		//this scenario is not properly implemented
	}
	
	@Test
	public void test_updatePaperFreeBilling_shouldUpdateAsTrue() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("flag", "true");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.UPDATE_PAPER_FREE_BILLING);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
	}

	@Test
	public void test_updatePaperFreeBilling_shouldUpdateAsFalse() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("flag", "false");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.UPDATE_PAPER_FREE_BILLING);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
	}
	
	@Test
	public void test_updatePaperFreeBilling_flagValueIsNull() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("flag", "");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.UPDATE_PAPER_FREE_BILLING);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("3", response.getBody().path("resultCode"));
		Assert.assertEquals("No Data", response.getBody().path("resultDescription"));
	}
	
	@Test
	public void test_updateParperFreeBilling_noData(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("flag", "true");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.UPDATE_PAPER_FREE_BILLING);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("3", response.getBody().path("resultCode"));
		Assert.assertEquals("No Data", response.getBody().path("resultDescription"));
	}
	
	
	/***** This scenario should properly address in Resource class  ***/
	@Test
	public void test_getBalanceForGMEMobile_sucess(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BALANCE_FOR_GME_MOBILE);
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
	}
	
	/**** This scenario should properly address in Resource class **/
	@Test
	public void test_getBalanceForGMEMobile_noData() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BALANCE_FOR_GME_MOBILE);
		Assert.assertEquals("1", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("Exception Occurred", response.path("resultDescription"));
	}
	
	@Test
	public void test_submitBankPayment_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("bankAccountNumber", "M1IF9ZYL-112");
		requestSpecification.formParam("bankRoutingNumber", "125000024");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("accountName", "");
		requestSpecification.formParam("accountChkDigit", "");
		requestSpecification.formParam("locale", "");
		requestSpecification.formParam("email", "");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId","");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_BANK_PAYMENT);
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		assertNotNull(response.path("confNumber"));
	}
	
	@Test
	public void test_submitBankPayment_invalidCANumber(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("bankAccountNumber", "M1IF9ZYL-112");
		requestSpecification.formParam("bankRoutingNumber", "125000024");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("accountName", "");
		requestSpecification.formParam("accountChkDigit", "");
		requestSpecification.formParam("locale", "");
		requestSpecification.formParam("email", "");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId","");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_BANK_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_CCSERR_07_PAY_BNK", response.path("errorCode"));
	}
	
	@Test
	public void test_submitBankPayment_bankAccNumberIsNull(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("bankAccountNumber", "");
		requestSpecification.formParam("bankRoutingNumber", "125000024");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("accountName", "");
		requestSpecification.formParam("accountChkDigit", "");
		requestSpecification.formParam("locale", "");
		requestSpecification.formParam("email", "");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId","");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_BANK_PAYMENT);
		Assert.assertEquals("1", response.path("errorCode"));
		Assert.assertEquals("00", response.path("statusCode"));
	}
	
	@Test
	public void test_submitBankPayment_bankAccNumberIsInvalid(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("bankAccountNumber", "012yubs");
		requestSpecification.formParam("bankRoutingNumber", "125000024");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("accountName", "");
		requestSpecification.formParam("accountChkDigit", "");
		requestSpecification.formParam("locale", "");
		requestSpecification.formParam("email", "");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId","");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_BANK_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_CCSERR_04_PAY_BNK", response.path("errorCode"));
	}
	
	@Test
	public void test_submitBankPayment_paymentAmountIsMinus(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("bankAccountNumber", "M1IF9ZYL-112");
		requestSpecification.formParam("bankRoutingNumber", "125000024");
		requestSpecification.formParam("paymentAmount", -5);
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("accountName", "");
		requestSpecification.formParam("accountChkDigit", "");
		requestSpecification.formParam("locale", "");
		requestSpecification.formParam("email", "");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId","");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_BANK_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_CCSERR_54_PAY_BNK", response.path("errorCode"));
	}
	
	@Test
	public void test_submitBankPayment_bankRoutingNumberIsInvalid(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("bankAccountNumber", "M1IF9ZYL-112");
		requestSpecification.formParam("bankRoutingNumber", "");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("accountName", "");
		requestSpecification.formParam("accountChkDigit", "");
		requestSpecification.formParam("locale", "");
		requestSpecification.formParam("email", "");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId","");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_BANK_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_CCSERR_03_PAY_BNK", response.path("errorCode"));
	}
	
	@Test
	public void test_submitBankPayment_paymentAmountIsNull(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("bankAccountNumber", "M1IF9ZYL-112");
		requestSpecification.formParam("bankRoutingNumber", "125000024");
		requestSpecification.formParam("paymentAmount","");
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("accountName", "");
		requestSpecification.formParam("accountChkDigit", "");
		requestSpecification.formParam("locale", "");
		requestSpecification.formParam("email", "");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId","");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_BANK_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_CCSERR_54_PAY_BNK", response.path("errorCode"));
	
	}
	
	@Test 
	public void test_submitCCPayment_ccNumberIsInvalid(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("authType", "ZVIS");
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("ccNumber", "T40120075092340888");
		requestSpecification.formParam("cvvNumber", "5555");
		requestSpecification.formParam("expirationDate", "01/2020");
		requestSpecification.formParam("billingZip", "77070");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("accountName", "KARA HOUSER");
		requestSpecification.formParam("accountChkDigit", "4");
		requestSpecification.formParam("locale", "E");
		requestSpecification.formParam("email", "asha.perera@nrg.com");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId", "");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_CC_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_CCSERR_50_PAY_CC", response.path("errorCode"));
		
	}
	
	@Test 
	public void test_submitCCPayment_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("authType", "ZVIS");
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("ccNumber", "T401200750923407777");
		requestSpecification.formParam("cvvNumber", "5555");
		requestSpecification.formParam("expirationDate", "01/2020");
		requestSpecification.formParam("billingZip", "77070");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("accountName", "KARA HOUSER");
		requestSpecification.formParam("accountChkDigit", "4");
		requestSpecification.formParam("locale", "E");
		requestSpecification.formParam("email", "asha.perera@nrg.com");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId", "");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_CC_PAYMENT);
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		assertNotNull(response.path("confNumber"));
	}
	
	@Test 
	public void test_submitCCPayment_invalidCA(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("authType", "ZVIS");
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("bpid", "6021838525");
		requestSpecification.formParam("ccNumber", "T401200750923407777");
		requestSpecification.formParam("cvvNumber", "5555");
		requestSpecification.formParam("expirationDate", "01/2020");
		requestSpecification.formParam("billingZip", "77070");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("accountName", "KARA HOUSER");
		requestSpecification.formParam("accountChkDigit", "4");
		requestSpecification.formParam("locale", "E");
		requestSpecification.formParam("email", "asha.perera@nrg.com");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId", "");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_CC_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_CCSERR_54_PAY_CC", response.path("errorCode"));
	}

	@Test 
	public void test_submitCCPayment_inValidBPNumber(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("authType", "ZVIS");
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("bpid", "");
		requestSpecification.formParam("ccNumber", "T401200750923407777");
		requestSpecification.formParam("cvvNumber", "5555");
		requestSpecification.formParam("expirationDate", "01/2020");
		requestSpecification.formParam("billingZip", "77070");
		requestSpecification.formParam("paymentAmount", commonUtilTest.getRandomAmount());
		requestSpecification.formParam("accountName", "KARA HOUSER");
		requestSpecification.formParam("accountChkDigit", "4");
		requestSpecification.formParam("locale", "E");
		requestSpecification.formParam("email", "asha.perera@nrg.com");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("paymentDate", dateUtil.getDate(new Date(), "MM/DD/YYYY"));
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("emailTypeId", "");
		Response response = given().spec(requestSpecification).post(EndPoint.SUBMIT_CC_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_ERR_PAY_CC", response.path("errorCode"));
	}
	
	@Test
	public void test_projectedBill_success() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("esid", "10443720009849083");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PROJECTED_BILL);
		assertNotNull("Projected Bill Data Should not be null", response.path("projectedBillResponse"));
		Assert.assertEquals("Success", response.path("resultDescription"));
		Assert.assertEquals("00", response.path("statusCode"));
	}
	
	@Test
	public void test_projectedBill_noData() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("esid", "10443720009849083");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PROJECTED_BILL);
		Assert.assertEquals("No Data", response.path("resultDescription"));
		Assert.assertEquals("3", response.path("resultCode"));
	}
	
	@Test
	public void test_avgTemperatureBill_success(){
	//TODO:	
	}
	
	@Test
	public void test_avgTemperatureBill_noData(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("billStartDate", "20180101");
		requestSpecification.formParam("billEndDate", "20190101");
		requestSpecification.formParam("zoneId", "77433");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_AVERAGE_TEMP_BILL);
		Assert.assertEquals("No Data", response.path("resultDescription"));
		Assert.assertEquals("3", response.path("resultCode"));
	}
	
	@Test
	public void test_getBankCCInfo_success() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("businessPartnerId", "6021838525");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BANK_CC_INFO);
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("Success", response.path("resultDescription"));
		assertNotNull(response.path("autoPayDetailsList"));
	}
	
	@Test
	public void test_getBankCCInfo_bPNumberIsNull() {
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("businessPartnerId", "");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BANK_CC_INFO);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("INVALID_BUSINESS_PARTNER_ID", response.path("resultDescription"));
	}
	
	@Test
	public void test_getPayAccounts_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("contractAccountNumber", "000072938063");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PAY_ACCOUNTS);
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		assertNotNull(response.path("payAccountList"));
	}
	
	@Test
	public void test_getPayAccounts_invalidCA(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("contractAccountNumber", "");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PAY_ACCOUNTS);
		JsonPath jsonPath = new JsonPath(response.body().asString());
		List<HashMap<String, String>> payAccountList = jsonPath.get("payAccountList");
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals(0, payAccountList.size());
	}
	
	@Test
	public void test_scheduleOneTimeCCPayment_paymentDateExceedsMaxDays(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("businessPartnerId", "0006233102");
		requestSpecification.formParam("contractAccountNumber", "000009975310");
		requestSpecification.formParam("ccNumber", "T401200750923407777");
		requestSpecification.formParam("expMonth", "02");
		requestSpecification.formParam("expYear", "2020");
		requestSpecification.formParam("paymentAmount",commonUtilTest.getRandomAmount());
		requestSpecification.formParam("scheduledDate", "08/28/2019");
		requestSpecification.formParam("zipCode", "77070");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.SCHEDULE_ONETIME_CC_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("Scheduled CC Payment date exceeds max. number of days allowed.", response.path("resultDescription"));
	}
	
	@Test
	public void test_scheduleOneTimeCCPayment_pastPaymentDate(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("businessPartnerId", "0006233102");
		requestSpecification.formParam("contractAccountNumber", "000009975310");
		requestSpecification.formParam("ccNumber", "T401200750923407777");
		requestSpecification.formParam("expMonth", "02");
		requestSpecification.formParam("expYear", "2020");
		requestSpecification.formParam("paymentAmount",commonUtilTest.getRandomAmount());
		requestSpecification.formParam("scheduledDate", "2/28/2019");
		requestSpecification.formParam("zipCode", "77070");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.SCHEDULE_ONETIME_CC_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("Scheduled CC Payment date is in the past.", response.path("resultDescription"));
	}
	
	@Test
	public void test_scheduleOneTimeCCPayment_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("businessPartnerId", "0006233102");
		requestSpecification.formParam("contractAccountNumber", "000009975310");
		requestSpecification.formParam("ccNumber", "T401200750923407777");
		requestSpecification.formParam("expMonth", "02");
		requestSpecification.formParam("expYear", "2020");
		requestSpecification.formParam("paymentAmount",commonUtilTest.getRandomAmount());
		requestSpecification.formParam("scheduledDate", dateUtil.addDaysToCurrentDate(7,"MM/dd/yyyy"));
		requestSpecification.formParam("zipCode", "77070");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.SCHEDULE_ONETIME_CC_PAYMENT);
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		assertNotNull(response.path("eTrackingId"));
		
	}
	
	@Test
	public void test_scheduleOneTimeCCPayment_inValidCA(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("businessPartnerId", "0006233102");
		requestSpecification.formParam("contractAccountNumber", "");
		requestSpecification.formParam("ccNumber", "T401200750923407777");
		requestSpecification.formParam("expMonth", "02");
		requestSpecification.formParam("expYear", "2020");
		requestSpecification.formParam("paymentAmount",commonUtilTest.getRandomAmount());
		requestSpecification.formParam("scheduledDate", dateUtil.addDaysToCurrentDate(7,"MM/dd/yyyy"));
		requestSpecification.formParam("zipCode", "77070");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.SCHEDULE_ONETIME_CC_PAYMENT);
		Assert.assertEquals("2", response.path("resultCode"));
		Assert.assertEquals("00", response.path("statusCode"));
		Assert.assertEquals("MSG_ERR_INVALID_REQUEST", response.path("resultDescription"));
		
		
	}
	
	@Test
	public void test_getPaymentMethods_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("contractAccountNumber", "000072938063");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PAYMENT_METHODS);
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("Successfully retrieved all Menthods of Payments", response.path("messageCode"));
		assertNotNull(response.path("paymentMethods"));
	}
	
	@Test
	public void test_getPaymentMethods_invalidCA(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("contractAccountNumber", "");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PAYMENT_METHODS);
		Assert.assertEquals("5", response.path("resultCode"));
		Assert.assertEquals("Invalid Input Parameters",response.path("resultDescription"));
	}
	
	@Test
	public void test_getBankPaymentInstitution_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("routingNumber", "125000024");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BANK_PAYMENT_INSTITUTION);
		Assert.assertEquals("0", response.path("resultCode"));
		Assert.assertEquals("Success",response.path("resultDescription"));
	} 
	
	@Test
	public void test_getBankPaymentInstitution_invalidRoutingNumber(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("routingNumber", "");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_BANK_PAYMENT_INSTITUTION);
		Assert.assertEquals("3", response.path("resultCode"));
		Assert.assertEquals("Invalid Routing Number",response.path("resultDescription"));
	}
	
	
}
