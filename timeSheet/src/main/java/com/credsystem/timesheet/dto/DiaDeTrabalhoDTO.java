package com.credsystem.timesheet.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DiaDeTrabalhoDTO.class)
public class DiaDeTrabalhoDTO {

	private Integer dia;
	private Integer hora;
	private Integer minuto;
	private String resumo;

	@JsonCreator
	public DiaDeTrabalhoDTO(
			@JsonProperty(value = "dia", required = true) Integer dia,
			@JsonProperty(value = "hora", required = true) Integer hora,
			@JsonProperty(value = "minuto", required = true) Integer minuto,
			@JsonProperty(value = "resumo", required = false) String resumo) {

		this.dia = dia;
		this.hora = hora;
		this.minuto = minuto;
		this.resumo = resumo;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	@Override
	public String toString() {
		return hora + "h:" + minuto + "m";
	}

}
