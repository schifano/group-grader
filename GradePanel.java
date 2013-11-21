package edu.ilstu;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class will display a text field containing the current performance in
 * the class.
 * 
 * @author John, Rachel and Corbin
 * 
 */
public class GradePanel extends JPanel
{
	private Course processingCourse;
	private static final long serialVersionUID = 1L;
	private JLabel gradeLabel;
	private JTextField gradeField;

	public GradePanel()
	{
		processingCourse = Course.getInstance();
		setLayout(new BorderLayout());

		gradeLabel = new JLabel("Percentage: ");

		double coursePerformance = 0.0;
		if (processingCourse != null)
		{
			coursePerformance = processingCourse.getPercentageTotal();
		}
		String display = "  " + coursePerformance + " % ";
		gradeField = new JTextField(display);
		gradeField.setEnabled(false);

		add(gradeLabel, BorderLayout.WEST);
		add(gradeField, BorderLayout.CENTER);
		setVisible(true);
	}

	public JTextField getGradeField()
	{
		return gradeField;
	}
}
