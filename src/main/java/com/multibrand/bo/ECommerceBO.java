package com.multibrand.bo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.helper.OeBoHelper;
import com.multibrand.service.ECommerceService;
import com.multibrand.vo.response.GoogleProductSetResponse;

/**
 * 
 * @author HChoudhary
 */
@Component
public class ECommerceBO extends OeBoHelper{

	@Autowired
	ECommerceService ecommerceService;
	
	public GoogleProductSetResponse googleProductSet(){
		
		GoogleProductSetResponse response = new GoogleProductSetResponse();
		try{
			response = ecommerceService.googleProductSet();
		}catch(Exception ex){
			System.out.println("exception : "+ ex);
		}
		
		return response;
	}
}
