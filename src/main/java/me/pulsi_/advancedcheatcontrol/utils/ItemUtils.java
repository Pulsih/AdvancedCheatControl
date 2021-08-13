package me.pulsi_.advancedcheatcontrol.utils;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    private static ItemStack barrier = new ItemStack(Material.BARRIER);
    public static ItemStack createItemStack(ConfigurationSection c) {
        ItemStack item;
        try {
            if (c.getString("Material").contains(":")) {
                String[] itemData = c.getString("Material").split(":");
                item = new ItemStack(Material.valueOf(itemData[0]), 1, Byte.parseByte(itemData[1]));
            } else {
                item = new ItemStack(Material.valueOf(c.getString("Material")));
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            item = barrier;
        }
        ItemMeta itemMeta = item.getItemMeta();
        try {
            String displayName = c.getString("Displayname");
            itemMeta.setDisplayName(ChatUtils.color(displayName));
        } catch (NullPointerException | IllegalArgumentException e) {
            itemMeta.setDisplayName(ChatUtils.color("&c&l*CANNOT FIND DISPLAYNAME*"));
        }
        List<String> lore = new ArrayList<>();
        if (c.getStringList("Lore") != null) {
            for (String lines : c.getStringList("Lore")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', lines));
            }
            itemMeta.setLore(lore);
        }
        if (c.getBoolean("Glowing")) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1, false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack guiFiller(AdvancedCheatControl plugin) {
        ItemStack filler;
        try {
            filler = new ItemStack(Material.valueOf(plugin.gui().getString("Gui-Filler.Material")));
        } catch (NullPointerException | IllegalArgumentException e) {
            filler = barrier;
        }
        ItemMeta fillerMeta = filler.getItemMeta();
            fillerMeta.setDisplayName(" ");

        filler.setItemMeta(fillerMeta);
        return filler;
    }
}