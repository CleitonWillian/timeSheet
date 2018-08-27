package com.credsystem.timesheet.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.beews.webstart.anntotion.Delete;
import com.beews.webstart.anntotion.Get;
import com.beews.webstart.anntotion.Post;
import com.credsystem.timesheet.dto.DiaDeTrabalhoDTO;
import com.credsystem.timesheet.entity.TimeSheet;
import com.credsystem.timesheet.exeption.DataExeption;
import com.credsystem.timesheet.exeption.TimeSheetExeption;
import com.credsystem.timesheet.service.TimeSheetService;


@Controller
public class TimeSheetController {

	@Autowired
	TimeSheetService timeSheetService;

	@Post
	public ResponseEntity<HttpStatus> marcarPonto(
			@RequestParam(value = "ano", required = false) Integer ano,
			@RequestParam(value = "mes", required = false) Integer mes,
			@RequestParam(value = "id", required = true) String id,
			@RequestBody(required = false) DiaDeTrabalhoDTO data) throws DataExeption {
		timeSheetService.marcarPonto(id,ano, mes, data);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);

	}
	
	@Delete
	public ResponseEntity<HttpStatus> removerPonto(
			@RequestParam(value = "ts", required = true) String timeSheetId,
			@RequestParam(value = "id", required = true) String id,
			@RequestBody(required = true) DiaDeTrabalhoDTO data) throws TimeSheetExeption {
		timeSheetService.removerPonto(id,timeSheetId, data);
		return  new ResponseEntity<>(HttpStatus.ACCEPTED);

	}
	
	@Get
	public ResponseEntity<TimeSheet> getTimeSheet(@RequestParam(value = "ano", required = false) Integer ano,
			@RequestParam(value = "mes", required = false) Integer mes,
			@RequestParam(value = "id", required = false) String id) throws DataExeption {
		return new ResponseEntity<>(timeSheetService.buscarTimeSheet(ano, mes, id),HttpStatus.ACCEPTED);
		
	}
	
	@Get("lista")
	public ResponseEntity<List<TimeSheet>> getTimeSheets(@RequestParam(value = "ano", required = false) Integer ano,
			@RequestParam(value = "mes", required = false) Integer mes,
			@RequestParam(value = "id", required = false) String id) {
		return new ResponseEntity<>(timeSheetService.buscarTimeSheets(ano, mes, id),HttpStatus.ACCEPTED);
		
	}
	

}
