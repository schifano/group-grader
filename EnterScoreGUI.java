package edu.ilstu;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EnterScoreGUI extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private ScoreButtonPanel sPanel;

	public EnterScoreGUI() 
	{
		super("Score Entry");
		setLayout(new BorderLayout());
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		buildScoreButtonPanel();
		add(new JLabel(" "), BorderLayout.NORTH);
		add(new JLabel("Score: "), BorderLayout.WEST);
		add(new JTextField(), BorderLayout.CENTER);
		add(new JLabel(" "), BorderLayout.EAST);
		add(sPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	public void buildScoreButtonPanel() 
	{
		sPanel = new ScoreButtonPanel();
	}
	
	public static void main(String [] args)
	{
		EnterScoreGUI app = new EnterScoreGUI();
	}
	
	
}