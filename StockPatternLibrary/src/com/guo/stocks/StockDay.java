package com.guo.stocks;
/* Jason Guo
 * 12/28/16 - present
 * (c) Knight Corporation LLC
 * This class keeps track of the data from one day of a stock
 */
public class StockDay {


	private double openPrice;
	private double closePrice;
	private double highPrice;
	private double lowPrice;
	private int volume;
	private String date;
	private final int LENGTH_DOUBLE = 10;
	
	public StockDay( double open, double close, double high, double low, int vol, String d) {
		
		setOpenPrice(open);
		setClosePrice(close);
		setHighPrice(high);
		setLowPrice(low);
		setVolume(vol);
		date = d;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	/*used for date comparisons
	/date must be in year-month-day format 
	but no need for leftside zeros*/
	public int getDateNumber() {
		
		return Integer.valueOf(date.substring(0,4)) * 10000 + 
			   Integer.valueOf(date.substring(5,7)) * 100 +
			   Integer.valueOf(date.substring(8,10));
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public String toString() {
		
		String open = String.valueOf(openPrice);
		open+= spaces(LENGTH_DOUBLE - open.length());
		
		String close = String.valueOf(closePrice);
		close += spaces(LENGTH_DOUBLE - close.length());
		
		String high = String.valueOf(highPrice);
		high += spaces(LENGTH_DOUBLE - high.length());
		
		String low = String.valueOf(lowPrice);
		low += spaces(LENGTH_DOUBLE - low.length());
		
		String vol = String.valueOf(volume);
		
		String toReturn = open+" "+close+" "+high+" "+low+" "+
						  spaces(LENGTH_DOUBLE - vol.length())+vol+" "+date; 
		
		return toReturn;
	}
	
	/**
	 * returns num number of spaces in a String
	 * @param num
	 * @return
	 */
	private String spaces(int num){
		
		StringBuilder toReturn = new StringBuilder("");
		
		for(int i = 0; i < num; i++)
			toReturn.append(" ");
		
		return toReturn.toString();
	}
	
}
