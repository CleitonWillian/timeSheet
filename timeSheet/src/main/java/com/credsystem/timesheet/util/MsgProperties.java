package com.credsystem.timesheet.util;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class MsgProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger lOG = LogManager.getLogger(MsgProperties.class);
	
	@Autowired
	private Environment environment;

	public void init() {
		if (this.environment.containsProperty("msgProperties")) {
			lOG.info("Propriedades de mensagens carregada..");
		} else {
			lOG.error("Não foi possivel carregar propriedades da menssagens ..");
		}
	}


	
	public String getMenssagem(String prop,Object... param) {
		try {
		
		String msg = this.environment.getProperty(prop);
		
		for (int i = 0; i < param.length; i++) 
			msg = msg.replace("{"+ i +"}", (String)param[i]);
		
		return msg;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
