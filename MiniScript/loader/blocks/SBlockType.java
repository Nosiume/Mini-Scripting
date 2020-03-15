package MiniScript.loader.blocks;

public enum SBlockType {

	/*
	 * 
	 * This will be used for event registering.
	 * 
	 * For example, left_click will trigger script block
	 * only if a player clicks left button
	 * 
	 * (Default is the main function, it will only be triggered of the user runs the program)
	 */
	
	DEFAULT,
	ENABLE,
	DISABLE,
	LEFT_CLICK,
	RIGHT_CLICK,
	BLOCK_BREAK,
	BLOCK_PLACE;
	
}
