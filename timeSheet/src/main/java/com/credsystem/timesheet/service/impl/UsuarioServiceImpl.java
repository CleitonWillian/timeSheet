package com.credsystem.timesheet.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credsystem.timesheet.dto.UsuarioDTO;
import com.credsystem.timesheet.entity.TimeSheet;
import com.credsystem.timesheet.entity.Usuario;
import com.credsystem.timesheet.exeption.TimeSheetExeption;
import com.credsystem.timesheet.exeption.UsuarioExeption;
import com.credsystem.timesheet.repository.TimeSheetRepository;
import com.credsystem.timesheet.repository.UsuarioRepository;
import com.credsystem.timesheet.service.UsuarioService;
import static com.credsystem.timesheet.util.DataUtil.agora;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TimeSheetRepository timeSheetRepository;

	@Override
	public void cadastrar(UsuarioDTO usuarioDto) throws TimeSheetExeption, UsuarioExeption {

		var usuario = usuarioRepository.find(usuarioDto.getUsuario());

		if (usuario == null)
			salvaNovoUsuario(usuarioDto);
		else
			throw new UsuarioExeption(usuarioDto.getUsuario() + " ja cadastrado");

	}

	private void salvaNovoUsuario(UsuarioDTO usuarioDto) throws TimeSheetExeption {

		Usuario usuario = new Usuario();
		usuario.setUsuario(usuarioDto.getUsuario());
		usuario.setNome(usuarioDto.getNome());

		var dateTime = agora();
		var ano = dateTime.getYear();
		var mes = dateTime.getMonthValue();
		var timeSheet = usuario.getTimeSheetPorAnoMes(ano, mes);

		if (timeSheet == null)
			timeSheet = TimeSheet.create(usuario, mes, ano);

		var diaDeTrabalho = timeSheet.getDiaDeTrabalho(dateTime.getDayOfMonth());

		diaDeTrabalho.getHistorico().add(dateTime);
		usuario.addTimeSheet(timeSheet);
		timeSheet.setUsuario(usuario);

		timeSheetRepository.save(timeSheet);
		usuarioRepository.save(usuario);

	}

}
