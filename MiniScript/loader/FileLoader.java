package MiniScript.loader;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import MiniScript.debug.WorkspaceException;
import MiniScript.loader.blocks.SBlock;
import MiniScript.loader.blocks.SBlockType;
import MiniScript.loader.scripting.Script;

public class FileLoader {

	//Our workspace file handle
	private File workspace;
	private String extension = ".ms";
	
	//Scripts
	private HashMap<String, Script> scripts = new HashMap<String, Script>();
	
	public FileLoader(String workspaceDirPath) throws WorkspaceException
	{
		this.workspace = new File(workspaceDirPath);

		//If our workspace is a new workspace (doesn't exist) we will create a new one
		if(!this.workspace.exists()) 
			this.workspace.mkdir(); // make directory
		
		//Can't use a workspace that is not a file directory
		if(!this.workspace.isDirectory())
			throw new WorkspaceException(workspaceDirPath);
	}
	
	//========== LOADING SYSTEM ===========
	
	public void reload()
	{
		scripts.clear(); // Free our obsolete scripts
		
		//Loops all file of workspace
		for(File file : workspace.listFiles())
		{
			//Check if file is a script
			if(file.getName().endsWith(this.extension))
			{
				String fileName = file.getName(); // Get full file name (with extension)
				fileName = fileName.substring(0, fileName.length() - this.extension.length()); // Removes extension
				
				Script s = new Script(file, fileName);
				s.load();
				this.scripts.put(fileName, s);
			}
		}
	}
	
	//========== Scripts ==========
	
	public void triggerEvent(SBlockType type)
	{
		for(Script s : MiniScript.MiniScript.getFileLoader().getScripts())
		{
			if(s.isEnabled())
			{
				for(SBlock bl : s.getBlocks())
				{
					if(bl.getType().equals(type))
					{
						bl.run();
					}
				}
			}
		}
	}
	
	//========== GETTERS ==========
	
	public HashMap<String, Script> getScriptMap()
	{
		return this.scripts;
	}
	
	public Set<String> getScriptsTree()
	{
		return this.scripts.keySet();
	}
	
	public Collection<Script> getScripts()
	{
		return this.scripts.values();
	}
	
	public File getWorkspace()
	{
		return workspace;
	}
	
}
