package me.hiroshi.villagers_plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtils {
    public static void send(CommandSender sender, String message) {
        send(sender, message, "&a");
    }

    public static void send(Player player, String message) {
        send(player, message, "&a");
    }


    public static void send(CommandSender sender, String message, String prefix) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }





}
