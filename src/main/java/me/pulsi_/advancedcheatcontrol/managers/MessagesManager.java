package me.pulsi_.advancedcheatcontrol.managers;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessagesManager {

    private AdvancedCheatControl plugin;
    public MessagesManager(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    public final void frozenMessage(Player p) {
        if (plugin.config().getString("Freeze-Options.Message.Text") == null) return;
        p.sendMessage(addPrefix(plugin.config().getString("Freeze-Options.Message.Text")));
    }

    public final void broadcastQuitInSS(Player p) {
        if (plugin.config().getString("ScreenShare-Options.Quit-Punishment.Broadcast-Message.Text") == null) return;
        Bukkit.broadcastMessage(addPrefix(plugin.config().getString("ScreenShare-Options.Quit-Punishment.Broadcast-Message.Text").replace("%player%", p.getName())));
    }

    public final void helpMessage(CommandSender s) {
        if (plugin.messages().getString("Help") == null) return;
        for (String msg : plugin.messages().getStringList("Help")) {
            s.sendMessage(ChatUtils.color(msg));
        }
    }

    public final void cannotFreezePlayer(CommandSender s) {
        if (plugin.messages().getString("Cannot-Freeze-Player") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Cannot-Freeze-Player")));
    }

    public final void cannotSSPlayer(CommandSender s) {
        if (plugin.messages().getString("Cannot-SS-Player") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Cannot-SS-Player")));
    }

    public final void cannotUseEnderPearl(Player p) {
        if (plugin.messages().getString("Cannot-Use-Enderpearl") == null) return;
        p.sendMessage(addPrefix(plugin.messages().getString("Cannot-Use-Enderpearl")));
    }

    public final void alreadyInSS(CommandSender s) {
        if (plugin.messages().getString("Already-In-SS") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Already-In-SS")));
    }

    public final void cannotSSYourself(CommandSender s) {
        if (plugin.messages().getString("Cannot-SS-Yourself") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Cannot-SS-Yourself")));
    }

    public final void ssEnded(Player p) {
        if (plugin.messages().getString("ScreenShare-Ended") == null) return;
        p.sendMessage(addPrefix(plugin.messages().getString("ScreenShare-Ended")));
    }

    public final void alreadyFrozen(CommandSender s, Player p) {
        if (plugin.messages().getString("Already-Frozen") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Already-Frozen").replace("%player%", p.getName())));
    }

    public final void alreadyUnfrozen(CommandSender s, Player p) {
        if (plugin.messages().getString("Already-Unfrozen") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Already-Unfrozen").replace("%player%", p.getName())));
    }

    public final void ssStarted(CommandSender s, Player p) {
        if (plugin.messages().getString("ScreenShare-Started") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("ScreenShare-Started").replace("%player%", p.getName())));
    }

    public final void underSS(Player p) {
        if (plugin.messages().getString("Under-ScreenShare") == null) return;
        p.sendMessage(addPrefix(plugin.messages().getString("Under-ScreenShare")));
    }

    public final void ssEndUsage(CommandSender s) {
        if (plugin.messages().getString("Screen-Share-End") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Screen-Share-End")));
    }

    public final void internalError(CommandSender s) {
        if (plugin.messages().getString("Internal-Error") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Internal-Error")));
    }

    public final void reloadCommand(CommandSender s) {
        if (plugin.messages().getString("Reload-Message") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Reload-Message")));
    }

    public final void unknownCommand(CommandSender s) {
        if (plugin.messages().getString("Unknown-Command") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Unknown-Command")));
    }

    public final void successfullyUnfrozen(CommandSender s, Player p) {
        if (plugin.messages().getString("Successfully-Unfrozen") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Successfully-Unfrozen").replace("%player%", p.getName())));
    }

    public final void successfullyFrozen(CommandSender s, Player p) {
        if (plugin.messages().getString("Successfully-Frozen") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Successfully-Frozen").replace("%player%", p.getName())));
    }

    public final void unfreezeUsage(CommandSender s) {
        if (plugin.messages().getString("Unfreeze-Usage") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Unfreeze-Usage")));
    }

    public final void freezeUsage(CommandSender s) {
        if (plugin.messages().getString("Freeze-Usage") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Freeze-Usage")));
    }

    public final void missingPlayerWarp(CommandSender s) {
        if (plugin.messages().getString("Player-Warp-Missing") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Player-Warp-Missing")));
    }

    public final void missingAdminWarp(CommandSender s) {
        if (plugin.messages().getString("Admin-Warp-Missing") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Admin-Warp-Missing")));
    }

    public final void setAdminExit(CommandSender s) {
        if (plugin.messages().getString("Successfully-Set-AdminExit") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Successfully-Set-AdminExit")));
    }

    public final void setPlayerExit(CommandSender s) {
        if (plugin.messages().getString("Successfully-Set-PlayerExit") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Successfully-Set-PlayerExit")));
    }

    public final void setAdminWarp(CommandSender s) {
        if (plugin.messages().getString("Successfully-Set-AdminWarp") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Successfully-Set-AdminWarp")));
    }

    public final void setPlayerWarp(CommandSender s) {
        if (plugin.messages().getString("Successfully-Set-PlayerWarp") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Successfully-Set-PlayerWarp")));
    }

    public final void noPermission(CommandSender s) {
        if (plugin.messages().getString("No-Permission") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("No-Permission")));
    }

    public final void ssUsage(CommandSender s) {
        if (plugin.messages().getString("Screen-Share-Usage") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Screen-Share-Usage")));
    }

    public final void setExitUsage(CommandSender s) {
        if (plugin.messages().getString("SetExit-Usage") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("SetExit-Usage")));
    }

    public final void setWarpUsage(CommandSender s) {
        if (plugin.messages().getString("SetWarp-Usage") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("SetWarp-Usage")));
    }

    public final void notPlayer(CommandSender s) {
        if (plugin.messages().getString("Not-Player") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Not-Player")));
    }

    public final void invalidPlayer(CommandSender s) {
        if (plugin.messages().getString("Invalid-Player") == null) return;
        s.sendMessage(addPrefix(plugin.messages().getString("Invalid-Player")));
    }

    private String addPrefix(String message) {
        if (plugin.config().getString("Prefix") == null) return ChatUtils.color("" + message);
        return ChatUtils.color(plugin.config().getString("Prefix") + message);
    }
}
