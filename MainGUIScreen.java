package edu.ilstu;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
	private JPanel coursePanel;
	private JLabel blankLabel; // Blank label for when no course is selected
	private JLabel courseLabel; // This is the variable for the display of the course name
	private JTextField courseField; // Holds the name of the course selected 
	private Course processingCourse; // course to contain information
	
	private JPanel courseGrade;
	private JLabel gradeLabel;
	private JTextField gradeField; // This is used to display the course performance
	
	
	private ButtonPanel buttonPanel; // These are the assets for the button panel
	// private FilePanel filePanel; // These are the assets for the file panel
	
	private File XMLFile; // These variable are used to create a new file
	private final JFileChooser fc = new JFileChooser(); // file chooser
	
	/**
	 * default constructor
	 */
	public MainGUIScreen()
	{
		super("Course Performance Calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 450);
		
		setLayout(new BorderLayout());
		processingCourse = Course.getInstance();
	}
	
	public void buildScreen()
	{
		// builds panels and sets up frames
		buildCoursePanel();
		buildGradePanel();
		
		
		buildButtonPanel();
		
		add(coursePanel, BorderLayout.NORTH);
		add(courseGrade, BorderLayout.CENTER);
		
		// add(filePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	
	// XML
	public File getXMLFile()
	{
		return this.XMLFile;
	}
	
	
	/**
	 * Method that builds the course panel.
	 */
	public void buildCoursePanel()
	{
		coursePanel = new JPanel();
		coursePanel.setLayout(new BorderLayout());
		
		blankLabel = new JLabel(" ");
		courseLabel = new JLabel("Course: ");
		
		String courseTitle;
		if (processingCourse != null) {
			courseTitle = processingCourse.getName();
			courseField = new JTextField(courseTitle);
		}
		else {
			courseTitle = "No course selected.";
			courseField = new JTextField(courseTitle);
		}
		courseField.setEnabled(false);

		
		coursePanel.add(blankLabel, BorderLayout.NORTH);
		coursePanel.add(courseLabel, BorderLayout.WEST);
		coursePanel.add(courseField, BorderLayout.CENTER);
	}
	
	/**
	 * Method that builds the grade panel to display the 
	 * selected course performance score as a percentage
	 */
	public void buildGradePanel() {

		courseGrade = new GradePanel();
	}
	
	
	/**
	 * builds the panel that contains all the buttons
	 */
	public void buildButtonPanel()
	{
		buttonPanel = new ButtonPanel();
		buttonPanel.getSaveButton().addActionListener(new loadExistingButtonListener());
		
		//buttonPanel = new ButtonPanel();
		buttonPanel.getCalculateButton().addActionListener(new calculateButtonListener());
		
		//buttonPanel = new ButtonPanel();
		buttonPanel.getLocateButton().addActionListener(new locateButtonListener());
	}


	/**
	 * Private class that is a button listener for loading an XML file.
	 */
	private class loadExistingButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(processingCourse != null)
			{
				processingCourse.toXML();
			}
			else
			{
				JOptionPane.showMessageDialog
			(null, "Please select an XML file containing course information first.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Private class that is a button listener for saving an XML file.
	 */
	private class calculateButtonListener implements ActionListener
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
	
	/**
	 * Private class that is a button listener for selecting an XML file.
	 */
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
				FileInputStream fin = null;
				if (XMLFile != null)
				try {
					fin = new FileInputStream(XMLFile);
					System.out.println("after XML loading");
					processingCourse = CourseXMLParser.readCourse(fin);
	
				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				}
				MainGUIScreen screen = new MainGUIScreen();
				screen.buildScreen();
			}
		}
	}
}