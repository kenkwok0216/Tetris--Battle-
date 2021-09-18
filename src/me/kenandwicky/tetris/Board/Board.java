package me.kenandwicky.tetris.Board;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;

import me.kenandwicky.tetris.Utils;
import me.kenandwicky.tetris.Tetromino.Tetromino;
import me.kenandwicky.tetris.Tetromino.TetrominoType;
import me.kenandwicky.tetris.tools.RandomClass;

public class Board {
		
	private static SettingsManager settings;
	public static Player player;
	private static TetrominoType[] bag = new TetrominoType[4];
	private static int NextPositionX, NextPositionY, NextPositionZ;
	private static int NamePositionX, NamePositionY, NamePositionZ;
	private static int HoldPositionX, HoldPositionY, HoldPositionZ;
	private static int BoardPositionX, BoardPositionY, BoardPositionZ;
	private static int ScorePositionX, ScorePositionY, ScorePositionZ;
	private static int LinePositionX, LinePositionY, LinePositionZ;
	private static int LevelPositionX, LevelPositionY, LevelPositionZ;
	private static int playerscore, playerlevel, playerline;
	public static Tetromino currentpiece;
	public static Tetromino holdpiece;
	
	
	private RandomClass rnd = new RandomClass();
	private boolean isHold = false;
	public static TetrominoType[][] board = new TetrominoType[10][21];
	
	public void building(Player player, SettingsManager settings) {
		Board.settings = settings;
		Board.player = player;
		if (PlayerDirection(player) == "South") {
			Location blockloc = player.getLocation();
			settings.getData().set("PlayerPosition.X", (int) blockloc.getX() - 1);
			settings.getData().set("PlayerPosition.Y", (int) blockloc.getY());
			settings.getData().set("PlayerPosition.Z", (int) blockloc.getZ());
			settings.saveData();
			blockloc = new Location(player.getWorld(),(int) blockloc.getX() - 15,(int) blockloc.getY() - 17,(int) blockloc.getZ() - 7);
			blockloc.getBlock().setType(Material.STRUCTURE_BLOCK);		
		} else {
			player.sendMessage("You must facing to South but you are now facing to " + PlayerDirection(player));
		}		
	}

