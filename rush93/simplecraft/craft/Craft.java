package rush93.simplecraft.craft;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import rush93.simplecraft.Utils;

public class Craft {


	public static boolean isCraftable(ItemStack item){
		getRessources(item);
		List<Recipe> recipes = Bukkit.getRecipesFor(item);
		if(recipes.size() <= 0 ){
			return false;
		} else {
			boolean shapeless = true;
			for (Recipe r : recipes) {
				if(r instanceof ShapedRecipe){
					shapeless = false;
					break;
				} else if(r instanceof ShapelessRecipe){
					shapeless = false;
					break;
				}
			}
			return !shapeless;
		}
		
	}
	
	public static ArrayList<CraftList> getRessources(ItemStack item){
		List<Recipe> recipes = Bukkit.getRecipesFor(item);
		ArrayList<CraftList> items = new ArrayList<>();
		int index = 0;
		
		for (Recipe r : recipes) {
			HashMap<String, HashMap<Short,Integer>> materials = new HashMap<String, HashMap<Short,Integer>>();
			if(r instanceof ShapedRecipe){
				String[] shape = ((ShapedRecipe) r).getShape();
				items.add(new CraftList(new ArrayList<ItemStack>(), ((ShapedRecipe) r).getResult().getAmount()));
				
				HashMap<Character, ItemStack> ingredients = (HashMap<Character, ItemStack>)(((ShapedRecipe) r).getIngredientMap());
				
				for (String string : shape) {
					
					for (int i = 0; i < string.length(); i++) {
						
						ItemStack item1 = ingredients.get(string.charAt(i));
						if(item1 != null){
							if(materials.containsKey(item1.getType().name())){
								if(materials.get(item1.getType().name()).containsKey(item1.getDurability())){
									int itemIndex = materials.get(item1.getType().name()).get(item1.getDurability());
									items.get(index).getItem(itemIndex).setAmount(items.get(index).getItem(itemIndex).getAmount()+item.getAmount());;
								}else{
									HashMap<Short, Integer> map = new  HashMap<Short,Integer>();
									map.put(item1.getDurability(),items.get(index).getIngredients().size());
									materials.put(item1.getType().name(),map);
									item1.setAmount(item.getAmount());
									items.get(index).addItem(item1);
								}
							} else{
								HashMap<Short, Integer> map = new  HashMap<Short,Integer>();
								map.put(item1.getDurability(),items.get(index).getIngredients().size());
								materials.put(item1.getType().name(),map);
								item1.setAmount(item.getAmount());
								items.get(index).addItem(item1);
							}
						} 
					}
				}

				index++;
			} else if(r instanceof ShapelessRecipe){
				List<ItemStack> ingredients = ((ShapelessRecipe) r).getIngredientList();
				
				items.add(new CraftList(new ArrayList<ItemStack>(), ((ShapelessRecipe) r).getResult().getAmount()));
				for (int j2 = 0; j2 < ingredients.size(); j2++) {
					
					ItemStack item1 = ingredients.get(j2);
					if(item1 != null){
						if(materials.containsKey(item1.getType().name())){
							if(materials.get(item1.getType().name()).containsKey(item1.getDurability())){
								int itemIndex = materials.get(item1.getType().name()).get(item1.getDurability());
								items.get(index).getItem(itemIndex).setAmount(items.get(index).getItem(itemIndex).getAmount()+1);;
							}else{
								HashMap<Short, Integer> map = new  HashMap<Short,Integer>();
								map.put(item1.getDurability(),items.get(index).getIngredients().size());
								materials.put(item1.getType().name(),map);
								items.get(index).addItem(item1);
							}
						} else{
							HashMap<Short, Integer> map = new  HashMap<Short,Integer>();
							map.put(item1.getDurability(),items.get(index).getIngredients().size());
							materials.put(item1.getType().name(),map);
							items.get(index).addItem(item1);
						}
					} 
				}
				break;
			}
		}
		return items;
	}
	
