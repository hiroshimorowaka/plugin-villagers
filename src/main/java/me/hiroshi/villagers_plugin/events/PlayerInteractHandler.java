package me.hiroshi.villagers_plugin.events;
import me.hiroshi.villagers_plugin.MessageUtils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractHandler implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();

        ItemStack playerItem = player.getInventory().getItemInMainHand();
        boolean isSneaking = player.isSneaking();
        Entity entityClicked = event.getRightClicked();

        if(!(entityClicked instanceof Villager villager))
            return;

        if (playerItem.getType() != Material.WOODEN_HOE)
            return;


        if (!isSneaking)
            return;

        Villager.Profession actualProfession = villager.getProfession();

        if(actualProfession == Villager.Profession.NONE){

            MessageUtils.send(player, "&cThis villager is unemployed!");
            return;

        }

        event.setCancelled(true);
        villager.setProfession(Villager.Profession.NONE);
        villager.setProfession(actualProfession);
        villager.setVillagerLevel(0);
        villager.setVillagerExperience(0);

        villager.shakeHead();
        MessageUtils.send(player, "&aYou successfuly reseted this villager!");
    }

}
