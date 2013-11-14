package edu.ilstu;

/**
 * Class to represent an item that is actually graded (not a category)
 * Members: Points received, total points for this item
 * @author Amit Shesh
 *
 */
//this is a test of change
public class GradeItem extends GradableItem 
{
	protected double points;
	protected double outOf;
	
	public GradeItem(String name,double weight,double outOf)
	{
		super(name,weight);
		points = 0;
		this.outOf = outOf;
	}
	
	/**
	 * returns the percentage of grade known thus far
	 * @Override 
	 */
	public double getPercentageTotal() 
	{
		// TODO Auto-generated method stub
		if ((isEvaluated()) || (isEstimated()))
			return weight;
		else
			return 0;
	}

	/**
	 * Sets the weight of this item in the course
	 * @param weight
	 */
	public void setWeight(double weight)
	{
		this.weight = weight;
	}
	
	/**
	 * Returns the score as an average percentage
	 *@Override 
	 */
	public double getPercentageScore() 
	{
		// TODO Auto-generated method stub
		if ((isEvaluated()) || (isEstimated())) 
			return weight * points /outOf;
		else
			return 0;
	}
	
	/**
	 * If the name of this item matches, the points are set
	 *@Override 
	 */
	public boolean setEvaluatedPoints(String itemName,double points)
	{
		if (itemName.equals(name))
		{
			this.points = points;
			status = 2; //evaluated
		
			return true;
		}
		return false;
	}
	
	/**
	 * If the name of this item matches, the estimated points are set
	 *@Override 
	 */
	
	public boolean setEstimatedPoints(String itemName,double points)
	{
		if (itemName.equals(name))
		{
			this.points = points;
			status = 1; //estimated
		
			return true;
		}
		return false;
	}
	
	
	/**
	 * Sets how many max. points can be awarded for this item.
	 * This is called only when the grade book is being set up
	 * @param outOf
	 */
	public void setOutOf(double outOf)
	{
		this.outOf = outOf;
	}
	
	/**
	 * Returns the maximum number of points that can be obtained in this item
	 * @return the outOf
	 */
	public double getOutOf()
	{
		return outOf;
	}
	
	/**
	 * If the name matches its own, return self else return null
	 * @Override
	 */
	public GradableItem getItem(String name)
	{
		if (this.name.equals(name))
			return this;
		
		return null;
	}
	
	public String toString()
	{
		String str="";
		
		str = str + name;
		
		if (isEvaluated())
			str = str + ":Got "+points+" out of "+outOf;
		else if (isEstimated())
			str = str + ":Estimated "+points+" out of "+outOf;
		else
			str = str + ":Not available";
		return str;
	}
	
	public String toXML()
	{
		String str;
		
		str = "<item name=\"" + name+ "\" outof=\"" + outOf+"\" weight=\"" + weight +"\">";
		
		if (isEvaluated())
			str = str + "\t <points status=\"evaluated\">" + points + "</points>\n";
		else if (isEstimated())
			str = str + "\t <points status=\"estimated\">" + points + "</points>\n";
		
		str = str + "</item>";
		
		return str;
	}
}
