package edu.ilstu;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EnterButtonPanel extends JPanel
{
	/**
	 * This class will provide a graphical component which will display only a
	 * BACK button for the user.
	 */
	private static final long serialVersionUID = 1L;

	private JButton back;

	/**
	 * This method will draw the back button.
	 */
	public EnterButtonPanel()
	{
		setLayout(new FlowLayout());

		back = new JButton("Back");

		add(back);

		setVisible(true);
	}

	/**
	 * This class will return the button object so that Object listeners can be
	 * added to the button.
	 * 
	 * @return
	 */
	public JButton getBackButton()
	{
		return back;
	}

}