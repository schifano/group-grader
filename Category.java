package edu.ilstu;

/**
 * Name of class: Category, an extension of GradableItem
 * @author Amit Shesh
 *
 * This class represents a category (Assignments, Exams, etc.). Each category can 
 * hold within it several items, which can themselves be sub-categories, or directly
 * graded items. 
 * 
 * This class supports adding up to 10 items per category. This limitation can be removed
 * by using an ArrayList instead of an array below
 * 
 * items: Array storing the contents of this category (GradeItem or Category objects)
 * numItems: No of items added to this category so far
 */


public class Category extends GradableItem 
{
	protected GradableItem []items;
	protected int numItems;
	

	public Category(String name)
	{
		super(name,0);
		numItems = 0;
		items = new GradableItem[10];
		for (int i=0;i<items.length;i++)
			items[i] = null;
		

	}
	
	/**
	 * Adds a new item into this category. Item may be a GradeItem or another category
	 * @param item: item to be added
	 */
	public boolean addItem(String categoryName,GradableItem item)
	{
		boolean found;
		int i;
		
		if (categoryName.equals(this.name))
		{
			items[numItems] = item;
			numItems++;
			this.weight += item.getWeight();
			return true;
		}
		
		i=0;
		found = false;
		while ((i<numItems) && (!found))
		{
			if (items[i] instanceof Category)
				found = ((Category)items[i]).addItem(categoryName,item);			
			if (found)
				this.weight += item.getWeight();
			i++;
		}
		return found;
	}
	
	
	
	
	
	/**
	 * Computes and returns the evaluated + estimated percentage weight in this category
	 * This is determined by taking the sum of all the weights of its evaluated + estimated contents
	 * @Override
	 * @return evaluated + estimated percentage weight in this category
	 */
	public double getPercentageTotal() 
	{
		// TODO Auto-generated method stub
		double total = 0;
		int i;
		
		if ((!isEvaluated()) && (!isEstimated()))
			return 0;
		
		for (i=0;i<numItems;i++)
		{
			if ((items[i].isEvaluated()) || (items[i].isEstimated()))
			{
				total = total + items[i].getPercentageTotal();
			}
		}
		
		return total;
	}

	/**
	 * Computes and returns the evaluated + estimated percentage of the grade in this category
	 * This is determined by taking the sum of all the weighted scores of its evaluated + estimated contents
	 * @Override
	 * @return evaluated + estimated percentage of the grade in this category
	 */
	public double getPercentageScore() 
	{
		// TODO Auto-generated method stub
		double total = 0;
		int i;
		
		if (!isEvaluated())
			return 0;
		
		for (i=0;i<numItems;i++)
		{
			if ((items[i].isEvaluated()) || (items[i].isEstimated()))
			{
				total = total + items[i].getPercentageScore();				
			}
		}
		
		return total;
	}
	
	/**
	 * Sets the score of an item within this category.
	 * Only a GradeItem has a score. So here it merely tries to pass it on to its 
	 * contents. If the grade item is found in one of the contents, at least part 
	 * of this category has been evaluated. Therefore the status of this category will
	 * be set to "evaluated"
	 * @Override
	 */
		
	public boolean setEvaluatedPoints(String itemName, double points) 
	{
		// TODO Auto-generated method stub
		boolean found;
		int i;
		
		i=0;
		found = false;
		while ((i<numItems) && (!found))
		{
			found = items[i].setEvaluatedPoints(itemName,points);
			if (found)
				this.status = 2; //evaluated
			i++;
		}
		return found;
	}
	
	/**
	 * Sets the estimated score of an item within this category.
	 * Only a GradeItem has a score. So here it merely tries to pass it on to its 
	 * contents. If the grade item is found in one of the contents, at least part 
	 * of this category has been evaluated. Therefore the status of this category will
	 * be set to "evaluated"
	 * @Override
	 */
		
	public boolean setEstimatedPoints(String itemName, double points) 
	{
		// TODO Auto-generated method stub
		boolean found;
		int i;
		
		i=0;
		found = false;
		while ((i<numItems) && (!found))
		{
			found = items[i].setEstimatedPoints(itemName,points);
			if (found)
			{
				if (!isEvaluated())
					this.status = 1;
			}
			i++;
		}
		return found;
	}
	
	/**
	 * Returns the object whose name is "name". First it compares it with its own name. If not, it looks for it
	 * in its children
	 * @Override
	 */

	public GradableItem getItem(String name)
	{
		if (this.name.equals(name))
			return this;
		int i;
		GradableItem item = null;
		
		i=0;
		while ((i<numItems) && (item == null))
		{
			item = items[i].getItem(name);
			i++;
		}

		return item;
	}
	

	public String toXML()
	{
		String str;
		
		str = "<category name=\""+ name + "\">\n";
		for (int i=0;i<numItems;i++)
		{
			str = str + "\t" + items[i].toXML() + "\n";
		}
		str = str + "</category>";
		return str;
	}
	
	public String toString()
	{
		String str;
		
		str = "Group: "+ name + "\n";
		for (int i=0;i<numItems;i++)
		{
			str = str + "\t" + items[i].toString() + "\n";
		}
		str = str + "End of group";
		return str;
	}
	
	
	

}
