package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;
import com.multibrand.util.Constants;

@XmlRootElement(name="SecurityContractCancelResponse")
@Component
public class SecurityContractCancelResponse implements Constants {
	
	public String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
