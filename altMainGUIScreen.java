package edu.ilstu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class altMainGUIScreen extends JFrame
{
	private ButtonPanel buttonPanel;
	private FilePanel filePanel;
	private EnterGradeGUIScreen enterGrade;
	
	private File XMLFile;
	private final JFileChooser fc = new JFileChooser();
	private Course processingCourse;
	private FileInputStream fin;
	
	
	public altMainGUIScreen()
	{
		super("alt Course Performance Calculator");
		setSize(600, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(0,1));
		
		buildFilePanel();
		buildButtonPanel();
		
		add(filePanel);
		add(buttonPanel);
		
		pack();
		setVisible(true);
	}
	
	public void buildDisplayPanel()
	{
		
	}

	public void buildButtonPanel()
	{
		buttonPanel = new ButtonPanel();
		//buttonPanel.getSaveButton().addActionListener();
		buttonPanel.getCalculateButton().addActionListener(new calculateListener());
	}

	public File getXMLFile()
	{
		return this.XMLFile;
	}

	public void buildFilePanel()
	{
		filePanel = new FilePanel();
		filePanel.getLocateButton().addActionListener(
				new locateButtonListener());
	}
	
	private class calculateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(XMLFile != null)
			{
				enterGrade = new EnterGradeGUIScreen();
				dispose();
			}
		}
	}
	
	private class locateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			fc.showOpenDialog(new JPanel());

			if (fc.getSelectedFile() != null)
				{
					XMLFile = fc.getSelectedFile();
					filePanel.setText("File: " + XMLFile.getName());
					buttonPanel.setVisible(true);
				}		
			try 
			{	
				fin = new FileInputStream(getXMLFile());
				processingCourse = CourseXMLParser.readCourse(fin);
			} 
			catch (FileNotFoundException e1)
			{
				System.out.println("File not found");
			}
		}
	}
	
	public static void main(String [] args)
	{
		altMainGUIScreen test = new altMainGUIScreen();
	}
}
