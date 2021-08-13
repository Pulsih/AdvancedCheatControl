package me.pulsi_.advancedcheatcontrol.utils;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MethodUtils {

    public static void sendTitle(String path, Player p) {
        try {
            String[] titles = path.split(",");
            String title = titles[0];
            String subTitle = titles[1];
            p.sendTitle(ChatUtils.color(title), ChatUtils.color(subTitle));
        } catch (NullPointerException | IllegalArgumentException e) {}
    }

    public static void playSound(String path, Player p) {
        try {
            String[] sound = path.split(" ");
            Sound soundType = Sound.valueOf(sound[0]);
            int volume = Integer.parseInt(sound[1]);
            int pitch = Integer.parseInt(sound[2]);
            p.playSound(p.getLocation(), soundType, volume, pitch);
        } catch (NullPointerException | IllegalArgumentException e) {}
    }

    public static String getWorldLocationPitch(String message) {
        return message.replace("Location{world=CraftWorld{name=", "").replace("},x=", " ")
                .replace(",y=", " ").replace(",z=", " ").replace(",pitch=", " ")
                .replace(",yaw=", " ").replace("}", "");
    }

    public static Location getLocationFromCustomString(String path) {
        String[] s = path.split(" ");
        Location loc = new Location(Bukkit.getWorld(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]), Double.parseDouble(s[3]), Float.parseFloat(s[5]), Float.parseFloat(s[4]));
        return loc;
    }

    public static void endScreenShare(Player admin, Player p, AdvancedCheatControl plugin) {
        if (SetUtils.SCREEN_SHARING.contains(p.getUniqueId())) SetUtils.SCREEN_SHARING.remove(p.getUniqueId());
        if (MapUtils.SCREEN_SHARING_ADMIN.containsKey(admin.getUniqueId())) MapUtils.SCREEN_SHARING_ADMIN.remove(admin.getUniqueId());
        if (SetUtils.FREEZE_PLAYER.contains(p.getUniqueId())) SetUtils.FREEZE_PLAYER.remove(p.getUniqueId());
        if (MapUtils.PRIVATE_MESSAGES.containsKey(p.getUniqueId())) MapUtils.PRIVATE_MESSAGES.remove(p.getUniqueId());
        if (MapUtils.PRIVATE_MESSAGES.containsKey(admin.getUniqueId())) MapUtils.PRIVATE_MESSAGES.remove(admin.getUniqueId());
        new MessagesManager(plugin).ssEnded(admin);
        new MessagesManager(plugin).ssEnded(p);
        if (plugin.config().getBoolean("ScreenShare-Options.End-Title.Enabled")) {
            String title = plugin.config().getString("ScreenShare-Options.End-Title.Text");
            MethodUtils.sendTitle(title, p);
        }
        if (plugin.config().getBoolean("ScreenShare-Options.Sound-On-End.Enabled")) {
            String sound = plugin.config().getString("ScreenShare-Options.Sound-On-End.Sound");
            MethodUtils.playSound(sound, admin);
            MethodUtils.playSound(sound, p);
        }
        try {
            if (plugin.storage().getString("Player-Exit") != null) p.teleport(MethodUtils.getLocationFromCustomString(plugin.storage().getString("Player-Exit")));
            if (plugin.storage().getString("Admin-Exit") != null) admin.teleport(MethodUtils.getLocationFromCustomString(plugin.storage().getString("Admin-Exit")));
        } catch (NumberFormatException e) {
            new MessagesManager(plugin).internalError(admin);
            ChatUtils.consoleMessage("");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: &f" + e.getMessage() + "&c. &csituated in &astorage.yml&c.");
            ChatUtils.consoleMessage("");
        } catch (ArrayIndexOutOfBoundsException e) {
            new MessagesManager(plugin).internalError(admin);
            ChatUtils.consoleMessage("");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: Expecting &f6 &cstrings but found &f" + e.getMessage() + "&c. &csituated in &astorage.yml&c.");
            ChatUtils.consoleMessage("");
        } catch (NullPointerException e) {
            new MessagesManager(plugin).internalError(admin);
            ChatUtils.consoleMessage("");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: &fUnknown world&c. situated in &astorage.yml&c.");
            ChatUtils.consoleMessage("");
        }
    }

    public static void endScreenShare(Player admin, OfflinePlayer p, AdvancedCheatControl plugin) {
        if (SetUtils.SCREEN_SHARING.contains(p.getUniqueId())) SetUtils.SCREEN_SHARING.remove(p.getUniqueId());
        if (MapUtils.SCREEN_SHARING_ADMIN.containsKey(admin.getUniqueId())) MapUtils.SCREEN_SHARING_ADMIN.remove(admin.getUniqueId());
        if (SetUtils.FREEZE_PLAYER.contains(p.getUniqueId())) SetUtils.FREEZE_PLAYER.remove(p.getUniqueId());
        if (MapUtils.PRIVATE_MESSAGES.containsKey(p.getUniqueId())) MapUtils.PRIVATE_MESSAGES.remove(p.getUniqueId());
        if (MapUtils.PRIVATE_MESSAGES.containsKey(admin.getUniqueId())) MapUtils.PRIVATE_MESSAGES.remove(admin.getUniqueId());
        new MessagesManager(plugin).ssEnded(admin);
        try {
            if (plugin.storage().getString("Admin-Exit") != null) admin.teleport(MethodUtils.getLocationFromCustomString(plugin.storage().getString("Admin-Exit")));
        } catch (NumberFormatException e) {
            new MessagesManager(plugin).internalError(admin);
            ChatUtils.consoleMessage("");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: &f" + e.getMessage() + "&c. &csituated in &astorage.yml&c.");
            ChatUtils.consoleMessage("");
        } catch (ArrayIndexOutOfBoundsException e) {
            new MessagesManager(plugin).internalError(admin);
            ChatUtils.consoleMessage("");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: Expecting &f6 &cstrings but found &f" + e.getMessage() + "&c. &csituated in &astorage.yml&c.");
            ChatUtils.consoleMessage("");
        } catch (NullPointerException e) {
            new MessagesManager(plugin).internalError(admin);
            ChatUtils.consoleMessage("");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
            ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: &fUnknown world&c. situated in &astorage.yml&c.");
            ChatUtils.consoleMessage("");
        }
    }
}