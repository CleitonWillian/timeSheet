package com.credsystem.timesheet.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.credsystem.timesheet.exeption.TimeSheetExeption;

@Document
public class Usuario {

	

	private String nome;
	private Double valorHora;
	@DBRef
	private List<TimeSheet> timeSheets = new ArrayList<>();
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

	public List<TimeSheet> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(List<TimeSheet> timeSheets) {
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

	public void addTimeSheet(TimeSheet timeSheet) throws TimeSheetExeption {

		if (timeSheets.contains(timeSheet))
			throw new TimeSheetExeption("timesheet ja contem na lista");
		timeSheets.add(timeSheet);
	}

	public TimeSheet getTimeSheetPorAnoMes(int ano, int mes)  {
		Optional<TimeSheet> cotemUmTimeSheet = getTimeSheets().stream()
				.filter(ts -> ts.getAno().equals(ano) && ts.getMes().equals(mes)).findFirst();
		return cotemUmTimeSheet.isPresent() ? cotemUmTimeSheet.get() : null;
	}

}
