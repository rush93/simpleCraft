package rush93.simplecraft.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import rush93.simplecraft.SimpleCraft;


public abstract class Commande {
	
	public String label;
	public SimpleCraft plugin;
	
	public Commande(String label , SimpleCraft plugin){
		this.label = label;
		this.plugin = plugin;
	}
	
	public abstract boolean run(CommandSender sender, String[] args);
	
	public abstract boolean getHelp(CommandSender sender);

	public boolean canExecute(CommandSender sender) {
		return true;
	}

	public List<String> getTab(CommandSender sender, String[] args) {
		return null;
	}
}
