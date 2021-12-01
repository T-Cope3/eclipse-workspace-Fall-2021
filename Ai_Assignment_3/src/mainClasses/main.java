package mainClasses;

/* Artificial Intelligence Section 02
 * Troy Cope
 * This is my original work, with no reference to exterior code.
 * 11/29/21, Assignment #3
 */

import java.util.ArrayList;
import java.util.Scanner;

public class main
{
	//static ArrayList<Integer> yesOrNo = new ArrayList<Integer>();
	static ArrayList<ArrayList<Integer>> possibleGrabs = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> optimalGrabs = new ArrayList<ArrayList<Integer>>();
	static float maxWeight;
	
	public static void main(String[] args)
	{		
		ArrayList<grabableItem> items = new ArrayList<grabableItem>();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter the max weight the knapsack can hold!");
		maxWeight = (float) scan.nextDouble();
		
		
		//read in the data from JSON format from a file? Or simply a CSV type deal.
		//for the length need to create a yes or no parallel
		
		//testing file if it exists
		helperClass.checkFile("sampleData.txt");
		
		//file checked, now scanning in
		items = helperClass.parseFile("sampleData.txt");
		
		//transfers perfectly, from method to actual ArrayList
		System.out.println(items);
		int counter = 0;
		int sizeBeforeMutation = 0;
		int sizeBeforeCross = 0;
		//LETS GET DOWN TO IT
		
		//generate initial
		
		while(optimalGrabs.size() < 50)
		{
			while(counter < 30)
			{
				for(int i = 0; i < 6; i++)
					possibleGrabs.add(grabItems(items));
				//System.out.println(possibleGrabs);
				
//				for(int i = 0; i < possibleGrabs.size(); i++)
//					evalSack(possibleGrabs, items, i);
				
				//necessary because if I add in, the program will continue to run
				sizeBeforeMutation = possibleGrabs.size();
				
				for(int i = 0; i < sizeBeforeMutation; i++)
					possibleGrabs.add(i, mutateYesOrNo());
				
				//System.out.println(possibleGrabs);
				
				
//				for(int i = 0; i < possibleGrabs.size(); i++)
//					evalSack(possibleGrabs, items, i);
				

				//this will cross in the mutations as well
				sizeBeforeCross = possibleGrabs.size();
				
				for(int i = 0; i < sizeBeforeCross; i++)
					possibleGrabs.add(crossGrabbed());
					//System.out.println(possibleGrabs);
					
					purgeLowValues(possibleGrabs, items);
					//System.out.println("\nThe top genes run through " + counter + " are: \n" + possibleGrabs);
					counter++;
			}
			

			//System.out.println("\nThe top genes after the final run through are: \n" + possibleGrabs + "\n");
			
			optimalGrabs.add(findLargestGene(possibleGrabs, items));					
			possibleGrabs.clear();
			counter = 0;
		}
		
		System.out.println("The final strands from the network are: " + optimalGrabs);
		System.out.println("\nTheir evaluations are: \n");
		
		for(int i = 0; i < optimalGrabs.size(); i++)
		{
			if(i%5 == 0 && i != 0)
				System.out.println();
			System.out.print(evalSack(optimalGrabs, items, i) + ", ");			
		}
		System.out.println();
		
		System.out.println("\nThe best gene from evaluation is: " + findLargestGene(optimalGrabs, items) + " which has a max value of: " + totalValue(findLargestGene(optimalGrabs, items), items)
		+ " and a weight of: " + totalWeight(findLargestGene(optimalGrabs, items), items));
		
		
		
	}
	
	//this is the backbone of the project in a sense
	//it uses an algorithm to match the genes with their values,
	//assesses those values and then pulls out the highest half
	//uses the elite purging method
	public static void purgeLowValues(ArrayList<ArrayList<Integer>> identifier, ArrayList<grabableItem> items)
	{
		int reducedGrabs = possibleGrabs.size()/4;
		//System.out.println("ReducedGrabs: " + reducedGrabs);
		ArrayList<Float> lowThreash = new ArrayList<Float>();
		
		for(int i = 0; i < possibleGrabs.size(); i++)
			lowThreash.add(evalSack(identifier, items, i));
		
		lowThreash.sort(null);
		//System.out.println(lowThreash);
		
		
		//this irritating loop covers up to 7 individual outcomes that the values could end up being
		//basically a catch-all and somewhat inefficient
		//hence the 3 conditionals
		for(int j = 0; j+reducedGrabs < lowThreash.size(); j++)
		{
			//the reduced-j is actually necessary as well as the j+reduced due to odds vs evens
			for(int i = 0; possibleGrabs.size() > reducedGrabs && i < possibleGrabs.size() && reducedGrabs-j > 0; i++)
				if(evalSack(identifier, items, i) < lowThreash.get(lowThreash.size()-(reducedGrabs-j)))
				{
					possibleGrabs.remove(i);		
					//have to account for auto adjustment
					i--;
				}
		}
		
		//this means each one is equal so it peels of the front
		while(possibleGrabs.size() > reducedGrabs)
			possibleGrabs.remove(0);
		
		//printing the represented sets as well as it's actual evaluation
		//System.out.println(possibleGrabs);
		
//		for(int i = 0; i < possibleGrabs.size(); i++)
//			System.out.println(evalSack(identifier, items, i));
	}
	
