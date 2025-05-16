package com.farmSystem.entity;



import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.farmSystem.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "user_table")
public class User extends Base implements UserDetails{
	
	
	@Column(name = "full_name",nullable = false)
	private String fullName;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "Email_id",nullable = false,unique = true)
	private String emailId;
	
	@Column(name = "mobile",nullable = false,length = 10,unique = true)
	private String phoneNumber;
	
	@Column(nullable = false)
	private double latitude;
	@Column(nullable = false)
	private double longitude;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role",nullable = false)
	private Role role;
	@Column(name = "Address",nullable = false)
	private String address;
	 @Column(insertable = false, updatable = false, 
	            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
		 return List.of(new SimpleGrantedAuthority("ROLE_" + role.name().toUpperCase()));
	 }
	 @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Equipment> equipments;

	@Override
	public String getUsername() {
	    return this.emailId;
	}
	@Override
	public String getPassword() {
	    return this.password;
	}
	@Override
	public boolean isAccountNonExpired() {
	    return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true;
	}

	@Override
	public boolean isEnabled() {
	    return true;
	}


	
	

}
