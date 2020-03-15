package MiniScript.loader.scripting.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import MiniScript.loader.blocks.SBlockType;

public class ScriptEvent implements Listener {
	
	@EventHandler
	public void onLeftClick(PlayerInteractEvent event)
	{
		Action action = event.getAction();
		if(action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR)
		{
			MiniScript.MiniScript.getFileLoader().triggerEvent(SBlockType.LEFT_CLICK);
		} else if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)
		{
			MiniScript.MiniScript.getFileLoader().triggerEvent(SBlockType.RIGHT_CLICK);
		}
	}
	
	@EventHandler
	public void onBreakBlock(BlockBreakEvent event)
	{
		MiniScript.MiniScript.getFileLoader().triggerEvent(SBlockType.BLOCK_BREAK);
	}
	
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent event)
	{
		MiniScript.MiniScript.getFileLoader().triggerEvent(SBlockType.BLOCK_PLACE);
	}
	
}
