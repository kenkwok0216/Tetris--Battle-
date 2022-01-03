package me.kenandwicky.tetris.GameLoop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.kenandwicky.tetris.Tetris;
import me.kenandwicky.tetris.Board.Board;
import me.kenandwicky.tetris.Tetromino.TetrominoType;

public class Game {

	public static Board board;
	private static int[] xy1 = new int[2];
	private static int[] xy2 = new int[2];
	
	public Game(Board board) {
		Game.board = board;
		resetposition(1);
		resetposition(2);
		GameStart();
	}
	
	private void GameStart() {
		board.Boardsetup();		
		board.NextPiece(Board.player1.getPlayer());   //all the things is start
		board.NextPiece(Board.player2.getPlayer());   //all the things is start
		board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, Board.player1.getPlayer());
		board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, Board.currentpiece2.type, Board.player2.getPlayer());
	}
	
	public void Next(Player p) {
		if (p.getName() == Board.player1.getName()) {
			board.SaveTetris(xy1[0], xy1[1], p);
			resetposition(1);
			LineChecker(p);
			board.attack(p);
			if (Board.CheckLoss(p) == false) {
				board.NextPiece(p);
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, p);
			} else {
				Bukkit.getScheduler().cancelTask(Execute.gameLoopID1);
				Board.player1loss = true;
				if(Board.player2loss == false) {
					p.sendTitle(ChatColor.AQUA + "Game Over, Wait until opponent to end", ChatColor.YELLOW + "Your Score is " + Board.player1.getScore(), 10, 100, 10);
				} else {
					sendResultMessage();
				}
			}
		} else if (p.getName() == Board.player2.getName()) {
			board.SaveTetris(xy2[0], xy2[1], p);
			resetposition(2);
			LineChecker(p);
			board.attack(p);
			if (Board.CheckLoss(p) == false) {
				board.NextPiece(p);
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, Board.currentpiece2.type, p);
			} else {
				Bukkit.getScheduler().cancelTask(Execute.gameLoopID2);
				Board.player2loss = true;
				if(Board.player1loss == false) {
					p.sendTitle(ChatColor.AQUA + "Game Over, Wait until opponent to end", ChatColor.YELLOW + "Your Score is " + Board.player2.getScore(), 10, 100, 10);
				} else {
					sendResultMessage();
				}
			}
		}
	}

	private void sendResultMessage() {
		if(Board.player1.getScore() > Board.player2.getScore()) {
			Board.player1.getPlayer().sendTitle(ChatColor.AQUA + "Congradulation!!", ChatColor.YELLOW + "Your Score is " + Board.player1.getScore() , 10, 100, 10);
			Board.player2.getPlayer().sendTitle(ChatColor.RED + "You Loss!", ChatColor.YELLOW + "Your Score is " + Board.player2.getScore() , 10, 100, 10);
		} else if(Board.player1.getScore() == Board.player2.getScore()){
			Board.player1.getPlayer().sendTitle(ChatColor.AQUA + "DRAW!", ChatColor.YELLOW + "Your Score is " + Board.player1.getScore() , 10, 100, 10);
			Board.player2.getPlayer().sendTitle(ChatColor.AQUA + "DRAW!", ChatColor.YELLOW + "Your Score is " + Board.player2.getScore(), 10, 100, 10);
		} else {
			Board.player1.getPlayer().sendTitle(ChatColor.RED + "You Loss!", ChatColor.YELLOW + "Your Score is " + Board.player1.getScore() , 10, 100, 10);
			Board.player2.getPlayer().sendTitle(ChatColor.AQUA + "Congradulation!!", ChatColor.YELLOW + "Your Score is " + Board.player2.getScore() , 10, 100, 10);
		}
		
	}

	public static void moveDown(Player p) {
		if (p.getName() == Board.player1.getName()) {
			if (checkCollision(xy1[0], xy1[1] - 1, p) == true) {
				return;
			} else {
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, TetrominoType.Empty, p);
				xy1[1] -= 1;
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, p);
			}
		} else if ((p.getName() == Board.player2.getName())) {
			if (checkCollision(xy2[0], xy2[1] - 1, p) == true) {
				return;
			} else {
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, TetrominoType.Empty, p);
				xy2[1] -= 1;
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, Board.currentpiece2.type, p);
			}
		}
		

	}

	public static void moveLeft(Player p) {
		if (p.getName() == Board.player1.getName()) {
			if (checkCollision(xy1[0] - 1, xy1[1], p) == true) {
				return;
			} else {
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, TetrominoType.Empty, p);
				xy1[0] -= 1;
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, p);
			}	
		} else if (p.getName() == Board.player2.getName()) {
			if (checkCollision(xy2[0] - 1, xy2[1], p) == true) {
				return;
			} else {
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, TetrominoType.Empty, p);
				xy2[0] -= 1;
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, Board.currentpiece2.type, p);
			}	
		}
		

	}

	public static void moveRight(Player p) {
		if (p.getName() == Board.player1.getName()) {
			if (checkCollision(xy1[0] + 1, xy1[1], p) == true) {
				return;
			} else {
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, TetrominoType.Empty, p);
				xy1[0] += 1;
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, p);
			}
		} else if ((p.getName() == Board.player2.getName())) {
			if (checkCollision(xy2[0] + 1, xy2[1], p) == true) {
				return;
			} else {
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, TetrominoType.Empty, p);
				xy2[0] += 1;
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, Board.currentpiece2.type, p);
			}
		}
		

	}

	public static void rotatedLeft(Player p) {
		if (p.getName() == Board.player1.getName()) {
			Board.currentpiece1.rotateLeft();
			if (checkCollision(xy1[0], xy1[1], p) == true) {
				Board.currentpiece1.rotateRight();
				return;
			} else {
				Board.currentpiece1.rotateRight();
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, TetrominoType.Empty, p);
				Board.currentpiece1.rotateLeft();
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, p);
			}		
		} else if (p.getName() == Board.player2.getName()) {
			Board.currentpiece2.rotateLeft();
			if (checkCollision(xy2[0], xy2[1], p) == true) {
				Board.currentpiece2.rotateRight();
				return;
			} else {
				Board.currentpiece2.rotateRight();
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, TetrominoType.Empty, p);
				Board.currentpiece2.rotateLeft();
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, Board.currentpiece2.type, p);
			}		
		}
		
					
	}

	public static void rotatedRight(Player p) {
		if (p.getName() == Board.player1.getName()) {
			Board.currentpiece1.rotateRight();
			if (checkCollision(xy1[0], xy1[1], p) == true) {
				Board.currentpiece1.rotateLeft();
				return;
			} else {
				Board.currentpiece1.rotateLeft();
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, TetrominoType.Empty, p);
				Board.currentpiece1.rotateRight();
				board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, p);
			}
		} else if (p.getName() == Board.player2.getName()) {
			Board.currentpiece2.rotateRight();
			if (checkCollision(xy2[0], xy2[1], p) == true) {
				Board.currentpiece2.rotateLeft();
				return;
			} else {
				Board.currentpiece2.rotateLeft();
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, TetrominoType.Empty, p);
				Board.currentpiece2.rotateRight();
				board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, Board.currentpiece2.type, p);
			}
		}
		

	}
	
	//to control the hold piece
	public static void holdPiece(Player p) {
		if (p.getName() == Board.player1.getName()) {
			board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, TetrominoType.Empty, p);
			board.HoldBox(p);
			resetposition(1);
			board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, p);
		} else if (p.getName() == Board.player2.getName()) {
			board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece2, TetrominoType.Empty, p);
			board.HoldBox(p);
			resetposition(2);
			board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece2, Board.currentpiece2.type, p);
		}

	}
	
	public static void LineChecker(Player p) {
		Board.LineCheck(p);
	}
	
	private static boolean checkCollision(int x, int y, Player p) {
		if (p.getName() == Board.player1.getName()) {
			boolean collision = false;
			for(int i = 0; i < 4; i++) {
				int coordX = x - Board.currentpiece1.coords[i][0];
				int coordY = y + Board.currentpiece1.coords[i][1];
				if(board.outOfBounds(coordX, coordY) == true || board.get(coordX,coordY, p) != TetrominoType.Empty) {
					collision = true;
					break;
				}
			}
			return collision;
		} else if (p.getName() == Board.player2.getName()) {
			boolean collision = false;
			for(int i = 0; i < 4; i++) {
				int coordX = x - Board.currentpiece2.coords[i][0];
				int coordY = y + Board.currentpiece2.coords[i][1];
				if(board.outOfBounds(coordX, coordY) == true || board.get(coordX,coordY, p) != TetrominoType.Empty) {
					collision = true;
					break;
				}
			}
			return collision;
		}
		return false;
		

	}
	
	private static void resetposition(int number) {
		if(number == 1) {
			xy1[0] = 3;
			xy1[1] = 17;
		} else {
			xy2[0] = 3;
			xy2[1] = 17;
		}
		

	}

	public static boolean CheckMoveDown(Player p) {
		if (p.getName() == Board.player1.getName()) {
			if (checkCollision(xy1[0], xy1[1] - 1, p) == true) {
				return false;
			} else {
				return true;
			}
		} else if (p.getName() == Board.player2.getName()) {
			if (checkCollision(xy2[0], xy2[1] - 1, p) == true) {
				return false;
			} else {
				return true;
			}
		}
		
		return true;
	}

	public static void HardDrop(Player p) {
		if (p.getName() == Board.player1.getName()) {
			for(int i = xy1[1]; i >= -1; i--) {  //from the position of the Tetromino first
				if(checkCollision(xy1[0], i - 1, p) == true) { //collision occur when further move down, i.e. the previous is the allowed one
					board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, TetrominoType.Empty, p);
					xy1[1] = i;
					board.TetrisBoard(xy1[0], xy1[1], Board.currentpiece1, Board.currentpiece1.type, p);
					break;
				} else {
					continue;
				}
			}
			Tetris.game.Next(p);
		} else if (p.getName() == Board.player2.getName()) {
			for(int i = xy2[1]; i >= -1; i--) {  //from the position of the Tetromino first
				if(checkCollision(xy2[0], i - 1, p) == true) { //collision occur when further move down, i.e. the previous is the allowed one
					board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, TetrominoType.Empty, p);
					xy2[1] = i;
					board.TetrisBoard(xy2[0], xy2[1], Board.currentpiece2, Board.currentpiece2.type, p);
					break;
				} else {
					continue;
				}
			}
			Tetris.game.Next(p);
		}
		

	}
	
	
	
	
	
	
}
