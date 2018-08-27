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

public class DataUtil {

	private DataUtil() {
		throw new IllegalStateException(DataUtil.class.getSimpleName() + " é uma Classe Para Utilidade");
	}
	
	public static LocalDateTime agora() {
		LocalDateTime now = LocalDateTime.now();
		return LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfYear(), now.getHour(), now.getMinute());
		
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
		return ld.getHour()== ponto.getHour() && ld.getMinute() == ponto.getMinute();
	}
	
	public static boolean comparaPontos(DiaDeTrabalhoDTO data, LocalDateTime ld) {
		return ld.getHour()== data.getHora() && ld.getMinute() == data.getMinuto();
	}

	
	public static Integer validaMes(Integer mes) throws DataExeption {
		
		if(mes != null && (mes<1 || mes >12 || mes > LocalDateTime.now().getMonthValue()))
			throw new DataExeption("Mes invalido");
		return mes != null ? mes : LocalDateTime.now().getMonthValue();
	}

	public static Integer validaAno(Integer ano)throws DataExeption {
		if(ano != null && ano > LocalDateTime.now().getYear())
			throw new DataExeption("Ano invalido");
		return ano != null ? ano : LocalDateTime.now().getYear();
	}
}
