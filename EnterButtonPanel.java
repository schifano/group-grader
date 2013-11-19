package edu.ilstu;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EnterButtonPanel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton back;
	
	public EnterButtonPanel()
	{
		setLayout(new FlowLayout());
		
		back = new JButton("Back");	
		
		add(back);
		
		setVisible(true);
	}
	
	public JButton getBackButton()
	{
		return back;
	}
	
}