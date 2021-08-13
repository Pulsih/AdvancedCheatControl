package me.pulsi_.advancedcheatcontrol.gui;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.ChatUtils;
import me.pulsi_.advancedcheatcontrol.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ConfirmGUI {

    private final AdvancedCheatControl plugin;
    public ConfirmGUI(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    public void openConfigGUI(Player p) {

        String title = plugin.gui().getString("Title");
        int lines = plugin.gui().getInt("Lines");

        Inventory confirmGUI = Bukkit.createInventory(null, guiLines(lines), ChatUtils.color(title));

        ConfigurationSection c = plugin.gui().getConfigurationSection("Items");
        for (String items : c.getKeys(false)) {
            try {
                confirmGUI.setItem(c.getConfigurationSection(items).getInt("Slot") - 1, ItemUtils.createItemStack(c.getConfigurationSection(items)));
            } catch (ArrayIndexOutOfBoundsException ex) {
                plugin.getServer().getConsoleSender().sendMessage(ChatUtils.color("&8[&a&lA&c&lC&d&lC&8] &cThere are some items placed incorrectly in the GUI!"));
                confirmGUI.addItem(ItemUtils.createItemStack(c.getConfigurationSection(items)));
            }
        }

        if (plugin.gui().getBoolean("Gui-Filler.Enabled")) {
            for (int i = 0; i < guiLines(lines); i++) {
                confirmGUI.getItem(i);
                if (confirmGUI.getItem(i) == null) confirmGUI.setItem(i, ItemUtils.guiFiller(plugin));
            }
        }

        p.openInventory(confirmGUI);
    }

    public int guiLines(int number) {
        switch (number) {
            case 1: return 9;
            case 2: return  18;
            default: return 27;
            case 4: return  36;
            case 5: return  45;
            case 6: return  54;
        }
    }
}