package com.ben.thepilot;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightclickListener implements Listener
{
	private Main main;
	
	public RightclickListener(Main main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onItemRightClick(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();
		
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if (player.getInventory().getItemInMainHand().getType() != null 
					&& player.getInventory().getItemInMainHand().getType() == Material.COMPASS)
			{
				main.applyPilotGUI(player);
				/*
				if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "The Whee Menu")
				{
					main.applyPilotGUI(player);
				}
				*/
			}
		}
		return;
	}
}
