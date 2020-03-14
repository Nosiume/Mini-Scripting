package MiniScript.debug;

public class WorkspaceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WorkspaceException(String path)
	{
		super("There was an error trying to load or use scripting workspace \n" + 
				"(Workspace Path is " + path + ".");
	}

}
