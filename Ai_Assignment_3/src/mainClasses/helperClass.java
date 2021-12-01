package mainClasses;

/* Artificial Intelligence Section 02
 * Troy Cope
 * This is my original work, with no reference to exterior code.
 * 11/29/21, Assignment #3
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class helperClass
{
	
	public static void checkFile(String fileName)
	{
		
		//creates a file and ensures it exists
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

	public static ArrayList<grabableItem> parseFile(String fileName) 
	{
		try 
		{
	      File myObj = new File(fileName);
	      Scanner fileReader = new Scanner(myObj);
	      //creating arrays to match the rows of data sets
	      ArrayList<String> holder = new ArrayList<String>();
	      ArrayList<grabableItem> items = new ArrayList<grabableItem>();
	      
	      
	      //reads in each line and then parses using CSV format
	      while(fileReader.hasNext())
	      {
	    	  holder.addAll(Arrays.asList(fileReader.nextLine().split(",")));
	          //System.out.println(holder);
	      }
	      
	      
	      //System.out.println("Total List Size is: " + holder.size());
	      
	      //size is exact
	      if(holder.size()%3 == 0)
	      {
	    	  //parsing into objects
	    	  for(int i = 0; i < holder.size(); i+=3)
		      {
		    	  items.add(new grabableItem(holder.get(i),
		    			  Double.parseDouble(holder.get(i+1)),
		    			  Double.parseDouble(holder.get(i+2))));
		      }
	      }
	      
	      //sorting via comparable
	      items.sort(null);
	      
	      //printing sorted list
	      //System.out.println(items);     
	
          	      
          //closing IO for safety
	      fileReader.close();
	      
	      return items;
	    } 
	 catch (FileNotFoundException e) 
	 	{
          System.out.println("An error occurred.");
          e.printStackTrace();
     	}
		
	return null;
		
	}
}
