package nl.thedutchmc.IskalliaUhcUtils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import nl.thedutchmc.IskalliaUhcUtils.CommandExecutors.PluginCommandExecutor;
import nl.thedutchmc.IskalliaUhcUtils.Configuration.ConfigurationHandler;
import nl.thedutchmc.IskalliaUhcUtils.EventHandlers.BlockEvents;
import nl.thedutchmc.IskalliaUhcUtils.EventHandlers.ChatEvents;
import nl.thedutchmc.IskalliaUhcUtils.EventHandlers.PlayerJoinEventListener;
import nl.thedutchmc.IskalliaUhcUtils.Schedulers.MessengerScheduler;

public class IskalliaUhcUtils extends JavaPlugin {

	public static boolean isDeathmatch = false;
	public static HashMap<UUID, PlayerObject> playerObjects = new HashMap<>();
	
	@Override
	public void onEnable() {
		
		System.out.println("[IskalliaUhcUtils] Welcome to IskalliaUhcUtils by Dutchy76 / TheDutchMC");
		
		getServer().getPluginManager().registerEvents(new BlockEvents(), this);
		getServer().getPluginManager().registerEvents(new ChatEvents(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
		
		PluginCommandExecutor plCmdExec = new PluginCommandExecutor();
		getCommand("arena").setExecutor(plCmdExec);
		getCommand("spec").setExecutor(plCmdExec);
		
		ConfigurationHandler cH = new ConfigurationHandler(this);
		cH.loadConfig();
		
		if(ConfigurationHandler.messenger) {
			MessengerScheduler mS = new MessengerScheduler(this);
			mS.schedule();
		}
	}
}
