package net.numalab.looklava.listeners;

import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.RayTraceResult;

public class LookListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SPECTATOR) {
            // スペクテイターだったら
            return;
        }

        RayTraceResult result = player.rayTraceBlocks(4, FluidCollisionMode.ALWAYS);
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
