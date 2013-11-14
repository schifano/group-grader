package edu.ilstu;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnterPointsPanel extends JPanel 
{
	private JLabel pointsLabel;
	private JLabel weightLabel;
	private JLabel outofLabel;
	
	private JTextField pointsField;
	private JTextField weightField;
	private JTextField outofField;
	
	public EnterPointsPanel()
	{
		setLayout(new GridLayout(1,4));
		pointsLabel = new JLabel("Points: ");
		outofLabel = new JLabel("Out of: ");
		weightLabel = new JLabel("Weight: ");
		
		pointsField = new JTextField();
		outofField  = new JTextField();
		weightField = new JTextField();
		
		add(pointsLabel);
		add(pointsField);
	
		add(outofLabel);
		add(outofField);
		
		add(weightLabel);
		add(weightField);
		
		setVisible(true);
	}
	
}
