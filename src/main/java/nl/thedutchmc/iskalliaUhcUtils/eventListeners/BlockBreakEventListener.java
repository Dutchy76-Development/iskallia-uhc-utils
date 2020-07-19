package nl.thedutchmc.iskalliaUhcUtils.eventListeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import nl.thedutchmc.iskalliaUhcUtils.ConfigurationHandler;
import nl.thedutchmc.iskalliaUhcUtils.IskalliaUhcUtils;

public class BlockBreakEventListener implements Listener {

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		
		if(!ConfigurationHandler.arenaSystemEnabled) return;
		
		if(!IskalliaUhcUtils.inDeathmatch) return;
		
		Location l = event.getBlock().getLocation();
		
		if(IskalliaUhcUtils.blocksPlacedByPlayerLocations.contains(l)) return;
		
		event.setCancelled(true);
	}	
}
