package nl.thedutchmc.IskalliaUhcUtils.EventHandlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.thedutchmc.IskalliaUhcUtils.PlayerObject;
import nl.thedutchmc.IskalliaUhcUtils.IskalliaUhcUtils;

public class PlayerJoinEventListener implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
	
		if(event.getPlayer().isOp()) {
			PlayerObject pO = new PlayerObject();
			pO.setSpectatorChatStatus(false);
			IskalliaUhcUtils.playerObjects.put(event.getPlayer().getUniqueId(), pO);
		}
	}
}
