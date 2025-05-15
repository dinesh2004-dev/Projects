package com.farmSystem.DTO;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ErrorDTO {
	
	private String message;
	
	private HttpStatus error;
	
	private int code;
	
	private LocalDateTime timeStamp;

}
