package com.multibrand.vo.response.billingResponse;

import java.util.Arrays;
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

	public static void main(String[] argz) {
		ContractDO[] contractDO = new ContractDO[5];
		ContractDO temDo = new ContractDO();
		temDo.setStrMoveOutDate("2019-01-01");
		contractDO[0] = temDo;
		temDo = new ContractDO();
		temDo.setStrMoveOutDate("2019-02-02");
		contractDO[1] = temDo;
		temDo = new ContractDO();
		temDo.setStrMoveOutDate("2019-03-03");
		contractDO[2] = temDo;
		temDo = new ContractDO();
		temDo.setStrMoveOutDate("2019-04-04");
		contractDO[3] = temDo;

		Arrays.sort(contractDO, new ContractDOSort());
		System.out.println(contractDO[0].getStrMoveOutDate());
		System.out.println(contractDO[1].getStrMoveOutDate());
		System.out.println(contractDO[2].getStrMoveOutDate());
		System.out.println(contractDO[3].getStrMoveOutDate());
	}

}
