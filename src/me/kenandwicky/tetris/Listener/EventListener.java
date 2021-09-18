package me.kenandwicky.tetris.Listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.kenandwicky.tetris.GameLoop.Game;
import me.kenandwicky.tetris.Tetris;
import me.kenandwicky.tetris.Board.Board;
import me.kenandwicky.tetris.Board.SettingsManager;

public class EventListener implements Listener {
	SettingsManager settings = SettingsManager.getInstance();
	
	//For the builder to set up the block
    @EventHandler
    public void onBuilderBreak(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
        	Board.setup();
        }
    }
    
    //When Player "join" then update the banner
    @EventHandler
    public void onPlayerJoing(PlayerJoinEvent e) {
    	Board.initialize(e.getPlayer(), settings);
    	if (e.getPlayer().getGameMode() == GameMode.ADVENTURE) {
    		Board.NameUpdate(e.getPlayer().getName());
    		ItemStack item = new ItemStack(Material.STICK, 1);
    		ItemMeta meta = item.getItemMeta();
    		meta.setDisplayName("rotation rods");
    		meta.addEnchant(Enchantment.DURABILITY, 5, true);
    		item.setItemMeta(meta);
    		e.getPlayer().getInventory().addItem(item);
    	}
    	
    	
    }
    
    @EventHandler
    public void onItemDrop (PlayerDropItemEvent e) {
            e.setCancelled(true);
    }
    
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) {
    	if (e.getWhoClicked().getGameMode() == GameMode.ADVENTURE) {
    		e.setCancelled(true);
    	}
    }
    
    //Checking the user Clicking
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {
    	if(Tetris.isStart == false) { //Tetris.isStart is use to Check whether the game start
    		return;
    	}
    	
    	if(e.getAction().equals(Action.LEFT_CLICK_AIR)) {
    		Game.rotatedLeft();
    		//e.getPlayer().sendMessage("Rotate left");
    		return;
    	} else if(e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
    		Game.rotatedRight();
    		//e.getPlayer().sendMessage("Rotate Right");
    		return;
    	}
    	
    }   
    
    
    @EventHandler
    public static void onPlayerMovement(PlayerMoveEvent e) { //Checking the movement of user to move the block
    	if(Tetris.isStart == false) { //Tetris.isStart is use to Check whether the game start
    		return;
    	}
    	
    	Location fromLocation = e.getFrom();
    	Location toLocation = e.getTo();
    	
    	double xDiff = Math.abs(toLocation.getX() - fromLocation.getX());
    	double yDiff = Math.abs(toLocation.getY() - fromLocation.getY());
    	double zDiff = Math.abs(toLocation.getZ() - fromLocation.getZ());
    	
    	if (xDiff > 0 || yDiff > 0 || zDiff > 0) {  //the user have movement
    		//e.getPlayer().teleport(fromLocation.setDirection(toLocation.getDirection()));
    		fromLocation.setYaw((float) 0);        //set the yaw and pitch to 0
    		fromLocation.setPitch((float) 5);
    		e.getPlayer().teleport(fromLocation);   //teleport back to original position
    		
    	}
    	
    	if (yDiff > 0) {
    		Game.HardDrop();
    	}
    	
    	if (zDiff > xDiff) {
    		if (toLocation.getZ() - fromLocation.getZ() < 0) {
    			Game.moveDown();
    		} else {
    			return;
    		}
    		return;
    	}
    	
    	if (xDiff > zDiff) {
    		if (toLocation.getX() - fromLocation.getX() > 0) {
    			Game.moveLeft();  			
    		} else {
    			Game.moveRight();
    		}
    		
    		return;	
    	}   	    	
    }
    
    //now allow user to use left hand
    @EventHandler
    public static void OnPlayerSwapHandEvent(PlayerSwapHandItemsEvent e) {
    	e.setCancelled(true);
    	Game.holdPiece();
    }
    
    
	
}
