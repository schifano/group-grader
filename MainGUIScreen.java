package edu.ilstu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

	private static final long serialVersionUID = 1L;
	private JPanel coursePanel;
	private JLabel blankLabel; // Blank label for when no course is selected
	private JLabel courseLabel; // This is the variable for the display of the
								// course name
	private JTextField courseField; // Holds the name of the course selected
	private Course processingCourse; // course to contain information

	private JPanel courseGrade;

	private ButtonPanel buttonPanel; // These are the assets for the button
										// panel
	// private FilePanel filePanel; // These are the assets for the file panel

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

	/**
	 * This method will build the main GUI screen for the user to select the XML
	 * file which contains the course information, or bring up the screen which
	 * allows the user to enter grades, or else save the information back to the
	 * same XML file from which the information was loaded.
	 */
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

	/**
	 * Method that builds the course panel which will display the name of the
	 * course which was loaded from the XML file.
	 */
	public void buildCoursePanel()
	{
		coursePanel = new JPanel();
		coursePanel.setLayout(new BorderLayout());

		blankLabel = new JLabel(" ");
		courseLabel = new JLabel("Course: ");

		String courseTitle;
		if (processingCourse != null)
		{
			courseTitle = processingCourse.getName();
			courseField = new JTextField(courseTitle);
		} else
		{
			courseTitle = "No course selected.";
			courseField = new JTextField(courseTitle);
		}
		courseField.setEnabled(false);

		coursePanel.add(blankLabel, BorderLayout.NORTH);
		coursePanel.add(courseLabel, BorderLayout.WEST);
		coursePanel.add(courseField, BorderLayout.CENTER);
	}

	/**
	 * Method that builds the grade panel to display the selected course
	 * performance score as a percentage. It is not necessary to refresh this
	 * textField since the MainGUIScreen is created new every time that it is
	 * drawn.
	 */
	public void buildGradePanel()
	{

		courseGrade = new GradePanel();
	}

	/**
	 * builds the panel that contains all the buttons
	 */
	public void buildButtonPanel()
	{
		buttonPanel = new ButtonPanel();
		buttonPanel.getSaveButton().addActionListener(
				new loadExistingButtonListener());

		// buttonPanel = new ButtonPanel();
		buttonPanel.getCalculateButton().addActionListener(
				new calculateButtonListener());

		// buttonPanel = new ButtonPanel();
		buttonPanel.getLocateButton().addActionListener(
				new locateButtonListener());
	}

	/**
	 * Private class that is a button listener for loading an XML file. The
	 * Course which is used is first created here and then, since it is a
	 * Singleton, it will be accessible to all other classes by creating a "new"
	 * Object in that class. Hee, hee- they don't know they are getting back the
	 * same old Course that every other class has had access to.
	 */
	private class loadExistingButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			File XMLFile;
			XMLFile = null;
			XMLFile = processingCourse.getXMLFile();
			FileOutputStream fout = null;
			if (XMLFile == null)
				System.out.println("XMLFile is null");
			if (XMLFile != null)
				try
				{
					fout = new FileOutputStream(XMLFile);
					processingCourse.writeCourseToXML(fout);
				} catch (FileNotFoundException e1)
				{
					System.out.println("File not found");
				}

			else
			{
				JOptionPane
						.showMessageDialog(
								null,
								"Please select an XML file containing course information first.",
								"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Private class that is a button listener for saving an XML file. There is
	 * no need for any sort of file chooser for this button, since it will save
	 * any updates to the same XML file which was loaded at the beginning of the
	 * program.
	 */
	private class calculateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (processingCourse != null)
			{
				new EnterGradeGUIScreen();
				dispose();
			} else
			{
				JOptionPane
						.showMessageDialog(
								null,
								"Please select an XML file containing course information first.",
								"Error", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * Private class that is a button listener for selecting an XML file. Once
	 * the XML file is selected, the MainGUIScreen will be disposed and redrawn
	 * instead of refreshing each individual element. The XML file will be saved
	 * in the Course object so it will be accessible to all classes.
	 */
	private class locateButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			{
				processingCourse = Course.getInstance();
				File XMLFile;
				XMLFile = null;
				fc.showOpenDialog(new JPanel());

				if (fc.getSelectedFile() != null)
				{
					XMLFile = fc.getSelectedFile();

				}
				buttonPanel.setVisible(true);
				dispose();
				FileInputStream fin = null;
				if (XMLFile != null)
					try
					{
						fin = new FileInputStream(XMLFile);
						processingCourse = CourseXMLParser.readCourse(fin);
						processingCourse.setXMLFile(XMLFile);

					} catch (FileNotFoundException e1)
					{
						System.out.println("File not found");
					}
				MainGUIScreen screen = new MainGUIScreen();
				screen.buildScreen();
			}
		}
	}
}