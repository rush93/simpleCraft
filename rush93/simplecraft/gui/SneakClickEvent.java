package rush93.simplecraft.gui;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import rush93.simplecraft.SimpleCraft;
import rush93.simplecraft.Utils;

public class SneakClickEvent  implements  Listener{
	

	public SneakClickEvent(SimpleCraft plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onSneakClick(PlayerInteractEvent e){
		if (Utils.EnableSneakClick) {
			Player p = e.getPlayer();
			if (Utils.hasSneakClickPermission(p) && p.getTargetBlock((Set<Material>)null, 100).getType().equals(Utils.SneakClickItem) && p.isSneaking()) {
				e.setCancelled(true);
				SimpleCraft.openItemListInventory(p);
			}
		}
	}
}
