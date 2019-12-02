package com.customer;

public class Customer {
	
	private int customerid;
	private int userid;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String zipcode;
	private String state;
	private String phonenumber;
	private String username;
	private String email;
	
	public Customer(String firstName, String lastName, String address, String city,
			String zipcode, String state, String phonenumber, String username, String email) {
		this.firstname = firstName;
		this.lastname = lastName;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.state = state;
		this.phonenumber = phonenumber;
		this.username = username;
		this.email = email;
	}
	
	public Customer(int customerId, String firstName, String lastName, String address, String city,
			String zipcode, String state, String phonenumber, String username, String email) {
		this.customerid = customerId;
		this.firstname = firstName;
		this.lastname = lastName;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.state = state;
		this.phonenumber = phonenumber;
		this.username = username;
		this.email = email;
	}
	
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Customer[customerid = " + customerid + ", userid = " + userid + ", firstname= " + firstname + ", lastname= " + lastname + ", address= " + address + ", city= " + city + ", zipcode= " + zipcode + ", state= " + state +", phonenumber= " + phonenumber + ", username= " + username + ", email= " + email +"]";
	}
}
