package com.guo.stocks;

import static org.junit.Assert.*;
import java.time.LocalDate;

import org.junit.Test;

public class StockDataUnitTest {

	@Test
	public void testGoogle() {
		LocalDate begin = LocalDate.of(2016, 5, 1);
		LocalDate end = LocalDate.of(2016, 12, 1);
		
		assertTrue(new YahooStockData().DownLoadHistoricPrice("GOOG", begin, end) == true);
	}

	@Test
	public void testTesla() {
		LocalDate begin = LocalDate.of(2016, 5, 1);
		LocalDate end = LocalDate.of(2016, 12, 1);
		
		assertTrue(new YahooStockData().DownLoadHistoricPrice("TSLA",begin, end) == true);
	}
	
	@Test
	public void testFacebook() {
		LocalDate begin = LocalDate.of(2016, 5, 1);
		LocalDate end = LocalDate.of(2016, 12, 1);
		assertTrue(new YahooStockData().DownLoadHistoricPrice("FB", begin, end) == true);
	}
	
	@Test
	public void testApple() {
		LocalDate begin = LocalDate.of(2016, 5, 1);
		LocalDate end = LocalDate.of(2016, 12, 1);
		assertTrue(new YahooStockData().DownLoadHistoricPrice("AAPL", begin, end) == true);
	}
	
	@Test
	public void testGoofie() {
		LocalDate begin = LocalDate.of(2016, 5, 1);
		LocalDate end = LocalDate.of(2016, 12, 1);
		assertTrue(new YahooStockData().DownLoadHistoricPrice("Goofie", begin, end) == false);
	}
	
	@Test
	public void testInvalidDateInterval() {
		LocalDate begin = LocalDate.of(2016, 12, 1);
		LocalDate end = LocalDate.of(2016, 5, 1);
		assertTrue(new YahooStockData().DownLoadHistoricPrice("AAPL", begin, end) == false);
	}
	
	@Test
	public void testParseLine() {
		assertTrue(new YahooStockData().PaserLine("2016-12-08,110.860001,112.43,110.599998,112.120003,26818500,112.120003") == true);
	}

}
