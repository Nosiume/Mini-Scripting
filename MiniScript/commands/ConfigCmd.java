package MiniScript.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfigCmd implements CommandExecutor {

	public static ItemStack scriptsItem;
	public static ItemStack runItem;
	
	public ConfigCmd()
	{
		scriptsItem = new ItemStack(Material.WRITABLE_BOOK, 1);
		ItemMeta sMeta = scriptsItem.getItemMeta();
		sMeta.setDisplayName("§cScripts");
		scriptsItem.setItemMeta(sMeta);
		
		runItem = new ItemStack(Material.REDSTONE, 1);
		ItemMeta rMeta = runItem.getItemMeta();
		rMeta.setDisplayName("§aRun Script");
		runItem.setItemMeta(rMeta);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg,
			String[] args) {
		
		if(sender instanceof Player)
		{
			Player p = (Player)sender;
			
			Inventory inv = Bukkit.createInventory(null, 9 * 1, "§bScripts Parameters");
			
			ItemStack empty = new ItemStack(Material.GLASS, 1);
			ItemMeta emptyM = empty.getItemMeta();
			emptyM.setDisplayName("§0Coming Soon...");
			empty.setItemMeta(emptyM);
			
			inv.addItem(scriptsItem);
			inv.addItem(runItem);
			
			p.openInventory(inv);
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
		}
		
		return true;
	}

}
