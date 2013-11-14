package edu.ilstu;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EnterButtonPanel extends JPanel 
{
	private JButton calculate;
	private JButton back;
	
	public EnterButtonPanel()
	{
		setLayout(new FlowLayout());
		
		back = new JButton("Back");	
		calculate = new JButton("Calculate");
		
		add(back);
		add(calculate);
		
		setVisible(true);
	}
	
	public JButton getBackButton()
	{
		return back;
	}
	
	public JButton getCalculateButton()
	{
		return calculate;
	}
}