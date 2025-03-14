package com.farmSystem.dao;

import java.time.LocalDateTime;


import com.farmSystem.enums.Role;


public class UserDAO{
	
	
	private int Id;
	

	private String fullName;
	
	
	private String password;
	
	
	private String emailId;
	
	
	private String phoneNumber;
	
	private Role role;
	
	private double lattitude;
	
	private double longitute;
	
	private String address;
	
	private LocalDateTime createdAt;
	
	public UserDAO() {
		
	}
	
	public UserDAO(String fullName,String password,String emailId,String phoneNumber,Role role,String address,double lattitude,double longitute) {
		this.fullName = fullName;
		
		this.password = password;
		
		this.emailId = emailId;
		
		this.phoneNumber = phoneNumber;
		
		this.role = role;
		
		this.address = address;
		
		this.lattitude = lattitude;
		
		this.longitute = longitute;
	}
	

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitute() {
		return longitute;
	}

	public void setLongitute(double longitute) {
		this.longitute = longitute;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		
		this.Id = id;
	}
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	@Override
	public String toString() {
		return "UserDAO [Id=" + Id + ", fullName=" + fullName + ", password=" + password + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", role=" + role + ", address=" + address + ", createdAt="
				+ createdAt + "]";
	}
	

}
