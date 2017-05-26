package rush93.simplecraft.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;


public class Allitems {

	public static ItemCreator QUIT = new ItemCreator(Material.BARRIER, (short)0, 1, ChatColor.DARK_RED + "Back");
	public static ItemCreator EMPTY = new ItemCreator(Material.STAINED_GLASS_PANE, (short)15, 1, ChatColor.RESET+"");
	public static ItemCreator PREV = new ItemCreator(Material.STAINED_GLASS_PANE, (short)14, 1, ChatColor.RED + "Previous page");
	public static ItemCreator NEXT = new ItemCreator(Material.STAINED_GLASS_PANE, (short)5, 1, ChatColor.GREEN + "Next page");
	
}
