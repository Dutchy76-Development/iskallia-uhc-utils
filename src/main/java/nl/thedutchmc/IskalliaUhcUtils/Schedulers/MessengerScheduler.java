package nl.thedutchmc.IskalliaUhcUtils.Schedulers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import nl.thedutchmc.IskalliaUhcUtils.IskalliaUhcUtils;
import nl.thedutchmc.IskalliaUhcUtils.Configuration.ConfigurationHandler;

public class MessengerScheduler {

	private IskalliaUhcUtils plugin;
	
	private static int messengerIndex;
	
	public MessengerScheduler(IskalliaUhcUtils plugin) {
		this.plugin = plugin;
	}
	
	public void schedule() {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				if(Bukkit.getOnlinePlayers().isEmpty()) return;
				
				if(messengerIndex + 1 > ConfigurationHandler.messages.size()) messengerIndex = 0;
				
				/* Formatting for operations
				 * $1 = hover
				 * $2 = click
				 */
				
				String msg = ConfigurationHandler.messages.get(messengerIndex);
				char[] msgChars = msg.toCharArray();
				
				String tellrawComponent = "";
				
				List<String> actionCharsStr = new ArrayList<>(); 
				
				int startOp = 0;
				int endOp = 0;
				
				for(int i = 0; i < msgChars.length; i++) {
					char c = msgChars[i];
					
					if(c == '{') {
		
						for(int j = i+1; j < msgChars.length; j++) {
							if(msgChars[j] != '}') {
								actionCharsStr.add(String.valueOf(msgChars[j]));
								endOp = j;
							} else {
								break;
							}
						}
					}
				}
				
				StringBuilder sb = new StringBuilder();
				for(String str : actionCharsStr) {
					sb.append(str);
				}
				
				for(int i = 0; i < msgChars.length; i++) {
					char c = msgChars[i];
					
					if(c ==  '$') {
						
						startOp = i;
						
						switch(msgChars[i+1]) {
						
						case '1':
							tellrawComponent = ",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"" + sb.toString() + "\"}";
							break;
						case '2':
							tellrawComponent = ",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + sb.toString() + "\"}";
							break;
						}
					}
				}
				
				String color = "";
				for(int i = 0; i < msgChars.length; i++) {
					if(msgChars[i] == '&') {
						char colorCode = msgChars[i+1];
												
						switch(colorCode) {
						
						case '0':
							color = "black";
							break;
						case '1':
							color = "dark_blue";
							break;
						case '2':
							color = "dark_green";
							break;
						case '3':
							color = "dark_aqua";
							break;
						case '4':
							color = "dark_red";
							break;
						case '5':
							color = "dark_purple";
							break;
						case '6':
							color = "gold";
							break;
						case '7':
							color = "gray";
							break;
						case '8':
							color = "dark_gray";
							break;
						case '9':
							color = "blue";
							break;
						case 'a':
							color = "green";
							break;
						case 'b':
							color = "aqua";
							break;
						case 'c':
							color = "red";
							break;
						case 'd':
							color = "pink";
							break;
						case 'e':
							color = "yellow";
							break;
						case 'f':
							color = "white";
							break;
						}
						msgChars[i] = '~';
						msgChars[i+1] = '~';
						break;
					}
				}
				
				String fMsg = "";
				for(int i = 0; i < msgChars.length; i++) {
					if(i >= startOp && i <= endOp + 1) {
						msgChars[i] = '~';
						
					} else {
						fMsg += msgChars[i];
					}
				}
				
				fMsg = fMsg.replace("~~", "");
				
				color =  ",\"color\":\"" + color + "\"";
				
				String finalTellraw = "{\"text\":\"" + fMsg + "\"" + color + tellrawComponent + "}";
				String fullCommand = "tellraw @a " + finalTellraw;
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), fullCommand);
				
				messengerIndex++;
			}
		}.runTaskTimer(plugin, 0, ConfigurationHandler.messengerInterval * 20);
	}
}
