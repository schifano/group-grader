package edu.ilstu;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{
	private JButton calculateButton;
	private JButton saveButton;
	
	public ButtonPanel()
	{
		setLayout(new FlowLayout());

		calculateButton = new JButton("Calculate Performance in Course");

		saveButton = new JButton("Save Changes to XML file");

		add(calculateButton);
		add(saveButton);

		setVisible(false);
	}

	public JButton getSaveButton()
	{
		return saveButton;
	}
	
	public JButton getCalculateButton()
	{
		return calculateButton;
	}
}
