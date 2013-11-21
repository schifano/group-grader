package edu.ilstu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * This class will create the screen which allows the user to enter individual
 * scores on different GradeItems
 * 
 * @author John, Rachel, Corbin
 * 
 */
public class EnterGradeGUIScreen extends JFrame
{
	private static final long serialVersionUID = 1L;
	private EnterButtonPanel bPanel;
	private GradePanel coursePerformance;
	private GradeComboBox grades;

	/**
	 * This will create the frame which will contain all the graphics components
	 * for the screen.
	 */
	public EnterGradeGUIScreen()
	{
		super("Grade Entry Form");
		setLayout(new BorderLayout());
		setSize(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		buildEnterButtonPanel();
		buildGradeDisplayPanel();
		buildGradeComboBox();

		add(grades, BorderLayout.NORTH);
		add(coursePerformance, BorderLayout.CENTER);
		add(bPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	/**
	 * This proc will create the Back button at the bottom of the screen and add
	 * an ActionListener for the button.
	 */
	public void buildEnterButtonPanel()
	{
		bPanel = new EnterButtonPanel();

		bPanel.getBackButton().addActionListener(new BackButtonListener());
	}

	/**
	 * This proc will create the JTree which will contain all the Course
	 * information displayed for the user.
	 */
	public void buildGradeComboBox()
	{
		grades = new GradeComboBox(coursePerformance);
	}

	/**
	 * This will build the panel which shows the user his performance in the
	 * course.
	 */
	public void buildGradeDisplayPanel()
	{
		coursePerformance = new GradePanel();
	}

	/**
	 * This class listens for for the user to press the back button. When they
	 * press that button, the Main GUI screen is drawn again.
	 * 
	 * @author John, Rachel and Corbin
	 * 
	 */
	private class BackButtonListener extends Thread implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dispose();
			MainGUIScreen main = new MainGUIScreen();
			main.buildScreen();
		}
	}

}
