package com.credsystem.timesheet.exeption;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.beews.util.string.StringUtil;
import com.credsystem.timesheet.dto.ErroDTO;
import com.credsystem.timesheet.util.MsgProperties;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

	@Autowired
	MsgProperties msgProperties;

	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<ErroDTO> handleCustomExceptions(CustomException ex, WebRequest request) {
		var menssagem = msgProperties.getMenssagem(ex.getProp(), ex.getParam());
		ErroDTO errorDetails = new ErroDTO(LocalDateTime.now(), menssagem, request.getLocale().getDisplayName(),
				getTipe(ex));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErroDTO> handleAllExceptions(Exception ex, WebRequest request) {
		ErroDTO errorDetails = new ErroDTO(LocalDateTime.now(), ex.getMessage(), request.getLocale().getDisplayName(),
				getTipe(ex));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	private String getTipe(Exception ex) {
		return StringUtil.splitCamelCase(ex.getClass().getSimpleName()).replace("Exception", "").trim();
	}

}
