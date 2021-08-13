package me.pulsi_.advancedcheatcontrol.commands;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.managers.MessagesManager;
import me.pulsi_.advancedcheatcontrol.utils.ChatUtils;
import me.pulsi_.advancedcheatcontrol.utils.MethodUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private AdvancedCheatControl plugin;
    public MainCommand(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

        MessagesManager messMan = new MessagesManager(plugin);

        if (args.length == 0) {
            s.sendMessage("");
            s.sendMessage(ChatUtils.color("&7Plugin &8[&a&lAdvanced&c&lCheat&d&lControl&8] " + plugin.getDescription().getVersion()));
            s.sendMessage(ChatUtils.color("&7Type &a/cc help &7for help! Made by &aPulsi_"));
            s.sendMessage("");
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "reload":
                    if (!s.hasPermission("advancedcheatcontrol.reload")) {
                        messMan.noPermission(s);
                        return false;
                    }
                    plugin.reloadConfigs();
                    messMan.reloadCommand(s);
                    break;

                case "setwarp":
                    if (!s.hasPermission("advancedcheatcontrol.setwarp")) {
                        messMan.noPermission(s);
                        return false;
                    }
                    if (!(s instanceof Player)) {
                        messMan.notPlayer(s);
                        return false;
                    }
                    messMan.setWarpUsage(s);
                    break;

                case "setexit":
                    if (!s.hasPermission("advancedcheatcontrol.setexit")) {
                        messMan.noPermission(s);
                        return false;
                    }
                    if (!(s instanceof Player)) {
                        messMan.notPlayer(s);
                        return false;
                    }
                    messMan.setExitUsage(s);
                    break;

                case "help":
                    if (!s.hasPermission("advancedcheatcontrol.help")) {
                        messMan.noPermission(s);
                        return false;
                    }
                    if (!(s instanceof Player)) {
                        messMan.notPlayer(s);
                        return false;
                    }
                    messMan.helpMessage(s);
                    break;

                default:
                    messMan.unknownCommand(s);
                    break;
            }
        }

        if (args.length == 2) {
            switch (args[0]) {
                case "setwarp":
                    if (!s.hasPermission("advancedcheatcontrol.setwarp")) {
                        messMan.noPermission(s);
                        return false;
                    }
                    if (!(s instanceof Player)) {
                        messMan.notPlayer(s);
                        return false;
                    }
                    if (args[1].equalsIgnoreCase("player")) {
                        String playerLocation = MethodUtils.getWorldLocationPitch(((Player) s).getLocation().toString());
                        plugin.storage().set("Player-Warp", playerLocation);
                        plugin.saveStorage();
                        messMan.setPlayerWarp(s);
                    } else if (args[1].equalsIgnoreCase("admin")) {
                        String adminLocation = MethodUtils.getWorldLocationPitch(((Player) s).getLocation().toString());
                        plugin.storage().set("Admin-Warp", adminLocation);
                        plugin.saveStorage();
                        messMan.setAdminWarp(s);
                    } else {
                        messMan.setWarpUsage(s);
                    }
                    break;

                case "setexit":
                    if (!s.hasPermission("advancedcheatcontrol.setexit")) {
                        messMan.noPermission(s);
                        return false;
                    }
                    if (!(s instanceof Player)) {
                        messMan.notPlayer(s);
                        return false;
                    }
                    if (args[1].equalsIgnoreCase("player")) {
                        String playerLocation = MethodUtils.getWorldLocationPitch(((Player) s).getLocation().toString());
                        plugin.storage().set("Player-Exit", playerLocation);
                        plugin.saveStorage();
                        messMan.setPlayerExit(s);
                    } else if (args[1].equalsIgnoreCase("admin")) {
                        String adminLocation = MethodUtils.getWorldLocationPitch(((Player) s).getLocation().toString());
                        plugin.storage().set("Admin-Exit", adminLocation);
                        plugin.saveStorage();
                        messMan.setAdminExit(s);
                    } else {
                        messMan.setWarpUsage(s);
                    }
                    break;

                default:
                    messMan.unknownCommand(s);
                    break;
            }
        }
        return true;
    }
}