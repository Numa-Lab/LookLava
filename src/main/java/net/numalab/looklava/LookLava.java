package net.numalab.looklava;

import net.numalab.looklava.listeners.LookListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class LookLava extends JavaPlugin {
    public static Logger LOGGER;
    public static LookLava instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        LOGGER = getLogger();

        getServer().getPluginManager().registerEvents(new LookListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
