package com.credsystem.timesheet.exeption.constant;

public enum UsuarioMsgEnum {

	JA_CADASTRADO("usuario.ja.cadastrado"),
	NAO_EXISTE("usuario.nao.existe");

	private final String prop;

	private UsuarioMsgEnum(String prop) {
		this.prop = prop;
	}

	@Override
	public String toString() {
		return prop;
	}

}
