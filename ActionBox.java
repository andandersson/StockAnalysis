package View;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import Model.*;

public class ActionBox extends JComboBox implements ActionListener{
	
	/*
	 * 
	 * This is a vlass for creating the JCombobox dropdownmenu on the application
	 */

	final private String[] info;
	JComboBox<String> OutputBox;
	
	public String[] getInfo() {
		return this.info;
	}
	
	public ActionBox(){
		
//		my = new DataFetcher();
		info = new String [] {"-----------------------------------------", 
				"Amount of days with increasing stockprices", 
				"Highest trading volume, and most significant price changes", 
				"Days with best opening price"};
		for(String i: info) {
			addItem(i);
		}
		addActionListener(this);
	}
	
}

