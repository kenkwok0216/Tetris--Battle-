package me.kenandwicky.tetris.GameLoop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.kenandwicky.tetris.Tetris;
import me.kenandwicky.tetris.Board.Board;

public class Execute implements CommandExecutor {

	public static int gameLoopID = -1; //gameLoopId return -1 when failed
	public static Loop loop;
	public static Speed speed;
	public static boolean isGameGoing = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command commnd, String label, String[] args) {
				
		//if sender do not have the permission of builder, it cannot run this command
		if(!sender.hasPermission("SingleTetris.Builder")) {
			sender.sendMessage(ChatColor.RED + "You don't have the permission");
			return true;
		}
		
		
		
		if (gameLoopID != -1) {
			Bukkit.getScheduler().cancelTask(gameLoopID);
		}
		
		loop = new Loop();
		speed = new Speed();
		Tetris.isStart = true;
		Tetris.game = new Game(Tetris.boardclass);
		gameLoopID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Tetris.plugin,
							loop,
							20, speed.ReturnSpeed(Board.getPlayerlevel()));	//Measure in takes (20 takes in second)
							// first number is delay 
							//e.g. 60 means "wait for 60 takes before it start"
							//the second number is interval
							//e.g. 20 means for every 20 takes. it will run "loop"
		return true;
	}	
}
