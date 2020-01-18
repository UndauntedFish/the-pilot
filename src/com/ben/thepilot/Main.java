package com.ben.thepilot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		System.out.println("[ThePilot] Successfully enabled!");
		
		Bukkit.getPluginManager().registerEvents(new RightclickListener(this), this);
		Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(this), this);

	}
	
	@Override
	public void onDisable()
	{
		// Restore all player's elytra slots
	}
	
	/* ELYTRA STORAGE */
	public HashMap<Player, ItemStack> chestSlot = new HashMap<>();
	/*
	 * Stores items that were previously in the player's armor slot prior to enabling elytra.
	 */
	
	/* PILOT UI */
	public void applyPilotGUI(Player player)
	{
		// BEGINNING
		Inventory gui = Bukkit.createInventory(player, 27, ChatColor.AQUA + "The Whee Menu");
		
		
		// LORES
		List<String> disableLore = new ArrayList<>();
		disableLore.add(ChatColor.GRAY + "Wanna stop flying?");
		disableLore.add(ChatColor.GRAY + "Click me, land-bound simpleton!");
		
		List<String> enableLore = new ArrayList<>();
		enableLore.add(ChatColor.GOLD + "Have you ever dreamt of literally flying?");
		enableLore.add(ChatColor.GOLD + "If so, click me for a fun time >:D muahehhae");
		
		List<String> launchLore = new ArrayList<>();
		launchLore.add(ChatColor.GOLD + "Wanna go whee? Click mee!");
		
		
		// ITEMSTACKS
		ItemStack toggle;
		ItemMeta toggleMeta;
		
		//If player's chestplate armor slot is not empty, and has an elytra in it, toggle elytra off.
		if (player.getInventory().getChestplate() != null 
				&& player.getInventory().getChestplate().getType() == Material.ELYTRA)
		{
			toggle = new ItemStack(Material.REDSTONE_BLOCK);
			toggleMeta = toggle.getItemMeta();
			
			toggleMeta.setDisplayName(ChatColor.RED + "Disable Flight Receptacle");
			toggleMeta.setLore(disableLore);
		}
		else
		{
			toggle = new ItemStack(Material.EMERALD_BLOCK);
			toggleMeta = toggle.getItemMeta();
			
			toggleMeta.setDisplayName(ChatColor.GREEN + "Enable Flight Receptacle");
			toggleMeta.setLore(enableLore);
		}
		toggle.setItemMeta(toggleMeta);
		
		// Launch item
		ItemStack launch = new ItemStack(Material.SLIME_BLOCK);
		ItemMeta launchMeta = launch.getItemMeta();
		
		launchMeta.setDisplayName(ChatColor.GREEN + "VTOL Boost");
		launchMeta.setLore(launchLore);
		launch.setItemMeta(launchMeta);
		
		
		// ITEM SETTING
		gui.setItem(11, toggle);
		gui.setItem(15, launch);
		
		
		// FINAL
		player.openInventory(gui);
	}
}
