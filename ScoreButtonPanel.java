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
		
		actualButton = new JButton("Actual score");
		estimatedButton = new JButton("Estimated score");
		
		add(actualButton);
		add(estimatedButton);
				
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
	
	
}
