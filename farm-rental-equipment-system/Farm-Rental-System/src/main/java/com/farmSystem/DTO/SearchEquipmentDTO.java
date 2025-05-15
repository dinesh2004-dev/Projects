package com.farmSystem.DTO;

import com.farmSystem.enums.Availability;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SearchEquipmentDTO {
	
	private String category;
	private String location;
	private Double minRate;
	private Double maxRate; 
	private String sortField;
	private String sortOrder;
	private int pageNumber;
	private int pageSize;
	private double userLng;
	private double userLat;
	private Double radius;
	private Availability availability;
}
