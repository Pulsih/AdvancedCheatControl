package me.pulsi_.advancedcheatcontrol.gui;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.ChatUtils;
import me.pulsi_.advancedcheatcontrol.utils.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfirmGUIListener implements Listener {

    private AdvancedCheatControl plugin;
    public ConfirmGUIListener(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void guiListener(InventoryClickEvent e) {

        Player admin = (Player) e.getWhoClicked();
        String title = plugin.gui().getString("Title");
        if (e.getCurrentItem() == null || !e.getView().getTitle().equals(ChatUtils.color(title))) return;
        e.setCancelled(true);

        for (String key : plugin.gui().getConfigurationSection("Items").getKeys(false)) {
            ConfigurationSection items = plugin.gui().getConfigurationSection("Items." + key);
            if (e.getSlot() + 1 != items.getInt("Slot") || items.getString("Actions") == null) continue;

            if (Bukkit.getPlayerExact(MapUtils.SCREEN_SHARING_ADMIN.get(admin.getUniqueId())) == null) {
                OfflinePlayer p = Bukkit.getOfflinePlayer(MapUtils.SCREEN_SHARING_ADMIN.get(admin.getUniqueId()));
                executeActions(admin, p, items, "Actions");
                return;
            }
            Player p = Bukkit.getPlayerExact(MapUtils.SCREEN_SHARING_ADMIN.get(admin.getUniqueId()));
            executeActions(admin, p, items, "Actions");
        }
    }

    public void executeActions(Player admin, Player p, ConfigurationSection items, String action) {
        Actions.executeConsoleCommand(p, items, action);
        Actions.executePlayerCommand(admin, p, items, action);
        Actions.closeInventory(admin, items, action);
        Actions.endSS(admin, p, items, action);
    }
    public void executeActions(Player admin, OfflinePlayer p, ConfigurationSection items, String action) {
        Actions.executeConsoleCommand(p, items, action);
        Actions.executePlayerCommand(admin, p, items, action);
        Actions.closeInventory(admin, items, action);
        Actions.endSS(admin, p, items, action);
    }
}