package aI_Calc;
//CTRL + SHIFT + O for all imports to be done

import java.util.HashMap;
import java.util.Scanner;

/*
 * Goal: create a user input loop which allows for interpretation of math
 * Needed: file storing and parsing, csv is fine.
 * Needed: actual equation parser, user input parser
 * Needed: HashMap for table, and calculate method, STATE 1 -> lookup, STATE 2 -> calculate & store
 * Needed: Input proofing when parsing
 */

public class AI_Main 
{

	public static void main(String[] args) 
	{
		String userInput;
		Scanner scan = new Scanner(System.in);
		FileManaging.createFile();
		
		
		//main user input loop, will check if allowed
		do 
		{
			System.out.println("Please enter your equation in text form, excluding numbers."
					+ "\nAn example would be: integral from 2 to 6 of x with respect to x."
					+ "\nWhen you are finished type: \"I am done\"");
			userInput = scan.nextLine();
			//this is where the parsing function would be called
		}
		while(!userInput.toLowerCase().equals("i am done"));
		
		//closing for safety
		scan.close();
		
		
		//the real guts of the program
		//one of the joys of HashMaps is that the same key will only be overwritten
		//this means the no duplicates will exist
		HashMap<String, String> currentHistory = FileManaging.pastHistory();
		
		
		//anything in here will be added after the current history
		currentHistory.put("First Key", "First Value");
		currentHistory.put("Second Key", "Second Value");
		
		//when first placed in hashmap it will be sorted with alphabetical,
		//but over multiple trials it will not be because it replaces exact key without changing
		
		currentHistory.put("Duplicate Test Key", "This can only be overwritten!");
		currentHistory.put("Alphabetical Test", "Maybe works?");
		//due to testing I know know that it retains data every time
		//this is where all the fun stuff happens
		
		FileManaging.storeHistory(currentHistory);
	}
}
