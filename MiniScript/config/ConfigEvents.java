package MiniScript.config;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import MiniScript.commands.ConfigCmd;
import MiniScript.loader.scripting.Script;

public class ConfigEvents implements Listener {

	@EventHandler
	public void onRunScript(InventoryClickEvent event)
	{
		Player p = (Player) event.getWhoClicked();
		ItemStack it = event.getCurrentItem();
		InventoryView view = event.getView();
		
		if(it != null && view.getTitle().equalsIgnoreCase("§bRun a Script"))
		{
			String name = it.getItemMeta().getDisplayName();
			name = name.substring(2, name.length());
			Script s = MiniScript.MiniScript.getFileLoader().getScriptMap().get(name);
			s.run();
			p.closeInventory();
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEnableScript(InventoryClickEvent event)
	{
		Player p = (Player) event.getWhoClicked();
		ItemStack it = event.getCurrentItem();
		InventoryView view = event.getView();
		
		if(it != null && view.getTitle().equalsIgnoreCase("§cScript Config"))
		{
			String name = it.getItemMeta().getDisplayName();
			name = name.substring(2, name.length());
			Script s = MiniScript.MiniScript.getFileLoader().getScriptMap().get(name);
			s.toggle();
			Config.giveScriptsInventory(p);
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSelectParameter(InventoryClickEvent event)
	{
		Player p = (Player) event.getWhoClicked();
		ItemStack it = event.getCurrentItem();
		InventoryView view = event.getView();
		
		if(it != null && view.getTitle().equalsIgnoreCase("§bScripts Parameters"))
		{
			if(it.isSimilar(ConfigCmd.scriptsItem))
			{
				p.closeInventory();
				Config.giveScriptsInventory(p);
			} else if (it.isSimilar(ConfigCmd.runItem))
			{
				p.closeInventory();
				Config.giveRunInventory(p);
			}
			event.setCancelled(true);
		}
	}
	
}
