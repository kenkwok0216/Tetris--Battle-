package me.kenandwicky.tetris;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import me.kenandwicky.tetris.Tetromino.TetrominoType;

public class Utils {
    private static Material[] blockTable = new Material[]{
    		/* I    */     Material.LIGHT_BLUE_CONCRETE,
    		/* O    */     Material.YELLOW_CONCRETE,
    		/* T    */     Material.PURPLE_CONCRETE,
    		/* J    */     Material.BLUE_CONCRETE,
    		/* L    */     Material.ORANGE_CONCRETE,   		
    		/* Z    */     Material.RED_CONCRETE,
            /* S    */     Material.LIME_CONCRETE,                                               
            /* Grey */     Material.GRAY_CONCRETE,
            /* None */     Material.AIR
    };
	
    static public Material tetrominoTypeToBlock(TetrominoType t) {
        return blockTable[t.ordinal()];
    }
	
	
    public static void placeTetromino(String worldName, int x, int y, int z, TetrominoType t) {
        Bukkit.getWorld(worldName).getBlockAt(x, y, z).setType(tetrominoTypeToBlock(t));
    }
}
