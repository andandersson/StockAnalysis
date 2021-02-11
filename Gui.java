package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.DataController;

public class Gui extends JFrame{
	private JPanel actionButtons;
	private JPanel console;

	private DataButton DB;
	private ClearButton CB;
	private DataController controller;
	private JLabel startLabel;
	private JLabel stopLabel;
	


	

	public Console townInfo; //instance of class Console, for dataoutput
	public ActionBox ab;	

	public JTextField start;
	public JTextField stop;

	public Gui(DataController contr) {
		controller = contr;
		setVisible(true);
		GridBagLayout b = new GridBagLayout();
		setLayout(b);
		GridBagConstraints buttons = new GridBagConstraints();
		GridBagConstraints labels = new GridBagConstraints();
		GridBagConstraints fields = new GridBagConstraints();
		GridBagConstraints dropDown = new GridBagConstraints();
		GridBagConstraints screen = new GridBagConstraints();
		
		
		Dimension applicationSize = new Dimension(300, 500);
		setMinimumSize(applicationSize); //size for the whole app
		
		DB = new DataButton(controller, this); //instance of databutton, with reference to this class
		
		ab = new ActionBox(); //instance of the dropdownmenu
	
		CB = new ClearButton(controller, this); //instance of Cearbutton
		start = new JTextField();  //instance of Jtextfield
		stop = new JTextField();   //instance of Jtextfield
		townInfo = new Console();  //creating the console
		startLabel = new JLabel("Startdate (MM/DD/YYYY):");
		stopLabel = new JLabel("Stopdate (MM/DD/YYYY):");
	
		buttons.gridy = 0; //position for the fetchbutton
		buttons.gridx = 0;
		buttons.weightx = 0.5;
		buttons.insets = new Insets(2,8,2,8);
//		buttons.weighty = 2;
//		buttons.anchor = GridBagConstraints.WEST;
		b.setConstraints(DB, buttons);
		add(DB);  //adding fetch-button
		
		
		buttons.gridy = 0; 
		buttons.gridx = 1;
		buttons.weightx = 0.5;
		buttons.insets = new Insets(2,8,2,8);
//		buttons.weighty = 2;
//		buttons.anchor = GridBagConstraints.EAST;
		b.setConstraints(CB, buttons);
		add(CB);  //adding clearconsole button
		
		labels.weighty = 0; //adding labels
		labels.gridy = 1;
		labels.gridx = 0;
		labels.weightx = 0.5;
		labels.insets = new Insets(2,8,2,8);
//		labels.weighty = 2;
//		labels.anchor = GridBagConstraints.WEST;
		b.setConstraints(startLabel, labels);
		add(startLabel);
		
		labels.gridy = 1;  //addingl labels
		labels.gridx = 1;
		labels.weightx = 0.5;
		labels.insets = new Insets(2,8,2,8);
//		labels.weighty = 2;
//		labels.anchor = GridBagConstraints.EAST;
		b.setConstraints(stopLabel, labels);
		add(stopLabel);
		
		fields.fill=GridBagConstraints.HORIZONTAL;
		fields.gridy = 2;  //adding Jtextfields
		fields.gridx = 0;
		fields.weightx = 0.5;
		fields.insets = new Insets(2,8,2,8);
//		fields.weighty = 2;
		
		b.setConstraints(start, fields);
		add(start);
		
		fields.gridy = 2;//adding Jtextfields
		fields.gridx = 1;
		fields.weightx = 0.5;
		fields.insets = new Insets(2,8,2,8);
//		fields.weighty = 2;
		b.setConstraints(stop, fields);
		add(stop);
		
		dropDown.gridwidth = 2;
		dropDown.gridy = 3;
		dropDown.gridx = 0;
		dropDown.weightx = 0.5;
		dropDown.insets = new Insets(2,8,2,8);
//		dropDown.weighty= 2;
		
		b.setConstraints(ab, dropDown);
		add(ab);
		
//		screen.ipady = 30;
//		screen.ipadx = 240;
//		screen.weightx = 4;
		screen.fill = GridBagConstraints.HORIZONTAL;
//		screen.fill = GridBagConstraints.VERTICAL;
//		screen.anchor = GridBagConstraints.CENTER; //and the outputconsole
		screen.gridy = 4;
		screen.gridx = 0;
		screen.gridwidth = 2;
		screen.ipady = 200;
		screen.insets = new Insets(2,8,2,8);
		
		b.setConstraints(townInfo.js, screen);
		add(townInfo.js);
	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
	}

}
