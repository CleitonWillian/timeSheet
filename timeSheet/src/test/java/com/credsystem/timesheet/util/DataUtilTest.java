package com.credsystem.timesheet.util;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public class DataUtilTest {

	@Test
	public void test() {
		
		List<LocalDate> diaUtil = DataUtil.getDiasUteis(2018,8);
		diaUtil.forEach(System.out::println);
		
	}

}
