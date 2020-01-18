package com.ben.thepilot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class MenuListener implements Listener
{
	private Main main;
	
	public MenuListener(Main main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onGUIClick(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		
		// If opened GUI's title is equal to 'The Assassin: Main Menu', we've got the right GUI bois
		if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(
				ChatColor.AQUA + "The Whee Menu"))
		{
			if (e.getCurrentItem() != null)
			{
				e.setCancelled(true); //Doesn't allow player to take out toggler items from GUI
				
				switch (e.getCurrentItem().getType())
				{
				case SLIME_BLOCK:
					player.setVelocity(new Vector(0, 300, 0));
					player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Wooooooooosh!");
					
					break;
				case REDSTONE_BLOCK:
					// Removes the elytra in the player's chestplate slot, and gives back prior item if applicable
					if (main.chestSlot.containsKey(player))
					{
						// Player had stuff in their chestplate slot before
						player.getInventory().setChestplate(main.chestSlot.get(player));
					}
					else
					{
						// Player had nothing in their chestplate slot, replace elytra with blank slot
						player.getInventory().setChestplate(null);
					}
					player.sendMessage(ChatColor.GOLD + "Flight receptacle dequipped!");
					
					break;
				case EMERALD_BLOCK:
					// Adds the elytra to the player's chestplate slot, stores existing chestplate slot items to hashmap
					if (player.getInventory().getChestplate() != null)
					{
						// Player had something in the chestplate slot to begin with
						main.chestSlot.put(player, player.getInventory().getChestplate());
						player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
					}
					else
					{
						// Player had nothing in the chestplate slot to begin with
						player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
					}
					player.sendMessage(ChatColor.GOLD + "Flight receptacle equipped!");

					break;
				default:
					return;
				}
			}
			
			player.closeInventory();
		}
	}
}
