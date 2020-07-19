package nl.thedutchmc.iskalliaUhcUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import nl.thedutchmc.iskalliaUhcUtils.commandHandlers.ArenaCommandHandler;
import nl.thedutchmc.iskalliaUhcUtils.commandHandlers.ReloadConfigCommandExecutor;
import nl.thedutchmc.iskalliaUhcUtils.commandHandlers.SpecOverrideCommandHandler;
import nl.thedutchmc.iskalliaUhcUtils.eventListeners.BlockBreakEventListener;
import nl.thedutchmc.iskalliaUhcUtils.eventListeners.BlockPlaceEventListener;
import nl.thedutchmc.iskalliaUhcUtils.eventListeners.GameStateChangedEventListener;
import nl.thedutchmc.iskalliaUhcUtils.eventListeners.PlayerChatEventListener;
import nl.thedutchmc.iskalliaUhcUtils.eventListeners.UhcGameStateChangedEventListener;

public class IskalliaUhcUtils extends JavaPlugin {

	public static boolean UhcPlusPresent = false;
	public static boolean UhcCorePresent = false;
	
	public static List<UUID> playerOverrideSpectatorChat = new ArrayList<>();
	public static List<Location> blocksPlacedByPlayerLocations = new ArrayList<>();
	
	public static boolean inDeathmatch = false;
	
	public static IskalliaUhcUtils INSTANCE;
	public static ConfigurationHandler configHandler;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		if(Bukkit.getPluginManager().isPluginEnabled("UhcPlus")) UhcPlusPresent = true;
		if(Bukkit.getPluginManager().isPluginEnabled("UhcCore")) UhcCorePresent = true;
		
		configHandler = new ConfigurationHandler();
		configHandler.loadConfig();
		
		Bukkit.getPluginManager().registerEvents(new PlayerChatEventListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreakEventListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockPlaceEventListener(), this);

		getCommand("specoverride").setExecutor(new SpecOverrideCommandHandler());
		getCommand("arena").setExecutor(new ArenaCommandHandler());
		getCommand("reloadconfig").setExecutor(new ReloadConfigCommandExecutor());
		
		if(UhcPlusPresent) {
			Bukkit.getPluginManager().registerEvents(new GameStateChangedEventListener(), this);
		}
		
		if(UhcCorePresent) {
			Bukkit.getPluginManager().registerEvents(new UhcGameStateChangedEventListener(), this);
		}
		
		
		
	}
	
}
