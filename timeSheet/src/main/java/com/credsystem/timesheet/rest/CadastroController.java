package com.credsystem.timesheet.rest;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beews.webstart.anntotion.Post;
import com.beews.webstart.rest.Resposta;
import com.credsystem.timesheet.dto.UsuarioDTO;
import com.credsystem.timesheet.exeption.CustomException;
import com.credsystem.timesheet.service.UsuarioService;

@Singleton
@Controller
@RequestMapping(value="login")
public class CadastroController {

	@Autowired
	UsuarioService usuarioService;
	
	@Post("cadastro")
	public ResponseEntity<Resposta> cadastrarUsuario(@RequestBody UsuarioDTO usuario) throws CustomException  {
		usuarioService.cadastrar(usuario);
		return new ResponseEntity<>(new Resposta(usuario.getUsuario()).salvar(), HttpStatus.ACCEPTED);

	}
	
	
}
