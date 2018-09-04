package com.credsystem.timesheet.entity;

import static com.credsystem.timesheet.util.DataUtil.getDiasDoMes;
import static com.credsystem.timesheet.util.DataUtil.agora;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="TimeSheet" )
public class TimeSheetEntity {

	@Id
	private String objectId;
	private List<DiaDeTrabalhoEntity> diasDeTrabalho;
	private Integer mes;
	private Integer ano;
	private String usuario;

	public static TimeSheetEntity create(String usuario) {
		var instante = agora();
		return new TimeSheetEntity(usuario,instante.getMonthValue(), instante.getYear());

	}

	public static TimeSheetEntity create(String usuario,int mes, int ano) {
		return new TimeSheetEntity(usuario,mes, ano);

	}
	
	public static TimeSheetEntity create(UsuarioEntity usuario,int mes, int ano) {
		return new TimeSheetEntity(usuario.getUsuario(),mes, ano);

	}

	public static TimeSheetEntity create(String usuario,LocalDateTime dateTime) {
		return new TimeSheetEntity(usuario,dateTime.getMonthValue(), dateTime.getYear());

	}
	
	private TimeSheetEntity() {
	}

	private TimeSheetEntity(String usuario,int mes, int ano) {
		super();
		this.usuario = usuario;
		this.mes = mes;
		this.ano = ano;
		getDiasDoMes(ano, mes).
		forEach(dia -> getDiasDeTrabalho().add(new DiaDeTrabalhoEntity(dia.atStartOfDay()))

		);

	}

	public String getObjectId() {
		return objectId;
	}
	
	public void setObjectId(String object_id) {
		this.objectId = object_id;
	}
	
	
	
	public void setDiasDeTrabalho(List<DiaDeTrabalhoEntity> diasDeTrabalho) {
		this.diasDeTrabalho = diasDeTrabalho;
	}


	public List<DiaDeTrabalhoEntity> getDiasDeTrabalho() {
		return diasDeTrabalho==null? diasDeTrabalho = new ArrayList<>():diasDeTrabalho;
	}

	public DiaDeTrabalhoEntity getDiaDeTrabalho(int dia) {
		return getDiasDeTrabalho().get(dia - 1);

	}

	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	public void setUsuario(UsuarioEntity usuario) {
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
		TimeSheetEntity other = (TimeSheetEntity) obj;
		return (ano == other.ano && mes == other.mes);
	}

}
