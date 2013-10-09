/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	/*
	 * Three circles
	 * */
	public void run() {
		LargeRedGoval();
		WhiteGoval();
		SmallRedGoval();
		
	}
	/*
	 * First of all get the coordination of the large red circle.
	 * If the window size is 200*200pixels and the center is 100*100 pixels.
	 * I know the radius is 72 pixels and I will get the left and bottom of the rectangle : 100-72/2=64
	 * */
	public void LargeRedGoval() {
		
		GOval redgoval = new GOval(64,64,72,72);
		redgoval.setFilled(true);
		redgoval.setColor(Color.red);
		redgoval.setFillColor(Color.red);
		add(redgoval);
	}
	
	/*
	 * For the white Goval is the same like large red circle.
	 * radius:0.65*72.  100-R/2 is the left and bottom of the rectangle.
	 * */
	public void WhiteGoval() {
		
		GOval myOval = new GOval(76.6, 76.6, 46.8, 46.8);
	    myOval.setFilled(true);
	    myOval.setColor(Color.WHITE);
	    myOval.setFillColor(Color.WHITE);
	    add(myOval);
		
	}
	
	/*
	 * For the small red Goval.
	 * radius:0.3*72.  100-R/2 is the left and bottom of the rectangle.
	 * */
	public void SmallRedGoval() {
		
		 GOval myOval = new GOval(89.2, 89.2, 21.6, 21.6);
		    myOval.setFilled(true);
		    myOval.setColor(Color.RED);
		    myOval.setFillColor(Color.RED);
		    add(myOval);
		
	}
}
