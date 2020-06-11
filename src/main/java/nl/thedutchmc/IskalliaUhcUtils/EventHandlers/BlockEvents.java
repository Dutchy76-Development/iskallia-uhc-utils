package nl.thedutchmc.IskalliaUhcUtils.EventHandlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import nl.thedutchmc.IskalliaUhcUtils.IskalliaUhcUtils;

public class BlockEvents implements Listener {

	static List<Location> blockLocations = new ArrayList<>();
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		
		if(IskalliaUhcUtils.isDeathmatch) {
			if(!blockLocations.contains(event.getBlock().getLocation())) {
				event.setCancelled(true);
			} else {
				blockLocations.remove(event.getBlock().getLocation());
			}
		}
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		
		if(IskalliaUhcUtils.isDeathmatch) blockLocations.add(event.getBlock().getLocation());
	}
}
