package com.guo.stocks;
/* Jason Guo
 * 12/28/16 - Current
 * (c) Knight Corporation LLC
 * This JUnitTest tests the functionality of the StockList and StockDay classes 
 */
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
public class StockTest {

	@Test
	public void StockDayTest01() {
		
		System.out.println("Start StockDay Test 1");
		
		double high = 10.0;
		double low = 6.0;
		double open = 7.0;
		double close = 8.0;
		int volume = 1000000;
		String date = "2017-01-06";
		StockDay test= new StockDay(open, close, high, low, volume, date);
		assertTrue( test.getClosePrice() == close &&
					test.getOpenPrice() == open &&
					test.getHighPrice() == high &&
					test.getLowPrice() == low &&
					test.getVolume() == volume &&
					test.getDate().equals(date));

		System.out.println("End StockDay Test 1\n\n");
	}
	
	//@Test
	public void StockListTest01(){
		
		System.out.println("Start StockList Test 1");
		Random ran = new Random();
		
		String[][] names = { {"Google", "GOOG"}, {"Facebook", "FB"}, {"Amazon", "AMAZ"}, {"Yahoo", "YHOO"}, {"Valve","VALV"}, 
				 {"Viacom", "VIA"}, {"Tesla", "TSLA"}, {"Dell","DELL"}, {"Fox", "FOX"}, {"Knight", "KNGT"} };

		
		for(int i = 0; i < 10; i++) {
			int highInt = ran.nextInt(100000);
			int lowInt = ran.nextInt(highInt);
			int openInt = ran.nextInt(highInt - lowInt) + lowInt;
			int closeInt = ran.nextInt(highInt - lowInt) + lowInt;
			int volume = ran.nextInt( 99000) + 1000;
			
			double high = highInt / (double) 100;
			double low = lowInt / (double) 100;
			double open = openInt / (double) 100;
			double close = closeInt / (double) 100;
			
			int year = ran.nextInt(10) + 2006;
			int month = ran.nextInt(12) + 1;
			int day = ran.nextInt(31) + 1;
			
			while( (day == 31 && month % 2 == 0) || (month == 2 && day <= 29 && year % 4 != 0) )
				day--;
			
			String date = year + "-" + month + "-" + day;
			StockDay test= new StockDay(open, close, high, low, volume, date);
			assertTrue(test.getClosePrice() == close);
			assertTrue(test.getOpenPrice() == open);
			assertTrue(test.getHighPrice() == high);
			assertTrue(test.getLowPrice() == low);
			assertTrue(test.getDate().equals(date));
		}
		
		System.out.println("End StockList Test 1\n\n");
	}
	
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
		
		StockList test = new StockList("test01");
		test.sortDateLowToHigh();
		
		EngulfDataPoint[] up = test.findUpwardEngulfingPattern();
		EngulfDataPoint[] down = test.findDownwardEngulfingPattern();
		
		assertTrue(up.length == 1);
		assertTrue(up[0].equals(new EngulfDataPoint(test.findDate("2016-12-15"), test.findDate("2016-12-16"))));
		assertTrue(down.length == 2);
		assertTrue(down[0].equals(new EngulfDataPoint(test.findDate("2016-12-19"), test.findDate("2016-12-20"))));
		assertTrue(down[1].equals(new EngulfDataPoint(test.findDate("2016-12-22"), test.findDate("2016-12-23"))));
		System.out.println("End StockList Test 6\n\n");
	}
}
