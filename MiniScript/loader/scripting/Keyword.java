package MiniScript.loader.scripting;


public abstract class Keyword {

	private String id;
	
	public Keyword(String id)
	{
		this.id = id;
		KeywordManager.keywords.add(this);
	}
	
	public abstract void use(String[] parameters, String script, int line);
	
	public String getId()
	{
		return id;
	}
	
}
