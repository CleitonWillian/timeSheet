package com.credsystem.timesheet.exeption;

import org.apache.commons.lang3.StringUtils;

public class PropiedadesNaoEncontradaExeption extends Exception {

	private static final long serialVersionUID = -7275628133516271981L;
	private final String propertyId;
	private String detalhes;

	public PropiedadesNaoEncontradaExeption(String propertyId) {
		super("Não foi possivel encontrar as propriedades para mensagens");
		this.propertyId = propertyId;
		setDetalhes();
	}

	public void setDetalhes() {
		if(StringUtils.isEmpty(propertyId )) {
			this.detalhes = "id da propiedade esta vazio ou nulo";
			return;
		}
		
		this.detalhes = propertyId + "não foi encontrado em src/main/resoureces";
		
				
	}

	public String getDetalhes() {
		return detalhes;
	}

}
