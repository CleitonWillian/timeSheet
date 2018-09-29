package com.credsystem.timesheet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beews.webstart.anntotion.Get;
import com.beews.webstart.properties.AppProperties;

@RestController
@RequestMapping(value = "/ping")
public class PingController {
	

	@Autowired
	AppProperties appProperties;
	
	@Get
	public ResponseEntity<AppProperties> pingApp() {
		return new ResponseEntity<>(appProperties, HttpStatus.OK);
	}
	
	
	
}