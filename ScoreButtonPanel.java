package edu.ilstu;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ScoreButtonPanel extends JPanel
{
	private JButton estimatedButton;
	private JButton actualButton;
	
	public ScoreButtonPanel()
	{
		setLayout(new FlowLayout());
		
		estimatedButton = new JButton("Estimated Button");
		actualButton = new JButton("Actual Button");
		
		add(estimatedButton);
		add(actualButton);
		
		setVisible(true);
	}
	
	public JButton getEstimatedButton()
	{
		return estimatedButton;
	}
	
	public JButton getActualButton()
	{
		return actualButton; 
	}
	
	public static void main(String [] args)
	{
		ScoreButtonPanel test = new ScoreButtonPanel();
	}
}
