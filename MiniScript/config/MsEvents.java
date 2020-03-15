package MiniScript.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import MiniScript.commands.MsCmd;
import MiniScript.loader.scripting.Script;
import MiniScript.utils.Utils;

@SuppressWarnings("deprecation")
public class MsEvents implements Listener {

	private List<Player> typingWorkspace = new ArrayList<Player>();
	
	@EventHandler
	public void onSetNewWorkspace(PlayerChatEvent event)
	{
		Player p = event.getPlayer();
		if(typingWorkspace.contains(p))
		{
			typingWorkspace.remove(p);
			String message = event.getMessage();
			
			if(message.contains("."))
			{
				p.sendMessage("§cWorkspace must be a directory");
				return;
			}
			
			Utils.setWorkspaceDir(message);
			MiniScript.MiniScript.recreateFileLoader();
			
			p.sendMessage("§aWorkspace has been created successfully in server files.");
			event.setCancelled(true);
		}
	}
	
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
			MS.giveScriptsInventory(p);
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
			if(it.isSimilar(MsCmd.scriptsItem))
			{
				p.closeInventory();
				MS.giveScriptsInventory(p);
			} else if (it.isSimilar(MsCmd.runItem))
			{
				p.closeInventory();
				MS.giveRunInventory(p);
			} else if (it.isSimilar(MsCmd.editWorkspace))
			{
				p.closeInventory();
				typingWorkspace.add(p);
				p.sendMessage("§7Veuillez envoyer le nom du nouveau workspace dans le chat...");
			}
			event.setCancelled(true);
		}
	}
	
}
