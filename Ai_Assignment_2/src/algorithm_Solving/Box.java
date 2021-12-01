/*
 * Created by: Troy Cope
 * For: AI
 * Due: 10/17/2021
 */
package algorithm_Solving;

import java.util.ArrayList;

public class Box  implements Comparable<Box>
{
	private int[] localArr;
	private int stateKey;
	private int blankIndex;
	private int cost;
	private int depth;
	private ArrayList<Box> children;
	private Box parent;
	private String moveType;
	//UCS, BFS, A* in that order
	// 0 ,  1 , 2,
	private int costType;
	
	public Box()
	{
		//send in tester array straight away if not given
		this(new int[] 
				{
						3,0,2,
						8,1,4,
						7,6,5
				});
	}
	
	//tons of constructors for different uses
	//these two for initialization
	public Box(int[] a)
	{
		localArr = a;
		blankIndex = findBlank();
		stateKey = generateKey();
		costType = 2;
		cost = this.calcCost(costType);
		depth = 0;
		children = new ArrayList<Box>();
		this.parent = null;
	}
	
	public Box(int[] a, int evalFunction)
	{
		localArr = a;
		blankIndex = findBlank();
		stateKey = generateKey();
		costType = evalFunction;
		depth = 0;
		cost = this.calcCost(costType);
		children = new ArrayList<Box>();
		this.parent = null;
	}

	//one with an eval function will always be with children
	public Box(int[] a, Box parent, String moveType)
	{
		localArr = a;
		blankIndex = findBlank();
		stateKey = generateKey();
		costType = parent.getCostType();
		depth = (parent.getDepth()+1);
		cost = this.calcCost(costType);		
		children = new ArrayList<Box>();
		this.parent = parent;
		this.setMoveType(moveType);
	}
	
	//necessary for PrioQ
	@Override
	public int compareTo(Box o) 
	{
		if(this.cost > o.getCost())
			return 1;
		else if(this.cost < o.getCost())
			return -1;
		return 0;
	}
	
	//makes them print in a neat way
	@Override
	public String toString()
	{
		String str = "";
		for(int i = 0; i < localArr.length; i++)
		{ 
			if(i % 3 == 0)
				{str += "[";}
			
			str += ""+localArr[i];
			
			if((i % 3 == 2))
				{str += "]\n";}			
		}	
		return str;
	}
	
	
	//used in finding movements in main
	private int findBlank()
	{
		for(int i = 0; i < localArr.length; i++)
		{
			if(localArr[i] == 0)
				return i;
		}
		return 0;
	}
	
	//useful when trying to check Boxes directly
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Box)
			if( ((Box)o).getStateKey() == this.stateKey)
				return true;
		return false;		
	}
	
	//no two alike, thankfully
	private int generateKey()
	{
		String sum = "";
		for(int i = 0; i < localArr.length; i++)
				sum+= ""+localArr[i];
		
		return Integer.parseInt(sum);				
	}
	
	//implementation of heuristics as shown by definition
	private int calcCost(int a) 
	{
		int thisCost = 0;
		switch(a)
		{
			case 0:
			{
				//Uniform Cost Search
				//g(n) = 1 for each depth
				//already accounted for when they are called a depth is added
				thisCost = this.depth;
				break;
			}
			case 1:
			{
				//Best First Search
				//h(n) where it finds the distances from spaces
				thisCost = HeuristicClassification.EvalManhattan(this);
				break;
			}
			case 2:
			{
				//A* Search where it uses both g(n) and h(n)
				thisCost = HeuristicClassification.EvalManhattan(this) + this.depth;
				break;
			}
		}
		
		//this will change the cost to the evaluated + parents
		return thisCost;
	}

	//Standard getters and setters
	
	public int getStateKey() 
	{
		return stateKey;
	}
	
	public int getBlankIndex() 
	{
		return blankIndex;
	}
	
	public void setBlankIndex(int x)
	{
		blankIndex = x;
	}
	
	public int[] getLocalArr()
	{
		return localArr;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}

	
	
	public int getCost() 
	{
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public int getCostType() {
		return costType;
	}


	public void setCostType(int costType) {
		this.costType = costType;
	}
	
	public void addChildren(ArrayList<Box> a)
	{
		this.children.addAll(a);
	}
	
	public Box getParent()
	{
		return this.parent;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

}
