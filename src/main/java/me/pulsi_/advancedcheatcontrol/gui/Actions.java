package me.pulsi_.advancedcheatcontrol.gui;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.MethodUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Actions {
    public static void executePlayerCommand(Player admin, Player p, ConfigurationSection items, String actions) {
        for (String commands : items.getStringList(actions)) {
            if (!commands.contains("[PLAYER] ")) continue;
            commands = commands.replace("[PLAYER] ","").replace("%player%", p.getName());
            admin.chat("/" + commands);
        }
    }

    public static void executePlayerCommand(Player admin, OfflinePlayer p, ConfigurationSection items, String actions) {
        for (String commands : items.getStringList(actions)) {
            if (!commands.contains("[PLAYER] ")) continue;
            commands = commands.replace("[PLAYER] ","").replace("%player%", p.getName());
            admin.chat("/" + commands);
        }
    }

    public static void executeConsoleCommand(Player p, ConfigurationSection items, String actions) {
        for (String commands : items.getStringList(actions)) {
            if (!commands.contains("[CONSOLE] ")) continue;
            commands = commands.replace("[CONSOLE] ","").replace("%player%", p.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands);
        }
    }

    public static void executeConsoleCommand(OfflinePlayer p, ConfigurationSection items, String actions) {
        for (String commands : items.getStringList(actions)) {
            if (!commands.contains("[CONSOLE] ")) continue;
            commands = commands.replace("[CONSOLE] ","").replace("%player%", p.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands);
        }
    }

    public static void closeInventory(Player p, ConfigurationSection items, String actions) {
        for (String commands : items.getStringList(actions)) {
            if (!commands.contains("[CLOSE_INVENTORY]")) continue;
            p.closeInventory();
        }
    }

    public static void endSS(Player admin, Player p, ConfigurationSection items, String actions) {
        for (String commands : items.getStringList(actions)) {
            if (!commands.contains("[END]")) continue;
            MethodUtils.endScreenShare(admin, p, JavaPlugin.getPlugin(AdvancedCheatControl.class));
            admin.closeInventory();
        }
    }

    public static void endSS(Player admin, OfflinePlayer p, ConfigurationSection items, String actions) {
        for (String commands : items.getStringList(actions)) {
            if (!commands.contains("[END]")) continue;
            MethodUtils.endScreenShare(admin, p, JavaPlugin.getPlugin(AdvancedCheatControl.class));
            admin.closeInventory();
        }
    }
}