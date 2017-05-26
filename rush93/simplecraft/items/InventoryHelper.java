package rush93.simplecraft.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import rush93.simplecraft.Utils;


public class InventoryHelper {


	public static Inventory getAllItemInventory() {
		Inventory inv = Bukkit.createInventory(null,Math.floorDiv(ItemCategory.CATEGORIES.length, 9)*9+9,"Categories");

		inv.setItem(0,Allitems.EMPTY.item);
		inv.setItem(1,Allitems.EMPTY.item);
		for (int i = 0; i < ItemCategory.CATEGORIES.length; i++) {
			inv.addItem(ItemCategory.CATEGORIES[i].getName());
			if(i%5==0){
				inv.setItem(7,Allitems.EMPTY.item);
				inv.setItem(8,Allitems.EMPTY.item);
				inv.setItem(9,Allitems.EMPTY.item);
				inv.setItem(10,Allitems.EMPTY.item);
			}
		}
		inv.setItem(16,Allitems.EMPTY.item);
		inv.setItem(17,Allitems.EMPTY.item);
		return inv;
	}
	
	public static String getInventoryName(String inventoryName){

		String version = Bukkit.getVersion();
		for(int i = 5; i <=8 ;i++){
			if(version.contains(" 1."+i) && inventoryName.length() >=32){
				inventoryName = inventoryName.substring(0, 32);
			}
		}
		return inventoryName;
	}

	public static Inventory getInventoryCategory(ItemCategory cat, int page) {

		Inventory inv = Bukkit.createInventory(null, 6*9,cat.getName().getItemMeta().getDisplayName() + " page: "+page);
		ArrayList<ItemStack> items = cat.getItems();
		for (int i = 45*(page-1); i < items.size() && i < 45*page; i++) {
			if(Utils.DisabledItems.containsKey(items.get(i).getType()) && Utils.DisabledItems.get(items.get(i).getType()).containsKey(items.get(i).getDurability())){
				items.remove(i);
				cat.setItems(items);
				i--;
				continue;
			}
			inv.addItem(items.get(i));
		}
		if(page > 1){
			inv.setItem(45,Allitems.PREV.item);
		} else{
			inv.setItem(45,Allitems.EMPTY.item);
		}
		inv.setItem(46,Allitems.EMPTY.item);
		inv.setItem(47,Allitems.EMPTY.item);
		inv.setItem(48,Allitems.EMPTY.item);

		inv.setItem(49,Allitems.QUIT.item);
		
		inv.setItem(50,Allitems.EMPTY.item);
		inv.setItem(51,Allitems.EMPTY.item);
		inv.setItem(52,Allitems.EMPTY.item);
		

		if( items.size() > 45*page){
			inv.setItem(53,Allitems.NEXT.item);
		} else{
			inv.setItem(53,Allitems.EMPTY.item);
		}
		return inv;
	}
}
