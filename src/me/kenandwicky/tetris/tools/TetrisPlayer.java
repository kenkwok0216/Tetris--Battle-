package me.kenandwicky.tetris.tools;

import org.bukkit.entity.Player;

public class TetrisPlayer {
	
	Player player;
	private int score, level, line, attackline;
	private TetrisPlayer opponent;
	
	public TetrisPlayer(Player player) {
		this.player = player;
		this.score = 0;
		this.level = 0;
		this.line = 0;
		this.attackline = 0;
	}
	
	public String getName() {
		return player.getName();
	}
	
	public Player getPlayer() {
		return player.getPlayer();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
	public int getattackline() {
		return attackline;
	}
	
	public void setattackline(int attackline) {
		this.attackline = attackline;
	}
	
	public void setopponent(TetrisPlayer opponent) {
		this.opponent = opponent;
	}
	
	public TetrisPlayer getopponent() {
		return opponent;
	}
	
	
}
