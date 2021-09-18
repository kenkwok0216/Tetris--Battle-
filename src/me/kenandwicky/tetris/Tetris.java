package me.kenandwicky.tetris;

import me.kenandwicky.tetris.Board.*;
import me.kenandwicky.tetris.GameLoop.*;
import me.kenandwicky.tetris.Listener.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Tetris extends JavaPlugin {
	
	SettingsManager settings = SettingsManager.getInstance();
	public static Board boardclass = new Board();
	public static Plugin plugin;
	public static Game game = null;
	public static boolean isStart = false;
	
	
	
	public void onEnable() {
		Bukkit.getServer().getConsoleSender().sendMessage("Tetris is working");
		Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
		settings.setup(this);
		plugin = this;
		this.getCommand("gamestart").setExecutor(new Execute());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		//if sender is not a Player, sender instanceof Player will be false (i.e. this statement ensure that the sender is a Player)
		if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.AQUA + "Console cannot run this command!");
            return true;
        }		
		
		//Casting by polymorphism
		Player player = (Player) sender;
		//if sender do not have the permission of builder, it cannot run this command
		if(!sender.hasPermission("SingleTetirs.Builder")) {
			sender.sendMessage(ChatColor.RED + "You don't have the permission");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("initializetetris")) {
			Board.initialize(player, settings);	
		}
		
		if(cmd.getName().equalsIgnoreCase("settetris")) {
			boardclass.building(player, settings);	
		}
		
		
		
		return true;
	}
	
	
}
