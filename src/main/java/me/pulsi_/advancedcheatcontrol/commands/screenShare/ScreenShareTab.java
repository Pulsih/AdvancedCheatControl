package me.pulsi_.advancedcheatcontrol.commands.screenShare;

import me.pulsi_.advancedcheatcontrol.utils.MapUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScreenShareTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender s, Command command, String alias, String[] args) {

        if (s instanceof Player) {
            if (MapUtils.SCREEN_SHARING_ADMIN.containsKey(((Player) s).getUniqueId())) {
                List<String> args1 = new ArrayList<>();
                args1.add("end");

                List<String> resultArgs1 = new ArrayList<>();
                if (args.length == 1) {
                    for (String a : args1) {
                        if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                            resultArgs1.add(a);
                    }
                    return resultArgs1;
                }
            }
        }
        return null;
    }
}
