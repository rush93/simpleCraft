package rush93.simplecraft.items;

import static org.bukkit.Material.APPLE;
import static org.bukkit.Material.BRICK;
import static org.bukkit.Material.DOUBLE_PLANT;
import static org.bukkit.Material.GOLD_SWORD;
import static org.bukkit.Material.IRON_AXE;
import static org.bukkit.Material.LAVA_BUCKET;
import static org.bukkit.Material.POTION;
import static org.bukkit.Material.POWERED_RAIL;
import static org.bukkit.Material.REDSTONE;
import static org.bukkit.Material.STICK;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class ItemCategory {

	public static ItemCategory BLOCKS = ItemCategory.createStaticBlock();
	public static ItemCategory DECORATION_BLOCK = ItemCategory.createStaticDecorationBlock();
	public static ItemCategory REDSTONE_CAT = ItemCategory.createStaticRedstone();
	public static ItemCategory TRANSPORTATION = ItemCategory.createStaticTransportation();
	public static ItemCategory MISCELLANEOUS = ItemCategory.createStaticMiscellaneous();
	public static ItemCategory FOODSTUFFS = ItemCategory.createStaticFoodstuffs();
	public static ItemCategory TOOLS = ItemCategory.createStaticTools();
	public static ItemCategory COMBATS = ItemCategory.createStaticCombats();
	public static ItemCategory BREWING = ItemCategory.createStaticBrewing();
	public static ItemCategory MATERIALS = ItemCategory.createStaticMaterials();
	
	public static ItemCategory[] CATEGORIES = {BLOCKS, DECORATION_BLOCK, REDSTONE_CAT, TRANSPORTATION, MISCELLANEOUS, FOODSTUFFS, TOOLS, COMBATS, BREWING, MATERIALS};
	
	protected ArrayList<ItemStack> items;
	protected ItemStack name;
	
	public ItemCategory(ArrayList<ItemStack> items, ItemStack name) {
		super();
		this.items = items;
		this.name = name;
	}
	
	public ArrayList<ItemStack> getItems() {
		return items;
	}
	public void setItems(ArrayList<ItemStack> items) {
		this.items = items;
	}
	public ItemStack getName() {
		return name;
	}
	public void setName(ItemStack name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCategory other = (ItemCategory) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public static ItemCategory getCategory(String name){
		for (int i = 0; i < CATEGORIES.length; i++) {
			if(CATEGORIES[i].getName().getItemMeta().getDisplayName().equals(name)){
				return CATEGORIES[i];
			}
		}
		return null;
	}
	
	public static ArrayList<ItemStack> addItem(ArrayList<ItemStack> items, String material, int data){
		Material mat = Material.getMaterial(material);
		if(mat != null ){
			items.add(new ItemStack(mat,1,(short)data));
		}
		return items;
	}
	
	public static ItemCategory createStaticBlock(){
		
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"STONE",1);
		items = addItem(items,"STONE",2);
		items = addItem(items,"STONE",3);
		items = addItem(items,"STONE",4);
		items = addItem(items,"STONE",5);
		items = addItem(items,"DIRT",1);
		items = addItem(items,"WOOD",0);
		items = addItem(items,"WOOD",1);
		items = addItem(items,"WOOD",2);
		items = addItem(items,"WOOD",3);
		items = addItem(items,"WOOD",4);
		items = addItem(items,"WOOD",5);
		items = addItem(items,"LAPIS_BLOCK",0);
		items = addItem(items,"SANDSTONE",0);
		items = addItem(items,"SANDSTONE",1);
		items = addItem(items,"SANDSTONE",2);
		items = addItem(items,"WOOL",0);
		items = addItem(items,"WOOL",1);
		items = addItem(items,"WOOL",2);
		items = addItem(items,"WOOL",3);
		items = addItem(items,"WOOL",4);
		items = addItem(items,"WOOL",5);
		items = addItem(items,"WOOL",6);
		items = addItem(items,"WOOL",7);
		items = addItem(items,"WOOL",8);
		items = addItem(items,"WOOL",9);
		items = addItem(items,"WOOL",10);
		items = addItem(items,"WOOL",11);
		items = addItem(items,"WOOL",12);
		items = addItem(items,"WOOL",13);
		items = addItem(items,"WOOL",14);
		items = addItem(items,"WOOL",15);
		items = addItem(items,"GOLD_BLOCK",0);
		items = addItem(items,"IRON_BLOCK",0);
		items = addItem(items,"STEP",0);
		items = addItem(items,"STEP",1);
		items = addItem(items,"STEP",3);
		items = addItem(items,"STEP",4);
		items = addItem(items,"STEP",5);
		items = addItem(items,"STEP",6);
		items = addItem(items,"STEP",7);
		items = addItem(items,"BRICK",0);
		items = addItem(items,"BOOKSHELF",0);
		items = addItem(items,"MOSSY_COBBLESTONE",0);
		items = addItem(items,"WOOD_STAIRS",0);
		items = addItem(items,"DIAMOND_BLOCK",0);
		items = addItem(items,"COBBLESTONE_STAIRS",0);
		items = addItem(items,"SNOW_BLOCK",0);
		items = addItem(items,"CLAY",0);
		items = addItem(items,"GLOWSTONE",0);
		items = addItem(items,"JACK_O_LANTERN",0);
		items = addItem(items,"STAINED_GLASS",0);
		items = addItem(items,"STAINED_GLASS",1);
		items = addItem(items,"STAINED_GLASS",2);
		items = addItem(items,"STAINED_GLASS",3);
		items = addItem(items,"STAINED_GLASS",4);
		items = addItem(items,"STAINED_GLASS",5);
		items = addItem(items,"STAINED_GLASS",6);
		items = addItem(items,"STAINED_GLASS",7);
		items = addItem(items,"STAINED_GLASS",8);
		items = addItem(items,"STAINED_GLASS",9);
		items = addItem(items,"STAINED_GLASS",10);
		items = addItem(items,"STAINED_GLASS",11);
		items = addItem(items,"STAINED_GLASS",12);
		items = addItem(items,"STAINED_GLASS",13);
		items = addItem(items,"STAINED_GLASS",14);
		items = addItem(items,"STAINED_GLASS",15);
		items = addItem(items,"SMOOTH_BRICK",0);
		items = addItem(items,"SMOOTH_BRICK",1);
		items = addItem(items,"SMOOTH_BRICK",3);
		items = addItem(items,"MELON_BLOCK",0);
		items = addItem(items,"BRICK_STAIRS",0);
		items = addItem(items,"SMOOTH_STAIRS",0);
		items = addItem(items,"NETHER_BRICK",0);
		items = addItem(items,"NETHER_BRICK_STAIRS",0);
		items = addItem(items,"WOOD_STEP",0);
		items = addItem(items,"WOOD_STEP",1);
		items = addItem(items,"WOOD_STEP",2);
		items = addItem(items,"WOOD_STEP",3);
		items = addItem(items,"WOOD_STEP",4);
		items = addItem(items,"WOOD_STEP",5);
		items = addItem(items,"SANDSTONE_STAIRS",0);
		items = addItem(items,"EMERALD_BLOCK",0);
		items = addItem(items,"SPRUCE_WOOD_STAIRS",0);
		items = addItem(items,"BIRCH_WOOD_STAIRS",0);
		items = addItem(items,"JUNGLE_WOOD_STAIRS",0);
		items = addItem(items,"COBBLE_WALL",0);
		items = addItem(items,"COBBLE_WALL",1);
		items = addItem(items,"QUARTZ_BLOCK",0);
		items = addItem(items,"QUARTZ_BLOCK",1);
		items = addItem(items,"QUARTZ_BLOCK",2);
		items = addItem(items,"QUARTZ_STAIRS",0);
		items = addItem(items,"STAINED_CLAY",1);
		items = addItem(items,"STAINED_CLAY",2);
		items = addItem(items,"STAINED_CLAY",3);
		items = addItem(items,"STAINED_CLAY",4);
		items = addItem(items,"STAINED_CLAY",5);
		items = addItem(items,"STAINED_CLAY",6);
		items = addItem(items,"STAINED_CLAY",7);
		items = addItem(items,"STAINED_CLAY",8);
		items = addItem(items,"STAINED_CLAY",9);
		items = addItem(items,"STAINED_CLAY",10);
		items = addItem(items,"STAINED_CLAY",11);
		items = addItem(items,"STAINED_CLAY",12);
		items = addItem(items,"STAINED_CLAY",13);
		items = addItem(items,"STAINED_CLAY",14);
		items = addItem(items,"STAINED_CLAY",15);
		items = addItem(items,"ACACIA_STAIRS",0);
		items = addItem(items,"DARK_OAK_STAIRS",0);
		items = addItem(items,"PRISMARINE",0);
		items = addItem(items,"PRISMARINE",1);
		items = addItem(items,"PRISMARINE",2);
		items = addItem(items,"SEA_LANTERN",0);
		items = addItem(items,"HAY_BLOCK",0);
		items = addItem(items,"COAL_BLOCK",0);
		items = addItem(items,"RED_SANDSTONE",0);
		items = addItem(items,"RED_SANDSTONE",1);
		items = addItem(items,"RED_SANDSTONE",2);
		items = addItem(items,"RED_SANDSTONE_STAIRS",0);
		items = addItem(items,"PURPUR_BLOCK",0);
		items = addItem(items,"PURPUR_PILLAR",0);
		items = addItem(items,"PURPUR_STAIRS",0);
		items = addItem(items,"PURPUR_SLAB",0);
		items = addItem(items,"END_BRICKS",0);
		items = addItem(items,"MAGMA",0);
		items = addItem(items,"NETHER_WART_BLOCK",0);
		items = addItem(items,"RED_NETHER_BRICK",0);
		items = addItem(items,"BONE_BLOCK",0);
		
		return new ItemCategory(items, new ItemCreator(BRICK, (short)0, 1, ChatColor.AQUA + "Building Blocks" + ChatColor.RESET).getItemStack());
	}
	
	public static ItemCategory createStaticDecorationBlock(){

		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"TORCH",0);
		items = addItem(items,"CHEST",0);
		items = addItem(items,"WORKBENCH",0);
		items = addItem(items,"FURNACE",0);
		items = addItem(items,"LADDER",0);
		items = addItem(items,"SNOW",0);
		items = addItem(items,"JUKEBOX",0);
		items = addItem(items,"FENCE",0);
		items = addItem(items,"IRON_FENCE",0);
		items = addItem(items,"THIN_GLASS",0);
		items = addItem(items,"NETHER_FENCE",0);
		items = addItem(items,"ENCHANTMENT_TABLE",0);
		items = addItem(items,"ENDER_CHEST",0);
		items = addItem(items,"ANVIL",0);
		items = addItem(items,"STAINED_GLASS_PANE",0);
		items = addItem(items,"STAINED_GLASS_PANE",1);
		items = addItem(items,"STAINED_GLASS_PANE",2);
		items = addItem(items,"STAINED_GLASS_PANE",3);
		items = addItem(items,"STAINED_GLASS_PANE",4);
		items = addItem(items,"STAINED_GLASS_PANE",5);
		items = addItem(items,"STAINED_GLASS_PANE",6);
		items = addItem(items,"STAINED_GLASS_PANE",7);
		items = addItem(items,"STAINED_GLASS_PANE",8);
		items = addItem(items,"STAINED_GLASS_PANE",9);
		items = addItem(items,"STAINED_GLASS_PANE",10);
		items = addItem(items,"STAINED_GLASS_PANE",11);
		items = addItem(items,"STAINED_GLASS_PANE",12);
		items = addItem(items,"STAINED_GLASS_PANE",13);
		items = addItem(items,"STAINED_GLASS_PANE",14);
		items = addItem(items,"STAINED_GLASS_PANE",15);
		items = addItem(items,"SLIME_BLOCK",0);
		items = addItem(items,"CARPET",0);
		items = addItem(items,"CARPET",1);
		items = addItem(items,"CARPET",2);
		items = addItem(items,"CARPET",3);
		items = addItem(items,"CARPET",4);
		items = addItem(items,"CARPET",5);
		items = addItem(items,"CARPET",6);
		items = addItem(items,"CARPET",7);
		items = addItem(items,"CARPET",8);
		items = addItem(items,"CARPET",9);
		items = addItem(items,"CARPET",10);
		items = addItem(items,"CARPET",11);
		items = addItem(items,"CARPET",12);
		items = addItem(items,"CARPET",13);
		items = addItem(items,"CARPET",14);
		items = addItem(items,"CARPET",15);
		items = addItem(items,"SPRUCE_FENCE",0);
		items = addItem(items,"BIRCH_FENCE",0);
		items = addItem(items,"JUNGLE_FENCE",0);
		items = addItem(items,"DARK_OAK_FENCE",0);
		items = addItem(items,"ACACIA_FENCE",0);
		items = addItem(items,"PAINTING",0);
		items = addItem(items,"END_ROD",0);
		items = addItem(items,"SIGN",0);
		items = addItem(items,"BED",0);
		items = addItem(items,"ITEM_FRAME",0);
		items = addItem(items,"FLOWER_POT",0);
		items = addItem(items,"ARMOR_STAND",0);
		items = addItem(items,"BANNER",0);
		items = addItem(items,"BANNER",1);
		items = addItem(items,"BANNER",2);
		items = addItem(items,"BANNER",3);
		items = addItem(items,"BANNER",4);
		items = addItem(items,"BANNER",5);
		items = addItem(items,"BANNER",6);
		items = addItem(items,"BANNER",7);
		items = addItem(items,"BANNER",8);
		items = addItem(items,"BANNER",9);
		items = addItem(items,"BANNER",10);
		items = addItem(items,"BANNER",11);
		items = addItem(items,"BANNER",12);
		items = addItem(items,"BANNER",13);
		items = addItem(items,"BANNER",14);
		items = addItem(items,"BANNER",15);
		items = addItem(items,"END_CRYSTAL",0);
		
		return new ItemCategory(items, new ItemCreator(DOUBLE_PLANT, (short)5, 1, ChatColor.GREEN +"Decoration Blocks"+ ChatColor.RESET).getItemStack());
	}
	
	public static ItemCategory createStaticRedstone(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"DISPENSER",0);
		items = addItem(items,"NOTE_BLOCK",0);
		items = addItem(items,"PISTON_STICKY_BASE",0);
		items = addItem(items,"PISTON_BASE",0);
		items = addItem(items,"TNT",0);
		items = addItem(items,"LEVER",0);
		items = addItem(items,"STONE_PLATE",0);
		items = addItem(items,"WOOD_PLATE",0);
		items = addItem(items,"REDSTONE_TORCH_ON",0);
		items = addItem(items,"STONE_BUTTON",0);
		items = addItem(items,"TRAP_DOOR",0);
		items = addItem(items,"FENCE_GATE",0);
		items = addItem(items,"REDSTONE_LAMP_OFF",0);
		items = addItem(items,"TRIPWIRE_HOOK",0);
		items = addItem(items,"WOOD_BUTTON",0);
		items = addItem(items,"TRAPPED_CHEST",0);
		items = addItem(items,"GOLD_PLATE",0);
		items = addItem(items,"IRON_PLATE",0);
		items = addItem(items,"DAYLIGHT_DETECTOR",0);
		items = addItem(items,"REDSTONE_BLOCK",0);
		items = addItem(items,"HOPPER",0);
		items = addItem(items,"DROPPER",0);
		items = addItem(items,"IRON_TRAPDOOR",0);
		items = addItem(items,"SPRUCE_FENCE_GATE",0);
		items = addItem(items,"BIRCH_FENCE_GATE",0);
		items = addItem(items,"JUNGLE_FENCE_GATE",0);
		items = addItem(items,"DARK_OAK_FENCE_GATE",0);
		items = addItem(items,"ACACIA_FENCE_GATE",0);
		items = addItem(items,"WOOD_DOOR",0);
		items = addItem(items,"IRON_DOOR",0);
		items = addItem(items,"REDSTONE",0);
		items = addItem(items,"DIODE",0);
		items = addItem(items,"REDSTONE_COMPARATOR",0);
		items = addItem(items,"SPRUCE_DOOR_ITEM",0);
		items = addItem(items,"BIRCH_DOOR_ITEM",0);
		items = addItem(items,"JUNGLE_DOOR_ITEM",0);
		items = addItem(items,"ACACIA_DOOR_ITEM",0);
		items = addItem(items,"DARK_OAK_DOOR_ITEM",0);
		return new ItemCategory(items, new ItemCreator(REDSTONE, (short)0, 1, ChatColor.RED +"Redstone"+ ChatColor.RESET).getItemStack());
	}
	
	public static ItemCategory createStaticTransportation(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"POWERED_RAIL",0);
		items = addItem(items,"DETECTOR_RAIL",0);
		items = addItem(items,"RAILS",0);
		items = addItem(items,"ACTIVATOR_RAIL",0);
		items = addItem(items,"MINECART",0);
		items = addItem(items,"BOAT",0);
		items = addItem(items,"STORAGE_MINECART",0);
		items = addItem(items,"POWERED_MINECART",0);
		items = addItem(items,"CARROT_STICK",0);
		items = addItem(items,"EXPLOSIVE_MINECART",0);
		items = addItem(items,"HOPPER_MINECART",0);
		items = addItem(items,"BOAT_SPRUCE",0);
		items = addItem(items,"BOAT_BIRCH",0);
		items = addItem(items,"BOAT_JUNGLE",0);
		items = addItem(items,"BOAT_ACACIA",0);
		items = addItem(items,"BOAT_DARK_OAK",0);
		return new ItemCategory(items, new ItemCreator(POWERED_RAIL, (short)0, 1, ChatColor.YELLOW +"Transportation"+ ChatColor.RESET).getItemStack());
	}
	
	public static ItemCategory createStaticMiscellaneous(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"BEACON",0);
		items = addItem(items,"BUCKET",0);
		items = addItem(items,"PAPER",0);
		items = addItem(items,"BOOK",0);
		items = addItem(items,"SLIME_BALL",0);
		items = addItem(items,"EYE_OF_ENDER",0);
		items = addItem(items,"FIREWORK_CHARGE",0);
		items = addItem(items,"BOOK_AND_QUILL",0);
		items = addItem(items,"EMPTY_MAP",0);
		return new ItemCategory(items, new ItemCreator(LAVA_BUCKET, (short)0, 1, ChatColor.GOLD +"Miscellaneous"+ ChatColor.RESET).getItemStack());
		
	}
	
	public static ItemCategory createStaticFoodstuffs(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"MUSHROOM_SOUP",0);
		items = addItem(items,"BREAD",0);
		items = addItem(items,"GOLDEN_APPLE",0);
		items = addItem(items,"CAKE",0);
		items = addItem(items,"COOKIE",0);
		items = addItem(items,"PUMPKIN_PIE",0);
		items = addItem(items,"RABBIT_STEW",0);
		items = addItem(items,"BEETROOT_SOUP",0);
		return new ItemCategory(items, new ItemCreator(APPLE, (short)0, 1, ChatColor.DARK_RED +"Foodstuffs"+ ChatColor.RESET).getItemStack());
		
	}
	
	public static ItemCategory createStaticTools(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"IRON_SPADE",0);
		items = addItem(items,"IRON_PICKAXE",0);
		items = addItem(items,"IRON_AXE",0);
		items = addItem(items,"FLINT_AND_STEEL",0);
		items = addItem(items,"WOOD_SPADE",0);
		items = addItem(items,"WOOD_PICKAXE",0);
		items = addItem(items,"WOOD_AXE",0);
		items = addItem(items,"STONE_SPADE",0);
		items = addItem(items,"STONE_PICKAXE",0);
		items = addItem(items,"STONE_AXE",0);
		items = addItem(items,"DIAMOND_SPADE",0);
		items = addItem(items,"DIAMOND_PICKAXE",0);
		items = addItem(items,"DIAMOND_AXE",0);
		items = addItem(items,"GOLD_SPADE",0);
		items = addItem(items,"GOLD_PICKAXE",0);
		items = addItem(items,"GOLD_AXE",0);
		items = addItem(items,"WOOD_HOE",0);
		items = addItem(items,"STONE_HOE",0);
		items = addItem(items,"IRON_HOE",0);
		items = addItem(items,"DIAMOND_HOE",0);
		items = addItem(items,"GOLD_HOE",0);
		items = addItem(items,"COMPASS",0);
		items = addItem(items,"FISHING_ROD",0);
		items = addItem(items,"WATCH",0);
		items = addItem(items,"SHEARS",0);
		items = addItem(items,"LEASH",0);
		items = addItem(items,"NAME_TAG",0);
		return new ItemCategory(items, new ItemCreator(IRON_AXE, (short)0, 1, ChatColor.DARK_AQUA +"Tools"+ ChatColor.RESET).getItemStack());
	}
	
	public static ItemCategory createStaticCombats(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"BOW",0);
		items = addItem(items,"ARROW",0);
		items = addItem(items,"IRON_SWORD",0);
		items = addItem(items,"WOOD_SWORD",0);
		items = addItem(items,"STONE_SWORD",0);
		items = addItem(items,"DIAMOND_SWORD",0);
		items = addItem(items,"GOLD_SWORD",0);
		items = addItem(items,"LEATHER_HELMET",0);
		items = addItem(items,"LEATHER_CHESTPLATE",0);
		items = addItem(items,"LEATHER_LEGGINGS",0);
		items = addItem(items,"LEATHER_BOOTS",0);
		items = addItem(items,"IRON_HELMET",0);
		items = addItem(items,"IRON_CHESTPLATE",0);
		items = addItem(items,"IRON_LEGGINGS",0);
		items = addItem(items,"IRON_BOOTS",0);
		items = addItem(items,"DIAMOND_HELMET",0);
		items = addItem(items,"DIAMOND_CHESTPLATE",0);
		items = addItem(items,"DIAMOND_LEGGINGS",0);
		items = addItem(items,"DIAMOND_BOOTS",0);
		items = addItem(items,"GOLD_HELMET",0);
		items = addItem(items,"GOLD_CHESTPLATE",0);
		items = addItem(items,"GOLD_LEGGINGS",0);
		items = addItem(items,"GOLD_BOOTS",0);
		items = addItem(items,"SPECTRAL_ARROW",0);
		items = addItem(items,"SHIELD",0);
		return new ItemCategory(items, new ItemCreator(GOLD_SWORD, (short)0, 1, ChatColor.BLUE +"Combats"+ ChatColor.RESET).getItemStack());
	}
	
	public static ItemCategory createStaticBrewing(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"GLASS_BOTTLE",0);
		items = addItem(items,"FERMENTED_SPIDER_EYE",0);
		items = addItem(items,"BLAZE_POWDER",0);
		items = addItem(items,"MAGMA_CREAM",0);
		items = addItem(items,"BREWING_STAND",0);
		items = addItem(items,"CAULDRON",0);
		items = addItem(items,"SPECKLED_MELON",0);
		items = addItem(items,"GOLDEN_CARROT",0);
		return new ItemCategory(items, new ItemCreator(POTION, (short)0, 1, ChatColor.LIGHT_PURPLE +"Brewing"+ ChatColor.RESET).getItemStack());
	}
	
	public static ItemCategory createStaticMaterials(){
		ArrayList<ItemStack> items = new ArrayList<>();
		items = addItem(items,"COAL",0);
		items = addItem(items,"DIAMOND",0);
		items = addItem(items,"IRON_INGOT",0);
		items = addItem(items,"GOLD_INGOT",0);
		items = addItem(items,"STICK",0);
		items = addItem(items,"BOWL",0);
		items = addItem(items,"WHEAT",0);
		items = addItem(items,"LEATHER",0);
		items = addItem(items,"CLAY_BRICK",0);
		items = addItem(items,"INK_SACK",1);
		items = addItem(items,"INK_SACK",4);
		items = addItem(items,"INK_SACK",5);
		items = addItem(items,"INK_SACK",6);
		items = addItem(items,"INK_SACK",7);
		items = addItem(items,"INK_SACK",8);
		items = addItem(items,"INK_SACK",9);
		items = addItem(items,"INK_SACK",10);
		items = addItem(items,"INK_SACK",11);
		items = addItem(items,"INK_SACK",12);
		items = addItem(items,"INK_SACK",13);
		items = addItem(items,"INK_SACK",14);
		items = addItem(items,"INK_SACK",15);
		items = addItem(items,"SUGAR",0);
		items = addItem(items,"PUMPKIN_SEEDS",0);
		items = addItem(items,"MELON_SEEDS",0);
		items = addItem(items,"GOLD_NUGGET",0);
		items = addItem(items,"EMERALD",0);
		return new ItemCategory(items, new ItemCreator(STICK, (short)0, 1, ChatColor.GRAY +"Materials"+ ChatColor.RESET).getItemStack());
	}
	
	public static void reloadCategory(){
		BLOCKS = ItemCategory.createStaticBlock();
		DECORATION_BLOCK = ItemCategory.createStaticDecorationBlock();
		REDSTONE_CAT = ItemCategory.createStaticRedstone();
		TRANSPORTATION = ItemCategory.createStaticTransportation();
		MISCELLANEOUS = ItemCategory.createStaticMiscellaneous();
		FOODSTUFFS = ItemCategory.createStaticFoodstuffs();
		TOOLS = ItemCategory.createStaticTools();
		COMBATS = ItemCategory.createStaticCombats();
		BREWING = ItemCategory.createStaticBrewing();
		MATERIALS = ItemCategory.createStaticMaterials();
	}
}
