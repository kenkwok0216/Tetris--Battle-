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
import me.kenandwicky.tetris.tools.TetrisPlayer;

public class Board {
		
	private static SettingsManager settings;
	public static Player player;
	
	public static Player p1, p2;
	public static TetrisPlayer player1, player2;
	
	private static TetrominoType[] bag1 = new TetrominoType[4];
	private static TetrominoType[] bag2 = new TetrominoType[4];
	
	private static int NextPositionX1, NextPositionY1, NextPositionZ1;
	private static int NextPositionX2, NextPositionY2, NextPositionZ2;
	private static int NamePositionX1, NamePositionY1, NamePositionZ1;
	private static int NamePositionX2, NamePositionY2, NamePositionZ2;
	private static int HoldPositionX1, HoldPositionY1, HoldPositionZ1;
	private static int HoldPositionX2, HoldPositionY2, HoldPositionZ2;
	private static int BoardPositionX1, BoardPositionY1, BoardPositionZ1;
	private static int BoardPositionX2, BoardPositionY2, BoardPositionZ2;
	private static int ScorePositionX1, ScorePositionY1, ScorePositionZ1;
	private static int ScorePositionX2, ScorePositionY2, ScorePositionZ2;
	private static int LinePositionX1, LinePositionY1, LinePositionZ1;
	private static int LinePositionX2, LinePositionY2, LinePositionZ2;
	private static int LevelPositionX1, LevelPositionY1, LevelPositionZ1;
	private static int LevelPositionX2, LevelPositionY2, LevelPositionZ2;
	
	public static boolean player1loss;
	public static boolean player2loss;
	
	//private static int playerscore, playerlevel, playerline;
	public static Tetromino currentpiece1, currentpiece2;
	
	public static Tetromino holdpiece1, holdpiece2;
	
	
	private RandomClass rnd = new RandomClass();
	private boolean isHold1 = false, isHold2 = false;
	
	public static TetrominoType[][] board1 = new TetrominoType[10][21];
	public static TetrominoType[][] board2 = new TetrominoType[10][21];
	
	public void building(Player player, SettingsManager settings) {
		Board.settings = settings;
		Board.player = player;
		if (PlayerDirection(player) == "South") {
			Location blockloc = player.getLocation();
			settings.getData().set("Player1Position.X", (int) blockloc.getX() - 1);
			settings.getData().set("Player1Position.Y", (int) blockloc.getY());
			settings.getData().set("Player1Position.Z", (int) blockloc.getZ());
			settings.getData().set("Player2Position.X", (int) blockloc.getX() - 25);
			settings.getData().set("Player2Position.Y", (int) blockloc.getY());
			settings.getData().set("Player2Position.Z", (int) blockloc.getZ());			
			settings.saveData();
			blockloc = new Location(player.getWorld(),(int) blockloc.getX() - 15,(int) blockloc.getY() - 17,(int) blockloc.getZ() - 7);
			blockloc.getBlock().setType(Material.STRUCTURE_BLOCK);
			blockloc = new Location(player.getWorld(),(int) blockloc.getX() - 33,(int) blockloc.getY(),(int) blockloc.getZ());
			blockloc.getBlock().setType(Material.STRUCTURE_BLOCK);
		} else {
			player.sendMessage("You must facing to South but you are now facing to " + PlayerDirection(player));
		}		
	}

