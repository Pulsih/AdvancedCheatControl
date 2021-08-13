package me.pulsi_.advancedcheatcontrol.events;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.ChatUtils;
import me.pulsi_.advancedcheatcontrol.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChat implements Listener {

    private final AdvancedCheatControl plugin;
    public PlayerChat(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p1 = e.getPlayer();
        if (!MapUtils.PRIVATE_MESSAGES.containsKey(p1.getUniqueId())) return;
        if (!plugin.config().getBoolean("ScreenShare-Options.Chat-Only-With-SS-Players.Enabled")) return;
        e.setCancelled(true);
        String format;
        if (plugin.config().getString("ScreenShare-Options.Chat-Only-With-SS-Players.Format") != null) {
            format = ChatUtils.color(plugin.config().getString("ScreenShare-Options.Chat-Only-With-SS-Players.Format")
                    .replace("%player%", p1.getName()).replace("%message%", e.getMessage()));
        } else {
            format = ChatUtils.color("&8&l(&4&lSS&8&l) &7%player%&8: &7%message%").replace("%player%", p1.getName()).replace("%message%", e.getMessage());
        }
        if (Bukkit.getPlayerExact(MapUtils.PRIVATE_MESSAGES.get(p1.getUniqueId())) != null) {
            Player p2 = Bukkit.getPlayerExact(MapUtils.PRIVATE_MESSAGES.get(p1.getUniqueId()));
            p2.sendMessage(format);
        }
        p1.sendMessage(format);

        if (plugin.config().getString("ScreenShare-Options.Chat-Only-With-SS-Players.Chat-Spy") != null) {
            String spyFormat = ChatUtils.color(plugin.config().getString("ScreenShare-Options.Chat-Only-With-SS-Players.Chat-Spy"))
                    .replace("%player%", p1.getName()).replace("%message%", e.getMessage());
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("advancedcheatcontrol.screenshare.spy"))
                    if (!MapUtils.PRIVATE_MESSAGES.containsKey(p.getUniqueId()))
                        p.sendMessage(spyFormat);
            }
        }
    }
}