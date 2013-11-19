package edu.ilstu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EnterGradeGUIScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private EnterButtonPanel bPanel;
	private Course processingCourse;
	private GradePanel coursePerformance;
	private GradeComboBox grades;

	// private JPanel iPanel;
	private MainGUIScreen main;

	public EnterGradeGUIScreen() {
		super("Grade Entry Form");
		setLayout(new BorderLayout());
		setSize(450, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		processingCourse = Course.getInstance();

		grades = new GradeComboBox();

		buildEnterButtonPanel();
		buildGradeDisplayPanel();

		add(grades, BorderLayout.NORTH);
		add(coursePerformance, BorderLayout.CENTER);
		add(bPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	public void buildEnterButtonPanel() {
		bPanel = new EnterButtonPanel();

		bPanel.getBackButton().addActionListener(new BackButtonListener());
	}

	public void buildGradeDisplayPanel() {
		coursePerformance = new GradePanel();
	}

	private class BackButtonListener extends Thread implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			main = new MainGUIScreen();
			main.buildScreen();
		}
	}

}
