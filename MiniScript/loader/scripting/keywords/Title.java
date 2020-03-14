package MiniScript.loader.scripting.keywords;

import net.minecraft.server.v1_14_R1.PacketPlayOutTitle.EnumTitleAction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import MiniScript.debug.ScriptException;
import MiniScript.loader.scripting.Keyword;
import MiniScript.utils.Utils;

public class Title extends Keyword {

	public Title() {
		super("title");
	}

	@Override
	public void use(String[] parameters, String script, int line) {
		// Keep out the last 2 parameters
		String[] messagePart = Utils.getSliceOf(parameters, 1,
				parameters.length - 2);

		StringBuilder sb = new StringBuilder();
		for (String p : messagePart)
			sb.append(p + " ");
		String msg = sb.toString().trim().replace("&", "§");

		// We verify user formulation (for clarity purpose)
		if (!parameters[parameters.length - 2].equalsIgnoreCase("to")) {
			try {
				throw new ScriptException(
						"Invalid parameter \""
								+ parameters[parameters.length - 2]
								+ "\" you must specify \"to\" and a target to which you want to send a title",
						script, line);
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}

		EnumTitleAction titleAction = EnumTitleAction.RESET;
		switch(parameters[0])
		{
		case "title":
			titleAction = EnumTitleAction.TITLE;
			break;
		case "subtitle":
			titleAction = EnumTitleAction.SUBTITLE;
			break;
		case "actionbar":
			titleAction = EnumTitleAction.ACTIONBAR;
			break;
		default:
			break;
		}
		
		// Then iterates through all possible targets
		switch (parameters[parameters.length - 1]) {
		case "server":
			// Sends to everyone in the server
			for(Player p : Bukkit.getOnlinePlayers())
				Utils.sendTitle(p, titleAction, msg);
			break;
		default:
			// We will only use this "default" call if the last parameter points
			// to a specific Player
			try {
				Player target = Bukkit.getPlayer(parameters[parameters.length - 1]);
				Utils.sendTitle(target, titleAction, msg);
			} catch (Exception e) {
				// If player is not found then
				try {
					throw new ScriptException(
							"Player " + parameters[parameters.length - 1]
									+ " not found.", script, line);
				} catch (ScriptException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
