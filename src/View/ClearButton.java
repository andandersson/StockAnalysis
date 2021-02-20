package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import Controller.*;

public class ClearButton extends JButton implements ActionListener {
	// this is class is for clearing the console.
	private DataController dc; // instance of datacontroller, for accessing the class
	private Gui win;
	private JLabel ClearData;
	private ActionBox a;

	public ClearButton(DataController con, Gui wow) {
		dc = con;
		win = wow;
		addActionListener(this);
		ClearData = new JLabel("Clear Console", JLabel.CENTER);
		this.add(ClearData, JButton.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dc.clearConsole();
	}
}
