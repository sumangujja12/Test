package com.multibrand.dao;

import com.multibrand.domain.KbaQuestionResponse;
import com.multibrand.dto.KBASubmitResultsDTO;

public interface KbaDAO {
	public boolean updateKbaDetails(KBASubmitResultsDTO kBASubmitResultsDTO) throws Exception;
	public boolean addKbaDetails(KbaQuestionResponse kBAQuestionsMasterDTO) throws Exception;
}
