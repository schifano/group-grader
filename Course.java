package edu.ilstu;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that represents the "grades" part of one Course for one student This
 * class maintains the gradebook as a Category object. Modified for this class
 * to be a Singleton so that any class can access the same object anywhere in
 * processing without having to pass the address of that object from class.
 * 
 * @author Amit Shesh, John, Rachel, Corbin
 * 
 * 
 */
public class Course
{
	private Category gradebook;
	private ArrayList<String> gradeItemNames;
	private ArrayList<String> categoryNames;
	private static Course INSTANCE;
	private File XMLFile;

	/**
	 * Default constructor, simply creates the Category object
	 */
	private Course(String name)
	{
		gradebook = new Category(name);
		gradeItemNames = new ArrayList<String>();
		categoryNames = new ArrayList<String>();
	}

	/**
	 * This will create the initial instance of the Course by passing in the
	 * name to the private constructor.
	 * 
	 * @param name
	 *            of the Course which will store the information
	 * @return Instance of Course Object
	 */
	public static Course getInstance(String name)
	{
		INSTANCE = new Course(name);
		return INSTANCE;
	}

	/**
	 * This constructor will always pass back the first instance of the Course
	 * which was first created by the constructor where the Course name was
	 * passed in.
	 * 
	 * @return instance of Course Object
	 */
	public static Course getInstance()
	{
		return INSTANCE;
	}

	public String getName()
	{
		return gradebook.getName();
	}

	/**
	 * Add a sub-category to the category within this gradebook named
	 * "categoryName". If an sub-category must be directly added to this
	 * gradebook, then the categoryName should be whatever name was given to
	 * this course
	 * 
	 * @param categoryName
	 * @param item
	 */
	public void addCategory(String categoryName, Category cat)
	{
		gradebook.addItem(categoryName, cat);
		categoryNames.add(cat.getName());
	}

	public ArrayList<String> getCategoryNames()
	{
		return categoryNames;
	}

	public ArrayList<String> getItemNames()
	{
		return gradeItemNames;
	}

	/**
	 * Add an item to the category within this gradebook named "categoryName".
	 * If an item must be directly added to this gradebook, then the
	 * categoryName should be whatever name was given to this course
	 * 
	 * @param categoryName
	 * @param item
	 */
	public void addItem(String categoryName, GradeItem item)
	{
		gradebook.addItem(categoryName, item);
		gradeItemNames.add(item.getName());
	}

	public void setXMLFile(File XMLFile)
	{
		this.XMLFile = XMLFile;
	}

	public GradableItem getItem(String name)
	{
		return gradebook.getItem(name);
	}

	/**
	 * Set the score of a particular grade item (whose name is "itemName") to
	 * "points"
	 * 
	 * @param itemName
	 * @param points
	 */
	public void setEvaluatedPoints(String itemName, double points)
	{
		gradebook.setEvaluatedPoints(itemName, points);
	}

	/**
	 * Set the estimated score of a particular grade item (whose name is
	 * "itemName") to "points"
	 * 
	 * @param itemName
	 * @param points
	 */
	public void setEstimatedPoints(String itemName, double points)
	{
		gradebook.setEstimatedPoints(itemName, points);
	}

	/**
	 * Get the percentage of this course that has been graded up to this point
	 * 
	 * @return
	 */
	public double getPercentageTotal()
	{

		return gradebook.getPercentageTotal();
	}

	/**
	 * Get the percentage evaluated score of this student in this course
	 * 
	 * @return
	 */
	public double getPercentageScore()
	{

		return gradebook.getPercentageScore();
	}

	public String toString()
	{
		/*
		 * int i;
		 * 
		 * String str = "Course Name: "+gradebook.getName()+"\n";
		 * 
		 * for (i=0;i<gradeItemNames.size();i++) { GradableItem g =
		 * gradebook.getItem(gradeItemNames.get(i)); str = str + g + "\n"; }
		 * 
		 * str = str +
		 * "Total (evaluated + estimated): "+getPercentageScore()+" out of "
		 * +getPercentageTotal();
		 * 
		 * return str;
		 */

		return gradebook.toString();
	}

	public String toXML()
	{
		String str;

		str = "<course>\n\t" + gradebook.toXML() + "\n</course>";
		return str;
	}

	public void writeCourseToXML(FileOutputStream fout)
	{
		String str = toXML();

		PrintWriter pw;

		pw = new PrintWriter(fout);

		pw.print(str);
		pw.close();
	}

	public File getXMLFile()
	{

		return this.XMLFile;
	}

}
