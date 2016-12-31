package com.guo.stocks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* Jason Guo
 * 12/28/16 - current
 * Knight Corporation LLC
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
	}
	
	public StockList(StockDay[] n, String c, String s) {
		
		setCompanyName(c);
		setSymbol(s);
		stockList = new ArrayList<StockDay>(n.length);
		
		for(int i = 0; i < stockList.size(); i++)
			stockList.add(n[i]);
				
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
	
	public String[] FindEngulfingPattern() {
		
		//probably should have used a linked list...
		int increment =stockList.size()/50 + 1;
		String[] toReturn = new String[stockList.size()/50 + 1];
		int size = 0;
		
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
			
			if(secondOpen < secondClose && secondClose > firstHigh  && secondOpen < firstLow) {
					
					toReturn[size] = second.getDate();
					size++;
					
					if(size == toReturn.length) {
						
						String[] temp = new String[toReturn.length + increment];
						
						for( int j = 0; j < size; j++)
							temp[j] = toReturn[j];
						
						toReturn = temp;
					}
				}
		}
		
		String[] finalToReturn = new String[size];
		
		for( int j = 0; j < size; j++)
			finalToReturn[j] = toReturn[j];
		
		return finalToReturn;
	}

	/**
	 * uses good ol' selection sort
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
		
		StringBuffer toReturn = new StringBuffer("Open       Close      High       Low           Volume  Date\n");
		for(int i = 0; i < stockList.size();i++) {
			toReturn.append(stockList.get(i));
			
			//makes things prettier, but harder for myself
			if(i != stockList.size() - 1)
				toReturn.append("\n");
				
		}
		
		return toReturn.toString();
	}
	
	public ArrayList<StockDay> getList() {
		
		return stockList;
	}
}

