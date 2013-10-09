/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		//Print out the message.
		println("Enter values to compute the Pythagorian theorem&quot;" );
		int a = readInt("a:");
		int b = readInt("b:");
		//convert the type from int to double;
		double x = (double)a;
		//Calculate
		double c = Math.sqrt(x*x+b*b);
		println("c:"+c);
	}
}
