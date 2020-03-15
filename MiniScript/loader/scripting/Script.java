package MiniScript.loader.scripting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import MiniScript.debug.ScriptException;
import MiniScript.loader.blocks.SBlock;
import MiniScript.loader.blocks.SBlockType;
import MiniScript.utils.Utils;

public class Script {

	private File script;
	private String name;
	private boolean enabled = true;

	private List<SBlock> toRun = new ArrayList<SBlock>();
	
	public Script(File file, String name)
	{
		this.script = file;
		this.name = name;
	}
	
	public void run()
	{
		//Trigger default events
		MiniScript.MiniScript.getFileLoader().triggerEvent(SBlockType.DEFAULT);
	}
	
	public void load()
	{
		List<String> lines = new ArrayList<String>();
		toRun.clear();
		
		try {
			//Load every lines
			BufferedReader br = new BufferedReader(new FileReader(this.script));
			String line; // Loads our lines
			while((line = br.readLine()) != null)
			{
				//If it's a commentary then do not load it
				if(line.startsWith("#"))
					continue;
				
				//Add the line to the line list
				lines.add(line.trim());
			}
			br.close(); // close the reader
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Counts which line we are on
		int lineCount = 0;
		
		//Creates our default block
		SBlock currBlock = new SBlock(this.name, 1, SBlockType.DEFAULT);
		
		//Bind every line to the right keyword and parameters applied to it
		for(String line : lines)
		{
			//Gets every part of our string
			String[] parts = line.split(" ");
			
			//Verify if we want  to switch to another Script Block
			if(parts[0].equalsIgnoreCase("on"))
			{
				SBlockType type = null;
				try {
					type = SBlockType.valueOf(parts[1]);
				} catch (IllegalArgumentException e)
				{
					ScriptException sExc = new ScriptException("Unknown Event Type", this.name, lineCount);
					sExc.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException e)
				{
					ScriptException sExc = new ScriptException("You must specify an event type.", this.name, lineCount);
					sExc.printStackTrace();
				}
				
				this.toRun.add(currBlock);
				currBlock = new SBlock(this.name, lineCount, type);
			}
			
			//Verify which keyword is executed by the user
			for(Keyword keyword : KeywordManager.keywords)
			{	
				//Verify if first word is a "known keyword"
				if(parts[0].equalsIgnoreCase(keyword.getId()))
				{
					currBlock.addAction(keyword, Utils.getSliceOf(parts, 1, parts.length));
				}
			}
			
			lineCount++;
		}
		
		this.toRun.add(currBlock); // Adds last script block
	}
	
	//======== GETTERS ========
	
	public String getName()
	{
		return name;
	}
	
	public List<SBlock> getBlocks()
	{
		return toRun;
	}
	
	//======== Script Management =======
	
	public void toggle()
	{
		this.enabled = !this.enabled;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
}
