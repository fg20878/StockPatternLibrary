package com.guo.stocks;

public class StockDataFile 
{
	private String StockFileName;
	private String StockListFileName = "c:\\stockdara\\list.txt";
	
	public String getStockFileName()
	{
		return StockFileName;
	}
		
	public void Set(String stockSymbol)
	{
		StockFileName = "c:\\StockData\\" + stockSymbol + ".txt";
	}
	
	public String getStockListFileName()
	{
		return StockListFileName;
	}
	
	public void getStockListFileName(String fileName)
	{
		StockListFileName = fileName;
	}
}
