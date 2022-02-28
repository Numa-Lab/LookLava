package net.numalab.looklava;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.collection.TeamSetValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Config extends BaseConfig {
    // ON
    public static boolean isEnabled = false;

    // 有効なチーム
    public TeamSetValue activeTeams = new TeamSetValue();

    // 見ている距離
    public DoubleValue range = new DoubleValue(4.0);

    public Config(@NotNull Plugin plugin) {
        super(plugin);
    }
}