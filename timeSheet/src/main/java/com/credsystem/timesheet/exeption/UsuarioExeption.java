package com.credsystem.timesheet.exeption;

public class UsuarioExeption extends Exception {

	private static final long serialVersionUID = -7275628133516271981L;
	private String mensagem ;

	public UsuarioExeption() {
		super();
	}

	public UsuarioExeption(String mensagem) {
		this.mensagem = mensagem;

	}
	
	@Override
	public String getMessage() {
		return mensagem;
	}
	
	
	

}
