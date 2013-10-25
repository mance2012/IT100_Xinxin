/*
 * File: Breakout.java
 * -------------------
 * Name:xinxin ma
 * With jianbin's help
 * Reference BouncingBall.java and Graphics Events Examples and getColliderObject method from Internet.
 * Section Leader:jianbin
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;
	
/**Number of bricks */  
    private static  int brickNum = NBRICKS_PER_ROW * NBRICK_ROWS; 

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/**Delay time for the animation */  
    private static final int DELAY = 15; 
/* Method: run() */
/** Runs the Breakout program. */
	
	//Public variable
	private GObject collider;
	private GOval ball;
	private Color color;
	private GRect paddle;
	public GPoint mouse;
	private double vx,vy;//Parameters of ball's speed
	private RandomGenerator rgen = new RandomGenerator();//Random speed
	
	public void run() {
		putBricks();
		drawPaddle();
		play();
	}
	
	private void putBricks() {
		
		/*
		 * 1.Put Bricks in the top of the screen.
		 * 2.Set different colors
		 * */
		for(int i = 0; i < NBRICK_ROWS; i++) {
			
			for(int j = 0; j < NBRICKS_PER_ROW; j++) {
				
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				//set colors
				
				brick.setFilled(true);
				
				switch(i) {
					case 0: color = Color.RED; break;
					case 1: color = Color.RED; break;
	                case 2: color = Color.ORANGE; break;
	                case 3: color = Color.ORANGE; break;  
	                case 4: color = Color.YELLOW; break;
	                case 5: color = Color.YELLOW; break;
	                case 6: color = Color.GREEN; break;
	                case 7: color = Color.GREEN; break;
	                case 8: color = Color.CYAN; break;
	                case 9: color = Color.CYAN; break;
				}
				brick.setColor(color);
				int x = (BRICK_WIDTH + BRICK_SEP) * j ;  
                int y = BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP)* i;  
                add(brick,x,y);  
			}
		}
		
	}
	
	private void drawPaddle() {
		/*
		 * Draw the Paddle
		 * */
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		int x = (getWidth()-PADDLE_WIDTH)/2;
		int y = getHeight() - PADDLE_HEIGHT - BRICK_Y_OFFSET;
		add(paddle,x,y);
	}
	
	private void play(){  
        
        /**
         * 1.Start game with MouseClick
         * 2.Paddle moving follow the Mouse
         * 3.Create a ball in the middle of the screen
         */  
          
        //Game start 
        addMouseListeners();  
        waitForClick(); 
        ball = new GOval(BALL_RADIUS,BALL_RADIUS);  
        ball.setFilled(true);  
        add(ball, getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS);  
        
        //Parameters of speed
        vx = rgen.nextDouble(1.0,5.0);  
        if (rgen.nextBoolean(0.5)) vx = -vx;  
        vy = 4.0;  
        /**
         * 1.Moving the ball, and Y's speed fix, x's speed is random
         * 2.If ball's position is under the paddle, the game will be over 
         * 3.If ball hit the wall and then will change the direction 
         */  
        while (true){  
            ball.move(vx,vy);  
            int i = 0;  //Times of Game 
            //If ball's position is under the paddle,the game will over
            if (ball.getY()+2*BALL_RADIUS > paddle.getY()){ 
                i++;  
                if (i>2){  
                    gameover();  
                    waitForClick();  
                    break;  
                } else {  
                    remove(ball);  
                    pause(1000);  
                    ball = new GOval(BALL_RADIUS,BALL_RADIUS);  
                    ball.setFilled(true);  
                    add(ball, getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS);  
                }  
                  
            }  
            //IF the ball hit the wall  
            if (ball.getX()<0 || ball.getX()+2*BALL_RADIUS>getWidth()) vx= -vx;  
            if (ball.getY()<0 ) vy= -vy;  
            
            pause(DELAY);  
            collider = getColliderObject();  
            if (collider == paddle){  
                vy = -vy;  
                vx = rgen.nextDouble(1.0, 5.0);  
                if (rgen.nextBoolean(0.5)) vx=-vx;  
            } else if (collider != null) {  
                vy = -vy;  
                remove(collider);  
                brickNum--;  
                //if the brick number is 0, so you win the game. 
                if (brickNum == 0) {  
                    win();  
                    waitForClick();  
                    break;  
                }  
            }  
        }  
        removeAll();  
    }
	
	/*
	 * If the game over, clean screen
	 * */
	private void gameover() {
		removeAll();
		GLabel win = new GLabel("You lose the game!!");
		add(win,(getWidth()-win.getWidth())/2,(getHeight()-win.getAscent())/2);
	}
	
	/*
	 * If you win the game, clean screen
	 * Give the prompt 
	 * */
	private void win() {
		removeAll();
		GLabel win = new GLabel("Congratulations! You win!");
		add(win,(getWidth()-win.getWidth())/2,(getHeight()-win.getAscent())/2);
	}
	
	/**
	 * Get the object of collider
	 * **/
	 private GObject getColliderObject() {  
	          
	        GObject check = getElementAt(ball.getX(),ball.getY());  //Top left corner  
	        if (check == null)  
	            check = getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY());  //Top right corner 
	            if (check == null)  
	                check = getElementAt(ball.getX(),ball.getY()+2*BALL_RADIUS);  //Bottom left corner 
	                
	        return check;  
	    }  
	 
	 
	//Paddle move follow the Mouse
	public void mouseClicked(MouseEvent e)
	{
	    mouse = new GPoint(e.getPoint());
		paddle.move(e.getX()-paddle.getX()-PADDLE_WIDTH/2, 0);
	}
	
	public void mouseDragged(MouseEvent e) {
		if(mouse != null) {
			paddle.move(e.getX()-mouse.getX(), 0);
			mouse = new GPoint(e.getPoint());
			
		}
		
	}
	
	

}
