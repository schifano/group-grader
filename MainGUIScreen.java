package edu.ilstu;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Creates the initial interface for user to create a new file or load an
 * existing one
 * 
 * @author Rachel, Corbin, and John
 * 
 */
public class MainGUIScreen extends JFrame
{
	/*
	 * These are the assets for the button panel
	 */
	private ButtonPanel buttonPanel;

	/*
	 * These are the assets for the file panel
	 */
	private FilePanel filePanel;
	
	/*
	 * This is the variable for the display of the course name
	 */
	private JLabel courseName;

	/*
	 * This is used to display the course performance
	 */
	private JTextField performance;
	/*
	 * These variable are used to create a new file
	 */
	private File XMLFile;

	/*
	 * file chooser
	 */
	private final JFileChooser fc = new JFileChooser();
	
	/*
	 *    course to contain information
	 */
	private Course processingCourse;
	/**
	 * default constructor
	 */
	public MainGUIScreen()
	{
		super("Course Performance Calculator");
		setSize(600, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		processingCourse = Course.getInstance();
	}
	
	public void buildScreen()
	{
		/*
		 * builds the panels
		 */
		/*
		 * sets up the frame
		 */
		
	
		buildButtonPanel();
		buildCourseLabel();
		buildFilePanel();
		buildCoursePerformance();
		

		/*
		 * adds the filepanel to the frame
		 */
		add(filePanel, BorderLayout.CENTER);
		add(courseName, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		add(performance, BorderLayout.WEST);
		

		pack();
		setVisible(true);
	}

	/**
	 * builds the panel that contains all the buttons
	 */
	public void buildButtonPanel()
	{
		buttonPanel = new ButtonPanel();
		buttonPanel.getSaveButton().addActionListener(
				new loadExistingListener());
		buttonPanel = new ButtonPanel();
		buttonPanel.getCalculateButton().addActionListener(new calculateListener());
	}

	public File getXMLFile()
	{
		return this.XMLFile;
	}

	public void buildCourseLabel()
	{
		String courseTitle = "None selcted";
		if (processingCourse != null)
		{
			courseTitle = processingCourse.getName();
		}
		String display = "        Current course selected: " + courseTitle;
		courseName = new JLabel(display);
	}
	public void buildCoursePerformance()
	{
		double coursePerformance = 0.0;
		if (processingCourse != null)
		{
			coursePerformance = processingCourse.getPercentageTotal();			
		}
		String display = "  " + coursePerformance + " % ";
		performance = new JTextField(display);
	}
	public void buildFilePanel()
	{
		
		filePanel = new FilePanel();
		filePanel.getLocateButton().addActionListener(
				new locateButtonListener());
	}

	private class loadExistingListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			fc.showOpenDialog(new JPanel());
		}
	}

	private class calculateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(processingCourse != null)
			{
				EnterGradeGUIScreen enter = new EnterGradeGUIScreen();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog
				(null, "Please select an XML file containing course information first.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	private class locateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			{
				fc.showOpenDialog(new JPanel());

				if (fc.getSelectedFile() != null)
				{
					XMLFile = fc.getSelectedFile();
				}
				buttonPanel.setVisible(true);
				dispose();
			}
		}
	}

}