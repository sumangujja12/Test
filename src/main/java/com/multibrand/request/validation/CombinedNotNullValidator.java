package com.multibrand.request.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapperImpl;

import com.multibrand.util.Constants;

public class CombinedNotNullValidator  implements 
				ConstraintValidator<CombinedNotNull, Object>, Constants {
					
	String[] fields;
	@Override
	public void initialize(final CombinedNotNull combinedNotNull) {
		fields = combinedNotNull.fields();		
	}

	
	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		 final BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);

	        for (String f : fields) {
	           String fieldValue = (String) beanWrapper.getPropertyValue(f);

	            if (StringUtils.isNotBlank(fieldValue)) {
	                return true;
	            }
	        }

		return false;
	} 

}
