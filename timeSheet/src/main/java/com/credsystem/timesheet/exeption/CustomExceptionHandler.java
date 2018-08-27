package com.credsystem.timesheet.exeption;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.credsystem.timesheet.dto.ErroDTO;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErroDTO> handleAllExceptions(Exception ex, WebRequest request) {
		
	    ErroDTO errorDetails = new ErroDTO(LocalDateTime.now(), ex.getMessage(),
	        request.getLocale().getDisplayName(), ex.getClass().getSimpleName());
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	


}
