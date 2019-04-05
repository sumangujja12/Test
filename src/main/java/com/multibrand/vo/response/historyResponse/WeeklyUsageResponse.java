package com.multibrand.vo.response.historyResponse;

import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;
import com.multibrand.vo.response.DailyResponseVO;
import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name = "weeklyUsageResponse")
@Component
public class WeeklyUsageResponse extends GenericResponse {

	private Set<DailyResponseVO> weeklyUsageData;

	public Set<DailyResponseVO> getWeeklyUsageData() {
		return weeklyUsageData;
	}

	public void setWeeklyUsageData(Set<DailyResponseVO> weeklyUsageData) {
		this.weeklyUsageData = weeklyUsageData;
	}

}
