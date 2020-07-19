package nl.thedutchmc.iskalliaUhcUtils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationHandler {

	public static boolean spectatorChatEnabled, arenaSystemEnabled;
	
	private File file;
	private FileConfiguration config;
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void loadConfig() {
		file = new File(IskalliaUhcUtils.INSTANCE.getDataFolder(), "config.yml");
		
		if (!file.exists()) {
			
			file.getParentFile().mkdirs();

			IskalliaUhcUtils.INSTANCE.saveResource("config.yml", false);
		}

		config = new YamlConfiguration();

		try {
			config.load(file);
			readConfig();

		} catch (IOException e) {
			e.printStackTrace();
		} catch(InvalidConfigurationException e) {
			System.err.println("[IskalliaUhcUtils] Invalid config.yml. Disabling.");
			Bukkit.getPluginManager().disablePlugin(IskalliaUhcUtils.INSTANCE);
		}
	}
	
	public void readConfig() {
		spectatorChatEnabled = Boolean.valueOf(this.getConfig().getString("spectatorChatEnabled"));
		arenaSystemEnabled = Boolean.valueOf(this.getConfig().getString("arenaSystemEnabled"));
	}
	
}
