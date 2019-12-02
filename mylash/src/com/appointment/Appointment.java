package com.appointment;

public class Appointment {
	
	private int appointmentid;
	private int customerid;
	private String appointmentdate;
	private String appointmenttime;
	private String appointmenttype;
	private boolean appointmentcomplete;
	
	public Appointment(String appointmentdate, String appointmenttime, String appointmenttype) {
		super();
		this.appointmentdate = appointmentdate;
		this.appointmenttime = appointmenttime;
		this.appointmenttype = appointmenttype;
	}

	public Appointment(int appointmentid, int customerid, String appointmentdate, String appointmenttime,
			String appointmenttype, boolean appointmentcomplete) {
		super();
		this.appointmentid = appointmentid;
		this.customerid = customerid;
		this.appointmentdate = appointmentdate;
		this.appointmenttime = appointmenttime;
		this.appointmenttype = appointmenttype;
		this.appointmentcomplete = appointmentcomplete;
	}

	public int getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getAppointmentdate() {
		return appointmentdate;
	}

	public void setAppointmentdate(String appointmentdate) {
		this.appointmentdate = appointmentdate;
	}

	public String getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
	}

	public String getAppointmenttype() {
		return appointmenttype;
	}

	public void setAppointmenttype(String appointmenttype) {
		this.appointmenttype = appointmenttype;
	}

	public boolean isAppointmentcomplete() {
		return appointmentcomplete;
	}

	public void setAppointmentcomplete(boolean appointmentcomplete) {
		this.appointmentcomplete = appointmentcomplete;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentid=" + appointmentid + ", customerid=" + customerid + ", appointmentdate="
				+ appointmentdate + ", appointmenttime=" + appointmenttime + ", appointmenttype=" + appointmenttype
				+ ", appointmentcomplete=" + appointmentcomplete + "]";
	}
	
}
