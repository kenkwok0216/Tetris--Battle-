package me.kenandwicky.tetris.GameLoop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.kenandwicky.tetris.Tetris;
import me.kenandwicky.tetris.Board.Board;
import me.kenandwicky.tetris.Board.SettingsManager;

public class Execute implements CommandExecutor {

	public static int gameLoopID1 = -1, gameLoopID2 = -1; //gameLoopId return -1 when failed
	public static Loop1 loop1;
	public static Loop2 loop2;
	public static Speed speed;
	public static boolean isGameGoing = false;
	SettingsManager settings = SettingsManager.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command commnd, String label, String[] args) {
				
		//if sender do not have the permission of builder, it cannot run this command
		if(!sender.hasPermission("SingleTetris.Builder")) {
			sender.sendMessage(ChatColor.RED + "You don't have the permission");
			return true;
		}
		
		int hashcode = 0;

		hashcode += settings.getData().getInt("HoldPosition1.X");  
		hashcode += settings.getData().getInt("HoldPosition1.Y");	
		hashcode += settings.getData().getInt("HoldPosition1.Z");
		hashcode += settings.getData().getInt("HoldPosition2.X");  
		hashcode += settings.getData().getInt("HoldPosition2.Y");	
		hashcode += settings.getData().getInt("HoldPosition2.Z");

		hashcode += settings.getData().getInt("BoardPosition1.X");
		hashcode += settings.getData().getInt("BoardPosition1.Y");
		hashcode += settings.getData().getInt("BoardPosition1.Z");
		hashcode += settings.getData().getInt("BoardPosition2.X");
		hashcode += settings.getData().getInt("BoardPosition2.Y");
		hashcode += settings.getData().getInt("BoardPosition2.Z");

		hashcode += settings.getData().getInt("ScorePosition1.X");
		hashcode += settings.getData().getInt("ScorePosition1.Y");
		hashcode += settings.getData().getInt("ScorePosition1.Z");
		hashcode += settings.getData().getInt("ScorePosition2.X");
		hashcode += settings.getData().getInt("ScorePosition2.Y");
		hashcode += settings.getData().getInt("ScorePosition2.Z");

		hashcode += settings.getData().getInt("LinePosition1.X");
		hashcode += settings.getData().getInt("LinePosition1.Y");
		hashcode += settings.getData().getInt("LinePosition1.Z");
		hashcode += settings.getData().getInt("LinePosition2.X");
		hashcode += settings.getData().getInt("LinePosition2.Y");
		hashcode += settings.getData().getInt("LinePosition2.Z"); 

		hashcode += settings.getData().getInt("LevelPosition1.X");
		hashcode += settings.getData().getInt("LevelPosition1.Y");
		hashcode += settings.getData().getInt("LevelPosition1.Z");
		hashcode += settings.getData().getInt("LevelPosition2.X");
		hashcode += settings.getData().getInt("LevelPosition2.Y");
		hashcode += settings.getData().getInt("LevelPosition2.Z");

		hashcode += settings.getData().getInt("NamePosition1.X");
		hashcode += settings.getData().getInt("NamePosition1.Y");
		hashcode += settings.getData().getInt("NamePosition1.Z");
		hashcode += settings.getData().getInt("NamePosition2.X");
		hashcode += settings.getData().getInt("NamePosition2.Y");
		hashcode += settings.getData().getInt("NamePosition2.Z"); 

		hashcode += settings.getData().getInt("Player1Position.X");
		hashcode += settings.getData().getInt("Player1Position.Y");
		hashcode += settings.getData().getInt("Player1Position.Z"); 
		hashcode += settings.getData().getInt("Player2Position.X");
		hashcode += settings.getData().getInt("Player2Position.Y");
		hashcode += settings.getData().getInt("Player2Position.Z"); 

		hashcode += settings.getData().getInt("NextPosition1.X");
		hashcode += settings.getData().getInt("NextPosition1.Y");
		hashcode += settings.getData().getInt("NextPosition1.Z");
		hashcode += settings.getData().getInt("NextPosition2.X");
		hashcode += settings.getData().getInt("NextPosition2.Y");
		hashcode += settings.getData().getInt("NextPosition2.Z");
		
		Bukkit.getServer().getConsoleSender().sendMessage((Integer.toString(hashcode)));
		
		hashcode = (Integer.toString(hashcode)).hashCode();		
		
		if(hashcode ==  settings.getData().getInt("hashcode")) {
			//nothing happen
		} else {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Tetris have Error");
			return true;
		}
		
		if (gameLoopID1 != -1) {
			Bukkit.getScheduler().cancelTask(gameLoopID1);
		}
		
		if (gameLoopID2 != -1) {
			Bukkit.getScheduler().cancelTask(gameLoopID2);
		}
		
		loop1 = new Loop1();
		loop2 = new Loop2();
		
		speed = new Speed();
		Tetris.isStart = true;
		Tetris.game = new Game(Tetris.boardclass);
		gameLoopID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Tetris.plugin,
							loop1,
							20, speed.ReturnSpeed(Board.player1.getLevel()));	//Measure in takes (20 takes in second)
							// first number is delay 
							//e.g. 60 means "wait for 60 takes before it start"
							//the second number is interval
							//e.g. 20 means for every 20 takes. it will run "loop"
		gameLoopID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Tetris.plugin,
						   loop2,
				           20, speed.ReturnSpeed(Board.player2.getLevel()));
		return true;
	}	
}
