package me.hiroshi.villagers_plugin.commands;

import com.destroystokyo.paper.entity.villager.Reputation;
import com.destroystokyo.paper.entity.villager.ReputationType;
import me.hiroshi.villagers_plugin.MessageUtils;
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
        if (CommandUtils.isNotPlayer(sender)) {
            MessageUtils.send(sender, "&cJust players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        List<Entity> entities = player.getNearbyEntities(20, 5, 20);
        Collection<? extends Player> players = sender.getServer().getOnlinePlayers();

        entities.stream()
                .parallel()
                .filter(entity -> entity instanceof Villager)
                .forEach(villagerEntity -> setPlayersReputationWithVillager((Villager) villagerEntity, players));

        return true;
    }

    private static void setPlayersReputationWithVillager(Villager v, Collection<? extends Player> players) {
        players.stream()
                .parallel()
                .map(Player::getUniqueId)
                .forEach(playerId -> {
                    Reputation actual_reputation = v.getReputation(playerId);
                    actual_reputation = actual_reputation != null ? actual_reputation : new Reputation();
                    actual_reputation.setReputation(ReputationType.MAJOR_POSITIVE, 20);
                    actual_reputation.setReputation(ReputationType.MINOR_POSITIVE, 25);
                    v.setReputation(playerId, actual_reputation);
                });
    }
}
