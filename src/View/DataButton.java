package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import Controller.*;

public class DataButton extends JButton implements ActionListener {
	private DataController dc; // instance of datacontroller, for accessing the class
	private Gui win; // instance of Window

	private JLabel GetData;
	private ActionBox a;

	public DataButton(DataController con, Gui wow) {

		dc = con;
		win = wow;
		addActionListener(this);
		GetData = new JLabel("Fetch Data", JLabel.CENTER);
		this.add(GetData, JButton.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		dc.printOnConsole();
	}

}
