package net.numalab.looklava.listeners;

import net.numalab.looklava.Config;
import net.numalab.looklava.LookLava;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class LookListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!Config.isEnabled) {
            // 無効だったら
            return;
        }

        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SPECTATOR) {
            // スペクテイターだったら
            return;
        }

        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = sb.getEntryTeam(player.getName());
        if (!LookLava.config.activeTeams.contains(team)) {
            // 設定されたチームだったら
            return;
        }

        RayTraceResult result = player.rayTraceBlocks(LookLava.config.maxRrange.value(), FluidCollisionMode.ALWAYS);
        if (result == null) {
            // ブロックが当たらなかった (空中とかみたら)
            return;
        }
        Block block = result.getHitBlock();
        if (block == null) {
            // ブロックがなかった (空中とかみたら)
            return;
        }
        // あたった場所
        Vector position = result.getHitPosition();
        // 距離
        double distance = player.getEyeLocation().toVector().distance(position);
        if (distance < LookLava.config.minRrange.value()) {
            // 距離が近すぎる
            return;
        }
        // 見たブロックを溶岩に置き換える
        block.setType(Material.LAVA);
    }
}
