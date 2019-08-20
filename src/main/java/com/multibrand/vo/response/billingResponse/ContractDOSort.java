package com.multibrand.vo.response.billingResponse;

import java.util.Comparator;
import java.util.Date;

public class ContractDOSort implements Comparator<ContractDO> {

	@Override
	public int compare(ContractDO o1, ContractDO o2) {

		if(o1 == null || o2 == null)
			return 0;
		
		if ((o1.getStrSourceMoveOutDate() == null || o2.getStrSourceMoveOutDate() == null)) {
			return 0;
			
		}
		Date date1 = null;
		Date date2 = null;

		date1 = o1.getStrSourceMoveOutDate();
		date2 = o2.getStrSourceMoveOutDate();

		if (date1.compareTo(date2) > 0) {
			return -1;
		} else if (date1.compareTo(date2) < 0) {
			return 1;
		} else if (date1.compareTo(date2) == 0) {
			return 0;
		} else {
			return 0;
		}

	}

}
