package net.numalab.looklava.listeners;

import net.numalab.looklava.Config;
import net.numalab.looklava.LookLava;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.RayTraceResult;

public class LookListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!Config.isEnabled) {
            // 無効だったら
            return;
        }

        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Player player = event.getPlayer();
        Team team = sb.getEntryTeam(player.getName());
        if (!LookLava.config.activeTeams.contains(team)) {
            // 設定されたチームだったら
            return;
        }

        RayTraceResult result = player.rayTraceBlocks(LookLava.config.range.value(), FluidCollisionMode.ALWAYS);
        if (result == null) {
            // ブロックが当たらなかった (空中とかみたら)
            return;
        }
        Block block = result.getHitBlock();
        if (block == null) {
            // ブロックがなかった (空中とかみたら)
            return;
        }
        // 見たブロックを溶岩に置き換える
        block.setType(Material.LAVA);
    }
}
