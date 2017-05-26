package rush93.simplecraft.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rush93.simplecraft.SimpleCraft;
import rush93.simplecraft.Utils;
import rush93.simplecraft.craft.Craft;
import rush93.simplecraft.items.ItemCategory;
import rush93.simplecraft.items.ItemCreator;

public class CraftCommand extends Commande{

	public CraftCommand(SimpleCraft plugin) {
		super("craft", plugin);
	}
	
	public List<String> getTab(CommandSender sender, String[] args) {
		if(!Utils.hasUsePermission(sender)){
			return null;
		}
		if(args.length == 2){
			ArrayList<String> list = new ArrayList<>();
			ArrayList<ItemStack> items = new ArrayList<>();
			for (int i = 0; i < ItemCategory.CATEGORIES.length; i++) {
				items.addAll(ItemCategory.CATEGORIES[i].getItems());
			}

			String begin = args[1];
			for (int i = 0; i < items.size(); i++){
				if (begin.length()<= items.get(i).getType().toString().length() && items.get(i).getType().toString().substring(0, begin.length()).equalsIgnoreCase(begin)) {
					if(!(Utils.DisabledItems.containsKey(items.get(i).getType()) && Utils.DisabledItems.get(items.get(i).getType()).containsKey(items.get(i).getDurability()))){
						String str = items.get(i).getType().toString() + (items.get(i).getDurability() == 0? "":":"+items.get(i).getDurability());
						list.add(str);
					}
				}
			}

			return list;
		} else if(args.length == 3){
			ArrayList<String> list = new ArrayList<>();
			String begin = args[2];
			list.add("8");
			list.add("16");
			list.add("32");
			list.add("64");
			
			for (int i = 0; i < list.size(); i++) {
				if (!(begin.length()<= list.get(i).length() && list.get(i).substring(0, begin.length()).equalsIgnoreCase(begin))) {
					list.remove(i);
					i--;
				}
			}
			return list;
		}
		return null;
	}
	
	public boolean canExecute(CommandSender sender) {
		return Utils.hasUsePermission(sender);
	}
	
	@Override
	public boolean run(CommandSender sender, String[] args) {
		if(!Utils.hasUsePermission(sender)){
			sender.sendMessage(Utils.MessageErrorNoPermission);
			return false;
		}
		if(!(sender instanceof Player)){
			sender.sendMessage(Utils.MessageNotAPlayer);
		}
		Player p = (Player)sender;
		Material m;
		if(args.length < 2){
			this.getHelp(p);
			return false;
		}

		String[] itemId = args[1].split(":");
		String materialName = args[1];
		short durability =0;
		if(itemId.length>=2){
			materialName = itemId[0];
			try {
				durability = Short.parseShort(itemId[1]);
			} catch (Exception e) {
				p.sendMessage(Utils.MessageInvalidData);
			}
			if(durability < 0){
				p.sendMessage(Utils.MessagePositiveInt);
			}
		}
		m = Material.getMaterial(materialName);
		if(m==null){
			p.sendMessage(Utils.MessageUnknowMaterial);
			return false;
		}
		int amount = 1;
		if(args.length>=3){
			try {
				amount = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {
				p.sendMessage(Utils.MessageInvalidAmount);
				return false;
			}
			if(amount <=0){
				p.sendMessage(Utils.MessagePositiveInt);
			}
		}
		ItemStack item = new ItemCreator(m, durability, amount).item;
		Craft.CraftItem(item,p);
		return true;
	}
	
	@Override
	public boolean getHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "/sc craft <Material[:data]> [amount] "+ChatColor.AQUA+ Utils.HelpCraft);
		return false;
	}

}
