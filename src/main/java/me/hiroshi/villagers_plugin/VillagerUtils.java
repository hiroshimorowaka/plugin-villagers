package me.hiroshi.villagers_plugin;

import com.destroystokyo.paper.entity.villager.Reputation;
import com.destroystokyo.paper.entity.villager.ReputationType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Villager;

import java.util.Arrays;

public abstract class VillagerUtils {
    public static void setPlayersReputationWithVillager(Villager v, OfflinePlayer[] players) {
       Arrays.stream(players)
                .map(OfflinePlayer::getUniqueId)
                .forEach(playerId -> {
                    Reputation reputation = v.getReputation(playerId);
                    reputation = reputation != null ? reputation : new Reputation();
                    reputation.setReputation(ReputationType.MAJOR_POSITIVE, 20);
                    reputation.setReputation(ReputationType.MINOR_POSITIVE, 25);
                    v.setReputation(playerId, reputation);
                    System.out.println("Reputation to playerID " + playerId + " Has been seted" );
                });
    }
}
