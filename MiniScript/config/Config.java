package MiniScript.config;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import MiniScript.loader.scripting.Script;

public class Config {

	public static void giveScriptsInventory(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9*4, "§cScript Config");
		
		for(Script s : MiniScript.MiniScript.getFileLoader().getScripts())
		{
			ItemStack it = new ItemStack(s.isEnabled() ? Material.GREEN_WOOL : Material.RED_WOOL, 1);
			ItemMeta meta = it.getItemMeta();
			meta.setDisplayName("§c" + s.getName());
			meta.setLore(Arrays.asList(new String[] {"§7Script is " + (s.isEnabled() ? "§aOn" : "§cOff")}));
			it.setItemMeta(meta);
			
			inv.addItem(it);
		}
		
		p.openInventory(inv);
	}
	
	public static void giveRunInventory(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9*4, "§bRun a Script");
		
		for(Script s : MiniScript.MiniScript.getFileLoader().getScripts())
		{
			ItemStack it = new ItemStack(Material.PAPER, 1);
			ItemMeta meta = it.getItemMeta();
			meta.setDisplayName("§c" + s.getName());
			it.setItemMeta(meta);
			inv.addItem(it);
		}
		
		p.openInventory(inv);
	}
	
}
