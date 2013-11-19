package edu.ilstu;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EnterScoreGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	

	// private JPanel iPanel;

	public EnterScoreGUI(GradeItem gradeItem) {
		super("Score Entry");
		setLayout(new BorderLayout());
		setSize(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


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