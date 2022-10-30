package me.newdavis.listener;

import me.newdavis.command.MinecartCmd;
import me.newdavis.minecart.Minecart;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class VehicleLeaveListener implements Listener {

    public VehicleLeaveListener() {
        Bukkit.getPluginManager().registerEvents(this, Minecart.getInstance());
    }

    @EventHandler
    public void onVehicleLeave(VehicleExitEvent e) {
        if (!(e.getExited() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getExited();
        if (!MinecartCmd.minecart.containsKey(p)) {
            return;
        }

        e.getVehicle().remove();
        MinecartCmd.minecart.remove(p);
        p.sendMessage(Minecart.PREFIX + "§cMinecart wurde entfernt!");
    }

}
