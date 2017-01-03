/* Jason Guo
 * Worked On: 1/2/17 - present
 * (c) Knight LLC
 * 
 * This class is used to store data from dates that demonstrate the Engulfing Pattern
 */
package com.guo.stocks;
 

public class EngulfDataPoint {

	private String date;
	private double firstHiLow;
	private double secondCloseOpen;
	
	public EngulfDataPoint(String d, double f, double s) {
		
		date = d;
		firstHiLow = f;
		secondCloseOpen = s;
	}
	
	public EngulfDataPoint(StockDay first, StockDay second) {
		
		if(second.getDateNumber() < first.getDateNumber())
			throw new IllegalArgumentException("First date must be after second date");
		
		date = first.getDate();
		firstHiLow = first.diffHiLow();
		secondCloseOpen = second.diffCloseOpen();
	}
	
	public EngulfDataPoint(EngulfDataPoint toCopy) {
		
		if(toCopy == null) 
			throw new IllegalArgumentException("Cannot copy null parameter");
		
		setDate(toCopy.getDate());
		setFirstHiLow(toCopy.getFirstHiLow());
		setSecondCloseOpen(toCopy.getSecondCloseOpen());
	}
	
	public String getDate() {
		return date;
	}
	
	/*used for date comparisons
	date must be in year-month-day format*/
	public int getDateNumber() {
		
		return Integer.valueOf(date.substring(0,4)) * 10000 + 
			   Integer.valueOf(date.substring(5,7)) * 100 +
			   Integer.valueOf(date.substring(8,10));
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getFirstHiLow() {
		return firstHiLow;
	}
	
	public void setFirstHiLow(double f) {
		firstHiLow = f;
	}
	
	public double getSecondCloseOpen() {
		return secondCloseOpen;
	}
	
	public void setSecondCloseOpen(double s) {
		secondCloseOpen = s;
	}
}