package nl.thedutchmc.IskalliaUhcUtils.EventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import nl.thedutchmc.IskalliaUhcUtils.IskalliaUhcUtils;

public class ChatEvents implements Listener {

	@EventHandler
	public void onPlayerAsyncChatEvent(AsyncPlayerChatEvent event) {
				
		Player p = event.getPlayer();
		
		if(p.getGameMode().equals(GameMode.SPECTATOR)) {
			if(p.isOp()) {
				if(IskalliaUhcUtils.playerObjects.get(p.getUniqueId()).getSpectatorChatStatus()) {						
					event.setCancelled(true);
					spectatorChatHandler(event.getMessage(), p);
				}
				
			} else {
				event.setCancelled(true);
				spectatorChatHandler(event.getMessage(), p);

			}
		}
	}
	
	void spectatorChatHandler(String message, Player p) {
		for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
			
			if(onlinePlayer.getGameMode().equals(GameMode.SPECTATOR)) onlinePlayer.sendMessage(ChatColor.AQUA + "[Spec] " + ChatColor.WHITE + p.getName() + ": " + message);
		}
	}
}
