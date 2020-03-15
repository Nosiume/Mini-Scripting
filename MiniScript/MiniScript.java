package MiniScript;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import MiniScript.commands.MsCmd;
import MiniScript.commands.ReloadCmd;
import MiniScript.commands.TreeCmd;
import MiniScript.config.MsEvents;
import MiniScript.debug.WorkspaceException;
import MiniScript.loader.FileLoader;
import MiniScript.loader.blocks.SBlockType;
import MiniScript.loader.scripting.KeywordManager;
import MiniScript.loader.scripting.events.ScriptEvent;
import MiniScript.utils.Utils;

public class MiniScript extends JavaPlugin {

	private static FileLoader fileLoader;
	public static MiniScript instance;
	
	@Override
	public void onEnable()
	{
		//Creates an instance
		instance = this;
		
		initialize();
		
		//Triggers on enable event
		fileLoader.triggerEvent(SBlockType.ENABLE);
		
		getCommand("ms-tree").setExecutor(new TreeCmd());
		getCommand("ms-reload").setExecutor(new ReloadCmd());
		getCommand("ms").setExecutor(new MsCmd());
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new MsEvents(), this);
		pm.registerEvents(new ScriptEvent(), this);
	}
	
	@Override
	public void onDisable()
	{
		fileLoader.triggerEvent(SBlockType.DISABLE);
	}
	
	private void initialize()
	{	
		//Initialize keywords
		KeywordManager.init();
		
		try {
			String workspaceDir = Utils.getWorkspaceDir();
			fileLoader = new FileLoader(workspaceDir);
			fileLoader.reload();
		} catch (WorkspaceException e) {
			e.printStackTrace();
		}
	}
	
	public static void recreateFileLoader()
	{
		try {
			String workspaceDir = Utils.getWorkspaceDir();
			fileLoader = new FileLoader(workspaceDir);
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
