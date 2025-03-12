package com.farmSystem.entity;

import java.time.LocalDateTime;


import com.farmSystem.enums.Availability;
import com.farmSystem.enums.Category;
import com.farmSystem.enums.Condition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Equipment")
public class Equipment extends Base {

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "owner_id",referencedColumnName= "id", nullable = false)
	private User owner;

	@Enumerated(EnumType.STRING)

	@Column(nullable = false)
	
	private Category category;
	
	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String description;
	@Enumerated(EnumType.STRING)

	@Column(name = "`condition`",nullable = false)
	private Condition condition;

	@Column(name = "rental_rate", nullable = false, precision = 2)
	private double rentalRate;
	@Enumerated(EnumType.STRING)

	@Column(nullable = false)

	private Availability availability;
	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public double getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	public Availability getAvailability() {
		return availability;
	}
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

}
