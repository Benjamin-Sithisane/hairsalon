package com.payment;

public class Payment {
	
	private int payementid;
	private int appointmentid;
	private String paymenttype;
	private float paymentamount;
	private String invoicenum;
	
	public Payment(int payementid, int appointmentid, String paymenttype, float paymentamount, String invoicenum) {
		super();
		this.payementid = payementid;
		this.appointmentid = appointmentid;
		this.paymenttype = paymenttype;
		this.paymentamount = paymentamount;
		this.invoicenum = invoicenum;
	}
	
	public int getPayementid() {
		return payementid;
	}
	public void setPayementid(int payementid) {
		this.payementid = payementid;
	}
	public int getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public float getPaymentamount() {
		return paymentamount;
	}
	public void setPaymentamount(float paymentamount) {
		this.paymentamount = paymentamount;
	}
	public String getInvoicenum() {
		return invoicenum;
	}
	public void setInvoicenum(String invoicenum) {
		this.invoicenum = invoicenum;
	}
	
	@Override
	public String toString() {
		return "Payment [payementid=" + payementid + ", appointmentid=" + appointmentid + ", paymenttype=" + paymenttype
				+ ", paymentamount=" + paymentamount + ", invoicenum=" + invoicenum + "]";
	}
}
