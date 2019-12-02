package com.payment;

public class Invoice {
	private int appointmentid;
	private String firstname;
	private String lastname;
	private String appointmentdate;
	private String appointmenttype;
	private float paymentamount;
	private String invoicenum;
		
	public Invoice(int appointmentid, String firstname, String lastname, String appointmentdate, String appointmenttype,
			float paymentamount, String invoicenum) {
		super();
		this.appointmentid = appointmentid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.appointmentdate = appointmentdate;
		this.appointmenttype = appointmenttype;
		this.paymentamount = paymentamount;
		this.invoicenum = invoicenum;
	}

	public int getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAppointmentdate() {
		return appointmentdate;
	}

	public void setAppointmentdate(String appointmentdate) {
		this.appointmentdate = appointmentdate;
	}

	public String getAppointmenttype() {
		return appointmenttype;
	}

	public void setAppointmenttype(String appointmenttype) {
		this.appointmenttype = appointmenttype;
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
		return "Invoice [appointmentid=" + appointmentid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", appointmentdate=" + appointmentdate + ", appointmenttype=" + appointmenttype + ", paymentamount="
				+ paymentamount + ", invoicenum=" + invoicenum + "]";
	}
}
