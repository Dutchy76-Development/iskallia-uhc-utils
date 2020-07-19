package nl.thedutchmc.iskalliaUhcUtils.commandHandlers;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.iskalliaUhcUtils.IskalliaUhcUtils;

public class SpecOverrideCommandHandler implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command may only be used by players!");
			return true;
		}
		
		if(!sender.hasPermission("iuu.chat")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
			return true;
		}
		
		Player player = (Player) sender;
		UUID uuid = player.getUniqueId();
		
		if(IskalliaUhcUtils.playerOverrideSpectatorChat.contains(uuid)) {
			sender.sendMessage(ChatColor.GOLD + "Spectator Chat override toggled to " + ChatColor.RED + "false");
			IskalliaUhcUtils.playerOverrideSpectatorChat.remove(uuid);
		} else {
			sender.sendMessage(ChatColor.GOLD + "Spectator Chat override toggled to " + ChatColor.RED + "true");
			IskalliaUhcUtils.playerOverrideSpectatorChat.add(uuid);
		}
		
		return true;
	}
	
}
