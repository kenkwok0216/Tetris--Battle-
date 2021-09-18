package me.kenandwicky.tetris.GameLoop;

import me.kenandwicky.tetris.Tetris;

public class Loop implements Runnable {

	@Override
	public void run() {
		// if it can move down then move down if not "gamenext"
		if(Game.CheckMoveDown() == true) { //it can move down return true
			Game.moveDown();
		} else {
			Tetris.game.Next();
		}
		
	}	
	
	
}
