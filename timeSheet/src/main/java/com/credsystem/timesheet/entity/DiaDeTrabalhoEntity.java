package com.credsystem.timesheet.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.credsystem.timesheet.util.DataUtil;

public class DiaDeTrabalhoEntity {

	private Integer dia;
	private List<LocalDateTime> historico;
	private String resumo;

	public DiaDeTrabalhoEntity() {
	}

	public DiaDeTrabalhoEntity(LocalDateTime dia) {
		this.dia = dia.getDayOfMonth();

	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}
	
	public void setDia(LocalDateTime dia) {
		this.dia = dia.getDayOfMonth();
	}

	public List<LocalDateTime> getHistorico() {
		return historico == null ? historico = new ArrayList<>() : historico;
	}

	public void setHistorico(List<LocalDateTime> historico) {
		this.historico = historico;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public void addPontoNesseHistorico(LocalDateTime ponto) {
		getHistorico().removeIf(ld -> DataUtil.comparaPontos(ponto, ld));
		getHistorico().add(ponto);

	}

}
