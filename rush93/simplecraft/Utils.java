package rush93.simplecraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import rush93.simplecraft.items.ItemCategory;

public class Utils {
	
	public static String HelpNoPermission;
	public static String HelpReload;
	public static String HelpCraft;
	public static String HelpHelp;
	public static String HelpBase;
	
	public static String MessageReloadSuccess;
	public static String MessageCraftSuccess;

	public static String MessageErrorNoPermission;
	public static String MessageErrorReload;
	public static String MessageNotAPlayer;
	public static String MessageInvalidData;
	public static String MessageInvalidAmount;
	public static String MessagePositiveInt;
	public static String MessageUnknowMaterial;
	public static String MessageUncraftable;
	public static String MessageNotCrafted;
	
	public static Boolean EnableUsePermission;
	
	public static Boolean EnableSneakClick;
	public static Material SneakClickItem;
	
	public static HashMap<Material,HashMap<Short,Boolean>> DisabledItems;
	
	public static void save(Object o, File f) {
		try {
			if(!f.exists())
				f.createNewFile();
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(o);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Object load(File f){
	
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			Object result = ois.readObject();
			ois.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isAdmin(CommandSender p){
		return p.isOp() || p.hasPermission(SimpleCraft.Admin_Perm);
	}
	public static int isPositiveInt(String s){
		int nb=-1;
		try {
			nb = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return -1;
		}
		if(nb<0){
			return -1;
		}else{
			return nb;
		}
	}

	public static void loadConfig(SimpleCraft plugin) {
		HelpNoPermission = FormatColorMinecraft(plugin.getConfig().getString("help.errors.noPermission"));
		HelpReload = FormatColorMinecraft(plugin.getConfig().getString("help.reload"));
		HelpCraft = FormatColorMinecraft(plugin.getConfig().getString("help.craft"));
		HelpHelp = FormatColorMinecraft(plugin.getConfig().getString("help.help"));
		HelpBase = FormatColorMinecraft(plugin.getConfig().getString("help.base"));
		
		MessageReloadSuccess = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.reloadSuccess"));
		MessageCraftSuccess = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.craftSuccess"));
		
		MessageErrorNoPermission = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.noPermission"));
		MessageErrorReload = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.reload"));
		MessageNotAPlayer = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.notAPlayer"));
		MessageInvalidData = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.invalidData"));
		MessageInvalidAmount = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.invalidAmount"));
		MessagePositiveInt = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.positiveInt"));
		MessageUnknowMaterial = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.unknowMaterial"));
		MessageUncraftable = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.uncraftable"));
		MessageNotCrafted = SimpleCraft.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.notCrafted"));

		EnableUsePermission = plugin.getConfig().getBoolean("enableUsePermission");
		EnableSneakClick = plugin.getConfig().getBoolean("enableSneakClick");
		SneakClickItem = Material.getMaterial(plugin.getConfig().getString("sneakClickItem"));
		if(SneakClickItem == null && EnableSneakClick) {
			Bukkit.getConsoleSender().sendMessage(SimpleCraft.prefix + ChatColor.RED + " Error: sneakClickItem is not reconized (" + plugin.getConfig().getString("sneakClickItem") + ")");
		}
		List<?> list = plugin.getConfig().getList("disabledCraft");
		DisabledItems = new HashMap<Material, HashMap<Short,Boolean>>();
		ItemCategory.reloadCategory();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof String){
				String[] arr = ((String)list.get(i)).split(":");
				String material = arr[0];
				short data = 0;
				if(arr.length>=2){
					try {
						data =Short.parseShort(arr[1]);
					} catch (Exception e) {
						Bukkit.getServer().getConsoleSender().sendMessage(SimpleCraft.prefix + ChatColor.RED+" Error in config: data value is not a short ( "+(String)list.get(i)+" )");
						continue;
					}
					if(data < 0){
						Bukkit.getServer().getConsoleSender().sendMessage(SimpleCraft.prefix + ChatColor.RED+" Error in config: data can't be negative ( "+(String)list.get(i)+" )");
						continue;
					}
				}
				
				Material m = Material.getMaterial(material);
				if(m == null ){
					Bukkit.getServer().getConsoleSender().sendMessage(SimpleCraft.prefix + ChatColor.RED+" Error in config: unreconized material ( "+(String)list.get(i)+" )");
					continue;
				}
				
				if(DisabledItems.containsKey(m)){
					if(DisabledItems.get(m).containsKey(data)){
						Bukkit.getServer().getConsoleSender().sendMessage(SimpleCraft.prefix + ChatColor.RED+" Error in config: duplicated material ( "+(String)list.get(i)+" )");
						continue;
					}
					DisabledItems.get(m).put(data, true);
					continue;
				}
				HashMap<Short, Boolean> map = new HashMap<>();
				map.put(data, true);
				DisabledItems.put(m, map);
				
			} else {
				Bukkit.getServer().getConsoleSender().sendMessage(SimpleCraft.prefix + ChatColor.RED+" Error in config: disabledCraft( "+i+ " ) no recognized as a String");
			}
		}
	}
	
	public static boolean hasUsePermission(CommandSender s){
		if(Utils.EnableUsePermission){
			if(Utils.isAdmin(s)){
				return true;
			}
			if(s.hasPermission(SimpleCraft.Use_Perm)){
				return true;
			}
			return false;
		}
		return true;
	}
	
	public static boolean hasSneakClickPermission(CommandSender s){
		if(Utils.EnableSneakClick){
			if(Utils.isAdmin(s)){
				return true;
			}
			if(s.hasPermission(SimpleCraft.SneakClick_Perm)){
				return true;
			}
			return false;
		}
		return true;
	}
	
	public static String FormatColorMinecraft(String text){
		text = text.replace("&0", ChatColor.BLACK+"");
		text = text.replace("&1", ChatColor.DARK_BLUE+"");
		text = text.replace("&2", ChatColor.DARK_GREEN+"");
		text = text.replace("&3", ChatColor.DARK_AQUA+"");
		text = text.replace("&4", ChatColor.DARK_RED+"");
		text = text.replace("&5", ChatColor.DARK_PURPLE+"");
		text = text.replace("&6", ChatColor.GOLD+"");
		text = text.replace("&7", ChatColor.GRAY+"");
		text = text.replace("&8", ChatColor.DARK_GRAY+"");
		text = text.replace("&9", ChatColor.BLUE+"");
		text = text.replace("&a", ChatColor.GREEN+"");
		text = text.replace("&b", ChatColor.AQUA+"");
		text = text.replace("&c", ChatColor.RED+"");
		text = text.replace("&d", ChatColor.LIGHT_PURPLE+"");
		text = text.replace("&e", ChatColor.YELLOW+"");
		text = text.replace("&f", ChatColor.WHITE+"");
		text = text.replace("&k", ChatColor.MAGIC+"");
		text = text.replace("&l", ChatColor.BOLD+"");
		text = text.replace("&m", ChatColor.STRIKETHROUGH+"");
		text = text.replace("&n", ChatColor.UNDERLINE+"");
		text = text.replace("&o", ChatColor.ITALIC+"");
		text = text.replace("&r", ChatColor.RESET+"");
		return text;
	}
}
