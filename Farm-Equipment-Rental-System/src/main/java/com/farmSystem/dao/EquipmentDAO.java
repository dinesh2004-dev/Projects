package com.farmSystem.dao;

import java.time.LocalDateTime;


import com.farmSystem.entity.User;
import com.farmSystem.enums.Availability;
import com.farmSystem.enums.Category;
import com.farmSystem.enums.Condition;




public class EquipmentDAO {

	private int Id;
	
	private String name;
	
	private User owner;

	private Category category;
	
	private String description;
	
	private Condition condition;
	
	private double rentalRate;

	private Availability availability;

	private String location;
	
	public EquipmentDAO() {
		
	}
	
	public EquipmentDAO(String name,String category,String location,String description,String condition,double rentalRate,String availability) {
		this.name = name;
		
		this.category = Category.valueOf(category);
		
		this.location = location;
		
		this.description = description;
		
		this.condition = Condition.valueOf(condition);
		
		this.rentalRate = rentalRate;
		
		this.availability = Availability.valueOf(availability);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getId() {
		return Id;
	}


	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "EquipmentDAO [Id=" + Id + ", name=" + name + ", owner=" + owner + ", category=" + category
				+ ", description=" + description + ", condition=" + condition + ", rentalRate=" + rentalRate
				+ ", availability=" + availability + "]";
	}
//	private LocalDateTime createDate;

}

