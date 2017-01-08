package com.guo.stocks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* Jason Guo
 * 12/28/16 - current
 * (c) Knight LLC
 * 
 * This class contains a list of daily stock statistics
 * along with methods that finds patterns from the days
 */
public class StockList {

	ArrayList<StockDay> stockList;
	
	private String companyName;
	private String symbol;
	
	public StockList(String fileName)  {
		
		Scanner in;
		try {
			in = new Scanner(new File("C:\\StockData\\"+fileName+".txt"));
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found. Failed To Load");
			symbol = "load failed";
			companyName = "load failed";
			stockList = null;
			return;
		}
		
		stockList = new ArrayList<StockDay>(50);
		symbol = fileName;
		while(in.hasNext()) {
			
			String line = in.nextLine();
			int endIndex = 10;
			
			String date = line.substring(0, endIndex );
			line = line.substring(endIndex + 1);
			
			endIndex = line.indexOf(",");
			double open = Double.valueOf(line.substring(0, endIndex));
			line = line.substring(endIndex + 1);
			
			endIndex = line.indexOf(",");
			double high = Double.valueOf(line.substring(0, endIndex));
			line = line.substring(endIndex + 1);
			
			endIndex = line.indexOf(",");
			double low = Double.valueOf(line.substring(0, endIndex));
			line = line.substring(endIndex + 1);
			
			endIndex = line.indexOf(",");
			double close = Double.valueOf(line.substring(0, endIndex));
			line = line.substring(endIndex + 1);
			
			endIndex = line.indexOf(",");
			int volume = Integer.valueOf(line.substring(0, endIndex));
			
			stockList.add(new StockDay(open, close, high, low, volume, date));	
		}
		
		for( int i = 0; i <= stockList.size()/2; i++) {
			
			StockDay temp = new StockDay(stockList.get(i));
			stockList.set(i, stockList.get(stockList.size() - 1 - i));
			stockList.set(stockList.size() - 1 - i, temp);
		}
	}
	
	public StockList(StockDay[] list, String c, String s) {
		
		setCompanyName(c);
		setSymbol(s);
		stockList = new ArrayList<StockDay>(list.length);
		
		for(int i = 0; i < list.length; i++)
			stockList.add(list[i]);
				
	}
	
	public StockList(ArrayList<StockDay> n) {
		
		stockList = new ArrayList<StockDay>(n);
	}
	
	public StockList(StockDay n) {
		
		stockList = new ArrayList<StockDay>(1);
		
		stockList.add(n);
	}
	
	
	public void AddStockDay(StockDay n) {
		
		stockList.add(n);
	}
	
	public EngulfDataPoint[] findUpwardEngulfingPattern() {
		
		return findEngulfingPattern(false);
	}
	
	public EngulfDataPoint[] findDownwardEngulfingPattern() {
		
		return findEngulfingPattern(true);
	}

	private EngulfDataPoint[] findEngulfingPattern(boolean isDown) {
		
		//probably should have used a linked list...
		EngulfDataPoint[] toReturn = new EngulfDataPoint[stockList.size()/50 + 1];
		int size = 0;
		int increment =stockList.size()/50 + 1;
		
		if(stockList.size() < 2)
			return null;
		
		ArrayList<StockDay> sortedStockList = getDateLowToHigh();
		
		for( int i = 0; i < sortedStockList.size() - 1; i++) {
			
			StockDay first = sortedStockList.get(i);
			StockDay second = sortedStockList.get(i + 1);
			
			double firstLow = first.getLowPrice();
			double firstHigh = first.getHighPrice();
			
			double secondClose = second.getClosePrice();
			double secondOpen = second.getOpenPrice();
			

			
			if(isDown && first.isDayUp() && second.isDayDown() && firstLow > secondClose && firstHigh < secondOpen ||
			   !isDown && first.isDayDown() && second.isDayUp() && firstLow > secondOpen && firstHigh < secondClose) {
					
					toReturn[size] = new EngulfDataPoint(first, second);
					size++;
					
					if(size == toReturn.length) {
						
						EngulfDataPoint[] temp = new EngulfDataPoint[toReturn.length + increment];
						
						for( int j = 0; j < size; j++)
							temp[j] = toReturn[j];
						
						toReturn = temp;
					}
				}
		}
		
		EngulfDataPoint[] finalToReturn = new EngulfDataPoint[size];
		
		for( int j = 0; j < size; j++)
			finalToReturn[j] = toReturn[j];
		
		return finalToReturn;
	}
	
	
	public StockDay findDate(String date) {
		
		return stockList.get(findDateIndex(date));
	}
	
	public int findDateIndex(String date) {
		
		for(int i = 0; i < stockList.size(); i++)
			if(stockList.get(i).getDate().equals(date))
				return i;
		
		return -1;
	}
	
	/**
	 * uses good ol' selection sort
	 * changed
	 * @return
	 */
	private ArrayList<StockDay> getDateLowToHigh() {
		
		ArrayList<StockDay> toReturn = new ArrayList<StockDay>(stockList);
		for(int i = 1; i < toReturn.size(); i++ ) {
			
			int curr = i;
			while(curr != 0 && 
					toReturn.get(curr - 1).getDateNumber() > toReturn.get(curr).getDateNumber() ) {
				
				StockDay temp = toReturn.get(curr);
				toReturn.set(curr, toReturn.get(curr - 1));
				toReturn.set(curr - 1, temp);
				curr--;
			}
		}
		
		return toReturn;
	}
	
	private ArrayList<StockDay> getDateHighToLow() {
		
		ArrayList<StockDay> toReturn = new ArrayList<StockDay>(stockList);
		for(int i = 1; i < toReturn.size(); i++ ) {
			
			int curr = i;
			while(curr != 0 && 
					toReturn.get(curr - 1).getDateNumber() < toReturn.get(curr).getDateNumber() ) {
				
				StockDay temp = toReturn.get(curr);
				toReturn.set(curr, toReturn.get(curr - 1));
				toReturn.set(curr - 1, temp);
				curr--;
			}
		}
		
		return toReturn;
	}

	public void sortDateLowToHigh() {
		
		stockList = getDateLowToHigh();
	}
	
	public void sortDateHighToLow() {
		
		stockList = getDateHighToLow();
	}
	
	
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		
		
		StringBuffer toReturn = new StringBuffer("Date       Open       Close      High       Low           Volume  \n");
		if(stockList != null) {
			
			for(int i = 0; i < stockList.size() - 1;i++)
				toReturn.append(stockList.get(i).toString()+"\n");
		
			if(stockList.size() != 0)
				toReturn.append(stockList.get(stockList.size() - 1).toString());
		}
		return toReturn.toString();
	}
	
	public StockDay[] getList() {
		
		StockDay [] toReturn = new StockDay[stockList.size()];
		
		stockList.toArray(toReturn);
		
		return toReturn;
	}
}

