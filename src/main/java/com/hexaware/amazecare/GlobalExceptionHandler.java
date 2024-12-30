package com.hexaware.amazecare;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.exceptions.InvalidUsernameException;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
	private ResponseMessageDto dto; 
	//Logger logger = LoggerFactory.getLogger(PatientService.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?> handleResourceNotFoundException(Exception e){
		 dto.setMsg(e.getMessage());
		// logger.error("ResourceNotFoundException thrown " + dto.getMsg());
		 return ResponseEntity.badRequest().body(dto);
	}
	@ExceptionHandler(InvalidUsernameException.class)
	ResponseEntity<?> handleUsernameInvalidException(Exception e){
		 dto.setMsg(e.getMessage());
		// logger.error("ResourceNotFoundException thrown " + dto.getMsg());
		 return ResponseEntity.badRequest().body(dto);
	}
}