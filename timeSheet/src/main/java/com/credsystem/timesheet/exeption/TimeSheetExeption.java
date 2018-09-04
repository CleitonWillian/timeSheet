package com.credsystem.timesheet.exeption;

import com.credsystem.timesheet.entity.TimeSheetEntity;
import com.credsystem.timesheet.exeption.constant.TimeSheetMsgEnum;

public class TimeSheetExeption extends CustomException {

	private static final long serialVersionUID = -7275628133516271981L;

	private TimeSheetExeption(TimeSheetMsgEnum prop, Object... param) {
		super(prop.toString(), param);

	}

	public static TimeSheetExeption mensagemPontoNaoEncontrado(String horario, String timeSheetId) {
		return new TimeSheetExeption(TimeSheetMsgEnum.PONTO_NAO_ENCOTRADO, horario, timeSheetId);

	}

	public static TimeSheetExeption mensagemTimeSheetNaoEncontrado(String timeSheetId) {
		return new TimeSheetExeption(TimeSheetMsgEnum.NAO_ENCONTRADO, timeSheetId);

	}

	public static TimeSheetExeption mensagemTimeSheetExistente(String timeSheetId) {
		return new TimeSheetExeption(TimeSheetMsgEnum.EXISTENTE, timeSheetId);
	}

	public static TimeSheetExeption mensagemTimeSheetExistente(TimeSheetEntity timeSheet) {
		return new TimeSheetExeption(TimeSheetMsgEnum.EXISTENTE, timeSheet.getObjectId());

	}

}
