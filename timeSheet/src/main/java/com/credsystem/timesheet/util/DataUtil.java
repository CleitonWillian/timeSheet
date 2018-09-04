package com.credsystem.timesheet.util;

import static java.util.stream.Stream.iterate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import com.credsystem.timesheet.dto.DiaDeTrabalhoDTO;
import com.credsystem.timesheet.exeption.DataExeption;
import com.credsystem.timesheet.exeption.constant.DataMsgEnum;

public class DataUtil {

	private DataUtil() {
		throw new IllegalStateException(DataUtil.class.getSimpleName() + " é uma Classe Para Utilidade");
	}
	
	public static LocalDateTime agora() {
		LocalDateTime now = LocalDateTime.now();
		return LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());
		
	}
	
	public static List<LocalDate> getDiasDoMes(int ano,int mes){
		YearMonth anoMes = YearMonth.of(ano, mes);

		return

		iterate(getPrimeiroDiaDoMes(anoMes), DataUtil::somaUmDia).limit(anoMes.lengthOfMonth())
				.collect(Collectors.toList());
	}
	
	public static List<LocalDate> getDiasUteis(int ano, int mes) {
		YearMonth anoMes = YearMonth.of(ano, mes);
		return iterate(getPrimeiroDiaDoMes(anoMes), DataUtil::somaUmDia).limit(anoMes.lengthOfMonth())
				.filter(data -> !data.getDayOfWeek().equals(DayOfWeek.SATURDAY)
						&& !data.getDayOfWeek().equals(DayOfWeek.SUNDAY))
				.collect(Collectors.toList());
	}
	
	

	private static LocalDate somaUmDia(LocalDate data) {
		return data.plusDays(1);
	}

	private static LocalDate getPrimeiroDiaDoMes(YearMonth anoMes) {
		return anoMes.atDay(1);
	}
	
	public static boolean comparaPontos(LocalDateTime ponto, LocalDateTime ld) {
		return funcaoComparOsPontos(ponto.getHour(), ponto.getMinute(), ld);
	}
	
	public static boolean comparaPontos(DiaDeTrabalhoDTO data, LocalDateTime ld) {
		return funcaoComparOsPontos(data.getHora(), data.getMinuto(), ld);
	}

	private static  boolean funcaoComparOsPontos(int hora, int minuto, LocalDateTime ld) {
		return ld.getHour()== hora && ld.getMinute() == minuto;
	}
	public static Integer validaMes(Integer mes) throws DataExeption {
		
		if(mes != null && (mes<1 || mes >12 || mes > LocalDateTime.now().getMonthValue()))
			throw new DataExeption(DataMsgEnum.MES_INVALIDO);
		return mes != null ? mes : LocalDateTime.now().getMonthValue();
	}

	public static Integer validaAno(Integer ano)throws DataExeption {
		if(ano != null && ano > LocalDateTime.now().getYear())
			throw new DataExeption(DataMsgEnum.ANO_INVALIDO);
		return ano != null ? ano : LocalDateTime.now().getYear();
	}
	
	public static String getHorario(DiaDeTrabalhoDTO diaDeTrabalho) {
		var builder = new StringBuilder();
		builder
		.append(diaDeTrabalho.getHora())
		.append(" : ")
		.append(diaDeTrabalho.getMinuto());
		return builder.toString();
	}
}
