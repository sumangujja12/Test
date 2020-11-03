package com.multibrand.vo.response.gmd;

import com.multibrand.vo.response.GenericResponse;

public class GmdMdStmtResponse extends GenericResponse {
	
	private GmdMdDailyStmtResponse stmtDailyData;
	private GmdMdMonthlyStmtResponse stmtMonthlyData;
	
	public GmdMdDailyStmtResponse getStmtDailyData() {
		return stmtDailyData;
	}
	public void setStmtDailyData(GmdMdDailyStmtResponse stmtDailyData) {
		this.stmtDailyData = stmtDailyData;
	}
	public GmdMdMonthlyStmtResponse getStmtMonthlyData() {
		return stmtMonthlyData;
	}
	public void setStmtMonthlyData(GmdMdMonthlyStmtResponse stmtMonthlyData) {
		this.stmtMonthlyData = stmtMonthlyData;
	}
	
	
	

}
