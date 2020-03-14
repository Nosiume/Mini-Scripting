package MiniScript.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg,
			String[] args) {
		
		/*
		 * For now this process is really fast but later on
		 * it could be taking some more time to load everything
		 */
		
		sender.sendMessage("§eReloading scripts...");
		MiniScript.MiniScript.getFileLoader().reload();
		sender.sendMessage("§aAll scripts reloaded successfully !");
		
		return true;
	}

}
