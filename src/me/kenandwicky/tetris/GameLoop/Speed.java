package me.kenandwicky.tetris.GameLoop;

public class Speed {
	int[] levelspeed = {21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 11, 11, 10, 10, 10, 8, 8, 8, 5};
	/*
	 * Level  / 	   Speed		 // Level  / 	   Speed 
	 * 	 0				 21				  10			 11
	 *   1				 20				  11			 11
	 *   2				 19				  12			 11
	 *   3				 18				  13			 10   
	 *   4				 17				  14			 10
	 *   5				 16				  15			 10
	 *   6				 15				  16			  8
	 *   7				 14				  17			  8
	 *   8				 13				  18			  8
	 *   9			     12				  19			  5
	 */

	public int ReturnSpeed(int playerlevel) {
		return levelspeed[playerlevel];
	}
	
	
	
}
