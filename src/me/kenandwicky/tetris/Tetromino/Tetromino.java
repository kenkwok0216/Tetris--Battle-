package me.kenandwicky.tetris.Tetromino;

public class Tetromino {
	//this array is use to store the block of the Tetromino
    private static final int[][][] coordsTable = new int[][][]{
    	/* I */     { {  0, 1 }, {  -1,  1 }, {  -2, 1 }, {  -3, 1 } },    //done 
    	/* O */     { {  -1,  1 }, {  -2,  1 }, {  -1, 2 }, {  -2, 2 } },  //done
    	/* T */     { { -1,  0 }, {  0,  1 }, {  -1, 1 }, {  -2, 1 } },   //done
    	/* J */     { { 0,  0 }, { -1, 0 }, {  -1, 1 }, {  -1, 2 } },     //done
    	/* L */     { { -1, 0 }, {  -2, 0 }, {  -1, 1 }, {  -1, 2 } },    //done
    	/* Z */     { {  -1, 1 }, {  -2,  1 }, { 0, 2 }, { -1, 2 } },    //done
        /* S */     { {  0, 1 }, {  -1,  1 }, {  -1, 2 }, {  -2, 2 } }   //done  
    };

	public int[][] coords;
	public TetrominoType type;

	public void rotateLeft() {
		if (type == TetrominoType.O) return;
		//transformation
		for (int i = 0; i < 4; i++) {
			coords[i][0] += 1;
			coords[i][1] -= 1;
		}
		//rotation
		for (int i = 0; i < 4; i++) {
    		int tmp = coords[i][0];
    		coords[i][0] = -coords[i][1];
    		coords[i][1] = tmp;
    	}
		//transformation	
		for (int i = 0; i < 4; i++) {
			coords[i][0] -= 1;
			coords[i][1] += 1;
		}
	}

	public void rotateRight() {
		if (type == TetrominoType.O) return;
		//transformation
		for (int i = 0; i < 4; i++) {
			coords[i][0] += 1;
			coords[i][1] -= 1;
		}
		//rotation
		for (int i = 0; i < 4; i++) {
			int tmp = coords[i][0];
			coords[i][0] = coords[i][1];
			coords[i][1] = -tmp;
		}
		//transformation	
		for (int i = 0; i < 4; i++) {
			coords[i][0] -= 1;
			coords[i][1] += 1;
		}
	}

	public Tetromino(TetrominoType type) {
		this.coords = new int[4][2];
		for (int i = 0; i < 4; i++) {
			this.coords[i] = coordsTable[type.ordinal()][i].clone();
		}
		this.type = type;
	}
}
