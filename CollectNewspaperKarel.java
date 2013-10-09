/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	/*
	 * precondition: Keral stands at the start position;
	 * Postcondition: Keral returns to the start position; 
	 * */
	public void run(){
		/*
		 * Show the point which Keral wants to do.
		 * */
		while(frontIsClear())
		{
			move();
		}
		gotopickBeepers();// Process that Karel go to pick up Beepers;
	}
	/*
	 * precondition: Keral stands at the start position;
	 * Postcondition: Keral moves and passes the corner ; 
	 * */
	private void gotopickBeepers() {
		if(frontIsBlocked()) {
			turnRight();
			changepointclearBeepers();
		}
	}
	/*
	 * precondition: Keral passes the corner and moving;
	 * Postcondition: Keral turns and to find the Beeper ; 
	 * */
	private void changepointclearBeepers() {
		while(leftIsBlocked()) {
			move();
		}
		turnLeft();
		clearnBeepers();
	}
	/*
	 *precondition: Keral has turn to the point which Beeber's point;
	 *Postcondition: Keral picks Beeper and turns around ;  
	 * */
	private void clearnBeepers() {
		move();
		while(beepersPresent()) {
			pickBeeper();
		}
		moveBackward();
	}
	/*
	 *precondition: Keral has been picked Beeper;
	 *Postcondition: Keral Turn back ;  
	 * */
	private void moveBackward() {
		turnAround();
		onthewayback();
	}
	/*
	 *precondition: Keral has been turned back;
	 *Postcondition: Keral Keep to move ; 
	 * */
	private void onthewayback() {
		while(frontIsClear()) {
			move();
		}
		gotostartpoint();
	}
	/*
	 * precondition: On the way back;
	 *Postcondition: Keral Turns to start position ;
	 * */
	private void gotostartpoint() {
		turnRight();
		if(frontIsClear()) {
			move();
		}
		turnRight();
	}

}
