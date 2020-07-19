package nl.thedutchmc.iskalliaUhcUtils.commandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.iskalliaUhcUtils.IskalliaUhcUtils;

public class ReloadConfigCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!sender.hasPermission("iuu.reloadconfig")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
			return true;
		}
		
		IskalliaUhcUtils.configHandler.readConfig();
		sender.sendMessage(ChatColor.GOLD + "Reloading config!");
		
		return true;
	}
	
}
