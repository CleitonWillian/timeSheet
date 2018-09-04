package com.credsystem.timesheet.service;

import java.util.List;

import com.credsystem.timesheet.dto.DiaDeTrabalhoDTO;
import com.credsystem.timesheet.entity.TimeSheetEntity;
import com.credsystem.timesheet.exeption.DataExeption;
import com.credsystem.timesheet.exeption.TimeSheetExeption;
import com.credsystem.timesheet.exeption.UsuarioExeption;

public interface TimeSheetService {

	void marcarPonto(String id, Integer ano, Integer mes, DiaDeTrabalhoDTO data) throws DataExeption;

	TimeSheetEntity buscarTimeSheet(Integer ano, Integer mes, String id) throws DataExeption;

	List<TimeSheetEntity> buscarTimeSheets(Integer ano, Integer mes, String id) throws UsuarioExeption;

	void removerPonto(String id, String timeSheetId, DiaDeTrabalhoDTO data) throws TimeSheetExeption;

}
