package com.multibrand.vo.response;

import java.util.List;

import com.multibrand.util.Constants;
import com.multibrand.vo.response.billingResponse.ContractAccountDO;
import com.multibrand.vo.response.historyResponse.PaymentDO;

/*
 * Adding fields for Cirro Collective Billing
 */
public class BPContractAccountDO extends ContractAccountDO
{
	private String collectiveAccountFlag = "";
	public String getCollectiveAccountFlag() {
		return collectiveAccountFlag;
	}

	public void setCollectiveAccountFlag(String collectiveAccountFlag) {
		this.collectiveAccountFlag = collectiveAccountFlag;
	}

	public String getCollectiveContractAccount() {
		return collectiveContractAccount;
	}

	public void setCollectiveContractAccount(String collectiveContractAccount) {
		this.collectiveContractAccount = collectiveContractAccount;
	}

	private String collectiveContractAccount = "";
	private String unBilledAmt = "";
	private List<BPContractDO> listOfContracts;
	private List<PaymentDO> paymentDO;
	private List<BPBalanceDO> arDetailDo;

	/**
	 * @return the unBilledAmt
	 */
	public String getUnBilledAmt()
	{
		return unBilledAmt;
	}

	/**
	 * @param unBilledAmt
	 *            the unBilledAmt to set
	 */
	public void setUnBilledAmt(String unBilledAmt)
	{
		this.unBilledAmt = unBilledAmt;
	}

	/**
	 * @return the paymentDO
	 */
	public List<PaymentDO> getPaymentDO()
	{
		return paymentDO;
	}

	/**
	 * @param paymentDO
	 *            the paymentDO to set
	 */
	public void setPaymentDO(List<PaymentDO> paymentDO)
	{
		this.paymentDO = paymentDO;
	}

	/**
	 * @return the arDetailDo
	 */
	public List<BPBalanceDO> getArDetailDo()
	{
		return arDetailDo;
	}

	/**
	 * @param arDetailDo
	 *            the arDetailDo to set
	 */
	public void setArDetailDo(List<BPBalanceDO> arDetailDo)
	{
		this.arDetailDo = arDetailDo;
	}

	/**
	 * @return the listOfContracts
	 */
	public BPContractDO[] getListOfContracts()
	{
		BPContractDO[] bpContractDOs = null;
		if (listOfContracts != null && listOfContracts.size() > 0) {
			bpContractDOs = new BPContractDO[listOfContracts.size()];
			int count  =0;
			for(BPContractDO bpContractDO :listOfContracts) {
				bpContractDOs[count] = bpContractDO;
				count++;
			}
		}
		return bpContractDOs;
	}

	/**
	 * @param listOfContracts the listOfContracts to set
	 */
	public void setListOfContracts(List<BPContractDO> listOfContracts)
	{
		this.listOfContracts = listOfContracts;
	}

	public java.lang.String getStrMultiContractFlag()
	{

		if (listOfContracts != null && listOfContracts.size() > 1) {

			return Constants.FLAG_Y;
		} else {
			return Constants.FLAG_O;
		}
	}

}
