package edu.ilstu;


/**
 * 
 * This abstract class represents an item that is associated with a certain grade in the course
 * There are two such items: GradeItem and Category (a category of items)
 * 
 * @author ashesh
 *
 */
public abstract class GradableItem 
{
	protected double weight;
	protected String name;
	protected int status; //0-unevaluted, 1- estimated, 2- evaluated 
	
	public GradableItem(String name,double weight)
	{
		this.weight = weight;
		this.name = name;
		status = 0;
	}
	
	/**
	 * Returns the weight of this gradable item in this course
	 * @return
	 */
	public double getWeight()
	{
		return weight;
	}
	
	/**
	 * Returns whether this item has been evaluated or not
	 * @return
	 */
	public boolean isEvaluated()
	{
		return status==2;
	}
	
	/**
	 * Returns whether this item has been estimated or not
	 * @return
	 */
	
	public boolean isEstimated()
	{
		return status == 1;
	}
	
	
	
	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * returns the percentage of grade known thus far
	 * @return
	 */
	public abstract double getPercentageTotal(); 
	
	/**
	 * returns the weighted total of all items whose scores are known (estimated or evaluated)
	 * @return
	 */
	public abstract double getPercentageScore();
	
	/**
	 * sets the actual score of a particular item
	 * 
	 * @param itemName
	 * @param points
	 * @return
	 */
	public abstract boolean setEvaluatedPoints(String itemName,double points);
	
	/**
	 * sets the estimated score of a particular item
	 * @param itemName
	 * @param points
	 * @return
	 */
	public abstract boolean setEstimatedPoints(String itemName,double points);
	
	/**
	 * gets the GradableItem whose name is as given
	 * @param name
	 * @return
	 */
	public abstract GradableItem getItem(String name);
	
	/**
	 * 
	 * returns a string that is the XML representation of the current item
	 * @return
	 */
	public abstract String toXML(); 
}
