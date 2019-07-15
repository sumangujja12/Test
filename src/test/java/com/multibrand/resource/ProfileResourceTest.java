package com.multibrand.resource;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.Test;


import com.multibrand.config.RestAssuredConfiguration;
import com.multibrand.util.EndPoint;
import com.multibrand.util.test.TestConstants;
import com.multibrand.vo.request.UserIdRequest;
import com.multibrand.vo.response.UserIdResponse;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProfileResourceTest implements TestConstants,EndPoint {
	
	@Test
	public void test_forgotUserName_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("zip", "90210");
		requestSpecification.formParam("languageCode", "EN");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.FORGOT_USERNAME);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
		Assert.assertEquals("Email with Username sent to user", response.getBody().path("messageText"));
	}
	
	@Test
	public void test_forgotUserName_invalidCA(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "895");
		requestSpecification.formParam("zip", "90210");
		requestSpecification.formParam("languageCode", "EN");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.FORGOT_USERNAME);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("2", response.getBody().path("resultCode"));
		Assert.assertEquals("Invalid Account Number", response.getBody().path("resultDescription"));
		Assert.assertEquals("Invalid Contract Account details- Account validation failed", response.getBody().path("messageText"));
	}
	
	@Test
	public void test_forgotUserName_cAnuberIsNull(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("zip", "90210");
		requestSpecification.formParam("languageCode", "EN");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.FORGOT_USERNAME);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("5", response.getBody().path("resultCode"));
		Assert.assertEquals("Invalid Input Parameters", response.getBody().path("resultDescription"));
		Assert.assertEquals("Invalid Input Parameters", response.getBody().path("messageText"));
	}
	
	@Test
	public void test_forgotUserName_invalidZipCode(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("zip", "75224");
		requestSpecification.formParam("languageCode", "EN");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.FORGOT_USERNAME);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("7", response.getBody().path("resultCode"));
		Assert.assertEquals("Invalid Zip Code", response.getBody().path("resultDescription"));
		Assert.assertEquals("Invalid Zip Code", response.getBody().path("messageText"));
	}
	
	@Test
	public void test_forgotPassword_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("zip", "90210");
		requestSpecification.formParam("languageCode", "EN");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.FORGOT_PASSWORD);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
		Assert.assertEquals("Password reset call sucessfull", response.getBody().path("messageText"));
	}
	
	@Test 
	public void test_forgotPassword_invalidCa(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "99");
		requestSpecification.formParam("zip", "75224");
		requestSpecification.formParam("languageCode", "EN");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.FORGOT_PASSWORD);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("2", response.getBody().path("resultCode"));
		Assert.assertEquals("Invalid Account Number", response.getBody().path("resultDescription"));
		Assert.assertEquals("Invalid Contract Account details- Account validation failed", response.getBody().path("messageText"));
	}
	
	@Test
	public void test_forgotPassword_cAnuberIsNull(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("zip", "90210");
		requestSpecification.formParam("languageCode", "EN");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.FORGOT_PASSWORD);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("5", response.getBody().path("resultCode"));
		Assert.assertEquals("Invalid Input Parameters", response.getBody().path("resultDescription"));
		Assert.assertEquals("Invalid Input Parameters", response.getBody().path("messageText"));
	}

	@Test 
	public void test_forgotPassword_invalidZipCode(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("zip", "75225");
		requestSpecification.formParam("languageCode", "EN");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.FORGOT_PASSWORD);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("7", response.getBody().path("resultCode"));
		Assert.assertEquals("Invalid Zip Code", response.getBody().path("resultDescription"));
		Assert.assertEquals("Invalid Zip Code", response.getBody().path("messageText"));
	}
	
	@Test
	public void test_validatePasswordlink_SuccessButLinkExpired(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("transactionId", "MDhBRTg2M0ZERjI2RTM3MjdBRDU2MkFCNTRGOEM5NUU1");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.VALIDATE_PASSWORD_LINK);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
		Assert.assertEquals("Link Expired", response.getBody().path("messageText"));
	}
	
	@Test
	public void test_getUserOrAcctNumber_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("userId", "khouser06");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_USER_OR_ACCOUNT_NUMBER);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
		assertNotNull(response.getBody().path("accountNumber"));
	}
	
	@Test
	public void test_getUserOrAcctNumber_invalidUserId(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("userId", "khouser");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_USER_OR_ACCOUNT_NUMBER);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("3", response.getBody().path("resultCode"));
		Assert.assertEquals("No Data", response.getBody().path("resultDescription"));
		
	}
	
	@Test
	public void test_getUserId_success(){
		UserIdRequest userIdRequest = new UserIdRequest();
		userIdRequest.setContractaccountnumber("000072938063");
		userIdRequest.setBrandname("GM");
		userIdRequest.setCompanycode("0271");
		
		String url = RestAssured.baseURI+":"+RestAssured.port+RestAssured.basePath+GET_USER_ID;
		Response response = RestAssured.given()
									   .contentType("application/json")
                                       .body(userIdRequest)
                                       .when()
                                       .post(url);
		
	  
		Assert.assertEquals(200, response.statusCode());
		UserIdResponse userIdRes = response.getBody().as(UserIdResponse.class);
		Assert.assertEquals("Success", userIdRes.getResultdescription());
		Assert.assertEquals("0", userIdRes.getResultcode());
		assertNotNull(userIdRes.getUserid());
	}
	
	@Test
	public void test_getUserId_caNumberIsEmpty(){
		UserIdRequest userIdRequest = new UserIdRequest();
		userIdRequest.setContractaccountnumber("");
		userIdRequest.setBrandname("GM");
		userIdRequest.setCompanycode("0271");
		
		String url = RestAssured.baseURI+":"+RestAssured.port+RestAssured.basePath+GET_USER_ID;
		Response response = RestAssured.given()
									   .contentType("application/json")
                                       .body(userIdRequest)
                                       .when()
                                       .post(url);
		
	  
		Assert.assertEquals(200, response.statusCode());
		UserIdResponse userIdRes = response.getBody().as(UserIdResponse.class);
		Assert.assertEquals("Request Entity has following errors: contractaccountnumber may not be empty", userIdRes.getResultdescription());
		Assert.assertEquals("1", userIdRes.getResultcode());
		
	}
	
	@Test
	public void test_getUserId_caNumberIsInvalid(){
		UserIdRequest userIdRequest = new UserIdRequest();
		userIdRequest.setContractaccountnumber("563433");
		userIdRequest.setBrandname("GM");
		userIdRequest.setCompanycode("0271");
		
		String url = RestAssured.baseURI+":"+RestAssured.port+RestAssured.basePath+GET_USER_ID;
		Response response = RestAssured.given()
									   .contentType("application/json")
                                       .body(userIdRequest)
                                       .when()
                                       .post(url);
		
	  
		Assert.assertEquals(200, response.statusCode());
		UserIdResponse userIdRes = response.getBody().as(UserIdResponse.class);
		Assert.assertEquals("No Data", userIdRes.getResultdescription());
		Assert.assertEquals("3", userIdRes.getResultcode());
	}
	
	@Test
	public void test_profileCheck_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("contractAccountNumber", "000072938063");
		requestSpecification.formParam("email", "RPENDUR1@NRG.COM");
		requestSpecification.formParam("checkDigit", "4");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PROFILE_CHECK);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
	}
	
	@Test
	public void test_profileCheck_invalidCa(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("contractAccountNumber", "");
		requestSpecification.formParam("email", "RPENDUR1@NRG.COM");
		requestSpecification.formParam("checkDigit", "4");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PROFILE_CHECK);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("3", response.getBody().path("resultCode"));
		Assert.assertEquals("No Data", response.getBody().path("resultDescription"));
	}
	
	@Test
	public void test_profileCheck_invalidEmailId(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("contractAccountNumber", "000072938063");
		requestSpecification.formParam("email", "RPENDUR1@NRG");
		requestSpecification.formParam("checkDigit", "4");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_PROFILE_CHECK);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("2", response.getBody().path("resultCode"));
		Assert.assertEquals("NO_MATCH", response.getBody().path("resultDescription"));
	}
	
	@Test
	public void test_smartMeterCheck_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("esid", "10443720009849083");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.SMART_METER_CHECK);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
	}
	
	@Test
	public void test_validateAccount_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "000072938063");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.VALIDATE_ACCOUNT);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
		assertNotNull(response.getBody().path("caNumber"));
	}
	
	@Test
	public void test_validateAccount_invalidCa(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("accountNumber", "");
		requestSpecification.formParam("brandName", "GM");
		requestSpecification.formParam("companyCode", "0271");
		Response response = given().spec(requestSpecification).post(EndPoint.VALIDATE_ACCOUNT);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("1", response.getBody().path("resultCode"));
		Assert.assertEquals("INVALID_DATA_OR_INPUT_ERROR", response.getBody().path("resultDescription"));
	}
	
	@Test
	public void test_getSVTData_success(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("bpNumber", "6013687735");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_SVT_DATA);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("0", response.getBody().path("resultCode"));
		Assert.assertEquals("Success", response.getBody().path("resultDescription"));
		assertNotNull(response.getBody().path("bussinessPartnerInfo"));
	}
	
	@Test
	public void test_getSVTData_invalidBpNumber(){
		RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		requestSpecification.formParam("bpNumber", "");
		requestSpecification.formParam("companyCode", "0271");
		requestSpecification.formParam("brandName", "GM");
		Response response = given().spec(requestSpecification).post(EndPoint.GET_SVT_DATA);
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals("3", response.getBody().path("resultCode"));
		Assert.assertEquals("No Data", response.getBody().path("resultDescription"));
		
	}
	
	
}
