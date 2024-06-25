package me.hiroshi.villagers_plugin;

import me.hiroshi.villagers_plugin.commands.Loc;
import me.hiroshi.villagers_plugin.commands.SetPriceForEveryone;
import me.hiroshi.villagers_plugin.events.ChatEvent;
import me.hiroshi.villagers_plugin.events.JoinEvent;
import me.hiroshi.villagers_plugin.events.PlayerInteractHandler;
import me.hiroshi.villagers_plugin.events.VillagerTransformEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillagersPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin Started");

        var pm = super.getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new ChatEvent(), this);
        pm.registerEvents(new VillagerTransformEvent(), this);
        pm.registerEvents(new PlayerInteractHandler(), this);

        super.getCommand("loc").setExecutor(new Loc());
        super.getCommand("reputation").setExecutor(new SetPriceForEveryone());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin Stopped");
    }
}
