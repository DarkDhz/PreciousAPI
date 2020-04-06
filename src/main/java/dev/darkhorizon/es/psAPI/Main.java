package dev.darkhorizon.es.psAPI;

import dev.darkhorizon.es.psAPI.events.PSEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        this.initEvents();
    }

    private void initEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PSEvents(), this);
    }

}
