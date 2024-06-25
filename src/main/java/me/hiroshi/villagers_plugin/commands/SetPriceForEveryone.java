package me.hiroshi.villagers_plugin.commands;

import com.destroystokyo.paper.entity.villager.Reputation;
import com.destroystokyo.paper.entity.villager.ReputationType;
import com.mojang.brigadier.Message;
import me.hiroshi.villagers_plugin.MessageUtils;
import me.hiroshi.villagers_plugin.VillagerUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SetPriceForEveryone implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (CommandUtils.isNotPlayer(sender)) {
            MessageUtils.send(sender, "&cJust players can use this command!");
            return true;
        }

        if(args.length < 3) {

            MessageUtils.send(sender, "&cThis command needs 3 arguments!");

            MessageUtils.send(sender, "Exemple: /reputation set 100 10");
            return true;
        }

        String radius = args[1];
        String height = args[2];


        try{

            Integer.parseInt(radius);
            Integer.parseInt(height);

        }catch (NumberFormatException e) {

            MessageUtils.send(sender, "&cThe 2 final arguments needs to be a valid integer!");

            return true;
        }


        Player player = (Player) sender;

        List<Entity> entities = player.getNearbyEntities(Integer.parseInt(radius), Integer.parseInt(height), Integer.parseInt(radius));

        OfflinePlayer[] allPLayers = sender.getServer().getOfflinePlayers();

        entities.stream()
                .parallel()
                .filter(entity -> entity instanceof Villager)
                .forEach(villagerEntity -> VillagerUtils.setPlayersReputationWithVillager((Villager) villagerEntity, allPLayers));

        MessageUtils.send(player, "&aAll villagers in this area have been successfuly seted!");

        return true;
    }



    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args){

        if(args.length == 1) {
            return Arrays.asList("set");
        }


        if(args.length == 2) {
            return Arrays.asList("100");
        }



        if(args.length == 3) {
            return Arrays.asList("5");
        }


        return new ArrayList<>();
    }


}
