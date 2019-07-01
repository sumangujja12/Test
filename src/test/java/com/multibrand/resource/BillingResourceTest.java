package com.multibrand.resource;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.multibrand.config.RestAssuredConfiguration;
import com.multibrand.util.DateValidator;
import com.multibrand.util.EndPoint;
import com.multibrand.util.TestConstants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BillingResourceTest implements TestConstants,EndPoint{
	
	private DateValidator dateValidator;

	@Before
	public void init() {
		if(dateValidator==null){
			dateValidator = new DateValidator();
		}
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
	

}
