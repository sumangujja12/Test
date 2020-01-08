package com.multibrand.dao;

import com.multibrand.vo.response.WeeklySummaryEmailResponse;

public interface WeeklySummaryEmailDAO {

	public WeeklySummaryEmailResponse getHistoricalWse(String contractAccountNumber, String companyCode);

}
