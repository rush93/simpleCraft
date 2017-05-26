package rush93.simplecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import rush93.simplecraft.SimpleCraft;
import rush93.simplecraft.Utils;

public class ReloadCommand extends Commande{

	public ReloadCommand(SimpleCraft plugin) {
		super("reload", plugin);
	}

	@Override
	public boolean run(CommandSender sender, String[] args) {
		if(Utils.isAdmin(sender)){
			try {
				this.plugin.reloadConfig();
				this.plugin.loadConfig();
				sender.sendMessage(Utils.MessageReloadSuccess);
			} catch (Exception e) {
				sender.sendMessage(Utils.MessageErrorReload);
			}
		}else{
			sender.sendMessage(Utils.MessageErrorNoPermission);
		}
		return false;
	}
	public boolean canExecute(CommandSender sender) {
		return Utils.isAdmin(sender);
	}
	
	@Override
	public boolean getHelp(CommandSender sender) {
		if(Utils.isAdmin(sender)){
			sender.sendMessage(ChatColor.GOLD + "/sc reload "+ChatColor.AQUA+ Utils.HelpReload);
		}
		return false;
	}

}
