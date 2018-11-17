package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="PromoCharityOutData")
public class PromoCharityOutData {

	
	private java.lang.String mandt;

    private java.lang.String zbukrs;

    private java.lang.String zcharityId;

    private java.lang.String zdefault;

    private java.math.BigDecimal zdiscount;

    private java.lang.String zpromoCd;

    private java.lang.String ztdsp;

    private java.lang.String zzChar1;

    private java.lang.String zzChar2;

	public java.lang.String getMandt() {
		return mandt;
	}

	public void setMandt(java.lang.String mandt) {
		this.mandt = mandt;
	}

	public java.lang.String getZbukrs() {
		return zbukrs;
	}

	public void setZbukrs(java.lang.String zbukrs) {
		this.zbukrs = zbukrs;
	}

	public java.lang.String getZcharityId() {
		return zcharityId;
	}

	public void setZcharityId(java.lang.String zcharityId) {
		this.zcharityId = zcharityId;
	}

	public java.lang.String getZdefault() {
		return zdefault;
	}

	public void setZdefault(java.lang.String zdefault) {
		this.zdefault = zdefault;
	}

	public java.math.BigDecimal getZdiscount() {
		return zdiscount;
	}

	public void setZdiscount(java.math.BigDecimal zdiscount) {
		this.zdiscount = zdiscount;
	}

	public java.lang.String getZpromoCd() {
		return zpromoCd;
	}

	public void setZpromoCd(java.lang.String zpromoCd) {
		this.zpromoCd = zpromoCd;
	}

	public java.lang.String getZtdsp() {
		return ztdsp;
	}

	public void setZtdsp(java.lang.String ztdsp) {
		this.ztdsp = ztdsp;
	}

	public java.lang.String getZzChar1() {
		return zzChar1;
	}

	public void setZzChar1(java.lang.String zzChar1) {
		this.zzChar1 = zzChar1;
	}

	public java.lang.String getZzChar2() {
		return zzChar2;
	}

	public void setZzChar2(java.lang.String zzChar2) {
		this.zzChar2 = zzChar2;
	}
   
}
