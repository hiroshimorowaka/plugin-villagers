package me.hiroshi.villagers_plugin.commands;

import me.hiroshi.villagers_plugin.Msg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class Loc implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lavel, String[] args) {

        if (!(sender instanceof Player)){
            Msg.send(sender, "&cJust players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            Msg.send(sender, "&cYou did not provide any player. Try again!");
            return true;
        }

        String playerName = args[0];

        Player target = Bukkit.getServer().getPlayerExact(playerName);

        if(target == null) {
            Msg.send(sender, "&cThis player is offline!");
            return true;
        }

        Location target_loc = target.getLocation();

        Msg.send(sender, "&c"+ playerName + " &fCoords is:\n&aX: " + new DecimalFormat("#.##").format(target_loc.getX()) + " Y: " + new DecimalFormat("#.##").format(target_loc.getY()) + " Z: " + new DecimalFormat("#.##").format(target_loc.getZ()));

        return true;
    };


}
