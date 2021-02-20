package View;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import Controller.*;
import Model.*;

public class Console {
	// The output console, it is added to the Window
	private StringBuffer csv;
	private String menuChoice;
	private JTextArea text;
	public JScrollPane js; // This is public for the only reason that it is to be accessibla by the
							// constructor in Gui

	public void clearData() {
		text.setText("");
	}

	public void printData(StringBuffer csvData) {
		csv = csvData;
		String consoleOutput = csv.toString();
		text.append(consoleOutput);
		csv.delete(0, csv.length());
	}
	// the outputconsole for the Data we are fetching from the database

	public Console() {
		text = new JTextArea();
		text.setVisible(true);
		js = new JScrollPane(text);
		js.setVisible(true);
		text.setEditable(true);
		js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
}
