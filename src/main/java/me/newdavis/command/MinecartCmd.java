package me.newdavis.command;

import me.newdavis.minecart.Minecart;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;

import java.util.HashMap;

public class MinecartCmd implements CommandExecutor {

    public static HashMap<Player, Vehicle> minecart = new HashMap<>();

    public MinecartCmd() {
        Minecart.getInstance().getCommand("minecart").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Minecart.PREFIX + "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
            return true;
        }

        Player p = (Player) sender;

        if(!playerIsOnRail(p)) {
            p.sendMessage(Minecart.PREFIX + "§cDu stehst auf keiner Schiene!");
            return true;
        }

        if(minecart.containsKey(p)) {
            p.sendMessage(Minecart.PREFIX + "§cDu bist bereits in einem Minecart");
            return true;
        }

        createMinecart(p);
        return true;
    }

    public boolean playerIsOnRail(Player p) {
        Material mat = p.getLocation().getBlock().getType();
        return (mat == Material.POWERED_RAIL || mat == Material.RAILS || mat == Material.ACTIVATOR_RAIL || mat == Material.DETECTOR_RAIL);
    }

    public void createMinecart(Player p) {
        Location location = p.getLocation();
        Vehicle vehicle = (Vehicle) location.getWorld().spawnEntity(location, EntityType.MINECART);
        vehicle.setPassenger(p);
        minecart.put(p, vehicle);
        p.sendMessage(Minecart.PREFIX + "§aDu hast dein Minecart erfolgreich erstellt.");
        p.sendMessage(Minecart.PREFIX + "§cUm es zu entfernen, sneaken!");
    }
}
