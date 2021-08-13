package me.pulsi_.advancedcheatcontrol.events;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.SetUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class OnCommand implements Listener {

    private AdvancedCheatControl plugin;
    public OnCommand(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        List<String> freezeCommandsWhitelist = plugin.config().getStringList("Freeze-Options.Disable-Commands.Commands-Whitelist");
        if (plugin.config().getBoolean("Freeze-Options.Disable-Commands.Enabled")) {
            if (SetUtils.FREEZE_PLAYER.contains(e.getPlayer().getUniqueId())) {
                if (!freezeCommandsWhitelist.contains(e.getMessage().toLowerCase()))
                    e.setCancelled(true);
            }
        }

        List<String> ssCommandsWhitelist = plugin.config().getStringList("ScreenShare-Options.Disable-Commands.Commands-Whitelist");
        if (plugin.config().getBoolean("ScreenShare-Options.Disable-Commands.Enabled")) {
            if (SetUtils.SCREEN_SHARING.contains(e.getPlayer().getUniqueId())) {
                if (!ssCommandsWhitelist.contains(e.getMessage().toLowerCase()))
                    e.setCancelled(true);
            }
        }
    }
}