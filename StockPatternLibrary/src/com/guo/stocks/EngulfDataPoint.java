/* Jason Guo
 * Worked On: 1/2/17 - present
 * (c) Knight LLC
 * 
 * This class is used to store data from dates that demonstrate the Engulfing Pattern
 */
package com.guo.stocks;
 

public class EngulfDataPoint {

	private String date;
	private double firstDiffHiLow;
	private double secondDiffCloseOpen;
	
	public EngulfDataPoint(String d, double f, double s) {
		
		date = d;
		firstDiffHiLow = f;
		secondDiffCloseOpen = s;
	}
	
	public EngulfDataPoint(StockDay first, StockDay second) {
		
		if(second.getDateNumber() < first.getDateNumber())
			throw new IllegalArgumentException("First date must be after second date");
		
		date = first.getDate();
		firstDiffHiLow = first.diffHiLow();
		secondDiffCloseOpen = second.diffCloseOpen();
	}
	
	public EngulfDataPoint(EngulfDataPoint toCopy) {
		
		if(toCopy == null) 
			throw new IllegalArgumentException("Cannot copy null parameter");
		
		setDate(toCopy.getDate());
		setFirstDiffHiLow(toCopy.getFirstDiffHiLow());
		setSecondDiffCloseOpen(toCopy.getSecondDiffCloseOpen());
	}
	
	public boolean equals(EngulfDataPoint toCompare) {
		
		return this.date.equals(toCompare.getDate()) && this.firstDiffHiLow == toCompare.getFirstDiffHiLow()
				&& this.secondDiffCloseOpen == toCompare.getSecondDiffCloseOpen();
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
	
	public double getFirstDiffHiLow() {
		return firstDiffHiLow;
	}
	
	public void setFirstDiffHiLow(double f) {
		firstDiffHiLow = f;
	}
	
	public double getSecondDiffCloseOpen() {
		return secondDiffCloseOpen;
	}
	
	public void setSecondDiffCloseOpen(double s) {
		secondDiffCloseOpen = s;
	}
}