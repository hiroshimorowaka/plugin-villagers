package me.hiroshi.villagers_plugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {
    public static boolean isNotPlayer(CommandSender sender) {
        return !(sender instanceof Player);
    }
}
