package me.hiroshi.villagers_plugin.commands;

import me.hiroshi.villagers_plugin.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Loc implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lavel, String[] args) {
        if (CommandUtils.isNotPlayer(sender)){
            MessageUtils.send(sender, "&cJust players can use this command!");
            return true;
        }

        if(args.length == 0) {
            MessageUtils.send(sender, "&cYou did not provide any player. Try again!");
            return true;
        }

        String playerName = args[0];
        Player target = Bukkit.getServer().getPlayerExact(playerName);

        if(target == null) {
            MessageUtils.send(sender, "&cThis player is offline!");
            return true;
        }

        Location targetLoc = target.getLocation();

        String coordsMessage = String.format(
                "&c %s &fCoords is: \n&aX: %.2f Y: %.2f Z: %.2f",
                playerName, targetLoc.getX(), targetLoc.getY(), targetLoc.getZ()
        );

        MessageUtils.send(sender, coordsMessage);

        return true;
    }
}
