package com.multibrand.dao;

import com.multibrand.vo.request.WeeklySummaryEmailRequest;
import com.multibrand.vo.response.WeeklySummaryEmailResponse;

public interface WeeklySummaryEmailDAO {

	public WeeklySummaryEmailResponse getHistoricalWse(WeeklySummaryEmailRequest request);

}
