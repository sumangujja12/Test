package com.multibrand.vo.response.historyResponse.xi;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @author ahanda1
 * Response POJO for XI payment history call. To be used with JAXB for marshalling and unmarshalling
 * PaymentHistoryResponse POJO will contain a list of this pojo
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "row", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="row", namespace="http://reliant.com/xi/GreenMountain")
public class Row {
	
	@XmlElement(name = "Account_ID")
	protected String accountId;
	
	@XmlElement(name = "Last_Payment_Amount")
	protected String Last_Payment_Amount;
	
	@XmlElement(name = "Last_Payment_Date")
	protected String Last_Payment_Date;
	
	@XmlElement(name = "Tender_Type")
	protected String Tender_Type;
	
	@XmlElement(name = "Result_Code")
	protected String Result_Code;
	
	@XmlElement(name = "Result_Description")
	protected String Result_Description;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getLast_Payment_Amount() {
		return Last_Payment_Amount;
	}

	public void setLast_Payment_Amount(String last_Payment_Amount) {
		Last_Payment_Amount = last_Payment_Amount;
	}

	public String getLast_Payment_Date() {
		return Last_Payment_Date;
	}

	public void setLast_Payment_Date(String last_Payment_Date) {
		Last_Payment_Date = last_Payment_Date;
	}

	public String getTender_Type() {
		return Tender_Type;
	}

	public void setTender_Type(String tender_Type) {
		Tender_Type = tender_Type;
	}

	public String getResult_Code() {
		return Result_Code;
	}

	public void setResult_Code(String result_Code) {
		Result_Code = result_Code;
	}

	public String getResult_Description() {
		return Result_Description;
	}

	public void setResult_Description(String result_Description) {
		Result_Description = result_Description;
	}

	
}
