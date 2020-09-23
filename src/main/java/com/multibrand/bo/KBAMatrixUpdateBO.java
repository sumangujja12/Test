package com.multibrand.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.dto.request.KBAMatrixUpdateRequest;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.KBAMatrixUpdateService;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.KBAMatrixUpdateResponse;

/**
 * This BO class is to handle all the GMD Related API calls.
 * 
 * @author rpendur1
 */
@Component
public class KBAMatrixUpdateBO extends BaseAbstractService implements Constants {

	@Autowired
	private KBAMatrixUpdateService kbaMatrixUpdateService;

	public KBAMatrixUpdateResponse kbaMatriUpdate(KBAMatrixUpdateRequest request) {
		return kbaMatrixUpdateService.kbaMatriUpdate(request);
	}
}