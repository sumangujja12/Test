package com.multibrand.vo.response.billingResponse;

import java.util.Comparator;
import java.util.Date;

import com.multibrand.domain.AmbOutputTab;
import com.multibrand.util.DateUtil;

public class AmbOutputTabSort implements Comparator<AmbOutputTab> {

	@Override
	public int compare(AmbOutputTab o1, AmbOutputTab o2) {
		if (o1 == null || o2 == null)
			return 0;
		if ((o1.getInvoice() == null || o2.getInvoice() == null)) {
			return 0;

		}
		Date date1 = null;
		Date date2 = null;

		date1 = DateUtil.getDate(o1.getBillAllocDate(), "yyyy-MM-dd");
		date2 = DateUtil.getDate(o2.getBillAllocDate(), "yyyy-MM-dd");

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
