/*
 * Created by: Troy Cope
 * For: AI
 * Due: 10/17/2021
 */
package algorithm_Solving;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Box_Worker
{	
	
	static final int[] GOAL_ARRAY =
		{
				1,2,3,
				8,0,4,
				7,6,5
		};
	
	static final int MAXCAP = 100000;
	
	
	public static void main(String[] args)
	{
		//standard variable declaration
		Scanner scan = new Scanner(System.in);
		String userInput = "0";
		int sum =0;
		int count = 0;
		int evaluationType = -1;
		int[] userGivenArray = null;
		Box currentState;
		Box goalState = new Box(GOAL_ARRAY);
		PriorityQueue<Box> prioQ = new PriorityQueue<>();
		
		//this section is for user inputs to ensure they are okay		
		while(sum != 36)
		{
			sum = 0;
			System.out.println("Please enter a string of 9 numbers without spaces, 0-8 where 0 is the empty "
					+ "space to represent" + " the box puzzle.");
			userInput = scan.next();
			userInput = userInput.trim();
			
			userGivenArray = new int[userInput.length()];
			for(int i = 0; i < userInput.length(); i++)
			{
				//always subtracting 48 because it returns ASCII value
				userGivenArray[i] = userInput.charAt(i)-48;
			}
			
			for(int i = 0; i < userGivenArray.length; i++)
				sum+= userGivenArray[i];			
		}
		
		//more selection
		while(evaluationType < 0 || evaluationType > 2)
		{
			System.out.println("Please enter one integer corresponding to the "
			+ "following choices:\n0: Uniform Cost Search\n1: Best First Search\n2: A* Search");
			evaluationType=scan.nextInt();
		}
		
		//assigns starting point of the search
		currentState = new Box(userGivenArray, evaluationType);
		prioQ.add(currentState);
		
		//generating the next boxes that are for scanning
		ArrayList<Box> nextStates = new ArrayList<Box>();	
		ArrayList<Box> expandedStates = new ArrayList<Box>();
		ArrayList<Box> finalPath = new ArrayList<Box>();	
		
		//start timer
		long time = System.currentTimeMillis();
		while(currentState.getStateKey() != goalState.getStateKey() && !prioQ.isEmpty())
		{	
			//build frontier and check off nodes expanded
			expandedStates.add(currentState);
			
			//NOTE: program evaluates D,L,R,U which means it has a bias
			//not completely random but creates each possible child & documents them
			nextStates = generateNextStates(detectMovements(currentState), currentState);
			currentState.addChildren(nextStates);
			
			//only adds the 
			for(int i = 0; i < nextStates.size(); i++)
			{
				if(expandedStates.contains(nextStates.get(i)))
				{}
				else
					prioQ.add(nextStates.get(i));
				
			}	
			
			//moving up each time and purging the PQueue of the most likely one
			currentState = prioQ.poll();
			
			//count is to close off a running search if it gets too large
			count++;
			if(count > MAXCAP)
				break;
		}
		
		//checks system and running
		time -= System.currentTimeMillis();
		time = Math.abs(time);
		
		//finishes running and prints all important info
		System.out.println("\nThe final node had: \nDepth: " + currentState.getDepth() + "\nNodes Expanded: " 
		+ expandedStates.size()	+ "\nNodes Opened: " + (expandedStates.size()+prioQ.size()) +
		"\nTime Taken: " + time + " milliseconds\n");
		
		//this can be reversed to find the path taken
		while(currentState != null)
		{
			finalPath.add(currentState);
			currentState = currentState.getParent();
		}
		
		//have to invert so that it goes start->finish instead of finish->start
		ArrayList<Box> holder = new ArrayList<Box>();
		
		while(!finalPath.isEmpty())
		{
			holder.add((finalPath.remove(finalPath.size()-1)));
		}
		
		//end of project printing
		System.out.println("Parent List goes as shown: " + ungodlyStatesPrintout(holder));
		
		Box temp = holder.remove(0);
		
		while(!holder.isEmpty())
		{
			temp = holder.remove(0);
			System.out.println("The list of moves is: " + temp.getMoveType());			
		}
		
		//this only triggers if the MAXCAP is hit, will still print all the other information
		if(count > MAXCAP)
		{
			System.out.println("The list is incomplete because the count reached " + MAXCAP + " and quit.");
		}
	} 			
	
	//once this completes, can select the optimal from the given states.
	public static ArrayList<Box> generateNextStates(int[] movements, Box givenState)
	{
		//at most 4 moves means 4 states at most
		ArrayList<Box> nextStates = new ArrayList<Box>();
		for(int i = 0; i < movements.length; i++)
		{
			nextStates.add(moveSpace(movements[i],givenState));
		}
		return nextStates;
	}
	
	//gives indexed 0-3 for
	//0 - Up, 1 - Left, 2 - Right, 3 - Down
	public static int[] detectMovements(Box givenState)
	{
		int[] temp = new int[4];
		int count = 0;
		if(givenState.getBlankIndex()-3 >= 0)
		{
			temp[count] = 0;
			count++;
		}
		if(givenState.getBlankIndex()-1 >= 0 && (givenState.getBlankIndex()-1)/3 == givenState.getBlankIndex()/3)
		{
			temp[count] = 1;
			count++;
		}
		if(givenState.getBlankIndex()+1 < givenState.getLocalArr().length && givenState.getBlankIndex()/3 == (givenState.getBlankIndex()+1)/3)
		{
			temp[count] = 2;
			count++;
		}
		if(givenState.getBlankIndex()+3 < givenState.getLocalArr().length)
		{
			temp[count] = 3;
			count++;
		}
		
		//have to loop and create a new array to constrain one later
		int[] ret = new int[count];
		for(int i = 0; i < count; i++)
			ret[i] = temp[i];
			
		return ret;
	}
	
	//Up: 0, Left: 1, Right: 2, Down: 3
	public static Box moveSpace(int direction, Box givenState)
	{
		int[] localArr = new int[givenState.getLocalArr().length];
		String str = "";
		
		for(int i = 0; i < localArr.length; i++)
			localArr[i] = givenState.getLocalArr()[i];
		
		switch(direction)
		{
		//can only swap around the blank, so space will always be "0"
			case 0:
			{
				//always exists because it has to be present in the array
				//swapping vertically upwards if possible
				str = "Move " + localArr[(givenState.getBlankIndex()-3)] + " down.";
				localArr[givenState.getBlankIndex()] = localArr[givenState.getBlankIndex()-3];
				localArr[givenState.getBlankIndex()-3] = 0;	
				break;
			}
			case 1:
			{
				str = "Move " + localArr[(givenState.getBlankIndex()-1)] + " right.";
				localArr[givenState.getBlankIndex()] = localArr[givenState.getBlankIndex()-1];
				localArr[givenState.getBlankIndex()-1] = 0;
				break;
			}
			case 2:
			{
				str = "Move " + localArr[(givenState.getBlankIndex()+1)] + " left.";
				localArr[givenState.getBlankIndex()] = localArr[givenState.getBlankIndex()+1];
				localArr[givenState.getBlankIndex()+1] = 0;
				break;
			}
			case 3:
			{
				str = "Move " + localArr[(givenState.getBlankIndex()+3)] + " up.";
				localArr[givenState.getBlankIndex()] = localArr[givenState.getBlankIndex()+3];
				localArr[givenState.getBlankIndex()+3] = 0;
				break;
			}
		}
		//array setup is changing so the key needs regenerated at the end
		return new Box(localArr, givenState, str);
	}
	
	//printing for a list of boxes to make showing the end result easy
	public static String ungodlyStatesPrintout(ArrayList<Box> nextStates)
	{
		String str = "\n";
		
			//print one row at a time from all 4 possible arrays
			for(int k = 0, j = 1; k < 9;)
			{
				
				for(int i = 0; i < nextStates.size(); i++)
				{
					
					for(k = 3*(j-1); k < 3*j; k++)
					{
						str += nextStates.get(i).getLocalArr()[k];
					}
					str += " ";
				}
				j++;
				str += "\n";
			}			
		
		return str;
	}

	
}
