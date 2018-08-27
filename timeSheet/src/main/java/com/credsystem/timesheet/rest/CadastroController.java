package com.credsystem.timesheet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beews.webstart.anntotion.Post;
import com.credsystem.timesheet.dto.UsuarioDTO;
import com.credsystem.timesheet.exeption.TimeSheetExeption;
import com.credsystem.timesheet.exeption.UsuarioExeption;
import com.credsystem.timesheet.service.UsuarioService;

@Controller
@RequestMapping(value="login")
public class CadastroController {

	@Autowired
	UsuarioService usuarioService;
	
	@Post
	public ResponseEntity<HttpStatus> cadastrarUsuario(@RequestBody UsuarioDTO usuario) throws TimeSheetExeption, UsuarioExeption {
		usuarioService.cadastrar(usuario);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	
}
