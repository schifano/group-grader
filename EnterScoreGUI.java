package edu.ilstu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EnterScoreGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private ScoreButtonPanel sPanel;
	private JTextField scoreEntry;
	private GradeItem item;
	private double score;

	public EnterScoreGUI(GradeItem item) 
	{
		super("Score Entry");
		this.item = item;
		setLayout(new BorderLayout());
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		scoreEntry = new JTextField();
		score = 0.0;

		buildScoreButtonPanel();
		add(new JLabel(" "), BorderLayout.NORTH);
		add(new JLabel("Score: "), BorderLayout.WEST);
		add(scoreEntry, BorderLayout.CENTER);

		add(new JLabel(" "), BorderLayout.EAST);
		add(sPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	public void buildScoreButtonPanel() {
		sPanel = new ScoreButtonPanel();
		sPanel.getActualButton().addActionListener(new ActualListener(item));
		sPanel.getEstimatedButton().addActionListener(new EstimatedListener(item));

	}

	private class ActualListener extends Thread implements ActionListener 
	{
		private GradeItem item;
		public ActualListener(GradeItem item)
		{
			this.item = item;
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			if (scoreEntry.getText() != null) 
			{
				score = Double.parseDouble(scoreEntry.getText());
				if (score != 0.0 && item != null) 
				{
					item.setEvaluatedPoints(item.getName(), score);
					EnterGradeGUIScreen enter = new EnterGradeGUIScreen();
				}
			}
		}
	}

	private class EstimatedListener extends Thread implements ActionListener 
	{
		private GradeItem item;
		public EstimatedListener(GradeItem item)
		{
			this.item = item;
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			if (scoreEntry.getText() != null) 
			{
				score = Double.parseDouble(scoreEntry.getText());
				if (score != 0.0 && item != null)
				{
					item.setEstimatedPoints(item.getName(), score);
					EnterGradeGUIScreen enter = new EnterGradeGUIScreen();
				}
			}

		}
	}
}