package com.guo.stocks;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStockList {

		@Test
		public void stockListTestDownEngulfing1() {
			
			System.out.println("Start DownEngulfing1\n");
			StockDay[] days = {new StockDay(10.0, 11.0, 15.0, 9.0, 1000000, "2017-01-05"), 
				new StockDay(16.0, 8.0, 25.0, 7.0, 1000000, "2017-01-06")};
			
			
			StockList test = new StockList(days, "Google", "GOOG");
			test.sortDateLowToHigh();
			
			EngulfDataPoint[] down = test.findDownwardEngulfingPattern();
			
			assertTrue(down != null);
			System.out.println("End DownEngulfing1\n\n");
		}
		
		@Test
		public void stockListTestDownEngulfing2() {
			
			System.out.println("Start DownEngulfing2\n");
			StockDay[] days = {new StockDay(10.0, 11.0, 15.0, 9.0, 1000000, "2017-01-05"), 
				new StockDay(14.0, 8.0, 25.0, 7.0, 1000000, "2017-01-06")};
			
			
			StockList test = new StockList(days, "Google", "GOOG");
			test.sortDateLowToHigh();
			
			EngulfDataPoint[] down = test.findDownwardEngulfingPattern();
			
			assertTrue(down == null);
			System.out.println("End DownEngulfing2\n\n");
		}
		
		@Test
		public void stockListTestDownEngulfing3() {
			
			System.out.println("Start DownEngulfing3\n");
			StockDay[] days = {new StockDay(10.0, 11.0, 15.0, 9.0, 1000000, "2017-01-05"), 
				new StockDay(16.0, 10.0, 25.0, 7.0, 1000000, "2017-01-06")};
			
			
			StockList test = new StockList(days, "Google", "GOOG");
			test.sortDateLowToHigh();
			
			EngulfDataPoint[] down = test.findDownwardEngulfingPattern();
			
			assertTrue(down == null);
			System.out.println("End DownEngulfing3\n\n");
		}
		
		@Test
		public void stockListTestDownEngulfing4() {
			
			System.out.println("Start DownEngulfing4\n");
			StockDay[] days = {new StockDay(10.0, 11.0, 15.0, 9.0, 1000000, "2017-01-05"), 
				new StockDay(15.0, 8.0, 25.0, 7.0, 1000000, "2017-01-06")};
			
			
			StockList test = new StockList(days, "Google", "GOOG");
			test.sortDateLowToHigh();
			
			EngulfDataPoint[] down = test.findDownwardEngulfingPattern();
			
			assertTrue(down == null);
			System.out.println("End DownEngulfing4\n\n");
		}
		
		@Test
		public void stockListTestDownEngulfing5() {
			
			System.out.println("Start DownEngulfing5\n");
			StockDay[] days = {new StockDay(10.0, 11.0, 15.0, 9.0, 1000000, "2017-01-05"), 
				new StockDay(16.0, 9.0, 25.0, 7.0, 1000000, "2017-01-06")};
			
			
			StockList test = new StockList(days, "Google", "GOOG");
			test.sortDateLowToHigh();
			
			EngulfDataPoint[] down = test.findDownwardEngulfingPattern();
			
			assertTrue(down == null);
			System.out.println("End DownEngulfing5\n\n");
		}


}
