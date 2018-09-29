package com.credsystem.timesheet.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.TestConfiguration;

import com.credsystem.timesheet.exeption.DataExeption;
public class DataUtilTest {

	final int MAX_TIMEOUT = 30; 
	
	@Test(timeout = MAX_TIMEOUT)
	public void devePossuirDiasUteisIgualAoMesIndicado() {
		
		List<LocalDate> diaUtil = DataUtil.getDiasUteis(2018,8);
		assertEquals("quantiddade de dias uteis: ",23, diaUtil.size());
		
	}
	
	@Test(timeout = MAX_TIMEOUT)
	public void quantidadeDeDiasDoMesDeveSerIgualADataCorrespondente() {
		
		List<LocalDate> diasDoMes = DataUtil.getDiasDoMes(2018,8);
		assertEquals("quantidade de dias do mes: ",31, diasDoMes.size());
		
	}
	
	@Test(timeout = MAX_TIMEOUT)
	public void deveRetornarAnoAtualSeANoForNulo() throws DataExeption {
		assertEquals("retorno deve ser ano atual: ",Integer.valueOf(LocalDate.now().getYear()), DataUtil.validaAno(null));
	}
	
	
	@Test(timeout = MAX_TIMEOUT, expected = DataExeption.class)
	public void anoNaoPodeSerMaiorQueAnoAtual() throws DataExeption {
		DataUtil.validaAno(2020);
	}
	

}
