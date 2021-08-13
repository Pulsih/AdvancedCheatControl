package me.pulsi_.advancedcheatcontrol;

import me.pulsi_.advancedcheatcontrol.managers.ConfigManager;
import me.pulsi_.advancedcheatcontrol.managers.DataManager;
import me.pulsi_.advancedcheatcontrol.others.bStats;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedCheatControl extends JavaPlugin {

    private ConfigManager configManager;
    private DataManager dataManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        configManager.createConfigs();
        dataManager = new DataManager(this);
        dataManager.loadCommands();
        dataManager.loadEvents();
        dataManager.startupMessage();

        new bStats(this, 12419);
    }

    @Override
    public void onDisable() {
        dataManager.shutdownMessage();
    }

    public FileConfiguration config() {
        return configManager.getConfig();
    }
    public FileConfiguration messages() {
        return configManager.getMessages();
    }
    public FileConfiguration storage() {
        return configManager.getStorage();
    }
    public FileConfiguration gui() {
        return configManager.getGui();
    }
    public void reloadConfigs() {
        configManager.reloadConfigs();
    }
    public void saveStorage() {
        configManager.saveStorage();
    }
}