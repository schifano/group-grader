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

		calculateButton = new JButton("Review and enter grades in course");

		saveButton = new JButton("Save Changes to XML file");

		add(calculateButton);
		add(saveButton);

		setVisible(true);
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
