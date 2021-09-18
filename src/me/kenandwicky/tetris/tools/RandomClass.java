package me.kenandwicky.tetris.tools;

import java.util.Random;

public class RandomClass {
	
	private int last_num;
	private Random rnd = new Random(System.currentTimeMillis());
		
	public int TetrisRandom() {
		rnd.setSeed(System.currentTimeMillis());
		int num = rnd.nextInt(7);
		if(last_num == num) {   //to lower the chance continuous 2 block is the same
			num = rnd.nextInt(7);
		}
		last_num = num;
		return num;
		
	}
	
}
