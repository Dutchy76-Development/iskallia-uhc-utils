 package nl.thedutchmc.iskalliaUhcUtils.eventListeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.iskalliaUhcUtils.ConfigurationHandler;
import nl.thedutchmc.iskalliaUhcUtils.IskalliaUhcUtils;

public class PlayerChatEventListener implements Listener {

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		
		if(!ConfigurationHandler.spectatorChatEnabled) return;
		
		if(!event.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) return;
		
		if(IskalliaUhcUtils.playerOverrideSpectatorChat.contains(event.getPlayer().getUniqueId())) return;
		
		event.setCancelled(true);
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getGameMode().equals(GameMode.SPECTATOR)) {
				p.sendMessage(ChatColor.GRAY + "[Spectator] " + ChatColor.RESET + event.getMessage());
			}
		}
	}
}
