package com.farmSystem.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingsRequestDTO {
	private int equipmentId;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
}
