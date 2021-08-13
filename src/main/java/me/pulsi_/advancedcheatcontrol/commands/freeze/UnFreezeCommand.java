package me.pulsi_.advancedcheatcontrol.commands.freeze;

import me.pulsi_.advancedcheatcontrol.AdvancedCheatControl;
import me.pulsi_.advancedcheatcontrol.managers.MessagesManager;
import me.pulsi_.advancedcheatcontrol.utils.MethodUtils;
import me.pulsi_.advancedcheatcontrol.utils.SetUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class UnFreezeCommand implements CommandExecutor {

    private AdvancedCheatControl plugin;
    public UnFreezeCommand(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

        MessagesManager messMan = new MessagesManager(plugin);

        if (args.length == 0) {
            if (!s.hasPermission("advancedcheatcontrol.unfreeze")) {
                messMan.noPermission(s);
                return false;
            }
            messMan.unfreezeUsage(s);
            return false;
        }

        if (args.length == 1) {
            if (!s.hasPermission("advancedcheatcontrol.unfreeze")) {
                messMan.noPermission(s);
                return false;
            }
            if (Bukkit.getPlayerExact(args[0]) == null) {
                messMan.invalidPlayer(s);
                return false;
            }
            Player p = Bukkit.getPlayerExact(args[0]);
            if (!SetUtils.FREEZE_PLAYER.contains(p.getUniqueId())) {
                messMan.alreadyUnfrozen(s, p);
                return false;
            }
            SetUtils.FREEZE_PLAYER.remove(p.getUniqueId());
            FreezeCommand.cancelTasks();
            if (p.hasPotionEffect(PotionEffectType.BLINDNESS))
                p.removePotionEffect(PotionEffectType.BLINDNESS);
            sendTitle(p);
            messMan.successfullyUnfrozen(s, p);
            playSound(p);
            return false;
        }
        messMan.unknownCommand(s);
        return true;
    }

    private void sendTitle(Player p) {
        if (!plugin.config().getBoolean("Freeze-Options.Unfroze-Title.Enabled")) return;
        String title = plugin.config().getString("Freeze-Options.Unfroze-Title.Text");
        MethodUtils.sendTitle(title, p);
    }

    private void playSound(Player p) {
        if (plugin.config().getBoolean("Freeze-Options.Sound-On-Unfreeze.Enabled")) {
            String sound = plugin.config().getString("Freeze-Options.Sound-On-Unfreeze.Sound");
            MethodUtils.playSound(sound, p);
        }
    }
}
