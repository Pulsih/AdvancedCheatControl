package me.pulsi_.advancedcheatcontrol.others;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.ChatUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker implements Listener {

    private final boolean isUpToDate;
    private final AdvancedCheatControl plugin;
    public UpdateChecker(AdvancedCheatControl plugin) {
        boolean isUpdated;
        this.plugin = plugin;
        try {
            isUpdated = isPluginUpdated();
        } catch (IOException e) {
            isUpdated = true;
        }
        this.isUpToDate = isUpdated;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!plugin.config().getBoolean("Update-Checker")) return;
        if (!e.getPlayer().isOp() || !e.getPlayer().hasPermission("advancedcheatcontrol.notify")) return;
        if (isUpToDate) return;
        final TextComponent update = new TextComponent(ChatUtils.color("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &aNew update available! &7(CLICK HERE)"));
        update.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/%E2%AD%90advancedcheatcontrol%E2%AD%90-professional-hack-control-plugin-1-7-1-17.95301/"));
        update.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click here to download it!").color(ChatColor.GRAY).create()));
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            e.getPlayer().sendMessage("");
            e.getPlayer().spigot().sendMessage(update);
            e.getPlayer().sendMessage("");
        }, 80);
    }

    private boolean isPluginUpdated() throws IOException {
        final String currentVersion = new BufferedReader(new InputStreamReader(new URL("https://api.spigotmc.org/legacy/update.php?resource=95301").openConnection().getInputStream())).readLine();
        return plugin.getDescription().getVersion().equals(currentVersion);
    }
}