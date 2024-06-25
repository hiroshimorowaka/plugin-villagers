package me.hiroshi.villagers_plugin.events;

import me.hiroshi.villagers_plugin.VillagerUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;

public class VillagerTransformEvent implements Listener {

    @EventHandler
    public void onZombieBecomesVillager(EntityTransformEvent event) {

        Entity ent = event.getTransformedEntity();

        if(ent instanceof Villager villager && event.getTransformReason() == EntityTransformEvent.TransformReason.CURED) {

            OfflinePlayer[] allPlayers = villager.getServer().getOfflinePlayers();

            VillagerUtils.setPlayersReputationWithVillager(villager, allPlayers);
        }
    }
}
