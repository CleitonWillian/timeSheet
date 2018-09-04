package com.credsystem.timesheet.dto;

import java.time.LocalDateTime;

public class ErroDTO {

	private String tipo;
	private String menssagem;
	private String detalhe;
	private LocalDateTime data;

	public ErroDTO(LocalDateTime timestamp, String message, String details, String tipo) {
		super();
		this.data = timestamp;
		this.menssagem = message;
		this.detalhe = details;
		this.tipo = tipo;
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

	public String getTipo() {
		return tipo;
	}

}
