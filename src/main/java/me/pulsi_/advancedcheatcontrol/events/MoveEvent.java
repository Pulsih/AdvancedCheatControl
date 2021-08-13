package me.pulsi_.advancedcheatcontrol.events;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.SetUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    private AdvancedCheatControl plugin;
    public MoveEvent(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (SetUtils.FREEZE_PLAYER.contains(p.getUniqueId()))
            p.teleport(e.getFrom());
    }
}