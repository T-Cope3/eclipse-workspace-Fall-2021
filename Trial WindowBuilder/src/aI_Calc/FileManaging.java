package aI_Calc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FileManaging 
{
	private static String fileName = "calculateHistory.csv";
	//used only for testing until or at the end of the program
	//read in HashMap and parses it
	
	//creates a file and ensures it exists
	public static void createFile()
	{
		try 
		{
	      File myObj = new File(fileName);
	      if (myObj.createNewFile()) 
	      {
	        System.out.println("File created as: " + myObj.getName());
	      }
	      else 
	      {
	        System.out.println("File currently exists.");
	       // append = true;
	      }
	    } 
		catch (IOException e) 
		{
	      System.out.println("File creation led to an error.");
	      e.printStackTrace();
		}
	}
	
	//will actually write the history when the program finishes
	public static void storeHistory(HashMap<String, String> aIRecord)
	{
		//boolean append = false;		
		//starts file writing
		try 
		{
		  //can use newline when wrapped, append can be used but as of now unnecessary
	      FileWriter outputStream = new FileWriter(fileName);
	      BufferedWriter myWriter = new BufferedWriter(outputStream);
	      
	      //because of how written, need to parse keys and values into separate arrays
	      for(String i : aIRecord.keySet())
	      {
	    	  System.out.println("Writing Key!: " + i);
	    	  myWriter.write(i+",");
	      }	    	  
	      
	      //hopping lines to make parsing easier
	      myWriter.newLine();
	      
	      for(String i: aIRecord.values())
	      {
	    	  System.out.println("Writing Value!: " + i);
	    	  myWriter.write(i+",");
	      }
	      
	      //spacing things out
	      myWriter.newLine();
	      
	      myWriter.close();
	      
	      System.out.println("File written to.");
		}
		catch (IOException e) 
		{
	      System.out.println("File writing has caused an error.");
	      e.printStackTrace();
		}
	}
	
	//section to parse the history in order to make a "current" HashMap which holds all the data
	public static HashMap<String, String> pastHistory()
	{
	 try {
	      File myObj = new File(fileName);
	      Scanner fileReader = new Scanner(myObj);
	      //creating arrays to match the rows of data sets
	      ArrayList<String> keys = new ArrayList<String>();
	      ArrayList<String> values = new ArrayList<String>();
	      
	      
	      //due to the fact that I always compress the HashMaps down to two lines
	      //there only needs to be two read lines
	      if(fileReader.hasNext())
	      {
	    	  keys.addAll(Arrays.asList(fileReader.nextLine().split(",")));
	          System.out.println(keys);
		      
	    	  values.addAll(Arrays.asList(fileReader.nextLine().split(",")));
	          System.out.println(values);
	      }
          	      
          //closing IO for safety
	      fileReader.close();
	      
	      //piecing together the HashMap
	      HashMap<String, String> fileHistory = new HashMap<String, String>();
	      
	      for(int i = 0; i < keys.size(); i++)
	    	  fileHistory.put(keys.get(i), values.get(i));
	      
	      //returning File->HashMap
	      System.out.println(fileHistory);
	      return fileHistory;
	    } 
	 catch (FileNotFoundException e) 
	 	{
          System.out.println("An error occurred.");
          e.printStackTrace();
     	}
	 
	 //should never happen. EVER.
	 return null;
	}
}
