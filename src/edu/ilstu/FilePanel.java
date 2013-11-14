package edu.ilstu;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FilePanel extends JPanel
{
	private JTextField fileField;

	private JButton locateButton;

	public FilePanel()
	{
		setLayout(new GridLayout(2, 1));
		setSize(100, 100);
		
		fileField = new JTextField("File: ");
		fileField.setEditable(false);
		
		locateButton = new JButton(
				"Choose XML File containing course information");
		
		add(fileField);
		add(locateButton);
		
		setVisible(true);
	}

	public JButton getLocateButton()
	{
		return locateButton;
	}
	
	public void setText(String str)
	{
			this.fileField.setText(str);
	}
}
