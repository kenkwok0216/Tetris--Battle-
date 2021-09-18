package me.kenandwicky.tetris.GameLoop;

import org.bukkit.Bukkit;

import me.kenandwicky.tetris.Tetris;
import me.kenandwicky.tetris.Board.Board;
import me.kenandwicky.tetris.Tetromino.TetrominoType;

public class Game {

	public static Board board;
	private static int[] xy = new int[2];
	
	public Game(Board board) {
		Game.board = board;
		resetposition();
		GameStart();
	}
	
	private void GameStart() {
		board.Boardsetup();		
		board.NextPiece();   //all the things is start
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		
	}
	
	public void Next() {
		board.SaveTetris(xy[0], xy[1]);
		resetposition();
		LineChecker();
		if (Board.CheckLoss() == false) {
			board.NextPiece();
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		} else {
			Bukkit.getScheduler().cancelTask(Execute.gameLoopID);
			Tetris.isStart = false;
			Board.player.sendMessage("you loss");
		}
	}

	public static void moveDown() {
		if (checkCollision(xy[0], xy[1] - 1) == true) {
			return;
		} else {
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
			xy[1] -= 1;
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		}
	}

	public static void moveLeft() {
		if (checkCollision(xy[0] - 1, xy[1]) == true) {
			return;
		} else {
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
			xy[0] -= 1;
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		}	
	}

	public static void moveRight() {
		if (checkCollision(xy[0] + 1, xy[1]) == true) {
			return;
		} else {
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
			xy[0] += 1;
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		}
	}

	public static void rotatedLeft() {
		Board.currentpiece.rotateLeft();
		if (checkCollision(xy[0], xy[1]) == true) {
			Board.currentpiece.rotateRight();
			return;
		} else {
			Board.currentpiece.rotateRight();
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
			Board.currentpiece.rotateLeft();
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		}							
	}

	public static void rotatedRight() {
		Board.currentpiece.rotateRight();
		if (checkCollision(xy[0], xy[1]) == true) {
			Board.currentpiece.rotateLeft();
			return;
		} else {
			Board.currentpiece.rotateLeft();
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
			Board.currentpiece.rotateRight();
			board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		}
	}
	
	//to control the hold piece
	public static void holdPiece() {
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
		board.HoldBox();
		resetposition();
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
	}
	
	public static void LineChecker() {
		Board.LineCheck();
	}
	
	private static boolean checkCollision(int x, int y) {
		boolean collision = false;
		for(int i = 0; i < 4; i++) {
			int coordX = x - Board.currentpiece.coords[i][0];
			int coordY = y + Board.currentpiece.coords[i][1];
			if(board.outOfBounds(coordX, coordY) == true || board.get(coordX,coordY) != TetrominoType.Empty) {
				collision = true;
				break;
			}
		}
		return collision;
	}
	
	private static void resetposition() {
		xy[0] = 3;
		xy[1] = 17;
	}

	public static boolean CheckMoveDown() {
		if (checkCollision(xy[0], xy[1] - 1) == true) {
			return false;
		} else {
			return true;
		}
	}

	public static void HardDrop() {
		for(int i = xy[1]; i >= -1; i--) {  //from the position of the Tetromino first
			if(checkCollision(xy[0], i - 1) == true) { //collision occur when further move down, i.e. the previous is the allowed one
				board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
				xy[1] = i;
				board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
				break;
			} else {
				continue;
			}
		}
		Tetris.game.Next();
	}
	
	
	
	
	
}
