package me.kenandwicky.tetris.GameLoop;

import me.kenandwicky.tetris.Tetris;
import me.kenandwicky.tetris.Board.Board;

public class Loop2 implements Runnable {

	@Override
	public void run() {
		
		// if it can move down then move down if not "gamenext"
		if(Game.CheckMoveDown(Board.player2.getPlayer()) == true) { //it can move down return true
			Game.moveDown(Board.player2.getPlayer());
		} else {
			Tetris.game.Next(Board.player2.getPlayer());
		}	
	}	
}
