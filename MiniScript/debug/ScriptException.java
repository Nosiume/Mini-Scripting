package MiniScript.debug;

public class ScriptException extends Exception {

	private static final long serialVersionUID = 1L;

	public ScriptException(String details, String script, int line)
	{
		super("There was an error in script \"" + script + "\" at line " + line + " : " + details);
	}
	
}
