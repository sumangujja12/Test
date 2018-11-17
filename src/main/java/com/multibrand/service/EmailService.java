package com.multibrand.service;

import org.springframework.stereotype.Component;

import com.multibrand.dto.request.EmailRequest;
import com.multibrand.dto.response.EmailResponse;

/**
 * @author bbachin1
 * 
 */
@Component
public interface EmailService {
	
	public EmailResponse sendEmail(EmailRequest emailRequest);

}
