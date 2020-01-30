package com.multibrand.dao;

import com.multibrand.domain.KbaQuestionResponse;

public interface KbaDAO {
	//public boolean updateKbaDetails(KBASubmitResultsDTO kBASubmitResultsDTO) throws Exception;
	public boolean addKbaDetails(KbaQuestionResponse kBAQuestionsMasterDTO) throws Exception;
}
