package MiniScript.commands;

import java.util.Collection;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import MiniScript.loader.scripting.Script;

public class TreeCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg,
			String[] args) {
		
		Collection<Script> scripts = MiniScript.MiniScript.getFileLoader().getScripts();
		
		sender.sendMessage("§l——————————————————————————");
		sender.sendMessage("");
		sender.sendMessage("§eAll loaded scripts : ");
		
		if(!scripts.isEmpty())
		{
			for(Script script : scripts)
				sender.sendMessage("§7 - §c" + script.getName() + " §7: "+ (script.isEnabled() ? "§aOn" : "§cOff"));
		} else
		{
			sender.sendMessage("   §cCan't find any scripts.");
		}
		
		sender.sendMessage("");
		sender.sendMessage("§l——————————————————————————");
		
		return true;
	}

}
