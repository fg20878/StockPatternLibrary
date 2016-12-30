package com.guo.stocks;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class StockPatternsApp {

	private JFrame frame;
	String stockList = "TSLA GOOG FB NVDA FDX";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockPatternsApp window = new StockPatternsApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StockPatternsApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 329, 292);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea textArea = new JTextArea();
		
		JButton btnNewButton = new JButton("Update Data");
		btnNewButton.setBounds(93, 214, 119, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				YahooStockData dataObj = new YahooStockData();
				LocalDate begin = LocalDate.of(2016, 1, 1);
				LocalDate end = LocalDate.now();
				String sList = textArea.getText();
				String[] temp;
				
				try
				{
					/* given string will be split by the argument delimiter provided. */
					temp = sList.split(" ");
					/* print substrings */
					for(int i =0; i < temp.length ; i++)
					{
						if (!temp[i].trim().equals(""))
						{
							dataObj.DownLoadHistoricPrice(temp[i], begin, end);
						}
					}
					JOptionPane.showMessageDialog(null, "Data downloaded successfully!", "Data Update", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Data Update", JOptionPane.INFORMATION_MESSAGE);
				}
			
			}
		});
		frame.getContentPane().setLayout(null);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Stock List:");
		lblNewLabel.setBounds(24, 11, 61, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 36, 245, 167);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane);
		
		// LoadStickList();
		textArea.setText(stockList);
		scrollPane.setViewportView(textArea);
	}
}
