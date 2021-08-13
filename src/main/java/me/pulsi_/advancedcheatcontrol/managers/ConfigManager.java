package me.pulsi_.advancedcheatcontrol.managers;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final AdvancedCheatControl plugin;
    private File configFile, messagesFile, storageFile, guiFile;
    private FileConfiguration config, messagesConfig, storageConfig, guiConfig;

    public ConfigManager(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    public void createConfigs() {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        storageFile = new File(plugin.getDataFolder(), "storage.yml");
        guiFile = new File(plugin.getDataFolder(), "gui.yml");

        if (!configFile.exists()) {
            configFile.getParentFile().mkdir();
            plugin.saveResource("config.yml", false);
        }
        if (!messagesFile.exists()) {
            messagesFile.getParentFile().mkdir();
            plugin.saveResource("messages.yml", false);
        }
        if (!storageFile.exists()) {
            storageFile.getParentFile().mkdir();
            plugin.saveResource("storage.yml", false);
        }
        if (!guiFile.exists()) {
            guiFile.getParentFile().mkdir();
            plugin.saveResource("gui.yml", false);
        }

        config = new YamlConfiguration();
        messagesConfig = new YamlConfiguration();
        storageConfig = new YamlConfiguration();
        guiConfig = new YamlConfiguration();

        try {
            config.load(configFile);
            messagesConfig.load(messagesFile);
            storageConfig.load(storageFile);
            guiConfig.load(guiFile);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }
    public FileConfiguration getMessages() {
        return messagesConfig;
    }
    public FileConfiguration getStorage() {
        return storageConfig;
    }
    public FileConfiguration getGui() {
        return guiConfig;
    }

    public void reloadConfigs() {
        config = YamlConfiguration.loadConfiguration(configFile);
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        storageConfig = YamlConfiguration.loadConfiguration(storageFile);
        guiConfig = YamlConfiguration.loadConfiguration(guiFile);
    }

    public void saveStorage() {
        try {
            storageConfig.save(storageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
