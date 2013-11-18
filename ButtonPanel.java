package edu.ilstu;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton calculateButton;
	private JButton saveButton;
	private JButton locateButton;
	
	public ButtonPanel()
	{
		setLayout(new FlowLayout());
		
		locateButton = new JButton("Load XML");
		add(locateButton);
		
		calculateButton = new JButton("Update Grades");
		add(calculateButton);
		
		saveButton = new JButton("Save XML");
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
	
	public JButton getLocateButton() 
	{
		return locateButton;
	}
	
}
