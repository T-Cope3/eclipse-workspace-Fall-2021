/*
 * Created by: Troy Cope
 * For: AI
 * Due: 10/17/2021
 */
package algorithm_Solving;

public class HeuristicClassification 
{
	
	final static int[] GOAL_ARRAY =
		{
				1,2,3,
				8,0,4,
				7,6,5
		};
	//sum of all distances where the values are not in place
	public static int EvalManhattan(Box a)
	{
	    int manHat = 0;
	
	    int expectedRow, expectedCol;
	    int actualRow, actualCol;
	    
	    for (int index = 0; index < a.getLocalArr().length-1; index++) 
	    {
	    		    	
            expectedRow = findValueIndex(GOAL_ARRAY, index)/3;
            expectedCol = findValueIndex(GOAL_ARRAY, index)%3;
            
            actualRow = findValueIndex(a.getLocalArr(),index)/3;
            actualCol = findValueIndex(a.getLocalArr(),index)%3;
            
            
            manHat += Math.abs(expectedRow - actualRow) + Math.abs((expectedCol - actualCol));
        }
	    return manHat;
	}
		
	//finding values within array for comparison
	public static int findValueIndex(int[] localArr, int a)
	{
		for(int i = 0; i < localArr.length; i++)
		{
			if(localArr[i] == a)
				return i;
		}
		return 0;
	}
}
