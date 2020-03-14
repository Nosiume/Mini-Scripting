package MiniScript.loader.scripting.keywords;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import MiniScript.debug.ScriptException;
import MiniScript.loader.scripting.Keyword;
import MiniScript.utils.Utils;

public class Send extends Keyword {

	public Send() {
		super("send");
	}

	@Override
	public void use(String[] parameters, String script, int line) {
		//Keep out the last 2 messages
		String[] messagePart = Utils.getSliceOf(parameters, 0, parameters.length-2);
		
		StringBuilder sb = new StringBuilder();
		for(String p : messagePart) sb.append(p + " ");
		String msg = sb.toString().trim().replace("&", "§");
		
		//We verify user formulation (for clarity purpose)
		if(!parameters[parameters.length-2].equalsIgnoreCase("to"))
		{
			try {
				throw new ScriptException(
						"Invalid parameter \"" + parameters[parameters.length-2] + "\" you must specify \"to\" and a target to which you want to send a message",
						script,
						line);
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		
		//Then iterates through all possible targets
		switch(parameters[parameters.length-1])
		{
		case "server":
			//Sends to everyone in the server
			Bukkit.broadcastMessage(msg);
			break;
		default:
			//We will only use this "default" call if the last parameter points to a specific Player
			try  {
				Player target = Bukkit.getPlayer(parameters[parameters.length-1]);
				target.sendMessage(msg);
			} catch (Exception e)
			{
				//If player is not found then
				try {
					throw new ScriptException("Player " + parameters[parameters.length-1] + " not found.", script, line);
				} catch (ScriptException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
