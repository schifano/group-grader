package edu.ilstu;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EnterGradeGUIScreen extends JFrame
{
	
	private EnterButtonPanel bPanel;
	private EnterOptionPanel oPanel;
	private EnterPointsPanel pPanel;
	private JPanel iPanel;
	
	private MainGUIScreen main;
	
	public EnterGradeGUIScreen()
	{
		super("Grade Entry Form");
		setLayout(new GridLayout(0,1));
		setSize(450, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		buildEnterButtonPanel();
		buildEnterInputPanel();
		
		add(iPanel);
		add(bPanel);
		
		pack();
		setVisible(true);
	}
	
	public void buildEnterButtonPanel()
	{
		bPanel = new EnterButtonPanel();
		
		bPanel.getBackButton().addActionListener(new BackButtonListener());
		bPanel.getCalculateButton().addActionListener(new CalculateButtonListener());
	}
	
	public void buildEnterOptionPanel()
	{
		oPanel = new EnterOptionPanel();
	}
	
	public void buildEnterPointsPanel()
	{
		pPanel = new EnterPointsPanel();
	}
	
	public void buildEnterInputPanel()
	{
		buildEnterOptionPanel();
		buildEnterPointsPanel();
		
		iPanel = new JPanel();
		iPanel.setLayout(new FlowLayout());
		
		iPanel.add(oPanel);
		iPanel.add(pPanel);
	}
	
	public static void main(String [] args)
	{
		EnterGradeGUIScreen test = new EnterGradeGUIScreen();
	}
	
	private class BackButtonListener extends Thread implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			main = new MainGUIScreen();
			dispose();
		}
	}
	
	private class CalculateButtonListener extends Thread implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("YOU CALCULATED!");
		}
	}
}
