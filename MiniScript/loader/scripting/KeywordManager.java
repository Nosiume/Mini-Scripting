package MiniScript.loader.scripting;

import java.util.ArrayList;
import java.util.List;

import MiniScript.loader.scripting.keywords.Send;
import MiniScript.loader.scripting.keywords.Title;

public class KeywordManager {

	public static List<Keyword> keywords = new ArrayList<Keyword>();
	
	public static void init()
	{
		keywords.add(new Send());
		keywords.add(new Title());
	}
	
}
