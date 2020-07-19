package nl.thedutchmc.iskalliaUhcUtils.eventListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import nl.thedutchmc.iskalliaUhcUtils.IskalliaUhcUtils;
import nl.thedutchmc.uhcplus.events.GameStateChangedEvent;
import nl.thedutchmc.uhcplus.uhc.GameState;

public class GameStateChangedEventListener implements Listener {
	
	@EventHandler
	public void onGameStateChangedEvent(GameStateChangedEvent event) {
		
		if(event.getNewGameState().equals(GameState.DEATHMATCH)) {
			IskalliaUhcUtils.inDeathmatch = true;
		}
	}

}
