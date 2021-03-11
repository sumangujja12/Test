package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;
import com.multibrand.util.Constants;

@XmlRootElement(name="SecurityInvoiceResponse")
@Component
public class SecurityInvoiceResponse implements Constants{
	
	public String msgCode;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	
}
