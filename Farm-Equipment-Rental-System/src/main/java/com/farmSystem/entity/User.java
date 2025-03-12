package com.farmSystem.entity;

import java.time.LocalDateTime;

import com.farmSystem.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class User extends Base{
	
	
	@Column(name = "full_name",nullable = false)
	private String fullName;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "Email_id",nullable = false,unique = true)
	private String emailId;
	
	@Column(name = "mobile",nullable = false,length = 10,unique = true)
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role",nullable = false)
	private Role role;
	@Column(name = "Address",nullable = false)
	private String address;
	 @Column(insertable = false, updatable = false, 
	            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	

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
		return "Owner [id = " + getId()+ ", fullName=" + fullName + ", password=" + password + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", role=" + role + ", address=" + address + "]";
	}
	
	
	

}
