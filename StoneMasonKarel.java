/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	/*
	 * precondition: Karel starts at 1st Avenue and 1st Street, facing east;
	 * Postcondition: Keral stands at 1st Avenue and 13st Street; 
	 * */
	public void run() {
		if(frontIsClear()) {
			turnLeft();
			ProcessPutBeepers();
		}
	}
	/*
	 * 
	 * Keral put Beeper on the first column.
	 * */
	private void ProcessPutBeepers() {
		while(noBeepersPresent()) {
			putBeeper();
			move();
		}
			upclumnprocess();
			
	}
	/*
	 * Finished first column and turn right.
	 * 
	 * */
	private void upclumnprocess() {
			while(frontIsClear()) {
				move();
			}
			turnRight();
			rightProcess();
		
	}
	/*
	 * The process that go to the second column
	 * */
	private void rightProcess() {
		move();
		while(leftIsClear()) {
			move();
		}
		turnRight();
		downcoumnprocess();
			
	}
	/*
	 * Finished second column.
	 * */
	private void downcoumnprocess() {
		while(noBeepersPresent()) {
			putBeeper();
			move();
			if(beepersPresent()) {
				move();
			}
		}
		leftProcess();
	}
	/*
	 * Turn and go to the last column's start position.
	 * */
	private void leftProcess() {
		turnLeft();
		while(frontIsClear()) {
			move();
		}
		upProcess();
	}
	/*
	 * Finished the last column.
	 * */
	private void upProcess() {
		turnLeft();
		while(frontIsClear()) {
			move();
			if(noBeepersPresent()) {
				putBeeper();
			}
		}
		gotoleftProcess();
	}
	/*
	 * Go to the third column and go to the 1st Avenue and 13st Street.
	 * */
	private void gotoleftProcess() {
		turnLeft();
		move();
		while(rightIsClear()) {
			move();
		}
		turnLeft();
		while(frontIsClear()) {
			move();
			if(noBeepersPresent()) {
				putBeeper();
			}
		}
		turnLeft();
		while(frontIsClear()) {
			move();
			
		}
	}

}
