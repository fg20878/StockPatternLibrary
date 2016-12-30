package com.guo.stocks;

import java.time.LocalDate;

public interface IDownloadData {
	
	public Boolean DownLoadHistoricPrice(String stockSymbol, LocalDate start, LocalDate end);
	public Boolean PaserLine(String line);
	public void UpdateDatabase();
}
