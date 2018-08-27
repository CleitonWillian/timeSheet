package com.credsystem.timesheet.dto;

import java.time.LocalDateTime;

public class ErroDTO {

	private LocalDateTime data;
	private String menssagem;
	private String detalhe;
	private String erro;

	public ErroDTO(LocalDateTime timestamp, String message, String details, String erro) {
		super();
		this.data = timestamp;
		this.menssagem = message;
		this.detalhe = details;
		this.erro = erro;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getMenssagem() {
		return menssagem;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public String getErro() {
		return erro;
	}

}
