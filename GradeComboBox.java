package edu.ilstu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * This class will add the JTree for displaying the course information. Each
 * leaf will contain an Object.
 * 
 * @author John, Corbin, and Rachel
 * 
 */
public class GradeComboBox extends JPanel implements TreeSelectionListener
{
	private static final long serialVersionUID = 1L;
	private JTree courseTree;
	private Course processingCourse;
	private JScrollPane scrollPane;
	private GradePanel gPanel;

	public GradeComboBox(GradePanel g)
	{
		super(new GridLayout(1, 0));
		processingCourse = Course.getInstance();
		String displayName = "";
		gPanel = g;

		if (processingCourse != null)
			displayName = processingCourse.getName();
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(displayName);
		createNodes(top);

		// Create a tree that allows one selection at a time.
		courseTree = new JTree(top);
		courseTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Listen for when the selection changes.
		courseTree.addTreeSelectionListener(this);
		scrollPane = new JScrollPane(courseTree);
		Dimension minimumSize = new Dimension(350, 350);
		scrollPane.setMinimumSize(minimumSize);
		add(scrollPane);

	}

	/**
	 * This method will add all the nodes from the Course with an Object on each
	 * leaf Node.
	 * 
	 * @param top
	 */
	private void createNodes(DefaultMutableTreeNode top)
	{
		// build a temporary arrayList for Categories
		ArrayList<String> categoryList = new ArrayList<String>();
		ArrayList<String> itemList = new ArrayList<String>();
		if (processingCourse != null)
		{
			categoryList = processingCourse.getCategoryNames();
			itemList = processingCourse.getItemNames();

			DefaultMutableTreeNode category = null;
			DefaultMutableTreeNode item = null;

			Category tempCategory = new Category("name");
			String categoryName = "";
			String itemName = "";
			for (int i = 0; i < categoryList.size(); i++)
			{
				categoryName = categoryList.get(i);
				category = new DefaultMutableTreeNode(categoryName);
				top.add(category);

				// locate items under each Category
				tempCategory = (Category) processingCourse
						.getItem(categoryName);
				for (int j = 0; j < itemList.size(); j++)
				{
					itemName = itemList.get(j);
					if (tempCategory.getItem(itemName) != null)
					{
						GradeItem tempGradeItem = (GradeItem) tempCategory
								.getItem(itemName);
						item = new DefaultMutableTreeNode(tempGradeItem);
						category.add(item);
					}
				}
			}
		}
	}

	/**
	 * This method will refresh the text on the Grade Panel based on the entry
	 * of different grades.
	 */
	public void refresher()
	{
		double coursePerformance = 0.0;
		if (processingCourse != null && processingCourse.getPercentageTotal() != 0)
		{
			coursePerformance = processingCourse.getPercentageScore()/ processingCourse.getPercentageTotal();
		}
		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(2);
		
		String displayText = "  " + defaultFormat.format(coursePerformance);
		
		gPanel.getGradeField().setText(displayText);
	}

	/**
	 * This listener class will detect when a Leaf is clicked and the Object
	 * attached to that Leaf will be used to update the score for that item.
	 */
	public void valueChanged(TreeSelectionEvent e)
	{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) courseTree
				.getLastSelectedPathComponent();
		if (node == null)
			return;
		Object nodeInfo = node.getUserObject();
		if (node.isLeaf())
		{
			GradeItem tempItem = (GradeItem) nodeInfo;
			EnterScoreGUI grades = new EnterScoreGUI(tempItem);
			grades.addWindowListener(new GradeListener());

		}
	}

	/**
	 * This class will detect when the EnterScoreGUI is closed, and that will
	 * refresh the course performance displayed on the EnterGradeGUIScreen.
	 * 
	 * @author John, Rachel, and Corbin
	 * 
	 */
	public class GradeListener implements WindowListener
	{
		@Override
		public void windowActivated(WindowEvent arg0)
		{
			;
		}

		@Override
		public void windowClosed(WindowEvent arg0)
		{
			refresher();
		}

		@Override
		public void windowClosing(WindowEvent arg0)
		{
			;
		}

		@Override
		public void windowDeactivated(WindowEvent arg0)
		{
			;
		}

		@Override
		public void windowDeiconified(WindowEvent arg0)
		{
			;
		}

		@Override
		public void windowIconified(WindowEvent arg0)
		{
			;
		}

		@Override
		public void windowOpened(WindowEvent arg0)
		{
			;
		}
	}
}
