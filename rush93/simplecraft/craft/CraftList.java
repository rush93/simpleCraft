package rush93.simplecraft.craft;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class CraftList {
	private ArrayList<ItemStack> ingredients;
	private int number;
	
	public CraftList(ArrayList<ItemStack> ingredients, int number) {
		super();
		this.ingredients = ingredients;
		this.number = number;
	}
	
	public ArrayList<ItemStack> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<ItemStack> ingredients) {
		this.ingredients = ingredients;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void addItem(ItemStack item){
		this.ingredients.add(item);
	}
	
	public ItemStack getItem(int i){
		return this.ingredients.get(i);
	}
}
