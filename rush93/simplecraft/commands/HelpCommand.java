package rush93.simplecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import rush93.simplecraft.SimpleCraft;
import rush93.simplecraft.Utils;

public class HelpCommand extends Commande{

	public HelpCommand(SimpleCraft plugin) {
		super("help", plugin);
	}

	@Override
	public boolean run(CommandSender sender, String[] args) {
		if(Utils.hasUsePermission(sender)){
			SimpleCraft.affHelp(sender);
			return true;
		}
		sender.sendMessage(Utils.MessageErrorNoPermission);
		return false;
	}

	@Override
	public boolean getHelp(CommandSender sender) {
		if(Utils.hasUsePermission(sender)){
			sender.sendMessage(ChatColor.GOLD + "/sc help "+ChatColor.AQUA+ Utils.HelpHelp);
			return true;
		}
		return false;
	}
	
	public boolean canExecute(CommandSender sender) {
		return Utils.hasUsePermission(sender);
	}
}
