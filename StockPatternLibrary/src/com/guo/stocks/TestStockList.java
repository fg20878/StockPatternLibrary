package com.guo.stocks;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStockList {

	
		@Test
		public void stockListTest02() {
			
			System.out.println("Start StockList Test 2");
			StockList test = new StockList("GOOG");
			//System.out.println(test.toString());
			System.out.println("End StockList Test 2\n\n");
		}
		
		@Test
		public void stockListTest03() {
			
			System.out.println("Start StockList Test 3");
			StockList test = new StockList("GOOG");
			EngulfDataPoint[] pattern = test.findDownwardEngulfingPattern();
			
			for(EngulfDataPoint date: pattern)
				System.out.println(date.getDate());
			
			System.out.println("End StockList Test 3\n\n");
		}
		
		@Test
		public void stockListTest04() {
			
			System.out.println("Start StockList Test 4");
			StockList test = new StockList("GOOG");
			test.sortDateLowToHigh();
			
			StockDay[] sortTest = test.getList();
			
			for(int i = 0; i < sortTest.length - 1; i++)
				assertTrue(sortTest[i].getDateNumber() < sortTest[i + 1].getDateNumber());
			
			//System.out.println(test.toString());
			System.out.println("End StockList Test 4\n\n");
		}
		
		@Test
		public void stockListTest05() {
			
			System.out.println("Start StockList Test 5");
			StockList test = new StockList("GOOG");
			StockDay[] copy = test.getList();
			test.sortDateLowToHigh();
			
			EngulfDataPoint[] pattern1 = test.findDownwardEngulfingPattern();
			EngulfDataPoint[] pattern2 = test.findUpwardEngulfingPattern();
			
			for(int i = 0; i < pattern1.length; i++) {
				int firstIndex =test.findDateIndex(pattern1[i].getDate());
				if(firstIndex == -1) {
					System.out.println("No downward engulfing patterns were found in GOOG");
					break;
				}
				StockDay first = copy[firstIndex];
				StockDay second = copy[firstIndex + 1];
				
				
				assertTrue(first.getClosePrice() > first.getOpenPrice());
				assertTrue(second.getClosePrice() < second.getOpenPrice());
				assertTrue(first.getHighPrice() < second.getOpenPrice());
				assertTrue(first.getLowPrice() > second.getClosePrice());
			}
			
			for(int i = 0; i < pattern2.length; i++) {
				int firstIndex =test.findDateIndex(pattern2[i].getDate());
				
				if(firstIndex == -1) {
					System.out.println("No upward engulfing patterns were found in GOOG");
					break;
				}
				StockDay first = copy[firstIndex];
				StockDay second = copy[firstIndex + 1];
				
				
				assertTrue(first.getClosePrice() < first.getOpenPrice());
				assertTrue(second.getClosePrice() > second.getOpenPrice());
				assertTrue(first.getHighPrice() < second.getClosePrice());
				assertTrue(first.getLowPrice() > second.getOpenPrice());
			}
			
			System.out.println("End StockList Test 5\n\n");
		}
		
		@Test
		public void stockListTest06() {
			
			System.out.println("Start StockList Test 6");
			StockDay[] days = {new StockDay(10.0, 11.0, 15.0, 9.0, 1000000, "2017-01-05"), 
				new StockDay(16.0, 8.0, 25.0, 7.0, 1000000, "2017-01-06")};
			
			
			StockList test = new StockList(days, "Google", "GOOG");
			//test.sortDateLowToHigh();
			
			EngulfDataPoint[] down = test.findDownwardEngulfingPattern();
			
			assertTrue(down != null);
			System.out.println("End StockList Test 6\n\n");
		}

}
