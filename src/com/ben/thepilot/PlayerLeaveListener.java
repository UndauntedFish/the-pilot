package com.ben.thepilot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener
{
	private Main main;
	
	public PlayerLeaveListener(Main main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e)
	{
		Player player = e.getPlayer();
		
		if (main.chestSlot.containsKey(player))
		{
			player.getInventory().setChestplate(main.chestSlot.get(player));
		}
		else
		{
			return;
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e)
	{
		Player player = e.getPlayer();
		
		if (main.chestSlot.containsKey(player))
		{
			player.getInventory().setChestplate(main.chestSlot.get(player));
		}
		else
		{
			return;
		}
	}
}
