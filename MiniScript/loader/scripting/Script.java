package MiniScript.loader.scripting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import MiniScript.utils.Utils;

public class Script {

	private File script;
	private String name;
	private boolean enabled = true;
	
	private List<String> lines = new ArrayList<String>();
	private HashMap<Keyword, String[]> toRun = new HashMap<Keyword, String[]>();
	
	public Script(File file, String name)
	{
		this.script = file;
		this.name = name;
	}
	
	public void run()
	{
		int lineCount = 1;
		for(Entry<Keyword, String[]> line : toRun.entrySet())
		{
			lineCount++;
			line.getKey().use(line.getValue(), name, lineCount);
		}
	}
	
	public void load()
	{
		toRun.clear();
		
		try {
			//Load every lines
			BufferedReader br = new BufferedReader(new FileReader(this.script));
			String line;
			while((line = br.readLine()) != null)
			{
				if(line.startsWith("#"))
					continue;
				
				this.lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Bind every line to the right keyword and parameters applied to it
		for(String line : lines)
		{
			//Gets every part of our string
			String[] parts = line.split(" ");
			
			//Verify which keyword is executed by the user
			for(Keyword keyword : KeywordManager.keywords)
			{
				//Verify if first word is a "known keyword"
				if(parts[0].equalsIgnoreCase(keyword.getId()))
				{
					toRun.put(keyword, Utils.getSliceOf(parts, 1, parts.length));
				}
			}
		}
	}
	
	//======== GETTERS ========
	
	public String getName()
	{
		return name;
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
