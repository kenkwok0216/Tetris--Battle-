package me.kenandwicky.tetris.GameLoop;

import me.kenandwicky.tetris.Tetris;
import me.kenandwicky.tetris.Board.Board;

public class Loop1 implements Runnable {

	@Override
	public void run() {
		
		// if it can move down then move down if not "gamenext"
		if(Game.CheckMoveDown(Board.player1.getPlayer()) == true) { //it can move down return true
			Game.moveDown(Board.player1.getPlayer());
		} else {
			Tetris.game.Next(Board.player1.getPlayer());
		}	
	}	
}
