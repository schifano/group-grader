package edu.ilstu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class GradeComboBox extends JPanel implements TreeSelectionListener {
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
		Dimension minimumSize = new Dimension(150, 150);
		scrollPane.setMinimumSize(minimumSize);
		add(scrollPane);
		

	}
	
	private void createNodes(DefaultMutableTreeNode top) 
	{
		// build a temporary arrayList for Categories
		ArrayList <String>categoryList = new ArrayList<String>();
		ArrayList <String>itemList = new ArrayList<String>();	
		if (processingCourse != null)
		{
			categoryList = processingCourse.getCategoryNames();
			itemList = processingCourse.getItemNames();
		
	    DefaultMutableTreeNode category = null;
	    DefaultMutableTreeNode item = null;
	    
	    Category tempCategory = new Category("name");
	    String categoryName = "";
	    String itemName = "";
		for(int i=0; i < categoryList.size(); i++)
		{
			categoryName = categoryList.get(i);
			category = new DefaultMutableTreeNode(categoryName);  
			top.add(category);
			
			//locate items under each Category
			tempCategory = (Category) processingCourse.getItem(categoryName);
				for(int j = 0; j < itemList.size(); j ++)
				{
					itemName = itemList.get(j);
					if (tempCategory.getItem(itemName) != null)
					{
						GradeItem tempGradeItem = (GradeItem)tempCategory.getItem(itemName);
						item = new DefaultMutableTreeNode(tempGradeItem);
						category.add(item);
					}
				}
				}
		}
	}

	public void ductTape()
	{
		gPanel.getGradeField().setText("SOMETHING DIFFERENT");;
	}
	
	public void valueChanged(TreeSelectionEvent e) 
	{
		DefaultMutableTreeNode node = 
				(DefaultMutableTreeNode) courseTree.getLastSelectedPathComponent();
		if (node == null)
			return;
		Object nodeInfo = node.getUserObject();
		if (node.isLeaf(
				)) 
		{
			GradeItem tempItem = (GradeItem) nodeInfo;
			System.out.println("test");
			EnterScoreGUI grades = new EnterScoreGUI(tempItem);
			grades.addWindowListener(new GradeListener());
			
			System.out.println(processingCourse.getPercentageTotal());
			System.out.println(processingCourse);
		}
	}

	public class GradeListener implements WindowListener
	{
		@Override
		public void windowActivated(WindowEvent arg0) 
		{
			System.out.println("The window is activated");
		}

		@Override
		public void windowClosed(WindowEvent arg0) 
		{
			System.out.println("The Window is CLOSED");
			ductTape();
		}

		@Override
		public void windowClosing(WindowEvent arg0)
		{
			//System.out.println("The Window is closing");
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) 
		{
						
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) 
		{
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) 
		{
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) 
		{
			System.out.println("The Window is OPEN");
		}
	}
}

