package com.credsystem.timesheet.service;

import com.credsystem.timesheet.dto.UsuarioDTO;
import com.credsystem.timesheet.exeption.TimeSheetExeption;
import com.credsystem.timesheet.exeption.UsuarioExeption;

public interface UsuarioService {


	void cadastrar(UsuarioDTO usuario) throws TimeSheetExeption , UsuarioExeption;

}
