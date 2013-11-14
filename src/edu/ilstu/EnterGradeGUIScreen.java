package edu.ilstu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EnterGradeGUIScreen extends JFrame
{
	
	private EnterButtonPanel bPanel;
	private EnterOptionPanel oPanel;
	private EnterPointsPanel pPanel;
	
	private JTextArea grades;
	private JScrollPane scroll;
	
	private JPanel iPanel;
	private Course course;
	private MainGUIScreen main;
	
	public EnterGradeGUIScreen()
	{
		super("Grade Entry Form");
		setLayout(new BorderLayout());
		setSize(450, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		grades = new JTextArea(course.getInstance(" ").toString());
		
		scroll = new JScrollPane(grades);
		
		buildEnterButtonPanel();
		buildEnterInputPanel();
		
		add(scroll, BorderLayout.NORTH);
		add(iPanel, BorderLayout.WEST);
		add(bPanel, BorderLayout.SOUTH);
		
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
