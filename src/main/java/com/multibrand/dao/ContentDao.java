package com.multibrand.dao;

import java.util.List;

import com.multibrand.dto.MobileContentDto;
import com.multibrand.vo.request.MaintenanceScheduleRequest;
import com.multibrand.vo.response.contentResponse.MaintenanceSchedule;



public interface ContentDao {
	public List<MobileContentDto> getMobileContentData();
	public List<MaintenanceSchedule> getMaintenanceSchedule(MaintenanceScheduleRequest request);
}
