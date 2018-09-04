package com.credsystem.timesheet.exeption;

import com.credsystem.timesheet.exeption.constant.DataMsgEnum;

public class DataExeption extends CustomException {

	private static final long serialVersionUID = -7275628133516271981L;

	public DataExeption(DataMsgEnum prop, Object... param) {
		super(prop.toString(), param);
	}

}
