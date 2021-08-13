package me.pulsi_.advancedcheatcontrol.managers;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.commands.MainCommand;
import me.pulsi_.advancedcheatcontrol.commands.MainCommandTab;
import me.pulsi_.advancedcheatcontrol.commands.freeze.FreezeCommand;
import me.pulsi_.advancedcheatcontrol.commands.freeze.UnFreezeCommand;
import me.pulsi_.advancedcheatcontrol.commands.screenShare.ScreenShareCommand;
import me.pulsi_.advancedcheatcontrol.commands.screenShare.ScreenShareTab;
import me.pulsi_.advancedcheatcontrol.events.*;
import me.pulsi_.advancedcheatcontrol.gui.ConfirmGUIListener;
import me.pulsi_.advancedcheatcontrol.utils.ChatUtils;

public class DataManager {

    private AdvancedCheatControl plugin;
    public DataManager(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    public void loadCommands() {
        plugin.getCommand("advancedcheatcontrol").setExecutor(new MainCommand(plugin));
        plugin.getCommand("advancedcheatcontrol").setTabCompleter(new MainCommandTab());
        plugin.getCommand("screenshare").setExecutor(new ScreenShareCommand(plugin));
        plugin.getCommand("screenshare").setTabCompleter(new ScreenShareTab());
        plugin.getCommand("freeze").setExecutor(new FreezeCommand(plugin));
        plugin.getCommand("unfreeze").setExecutor(new UnFreezeCommand(plugin));
    }

    public void loadEvents() {
        plugin.getServer().getPluginManager().registerEvents(new MoveEvent(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EntityDamageByEntity(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnCommand(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ConfirmGUIListener(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerChat(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerQuit(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ItemClick(plugin), plugin);
    }

    public void startupMessage() {
        ChatUtils.consoleMessage("");
        ChatUtils.consoleMessage(" &fEnabling &8[&a&lAdvanced&c&lCheat&d&lControl&8]");
        ChatUtils.consoleMessage(" &fVersion &dv" + plugin.getDescription().getVersion());
        ChatUtils.consoleMessage(" &fMade by &dPulsi_ &c<3");
        ChatUtils.consoleMessage("");
    }

    public void shutdownMessage() {
        ChatUtils.consoleMessage("");
        ChatUtils.consoleMessage(" &fDisabling &8[&a&lAdvanced&c&lCheat&d&lControl&8]");
        ChatUtils.consoleMessage("");
    }
}