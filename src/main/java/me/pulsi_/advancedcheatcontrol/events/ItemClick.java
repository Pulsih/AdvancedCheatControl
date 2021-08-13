package me.pulsi_.advancedcheatcontrol.events;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.managers.MessagesManager;
import me.pulsi_.advancedcheatcontrol.utils.SetUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemClick implements Listener {

    private AdvancedCheatControl plugin;
    public ItemClick(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getItem() == null) return;
        MessagesManager messMan = new MessagesManager(plugin);
        if (plugin.config().getBoolean("Freeze-Options.Disable-Enderpearls") && !plugin.config().getBoolean("ScreenShare-Options.Disable-Enderpearls")) {
            if (SetUtils.FREEZE_PLAYER.contains(e.getPlayer().getUniqueId()))
                if (e.getItem().getType() == Material.ENDER_PEARL) {
                    messMan.cannotUseEnderPearl(e.getPlayer());
                    e.setCancelled(true);
                }
        }
        if (plugin.config().getBoolean("ScreenShare-Options.Disable-Enderpearls") && !plugin.config().getBoolean("Freeze-Options.Disable-Enderpearls")) {
            if (SetUtils.SCREEN_SHARING.contains(e.getPlayer().getUniqueId()))
                if (e.getItem().getType() == Material.ENDER_PEARL) {
                    messMan.cannotUseEnderPearl(e.getPlayer());
                    e.setCancelled(true);
                }
        }

        if (plugin.config().getBoolean("ScreenShare-Options.Disable-Enderpearls") && plugin.config().getBoolean("Freeze-Options.Disable-Enderpearls")) {
            if (SetUtils.SCREEN_SHARING.contains(e.getPlayer().getUniqueId()) || SetUtils.FREEZE_PLAYER.contains(e.getPlayer().getUniqueId()))
                if (e.getItem().getType() == Material.ENDER_PEARL) {
                    messMan.cannotUseEnderPearl(e.getPlayer());
                    e.setCancelled(true);
                }
        }
    }
}