	public static boolean hasItem(ItemStack item, Player p){
		PlayerInventory inv = p.getInventory();
		ItemStack[] items = inv.getContents();
		int amount = 0;
		for(int i = 0; i < items.length;i++){
			ItemStack invItem = items[i];
			if(invItem != null){
				if(invItem.getType().equals(item.getType()) && (invItem.getDurability() == item.getDurability() || item.getDurability() >= Short.MAX_VALUE)){
					amount+=invItem.getAmount();
					if(amount >= item.getAmount()){
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	public static ArrayList<ItemStack> removeItem(ItemStack item, Player p){
		PlayerInventory inv = p.getInventory();
		ItemStack[] items = inv.getContents();
		ArrayList<ItemStack> itemsTook = new ArrayList<>();
		int amount = item.getAmount();
		for(int i = 0; i < items.length;i++){
			ItemStack invItem = items[i];
			if(invItem != null){
				if(invItem.getType().equals(item.getType()) && (invItem.getDurability() == item.getDurability() || item.getDurability() >= Short.MAX_VALUE)){
					if(amount < invItem.getAmount()){
						itemsTook.add(new ItemStack(invItem.getType(), amount, invItem.getDurability()));
						invItem.setAmount(invItem.getAmount()-amount);
						break;
					}
					itemsTook.add(new ItemStack(invItem.getType(), invItem.getAmount(), invItem.getDurability()));
					amount-=invItem.getAmount();
					items[i] = null;
					
				}
			}
		}
		inv.setContents(items);
		return itemsTook;
	}
	
	public static boolean CraftItemRecurcif(ItemStack item, Player p, boolean displayMessage,HashMap<Material,HashMap<Short,Boolean>> map){
		if(Utils.DisabledItems.containsKey(item.getType()) && Utils.DisabledItems.get(item.getType()).containsKey(item.getDurability())){
			if(displayMessage)
				p.sendMessage(Utils.MessageUncraftable);
			return false;
		}
		ArrayList<CraftList> recipes = getRessources(item);
		if(recipes.size() <= 0){
			if(displayMessage)
				p.sendMessage(Utils.MessageUncraftable);
			return false;
		}
		int prevAmount = item.getAmount();
		int nb = recipes.get(0).getNumber()==0?1:recipes.get(0).getNumber();
		int amount = item.getAmount()/nb;
		if(item.getAmount()%nb > 0){
			amount++;
		}
		item.setAmount(amount);
		recipes = getRessources(item);
		item.setAmount(prevAmount);
		if(map.containsKey(item.getType())){
			if(map.get(item.getType()).containsKey(item.getDurability()) || item.getDurability() == Short.MAX_VALUE){
				if(displayMessage)
					p.sendMessage(Utils.MessageNotCrafted);
				return false;
			} else{
				map.get(item.getType()).put(item.getDurability(), true);
			}
		} else{
			HashMap<Short, Boolean> mapa = new HashMap<Short, Boolean>();
			mapa.put(item.getDurability(), true);
			map.put(item.getType(), mapa);
		}
		PlayerInventory playerInventory = p.getInventory();
		boolean isCrafted = false;
		
		for (int i = 0; i < recipes.size(); i++) {
			
			ArrayList<ItemStack> items = recipes.get(i).getIngredients();
			ArrayList<ArrayList<ItemStack>> itemTook = new ArrayList<>();
			boolean hasItem = true;
			
			for (int j = 0; j < items.size(); j++) {
				
				ItemStack Craftitem = items.get(j);
				
				if(!hasItem(Craftitem,p)){
					
					if(isCraftable(Craftitem)){
						
						if(!CraftItemRecurcif(Craftitem, p, false, map)){
							
							hasItem = false;
							break;
							
						} else {
							
							itemTook.add(removeItem(Craftitem, p));
						}
					} else{
						
						hasItem = false;
						break;
					}
				} else {
					itemTook.add(removeItem(Craftitem, p));
				}
			}
			if(hasItem){
				ItemStack itemToGive = new ItemStack(item.getType(),(recipes.get(i).getNumber()==0?1:recipes.get(i).getNumber())*amount,item.getDurability());
				putInInventory(playerInventory,itemToGive,p.getLocation());
				isCrafted = true;
				break;
			} else {
				for (int j = 0; j < itemTook.size(); j++) {
					for (int j2 = 0; j2 < itemTook.get(j).size(); j2++) {
						putInInventory(playerInventory,itemTook.get(j).get(j2),p.getLocation());
					}
				}
			}
			
		}
		if(isCrafted){
			if(displayMessage)
				p.sendMessage(Utils.MessageCraftSuccess);
			return true;
		} else {
			if(displayMessage)
				p.sendMessage(Utils.MessageNotCrafted);
			return false;
		}
	}
	
	public static void CraftItem(ItemStack item, Player p){
		CraftItemRecurcif(item,p,true,new HashMap<Material,HashMap<Short,Boolean>>());
	}
	
	public static void putInInventory(PlayerInventory inv,ItemStack item, Location pos){
		
		ItemStack[] items = inv.getContents();
		
		int amount = item.getAmount();
		for (int i = 0; i < items.length; i++) {
			if(items[i] != null && !items[i].getType().equals(Material.AIR) && items[i].isSimilar(item)) {
				int nbToAdd = amount;
				if(items[i].getMaxStackSize() < nbToAdd + items[i].getAmount()){
					nbToAdd = items[i].getMaxStackSize()-items[i].getAmount();
				}
				items[i].setAmount(items[i].getAmount()+nbToAdd);;
				amount-=nbToAdd;
				if(amount <= 0){
					break;
				}
			}
		}
		if(amount > 0){
			for (int i = 0; i < items.length && i < 35; i++) {
				if(items[i] == null || items[i].getType().equals(Material.AIR)){
					int nbToGive = amount;
					if(item.getMaxStackSize()<nbToGive){
						nbToGive = item.getMaxStackSize();
					}
					items[i] = new ItemStack(item.getType(),nbToGive,item.getDurability());
					amount-=nbToGive;
					if(amount <= 0){
						break;
					}
				}
			}
			if(amount>0){
				pos.getWorld().dropItem(pos, item);
			}
		}
		inv.setContents(items);
	}
	
}