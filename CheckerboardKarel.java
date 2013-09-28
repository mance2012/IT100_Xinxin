/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	/*
	 * Drop alternating beepers while facing East and West.
	 * */
	public void run() {
		putBeeper();
		while(frontIsClear()) {
			faceEast();
			faceWest();
		}
	}
	/*
	 * Drop alternating beepers while facing East.
	 * */
	private void faceEast() {
		while(facingEast()) {
			move();
			if(frontIsClear()) {
				move();
				putBeeper();
			}
			upandgotowest();
		}
		
	}
	/*
	 * When Keral facing block on the end ,move up and turn to West.
	 * */
	private void upandgotowest() {
		if(frontIsBlocked()) {
			turnLeft();
			if(frontIsClear()) {
				move();
				turnLeft();
				putBeeper();
			}
		}
	}
	/*
	 * Drop alternating beepers while facing West.
	 * */
	private void faceWest() {
		while(facingWest()) {
			move();
			if(frontIsClear()) {
				move();
				putBeeper();
			}
			upandgotoEast();
		}
	}
	/*
	 * When Keral facing block on the end ,move up and turn to East.
	 * */
	private void upandgotoEast() {
		if(frontIsBlocked()) {
			turnRight();
			if(frontIsClear()) {
				move();
				putBeeper();
				turnRight();
			}
		}
	}
	

}
