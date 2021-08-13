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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class FreezeCommand implements CommandExecutor {

    private static BukkitTask task;
    private final AdvancedCheatControl plugin;
    public FreezeCommand(AdvancedCheatControl plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

        MessagesManager messMan = new MessagesManager(plugin);

        if (args.length == 0) {
            if (!s.hasPermission("advancedcheatcontrol.freeze")) {
                messMan.noPermission(s);
                return false;
            }
            messMan.freezeUsage(s);
            return false;
        }

        if (args.length == 1) {
            if (!s.hasPermission("advancedcheatcontrol.freeze")) {
                messMan.noPermission(s);
                return false;
            }
            if (Bukkit.getPlayerExact(args[0]) == null) {
                messMan.invalidPlayer(s);
                return false;
            }
            Player p = Bukkit.getPlayerExact(args[0]);
            if (p.hasPermission("advancedcheatcontrol.freeze.exempt")) {
                messMan.cannotFreezePlayer(s);
                return false;
            }
            if (SetUtils.FREEZE_PLAYER.contains(p.getUniqueId())) {
                messMan.alreadyFrozen(s, p);
                return false;
            }
            SetUtils.FREEZE_PLAYER.add(p.getUniqueId());
            if (plugin.config().getBoolean("Freeze-Options.Give-Blindness"))
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999999,0), true);
            sendTitle(p);
            sendMessage(p, messMan);
            messMan.successfullyFrozen(s, p);
            playSound(p);
            return false;
        }
        messMan.unknownCommand(s);
        return true;
    }

    private void sendMessage(Player p, MessagesManager messMan) {
        if (!plugin.config().getBoolean("Freeze-Options.Message.Enabled")) return;
        if (plugin.config().getBoolean("Freeze-Options.Message.Persistent")) {
            task = Bukkit.getScheduler().runTaskTimer(plugin, () -> messMan.frozenMessage(p), 0, 60L);
        } else {
            messMan.frozenMessage(p);
        }
    }

    private void sendTitle(Player p) {
        if (!plugin.config().getBoolean("Freeze-Options.Title.Enabled")) return;
        String title = plugin.config().getString("Freeze-Options.Title.Text");
        if (plugin.config().getBoolean("Freeze-Options.Title.Persistent")) {
            task = Bukkit.getScheduler().runTaskTimer(plugin, () -> MethodUtils.sendTitle(title, p), 0, 60L);
        } else {
            MethodUtils.sendTitle(title, p);
        }
    }

    private void playSound(Player p) {
        if (plugin.config().getBoolean("Freeze-Options.Sound-On-Freeze.Enabled")) {
            String sound = plugin.config().getString("Freeze-Options.Sound-On-Freeze.Sound");
            MethodUtils.playSound(sound, p);
        }
    }

    public static void cancelTasks() {
        if (task != null) task.cancel();
    }
}