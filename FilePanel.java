package edu.ilstu;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FilePanel extends JPanel
{
	private JPanel fileNamePanel;
	private JLabel fileLabel;
	private JLabel blankLabel;

	private JTextField fileField;

	private JButton locateButton;

	public FilePanel()
	{
		setLayout(new GridLayout(0, 1));

		blankLabel = new JLabel(" ");
		locateButton = new JButton(
				"Choose XML File containing course information");

		fileNamePanel = new JPanel();
		fileNamePanel.setLayout(new FlowLayout());
		fileLabel = new JLabel("Current File: ");
		fileField = new JTextField("NO FILE");

		fileNamePanel.add(fileLabel);
		fileNamePanel.add(fileField);

		add(locateButton);
		add(fileNamePanel);
		setVisible(true);
	}

	public JButton getLocateButton()
	{
		return locateButton;
	}
}
