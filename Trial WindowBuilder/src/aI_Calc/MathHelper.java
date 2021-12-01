package aI_Calc;

import java.util.ArrayList;

public class MathHelper 
{
	// I will handle subtraction as negative addition, it's easier once parsed.
	//these are very self explanatory, they just condense the actual program and make it clearer
	public static double add(double input, double input2)
	{
		double sum = 0;
		sum = input + input2;	
		return sum;
	}
	
	public static double multiply(double input, double input2)
	{
		double sum = input;
		sum *= input2;
		//multiplication will always yield a result, even if by 1 or 0
		return sum;
	}
	
	//dividend then divisor
	public static double divide(double input, double input2)
	{
		//the reason we use the first one:
		//10 divided by 6 = 10,/,2,/,5 = 10,2,5 post parse becomes
		//10/2 = 5/5 = 1.
		double sum = input;
		sum /= input2;
		
		return sum;
	}
	
	//calculate the singular answer from each full equation
	public static double calc(ArrayList<String> ops, ArrayList<Double> nums)
	{
		int opIndex;
		double answer = 0;
		
		while(ops.size()>0)
		{
			//each of these follow the same pattern so I'll write a little bit about it
			//checks if the operation exits, in PEMDAS order
			//then removes the operation and replaces the two numbers with their answer
			if(ops.indexOf("/") >= 0)
			{
				opIndex = ops.indexOf("/");
				ops.remove(opIndex);
				
				//after removing opIndex, opIndex <- opIndex+1.
				if(nums.get(opIndex+1) == 0)
					{
					System.out.println("Division by zero detected. Returning -999.");
					return -.932451923726152181;
					}
				
				answer = divide(nums.remove(opIndex),nums.remove(opIndex));
				nums.add(opIndex, answer);
			}
			
			if(ops.indexOf("*") >= 0)
			{
				opIndex = ops.indexOf("*");
				ops.remove(opIndex);
				
				//after removing opIndex, opIndex <- opIndex+1.
				answer = multiply(nums.remove(opIndex),nums.remove(opIndex));
				nums.add(opIndex, answer);
			}
			
			if(ops.indexOf("-") >= 0)
			{
				opIndex = ops.indexOf("-");
				ops.remove(opIndex);
				
				//after removing opIndex, opIndex <- opIndex+1.
				answer = add(nums.remove(opIndex),(-1*nums.remove(opIndex)));
				nums.add(opIndex, answer);
			}
			
			if(ops.indexOf("+") >= 0)
			{
				opIndex = ops.indexOf("+");
				ops.remove(opIndex);
				
				//after removing opIndex, opIndex <- opIndex+1.
				answer = add(nums.remove(opIndex),nums.remove(opIndex));
				nums.add(opIndex, answer);
			}
			System.out.println("Ops: " + ops);
			System.out.println("Nums: " + nums);
		}
		return nums.get(0);
	}
	
	//created to compare truncation
	//makes doubles like 2.0 into integers, 2.0 -> 2
	public static String formatDecimals(double a)
	{
		if(a == -.932451923726152181)
			return "DNE";
		if((int)a == a)
		{
			return ("" + (int)a);
		}
		else
			return ("" + a);
	}
}
