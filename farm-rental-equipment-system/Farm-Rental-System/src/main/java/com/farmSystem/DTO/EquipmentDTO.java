package com.farmSystem.DTO;

import java.time.LocalDateTime;

import com.farmSystem.entity.Base;
import com.farmSystem.entity.User;
import com.farmSystem.enums.Availability;
import com.farmSystem.enums.Category;
import com.farmSystem.enums.Condition;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EquipmentDTO extends Base {

	
	private String name;

	private int id;
	private User owner;

	private Category category;


	private String location;

	
	private String description;

	
	private Condition condition;

	
	private double rentalRate;

	private Availability availability;

	private LocalDateTime createDate;

	

}

