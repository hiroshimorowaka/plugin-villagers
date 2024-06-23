package me.hiroshi.villagers_plugin;

import me.hiroshi.villagers_plugin.commands.Loc;
import me.hiroshi.villagers_plugin.commands.SetPriceForEveryone;
import me.hiroshi.villagers_plugin.events.chat;
import me.hiroshi.villagers_plugin.events.join;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillagersPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin Started");

        getServer().getPluginManager().registerEvents(new join(), this);
        getServer().getPluginManager().registerEvents(new chat(), this);
        getCommand("loc").setExecutor(new Loc());
        getCommand("teste").setExecutor(new SetPriceForEveryone());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin Stopped");
    }
}