	public static void setup() {
		if (settings != null) {
			String worldname = player.getWorld().getName().toString();
			int x = settings.getData().getInt("PlayerPosition.X");
			int y = settings.getData().getInt("PlayerPosition.Y");
			int z = settings.getData().getInt("PlayerPosition.Z");		
			Bukkit.getWorld(worldname).getBlockAt(x, y, z).setType(Material.AIR);
			x -= 14; y -= 17; z -= 7;
			Bukkit.getWorld(worldname).getBlockAt(x, y, z).setType(Material.AIR);
			x = settings.getData().getInt("PlayerPosition.X") + 16;
			y = settings.getData().getInt("PlayerPosition.Y") - 17;
			z = settings.getData().getInt("PlayerPosition.Z") + 32;
			for (int i = 0; i <= 31; i++) {
				for (int j = 0; j <= 25; j++) {
					if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.RED_CONCRETE) {
						SavePosition("HoldPosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.ORANGE_CONCRETE) {
						SavePosition("ScorePosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.YELLOW_CONCRETE) {
						SavePosition("LinePosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.GREEN_CONCRETE) {
						SavePosition("LevelPosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.BLUE_CONCRETE) {
						SavePosition("BoardPosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.LIGHT_BLUE_CONCRETE) {
						SavePosition("NextPosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.PURPLE_CONCRETE) {
						SavePosition("NamePosition", x - i, y + j, z);
						Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						BuildBanner(new Location(player.getWorld(), x - i, y + j, z), '?');
					}
				}
			}
			settings.saveData();
		} else {
			settings = SettingsManager.getInstance();
		}	
	}
	
	public void NextPiece() {
		if (bag[0] == null) {
			int y = rnd.TetrisRandom();
			TetrominoType starttype = TetrominoType.values()[y];
			Tetromino startpiece = new Tetromino(starttype);
			currentpiece = startpiece;
			for (int i = 1; i < 5; i++) {		
				int x = rnd.TetrisRandom();
				TetrominoType type = TetrominoType.values()[x];
				Tetromino piece = new Tetromino(type);
				bag[i - 1] = type;
				NextPositionX = settings.getData().getInt("NextPosition.X") - 1;
				NextPositionY = settings.getData().getInt("NextPosition.Y") + (4-i) * 5;
				NextPositionZ = settings.getData().getInt("NextPosition.Z") + 1;
				ClearPieceinBox(NextPositionX, NextPositionY, NextPositionZ);
				setPieceBlocks(NextPositionX, NextPositionY, NextPositionZ, piece, type);
			}
		} else {
			Tetromino nextpiece = new Tetromino(bag[0]);
			currentpiece = nextpiece;
			for (int i = 0; i < 3; i++) {
				bag[i] = bag[i+1];
			}
			int x = rnd.TetrisRandom();
			bag[3] = TetrominoType.values()[x];
			for (int i = 1; i < 5; i++) {
				Tetromino piece = new Tetromino(bag[i-1]);
				NextPositionX = settings.getData().getInt("NextPosition.X") - 1;
				NextPositionY = settings.getData().getInt("NextPosition.Y") + (4-i) * 5;
				NextPositionZ = settings.getData().getInt("NextPosition.Z") + 1;
				ClearPieceinBox(NextPositionX, NextPositionY, NextPositionZ);
				setPieceBlocks(NextPositionX, NextPositionY, NextPositionZ, piece, bag[i-1]);
			}
		}
				
	}
	
	public void ClearPieceinBox(int x, int y, int z) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	Bukkit.getWorld("world").getBlockAt(x - i, y + j, z).setType(Material.AIR);
            }
        }
	}
	
	public void HoldBox() {
		if(isHold == false) {
			Tetromino piece = currentpiece;
			TetrominoType type = currentpiece.type;
			if(type != TetrominoType.I) {
				setPieceBlocks(HoldPositionX, HoldPositionY, HoldPositionZ, piece, type);
				holdpiece = piece;
				NextPiece();
				isHold = true;
			} else {
				if(currentpiece.coords[3][1] == -1) { //this will happen if coords is {{-1,2}, {-1,1}, {-1,0}, {-1,1}}
					setPieceBlocks(HoldPositionX, HoldPositionY + 1, HoldPositionZ, piece, type);
					holdpiece = piece;
					NextPiece();
					isHold = true;
				} else {
					setPieceBlocks(HoldPositionX, HoldPositionY, HoldPositionZ, piece, type);
					holdpiece = piece;
					NextPiece();
					isHold = true;
				}
			}
		} else {
			Tetromino tmppiece = currentpiece;
			currentpiece = holdpiece;
			holdpiece = tmppiece;
			if(holdpiece.type != TetrominoType.I) {
				ClearPieceinBox(HoldPositionX, HoldPositionY, HoldPositionZ);
				ClearPieceinBox(HoldPositionX + 1, HoldPositionY, HoldPositionZ);
				setPieceBlocks(HoldPositionX, HoldPositionY, HoldPositionZ, holdpiece, holdpiece.type);	 
			} else {
				if(holdpiece.coords[3][1] == -1) { //this will happen if coords is {{-1,2}, {-1,1}, {-1,0}, {-1,1}}
					ClearPieceinBox(HoldPositionX, HoldPositionY, HoldPositionZ);
					ClearPieceinBox(HoldPositionX + 1, HoldPositionY, HoldPositionZ);
					setPieceBlocks(HoldPositionX, HoldPositionY + 1, HoldPositionZ, holdpiece, holdpiece.type);	 
				} else {
					ClearPieceinBox(HoldPositionX, HoldPositionY, HoldPositionZ);
					ClearPieceinBox(HoldPositionX + 1, HoldPositionY, HoldPositionZ);
					setPieceBlocks(HoldPositionX, HoldPositionY, HoldPositionZ, holdpiece, holdpiece.type);	 
				}
			}		

		}
	}
	
	
    public void setPieceBlocks(int coordX, int coordY, int coordZ, Tetromino piece, TetrominoType type) {	
    	for (int i = 0; i < 4; i++) {
            int x, y;   
            x = coordX + piece.coords[i][0];
            y = coordY + piece.coords[i][1];
            setBlock(x, y, coordZ, type);
        }
    }
    
    public static void setBlock(int x, int y, int z, TetrominoType t) {
        Utils.placeTetromino("world", x, y, z, t);
    }
	
	private static void SavePosition(String string, int x, int y, int z) {
		if (settings != null) {
			settings.getData().set(string + ".X", x);
			settings.getData().set(string + ".Y", y);
			settings.getData().set(string + ".Z", z);
		} else {
			settings = SettingsManager.getInstance();
		}

	}

	private static void BuildBanner(Location blockloc, char c) {
		BannerClass banners = new BannerClass();
		BannerMeta meta = (BannerMeta) banners.Make(c).getItemMeta();
		blockloc.getBlock().setType(banners.Make(c).getType());		
		if (blockloc.getBlock().getType() == banners.Make(c).getType()) {
			Banner banner = (Banner) blockloc.getBlock().getState();
			banner.setPatterns(meta.getPatterns());
			banner.update();
		}		
	}
	
	public static void NameUpdate(String s) {
		int x = NamePositionX;
		int y = NamePositionY;
		int z = NamePositionZ;
		String reverse = "";
		for(int i = s.length() - 1; i >= 0; i--) {
			if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				reverse = reverse + (char) (s.charAt(i) - ('a' - 'A'));
			} else {
				reverse = reverse + s.charAt(i);
			}	
		}
		for(int i = 0; i < s.length(); i++) {
			BuildBanner(new Location(player.getWorld(), x + i, y, z), reverse.charAt(i));
		}
		for(int i = 0; i < 18 - s.length(); i++) {
			BuildBanner(new Location(player.getWorld(), x + i + s.length(), y, z), '?');
		}
	}
	
	public static void ScoreUpdate(String s) {
		int x = ScorePositionX;
		int y = ScorePositionY;
		int z = ScorePositionZ;
		String reverse = "";
		for(int i = s.length() - 1; i >= 0; i--) {
			if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				reverse = reverse + (char) (s.charAt(i) - ('a' - 'A'));
			} else {
				reverse = reverse + s.charAt(i);
			}	
		}
		for(int i = 0; i < s.length(); i++) {
			BuildBanner(new Location(player.getWorld(), x + i, y, z), reverse.charAt(i));
		}
		for(int i = 0; i < 8 - s.length(); i++) {
			BuildBanner(new Location(player.getWorld(), x + i + s.length(), y, z), '?');
		}
	}
	
