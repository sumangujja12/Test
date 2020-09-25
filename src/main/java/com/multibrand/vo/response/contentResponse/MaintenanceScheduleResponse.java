package com.multibrand.vo.response.contentResponse;

import java.util.List;

import com.multibrand.vo.response.GenericResponse;

public class MaintenanceScheduleResponse extends GenericResponse {
	
	List<MaintenanceSchedule> maintenanceSchedules;

	public List<MaintenanceSchedule> getMaintenanceSchedules() {
		return maintenanceSchedules;
	}

	public void setMaintenanceSchedules(List<MaintenanceSchedule> maintenanceSchedules) {
		this.maintenanceSchedules = maintenanceSchedules;
	}

}
