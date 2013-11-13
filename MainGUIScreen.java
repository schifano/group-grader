package edu.ilstu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	 * These variable are used to create a new file should the user decide
	 */
	private File XMLFile;

	/*
	 * file chooser
	 */
	private final JFileChooser fc = new JFileChooser();

	/**
	 * default constructor
	 */
	public MainGUIScreen()
	{
		/*
		 * sets up the frame
		 */
		super("Course Performance Calculator");
		setSize(600, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		/*
		 * builds the panels
		 */
		buildFilePanel();
		buildButtonPanel();

		/*
		 * adds the filepanel to the frame
		 */
		add(filePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

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
		;
	}

	private class loadExistingListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			fc.showOpenDialog(new JPanel());
		}
	}

	private class locateButtonListener extends Thread implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			synchronized (this)
			{
				fc.showOpenDialog(new JPanel());

				if (fc.getSelectedFile() != null)
				{
					XMLFile = fc.getSelectedFile();
				}
				notify();
				dispose();
			}
		}
	}

}