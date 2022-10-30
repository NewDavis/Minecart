package me.newdavis.listener;

import me.newdavis.command.MinecartCmd;
import me.newdavis.minecart.Minecart;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    public QuitListener() {
        Bukkit.getPluginManager().registerEvents(this, Minecart.getInstance());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!MinecartCmd.minecart.containsKey(p)) {
            return;
        }

        MinecartCmd.minecart.get(p).setPassenger(null);
        MinecartCmd.minecart.get(p).remove();
        MinecartCmd.minecart.remove(p);
    }

}
