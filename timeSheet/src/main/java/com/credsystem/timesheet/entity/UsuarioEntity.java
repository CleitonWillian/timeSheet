package com.credsystem.timesheet.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.credsystem.timesheet.exeption.TimeSheetExeption;

@Document(collection="Usuario")
public class UsuarioEntity {

	private String nome;
	private Double valorHora;
	@DBRef
	private List<TimeSheetEntity> timeSheets = new ArrayList<>();
	@Id
	private String usuario;
	private String senha;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	public List<TimeSheetEntity> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(List<TimeSheetEntity> timeSheets) {
		this.timeSheets = timeSheets;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void addTimeSheet(TimeSheetEntity timeSheet) throws TimeSheetExeption {

		if (timeSheets.contains(timeSheet))
			throw TimeSheetExeption.mensagemTimeSheetExistente(timeSheet);
		timeSheets.add(timeSheet);
	}

	public TimeSheetEntity getTimeSheetPorAnoMes(int ano, int mes)  {
		Optional<TimeSheetEntity> cotemUmTimeSheet = getTimeSheets().stream()
				.filter(ts -> ts.getAno().equals(ano) && ts.getMes().equals(mes)).findFirst();
		return cotemUmTimeSheet.isPresent() ? cotemUmTimeSheet.get() : null;
	}

}
