package MiniScript.loader.blocks;

import java.util.HashMap;
import java.util.Map.Entry;

import MiniScript.loader.scripting.Keyword;

public class SBlock {

	private String script; // Block's script name
	private SBlockType type; // Loads the block's utility
	private int line; // The start line of the block
	
	//Procedures and java executions by keyword system
	private HashMap<Keyword, String[]> toRun = new HashMap<Keyword, String[]>();
	
	//Initialize with basic informations
	public SBlock(String script, int line, SBlockType type)
	{
		this.script = script;
		this.line = line;
		this.type = type;
	}
	
	//Adds a new procedure to the execution list
	public void addAction(Keyword keyword, String[] parameters)
	{
		toRun.put(keyword, parameters);
	}
	
	//Runs the block
	public void run()
	{
		int count = 0;
		for(Entry<Keyword, String[]> line : toRun.entrySet())
		{
			line.getKey().use(line.getValue(), script, this.line + count);
			count++;
		}
	}
	
	//========= GETTERS =========
	
	public SBlockType getType()
	{
		return type;
	}
	
}
