package nl.thedutchmc.iskalliaUhcUtils.eventListeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import nl.thedutchmc.iskalliaUhcUtils.ConfigurationHandler;
import nl.thedutchmc.iskalliaUhcUtils.IskalliaUhcUtils;

public class BlockPlaceEventListener implements Listener {

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		
		if(!ConfigurationHandler.arenaSystemEnabled);
		
		if(!IskalliaUhcUtils.inDeathmatch) return;
		
		Location l = event.getBlock().getLocation();
		
		if(!IskalliaUhcUtils.blocksPlacedByPlayerLocations.contains(l)) IskalliaUhcUtils.blocksPlacedByPlayerLocations.add(l);

	}
	
}
