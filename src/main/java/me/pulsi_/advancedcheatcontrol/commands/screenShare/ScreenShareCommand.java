package me.pulsi_.advancedcheatcontrol.commands.screenShare;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.gui.ConfirmGUI;
import me.pulsi_.advancedcheatcontrol.managers.MessagesManager;
import me.pulsi_.advancedcheatcontrol.utils.ChatUtils;
import me.pulsi_.advancedcheatcontrol.utils.MapUtils;
import me.pulsi_.advancedcheatcontrol.utils.MethodUtils;
import me.pulsi_.advancedcheatcontrol.utils.SetUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScreenShareCommand implements CommandExecutor {

    private final AdvancedCheatControl plugin;
    public ScreenShareCommand(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

        MessagesManager messMan = new MessagesManager(plugin);

        if (args.length == 0) {
            if (!s.hasPermission("advancedcheatcontrol.screenshare")) {
                messMan.noPermission(s);
                return false;
            }
            if (!(s instanceof Player)) {
                messMan.notPlayer(s);
                return false;
            }
            if (!MapUtils.SCREEN_SHARING_ADMIN.containsKey(((Player) s).getUniqueId())) {
                messMan.ssUsage(s);
            } else {
                messMan.ssEndUsage(s);
            }
        }

        if (args.length == 1) {
            if (!s.hasPermission("advancedcheatcontrol.screenshare")) {
                messMan.noPermission(s);
                return false;
            }
            if (!(s instanceof Player)) {
                messMan.notPlayer(s);
                return false;
            }
            Player admin = (Player) s;
            if (Bukkit.getPlayerExact(args[0]) == admin) {
                messMan.cannotSSYourself(admin);
                return false;
            }
            if (!MapUtils.SCREEN_SHARING_ADMIN.containsKey(admin.getUniqueId())) {
                if (plugin.storage().getString("Player-Warp") == null) {
                    messMan.missingPlayerWarp(s);
                    return false;
                }
                if (plugin.storage().getString("Admin-Warp") == null) {
                    messMan.missingAdminWarp(s);
                    return false;
                }
                if (Bukkit.getPlayerExact(args[0]) == null) {
                    messMan.invalidPlayer(s);
                    return false;
                }
                Player p = Bukkit.getPlayerExact(args[0]);
                if (SetUtils.SCREEN_SHARING.contains(p.getUniqueId()) || MapUtils.SCREEN_SHARING_ADMIN.containsKey(p.getUniqueId())) {
                    messMan.alreadyInSS(s);
                    return false;
                }
                if (p.hasPermission("advancedcheatcontrol.screenshare.exempt")) {
                    messMan.cannotSSPlayer(s);
                    return false;
                }
                try {
                    addMaps(admin, p);
                    p.teleport(MethodUtils.getLocationFromCustomString(plugin.storage().getString("Player-Warp")));
                    admin.teleport(MethodUtils.getLocationFromCustomString(plugin.storage().getString("Admin-Warp")));
                    sentTitle(p);
                    playSound(admin);
                    playSound(p);
                    messMan.underSS(p);
                    messMan.ssStarted(s, p);
                } catch (NumberFormatException e) {
                    messMan.internalError(s);
                    ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
                    ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: &f" + e.getMessage() + "&c. &csituated in &astorage.yml&c.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    messMan.internalError(s);
                    ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
                    ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: Expecting &f6 &cstrings but found &f" + e.getMessage() + "&c. &csituated in &astorage.yml&c.");
                } catch (NullPointerException e) {
                    messMan.internalError(s);
                    ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cWarning, an error has occurred while trying to start the ScreenShare.");
                    ChatUtils.consoleMessage("&8[&a&lAdvanced&c&lCheat&d&lControl&8] &cError cause: &fUnknown world&c. situated in &astorage.yml&c.");
                }
            } else {
                if (args[0].equalsIgnoreCase("end")) {
                    if (plugin.config().getBoolean("ScreenShare-Options.End-With-Gui")) {
                        new ConfirmGUI(plugin).openConfigGUI(admin);
                    } else {
                        if (MapUtils.SCREEN_SHARING_ADMIN.get(admin.getUniqueId()) == null) {
                            OfflinePlayer p = Bukkit.getOfflinePlayer(MapUtils.SCREEN_SHARING_ADMIN.get(admin.getUniqueId()));
                            MethodUtils.endScreenShare(admin, p, plugin);
                        } else {
                            Player p = Bukkit.getPlayerExact(MapUtils.SCREEN_SHARING_ADMIN.get(admin.getUniqueId()));
                            MethodUtils.endScreenShare(admin, p, plugin);
                        }
                    }
                    return false;
                }
                messMan.ssEndUsage(s);
            }
        }
        return true;
    }

    private void sentTitle(Player p) {
        if (plugin.config().getBoolean("ScreenShare-Options.Title.Enabled")) {
            String title = plugin.config().getString("ScreenShare-Options.Title.Text");
            MethodUtils.sendTitle(title, p);
        }
    }

    private void playSound(Player p) {
        if (plugin.config().getBoolean("ScreenShare-Options.Sound-On-Start.Enabled")) {
            String sound = plugin.config().getString("ScreenShare-Options.Sound-On-Start.Sound");
            MethodUtils.playSound(sound, p);
        }
    }

    private void addMaps(Player admin, Player p) {
        SetUtils.SCREEN_SHARING.add(p.getUniqueId());
        MapUtils.SCREEN_SHARING_ADMIN.put(admin.getUniqueId(), p.getName());
        if (plugin.config().getBoolean("ScreenShare-Options.Freeze-On-Start"))
            SetUtils.FREEZE_PLAYER.add(p.getUniqueId());
        MapUtils.PRIVATE_MESSAGES.put(admin.getUniqueId(), p.getName());
        MapUtils.PRIVATE_MESSAGES.put(p.getUniqueId(), admin.getName());
    }
}