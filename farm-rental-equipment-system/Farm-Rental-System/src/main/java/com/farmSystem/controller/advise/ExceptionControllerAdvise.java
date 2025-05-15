package com.farmSystem.controller.advise;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.farmSystem.DTO.ErrorDTO;
import com.farmSystem.exception.UserNotFoundException;

import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ExceptionControllerAdvise {
	
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException exception){
		
		ErrorDTO errorDTO = ErrorDTO.builder()
							.message(exception.getMessage())
							.error(HttpStatus.NOT_FOUND)
							.code(HttpStatus.NOT_FOUND.value())
							.timeStamp(LocalDateTime.now())
							.build();
		return ResponseEntity.status(errorDTO.getError()).body(errorDTO);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleValidationException(ValidationException exception){
		System.out.println("1");
		ErrorDTO errorDTO = ErrorDTO.builder()
							.message(exception.getMessage())
							.error(HttpStatus.CONFLICT)
							.code(HttpStatus.CONFLICT.value())
							.timeStamp(LocalDateTime.now())
							.build();
		return ResponseEntity.status(errorDTO.getError()).body(errorDTO);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
		System.out.println("2");
		ErrorDTO errorDTO = ErrorDTO.builder()
							.message(exception.getMessage())
							.error(HttpStatus.CONFLICT)
							.code(HttpStatus.CONFLICT.value())
							.timeStamp(LocalDateTime.now())
							.build();
		return ResponseEntity.status(errorDTO.getError()).body(errorDTO);
		
	}
}
