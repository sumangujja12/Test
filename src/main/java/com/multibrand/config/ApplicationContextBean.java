package com.multibrand.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class ApplicationContextBean implements  ApplicationContextAware {

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextBean.context = applicationContext;
	}
	
	
	public static ApplicationContext getApplicationContext() {
		return ApplicationContextBean.context;
	}
	

}
