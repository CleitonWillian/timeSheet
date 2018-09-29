package com.credsystem.timesheet.util;

import java.io.Serializable;

import javax.el.PropertyNotFoundException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class MsgProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger lOG = LogManager.getLogger(MsgProperties.class);

	@Autowired
	private Environment environment;

	public void init() throws Exception {
		if (this.environment.containsProperty("msgProperties")) {
			lOG.info("Propriedades de mensagens carregada..");
		} else {
			lOG.error("Não foi possivel carregar propriedades da menssagens..");
			throw new PropertyNotFoundException("Não foi possivel encontrar propriedades da menssagen");
		}
	}

	public String getMenssagem(String prop, Object... param) {
		try {

			var msg = this.environment.getProperty(prop);
			
			for (int i = 0; i < param.length; i++)
				msg = msg.replace("{" + i + "}", (String) param[i]);

			return msg;
		} catch (Exception e) {
			lOG.log(Level.ERROR, "Erro com biding da propriedade de menssagens", e);
		}
		return null;
	}
}
