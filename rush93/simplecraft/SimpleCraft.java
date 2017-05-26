package rush93.simplecraft;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush93.simplecraft.commands.Commande;
import rush93.simplecraft.commands.CraftCommand;
import rush93.simplecraft.commands.HelpCommand;
import rush93.simplecraft.commands.ReloadCommand;
import rush93.simplecraft.gui.InventoryEvent;
import rush93.simplecraft.gui.SneakClickEvent;
import rush93.simplecraft.items.InventoryHelper;
import rush93.simplecraft.items.ItemCategory;

public class SimpleCraft  extends JavaPlugin {
	public CommandSender console;
	public final static String Admin_Perm = "simplecraft.admin";
	public final static String Use_Perm = "simplecraft.use";
	public final static String SneakClick_Perm = "simplecraft.sneakclick";
	public final static String prefix = ChatColor.GOLD +"["+ChatColor.AQUA+"SimpleCraft"+ChatColor.GOLD + "] ";
	public static ArrayList<Commande> COMMANDES;
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{
		ArrayList<String> liste = new ArrayList<>();
		if(args.length==0){
			liste.add("ebank");
			return liste;
		}
		
		if(args.length==1){
			String label = args[0];
			for (int i = 0; i < COMMANDES.size(); i++) {
				if(label.length()<= COMMANDES.get(i).label.length() && COMMANDES.get(i).label.substring(0, label.length()).equalsIgnoreCase(label) && COMMANDES.get(i).canExecute(sender)){
					liste.add(COMMANDES.get(i).label);
				}
			}
		}else{
			for (int i = 0; i < COMMANDES.size(); i++) {
				if(args[0].equalsIgnoreCase(COMMANDES.get(i).label) && COMMANDES.get(i).canExecute(sender)){
					return COMMANDES.get(i).getTab(sender, args);
				}
			}
		}
		return liste;
	}
	
	@Override
	public void onEnable(){
		console = this.getServer().getConsoleSender();
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.addPermission(new Permission(Admin_Perm));
		if(loadConfig()){
			initCommandes();
			registerEvent();
			getServer().getConsoleSender().sendMessage(ChatColor.AQUA+"BONJOUR ! j'aime les bonbons");
		}
	}
	
	private void registerEvent(){
		new InventoryEvent(this);
		new SneakClickEvent(this);
	}
	
	private void initCommandes() {
		SimpleCraft.COMMANDES = new ArrayList<>();
		SimpleCraft.COMMANDES.add(new ReloadCommand(this));
		SimpleCraft.COMMANDES.add(new CraftCommand(this));
		SimpleCraft.COMMANDES.add(new HelpCommand(this));
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender p, Command commande, String commandLabel, String[] args){
		if(commandLabel.equalsIgnoreCase("sc") || commandLabel.equalsIgnoreCase("scraft") || commandLabel.equalsIgnoreCase("simplecraft")){
			if(args.length < 1){
				if(!Utils.hasUsePermission(p)){
					p.sendMessage(Utils.MessageErrorNoPermission);
					return false;
				}
				SimpleCraft.openItemListInventory((Player)p);
				return true;
			}
			for (int i = 0; i < COMMANDES.size(); i++) {
				if(COMMANDES.get(i).label.equalsIgnoreCase(args[0])){
					return COMMANDES.get(i).run(p, args);
				}
			}
			SimpleCraft.affHelp(p);
		}
		return false;
		
	}
	public static void openItemListInventory(Player p){
		p.openInventory(InventoryHelper.getAllItemInventory());
	}
	

	public void openCategoryInventory(Player p,ItemCategory cat, int page) {
		p.openInventory(InventoryHelper.getInventoryCategory(cat,page));
	}
	
	public static void affHelp(CommandSender p){
		p.sendMessage(ChatColor.GOLD + "~~" + ChatColor.AQUA + " SimpleCraft " + ChatColor.GOLD + "~~");
		p.sendMessage(ChatColor.GRAY +"Plugin made by : rush93");
		boolean showOne=false;
		p.sendMessage(ChatColor.GOLD + "/sc "+ChatColor.AQUA+ Utils.HelpBase);
		for (int i = 0; i < COMMANDES.size(); i++) {
			if(COMMANDES.get(i).getHelp(p) && !showOne){
				showOne = true;
			}
		}
	}
	
	public boolean loadConfig(){
		try{
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			Utils.loadConfig(this);
		}catch(Exception e){
			this.console.sendMessage(prefix + ""+ ChatColor.RED +"Error ! in config.yml please fix it !\n");
			return false;
		}
		return true;
	}

}
