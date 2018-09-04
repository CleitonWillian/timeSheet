package com.credsystem.timesheet.exeption.constant;

public enum TimeSheetMsgEnum {

	PONTO_NAO_ENCOTRADO("timesheet.ponto.nao.encontrado"),
	NAO_ENCONTRADO("timesheet.nao.encontrado"),
	EXISTENTE("timeshhet.existente");

	private final String prop;

	private TimeSheetMsgEnum(String prop) {
		this.prop = prop;
	}

	@Override
	public String toString() {
		return prop;
	}

}
