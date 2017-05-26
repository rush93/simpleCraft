package rush93.simplecraft.gui;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import rush93.simplecraft.SimpleCraft;
import rush93.simplecraft.Utils;
import rush93.simplecraft.craft.Craft;
import rush93.simplecraft.craft.CraftList;
import rush93.simplecraft.items.Allitems;
import rush93.simplecraft.items.ItemCategory;

public class InventoryEvent implements  Listener{
	private SimpleCraft plugin;
	

	public InventoryEvent(SimpleCraft plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onClickMenu(InventoryClickEvent event) {
		if(event.getInventory().getName().contains(" page: ")){
			if(event.isShiftClick()){
				event.setCancelled(true);
			}
			if(event.getRawSlot() > -1 && event.getRawSlot() <  event.getView().getTopInventory().getSize()){

				event.setCancelled(true);
				ItemStack item = event.getCurrentItem();
				if(item==null){
					item = event.getCursor();
				}
				if(Allitems.EMPTY.equals(item)){
					return;
				}
				if(Allitems.PREV.equals(item) || Allitems.NEXT.equals(item) ){
					String[] name = event.getInventory().getName().split(" page: ");
					int page = Integer.parseInt(name[1]);
					
					ItemCategory cat = ItemCategory.getCategory(name[0]);
					if(cat!=null){
						event.setCancelled(true);
						plugin.openCategoryInventory((Player) event.getWhoClicked(),cat,Allitems.PREV.equals(item)?page-1:page+1);
					}
					return;
				}
				if(Allitems.NEXT.equals(item)){
					return;
				}
				if(Allitems.QUIT.equals(item)){
					SimpleCraft.openItemListInventory(((Player) event.getWhoClicked()));
					return;
				}
				if(item ==null || item.getType().equals(Material.AIR)){
					return;
				}

				if(event.isShiftClick()){
					Player p = (Player) event.getWhoClicked();
					ArrayList<CraftList> recipes = Craft.getRessources(item);
					if(recipes.size() <= 0){
							p.sendMessage(Utils.MessageUncraftable);
						return ;
					}
					int nb = recipes.get(0).getNumber()==0?1:recipes.get(0).getNumber();
					int amount = item.getMaxStackSize()/nb;
					
					for(int i = 0; i<amount;i++){
						if(!Craft.CraftItemRecurcif(item,(Player) event.getWhoClicked(),false,new HashMap<Material,HashMap<Short,Boolean>>())){
							p.sendMessage(Utils.MessageNotCrafted);
							return;
						}
					}
					((Player) event.getWhoClicked()).sendMessage(Utils.MessageCraftSuccess);
				} else {
					Craft.CraftItem(item,(Player) event.getWhoClicked());
				}
			}
		} 
		else if(event.getInventory().getName().equals("Categories")){
			if(event.isShiftClick()){
				event.setCancelled(true);
			} 
			if(event.getRawSlot() > -1 && event.getRawSlot() <  event.getView().getTopInventory().getSize()){
				ItemStack item = event.getCurrentItem();
				if(item==null || item.getType().equals(Material.AIR)){
					item = event.getCursor();
				}
				if(Allitems.EMPTY.equals(item)){
					event.setCancelled(true);
					return;
				}
				if(item!=null &&  !item.getType().equals(Material.AIR) && item.getItemMeta()!=null){
					ItemCategory cat = ItemCategory.getCategory(item.getItemMeta().getDisplayName());
					if(cat!=null){
						event.setCancelled(true);
						plugin.openCategoryInventory((Player) event.getWhoClicked(),cat,1);
					}
				}
			}
		}
	}
	
}
