package mainClasses;

/* Artificial Intelligence Section 02
 * Troy Cope
 * This is my original work, with no reference to exterior code.
 * 11/29/21, Assignment #3
 */

public class grabableItem implements Comparable<grabableItem>
{
	double weight, value;
	String name;
	
	public grabableItem()
	{
		this("Test Item",1,1);
	}
	
	public grabableItem(String n, double v, double w)
	{
		name = n;
		value = v;
		weight = w;
	}

	@Override
	public int compareTo(grabableItem o) 
	{
		if(o.value/o.weight > this.value/this.weight)
			return 1;
		else if(o.value/o.weight < this.value/this.weight)
			return -1;
		return 0;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
