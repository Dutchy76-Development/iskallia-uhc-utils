package nl.thedutchmc.iskalliaUhcUtils.eventListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.val59000mc.events.UhcGameStateChangedEvent;
import com.gmail.val59000mc.game.GameState;

import nl.thedutchmc.iskalliaUhcUtils.IskalliaUhcUtils;

public class UhcGameStateChangedEventListener implements Listener {

	@EventHandler
	public void onGameStateChangedEventListener(UhcGameStateChangedEvent event) {
		
		if(event.getNewGameState().equals(GameState.DEATHMATCH)) {
			IskalliaUhcUtils.inDeathmatch = true;
		}	
	}
}