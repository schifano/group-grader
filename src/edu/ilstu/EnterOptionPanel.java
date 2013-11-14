package edu.ilstu;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class EnterOptionPanel extends JPanel 
{
	private ButtonGroup optionGroup;
	private JRadioButton option1;
	private JRadioButton option2;
	private JRadioButton option3;
	private JRadioButton option4;
	
	public EnterOptionPanel()
	{
		setLayout(new GridLayout(0,1));
		
		buildButtonGroup();
		add(option1);
		add(option2);
		add(option3);
		add(option4);
		
		setVisible(true);
	}
	
	public void buildButtonGroup()
	{
		optionGroup = new ButtonGroup();
		option1 = new JRadioButton("Exams");
		option2 = new JRadioButton("Individual Assignments");
		option3 = new JRadioButton("Group Projects");
		option4 = new JRadioButton("Homework, Quizes, Participation");
		
		optionGroup.add(option1);
		optionGroup.add(option2);
		optionGroup.add(option3);
		optionGroup.add(option4);
	}
	
}
