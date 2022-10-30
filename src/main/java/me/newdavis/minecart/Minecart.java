package me.newdavis.minecart;

import me.newdavis.command.MinecartCmd;
import me.newdavis.listener.QuitListener;
import me.newdavis.listener.VehicleLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minecart extends JavaPlugin {

    public static final String PREFIX = "§8┃ §f§lMINECART §8»§7 ";
    private static Minecart instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§aaktiviert");

        new MinecartCmd();
        new VehicleLeaveListener();
        new QuitListener();
    }

    public static Minecart getInstance() {
        return instance;
    }
}
