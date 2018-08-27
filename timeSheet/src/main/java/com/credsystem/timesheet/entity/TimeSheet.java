package com.credsystem.timesheet.entity;

import static com.credsystem.timesheet.util.DataUtil.getDiasDoMes;
import static com.credsystem.timesheet.util.DataUtil.agora;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TimeSheet {

	@Id
	private String objectId;
	private List<DiaDeTrabalho> diasDeTrabalho;
	private Integer mes;
	private Integer ano;
	private String usuario;

	public static TimeSheet create(String usuario) {
		var instante = agora();
		return new TimeSheet(usuario,instante.getMonthValue(), instante.getYear());

	}

	public static TimeSheet create(String usuario,int mes, int ano) {
		return new TimeSheet(usuario,mes, ano);

	}
	
	public static TimeSheet create(Usuario usuario,int mes, int ano) {
		return new TimeSheet(usuario.getUsuario(),mes, ano);

	}

	public static TimeSheet create(String usuario,LocalDateTime dateTime) {
		return new TimeSheet(usuario,dateTime.getMonthValue(), dateTime.getYear());

	}
	
	private TimeSheet() {
	}

	private TimeSheet(String usuario,int mes, int ano) {
		super();
		this.usuario = usuario;
		this.mes = mes;
		this.ano = ano;
		getDiasDoMes(ano, mes).
		forEach(dia -> getDiasDeTrabalho().add(new DiaDeTrabalho(dia.atStartOfDay()))

		);

	}

	public String getObjectId() {
		return objectId;
	}
	
	public void setObjectId(String object_id) {
		this.objectId = object_id;
	}
	
	
	
	public void setDiasDeTrabalho(List<DiaDeTrabalho> diasDeTrabalho) {
		this.diasDeTrabalho = diasDeTrabalho;
	}


	public List<DiaDeTrabalho> getDiasDeTrabalho() {
		return diasDeTrabalho==null? diasDeTrabalho = new ArrayList<>():diasDeTrabalho;
	}

	public DiaDeTrabalho getDiaDeTrabalho(int dia) {
		return getDiasDeTrabalho().get(dia - 1);

	}

	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario.getUsuario();
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + mes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSheet other = (TimeSheet) obj;
		return (ano == other.ano && mes == other.mes);
	}

}
