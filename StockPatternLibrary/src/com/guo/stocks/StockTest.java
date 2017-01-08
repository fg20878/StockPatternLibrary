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
	
	@Test
	public void StockDayTestUpDay1() {
		
		System.out.println("Start StockDay Test 1");
		
		StockDay test= new StockDay(16.0, 17.0, 18.0, 15.0, 1000000, "2017-01-05");
		assertTrue( test.isDayUp() );

		System.out.println("End StockDay Test 1\n\n");
	}
	
	@Test
	public void StockDayTestUpDay2() {
		
		System.out.println("Start StockDay Test 1");
		
		StockDay test= new StockDay(17.0, 16.0, 18.0, 15.0, 1000000, "2017-01-05");
		assertTrue( !test.isDayUp() );

		System.out.println("End StockDay Test 1\n\n");
	}
	
	@Test
	public void StockDayTestUpDay3() {
		
		System.out.println("Start StockDay Test 1");
		
		StockDay test= new StockDay(16.0, 16.0, 18.0, 15.0, 1000000, "2017-01-05");
		assertTrue( !test.isDayUp() );

		System.out.println("End StockDay Test 1\n\n");
	}
	
	@Test
	public void StockDayTestDownDay1() {
		
		System.out.println("Start StockDay Test 1");
		
		StockDay test= new StockDay(17.0, 16.0, 18.0, 15.0, 1000000, "2017-01-05");
		assertTrue( test.isDayDown() );

		System.out.println("End StockDay Test 1\n\n");
	}
	
	@Test
	public void StockDayTestDownDay2() {
		
		System.out.println("Start StockDay Test 1");
		
		StockDay test= new StockDay(16.0, 17.0, 18.0, 15.0, 1000000, "2017-01-05");
		assertTrue( !test.isDayDown() );

		System.out.println("End StockDay Test 1\n\n");
	}
	
	@Test
	public void StockDayTestDownDay3() {
		
		System.out.println("Start StockDay Test 1");
		
		StockDay test= new StockDay(16.0, 16.0, 18.0, 15.0, 1000000, "2017-01-05");
		assertTrue( !test.isDayDown() );

		System.out.println("End StockDay Test 1\n\n");
	}
}
