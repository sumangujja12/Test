package com.multibrand.helper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.multibrand.dao.ContentDao;
import com.multibrand.dto.MobileContentDto;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.contentResponse.MobileContent;
import com.multibrand.vo.response.contentResponse.MobileContentItem;
import com.multibrand.vo.response.contentResponse.MobileContentResponse;

@Component
public class ErrorContentHelper {
	
	
	@Resource(name = "contentDao")
	private ContentDao contentDaoImpl;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	//public static final String GME_ERROR_CONTENT_CACHE ="contentpackage";
	public static List<MobileContentDto> slist;
	
	@Cacheable(value = Constants.ERROR_CONTENT_CACHE, key = "#key")
	public String getErrorMessage(String key)
	{
		
		String value = Constants.ERROR_CONTENT_DEFAULT;
		if(key.isEmpty())
			key = value;
		
		List<MobileContentDto> mobileContentData = getErrorContentList();
		
		if (mobileContentData != null && mobileContentData.size() > 0) {
			for (MobileContentDto content : mobileContentData) {
				if(!content.getName().isEmpty()&&content.getName().equalsIgnoreCase(key))
				{
					value = content.getValue();
					break; 
				}
			}
		}else{
			logger.info("Database Mapping for key {0} is not available",key);
		}
							
	return value;
	}
	
	//@Cacheable(value = GME_ERROR_CONTENT_CACHE)
	public List<MobileContentDto> getErrorContentList(){
		try{
			if(slist==null){
				slist = contentDaoImpl.getMobileContentData();
				logger.info("Getting value from DB Call Static - getErrorContentList call");
			}else{
				logger.info("Getting value from Appliation Static - getErrorContentList call");
				return slist;
			}
		}catch (Exception e) {
			logger.error("Error in getErrorContentList call");
			
		}
		return slist;
	}
	
	
	public MobileContentResponse getMobileContentData() {
		MobileContentItem errors = new MobileContentItem();
		MobileContentResponse mobileResponse = new MobileContentResponse();
		try {
		List<MobileContentDto> mobileContentData = contentDaoImpl.getMobileContentData();
		if (mobileContentData != null && mobileContentData.size() > 0) {
			for (MobileContentDto content : mobileContentData) {
				if (Constants.ERRORS.equalsIgnoreCase(content.getArea())) {
					if (Constants.EN.equalsIgnoreCase(content.getLanuage())) {
						errors.getEnglish().add(new MobileContent(content.getName(), content.getValue()));
					} else {
						errors.getSpanish().add(new MobileContent(content.getName(), content.getValue()));
					}
				}
			}
		} else {
			mobileResponse.setErrorCode(Constants.RESULT_CODE_THREE);
			mobileResponse.setErrorDescription(Constants.RESULT_CODE_DESCRIPTION_NO_DATA);
		}
		mobileResponse.setErrors(errors);
		} catch (Exception e) {
			logger.error("Exception Occured while getting mobile content data.. "+e);
			mobileResponse.setErrorCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			mobileResponse.setErrorDescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
		}
		return mobileResponse;

	}
}