	public static void setup() {
		if (settings != null) {
			String worldname = player.getWorld().getName().toString();
			int x = settings.getData().getInt("Player1Position.X");
			int y = settings.getData().getInt("Player1Position.Y");
			int z = settings.getData().getInt("Player1Position.Z");		
			Bukkit.getWorld(worldname).getBlockAt(x, y, z).setType(Material.AIR);
			x -= 14; y -= 17; z -= 7;
			Bukkit.getWorld(worldname).getBlockAt(x, y, z).setType(Material.AIR);
			x -= 33;
			Bukkit.getWorld(worldname).getBlockAt(x, y, z).setType(Material.AIR);
			x = settings.getData().getInt("Player1Position.X") + 16;
			y = settings.getData().getInt("Player1Position.Y") - 17;
			z = settings.getData().getInt("Player1Position.Z") + 32;
			for (int i = 0; i <= 64; i++) {
				for (int j = 0; j <= 25; j++) {
					if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.RED_CONCRETE) {
						if(i <= 31) {
							SavePosition("HoldPosition1", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						} else {
							SavePosition("HoldPosition2", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						}
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.ORANGE_CONCRETE) {
						if(i <= 31) {
							SavePosition("ScorePosition1", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						} else {
							SavePosition("ScorePosition2", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						}												
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.YELLOW_CONCRETE) {
						if(i <= 31) {
							SavePosition("LinePosition1", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						} else {
							SavePosition("LinePosition2", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						}
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.GREEN_CONCRETE) {
						if(i <= 31) {
							SavePosition("LevelPosition1", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						} else {
							SavePosition("LevelPosition2", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						}	
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.BLUE_CONCRETE) {
						if(i <= 31) {
							SavePosition("BoardPosition1", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						} else {
							SavePosition("BoardPosition2", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						}	
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.LIGHT_BLUE_CONCRETE) {
						if(i <= 31) {
							SavePosition("NextPosition1", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						} else {
							SavePosition("NextPosition2", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
						}	
					} else if (Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).getType() == Material.PURPLE_CONCRETE) {
						if(i <= 31) {
							SavePosition("NamePosition1", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
							BuildBanner(new Location(player.getWorld(), x - i, y + j, z), '?');
						} else {
							SavePosition("NamePosition2", x - i, y + j, z);
							Bukkit.getWorld(worldname).getBlockAt(x - i, y + j, z).setType(Material.AIR);
							BuildBanner(new Location(player.getWorld(), x - i, y + j, z), '?');;
						}	
					}
				}
			}
			settings.saveData();
		} else {
			settings = SettingsManager.getInstance();
		}	
	}
	
	

	public void NextPiece(Player p) {
		if (p.getName() == player1.getName()) {
			if (bag1[0] == null) {
				int y = rnd.TetrisRandom();
				TetrominoType starttype = TetrominoType.values()[y];
				Tetromino startpiece = new Tetromino(starttype);
				currentpiece1 = startpiece;
				for (int i = 1; i < 5; i++) {		
					int x = rnd.TetrisRandom();
					TetrominoType type = TetrominoType.values()[x];
					Tetromino piece = new Tetromino(type);
					bag1[i - 1] = type;
					NextPositionX1 = settings.getData().getInt("NextPosition1.X") - 1;
					NextPositionY1 = settings.getData().getInt("NextPosition1.Y") + (4-i) * 5;
					NextPositionZ1 = settings.getData().getInt("NextPosition1.Z") + 1;
					ClearPieceinBox(NextPositionX1, NextPositionY1, NextPositionZ1);
					setPieceBlocks(NextPositionX1, NextPositionY1, NextPositionZ1, piece, type);
				}
			} else {
				Tetromino nextpiece = new Tetromino(bag1[0]);
				currentpiece1 = nextpiece;
				for (int i = 0; i < 3; i++) {
					bag1[i] = bag1[i+1];
				}
				int x = rnd.TetrisRandom();
				bag1[3] = TetrominoType.values()[x];
				for (int i = 1; i < 5; i++) {
					Tetromino piece = new Tetromino(bag1[i-1]);
					NextPositionX1 = settings.getData().getInt("NextPosition1.X") - 1;
					NextPositionY1 = settings.getData().getInt("NextPosition1.Y") + (4-i) * 5;
					NextPositionZ1 = settings.getData().getInt("NextPosition1.Z") + 1;
					ClearPieceinBox(NextPositionX1, NextPositionY1, NextPositionZ1);
					setPieceBlocks(NextPositionX1, NextPositionY1, NextPositionZ1, piece, bag1[i-1]);
				}
			}
		} else if (p.getName() == player2.getName()) {
			if (bag2[0] == null) {
				int y = rnd.TetrisRandom();
				TetrominoType starttype = TetrominoType.values()[y];
				Tetromino startpiece = new Tetromino(starttype);
				currentpiece2 = startpiece;
				for (int i = 1; i < 5; i++) {		
					int x = rnd.TetrisRandom();
					TetrominoType type = TetrominoType.values()[x];
					Tetromino piece = new Tetromino(type);
					bag2[i - 1] = type;
					NextPositionX2 = settings.getData().getInt("NextPosition2.X") - 1;
					NextPositionY2 = settings.getData().getInt("NextPosition2.Y") + (4-i) * 5;
					NextPositionZ2 = settings.getData().getInt("NextPosition2.Z") + 1;
					ClearPieceinBox(NextPositionX2, NextPositionY2, NextPositionZ2);
					setPieceBlocks(NextPositionX2, NextPositionY2, NextPositionZ2, piece, type);
				}
			} else {
				Tetromino nextpiece = new Tetromino(bag2[0]);
				currentpiece2 = nextpiece;
				for (int i = 0; i < 3; i++) {
					bag2[i] = bag2[i+1];
				}
				int x = rnd.TetrisRandom();
				bag2[3] = TetrominoType.values()[x];
				for (int i = 1; i < 5; i++) {
					Tetromino piece = new Tetromino(bag2[i-1]);
					NextPositionX2 = settings.getData().getInt("NextPosition2.X") - 1;
					NextPositionY2 = settings.getData().getInt("NextPosition2.Y") + (4-i) * 5;
					NextPositionZ2 = settings.getData().getInt("NextPosition2.Z") + 1;
					ClearPieceinBox(NextPositionX2, NextPositionY2, NextPositionZ2);
					setPieceBlocks(NextPositionX2, NextPositionY2, NextPositionZ2, piece, bag2[i-1]);
				}
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
	

	public void HoldBox(Player p) {
		if(p.getName() == player1.getName()) {
			if(isHold1 == false) {
				Tetromino piece = currentpiece1;
				TetrominoType type = currentpiece1.type;
				if(type != TetrominoType.I) {
					setPieceBlocks(HoldPositionX1, HoldPositionY1, HoldPositionZ1, piece, type);
					holdpiece1 = piece;
					NextPiece(p);
					isHold1 = true;
				} else {
					if(currentpiece1.coords[3][1] == -1) { //this will happen if coords is {{-1,2}, {-1,1}, {-1,0}, {-1,1}}
						setPieceBlocks(HoldPositionX1, HoldPositionY1 + 1, HoldPositionZ1, piece, type);
						holdpiece1 = piece;
						NextPiece(p);
						isHold1 = true;
					} else {
						setPieceBlocks(HoldPositionX1, HoldPositionY1, HoldPositionZ1, piece, type);
						holdpiece1 = piece;
						NextPiece(p);
						isHold1 = true;
					}
				}
			} else {
				Tetromino tmppiece = currentpiece1;
				currentpiece1 = holdpiece1;
				holdpiece1 = tmppiece;
				if(holdpiece1.type != TetrominoType.I) {
					ClearPieceinBox(HoldPositionX1, HoldPositionY1, HoldPositionZ1);
					ClearPieceinBox(HoldPositionX1 + 1, HoldPositionY1, HoldPositionZ1);
					setPieceBlocks(HoldPositionX1, HoldPositionY1, HoldPositionZ1, holdpiece1, holdpiece1.type);	 
				} else {
					if(holdpiece1.coords[3][1] == -1) { //this will happen if coords is {{-1,2}, {-1,1}, {-1,0}, {-1,1}}
						ClearPieceinBox(HoldPositionX1, HoldPositionY1, HoldPositionZ1);
						ClearPieceinBox(HoldPositionX1 + 1, HoldPositionY1, HoldPositionZ1);
						setPieceBlocks(HoldPositionX1, HoldPositionY1 + 1, HoldPositionZ1, holdpiece1, holdpiece1.type);	 
					} else {
						ClearPieceinBox(HoldPositionX1, HoldPositionY1, HoldPositionZ1);
						ClearPieceinBox(HoldPositionX1 + 1, HoldPositionY1, HoldPositionZ1);
						setPieceBlocks(HoldPositionX1, HoldPositionY1, HoldPositionZ1, holdpiece1, holdpiece1.type);	 
					}
				}		

			}
		} else if (p.getName() == player2.getName()) {
			if(isHold2 == false) {
				Tetromino piece = currentpiece2;
				TetrominoType type = currentpiece2.type;
				if(type != TetrominoType.I) {
					setPieceBlocks(HoldPositionX2, HoldPositionY2, HoldPositionZ2, piece, type);
					holdpiece2 = piece;
					NextPiece(p);
					isHold2 = true;
				} else {
					if(currentpiece2.coords[3][1] == -1) { //this will happen if coords is {{-1,2}, {-1,1}, {-1,0}, {-1,1}}
						setPieceBlocks(HoldPositionX2, HoldPositionY2 + 1, HoldPositionZ2, piece, type);
						holdpiece2 = piece;
						NextPiece(p);
						isHold2 = true;
					} else {
						setPieceBlocks(HoldPositionX2, HoldPositionY2, HoldPositionZ2, piece, type);
						holdpiece2 = piece;
						NextPiece(p);
						isHold2 = true;
					}
				}
			} else {
				Tetromino tmppiece = currentpiece2;
				currentpiece2 = holdpiece2;
				holdpiece2 = tmppiece;
				if(holdpiece2.type != TetrominoType.I) {
					ClearPieceinBox(HoldPositionX2, HoldPositionY2, HoldPositionZ2);
					ClearPieceinBox(HoldPositionX2 + 1, HoldPositionY2, HoldPositionZ2);
					setPieceBlocks(HoldPositionX2, HoldPositionY2, HoldPositionZ2, holdpiece2, holdpiece2.type);	 
				} else {
					if(holdpiece2.coords[3][1] == -1) { //this will happen if coords is {{-1,2}, {-1,1}, {-1,0}, {-1,1}}
						ClearPieceinBox(HoldPositionX2, HoldPositionY2, HoldPositionZ2);
						ClearPieceinBox(HoldPositionX2 + 1, HoldPositionY2, HoldPositionZ2);
						setPieceBlocks(HoldPositionX2, HoldPositionY2 + 1, HoldPositionZ2, holdpiece2, holdpiece2.type);	 
					} else {
						ClearPieceinBox(HoldPositionX2, HoldPositionY2, HoldPositionZ2);
						ClearPieceinBox(HoldPositionX2 + 1, HoldPositionY2, HoldPositionZ2);
						setPieceBlocks(HoldPositionX2, HoldPositionY2, HoldPositionZ2, holdpiece2, holdpiece2.type);	 
					}
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
		String reverse = "";
		//name position 1
		NamePositionX1 = settings.getData().getInt("NamePosition1.X");
		NamePositionY1 = settings.getData().getInt("NamePosition1.Y");
		NamePositionZ1 = settings.getData().getInt("NamePosition1.Z");
		//name position 2
		NamePositionX2 = settings.getData().getInt("NamePosition2.X");
		NamePositionY2 = settings.getData().getInt("NamePosition2.Y");
		NamePositionZ2 = settings.getData().getInt("NamePosition2.Z");
		if(s == player1.getName()) {
			int x = NamePositionX1;
			int y = NamePositionY1;
			int z = NamePositionZ1;
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
		} else if (s == player2.getName()) {
			int x = NamePositionX2;
			int y = NamePositionY2;
			int z = NamePositionZ2;
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
	}
	
	public static void ScoreUpdate(TetrisPlayer tp) {
		if(player1.getName() == tp.getName()) {
			int x = ScorePositionX1;
			int y = ScorePositionY1;
			int z = ScorePositionZ1;
			String s = Integer.toString(tp.getScore());
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
		} else if(player2.getName() == tp.getName()) {
			int x = ScorePositionX2;
			int y = ScorePositionY2;
			int z = ScorePositionZ2;
			String s = Integer.toString(tp.getScore());
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
						

	}
	
	public static void LineUpdate(TetrisPlayer tp) {
		if(player1.getName() == tp.getName()) {
			int x = LinePositionX1;
			int y = LinePositionY1;
			int z = LinePositionZ1;
			String s = Integer.toString(tp.getLine());
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
		} else if(player2.getName() == tp.getName()) {
			int x = LinePositionX2;
			int y = LinePositionY2;
			int z = LinePositionZ2;
			String s = Integer.toString(tp.getLine());
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
					

	}
	
	public static void LevelUpdate(TetrisPlayer tp) {
		if(player1.getName() == tp.getName()) {
			int x = LevelPositionX1;
			int y = LevelPositionY1;
			int z = LevelPositionZ1;
			String s = Integer.toString(tp.getLevel());
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
		} else if(player2.getName() == tp.getName()) {
			int x = LevelPositionX2;
			int y = LevelPositionY2;
			int z = LevelPositionZ2;
			String s = Integer.toString(tp.getLevel());
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
	}
	
	
	//THIS METHOD IS A ADMIN TEST METHOD
	public static void initialize(Player player, SettingsManager settings) {
			Board.settings = settings;
			Board.player = player;
	}
	

	public void TetrisBoard(int boardX, int boardY, Tetromino piece, TetrominoType type, Player p) {
		if(p.getName() == player1.getName()) {
			int x = settings.getData().getInt("BoardPosition1.X");
			int y = settings.getData().getInt("BoardPosition1.Y");
			int z = settings.getData().getInt("BoardPosition1.Z") + 1;
			setPieceBlocks(x - boardX, y + boardY, z, piece, type);	
		} else if(p.getName() == player2.getName()) {
			int x = settings.getData().getInt("BoardPosition2.X");
			int y = settings.getData().getInt("BoardPosition2.Y");
			int z = settings.getData().getInt("BoardPosition2.Z") + 1;
			setPieceBlocks(x - boardX, y + boardY, z, piece, type);	
		}
	
	}
	

	public void SaveTetris(int boardX, int boardY, Player p) {
    	if(p.getName() == player1.getName()) {
    		for (int i = 0; i < 4; i++) {
        		int CoordX = boardX - currentpiece1.coords[i][0];
        		int CoordY = boardY + currentpiece1.coords[i][1];
        		board1[CoordX][CoordY] = currentpiece1.type;
            }	
    	} else if(p.getName() == player2.getName()) {
    		for (int i = 0; i < 4; i++) {
        		int CoordX = boardX - currentpiece2.coords[i][0];
        		int CoordY = boardY + currentpiece2.coords[i][1];
        		board2[CoordX][CoordY] = currentpiece2.type;
            } 
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
	

	public TetrominoType get(int x, int y, Player p) {
		if(p.getName() == player1.getName()) {
			return board1[x][y];
		} else if (p.getName() == player2.getName()) {
			return board2[x][y];
		} else {
			return null;
		}
		
	}


	public void Boardsetup() {
		settings.reloadConfig();
		HoldPositionX1 = settings.getData().getInt("HoldPosition1.X") - 1;
		HoldPositionY1 = settings.getData().getInt("HoldPosition1.Y");
		HoldPositionZ1 = settings.getData().getInt("HoldPosition1.Z") + 1;
		HoldPositionX2 = settings.getData().getInt("HoldPosition2.X") - 1;
		HoldPositionY2 = settings.getData().getInt("HoldPosition2.Y");
		HoldPositionZ2 = settings.getData().getInt("HoldPosition2.Z") + 1;
		BoardPositionX1 = settings.getData().getInt("BoardPosition1.X");
		BoardPositionY1 = settings.getData().getInt("BoardPosition1.Y");
		BoardPositionZ1 = settings.getData().getInt("BoardPosition1.Z") + 1;
		BoardPositionX2 = settings.getData().getInt("BoardPosition2.X");
		BoardPositionY2 = settings.getData().getInt("BoardPosition2.Y");
		BoardPositionZ2 = settings.getData().getInt("BoardPosition2.Z") + 1;		
		ScorePositionX1 = settings.getData().getInt("ScorePosition1.X") - 7;
		ScorePositionY1 = settings.getData().getInt("ScorePosition1.Y");
		ScorePositionZ1 = settings.getData().getInt("ScorePosition1.Z");
		ScorePositionX2 = settings.getData().getInt("ScorePosition2.X") - 7;
		ScorePositionY2 = settings.getData().getInt("ScorePosition2.Y");
		ScorePositionZ2 = settings.getData().getInt("ScorePosition2.Z");		
		LinePositionX1 = settings.getData().getInt("LinePosition1.X") - 7;
		LinePositionY1 = settings.getData().getInt("LinePosition1.Y");
		LinePositionZ1 = settings.getData().getInt("LinePosition1.Z");
		LinePositionX2 = settings.getData().getInt("LinePosition2.X") - 7;
		LinePositionY2 = settings.getData().getInt("LinePosition2.Y");
		LinePositionZ2 = settings.getData().getInt("LinePosition2.Z");
		LevelPositionX1 = settings.getData().getInt("LevelPosition1.X") - 1;
		LevelPositionY1 = settings.getData().getInt("LevelPosition1.Y");
		LevelPositionZ1 = settings.getData().getInt("LevelPosition1.Z");	
		LevelPositionX2 = settings.getData().getInt("LevelPosition2.X") - 1;
		LevelPositionY2 = settings.getData().getInt("LevelPosition2.Y");
		LevelPositionZ2 = settings.getData().getInt("LevelPosition2.Z"); 
		ScoreUpdate(player1);
		ScoreUpdate(player2);
		LevelUpdate(player1);
		LevelUpdate(player2);
		LineUpdate(player1);
		LineUpdate(player2);
		player1loss = false;
		player2loss = false;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 21; j++) {
				board1[i][j] = TetrominoType.Empty;
				board2[i][j] = TetrominoType.Empty;
				setBlock(BoardPositionX1 - i, BoardPositionY1 + j, BoardPositionZ1, board1[i][j]);
				setBlock(BoardPositionX2 - i, BoardPositionY2 + j, BoardPositionZ2, board2[i][j]);
			}
		}
		ClearPieceinBox(HoldPositionX1, HoldPositionY1, HoldPositionZ1);
		ClearPieceinBox(HoldPositionX1 + 1, HoldPositionY1, HoldPositionZ1); //due to rotation, the position may be larger
		ClearPieceinBox(HoldPositionX2, HoldPositionY2, HoldPositionZ2);
		ClearPieceinBox(HoldPositionX2 + 1, HoldPositionY2, HoldPositionZ2); //due to rotation, the position may be larger
	}


	public static void LineCheck(Player p) {
		if(p.getName() == player1.getName()) {
			int linecheck = 0;
			for (int i = 19; i >= 0; i--) {
				for (int j = 0; j < 10; j++) {
					if (board1[j][i] == TetrominoType.Empty) {
						break;
					} else if (j == 9){
						ClearLine(i, p);
						linecheck++;
					}
				}
			}
			ScoreCalculation(linecheck, player1);
		} else if (p.getName() == player2.getName()) {
			int linecheck = 0;
			for (int i = 19; i >= 0; i--) {
				for (int j = 0; j < 10; j++) {
					if (board2[j][i] == TetrominoType.Empty) {
						break;
					} else if (j == 9){
						ClearLine(i, p);
						linecheck++;
					}
				}
			}
			ScoreCalculation(linecheck, player2);
		}

	}
	

	private static void ScoreCalculation(int line, TetrisPlayer tp) {
		if (line == 0) {
			return;
		} else if (line == 1) {
			tp.setScore(tp.getScore() + 40 * (tp.getLevel() + 1));
			tp.setLine(tp.getLine() + 1);
		} else if (line == 2) {
			tp.setScore(tp.getScore() + 100 * (tp.getLevel() + 1));
			tp.setLine(tp.getLine() + 2);
		} else if (line == 3) {
			tp.setScore(tp.getScore() + 300 * (tp.getLevel() + 1));
			tp.setLine(tp.getLine() + 3);
		} else if (line == 4) {
			tp.setScore(tp.getScore() + 1200 * (tp.getLevel() + 1));
			tp.setLine(tp.getLine() + 4);
		} 
		ScoreUpdate(tp);
		LineUpdate(tp);
		if(isLevelNeedUpdate(tp.getLevel(), tp) == true) {
			tp.setLevel(tp.getLevel() + 1);
			LevelUpdate(tp);
		}
	}
	
	
	private static boolean isLevelNeedUpdate(int level, TetrisPlayer tp) {
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
		if(tp.getLine() >= updatelevel[level] ) {
			return true;
		} else {
			return false;
		}
	}


	//the following method is used to clear the whole line of the board
	public static void ClearLine(int line, Player p) {
		if(p.getName() == player1.getName()) {
			for (int i = 0; i < 10; i++) {
				for (int j = line; j < 20; j++) {
					board1[i][j] = board1[i][j + 1];
					setBlock(BoardPositionX1 - i, BoardPositionY1 + j, BoardPositionZ1, board1[i][j + 1]);
				}
			}
		} else if(p.getName() == player2.getName()) {
			for (int i = 0; i < 10; i++) {
				for (int j = line; j < 20; j++) {
					board2[i][j] = board2[i][j + 1];
					setBlock(BoardPositionX2 - i, BoardPositionY2 + j, BoardPositionZ2, board2[i][j + 1]);
				}
			}
		}

	}



	public static boolean CheckLoss(Player p) {
		if(p.getName() == player1.getName()) {
			for(int i = 0; i < 10; i++) {
				if(board1[i][20] != TetrominoType.Empty) {
					return true;
				} 
			}
			for(int i = 0; i < 4; i++) {
				int coordX = 3 - new Tetromino(bag1[0]).coords[i][0];
				int coordY = 17 + new Tetromino(bag1[0]).coords[i][1];
				if(board1[coordX][coordY] != TetrominoType.Empty) {
					return true;
				}
			}
		} else if(p.getName() == player2.getName()) {
			for(int i = 0; i < 10; i++) {
				if(board2[i][20] != TetrominoType.Empty) {
					return true;
				} 
			}
			for(int i = 0; i < 4; i++) {
				int coordX = 3 - new Tetromino(bag2[0]).coords[i][0];
				int coordY = 17 + new Tetromino(bag2[0]).coords[i][1];
				if(board2[coordX][coordY] != TetrominoType.Empty) {
					return true;
				}
			}
		}

		
		return false;

	}
	
}
