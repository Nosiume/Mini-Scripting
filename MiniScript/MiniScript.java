package MiniScript;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import MiniScript.commands.ConfigCmd;
import MiniScript.commands.ReloadCmd;
import MiniScript.commands.TreeCmd;
import MiniScript.config.ConfigEvents;
import MiniScript.debug.WorkspaceException;
import MiniScript.loader.FileLoader;
import MiniScript.loader.scripting.KeywordManager;

public class MiniScript extends JavaPlugin {

	private static FileLoader fileLoader;
	
	@Override
	public void onEnable()
	{
		initialize();
		
		getCommand("ms-tree").setExecutor(new TreeCmd());
		getCommand("ms-reload").setExecutor(new ReloadCmd());
		getCommand("ms-config").setExecutor(new ConfigCmd());
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ConfigEvents(), this);
	}
	
	private void initialize()
	{
		//Initialize keywords
		KeywordManager.init();
		
		try {
			fileLoader = new FileLoader("workspaces");
			fileLoader.reload();
		} catch (WorkspaceException e) {
			e.printStackTrace();
		}
	}
	
	//========= GETTERS =========
	
	public static FileLoader getFileLoader()
	{
		return fileLoader;
	}
	
}
