package me.kenandwicky.tetris.tools;

public class DataEncryption {
	private static int p = 919;
	private static int q = 233;

	public static int Encryption(int x) {
		int test = (x + p) ^ q;
		return test;
	}
	
	public static int Decryption(int x) {
		int test = (x ^ q) - p;
		return test;
	}
}
