package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {
	
	@Test
	public void retornaTrueParaDataFutura( ) {
		LocalDate date = LocalDate.of(2099, 01, 01);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void retornaTrueParaDataPresente( ) {
		LocalDate date = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void retornaTrueParaDataPassada( ) {
		LocalDate date = LocalDate.of(1000, 01, 01);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
	}

}
