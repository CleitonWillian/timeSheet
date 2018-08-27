package com.credsystem.timesheet.service;

import java.util.List;

import com.credsystem.timesheet.dto.DiaDeTrabalhoDTO;
import com.credsystem.timesheet.entity.TimeSheet;
import com.credsystem.timesheet.exeption.DataExeption;
import com.credsystem.timesheet.exeption.TimeSheetExeption;

public interface TimeSheetService {

	void marcarPonto(String id, Integer ano, Integer mes, DiaDeTrabalhoDTO data) throws DataExeption;

	TimeSheet buscarTimeSheet(Integer ano, Integer mes, String id) throws DataExeption;

	List<TimeSheet> buscarTimeSheets(Integer ano, Integer mes, String id);

	void removerPonto(String id, String timeSheetId, DiaDeTrabalhoDTO data) throws TimeSheetExeption;

}
