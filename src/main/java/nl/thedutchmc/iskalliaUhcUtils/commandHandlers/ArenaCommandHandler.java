package nl.thedutchmc.iskalliaUhcUtils.commandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.iskalliaUhcUtils.IskalliaUhcUtils;

public class ArenaCommandHandler implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!sender.hasPermission("iuu.arena")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			return true;
		}
		
		IskalliaUhcUtils.inDeathmatch = !IskalliaUhcUtils.inDeathmatch;
		sender.sendMessage(ChatColor.GOLD + "Deathmatch status toggled to " + IskalliaUhcUtils.inDeathmatch);
		
		return true;
	}
}