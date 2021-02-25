package com.multibrand.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface SmartCarDAO {


	public  List<Map<String, Object>> getUserSmartCarTokens(String userUniqueId);
	
}
