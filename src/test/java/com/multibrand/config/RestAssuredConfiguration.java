package com.multibrand.config;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.multibrand.bo.BillingBoTest;
import com.multibrand.resource.BillingResourceTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	BillingResourceTest.class,
	BillingBoTest.class
})
public class RestAssuredConfiguration {
	
	@BeforeClass
	public static void configure(){
		/*RestAssured.baseURI="http://txaixsexbls04.retail.nrgenergy.com";
		RestAssured.port=14087;
		RestAssured.basePath="/NRGREST/rest/";*/
		RestAssured.baseURI="http://stg1-ws.nrgenergy.com";
		RestAssured.basePath="/NRGREST/rest/";
	}
	
	public RequestSpecification getRequestSpecification(){
		RequestSpecification spec= RestAssured.given().contentType(ContentType.URLENC);
		spec.accept(ContentType.JSON);
		spec.auth().preemptive().basic("khouser06", "Staging1");
		return spec;
	}

}
