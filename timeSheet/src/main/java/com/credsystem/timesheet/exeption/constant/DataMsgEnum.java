package com.credsystem.timesheet.exeption.constant;

public enum DataMsgEnum {

	MES_INVALIDO("data.mes.invalido"),
	ANO_INVALIDO("data.ano.invalido");

	private final String prop;

	private DataMsgEnum(String prop) {
		this.prop = prop;
	}

	@Override
	public String toString() {
		return prop;
	}

}
