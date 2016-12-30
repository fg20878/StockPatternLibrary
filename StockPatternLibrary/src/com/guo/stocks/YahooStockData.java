package com.guo.stocks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;


public class YahooStockData implements IDownloadData {
	
	public Boolean DownLoadHistoricPrice(String stockSymbol, LocalDate start, LocalDate end)
	{
		int numberOfRows = 0;
		try 
		{
			if(start.isBefore(end) || start.isEqual(end))
			{
				String urlString = "http://ichart.yahoo.com/table.csv?s=" + stockSymbol + 
						"&a=" + start.getMonthValue() + "&b=" + start.getDayOfMonth() + "&c=" + start.getYear() + 
						"&d=" + end.getMonthValue() + "&e=" + end.getDayOfMonth() + "&f" + end.getYear() + "&g=d";
	            URL url = new URL(urlString);
	            URLConnection urlConn = url.openConnection();
	            InputStreamReader inputStreamReader = new InputStreamReader(urlConn.getInputStream());
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            
	            String FileName = "c:\\StockData\\" + stockSymbol + ".txt";
	            OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(FileName));
	            String line;
	            
	            while ((line = bufferedReader.readLine()) != null) {
	            	
	            	numberOfRows++;
	            	if(numberOfRows > 1)
	            	{
	            		line += "\n";
	            		os.write(line);
	            		//System.out.println(line);
	            	}
	            }
	            bufferedReader.close();
	            inputStreamReader.close();
	            os.close();
			}
	    } 
		catch (Exception e) 
		{
			e.printStackTrace();
			numberOfRows = 0;
		}
		return (numberOfRows > 1) ? true:false; // data starts in line#2 
	}
	
	public Boolean PaserLine(String line)
	{
		String[] stockData = line.split(",");
		
	    System.out.println("Stock Price [Date= " + stockData[0] + " , Open="
	      + stockData[1] + " , High=" + stockData[2] + " , Low="
	      + stockData[3] + " , Close=" + stockData[4] + "Volumn=" + stockData[5] + "]");
	    
	    Boolean ret = true;
	    for(int i=0; i<6; i++)
	    {
	    	ret &= (stockData[i].length() > 0);
	    }
	    return ret;
	}

	public void UpdateDatabase() {
		// TODO Auto-generated method stub
		
	}

}
