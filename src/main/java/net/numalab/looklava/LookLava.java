package net.numalab.looklava;

import dev.kotx.flylib.FlyLib;
import net.kunmc.lab.configlib.ConfigCommand;
import net.kunmc.lab.configlib.ConfigCommandBuilder;
import net.numalab.looklava.command.MainCommand;
import net.numalab.looklava.listeners.LookListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class LookLava extends JavaPlugin {
    public static Logger LOGGER;
    public static LookLava instance;
    public static Config config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        LOGGER = getLogger();

        config = new Config(this);
        config.saveConfigIfAbsent();
        config.loadConfig();

        ConfigCommand configCommand = new ConfigCommandBuilder(config).build();


        FlyLib.create(this, builder -> {
            builder.command(new MainCommand("looklava", configCommand));
        });

        getServer().getPluginManager().registerEvents(new LookListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
