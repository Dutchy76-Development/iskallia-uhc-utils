package nl.thedutchmc.IskalliaUhcUtils.CommandExecutors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.thedutchmc.IskalliaUhcUtils.PlayerObject;
import nl.thedutchmc.IskalliaUhcUtils.IskalliaUhcUtils;

public class PluginCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("arena")) {
			if(sender.isOp()) {
				sender.sendMessage(ChatColor.GOLD + "Arena status toggled to " + ChatColor.RED + !IskalliaUhcUtils.isDeathmatch);
				IskalliaUhcUtils.isDeathmatch = !IskalliaUhcUtils.isDeathmatch;
			} else {
				sender.sendMessage(ChatColor.RED + "You need to be an operator to execute this command!");
			}
		}
		
		if(command.getName().equalsIgnoreCase("spec")) {
			
			if(sender.isOp()) {
				
				Player p = (Player) sender;
				PlayerObject pO = IskalliaUhcUtils.playerObjects.get(p.getUniqueId());
				sender.sendMessage(ChatColor.GOLD + "Spectator chat toggled to " + ChatColor.RED + !pO.getSpectatorChatStatus());
				pO.setSpectatorChatStatus(!pO.getSpectatorChatStatus());
				
			} else {
				sender.sendMessage(ChatColor.RED + "You need to be an operator to execute this command!");
			}
			
		}
		
		return true;
	}

}
