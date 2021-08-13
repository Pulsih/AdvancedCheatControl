package me.pulsi_.advancedcheatcontrol.events;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.managers.MessagesManager;
import me.pulsi_.advancedcheatcontrol.utils.SetUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private AdvancedCheatControl plugin;
    public PlayerQuit(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!SetUtils.SCREEN_SHARING.contains(p.getUniqueId())) return;
        if (plugin.config().getBoolean("ScreenShare-Options.Quit-Punishment.Broadcast-Message.Enabled")) new MessagesManager(plugin).broadcastQuitInSS(p);
        if (plugin.config().getBoolean("ScreenShare-Options.Quit-Punishment.Commands.Enabled"))
            for (String commands : plugin.config().getStringList("ScreenShare-Options.Quit-Punishment.Commands.Commands-To-Execute"))
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%player%", p.getName()));
    }
}