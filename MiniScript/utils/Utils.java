package MiniScript.utils;

import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle.EnumTitleAction;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {

	public static void sendTitle(Player p, EnumTitleAction action, String json)
	{
		PacketPlayOutTitle packet = new PacketPlayOutTitle(action, ChatSerializer.a(json));
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static String[] getSliceOf(String[] array, int start, int end)
	{
		String[] render = new String[end - start];
		int count = 0;
		for(int i = start ; i < end ; i++)
		{
			render[count] = array[i];
			count++;
		}
		return render;
	}

	public static String getWorkspaceDir() {
		FileConfiguration config = MiniScript.MiniScript.instance.getConfig();
		return config.getString("workspace");
	}
	
	public static void setWorkspaceDir(String filePath)
	{
		FileConfiguration config = MiniScript.MiniScript.instance.getConfig();
		config.set("workspace", filePath);
		MiniScript.MiniScript.instance.saveConfig();
	}
	
}
