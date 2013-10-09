/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	/*
	 * Karel drops beepers along the first row
	 * Then Karel picks up the beepers, first from the east end,
	 *  then from the west, and so on
	 *  Karel puts the last Beeper in the middle of the first row
	 * */
	public void run () {
       putBeeperontheend();
       while(frontIsClear()) {
    	   weastAction();
    	   eastAction();
       }
      
    }
	/*
	 * Karel drops beepers in the second position between west and east.
	 * */
    private void putBeeperontheend() {
    	 move();
         putBeeper();
         while (frontIsClear()) {
             move();
         }
         turnAround();
         move();
         putBeeper();
    }
    /*
     * Karel goes all the way to the west, drops a beeper.
     * Karel goes to the eats of the dropped beeper, drops a beeper right next to it, 
     * then goes back and picks up the beeper at the very end
     * */
    private void weastAction() {
    	if(facingWest()) {
    		move();
    		if(noBeepersPresent()) {
    			putBeeper();
    		}
    		turnAround();
    		move();
    		pickBeeper();
    		turnAround();
    		move();
    		move();
    	}
    	checkBeeper();
    	turnAround();
    }
    /*
     * Karel checks the beeper.
     * Karel will move if nobeeperspresent and clear in front of it.
     * And also check the wall in front of it.
     * */
    private void checkBeeper() {
    	 while (noBeepersPresent()) {
             if(frontIsClear()) {
                     move();
                 }
             if(frontIsBlocked()) {
                 turnAround();
                 while(frontIsClear()) {
                     if(noBeepersPresent()) {
                         move();
                     }
                 }
             }
         }
    }
    /*
     * Karel goes all the way to the east, drops a beeper. 
     * Karel goes to the west of the dropped beeper, drops a beeper right next to it, 
     * then goes back and picks up the beeper at the very end.
     * */
    private void eastAction() {
    	if(facingEast()) {
    		 pickBeeper();
             move();
             if(noBeepersPresent()) {
                 putBeeper();
             }
             move();
         	checkBeeper();
        	turnAround();
    	}
    }

}