	public static void LineUpdate(String s) {
		int x = LinePositionX;
		int y = LinePositionY;
		int z = LinePositionZ;
		String reverse = "";
		for(int i = s.length() - 1; i >= 0; i--) {
			if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				reverse = reverse + (char) (s.charAt(i) - ('a' - 'A'));
			} else {
				reverse = reverse + s.charAt(i);
			}	
		}
		for(int i = 0; i < s.length(); i++) {
			BuildBanner(new Location(player.getWorld(), x + i, y, z), reverse.charAt(i));
		}
		for(int i = 0; i < 8 - s.length(); i++) {
			BuildBanner(new Location(player.getWorld(), x + i + s.length(), y, z), '?');
		}
	}
	
	public static void LevelUpdate(String s) {
		int x = LevelPositionX;
		int y = LevelPositionY;
		int z = LevelPositionZ;
		String reverse = "";
		for(int i = s.length() - 1; i >= 0; i--) {
			if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				reverse = reverse + (char) (s.charAt(i) - ('a' - 'A'));
			} else {
				reverse = reverse + s.charAt(i);
			}	
		}
		for(int i = 0; i < s.length(); i++) {
			BuildBanner(new Location(player.getWorld(), x + i, y, z), reverse.charAt(i));
		}
		for(int i = 0; i < 2 - s.length(); i++) {
			BuildBanner(new Location(player.getWorld(), x + i + s.length(), y, z), '0');
		}
	}
	
	
	//THIS METHOD IS A ADMIN TEST METHOD
	public static void initialize(Player player, SettingsManager settings) {
			Board.settings = settings;
			Board.player = player;
	}
	
	public void TetrisBoard(int boardX, int boardY, Tetromino piece, TetrominoType type) {
		int x = settings.getData().getInt("BoardPosition.X");
		int y = settings.getData().getInt("BoardPosition.Y");
		int z = settings.getData().getInt("BoardPosition.Z") + 1;
		setPieceBlocks(x - boardX, y + boardY, z, piece, type);		
	}
	
	public void SaveTetris(int boardX, int boardY) {
    	for (int i = 0; i < 4; i++) {
    		int CoordX = boardX - currentpiece.coords[i][0];
    		int CoordY = boardY + currentpiece.coords[i][1];
    		board[CoordX][CoordY] = currentpiece.type;
        }	     	
	}
	
	
	//The East and West 
	private String PlayerDirection(Player player) {
		float degrees = player.getLocation().getYaw();
        if (degrees < 22.5) { 
        	return "South";
        } else if (degrees < 67.5) {
        	return "Southwest";
        } else if (degrees < 112.5) {
        	return "West";
        } else if (degrees < 157.5) {
        	return "Northwest";
        } else if (degrees < 202.5) {
        	return "North";
        } else if (degrees < 247.5) {
        	return "Northeast";
        } else if (degrees < 292.5) {
        	return "East";
        } else if (degrees < 337.5) {
        	return "Southeast";
        } else {
        	return "South";
        }	
	}

	public boolean outOfBounds(int x, int y) {
		if (y < 0 || y > 21 || x < 0 || x > 9) {
			return true;
		} else {
			return false;
		}
	}

	public TetrominoType get(int x, int y) {
		return board[x][y];
	}

	public void Boardsetup() {
		settings.reloadConfig();
		HoldPositionX = settings.getData().getInt("HoldPosition.X") - 1;
		HoldPositionY = settings.getData().getInt("HoldPosition.Y");
		HoldPositionZ = settings.getData().getInt("HoldPosition.Z") + 1;
		BoardPositionX = settings.getData().getInt("BoardPosition.X");
		BoardPositionY = settings.getData().getInt("BoardPosition.Y");
		BoardPositionZ = settings.getData().getInt("BoardPosition.Z") + 1;
		NamePositionX = settings.getData().getInt("NamePosition.X");
		NamePositionY = settings.getData().getInt("NamePosition.Y");
		NamePositionZ = settings.getData().getInt("NamePosition.Z");
		ScorePositionX = settings.getData().getInt("ScorePosition.X") - 7;
		ScorePositionY = settings.getData().getInt("ScorePosition.Y");
		ScorePositionZ = settings.getData().getInt("ScorePosition.Z");
		LinePositionX = settings.getData().getInt("LinePosition.X") - 7;
		LinePositionY = settings.getData().getInt("LinePosition.Y");
		LinePositionZ = settings.getData().getInt("LinePosition.Z"); 
		LevelPositionX = settings.getData().getInt("LevelPosition.X") - 1;
		LevelPositionY = settings.getData().getInt("LevelPosition.Y");
		LevelPositionZ = settings.getData().getInt("LevelPosition.Z"); 
		playerscore = 0;
		ScoreUpdate("0");
		playerlevel = 0;
		LevelUpdate("0");
		playerline = 0;
		LineUpdate("0");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 21; j++) {
				board[i][j] = TetrominoType.Empty;
				setBlock(BoardPositionX - i, BoardPositionY + j, BoardPositionZ, board[i][j]);
			}
		}
		ClearPieceinBox(HoldPositionX, HoldPositionY, HoldPositionZ);
		ClearPieceinBox(HoldPositionX + 1, HoldPositionY, HoldPositionZ); //due to rotation, the position may be larger
	}

	public static void LineCheck() {
		int linecheck = 0;
		for (int i = 19; i >= 0; i--) {
			for (int j = 0; j < 10; j++) {
				if (board[j][i] == TetrominoType.Empty) {
					break;
				} else if (j == 9){
					ClearLine(i);
					linecheck++;
				}
			}
		}
		ScoreCalculation(linecheck);
	}
	
	private static void ScoreCalculation(int line) {
		if (line == 0) {
			return;
		} else if (line == 1) {
			playerscore += 40 * (getPlayerlevel() + 1);
			playerline += 1;
		} else if (line == 2) {
			playerscore += 100 * (getPlayerlevel() + 1);
			playerline += 2;
		} else if (line == 3) {
			playerscore += 300 * (getPlayerlevel() + 1);
			playerline += 3;
		} else if (line == 4) {
			playerscore += 1200 * (getPlayerlevel() + 1);
			playerline += 4;
		} 
		ScoreUpdate(Integer.toString(playerscore));
		LineUpdate(Integer.toString(playerline));
		if(isLevelNeedUpdate(playerlevel) == true) {
			playerlevel += 1;
			LevelUpdate(Integer.toString(getPlayerlevel()));
		}
	}
	
	
	private static boolean isLevelNeedUpdate(int level) {
		int[] updatelevel = {10, 30, 60, 100, 150, 210, 280, 360, 450, 550, 650, 750, 850, 950, 1050, 1150, 1250, 1360, 1480, 1610, 99999999};
		/*
		 * Level  / line needed to update // Level  / line needed to update 
		 * 	 0				10				  10			100
		 *   1				20				  11			100
		 *   2				30				  12			100
		 *   3				40				  13			100   
		 *   4				50				  14			100
		 *   5				60				  15			100
		 *   6				70				  16			110
		 *   7				80				  17			120
		 *   8				90				  18			130
		 *   9			   100				  19			max
		 */
		if(playerline >= updatelevel[level] ) {
			return true;
		} else {
			return false;
		}
	}

	//the following method is used to clear the whole line of the board
	public static void ClearLine(int line) {
		for (int i = 0; i < 10; i++) {
			for (int j = line; j < 20; j++) {
				board[i][j] = board[i][j + 1];
				setBlock(BoardPositionX - i, BoardPositionY + j, BoardPositionZ, board[i][j + 1]);
			}
		}
	}

	public static int getPlayerlevel() {
		return playerlevel;
	}


	public static boolean CheckLoss() {
		for(int i = 0; i < 10; i++) {
			if(board[i][20] != TetrominoType.Empty) {
				return true;
			} 
		}
		for(int i = 0; i < 4; i++) {
			int coordX = 3 - new Tetromino(bag[0]).coords[i][0];
			int coordY = 17 + new Tetromino(bag[0]).coords[i][1];
			if(board[coordX][coordY] != TetrominoType.Empty) {
				return true;
			}
		}
		
		return false;
	}
}
