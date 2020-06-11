package nl.thedutchmc.IskalliaUhcUtils.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.thedutchmc.IskalliaUhcUtils.IskalliaUhcUtils;

public class ConfigurationHandler {

	public static boolean messenger;
	public static int messengerInterval;
	public static List<String> messages = new ArrayList<String>();
	
	private IskalliaUhcUtils plugin;
	
	public ConfigurationHandler(IskalliaUhcUtils plugin) {
		this.plugin = plugin;
	}
	
	private File configFile;
	private FileConfiguration config;
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void loadConfig() {
		configFile = new File(plugin.getDataFolder(), "config.yml");
		
		if(!configFile.exists()) {
			configFile.getParentFile().mkdirs();

			plugin.saveResource("config.yml", false);
		}
		
		config = new YamlConfiguration();
		
		try {
			config.load(configFile);
			readConfig();
		} catch(IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readConfig() {
		messenger = this.getConfig().getBoolean("messenger");
		messengerInterval = this.getConfig().getInt("messengerInterval");
		messages = (List<String>) this.getConfig().getList("messages");
	}
}
