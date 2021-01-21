package com.multibrand.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.multibrand.vo.request.PushNotifiPreferenceRequest;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;

public class PreferencesResourceTest {
	
	@Test
	public  void getResponseStatus() throws MalformedURLException{
		
		PushNotifiPreferenceRequest request = new PushNotifiPreferenceRequest();
		request.setBrandName("GR");
		request.setCompanyCode("0271");
		request.setContractAccountNumber("10580093");
		
		URL url = new URL("http://localhost:8080/nrgrest/rest/preferences/readPushNotiPreference");
 
		String response = given().
        contentType("application/json").
        body(request).
        when().
        post(url).asString();
        
		System.out.println("Response ::: " +response);
		
		

	}

}
