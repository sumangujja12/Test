package com.multibrand.vo.response.contentResponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mobileContent")
public class MobileContent {
	
	private String name;
	private String value;
	
	public MobileContent() {
		
	}
	
	public MobileContent(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}
