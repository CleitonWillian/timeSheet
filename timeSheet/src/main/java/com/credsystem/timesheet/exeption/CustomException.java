package com.credsystem.timesheet.exeption;

import java.io.Serializable;

public class CustomException  extends Exception implements Serializable {
	
	private static final long serialVersionUID = -7265283428723831692L;
	
	private final String prop;
	private final Object[] param;
	
	public CustomException(String prop, Object... param ) {
		this.prop = prop;
		this.param = param;

	}


	public String getProp() {
		return prop;
	}


	public Object[] getParam() {
		return param;
	}
	
	
	
	
	
	
	
	

}
