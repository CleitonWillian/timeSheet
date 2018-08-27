package com.credsystem.timesheet.exeption;

public class TimeSheetExeption extends Exception {

	private static final long serialVersionUID = -7275628133516271981L;
	private String mensagem;


	public TimeSheetExeption(String mensagem) {
		this.mensagem = mensagem;

	}
	
	@Override
	public String getMessage() {
		return mensagem;
	}
	
	
	

}
