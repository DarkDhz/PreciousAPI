package dev.darkhorizon.es.psAPI;

import dev.darkhorizon.es.psAPI.commands.kick;
import dev.darkhorizon.es.psAPI.events.PSEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        this.initEvents();
        this.initCommands();
    }

    private void initEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PSEvents(), this);
    }

    private void initCommands() {
        this.getCommand("pkick").setExecutor(new kick());
    }

}
