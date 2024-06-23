package me.hiroshi.villagers_plugin.commands;

import com.destroystokyo.paper.entity.villager.Reputation;
import com.destroystokyo.paper.entity.villager.ReputationType;
import me.hiroshi.villagers_plugin.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.Collection;
import java.util.List;

public class SetPriceForEveryone implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String lavel, String[] args) {

        if (!(sender instanceof Player)) {
            Msg.send(sender, "&cJust players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        List<Entity> entities = player.getNearbyEntities(20, 5, 20);

        Collection<Player> players = (Collection<Player>) sender.getServer().getOnlinePlayers();

        for (Entity entity : entities) {

            System.out.println("Entity: " + entity.toString());

            if (entity instanceof Villager v) {

                players.forEach(pl -> {

                    Reputation actual_reputation = v.getReputation(pl.getUniqueId());
                    if (actual_reputation != null) {
                        actual_reputation.setReputation(ReputationType.MAJOR_POSITIVE, 20);
                        actual_reputation.setReputation(ReputationType.MINOR_POSITIVE, 25);
                        v.setReputation(pl.getUniqueId(), actual_reputation);

                    }else{
                        Reputation rep = new Reputation();
                        rep.setReputation(ReputationType.MAJOR_POSITIVE, 20);
                        rep.setReputation(ReputationType.MINOR_POSITIVE, 25);
                        v.setReputation(pl.getUniqueId(), rep);
                    }
                });
            }
        }
        return true;
    }
    };
