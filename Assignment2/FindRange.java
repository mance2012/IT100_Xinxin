/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		int sentinel = 0;
		println("This program finds the largest and smallest numbers.");
		int number = readInt("?");
		//If the first value is 0, it will break.
		if (number==sentinel){
            println("No values have been entered.");
        }
        else{
            int largestNumber= number;
            int smallestNumber= number;
            while (true) {
                int numbers = readInt("?");        
                if (numbers == sentinel) break;
                if (numbers != 0 && numbers<=smallestNumber){
                	smallestNumber = numbers;
                }    
                if (numbers>=largestNumber){
                largestNumber=numbers;
            }             
        }
            // The result message after the value is 0
        println("This program finds the largest and smallest numbers.");
        println("Largest:" + largestNumber );
        println("Smallest:" + smallestNumber );    
        }
	}
}