	public static ArrayList<Integer> findLargestGene(ArrayList<ArrayList<Integer>> identifier, ArrayList<grabableItem> items)
	{
		ArrayList<Integer> highest = identifier.get(0);
		double evalHigh = evalSack(identifier, items, 0);
		
		for(int i = 0; i < identifier.size(); i++)
		{
			if(evalSack(identifier, items, i) > evalHigh)
			{
				evalHigh = evalSack(identifier, items, i);
				highest = identifier.get(i);
			}
		}
		
		return highest;
	}
	
	public static ArrayList<Integer> grabItems(ArrayList<grabableItem> items)
	{
		ArrayList<Integer> yesOrNo = new ArrayList<Integer>();
		
		for(int i = 0; i < items.size(); i++)
		{
			if(Math.random()+0.5 >= 1)
				yesOrNo.add(1);
			else
				yesOrNo.add(0);
		}
		
		return yesOrNo;
	}
	
	//finds the weight of all assigned items in the bag
	public static float totalWeight(ArrayList<ArrayList<Integer>> identifier, ArrayList<grabableItem> items, int whichSet)
	{
		float total = 0;
		
		for(int i = 0; i < items.size(); i++)
		{
			if(identifier.get(whichSet).get(i) == 1)
				total += items.get(i).weight;
		}
		
		return total;
	}
	
	public static float totalWeight(ArrayList<Integer> identifier, ArrayList<grabableItem> items)
	{
		float total = 0;
		
		for(int i = 0; i < items.size(); i++)
		{
			if(identifier.get(i) == 1)
				total += items.get(i).weight;
		}
		
		return total;
	}
	
	//finds the value of all assigned items in the bag
	public static float totalValue(ArrayList<ArrayList<Integer>> identifier, ArrayList<grabableItem> items, int whichSet)
	{
		float total = 0;
		
		for(int i = 0; i < items.size(); i++)
		{
			if(identifier.get(whichSet).get(i) == 1)
				total += items.get(i).value;
		}		
		
		return total;
	}
	
	public static float totalValue(ArrayList<Integer> identifier, ArrayList<grabableItem> items)
	{
		float total = 0;
		
		for(int i = 0; i < items.size(); i++)
		{
			if(identifier.get(i) == 1)
				total += items.get(i).value;
		}		
		
		return total;
	}
	
	public static float evalSack(ArrayList<ArrayList<Integer>> identifier, ArrayList<grabableItem> items, int whichSet)
	{
		float weightOfGiven = totalWeight(identifier, items, whichSet);
		float valueOfGiven = totalValue(identifier, items, whichSet);
		float denom = maxWeight-weightOfGiven;
		
		//basically gets rid of anything over the max, will still cross and mutate before deletion
		if(denom < 0)
			return denom;
		
//		if(weightOfGiven > maxWeight)
//			System.out.println("Knapsack is over filled! Needs less weight!");
		
//		System.out.println("Total Value: " + valueOfGiven);
//		System.out.println("Total Weight: " + weightOfGiven + "\n");
//		
		return valueOfGiven;
	}
	
	//selects an individual chromosome and flips it
	//doesn't use the last value because it can't be accessed, 0->(size-1)
	public static ArrayList<Integer> mutateYesOrNo()
	{
		int selectedFromAll = (int) Math.floor((Math.random() * possibleGrabs.size()));
		ArrayList<Integer> holder = possibleGrabs.get(selectedFromAll);
		int selectedMut = (int) Math.floor((Math.random() * holder.size()));
		
		if(holder.get(selectedMut) == 1)
			holder.set(selectedMut, 0);
		else
			holder.set(selectedMut, 1);
		
		return holder;
	}
	
	//can only be applied once I have multiple line to cross over, or crosses over within self
	public static ArrayList<Integer> crossGrabbed() 
	{
		
		ArrayList<Integer> holder = new ArrayList<Integer>();
		int selectedCrossSection = (int) Math.floor((Math.random() * possibleGrabs.get(0).size()));
		int crosser = (int) Math.floor((Math.random() * possibleGrabs.size()));
		int crossed = (int) Math.floor((Math.random() * possibleGrabs.size()));
		
		//reassigning a crossover point when  none would be "crossed"
		while(selectedCrossSection == 0 || selectedCrossSection == possibleGrabs.get(0).size()-1)
			selectedCrossSection = (int) Math.floor((Math.random() * possibleGrabs.get(0).size()));
		
		//making sure they are crossed from different genes
		if(possibleGrabs.size() > 1)
			while(crosser == crossed)
				crosser = (int) Math.floor((Math.random() * possibleGrabs.size()));
		
//		System.out.println("Selected Cross: " + selectedCrossSection);
//		System.out.println("Selected Crosser: " + crosser);
//		System.out.println("Selected Crossed: " + crossed);
		
		//essentially splitting each array into the holder one
		for(int i = 0; i < selectedCrossSection; i++)
			holder.add(i, possibleGrabs.get(crossed).get(i));
			
		for(int i = selectedCrossSection; i < possibleGrabs.get(crosser).size(); i++)
			holder.add(i, possibleGrabs.get(crosser).get(i));
		
		return holder;
	}
}
