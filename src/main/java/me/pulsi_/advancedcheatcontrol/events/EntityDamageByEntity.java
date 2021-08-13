package me.pulsi_.advancedcheatcontrol.events;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.MapUtils;
import me.pulsi_.advancedcheatcontrol.utils.SetUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {

    private final AdvancedCheatControl plugin;
    public EntityDamageByEntity(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (plugin.config().getBoolean("Freeze-Options.Disable-PvP")) {
            if (SetUtils.FREEZE_PLAYER.contains(e.getEntity().getUniqueId())) e.setCancelled(true);
            if (SetUtils.FREEZE_PLAYER.contains(e.getDamager().getUniqueId())) e.setCancelled(true);
        }
        if (plugin.config().getBoolean("ScreenShare-Options.Disable-PvP")) {
            if (SetUtils.SCREEN_SHARING.contains(e.getEntity().getUniqueId())) e.setCancelled(true);
            if (SetUtils.SCREEN_SHARING.contains(e.getDamager().getUniqueId())) e.setCancelled(true);
            if (MapUtils.SCREEN_SHARING_ADMIN.containsKey(e.getEntity().getUniqueId())) e.setCancelled(true);
            if (MapUtils.SCREEN_SHARING_ADMIN.containsKey(e.getDamager().getUniqueId())) e.setCancelled(true);
        }
    }
}
