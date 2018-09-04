package com.credsystem.timesheet.exeption;

import com.credsystem.timesheet.exeption.constant.UsuarioMsgEnum;

public class UsuarioExeption extends CustomException {

	private static final long serialVersionUID = -7275628133516271981L;

	private UsuarioExeption(UsuarioMsgEnum prop, Object... param) {
		super(prop.toString(), param);
	}
	
	public static UsuarioExeption usuarioJaCadastrado(String usuario) {
		return new UsuarioExeption(UsuarioMsgEnum.JA_CADASTRADO, usuario);
	}
	
	public static UsuarioExeption usuarioNaoExiste(String usuario) {
		return new UsuarioExeption(UsuarioMsgEnum.NAO_EXISTE, usuario);
	}
	

}
